package us.bringardner.shell.antlr.statement;

import java.io.IOException;

import us.bringardner.filesource.sh.FileSourceShParser.Boolean_statementContext;
import us.bringardner.filesource.sh.FileSourceShParser.StatementContext;
import us.bringardner.shell.ShellContext;
import us.bringardner.shell.antlr.Statement;

public class LogicStatement extends Statement{

	Statement left;
	Statement right;
	Boolean_statementContext boolean_statement;
	public LogicStatement(StatementContext context,Statement left, Statement right) {
		super(context);		 
		this.left = left;
		this.right = right;
	}

	public LogicStatement(StatementContext ctx, Boolean_statementContext boolean_statement) {
		super(ctx);
		this.boolean_statement = boolean_statement;
	}

	@Override
	protected int execute(ShellContext sc) throws IOException {
		
		StatementContext ctx = (StatementContext)getContext();
		
		if( boolean_statement!=null) {
			int ret = boolean_statement.boolean_().TRUE() != null?0:1;
			return ret;
		}
		
		int ret = left.process(sc);
		
		if( ctx.OR()!=null) {
			if( ret!=0) {
				ret = right.process(sc); 
			}
		} else if( ctx.AND()!=null) {
			if( ret==0) {
				ret = right.process(sc); 
			}
		} else {
			sc.stderr.println("Invalid logic statement="+ctx.getText());
			return 1;
		}
		return ret;
	}

}
