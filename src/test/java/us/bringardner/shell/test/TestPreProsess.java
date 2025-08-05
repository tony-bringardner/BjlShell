package us.bringardner.shell.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import us.bringardner.shell.Console;
import us.bringardner.shell.ShellContext;
import us.bringardner.shell.antlr.FileSourceShPreProcessorVisitorImpl;

public class TestPreProsess {

	@Test
	public void testHere_01() {
		
		Console console = new Console();
		ShellContext ctx = new ShellContext(console);
		ctx.enterCommand();
		String code1 = "cat <<-EOT \n"
				+ "\tline 1\n"
				+ "\t  line2\n"
				+ "    line3\n"
				+ "\tEOT\nls -ltr\n"
				+ "cat <<EOT \n"
				+ "\tline 1\n"
				+ "\t  line2\n"
				+ "    line3\n"
				+ "\tEOT\nls -ltr"
				;
		String code = console.preProcess(code1,ctx);
		//System.out.println(code1);
		String[] parts = code.split("\n");
		assertEquals(4, parts.length);
		assertTrue(parts[0].startsWith("cat <<EOT"));
		assertTrue(parts[2].startsWith("cat <<EOT"));
		assertEquals(parts[1], "ls -ltr");
		assertEquals(parts[3], "ls -ltr");
		String expect = "line 1\n"
				+ "  line2\n"
				+ "    line3\n\n"
				;
		String id = parts[0].substring(6).trim();
		String val = (String) ctx.getValue(id);
		assertEquals(expect, val);
		expect = "\tline 1\n"
				+ "\t  line2\n"
				+ "    line3\n\t"
				;
		id = parts[2].substring(6).trim();
		val = (String) ctx.getValue(id);
		assertEquals(expect, val);
		ctx.exitCommand();
		//  id should be gone now
		val = (String) ctx.getValue(id);
		assertNull(val);
	}
	

	@Test
	public void testMathSymbols2() {
		Console console = new Console();
		ShellContext ctx = new ShellContext(console);
		ctx.enterCommand();
		String expect = 
				"$((1-2))";
		
		String code = expect;
		String actual = console.preProcess(code, ctx);
		assertEquals(expect, actual);
		ctx.exitCommand();
	}

	@Test
	public void testMathSymbols() {
		Console console = new Console();
		ShellContext ctx = new ShellContext(console);
		ctx.enterCommand();
		String expect = 
				"str=\" test -gt -lt\"\n"
				+ "str=' test -gt -lt'\n"
				+ "until [ $counter < 5 ]\n"
				+ "until [ $counter > 5 ]\n"
				+ "until [ $counter >= 5 ]\n"
				+ "until [ $counter <= 5 ]\n"
				+ "until [ $counter == 5 ]\n"
				+ "until [ $counter != 5 ]\n"
				+ "do";
		
		String code = 
				"str=\" test -gt -lt\"\n"
				+ "str=' test -gt -lt'\n"
				+ "until [ $counter -lt 5 ]\n"
				+ "until [ $counter -gt 5 ]\n"
				+ "until [ $counter -ge 5 ]\n"
				+ "until [ $counter -le 5 ]\n"
				+ "until [ $counter -eq 5 ]\n"
				+ "until [ $counter -ne 5 ]\n"
				+ "do";
		String actual = console.preProcess(code, ctx);
		assertEquals(expect, actual);
		ctx.exitCommand();
	}
	//Outer loop: $i
	@Test
	public void testExpandString03() {
		ShellContext ctx = new ShellContext(new Console());
		ctx.setVariable("j", "1");
		String expect = "    Inner loop: 1";
		String code = "    Inner loop: $j";
		String actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		//System.out.println("actual = "+actual);
		assertEquals(expect, actual);
		
	}

	@Test
	public void testExpandString01() {
		ShellContext ctx = new ShellContext(new Console());
		ctx.setVariable("name", "tony");
		String expect = "hello tony";
		String code = "hello $name";
		String actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		//System.out.println("actual = "+actual);
		assertEquals(expect, actual);
		
	}

	@Test
	public void testExpandString02() {
		ShellContext ctx = new ShellContext(new Console());
		ctx.setVariable("var", "tony");
		String expect = "hello tony";
		String code = "hello $var";
		String actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		//System.out.println("actual = "+actual);
		assertEquals(expect, actual);
		
	}

	@Test
	public void testExpandString04() {
		ShellContext ctx = new ShellContext(new Console());
		ctx.setVariable("name", "tony");
		String expect = "hello tony";
		String code = "hello ${name-uns et}";
		String actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		//System.out.println("actual = "+actual);
		assertEquals(expect, actual);
		
	}

	@Test
	public void testExpandSubtract01() {
		ShellContext ctx = new ShellContext(new Console());
		String expect = "7";
		String code = "$((10-3))";
		String actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		assertEquals(expect, actual);
		
		ctx = new ShellContext(new Console());
		expect = "7.0";
		code = "$((10-3.0))";
		actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		assertEquals(expect, actual);
		
	}
	
	@Test
	public void testExpandStringAdd01() {
		ShellContext ctx = new ShellContext(new Console());
		String expect = "7";
		String code = "$((5+2))";
		String actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		//System.out.println("actual = "+actual);
		assertEquals(expect, actual);
		
		expect = "7.0";
		code = "$((5.0+2))";
		actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		//System.out.println("actual = "+actual);
		assertEquals(expect, actual);
		
	}

	@Test
	public void testExpandStringMultiply01() {
		ShellContext ctx = new ShellContext(new Console());
		String expect = "10";
		String code = "$((5*2))";
		String actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		assertEquals(expect, actual);
		
		expect = "10.0";
		code = "$((5.0*2))";
		actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		assertEquals(expect, actual);
		
	}

	@Test
	public void testExpandStringDivide01() {
		ShellContext ctx = new ShellContext(new Console());
		String expect = "2";
		String code = "$((5/2))";
		String actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		assertEquals(expect, actual);
		
		expect = "2.5";
		code = "$((5.0/2))";
		actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		assertEquals(expect, actual);
		
	}

	@Test
	public void testExpandStringMod01() {
		ShellContext ctx = new ShellContext(new Console());
		String expect = "1";
		String code = "$((5%2))";
		String actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		assertEquals(expect, actual);
		
		expect = "1.0";
		code = "$((5.0%2))";
		actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		assertEquals(expect, actual);
		
	}
	
	@Test
	public void testExpandStringPow01() {
		ShellContext ctx = new ShellContext(new Console());
		String expect = "100";
		String code = "$((10**2))";
		String actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		assertEquals(expect, actual);
		
		expect = "100.0";
		code = "$((10.0**2))";
		actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		assertEquals(expect, actual);
		
		expect = "100.0";
		code = "$((10**2.0))";
		actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		assertEquals(expect, actual);
	}

	@Test
	public void testExpandStringPlusEq() {
		ShellContext ctx = new ShellContext(new Console());
		ctx.setVariable("name", 10);
		String expect = "13";
		String code = "$((name+=3))";
		String actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		assertEquals(expect, actual);
		ctx.setVariable("name", 10.0);
		expect = "13.0";
		code = "$((name+=3))";
		actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		assertEquals(expect, actual);
		
	}
	@Test
	public void testExpandStringMinusEq() {
		ShellContext ctx = new ShellContext(new Console());
		ctx.setVariable("name", 13);
		String expect = "10";
		String code = "$((name-=3))";
		String actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		assertEquals(expect, actual);
		ctx.setVariable("name", 13.0);
		expect = "10.0";
		code = "$((name-=3))";
		actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		assertEquals(expect, actual);
		
	}
	
	@Test
	public void testExpandStringMultiplyEq() {
		ShellContext ctx = new ShellContext(new Console());
		ctx.setVariable("name", 10);
		String expect = "100";
		String code = "$((name*=10))";
		String actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		assertEquals(expect, actual);
		ctx.setVariable("name", 10.0);
		expect = "100.0";
		code = "$((name*=10))";
		actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		assertEquals(expect, actual);
		
	}
	
	@Test
	public void testExpandStringDivideEq() {
		ShellContext ctx = new ShellContext(new Console());
		ctx.setVariable("name", 10);
		String expect = "5";
		String code = "$((name/=2))";
		String actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		assertEquals(expect, actual);
		ctx.setVariable("name", 10.0);
		expect = "5.0";
		code = "$((name/=2))";
		actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		assertEquals(expect, actual);
		
	}
	
	@Test
	public void testExpandStringModEq() {
		ShellContext ctx = new ShellContext(new Console());
		ctx.setVariable("name", 11);
		String expect = "1";
		String code = "$((name%=2))";
		String actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		assertEquals(expect, actual);
		ctx.setVariable("name", 11.0);
		expect = "1.0";
		code = "$((name%=2))";
		actual = FileSourceShPreProcessorVisitorImpl.processString(code, ctx);
		assertEquals(expect, actual);
		
	}
	
	@Test
	public void testExpandBrace01() {
		
		String [] code = {
				"nothin to do here\n",
				"for j in {1..3}; do\n"
				+ "for j in {12..33..3}; do\n"
				+ "    if [[ $j -eq 2 ]]; then\n"
				+ "      break\n"
				+ "    fi\n"
				+ "    echo \"j: $j\"\n"
				+ "  done\n",
				"pre{1..8}\n",
				"cmd arg {12..20..2}post\n",
				"pre{1..2}post\n",
				"pre{a..z..3}post\n",
				"pre{A..Z..3}\n",
				"pre{a..z}post\n",

		};

		String [] expect = {
				"nothin to do here\n",
				 "for j in 1 2 3; do\n"
				+ "for j in 12 15 18 21 24 27 30 33; do\n"
				+ "    if [[ $j == 2 ]]; then\n"
				+ "      break\n"
				+ "    fi\n"
				+ "    echo \"j: $j\"\n"
				+ "  done\n",
				 "pre1 pre2 pre3 pre4 pre5 pre6 pre7 pre8\n",
				 "cmd arg 12post 14post 16post 18post 20post\n",
				 "pre1post pre2post\n",
				 "preapost predpost pregpost prejpost prempost preppost prespost prevpost preypost\n",
				 "preA preD preG preJ preM preP preS preV preY\n",
				 "preapost prebpost precpost predpost preepost prefpost pregpost prehpost preipost prejpost prekpost prelpost prempost prenpost preopost preppost preqpost prerpost prespost pretpost preupost prevpost prewpost prexpost preypost prezpost\n"
		};
		Console console = new Console();
		ShellContext ctx = new ShellContext(console);
		for (int idx = 0; idx < expect.length; idx++) {
			String actual = console.preProcess(code[idx], ctx);			
			assertEquals(expect[idx], actual,code[idx]);
		}
		
	}
	
	
	
	@Test
	public void testHashSymbols2() {
		Console console = new Console();
		String code = "be#fore ${#parameter} a#fter "+
					"bef#re ${parameter#word} af#ter "+
					 "bef#ore ${parameter##word} aft#er ";
		String expect = "be#fore ${|parameter} a#fter bef#re ${parameter|word} af#ter bef#ore ${parameter||word} aft#er ";	
		String actual = console.convertHash(code);
		
		assertEquals(expect, actual);
	}
	
}
