package us.bringardner.shell.antlr.statement;

import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

import us.bringardner.shell.ShellContext;
import us.bringardner.shell.antlr.Argument;
import us.bringardner.shell.antlr.Statement;

public class ForStatement extends LoopStatement{
	//   : FOR ID IN argument+ SEMI? DO loop_statement+ DONE
	String varName;
	List<Statement> stmts;

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



	public ForStatement(ParserRuleContext context) {
		super(context);
	}

	protected int execute(ShellContext sc) throws IOException {
		int ret = 0;
		ShellContext.LoopControl tmp = null;
		for(Argument arg : args ) {
			if(ShellContext.LoopControl.Break.equals(tmp)) {
				break;
			}
			Object val = arg.getValue(sc);
			sc.setLocalVariable(varName, val);	
			
			
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

		return ret;
	}
}
