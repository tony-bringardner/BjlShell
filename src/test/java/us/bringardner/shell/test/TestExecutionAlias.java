package us.bringardner.shell.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import us.bringardner.io.filesource.FileSourceFactory;
import us.bringardner.io.filesource.fileproxy.FileProxyFactory;
import us.bringardner.shell.Console;


@TestMethodOrder(OrderAnnotation.class)
public class TestExecutionAlias {

	private static File testFilesDir;
	private static Console console;
	
	@BeforeAll
	public static void setup() throws IOException {
		String home = "LsTestFiles";
		FileSourceFactory.setDefaultFactory(new FileProxyFactory());
		testFilesDir = new File(home).getCanonicalFile();
		System.setProperty("user.home", testFilesDir.getAbsolutePath());
		console = new Console();
	}

	public static class ExecuteResult {
		int exitCode=0;		
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		ByteArrayOutputStream bae = new ByteArrayOutputStream();			
	}
	
	public static ExecuteResult executeCommand(String command,String stdIn,int expectedExitCode) {
		
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
		assertEquals(expectedExitCode, ret.exitCode);
		
		return ret;
	}
	
	@Test
	@Order(1)
	public void testAlias01() throws Exception{
		String cmd = "alias lll='ls -ltr' l2='ls -l'"
				;
		
		String expect = 
				""
				;
		
		ExecuteResult res = executeCommand(cmd,"", 0);
		String out = new String(res.bao.toByteArray());
		String err = new String(res.bae.toByteArray());
		assertEquals("", err);
		assertEquals(expect, out);
		assertEquals(0, res.exitCode);
		
		
	}
	
	@Test
	@Order(2)
	public void testAlias02() throws Exception{
		String cmd = "alias -p"
				;
		
		String expect = 
				"alias l2='ls -l'\n"
				+"alias lll='ls -ltr'\n"
			
				;
		
		ExecuteResult res = executeCommand(cmd,"", 0);
		String out = new String(res.bao.toByteArray());
		String err = new String(res.bae.toByteArray());
		assertEquals("", err);
		assertEquals(expect, out);
		assertEquals(0, res.exitCode);
		
		
	}
	
	@Test
	@Order(3)
	public void testAlias03() throws Exception{
		String cmd = "lll\n"
				;
		
		String expect = 
				"drwxr-xr-x 1 tony  staff   224  Nov 13 2022 Folder01\n"
				+ "-rw-r--r-- 1 tony  staff  1547  Jun 16 2025 AbcFileA.js\n"
				+ "-rwxr-xr-x 1 tony  staff  3710  Jun 16 2025 AbcFileB.php\n"
				+ "-rwxr-xr-x 1 tony  staff    20  Jun 16 2025 AbcFileC.txt\n"
				+ "-rwxr-xr-x 1 tony  staff    20  Jun 16 2025 AbcFileD.properties\n"
				+ ""
				;
		
		ExecuteResult res = executeCommand(cmd,"", 0);
		String out = new String(res.bao.toByteArray());
		String err = new String(res.bae.toByteArray());
		assertEquals("", err);
		assertEquals(expect, out);
		assertEquals(0, res.exitCode);
		
		
	}

	@Test
	@Order(4)
	public void testAlias04() throws Exception{
		String cmd = "lll Folder01\n"
				;
		
		String expect = 
				"drwxr-xr-x 1 tony  staff   224  Jul 18 2013 Folder01abc.1\n"
				+ "drwxr-xr-x 1 tony  staff   160  Jun 16 2025 Folder01def.2\n"
				+ "-rwxr-xr-x 1 tony  staff    20  Jun 16 2025 AbcFile01.txt\n"
				+ "-rwxr-xr-x 1 tony  staff    20  Jun 16 2025 AbcFile01.properties\n"
				+ "-rwxr-xr-x 1 tony  staff    20  Jun 16 2025 AbcFile01.php\n"
				;
		
		ExecuteResult res = executeCommand(cmd,"", 0);
		String out = new String(res.bao.toByteArray());
		String err = new String(res.bae.toByteArray());
		assertEquals("", err);
		assertEquals(expect, out);
		assertEquals(0, res.exitCode);
		
		
	}

	@Test
	@Order(5)
	public void testAlias05() throws Exception{
		String cmd = "lll Folder01\n"
				;
		
		String expect = 
				"drwxr-xr-x 1 tony  staff   224  Jul 18 2013 Folder01abc.1\n"
				+ "drwxr-xr-x 1 tony  staff   160  Jun 16 2025 Folder01def.2\n"
				+ "-rwxr-xr-x 1 tony  staff    20  Jun 16 2025 AbcFile01.txt\n"
				+ "-rwxr-xr-x 1 tony  staff    20  Jun 16 2025 AbcFile01.properties\n"
				+ "-rwxr-xr-x 1 tony  staff    20  Jun 16 2025 AbcFile01.php\n"
				;
		
		ExecuteResult res = executeCommand(cmd,"", 0);
		String out = new String(res.bao.toByteArray());
		String err = new String(res.bae.toByteArray());
		assertEquals("", err);
		assertEquals(expect, out);
		assertEquals(0, res.exitCode);
		
		
	}
	
	@Test
	@Order(6)
	public void testAlias06() throws Exception{
		String cmd = "l2\n"
				;
		
		String expect = 
				"-rw-r--r-- 1 tony  staff  1547  Jun 16 2025 AbcFileA.js\n"
				+ "-rwxr-xr-x 1 tony  staff  3710  Jun 16 2025 AbcFileB.php\n"
				+ "-rwxr-xr-x 1 tony  staff    20  Jun 16 2025 AbcFileC.txt\n"
				+ "-rwxr-xr-x 1 tony  staff    20  Jun 16 2025 AbcFileD.properties\n"
				+ "drwxr-xr-x 1 tony  staff   224  Nov 13 2022 Folder01\n"
				;
		
		ExecuteResult res = executeCommand(cmd,"", 0);
		String out = new String(res.bao.toByteArray());
		String err = new String(res.bae.toByteArray());
		assertEquals("", err);
		assertEquals(expect, out);
		assertEquals(0, res.exitCode);
	}
	
	@Test
	@Order(7)
	public void testAlias07() throws Exception{
		String cmd = "unalias l2\n"
				;
		
		String expect = 
				""
				;
		
		ExecuteResult res = executeCommand(cmd,"", 0);
		String out = new String(res.bao.toByteArray());
		String err = new String(res.bae.toByteArray());
		assertEquals("", err);
		assertEquals(expect, out);
		assertEquals(0, res.exitCode);
	}

	@Test
	@Order(8)
	public void testAlias08() throws Exception{
		String cmd = "alias -p"
				;
		
		String expect = 
				"alias lll='ls -ltr'\n"
			
				;
		
		ExecuteResult res = executeCommand(cmd,"", 0);
		String out = new String(res.bao.toByteArray());
		String err = new String(res.bae.toByteArray());
		assertEquals("", err);
		assertEquals(expect, out);
		assertEquals(0, res.exitCode);
	}
	
	@Test
	@Order(9)
	public void testAlias09() throws Exception{
		String cmd = "unalias -a"
				;
		
		String expect = 
				""
			
				;
		
		ExecuteResult res = executeCommand(cmd,"", 0);
		String out = new String(res.bao.toByteArray());
		String err = new String(res.bae.toByteArray());
		assertEquals("", err);
		assertEquals(expect, out);
		assertEquals(0, res.exitCode);
	}
	
	@Test
	@Order(10)
	public void testAlias10() throws Exception{
		String cmd = "alias ls='ls -ltr'\n"
				+ "ls\n"
				;
		
		String expect = 
				"drwxr-xr-x 1 tony  staff   224  Nov 13 2022 Folder01\n"
				+ "-rw-r--r-- 1 tony  staff  1547  Jun 16 2025 AbcFileA.js\n"
				+ "-rwxr-xr-x 1 tony  staff  3710  Jun 16 2025 AbcFileB.php\n"
				+ "-rwxr-xr-x 1 tony  staff    20  Jun 16 2025 AbcFileC.txt\n"
				+ "-rwxr-xr-x 1 tony  staff    20  Jun 16 2025 AbcFileD.properties\n"
				+ ""
			
				;
		
		ExecuteResult res = executeCommand(cmd,"", 0);
		String out = new String(res.bao.toByteArray());
		String err = new String(res.bae.toByteArray());
		assertEquals("", err);
		assertEquals(expect, out);
		assertEquals(0, res.exitCode);
	}

}
