package us.bringardner.shell.antlr.expr;

import org.antlr.v4.runtime.ParserRuleContext;

import us.bringardner.filesource.sh.ExprParser.ExprContext;

public class Expr {

	private ParserRuleContext context;
	
	public Expr(ExprContext ctx) {
		this.context = ctx;
	}

}
