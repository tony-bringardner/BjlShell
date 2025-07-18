package us.bringardner.shell.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import us.bringardner.shell.Console;

import org.junit.jupiter.api.Order;

@TestMethodOrder(OrderAnnotation.class)
public class TestLs {

	public static String fileDate;
	public static Console console;

	@BeforeAll
	public static void beforeAll() throws IOException, InterruptedException {

		File file = new File("LsTestFiles");
		//setAccessDate();

		//System.exit(0);
		System.setProperty("user.home", file.getAbsolutePath());
		console = new Console();
	}

	public static void setAccessDate() throws IOException, InterruptedException {
		File top = new File("LsTestFiles");
		Random r = new Random();
		List<File> complete = new ArrayList<>();
		List<File> files = new ArrayList<>();
		getFiles(files,top);

		while(complete.size() < files.size()) {
			int idx = r.nextInt(files.size());
			if( idx < files.size()) {
				File file = files.get(idx);
				if(!complete.contains(file)) {					
					long cdate = r.nextLong(System.currentTimeMillis());
					FileTime time = FileTime.fromMillis(cdate);
					Files.setAttribute(file.toPath(), "lastAccessTime", time);
					complete.add(file);
					System.out.println(file);
					int sleep = r.nextInt(600000);
					while(sleep < 60000) {
						sleep = r.nextInt(600000);
					}
					
					Thread.sleep(sleep);
				}
			}
		}
	}

	private static void getFiles(List<File> files, File file) {
		files.add(file);
		if( file.isDirectory()) {
			for(File f : file.listFiles()) {
				getFiles(files, f);
			}
		}		
	}

	public static void setDates(Random r, File file) throws IOException, InterruptedException {
		System.out.println(file);
		Path p = file.toPath();
		/*
"lastModifiedTime"    FileTime
"lastAccessTime"      FileTime
"creationTime"        FileTime

		 */


		long cdate = r.nextLong(System.currentTimeMillis());
		FileTime ctime = FileTime.fromMillis(cdate);
		long mdate = cdate+r.nextLong(99999999);
		FileTime mtime = FileTime.fromMillis(mdate);
		long adate = mdate+r.nextLong(99999);
		FileTime atime = FileTime.fromMillis(adate);
		
		if( file.isDirectory()) {
			for(File kid : file.listFiles()) {
				setDates(r,kid);
			}			
		}
		Files.setAttribute(p, "creationTime", ctime);
		Files.setAttribute(p, "lastModifiedTime", mtime);
		Files.setAttribute(p, "lastAccessTime", atime);


		Thread.sleep(60100);

	}

	@AfterAll
	public static void afterAll() {

	}

	public String executeCommand(String command) throws IOException {
		PrintStream stdout= System.out;
		PrintStream stderr= System.err;
		InputStream stdin = System.in;


		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		System.setOut(new PrintStream(bao));
		ByteArrayOutputStream bae = new ByteArrayOutputStream();
		System.setErr(new PrintStream(bae));
		System.setIn(new ByteArrayInputStream(new byte[0]));

		console.executeWithoutAntlr(command);
		String err = new String(bae.toByteArray());
		
		String actual = new String(bao.toByteArray());
		
		System.setIn(stdin);
		System.setOut(stdout);
		System.setErr(stderr);
		
		if( !err.isEmpty()) {
			System.err.println(err);
			System.err.println("stdout="+actual);
			actual = err;
		}
		return actual;
	}

	@Test
	@Order(1)
	public void testLs() throws IOException {


		String cmd = "ls -1aAbBcCdFgGhHIlLoQrRStuUxX";
		String expect = 
				  "AbcFileA.js\n"
				+ "AbcFileB.php\n"
				+ "AbcFileC.txt\n"
				+ "AbcFileD.properties\n"
				+ "Folder01";
		
		cmd = "ls";

		String actual = executeCommand(cmd).trim();		
		assertEquals(expect, actual);
	}


	@Test
	@Order(2)
	public void testLs_X() throws IOException {


		String cmd = "ls -1aAbBcCdFgGhHIlLoQrRStuUxX";
		String expect = 
				  "Folder01\n"
				  + "AbcFileA.js\n"
				  + "AbcFileB.php\n"
				  + "AbcFileD.properties\n"
				  + "AbcFileC.txt";
		
		cmd = "ls -X";

		String actual = executeCommand(cmd).trim();		
		assertEquals(expect, actual);
	}

	
	@Test
	@Order(3)
	public void testLs_a() throws IOException {


		String expect = 
				  ".Hidden01.txt\n"
				  + ".Hidden02.txt\n"
				  + "AbcFileA.js\n"
				  + "AbcFileB.php\n"
				  + "AbcFileC.txt\n"
				  + "AbcFileD.properties\n"
				  + "Folder01";
		
		String cmd = "ls -a";

		String actual = executeCommand(cmd).trim();		
		assertEquals(expect, actual);
	}
	
	@Test
	@Order(4)
	public void testLs_l() throws IOException {


		String expect1 = 
				    "-rw-r--r--1tonystaff1547Jun162025AbcFileA.js-rwxr-xr-x1tonystaff3710Jun162025AbcFileB.php-rwxr-xr-x1tonystaff20Jun162025AbcFileC.txt-rwxr-xr-x1tonystaff20Jun162025AbcFileD.propertiesdrwxr-xr-x1tonystaff224Nov132022Folder01"
				  
				  ;
		String expect = expect1.replaceAll("\\s", "");
		
		String cmd = "ls -l";

		String actual1 = executeCommand(cmd).trim();
		//System.out.println(actual1);
		String actual = actual1.replaceAll("\\s", "");
		
		assertEquals(expect, actual);
	}

	@Test
	@Order(4)
	public void testLs_lt() throws IOException {


		String expect1 = 
				    "-rwxr-xr-x 1 tony  staff    20  Jun 16 2025 AbcFileD.properties\n"
				    + "-rwxr-xr-x 1 tony  staff    20  Jun 16 2025 AbcFileC.txt\n"
				    + "-rwxr-xr-x 1 tony  staff  3710  Jun 16 2025 AbcFileB.php\n"
				    + "-rw-r--r-- 1 tony  staff  1547  Jun 16 2025 AbcFileA.js\n"
				    + "drwxr-xr-x 1 tony  staff   224  Nov 13 2022 Folder01"
				  
				  ;
		String expect = expect1.replaceAll("\\s", "");
		
		String cmd = "ls -lt";

		String actual1 = executeCommand(cmd).trim();
		String actual = actual1.replaceAll("\\s", "");
		if( !expect.equals(actual)) {
			assertEquals(expect1, actual1);	
		}
		
	}
	
	@Test
	@Order(4)
	public void testLs_ltr() throws IOException {


		String expect1 = 
				    "drwxr-xr-x 1 tony  staff   224  Nov 13 2022 Folder01\n"
				    + "-rw-r--r-- 1 tony  staff  1547  Jun 16 2025 AbcFileA.js\n"
				    + "-rwxr-xr-x 1 tony  staff  3710  Jun 16 2025 AbcFileB.php\n"
				    + "-rwxr-xr-x 1 tony  staff    20  Jun 16 2025 AbcFileC.txt\n"
				    + "-rwxr-xr-x 1 tony  staff    20  Jun 16 2025 AbcFileD.properties\n"
				  
				  ;
		String expect = expect1.replaceAll("\\s", "");
		
		String cmd = "ls -ltr";

		String actual1 = executeCommand(cmd).trim();
		//System.out.println(actual1);
		String actual = actual1.replaceAll("\\s", "");
		if( !expect.equals(actual)) {
			assertEquals(expect1, actual1);	
		}
		
	}
}
