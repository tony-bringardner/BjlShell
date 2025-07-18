package us.bringardner.shell.antlr.statement;

import java.io.IOException;
import java.util.List;

import us.bringardner.filesource.sh.FileSourceShParser.WhileStatementContext;
import us.bringardner.shell.ShellContext;
import us.bringardner.shell.antlr.Compare;
import us.bringardner.shell.antlr.Statement;

public class WhileStatement extends LoopStatement{

	Compare compare;
	List<Statement> stmts;

	public WhileStatement(WhileStatementContext context) {
		super(context);
	}

	public WhileStatement(WhileStatementContext context, Compare compare, List<Statement> stmts) {
		this(context);
		this.compare = compare;
		this.stmts = stmts;
	}

	public Compare getCompare() {
		return compare;
	}

	public void setCompare(Compare compare) {
		this.compare = compare;
	}

	public List<Statement> getStmts() {
		return stmts;
	}

	public void setStmts(List<Statement> stmts) {
		this.stmts = stmts;
	}

	@Override
	protected int execute(ShellContext sc) throws IOException {
		int ret = 0;

		WhileStatementContext ctx = ((WhileStatementContext)getContext());
		Compare test = new Compare(ctx.compare());
		ShellContext.LoopControl trigger = null;
		while( test.evaluate(sc) && !ShellContext.LoopControl.Break.equals(trigger)) {

			for(Statement stmt : stmts) {
				try {
					ret = stmt.process(sc);
				} catch(LoopControlException e) {
					if(e.howFar>1) {
						throw new LoopControlException(e.type, e.howFar-1);
					}
					trigger = e.type;
					break;
				}
				if( ret != 0 ) {
					return ret;
				}

			}						
		}

		return ret;
	}

}
