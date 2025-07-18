package us.bringardner.shell.antlr.statement;

import java.io.IOException;

import org.antlr.v4.runtime.ParserRuleContext;

import us.bringardner.filesource.sh.FileSourceShParser.MathStatementContext;
import us.bringardner.shell.ShellContext;
import us.bringardner.shell.antlr.Expression;
import us.bringardner.shell.antlr.Statement;

public class MathStatement extends Statement{

	Expression expr;

	public MathStatement(ParserRuleContext context) {
		super(context);
	}

	public MathStatement(MathStatementContext ctx, Expression expr) {
		this(ctx);
		this.expr = expr;
	}

	@Override
	protected int execute(ShellContext sc) throws IOException {
		int ret = 0;
		MathStatementContext ctx = ((MathStatementContext)getContext());
		// parameters alone are ignore, for now
		if( expr != null ) {
			expr.evaluate(sc);
		} else if(ctx.mathExpression()!=null ) {
			expr = new Expression(ctx.mathExpression().expression());
			expr.evaluate(sc);
		} else {
			throw new RuntimeException("Invlide math expression "+ctx.getText());
		}


		return ret;
	}


}
