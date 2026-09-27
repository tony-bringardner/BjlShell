package us.bringardner.shell.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import us.bringardner.shell.ShellCommand;
import us.bringardner.shell.ShellContext;

import us.bringardner.shell.job.IJob;
import us.bringardner.shell.job.JobManager;
import us.bringardner.shell.job.JobState;

public class Jobs extends ShellCommand{
	enum Options {l,n,p,r,s};

	static String name = "jobs";
	// 								jobspec = %#
	static String help = "jobs [-rs] [jobspec]\n"
			+ "jobs -x command [arguments]\n"
			+ "The first form lists the active jobs. The options have the following meanings:\n"
			+ "	-l   List process IDs in addition to the normal information.\n"
			+ "	-r   Display only running jobs.\n"
			+ "	-s   Display only stopped/suspended jobs.\n"
			+ "\n"
			+ "If jobspec is given, output is restricted to information about that job. If jobspec is not supplied, \n"
			+ "	the status of all jobs is listed.\n"
			+ "If the -x option is supplied, jobs replaces any jobspec found in command or arguments with the "
			+ "	corresponding process group ID, and executes command, passing it arguments, returning its exit status."
			;

	public Jobs() {
		super(name, help);
	}

	List<Integer> specs;
	public Jobs(List<Integer> specs) {
		this();
		this.specs = specs;
	}


	@Override
	public int process(ShellContext ctx) throws IOException {
		int ret = 0;
		ShellArgument options = parseArgs(ctx, Options.class);

		/*
[1]+  Running                 sleep 10 &
[1]+  Done                    sleep 10
		 */
		//  this defines the current job
		JobManager jm = ctx.console.jobManager;

		List<Integer> jobs = new ArrayList<>();
		//  %[0-9] is parse as signed number rather that jobSpec 
		if(options.paths.size()>0) {
			for(String tmp : options.paths) {
				int i = Jobs.parseJobSpec(jm, tmp);
				if( i >=0) {
					jobs.add(i);
				}
			}
		}
		if( specs !=null && specs.size()>0) {			
			jobs.addAll(specs);			
		} 

		if( jobs.size()==0) {
			for(IJob job : jm.getJobs()) {
				jobs.add(job.getJobNumber());
			}
		}

		for(Integer idx : jobs) {



			IJob job = jm.getJob(idx);
			if( !job.isDisowned()) {
				boolean show = false;
				JobState state = job.getState();
				if( state == JobState.Running) {				
					show = !options.options.contains(Options.s) || options.options.contains(Options.r);
				} else if( state == JobState.Suspended) {
					show = options.options.contains(Options.s) || !options.options.contains(Options.r);
				} else if( state == JobState.Termnated) {
					show = !options.options.contains(Options.s) && !options.options.contains(Options.r);
				}


				if( show ) {
					int jobSize = jm.getJobs().size();
					String flag = idx == jobSize-1 ?"+":idx == (jobSize-2)?"-":" ";
					if(options.options.contains(Options.l)) {
						ctx.stdout.println("["+((idx+1))+"] "+flag+" "+job.getPid()+" "+state+" "+job.toString());
					} else {
						ctx.stdout.println("["+((idx+1))+"] "+flag+" "+state+" "+job.toString());
					}
				}
			}
		}

		return ret;
	}


	public static int parseJobSpec(JobManager jm,String tmp) throws IOException {
		List<IJob> jobs = jm.getJobs();
		int jobSize = jobs.size();
		
		if( tmp.charAt(0) == '%') {
			tmp = tmp.substring(1);
			if(Character.isDigit(tmp.charAt(0)) ) {
				int i = Integer.parseInt(tmp)-1;
				if( i>=0 && i< jobSize) {
					return i;
				}
			} else {
				switch (tmp.charAt(0)){
				case '%':// current
				case '+':
					if( jobSize > 0 ) {
						return jobSize-1;
					}
					break;
				case '-':
					if( jobSize > 1 ) {
						return jobSize-2;
					}
					break;
				case '?'://Using ‘%?ce’, on the other hand, refers to any job containing the string ‘ce’ in its command line. If the prefix or substring matches more than one job, Bash reports an error.
					//kill: ee: ambiguous job spec
					String name  = tmp.substring(1);
					List<IJob> found = new ArrayList<IJob>();
					for(IJob job : jobs) {
						String cmd = job.getCommandLine();
						if( cmd.contains(name) ) {
							found.add(job);
						}
					}
					if( found.size()==0) {
						return -1;
					}
					if( found.size()>1) {
						throw new IOException("? "+name+": ambiguous job spec");
					}
					return found.get(0).getJobNumber();
				default:
					throw new IllegalArgumentException("Unexpected value: " + tmp.charAt(0));
				}
			}
		} else {
			try {
				int ret = Integer.parseInt(tmp);
				return ret;
			} catch (Exception e) {
			}
		}
		return -1;
	}


}
