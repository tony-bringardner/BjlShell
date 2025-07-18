package us.bringardner.shell.commands;

import java.io.IOException;

import us.bringardner.shell.Console;
import us.bringardner.shell.ShellCommand;
import us.bringardner.shell.ShellContext;

public class Help extends ShellCommand{
	static String name = "help";
	static String help = "Helpfull information about supported commands";
	
	public Help() {
		super(name, help);
	}

	@Override
	public int process(ShellContext ctx) throws IOException {
		int ret = 0;
		
		if( ctx.args.length  < 2) {
			for(ShellCommand cmd : Console.commands.values()) {
				ctx.stdout.println(cmd.getName()+": "+ cmd.getHelp());
			}
		} else {
			for (int idx = 1; idx < ctx.args.length; idx++) {
				ShellCommand cmd = Console.commands.get(ctx.args[idx]);
				if( cmd == null ) {
					ctx.stdout.println("No command for "+ctx.args[idx]);
				} else {
					ctx.stdout.println(ctx.args[idx]+ " "+cmd.getHelp());
				}
			}
		}
		return ret;
	}

}
