package us.bringardner.shell.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import us.bringardner.io.filesource.FileSource;
import us.bringardner.shell.Console;
import us.bringardner.shell.ShellCommand;
import us.bringardner.shell.ShellContext;

public class Cd extends ShellCommand{
	static String name = "cd";
	// physical 
	static String help = "change the current directory\n"
			+ "\t [-L] (default) Change the current working directory\n"
			+ "\t -P (physical) Change current working directory after following all symbolic links.";
	
	public Cd() {
		super(name, help);
	}

	@Override
	public int process(ShellContext ctx) throws IOException {
		int ret = 0;
		boolean follow = false;
		List<String> args = new ArrayList<>();
		for(int idx=0; idx < ctx.args.length; idx++ ) {
			String arg = ctx.args[idx].trim();
			if( arg.startsWith("-")) {
				follow = arg.equals("-P");
			} else {
				args.add(arg);
			}
		}
		String path = "~";
		
		if( args.size()>0) {
			if( args.size()>2) {
				ctx.stderr.println("cd: too many args: "+path);
				return -1;
			}			
			path = args.get(1);
		}
		
		List<FileSource> dirs = getFiles(ctx, path);
		if( dirs==null || dirs.size()==0) {
			ctx.stderr.println("cd: no such file or directory: "+path);
			return -1;
		}
		FileSource dir = dirs.get(0);
		
		if( follow ) {
			FileSource link = dir.getLinkedTo();
			while(link !=null ) {
				dir = link;
				link = dir.getLinkedTo();
			}
		}
		if( !dir.isDirectory()) {
			ctx.stderr.println("cd: not a directory: "+path);
			return -1;
		}

		ctx.console.setCurrentDirectory(dir);
		
		String cwd = dir.getAbsolutePath();
		String old = (String) ctx.console.getVariable(Console.VARIABLE_PWD);
		if(old !=null ) {
			ctx.console.setVariable(Console.VARIABLE_OLDPWD,old);
		}
		ctx.console.setVariable(Console.VARIABLE_PWD,cwd);
		
		return ret;
	}

}
