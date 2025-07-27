package us.bringardner.shell.antlr.statement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.ParserRuleContext;

import us.bringardner.io.filesource.FileSource;
import us.bringardner.shell.ShellCommand;
import us.bringardner.shell.ShellContext;
import us.bringardner.shell.antlr.Compare;
import us.bringardner.shell.antlr.Expression;
import us.bringardner.shell.antlr.Statement;
import us.bringardner.shell.commands.Read;

public class SelectStatement extends LoopStatement{
	//   : SELECT ID IN argument+ SEMI? DO loop_statement+ DONE
	String varName;
	List<Statement> stmts;
	Compare compare;
	Expression expr;
	int maxLen;
	List<String> entries;

	public Compare getCompare() {
		return compare;
	}

	public void setCompare(Compare compare) {
		this.compare = compare;
	}

	public Expression getExpr() {
		return expr;
	}

	public void setExpr(Expression expr) {
		this.expr = expr;
	}

	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	public List<Statement> getStmts() {
		return stmts;
	}

	public void setStmts(List<Statement> stmts) {
		this.stmts = stmts;
	}



	public SelectStatement(ParserRuleContext context) {
		super(context);
	}


	public int execute(ShellContext sc) throws IOException {
		int ret = 0;

		if( args.length>0) {
			entries = new ArrayList<>();

			for (int idx = 0; idx < args.length; idx++) {
				String av = ""+args[idx].getValue(sc);
				if( ShellCommand.hasWildcard(av)) {
					List<FileSource> files = ShellCommand.getFiles(sc, av);
					if( files == null || files.isEmpty()) {
						entries.add(av);
						maxLen = Math.max(maxLen, av.length());
					} else {
						Collections.sort(files, new Comparator<FileSource>() {

							@Override
							public int compare(FileSource o1, FileSource o2) {
								return o1.getName().compareTo(o2.getName());
							}						
						});
						String clean = ShellCommand.removeWildcards(av);
						for(FileSource f : files) {
							String path = f.getAbsolutePath();
							int pos = path.indexOf(clean);
							if( pos >0) {
								path = path.substring(pos);
								entries.add(path);
							} else {
								entries.add(f.getName());
							}
							maxLen = Math.max(maxLen, entries.getLast().length());
						}
					}
				} else {
					entries.add(av);
					maxLen = Math.max(maxLen, av.length());
				}

			}

			maxLen += 6;
			Object tmp1 = sc.getVariable("PS3");
			if( tmp1 == null ) {
				tmp1 = "#? ";
			}
			String prompt = ""+tmp1;
			ShellContext.LoopControl tmp = null;
			Read r = new Read();

			while(!ShellContext.LoopControl.Break.equals(tmp)) {
				display(sc);

				String res = r.readLine(sc, prompt);
				sc.setVariable("REPLY", res);
				if( !res.isEmpty() ) {
					try {
						int pos = Integer.parseInt(res)-1;
						if( pos >=0 && pos < entries.size()) {
							res = entries.get(pos);
						}
						sc.setLocalVariable(varName, res);
					} catch (Exception e) {				
						sc.setLocalVariable(varName, "");
					}

					for(Statement stmt : stmts) {
						try {
							ret = stmt.process(sc);
						} catch(LoopControlException e) {
							if(e.howFar>1) {
								throw new LoopControlException(e.type, e.howFar-1);
							}
							tmp = e.type;
							break;
						}
					}
				}
			}

		}	
		return ret;
	}

	int lines =0;
	int cols = 0;

	private void display(ShellContext sc) {
		int l = getLines(sc);


		int c = 1;

		if( entries.size() > l) {
			c = getCols(sc)/maxLen;
			l /= c;			
		}

		String fmt = "(%2d) %-"+maxLen+"s ";
		for(int idx = 0; idx < entries.size(); idx+=c) {
			for(int col=0; col< c; col++ ) {
				int i = idx+col;
				if( i < entries.size()) {
					sc.stderr.printf(fmt, (i+1),entries.get(i));
				}
			}
			sc.stderr.println();
		}
	}

	private int getLines(ShellContext sc) {
		if( lines == 0 ) {
			try {
				lines = Integer.parseInt(""+sc.getVariable("LINES"));
			} catch (Exception e) {
				lines = 10;
			}

		}
		return lines;
	}
	private int getCols(ShellContext sc) {
		if( cols == 0 ) {
			try {
				cols = Integer.parseInt(""+sc.getVariable("COLUMNS"));
			} catch (Exception e) {
				cols = 80;
			}
		}
		return cols;
	}
}
