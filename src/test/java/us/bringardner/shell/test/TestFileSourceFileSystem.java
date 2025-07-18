package us.bringardner.shell.test;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import us.bringardner.io.filesource.FileSource;
import us.bringardner.io.filesource.FileSourceFactory;
import us.bringardner.io.filesource.memory.MemoryFileSourceFactory;
import us.bringardner.shell.FileSourceFileSystem;

public class TestFileSourceFileSystem {

	public static String fileDate;
	
	@BeforeAll
	public static void beforeAll() throws IOException {
		//System.out.println("In setup");
	}
	

	@AfterAll
	public static void afterAll() {
		//System.out.println("After all");
	}
	
	private static void fillDir(FileSource dir,int i) throws IOException {
		for(int idx=0; idx < 10; idx++ ) {
			FileSource file = dir.getChild("Test-"+i+"-"+idx+".txt");
			try(OutputStream out = file.getOutputStream()) {
				out.write("0123456789".getBytes());
			}
		}
		
	}
	
	FileSource getProxy(InvocationHandler fs) {
		FileSource ret = (FileSource) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] { FileSource.class }, fs);

		return ret;
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testRoot() throws IOException {
		
		FileSourceFileSystem fs = new FileSourceFileSystem("");
		//System.out.println("fs = "+fs);
				
		FileSource [] kids = fs.listFiles();
		
		//System.out.println("fs kids = "+kids.length);
		FileSource localRoot = FileSourceFactory.getDefaultFactory().createFileSource("/");
		//System.out.println("localRoot = "+localRoot);
		
		FileSource [] localkids = localRoot.listFiles();
		
		//System.out.println("localRoot kids = "+localkids.length);
		boolean ok = fs.mount(localRoot, "");
		kids = fs.listFiles();
		
		//System.out.println("fs kids = "+kids.length);
		
		for(FileSource kid : kids) {
			//System.out.println(kid);
		}
		
		MemoryFileSourceFactory mf = new MemoryFileSourceFactory();
		FileSource mfroot = mf.listRoots()[0];
		fillDir(mfroot, 0);
		
		kids = mfroot.listFiles();
		
		//System.out.println("mfroot kids = "+kids.length);
		
		for(FileSource kid : kids) {
		//	System.out.println(kid);
		}

		ok = fs.mount(mfroot, "mem");
		kids = fs.listFiles();
		for(FileSource kid : kids) {
			//System.out.println(kid);
		}

		FileSource mdir = fs.getChild("mem");
		//System.out.println("mdir = "+mdir);

		kids = mdir.listFiles();
		//System.out.println("mdir kids = "+kids.length);

		for(FileSource kid : kids) {
		//	System.out.println(kid);
		}

		FileSource localdir = fs.getChild("");
		//System.out.println("localdir = "+localdir);

		kids = localdir.listFiles();
		//System.out.println("localdir kids = "+kids.length);

		for(FileSource kid : kids) {
			//System.out.println(kid);
		}

	}
	
		
		
}
