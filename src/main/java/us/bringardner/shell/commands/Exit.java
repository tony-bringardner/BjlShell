package us.bringardner.shell.commands;

import java.io.IOException;

import us.bringardner.shell.ShellCommand;
import us.bringardner.shell.ShellContext;
import us.bringardner.shell.antlr.signal.ExitException;

public class Exit extends ShellCommand{
	static String name = "exit";
	static String help = "Exit the process \n"
			;
	
	static int cnt = 0;
	public Exit() {
		super(name, help);
	}

	@Override
	public int process(ShellContext ctx) throws IOException {
		
		int ret = 0;
		if( ctx.args.length>1) {
			try {
				ret = Integer.parseInt(ctx.args[1]);
			} catch (Exception e) {
			}
		}
		throw new ExitException(ctx, ret);
	}

}
