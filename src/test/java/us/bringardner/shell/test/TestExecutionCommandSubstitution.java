package us.bringardner.shell.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import us.bringardner.shell.Console;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


@TestMethodOrder(OrderAnnotation.class)
public class TestExecutionCommandSubstitution {

	public static class ExecuteResult {
		int exitCode=0;		
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		ByteArrayOutputStream bae = new ByteArrayOutputStream();			
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
		
		return ret;
	}
	@Test
	public void testCommandSubstitue01() throws Exception{
		String cmd = "$(echo -n test)"
				;

		String expect = 
				""
				;
		
		ExecuteResult res = executeCommand(cmd,"");
		String out = new String(res.bao.toByteArray());
		String err = new String(res.bae.toByteArray());
		assertEquals("", err);
		assertEquals(expect, out);
		assertEquals(0, res.exitCode);
		
		
	}
	
	@Test
	public void testCommandSubstitue02() throws Exception{
		String cmd = "echo $(echo -n test)"
				;

		String expect = 
				"test\n"
				;
		
		ExecuteResult res = executeCommand(cmd,"");
		String out = new String(res.bao.toByteArray());
		String err = new String(res.bae.toByteArray());
		assertEquals("", err);
		assertEquals(expect, out);
		assertEquals(0, res.exitCode);
		
		
	}
		
	@Test
	public void testCommandSubstitue03() throws Exception{
		String cmd = "var=$(echo -n test)\n"
				+ "echo \"var=$var\"";
				;

		String expect = 
				"var=test\n"
				;
		
		ExecuteResult res = executeCommand(cmd,"");
		String out = new String(res.bao.toByteArray());
		String err = new String(res.bae.toByteArray());
		assertEquals("", err);
		assertEquals(expect, out);
		assertEquals(0, res.exitCode);
	}
	
	@Test
	public void testCommandSubstitue04() throws Exception{
		String cmd = "echo $(echo -n test)\n"
				;

		String expect = 
				"test\n"
				;
		
		ExecuteResult res = executeCommand(cmd,"");
		String out = new String(res.bao.toByteArray());
		String err = new String(res.bae.toByteArray());
		assertEquals("", err);
		assertEquals(expect, out);
		assertEquals(0, res.exitCode);
		
		
	}
}
