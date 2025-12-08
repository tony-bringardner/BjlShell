// Generated from /Volumes/Data/eclipse-git/BjlShell/Antlr4/ExprParser.g4 by ANTLR 4.13.2
package us.bringardner.filesource.sh;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExprParser}.
 */
public interface ExprParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExprParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(ExprParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(ExprParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(ExprParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(ExprParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#statement_type}.
	 * @param ctx the parse tree
	 */
	void enterStatement_type(ExprParser.Statement_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#statement_type}.
	 * @param ctx the parse tree
	 */
	void exitStatement_type(ExprParser.Statement_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#statment_terminator}.
	 * @param ctx the parse tree
	 */
	void enterStatment_terminator(ExprParser.Statment_terminatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#statment_terminator}.
	 * @param ctx the parse tree
	 */
	void exitStatment_terminator(ExprParser.Statment_terminatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(ExprParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(ExprParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(ExprParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(ExprParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#command_name}.
	 * @param ctx the parse tree
	 */
	void enterCommand_name(ExprParser.Command_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#command_name}.
	 * @param ctx the parse tree
	 */
	void exitCommand_name(ExprParser.Command_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#assingment}.
	 * @param ctx the parse tree
	 */
	void enterAssingment(ExprParser.AssingmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#assingment}.
	 * @param ctx the parse tree
	 */
	void exitAssingment(ExprParser.AssingmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(ExprParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(ExprParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(ExprParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(ExprParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#array_element}.
	 * @param ctx the parse tree
	 */
	void enterArray_element(ExprParser.Array_elementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#array_element}.
	 * @param ctx the parse tree
	 */
	void exitArray_element(ExprParser.Array_elementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(ExprParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(ExprParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(ExprParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(ExprParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#boolean}.
	 * @param ctx the parse tree
	 */
	void enterBoolean(ExprParser.BooleanContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#boolean}.
	 * @param ctx the parse tree
	 */
	void exitBoolean(ExprParser.BooleanContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExprParser#path}.
	 * @param ctx the parse tree
	 */
	void enterPath(ExprParser.PathContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExprParser#path}.
	 * @param ctx the parse tree
	 */
	void exitPath(ExprParser.PathContext ctx);
}