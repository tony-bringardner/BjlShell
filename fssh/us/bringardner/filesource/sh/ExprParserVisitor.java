// Generated from /Volumes/Data/eclipse-git/BjlShell/Antlr4/ExprParser.g4 by ANTLR 4.13.2
package us.bringardner.filesource.sh;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExprParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExprParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExprParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(ExprParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(ExprParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#statement_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement_type(ExprParser.Statement_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#statment_terminator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatment_terminator(ExprParser.Statment_terminatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#command}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand(ExprParser.CommandContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(ExprParser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#command_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommand_name(ExprParser.Command_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#assingment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssingment(ExprParser.AssingmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(ExprParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(ExprParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#array_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray_element(ExprParser.Array_elementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(ExprParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(ExprParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#boolean}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean(ExprParser.BooleanContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExprParser#path}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPath(ExprParser.PathContext ctx);
}