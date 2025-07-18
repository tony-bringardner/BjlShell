package us.bringardner.shell.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import us.bringardner.shell.Console;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;


@TestMethodOrder(OrderAnnotation.class)
public class TestExecutionExport {

	
	public static void setup(String home) throws IOException {
	}

	public static class ExecuteResult {
		int exitCode=0;		
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		ByteArrayOutputStream bae = new ByteArrayOutputStream();			
	}
	
	public static ExecuteResult executeCommand(Console console,String command,String stdIn,int exitCode) {
		
		PrintStream stdout= System.out;
		PrintStream stderr= System.err;
		InputStream stdin = System.in;
		
		ExecuteResult ret = new ExecuteResult();
		System.setOut(new PrintStream(ret.bao));
		System.setErr(new PrintStream(ret.bae));
		System.setIn(new ByteArrayInputStream(stdIn.getBytes()));
		
		ret.exitCode=console.executeUsingAntlr(command);
		System.setIn(stdin);
		System.setOut(stdout);
		System.setErr(stderr);
		
		String err = new String(ret.bae.toByteArray());
		if( !err.isEmpty()) {
			System.out.println(command);
			System.out.println(err);
		}
		assertEquals(exitCode, ret.exitCode);
		
		return ret;
	}
	
	@Test
	public void testExport01() throws Exception{
		String cmd = "export\n"
				;

		String stdIn = "";
		int exitCode = 0;
		
		
		ExecuteResult ret = executeCommand(new Console(),cmd,stdIn,exitCode);
		
		String out = new String(ret.bao.toByteArray());
		String err = new String(ret.bae.toByteArray());
		if( !err.isEmpty()) {
			System.out.println(cmd);
			System.out.println(err);
		}
		Pattern expect = Pattern.compile("(.+[=].+)");
		for(String line : out.split("\n")) {
			Matcher m = expect.matcher(line);
			assertTrue(m.matches(),"Line="+line);
		}
	}
	
	@Test
	public void testExport02() throws Exception{
		String cmd = "export -p\n"
				;

		String stdIn = "";
		int exitCode = 0;
		
		
		ExecuteResult ret = executeCommand(new Console(),cmd,stdIn,exitCode);
		
		String out = new String(ret.bao.toByteArray());
		String err = new String(ret.bae.toByteArray());
		if( !err.isEmpty()) {
			System.out.println(cmd);
			System.out.println(err);
		}
		Pattern expect = Pattern.compile("declare -x (.+[=].+)");
		for(String line : out.split("\n")) {
			Matcher m = expect.matcher(line);
			assertTrue(m.matches(),"Line="+line);
		}
	}
	
	@Test
	public void testExport03() throws Exception{
		String cmd = "export -p\n"
				;

		String stdIn = "";
		int exitCode = 0;
		
		Console console = new Console();
		console.isInteractive = false;
		ExecuteResult ret = executeCommand(console,cmd,stdIn,exitCode);
		
		String out = new String(ret.bao.toByteArray());
		String err = new String(ret.bae.toByteArray());
		if( !err.isEmpty()) {
			System.out.println(cmd);
			System.out.println(err);
		}
		Pattern expect = Pattern.compile("export (.+[=].+)");
		for(String line : out.split("\n")) {
			Matcher m = expect.matcher(line);
			assertTrue(m.matches(),"Line="+line);
		}
	}
	
	@Test
	public void testExport04() throws Exception{
		String var = "testVar";
		String val = "testVal";
		String fulVAl=var+"="+val;
		String cmd = "export "+fulVAl+"\n";

		String stdIn = "";
		int exitCode = 0;
		
		Console console = new Console();
		console.isInteractive = false;
		executeCommand(console,cmd,stdIn,exitCode);
		
		cmd = "export -p";
		ExecuteResult ret = executeCommand(console,cmd,stdIn,exitCode);
		String out = new String(ret.bao.toByteArray());
		int idx = out.indexOf(fulVAl);
		assertTrue(idx>=0);
		
		cmd = "export -n "+var;
		ret = executeCommand(console,cmd,stdIn,exitCode);
		
		cmd = "export -p";
		ret = executeCommand(console,cmd,stdIn,exitCode);
		
		out = new String(ret.bao.toByteArray());
		idx = out.indexOf(var);
		assertTrue(idx<0);
		
		Object actualVal = console.getVariable(var);
		assertNotNull(actualVal);
		assertEquals(val, actualVal);
	}
}
