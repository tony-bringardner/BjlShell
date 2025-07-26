package us.bringardner.shell.antlr.statement;

import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

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
	String [] entries;

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
			entries = new String[args.length];
			for (int idx = 0; idx < entries.length; idx++) {
				entries[idx] = ""+args[idx].getValue(sc);
				maxLen = Math.max(maxLen, entries[idx].length());
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
						if( pos >=0 && pos < entries.length) {
							res = entries[pos];
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
		
		if( entries.length > l) {
			c = getCols(sc)/maxLen;
			l /= c;			
		}
		
		String fmt = "(%2d) %-"+maxLen+"s ";
		for(int idx = 0; idx < entries.length; idx+=c) {
			for(int col=0; col< c; col++ ) {
				int i = idx+col;
				if( i < entries.length) {
					sc.stderr.printf(fmt, (i+1),entries[i]);
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
