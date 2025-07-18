package us.bringardner.shell.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Set;

public class ManageTestFiles {

	public static void main(String[] args) throws IOException {
		boolean capture = false;
		if( capture ) {
			captureAll();
		} else {
			restore();
		}

	}

	public static void restore() throws IOException {
		File dir = new File(".").getCanonicalFile();
		File dataFile = new File(dir,"TestFileData.txt");
		InputStream in = new FileInputStream(dataFile);
		String [] lines = new String(in.readAllBytes()).split("\n");
		in.close();
		for(String line : lines) {
			restore(dir,line);			
		}
	}

	public static void restore(File dir,String line) throws IOException {
		//0 BjlFileSystemConsole/TouchTestFiles,
		//1 type=dir,
		//2 creationTime=1741690170000,
		//3 lastAccessTime=1746608963782,
		//4 lastModifiedTime=1746534217000,
		//5 permissions=OWNER_READ:GROUP_READ:OWNER_WRITE:OTHERS_READ:OWNER_EXECUTE:GROUP_EXECUTE:OTHERS_EXECUTE:

		String []parts = line.split("[,]");
		File file = new File(dir,parts[0]);
		long create = Long.parseLong(parts[2].substring(parts[2].indexOf('=')+1));
		long lastAccess = Long.parseLong(parts[3].substring(parts[3].indexOf('=')+1));
		long lastMod = Long.parseLong(parts[4].substring(parts[4].indexOf('=')+1));
		String tmp = parts[5].substring(parts[5].indexOf('=')+1);
		String perms [] = tmp.split(":");


		System.out.println("file="+file);

		PosixFileAttributeView view = Files.getFileAttributeView(file.toPath(), PosixFileAttributeView.class,LinkOption.NOFOLLOW_LINKS);
		view.setTimes(FileTime.fromMillis(lastMod), FileTime.fromMillis(lastAccess),FileTime.fromMillis(create));

		Set<PosixFilePermission> perm = new HashSet<>();
		for(String p : perms) {
			perm.add(PosixFilePermission.valueOf(p));	
		}
		try {
			view.setPermissions(perm);	
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public static void captureAll() throws IOException {
		File dir = new File(".").getCanonicalFile();
		File[] kids = dir.listFiles((f)->{
			return f.isDirectory() && f.getName().endsWith("TestFiles");
		});

		File dataFile = new File(dir,"TestFileData.txt");
		System.setOut(new PrintStream(dataFile));
		for(File kid : kids) {
			capture(dir,kid);
		}

	}

	public static void capture(File projectDir,File fileOrDir) throws IOException {

		StringBuilder buf= new StringBuilder(

				fileOrDir.getAbsolutePath().substring(projectDir.getAbsolutePath().length())

				);
		buf.append(",type=");
		buf.append(fileOrDir.isDirectory()?"dir":"file");
		PosixFileAttributeView view = Files.getFileAttributeView(fileOrDir.toPath(), PosixFileAttributeView.class,LinkOption.NOFOLLOW_LINKS);
		PosixFileAttributes at = view.readAttributes();
		buf.append(",creationTime="+at.creationTime().toMillis());
		buf.append(",lastAccessTime="+at.lastAccessTime().toMillis());
		buf.append(",lastModifiedTime="+at.lastModifiedTime().toMillis());

		Set<PosixFilePermission> perm = at.permissions();
		buf.append(",permissions=");
		for(PosixFilePermission p : perm) {
			buf.append(p.name()+":");
		}

		System.out.println(buf);

		if( fileOrDir.isDirectory()) {
			for(File file: fileOrDir.listFiles()) {
				capture(projectDir,file);
			}
		}

	}

}
