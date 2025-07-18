package us.bringardner.shell.antlr.statement;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.antlr.v4.runtime.ParserRuleContext;

import us.bringardner.filesource.sh.FileSourceShParser.AssociativeArrayElementContext;
import us.bringardner.filesource.sh.FileSourceShParser.AssociativeArrayInitializerContext;
import us.bringardner.filesource.sh.FileSourceShParser.DeclareAssociativeArrayStatementContext;
import us.bringardner.shell.ShellContext;
import us.bringardner.shell.antlr.Argument;
import us.bringardner.shell.antlr.Statement;

public class DeclareAssociateArrayStatement extends Statement{

	public DeclareAssociateArrayStatement(ParserRuleContext context) {
		super(context);
	}

	/*


// New rule to support 'declare -A my_array' and 'declare -A my_array=([key1]=value1 [key2]=value2)'
declareAssociativeArrayStatement
    : DECLARE_A id1=ID (NL? EQ NL? associativeArrayInitializer)? NL? CMD_TERMINATOR?
    ;

associativeArrayInitializer
    : NL? LPAREN NL? (associativeArrayElement NL?) * RPAREN
    ;

associativeArrayElement
    :NL? LSQUARE key=argument RSQUARE EQ value=argument NL?
    ;    
	 */
	@Override
	protected int execute(ShellContext sc) throws IOException {
		int ret = 0;
		DeclareAssociativeArrayStatementContext ctx = (DeclareAssociativeArrayStatementContext) getContext();
		String name = ctx.id1.getText();
		Map<String,Object> map = new TreeMap<>();
		AssociativeArrayInitializerContext init = ctx.associativeArrayInitializer();
		for( AssociativeArrayElementContext e : init.associativeArrayElement()) {
			Argument keyArg = new Argument(e.key);
			Object key = keyArg.getValue(sc);
			Argument valArg = new Argument(e.value);
			Object val = valArg.getValue(sc);
			map.put(""+key, val);
		}
		sc.setVariable(name, map);
		return ret;
	}

}
