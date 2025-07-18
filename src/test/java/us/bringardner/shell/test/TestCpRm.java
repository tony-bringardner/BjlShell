package us.bringardner.shell.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import us.bringardner.io.filesource.FileSource;
import us.bringardner.io.filesource.FileSourceFactory;
import us.bringardner.io.filesource.fileproxy.FileProxyFactory;
import us.bringardner.shell.Console;

@TestMethodOrder(OrderAnnotation.class)
public class TestCpRm {

	public static String fileDate;
	public static Console console;
	
	@BeforeAll
	public static void beforeAll() throws IOException {
		FileSourceFactory.setDefaultFactory(new FileProxyFactory());
		FileSource file = FileSourceFactory.getDefaultFactory().createFileSource("CpTestFiles");

		System.setProperty("user.home", file.getAbsolutePath());
		console = new Console();
	}
	
	@AfterAll
	public static void afterAll() {
		
	}
	
	public static class ExecuteResult {
		int exitCode=0;		
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		ByteArrayOutputStream bae = new ByteArrayOutputStream();			
	}
	
	public ExecuteResult executeCommand(String command,String stdIn) throws IOException {
		PrintStream stdout= System.out;
		PrintStream stderr= System.err;
		InputStream stdin = System.in;
		
		ExecuteResult ret = new ExecuteResult();
		System.setOut(new PrintStream(ret.bao));
		System.setErr(new PrintStream(ret.bae));
		System.setIn(new ByteArrayInputStream(stdIn.getBytes()));
		
		console.executeWithoutAntlr(command);
		System.setIn(stdin);
		System.setOut(stdout);
		System.setErr(stderr);
		
		return ret;
	}

	
	@Test
	@Order(1)
	public void testCp_File2File() throws IOException {
		File file = new File("target/Co01.txt").getCanonicalFile();
		if( file.exists()) {
			assertTrue(file.delete(),"Can't delete existing file");
		}
		
		String source = "AbcFile.txt";
		String dest = "../target/Co01.txt";
		String cmd = "cp "+source+" "+dest;

		int exit = console.executeWithoutAntlr(cmd);
		assertEquals(0, exit,"Cp exit code");
		
		assertTrue(file.exists(),"File does not exist after cp");
		
		cmd = "rm "+dest;
		exit = console.executeWithoutAntlr(cmd);
		
		assertEquals(0, exit,"Rm exit code");		
		//file = new File("target/Co01.txt").getCanonicalFile();		
		assertFalse(file.exists(),"File still exist after rm");
		
	}

	@Test
	@Order(2)
	public void testCp_File2Dir() throws IOException {
		File dir = new File("target/CpTestDir").getCanonicalFile();
		if( !dir.exists()) {
			assertTrue(dir.mkdirs(),"Can't create "+dir);
		}
		
		File file = new File(dir,"Co01.txt").getCanonicalFile();
		if( file.exists()) {
			assertTrue(file.delete(),"Can't delete existing file");
		}
		
		String source = "AbcFile.txt";
		String dest = "../target/CpTestDir/Co01.txt";
		String cmd = "cp "+source+" "+dest;

		int exit = console.executeWithoutAntlr(cmd);
		
		assertEquals(0, exit,"Cp exit code");		
		assertTrue(file.exists(),"File does not exist after cp");
		cmd = "rm "+dest;
		exit = console.executeWithoutAntlr(cmd);
		
		assertEquals(0, exit,"Rm exit code");
		
		assertFalse(file.exists(),"File still exist after rm");
		
	}

	@Test
	@Order(3)
	public void testCp_MultiByWildcardFile2Dir() throws IOException {
		File dir = new File("target/CpTestDir").getCanonicalFile();
		if( !dir.exists()) {
			assertTrue(dir.mkdirs(),"Can't create "+dir);
		}
		
		for(File file : dir.listFiles()) {
			if( file.exists()) {
				assertTrue(file.delete(),"Can't delete existing file");
			}
		}
		
		String source = "Abc*";
		String dest = "../target/CpTestDir";
		String cmd = "cp "+source+" "+dest;

		int exit = console.executeWithoutAntlr(cmd);
		
		assertEquals(0, exit,"Cp exit code");		
		
		assertEquals(4, dir.listFiles().length,"child count after cp");
		
		cmd = "rm -rf "+dest;
		exit = console.executeWithoutAntlr(cmd);
		
		assertEquals(0, exit,"Rm 02 exit code only -rf should NOT fail");
		
		
		assertFalse(dir.exists(),"Dir still exist after rm");
		
	}
	
	@Test
	@Order(4)
	public void testCp_MultiByListFile2Dir() throws IOException {
		File dir = new File("target/CpTestDir").getCanonicalFile();
		if( !dir.exists()) {
			assertTrue(dir.mkdirs(),"Can't create "+dir);
		}
		
		for(File file : dir.listFiles()) {
			if( file.exists()) {
				assertTrue(file.delete(),"Can't delete existing file");
			}
		}
		
		String source = "AbcFile.js AbcFile.txt AbcFile.php";
		String dest = "../target/CpTestDir";
		String cmd = "cp "+source+" "+dest;

		int exit = console.executeWithoutAntlr(cmd);
		
		assertEquals(0, exit,"Cp exit code");		
		
		assertEquals(3, dir.listFiles().length,"child count after cp");
		
		cmd = "rm -rf "+dest;
		exit = console.executeWithoutAntlr(cmd);
		
		assertEquals(0, exit,"Rm 02 exit code only -rf should NOT fail");
		
		
		assertFalse(dir.exists(),"Dir still exist after rm");
		
	}

	@Test
	@Order(4)
	public void testCp_MultiByListFile2DirRm1by1() throws IOException {
		File dir = new File("target/CpTestDir").getCanonicalFile();
		if( !dir.exists()) {
			assertTrue(dir.mkdirs(),"Can't create "+dir);
		}
		
		for(File file : dir.listFiles()) {
			if( file.exists()) {
				assertTrue(file.delete(),"Can't delete existing file");
			}
		}
		
		String source = "AbcFile.js AbcFile.txt AbcFile.php";
		String dest = "../target/CpTestDir";
		String cmd = "cp "+source+" "+dest;

		int exit = console.executeWithoutAntlr(cmd);
		
		assertEquals(0, exit,"Cp exit code");		
		
		assertEquals(3, dir.listFiles().length,"child count after cp");
		
		
		cmd = "rm  ../target/CpTestDir/AbcFile.txt";
		exit = console.executeWithoutAntlr(cmd);		
		assertEquals(0, exit,"Rm 01 exit code only txt should NOT fail");
		
		cmd = "rm  ../target/CpTestDir/AbcFile.php";
		exit = console.executeWithoutAntlr(cmd);		
		assertEquals(0, exit,"Rm 02 exit code only php should NOT fail");
		
		cmd = "rm  ../target/CpTestDir/AbcFile.js";
		exit = console.executeWithoutAntlr(cmd);		
		assertEquals(0, exit,"Rm 03 exit code only js should NOT fail");

		cmd = "rm -d "+dest;
		exit = console.executeWithoutAntlr(cmd);		
		assertEquals(0, exit,"Rm 04 exit code only dir should NOT fail");

		
		assertFalse(dir.exists(),"Dir still exist after rm");
		
	}

	@Test
	@Order(5)
	public void testCp_rm_i() throws IOException {
		File file = new File("target/Co01.txt").getCanonicalFile();
		if( file.exists()) {
			assertTrue(file.delete(),"Can't delete existing file");
		}
		
		String source = "AbcFile.txt";
		String dest = "../target/Co01.txt";
		String cmd = "cp "+source+" "+dest;

		int exit = console.executeWithoutAntlr(cmd);
		assertEquals(0, exit,"Cp exit code");
		
		assertTrue(file.exists(),"File does not exist after cp");
		
		/*
		 * -i with no STDIN should prompt and slightly leave the file there 
		 */
		cmd = "rm -i "+dest;
		ExecuteResult result = executeCommand(cmd, "");
		String out = new String(result.bao.toByteArray());
		String err = new String(result.bae.toByteArray());
		assertEquals(0, result.exitCode,"Rm exit code");
		assertTrue(out.startsWith("remove"),"First part of the prompt is wrong");
		assertTrue(out.endsWith("CpTestFiles/../target/Co01.txt?"),"Last part of the prompt is wrong");
		
		assertEquals("", err,"Err should be empty");
		assertTrue(file.exists(),"File should exist after rm");
		
		result = executeCommand(cmd, "y\n");
		out = new String(result.bao.toByteArray());
		err = new String(result.bae.toByteArray());
		assertEquals(0, result.exitCode,"Rm exit code");		
		assertTrue(out.startsWith("remove"),"First part of the prompt is wrong");
		assertTrue(out.endsWith("CpTestFiles/../target/Co01.txt?"),"Last part of the prompt is wrong");
		
		assertEquals("", err,"Err should be empty");
		assertEquals(0, result.exitCode,"Rm exit code");		
		assertFalse(file.exists(),"File still exist after rm");
		
	}
}
