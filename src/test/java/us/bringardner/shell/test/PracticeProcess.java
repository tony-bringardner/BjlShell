package us.bringardner.shell.test;

import static org.jline.keymap.KeyMap.alt;
import static org.jline.keymap.KeyMap.ctrl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.jline.keymap.KeyMap;
import org.jline.reader.Binding;
import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.Reference;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;

import us.bringardner.shell.Console;
import us.bringardner.shell.NativeKeyboard;

public class PracticeProcess {
	/*
	 * 
Command: /Library/Java/JavaVirtualMachines/graalvm-jdk-21.0.1+12.1/Contents/Home/bin/java

Arguments: 
	-XX:+ShowCodeDetailsInExceptionMessages 
	-agentlib:jdwp=transport=dt_socket,suspend=y,address=localhost:80001 
	-Djava.library.path=/Volumes/SanDisk1/eclipse-workspace-jmail/BjlFileSystemConsole/macos 
	-javaagent:/Volumes/Data/Applications/Eclipse/Eclipse2024-12/windowbuilder-master/Eclipse-Jmail.app/Contents/Eclipse/configuration/org.eclipse.osgi/2174/0/.cp/lib/javaagent-shaded.jar -Dfile.encoding=UTF-8 -Dstdout.encoding=UTF-8 -Dstderr.encoding=UTF-8 -classpath /Volumes/SanDisk1/eclipse-workspace-jmail/BjlFileSystemConsole/target/test-classes:/Volumes/SanDisk1/eclipse-workspace-jmail/BjlFileSystemConsole/target/classes:/Users/tony/.p2/pool/plugins/junit-jupiter-api_5.11.3.jar:/Users/tony/.p2/pool/plugins/junit-jupiter-engine_5.11.3.jar:/Users/tony/.p2/pool/plugins/junit-jupiter-migrationsupport_5.11.3.jar:/Users/tony/.p2/pool/plugins/junit-jupiter-params_5.11.3.jar:/Users/tony/.p2/pool/plugins/junit-platform-commons_1.11.3.jar:/Users/tony/.p2/pool/plugins/junit-platform-engine_1.11.3.jar:/Users/tony/.p2/pool/plugins/junit-platform-launcher_1.11.3.jar:/Users/tony/.p2/pool/plugins/junit-platform-runner_1.11.3.jar:/Users/tony/.p2/pool/plugins/junit-platform-suite-api_1.11.3.jar:/Users/tony/.p2/pool/plugins/junit-platform-suite-engine_1.11.3.jar:/Users/tony/.p2/pool/plugins/junit-platform-suite-commons_1.11.3.jar:/Users/tony/.p2/pool/plugins/junit-vintage-engine_5.11.3.jar:/Users/tony/.p2/pool/plugins/org.opentest4j_1.3.0.jar:/Users/tony/.p2/pool/plugins/org.apiguardian.api_1.1.2.jar:/Users/tony/.p2/pool/plugins/org.junit_4.13.2.v20240929-1000.jar:/Users/tony/.p2/pool/plugins/org.hamcrest_3.0.0.jar:/Volumes/SanDisk1/eclipse-workspace-jmail/BjlFileSystemConsole/lib/antlr-4.13.2-complete.jar:/Volumes/SanDisk1/eclipse-workspace-jmail/BjlFileSyetmViewer/target/classes:/Volumes/SanDisk1/eclipse-workspace-jmail/BjlFileSyetmViewer/target/test-classes:/Volumes/SanDisk1/eclipse-workspace-jmail/BjlFileSystem/target/classes:/Volumes/SanDisk1/eclipse-workspace-jmail/BjlFileSystem/target/test-classes:/Volumes/SanDisk1/eclipse-workspace-jmail/BjlCore/target/classes:/Volumes/SanDisk1/eclipse-workspace-jmail/BjlCore/target/test-classes:/Users/tony/.m2/repository/org/apache/logging/log4j/log4j-api/2.13.2/log4j-api-2.13.2.jar:/Users/tony/.m2/repository/org/apache/logging/log4j/log4j-core/2.13.2/log4j-core-2.13.2.jar:/Users/tony/.m2/repository/org/junit/jupiter/junit-jupiter-engine/5.9.1/junit-jupiter-engine-5.9.1.jar:/Users/tony/.m2/repository/org/junit/platform/junit-platform-engine/1.9.1/junit-platform-engine-1.9.1.jar:/Users/tony/.m2/repository/org/opentest4j/opentest4j/1.2.0/opentest4j-1.2.0.jar:/Users/tony/.m2/repository/org/junit/platform/junit-platform-commons/1.9.1/junit-platform-commons-1.9.1.jar:/Users/tony/.m2/repository/org/junit/jupiter/junit-jupiter-api/5.9.1/junit-jupiter-api-5.9.1.jar:/Users/tony/.m2/repository/org/apiguardian/apiguardian-api/1.1.2/apiguardian-api-1.1.2.jar:/Users/tony/.m2/repository/org/junit/platform/junit-platform-suite-engine/1.9.1/junit-platform-suite-engine-1.9.1.jar:/Users/tony/.m2/repository/org/junit/platform/junit-platform-suite-api/1.9.1/junit-platform-suite-api-1.9.1.jar:/Users/tony/.m2/repository/org/junit/platform/junit-platform-suite-commons/1.9.1/junit-platform-suite-commons-1.9.1.jar:/Users/tony/.m2/repository/org/junit/platform/junit-platform-launcher/1.9.1/junit-platform-launcher-1.9.1.jar:/Volumes/SanDisk1/eclipse-workspace-jmail/BjlIo/target/classes:/Volumes/SanDisk1/eclipse-workspace-jmail/BjlIo/target/test-classes:/Users/tony/.m2/repository/junit/junit/4.12/junit-4.12.jar:/Users/tony/.m2/repository/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar:/Volumes/SanDisk1/eclipse-workspace-jmail/BjlFileSystemJdbc/target/classes:/Volumes/SanDisk1/eclipse-workspace-jmail/BjlFileSystemJdbc/target/test-classes:/Users/tony/.m2/repository/org/hsqldb/hsqldb/2.7.3/hsqldb-2.7.3.jar:/Users/tony/.m2/repository/org/postgresql/postgresql/42.7.4/postgresql-42.7.4.jar:/Users/tony/.m2/repository/org/checkerframework/checker-qual/3.42.0/checker-qual-3.42.0.jar:/Users/tony/.m2/repository/mysql/mysql-connector-java/8.0.20/mysql-connector-java-8.0.20.jar:/Users/tony/.m2/repository/com/google/protobuf/protobuf-java/3.6.1/protobuf-java-3.6.1.jar:/Users/tony/.m2/repository/org/xerial/sqlite-jdbc/3.47.2.0/sqlite-jdbc-3.47.2.0.jar:/Volumes/SanDisk1/eclipse-workspace-jmail/BjlFileSystemSftp/target/classes:/Volumes/SanDisk1/eclipse-workspace-jmail/BjlFileSystemSftp/target/test-classes:/Users/tony/.m2/repository/com/jcraft/jsch/0.1.55/jsch-0.1.55.jar:/Users/tony/.m2/repository/com/sshtools/maverick-synergy-server/3.1.2/maverick-synergy-server-3.1.2.jar:/Users/tony/.m2/repository/com/sshtools/maverick-base/3.1.2/maverick-base-3.1.2.jar:/Users/tony/.m2/repository/com/sshtools/maverick-logging/3.1.2/maverick-logging-3.1.2.jar:/Users/tony/.m2/repository/com/sshtools/maverick-utils/3.1.2/maverick-utils-3.1.2.jar:/Users/tony/.m2/repository/com/sshtools/maverick-synergy-common/3.1.2/maverick-synergy-common-3.1.2.jar:/Users/tony/.m2/repository/com/sshtools/maverick-virtual-session/3.1.2/maverick-virtual-session-3.1.2.jar:/Users/tony/.m2/repository/org/jetbrains/pty4j/pty4j/0.12.11/pty4j-0.12.11.jar:/Users/tony/.m2/repository/org/jetbrains/annotations/20.1.0/annotations-20.1.0.jar:/Users/tony/.m2/repository/org/jetbrains/pty4j/purejavacomm/0.0.11.1/purejavacomm-0.0.11.1.jar:/Users/tony/.m2/repository/net/java/dev/jna/jna-platform/5.12.1/jna-platform-5.12.1.jar:/Users/tony/.m2/repository/net/java/dev/jna/jna/5.12.1/jna-5.12.1.jar:/Users/tony/.m2/repository/org/jline/jline/3.21.0/jline-3.21.0.jar:/Users/tony/.m2/repository/commons-cli/commons-cli/1.5.0/commons-cli-1.5.0.jar:/Users/tony/.m2/repository/org/apache/commons/commons-lang3/3.12.0/commons-lang3-3.12.0.jar:/Users/tony/.m2/repository/com/sshtools/maverick-virtual-filesystem/3.1.2/maverick-virtual-filesystem-3.1.2.jar:/Users/tony/.m2/repository/org/apache/commons/commons-vfs2/2.9.0/commons-vfs2-2.9.0.jar:/Users/tony/.m2/repository/commons-logging/commons-logging/1.2/commons-logging-1.2.jar:/Volumes/SanDisk1/eclipse-workspace-jmail/BjlFileSystemFtp/target/classes:/Volumes/SanDisk1/eclipse-workspace-jmail/BjlFileSystemFtp/target/test-classes:/Volumes/SanDisk1/eclipse-workspace-jmail/BjlNetFtp/target/classes:/Volumes/SanDisk1/eclipse-workspace-jmail/BjlNetFtp/target/test-classes:/Volumes/SanDisk1/eclipse-workspace-jmail/BjlNetFramework/target/classes:/Volumes/SanDisk1/eclipse-workspace-jmail/BjlNetFramework/target/test-classes:/Users/tony/.m2/repository/us/bringardner/bjl_net/0.1.0/bjl_net-0.1.0.jar:/Users/tony/.m2/repository/junit/junit/4.10/junit-4.10.jar:/Users/tony/.m2/repository/org/hamcrest/hamcrest-core/1.1/hamcrest-core-1.1.jar:/Users/tony/.m2/repository/org/junit/jupiter/junit-jupiter-engine/5.11.4/junit-jupiter-engine-5.11.4.jar:/Users/tony/.m2/repository/org/junit/platform/junit-platform-engine/1.11.4/junit-platform-engine-1.11.4.jar:/Users/tony/.m2/repository/org/junit/jupiter/junit-jupiter-api/5.11.4/junit-jupiter-api-5.11.4.jar:/Users/tony/.m2/repository/org/opentest4j/opentest4j/1.3.0/opentest4j-1.3.0.jar:/Users/tony/.m2/repository/org/junit/platform/junit-platform-commons/1.11.4/junit-platform-commons-1.11.4.jar:/Users/tony/.m2/repository/com/jcraft/jsch/0.1.54/jsch-0.1.54.jar:/Users/tony/.m2/repository/com/formdev/flatlaf/3.5.4/flatlaf-3.5.4.jar:/Users/tony/.m2/repository/com/formdev/flatlaf-intellij-themes/3.5.4/flatlaf-intellij-themes-3.5.4.jar:/Users/tony/.m2/repository/com/miglayout/miglayout-swing/11.4.2/miglayout-swing-11.4.2.jar:/Users/tony/.m2/repository/com/miglayout/miglayout-core/11.4.2/miglayout-core-11.4.2.jar:/Users/tony/.m2/repository/com/formdev/flatlaf-fonts-inter/4.0/flatlaf-fonts-inter-4.0.jar:/Users/tony/.m2/repository/com/formdev/flatlaf-fonts-jetbrains-mono/2.304/flatlaf-fonts-jetbrains-mono-2.304.jar:/Users/tony/.m2/repository/com/formdev/flatlaf-fonts-roboto/2.137/flatlaf-fonts-roboto-2.137.jar:/Users/tony/.m2/repository/com/formdev/flatlaf-fonts-roboto-mono/3.000/flatlaf-fonts-roboto-mono-3.000.jar:/Users/tony/.m2/repository/com/formdev/flatlaf-extras/3.5.4/flatlaf-extras-3.5.4.jar:/Users/tony/.m2/repository/com/github/weisj/jsvg/1.4.0/jsvg-1.4.0.jar:/Users/tony/.m2/repository/org/apache/sshd/sshd-core/0.12.0/sshd-core-0.12.0.jar:/Users/tony/.m2/repository/org/apache/mina/mina-core/2.0.7/mina-core-2.0.7.jar:/Users/tony/.m2/repository/org/slf4j/slf4j-jdk14/1.6.4/slf4j-jdk14-1.6.4.jar:/Users/tony/.m2/repository/org/slf4j/slf4j-api/1.6.4/slf4j-api-1.6.4.jar:/Users/tony/.m2/repository/org/bouncycastle/bcpg-jdk15on/1.49/bcpg-jdk15on-1.49.jar:/Users/tony/.m2/repository/org/bouncycastle/bcprov-jdk15on/1.49/bcprov-jdk15on-1.49.jar:/Users/tony/.m2/repository/org/bouncycastle/bcpkix-jdk15on/1.49/bcpkix-jdk15on-1.49.jar:/Users/tony/.m2/repository/tomcat/tomcat-apr/5.5.23/tomcat-apr-5.5.23.jar:/Users/tony/.m2/repository/net/sf/jfcunit/jfcunit/2.08/jfcunit-2.08.jar:/Users/tony/.m2/repository/junit/junit/3.8.1/junit-3.8.1.jar:/Users/tony/.m2/repository/jakarta-regexp/jakarta-regexp/1.4/jakarta-regexp-1.4.jar 
	us.bringardner.io.filesource.console.test.PracticeProcecc 

	 */

	public static void printProcessDetails(ProcessHandle process){

		//Get the instance of process info
		ProcessHandle.Info info = process.info();
		if ( info.command().orElse("").equals("")){
			return;
		}
		
		//Get the process id
		System.out.println("Process id: " + process.pid());
		//Get the command pathname of the process
		System.out.println("Command: " + info.command().orElse(""));
		//Get the arguments of the process
		String[] arguments = info.arguments().orElse(new String[]{});
		if ( arguments.length != 0){
			System.out.print("Arguments: ");
			for(String arg : arguments){
				System.out.print(arg + " ");
			}
			System.out.println();
		}
		//Get the start time of the process
		System.out.println("Started at: " + info.startInstant().orElse(Instant.now()).toString());
		//Get the time the process ran for
		System.out.println("Ran for: " + info.totalCpuDuration().orElse(Duration.ofMillis(0)).toMillis() + "ms");
		//Get the owner of the process
		System.out.println("Owner: " + info.user().orElse(""));
		String [] parts = info.command().orElse("").split("/");
		String cmd = parts[parts.length-1];
		String line = String.format("% 5d %s %s", process.pid(),cmd,info.user().orElse(""));
		System.out.println(line);
	}

	public static class EscapeCode {
		byte data[];
		byte code;

		public EscapeCode(byte[]data,byte code) {
			this.data=data;
			this.code = code;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof EscapeCode) {
				EscapeCode other = (EscapeCode) obj;
				if( other.data.length == data.length) {
					for (int idx = 0;idx < data.length; idx++) {
						if(data[idx] == other.data[idx]) {
							return false;
						}
					}
				}
				return true;
			} else {
				return false;
			}			
		}
	}

	public static List<EscapeCode> codes = new ArrayList<>();
	static {
		byte [] data = {27,27};
		EscapeCode c = new EscapeCode(data,(byte)27);
		codes.add(c);
	}
	public static void testVt100() throws IOException {
		File file = new File("./target/test.txt").getCanonicalFile();
		//                                           /usr/bin/vi
		ProcessBuilder builder = new ProcessBuilder("/usr/bin/vi",file.getAbsolutePath());
		Process p;
		try {
			p = builder.start();

			final InputStream in = p.getInputStream();
			final OutputStream out = p.getOutputStream();

			new Thread(()->{
				try {
					Thread.sleep(4000);
					//out.write(27);					
					out.write(":q\r\n".getBytes());
					out.flush();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}).start();

			new Thread(()->{
				byte data [] = new byte[3];
				int col = 0;

				try {
					int i = in.read(data);

					while( i !=-1) {
						System.out.println(" i="+i);
						for (int idx = 0; idx < i; idx++) {
							System.out.print(" "+((char)+data[idx]));
							if( (col +=2) >60) {
								col = 0;
								System.out.println();
							}	
						}
						i = in.read(data);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}).start();
			int time = 0;
			while(p.isAlive()) {
				try {
					p.waitFor(1000, TimeUnit.MILLISECONDS);
					if( ++time > 3) { 
						Console.System_out.println("ExternalProcess Waiting for "+(time*1000));
					}
				} catch (InterruptedException e) {
				}
			}
			int exitCode = p.exitValue();
			System.out.println("ec="+exitCode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void jline() {
		 try {
	            // Create a terminal
	            Terminal terminal = TerminalBuilder.builder()
	                    .system(true)
	                    .build();

	            // Create line reader
	            LineReader reader = LineReaderBuilder.builder()
	                    .terminal(terminal)
	                    .build();

	            // Prompt and read input
	            String line = reader.readLine("JLine > ");

	            // Print the result
	            System.out.println("You entered: " + line);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		//String directory = "/Users/tony";
		 Runtime run  = Runtime.getRuntime();
         String cmd [] = {"vi","test.sh"};
         
		 Process p = run.exec(cmd);
		 @SuppressWarnings("resource")
		NativeKeyboard kb = new NativeKeyboard();
		 OutputStream stream = p.getOutputStream();
		 //stream.write(":q\n".getBytes());
		 
		 
		 while( p.isAlive()) {
			 int key = kb.read();
			 if( key > 0 ) {
				 System.out.println("key="+key);
				 stream.write(key);
				 stream.flush();
				 System.out.println("flushed key="+key);
			 }
		 }
		 
		 String err = new String(p.getErrorStream().readAllBytes());
		 System.out.println("err="+err);
		 String out = new String(p.getInputStream().readAllBytes());
		 System.out.println("out="+out);
		 System.out.println("Waiting...");
		 int exitCode = p.waitFor();
		 System.out.println("exitCode="+exitCode);
	}
	
	public static void main4(String[] args) throws IOException {
		String directory = "/Users/tony";
		 ProcessBuilder[] builders = {
		              new ProcessBuilder("find", directory, "-type", "f"),
		              new ProcessBuilder("xargs", "grep", "-h", "^import "),
		              new ProcessBuilder("awk", "{print $2;}"),
		              new ProcessBuilder("sort", "-u")};
		 List<Process> processes = ProcessBuilder.startPipeline(
		         Arrays.asList(builders));
		 Process last = processes.get(processes.size()-1);
		 try (InputStream is = last.getInputStream();
		         Reader isr = new InputStreamReader(is);
		         BufferedReader r = new BufferedReader(isr)) {
		     long count = r.lines().count();
		     System.out.println("count="+count);
		 }

	}
	
	public static void main3(String[] args) {
		
	    try {
	        // Create a terminal
	        Terminal terminal = TerminalBuilder.builder().system(true).build();

	        // Create a line reader
	        LineReader reader = LineReaderBuilder.builder().terminal(terminal).build();

	        // Get the main key map
	        KeyMap<Binding> keyMap = reader.getKeyMaps().get(LineReader.MAIN);

	        // Bind Ctrl+T to transpose characters (swap the character before and at the cursor)
	        keyMap.bind(new Reference(LineReader.TRANSPOSE_CHARS), ctrl('T'));

	        // Bind Alt+U to uppercase the current word
	        keyMap.bind(new Reference("upcase-word"), alt('U'));

	        // Bind Alt+L to lowercase the current word
	        keyMap.bind(new Reference("downcase-word"), alt('L'));

	        // Bind Alt+C to capitalize the current word
	        keyMap.bind(new Reference(LineReader.CAPITALIZE_WORD), alt('C'));

	        // Bind Ctrl+K to kill (cut) text from cursor to end of line
	        keyMap.bind(new Reference(LineReader.KILL_LINE), ctrl('K'));

	        // Bind Ctrl+Y to yank (paste) previously killed text
	        keyMap.bind(new Reference(LineReader.YANK), ctrl('Y'));

	        // Bind Ctrl+R to reverse search in history
	        keyMap.bind(new Reference(LineReader.HISTORY_INCREMENTAL_SEARCH_BACKWARD), ctrl('R'));

	        // Display instructions
	        terminal.writer().println("Key Binding Example");
	        terminal.writer().println("------------------");
	        terminal.writer().println("Try these key bindings:");
	        terminal.writer().println("  Ctrl+T: Transpose characters");
	        terminal.writer().println("  Alt+U: Uppercase word");
	        terminal.writer().println("  Alt+L: Lowercase word");
	        terminal.writer().println("  Alt+C: Capitalize word");
	        terminal.writer().println("  Ctrl+K: Kill line");
	        terminal.writer().println("  Ctrl+Y: Yank");
	        terminal.writer().println("  Ctrl+R: Search history");
	        terminal.writer().println();
	        terminal.writer().println("Type 'exit' to quit");
	        terminal.writer().println();

	        // Read lines with custom key bindings
	        while (true) {
	            try {
	                String line = reader.readLine("keybinding> ");

	                if ("exit".equalsIgnoreCase(line)) {
	                    break;
	                }

	                terminal.writer().println("You entered: " + line);
	                terminal.writer().flush();
	            } catch (UserInterruptException e) {
	                // Ctrl+C
	                terminal.writer().println("Interrupted");
	            } catch (EndOfFileException e) {
	                // Ctrl+D
	                terminal.writer().println("EOF");
	                break;
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void main2(String[] args) {
	    try {
	        // Create a terminal
	        Terminal terminal = TerminalBuilder.builder().system(true).build();

	        // Create a line reader
	        LineReader reader = LineReaderBuilder.builder().terminal(terminal).build();

	        // Set variables for prompt customization
	        reader.setVariable("prompt", "%N@%M:%w%n> ");
	        reader.setVariable("rprompt", "%T");
	        reader.setVariable(LineReader.SECONDARY_PROMPT_PATTERN, "%M> ");

	        // Custom prompt with colors
	        AttributedString prompt =
	                new AttributedString("jline> ", AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN));

	        // Custom right prompt with colors
	        AttributedString rightPrompt =
	                new AttributedString("[demo]", AttributedStyle.DEFAULT.foreground(AttributedStyle.BLUE));

	        // Read lines with different prompt styles
	        try {
	            // Using pattern-based prompt
	            String line1 = reader.readLine();
	            terminal.writer().println("You entered: " + line1);

	            // Using AttributedString prompt (convert to String)
	            String line2 = reader.readLine(prompt.toAnsi(), rightPrompt.toAnsi(), (Character) null, null);
	            terminal.writer().println("You entered: " + line2);

	            // Using a simple string prompt
	            String line3 = reader.readLine("simple> ");
	            terminal.writer().println("You entered: " + line3);

	            // Using a masked prompt for passwords
	            String password = reader.readLine("Password: ", '*');
	            terminal.writer().println("Password length: " + password.length());

	        } catch (UserInterruptException e) {
	            // Ctrl+C
	            terminal.writer().println("Interrupted");
	        } catch (EndOfFileException e) {
	            // Ctrl+D
	            terminal.writer().println("EOF");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static void main1(String[] args) throws IOException {
		printProcessDetails(ProcessHandle.current());
		System.exit(0);
		Stream<ProcessHandle> processes = ProcessHandle.allProcesses();
		AtomicInteger nameSize = new AtomicInteger();

		// Filter the processes to only include Java processes
		processes.forEach((p)->{
			String [] parts = p.info().command().orElse("").split("/");
			String cmd = parts[parts.length-1];
			nameSize.set(Math.max(nameSize.get(), cmd.length()));
		});

		processes = ProcessHandle.allProcesses();
		// Filter the processes to only include Java processes
		processes.forEach((p)->{
			String [] parts = p.info().command().orElse("").split("/");
			String cmd = parts[parts.length-1];
			int need = nameSize.get()  - cmd.length();
			if( need > 0 ) {
				cmd += "                                                              ".substring(0,need);
			}
			String line = String.format("% 5d %s %s", p.pid(),cmd,p.info().user().orElse(""));
			System.out.println(line);

		});

		System.exit(0);;
		System.out.println();
		ProcessHandle.Info info = ProcessHandle.current().info();
		String command = info.command().orElse("");
		String[] arguments = info.arguments().orElse(new String[]{});
		List<String> list = new ArrayList<>();
		list.add(command);
		for (int idx = 0; idx < arguments.length; idx++) {
			String arg = arguments[idx];
			if( !arg.startsWith("-agentlib:") 
					&& !arg.startsWith("-javaagent:")
					) {
				list.add(arg);
			}
			if( arg.startsWith("us.bring")) {
				break;
			}

		}


		if( args.length==0) {
			list.add("-stop");
			ProcessBuilder builder = new ProcessBuilder(list);
			Process p = builder.start();
			int time = 0;
			while(p.isAlive()) {
				try {
					p.waitFor(1000, TimeUnit.MILLISECONDS);
					if( ++time > 3) { 
						Console.System_out.println("ExternalProcess Waiting for "+(time*1000));
					}
				} catch (InterruptedException e) {
				}
			}
			int exitCode = p.exitValue();

			System.out.println("Child exiteCode = "+exitCode);
			String out = new String(p.getInputStream().readAllBytes());
			System.out.println("child out = "+out);
		} else {
			System.out.println("In child");
			System.exit(1);;
		}



	}

	

}
