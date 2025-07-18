package us.bringardner.shell.antlr.statement;

import java.io.IOException;
import java.util.List;

import us.bringardner.filesource.sh.FileSourceShParser.Statement_group1Context;
import us.bringardner.shell.ShellContext;
import us.bringardner.shell.antlr.Statement;

/*
( list )
Placing a list of commands between parentheses forces the shell to create a subshell (see Command Execution Environment), 
	and each of the commands in list is executed in that subshell environment. 
	Since the list is executed in a subshell, variable assignments do not remain in effect after the subshell completes.


{ list; }
Placing a list of commands between curly braces causes the list to be executed in the current shell context. 
	No subshell is created. The semicolon (or newline) following list is required.
 */
public class StatementGroup1 extends Statement{
	List<Statement> stmts;

	public StatementGroup1(Statement_group1Context context, List<Statement> stmts) {
		super(context);
		this.stmts = stmts;		
	}

	@Override
	protected int execute(ShellContext sc) throws IOException {
		Statement_group1Context ctx = (Statement_group1Context)getContext();
		if(ctx.LPAREN()!=null) {
			// new sub shell
			throw new IOException("new subshell not implemente");
		}
		
		int ret = 0;
		for(Statement s : stmts) {
			if( (ret=s.process(sc))!=0) {
				break;
			}
		}
		
		return ret;
	}

}
