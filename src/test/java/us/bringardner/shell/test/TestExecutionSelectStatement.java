package us.bringardner.shell.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import us.bringardner.shell.Console;


@TestMethodOrder(OrderAnnotation.class)
public class TestExecutionSelectStatement extends AbstractConsoleTest {

	
	
	@BeforeAll
	public static void setup() throws IOException {
		AbstractConsoleTest.setup("TestFiles");
		if(!testFilesDir.exists() ) {
			System.out.println(testFilesDir.getAbsolutePath()+" does not exists");
		}
	}
	
	public static ExecuteResult executeCommand(String command,String stdIn) {
		PrintStream stdout= System.out;
		PrintStream stderr= System.err;
		InputStream stdin = System.in;
		
		ExecuteResult ret = new ExecuteResult();
		System.setOut(new PrintStream(ret.bao));
		System.setErr(new PrintStream(ret.bae));
		System.setIn(new ByteArrayInputStream(stdIn.getBytes()));
		Console console = new Console();
		ret.exitCode=console.executeUsingAntlr(command);
		System.setIn(stdin);
		System.setOut(stdout);
		System.setErr(stderr);
		
		if( ret.bae.size()!=0) {
			//System.out.println(new String(ret.bae.toByteArray()));
		}
		return ret;
	}
	

	@Test
	public void testSelectStatent01() throws Exception{
		String cmd = 
				 "select fname in 'one       ' two three four five;\n"
				 + "do\n"
				 + "	echo \"you picked $fname ($REPLY)\"\n"
				 + "	break;\n"
				 + "done\n"
				;
		
		
		String expectOut = 	"#? you picked two (2)\n";
		String expectErr = 	"( 1) one              \n"
				+ "( 2) two              \n"
				+ "( 3) three            \n"
				+ "( 4) four             \n"
				+ "( 5) five             \n";
		String stdin = "2\n";
		
		ExecuteResult res = executeCommand(cmd,stdin);
		String out = new String(res.bao.toByteArray());
		String err = new String(res.bae.toByteArray());
		assertEquals(0, res.exitCode);
		assertEquals(expectOut, out);
		assertEquals(expectErr, err);
	}

	@Test
	public void testSelectStatent02() throws Exception{
		String cmd = 
				 "select fname in 'one       ' two three four five;\n"
				 + "do\n"
				 + "	echo you picked $fname \"($REPLY)\"\n"
				 + "	break;\n"
				 + "done\n"
				;
		
		
		String expectOut = 	"#? #? you picked two (2)\n";
		String expectErr = 	
				"( 1) one              \n"
				+ "( 2) two              \n"
				+ "( 3) three            \n"
				+ "( 4) four             \n"
				+ "( 5) five             \n"
				+ "( 1) one              \n"
				+ "( 2) two              \n"
				+ "( 3) three            \n"
				+ "( 4) four             \n"
				+ "( 5) five             \n"
				+ "";
		String stdin = "\n2\n";
		
		ExecuteResult res = executeCommand(cmd,stdin);
		String out = new String(res.bao.toByteArray());
		String err = new String(res.bae.toByteArray());
		assertEquals(0, res.exitCode);
		assertEquals(expectOut, out);
		assertEquals(expectErr, err);
	}
	
	@Test
	public void testSelectStatent03() throws Exception{
		String cmd = 
				 "select fname in 'one       ' two three four five;\n"
				 + "do\n"
				 + "	echo \"you picked $fname ($REPLY)\"\n"
				 + "	break;\n"
				 + "done\n"
				;
		
		
		String expectOut = 	"#? you picked  (test)\n";
		String expectErr = 	"( 1) one              \n"
				+ "( 2) two              \n"
				+ "( 3) three            \n"
				+ "( 4) four             \n"
				+ "( 5) five             \n";
		String stdin = "test\n";
		
		ExecuteResult res = executeCommand(cmd,stdin);
		String out = new String(res.bao.toByteArray());
		String err = new String(res.bae.toByteArray());
		assertEquals(0, res.exitCode);
		assertEquals(expectOut, out);
		assertEquals(expectErr, err);
	}


}
