package us.bringardner.shell.antlr.statement;

import java.io.IOException;

import org.antlr.v4.runtime.ParserRuleContext;

import us.bringardner.shell.ShellContext;
import us.bringardner.shell.antlr.Statement;

public class RdedirectStatement extends Statement{

	public RdedirectStatement(ParserRuleContext context) {
		super(context);
	}

	@Override
	protected int execute(ShellContext ctx) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

}
