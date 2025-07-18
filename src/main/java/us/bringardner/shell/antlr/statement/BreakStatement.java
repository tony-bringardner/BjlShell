package us.bringardner.shell.antlr.statement;

import java.io.IOException;

import org.antlr.v4.runtime.ParserRuleContext;

import us.bringardner.filesource.sh.FileSourceShParser.Loop_controll_statementContext;
import us.bringardner.shell.ShellContext;
import us.bringardner.shell.ShellContext.LoopControl;
import us.bringardner.shell.antlr.Statement;

public class BreakStatement extends Statement{

	public BreakStatement(ParserRuleContext context) {
		super(context);		 
	}

	@Override
	protected int execute(ShellContext nc) throws IOException {
		Loop_controll_statementContext ctx = (Loop_controll_statementContext)getContext();
		int n = 1;
		if( ctx.NUMBER()!=null) {
			n = Integer.parseInt(ctx.NUMBER().getText());
		}
		throw new LoopStatement.LoopControlException(LoopControl.Break, n);
		
	}

}
