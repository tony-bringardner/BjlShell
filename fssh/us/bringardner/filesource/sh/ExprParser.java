// Generated from /Volumes/Data/eclipse-git/BjlShell/Antlr4/ExprParser.g4 by ANTLR 4.13.2
package us.bringardner.filesource.sh;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class ExprParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PATH=1, SEMI=2, NL=3, LT=4, LT_EQ=5, GT=6, GT_EQ=7, NOT=8, AND=9, OR=10, 
		NUMBER=11, VARIABLE=12, DOT=13, SLASH=14, INTEGER=15, DECIMAL=16, DQ_STRING=17, 
		SQ_STRING=18, ESC=19, WS=20, TRUE=21, FALSE=22, COMMENT=23, LINE_COMMENT=24, 
		LCURLY=25, RCURLY=26, QUOTE=27, BACKQUOTE=28, CONTINUE=29, BREAK=30, FOR=31, 
		SELECT=32, IN=33, WHILE=34, DONE=35, UNTIL=36, IF=37, FI=38, THEN=39, 
		ELSE=40, ELIF=41, CASE=42, ESAC=43, PERC=44, PLUS=45, STAR=46, POW=47, 
		DO=48, EQ=49, EQUALITY=50, RX_EQUALITY=51, NOT_EQ=52, MINUS=53, PIPE=54, 
		AMP=55, TILDE=56, QUESTION=57, TIME=58, LPAREN=59, RPAREN=60, LSQUARE=61, 
		RSQUARE=62, REDIRECT_APPEND_OUT_2=63, REDIRECT_APPEND_OUT=64, REDIRECT_READ_WRITE=65, 
		REDIRECT_BOTH=66, REDIRECT_BOTH_2=67, REDIRECT_INPUT_FROM_FID=68, COMMA=69, 
		MINUS_ASSIGN=70, STAR_ASSIGN=71, DIV_ASSIGN=72, MOD_ASSIGN=73, DIGIT=74, 
		SPECIAL_UNIX=75, SPECIAL_WINDOWS=76, POS=77, PERC_PERC=78, PERC_MINUS=79, 
		PERC_PLUS=80, PERC_QUESTION=81, ID=82, LETTER_OR_DIGIT=83, P1=84, AT=85, 
		TEXT=86, DOLLAR_LPAREN_LPAREN=87, RPAREN_RPAREN=88, LPAREN_LPAREN=89, 
		NOT_CURLY=90, DECLARE_A=91, DIVIDE=92, RX_CHAR=93, POSIX_CHAR_CLASS=94, 
		CHAR_CLASS=95;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_statement_type = 2, RULE_statment_terminator = 3, 
		RULE_command = 4, RULE_argument = 5, RULE_command_name = 6, RULE_assingment = 7, 
		RULE_expr = 8, RULE_value = 9, RULE_array_element = 10, RULE_constant = 11, 
		RULE_string = 12, RULE_boolean = 13, RULE_path = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "statement_type", "statment_terminator", "command", 
			"argument", "command_name", "assingment", "expr", "value", "array_element", 
			"constant", "string", "boolean", "path"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "';'", "'\\n'", "'<'", "'<='", "'>'", "'>='", "'!'", "'&&'", 
			"'||'", null, null, "'.'", "'/'", null, null, null, null, null, null, 
			"'true'", "'false'", null, null, "'{'", "'}'", "'''", "'`'", "'continue'", 
			"'break'", "'for'", "'select'", "'in'", "'while'", "'done'", "'until'", 
			"'if'", "'fi'", "'then'", "'else'", "'elif'", "'case'", "'esac'", "'%'", 
			"'+'", "'*'", "'**'", "'do'", "'='", null, "'=~'", null, "'-'", "'|'", 
			"'&'", "'~'", "'?'", "'time'", "'('", "')'", "'['", "']'", "'&>>'", "'>>'", 
			"'<>'", "'>&'", "'&>'", "'<&'", "','", "'-='", "'*='", "':^:='", "'%='", 
			null, null, null, "'^'", "'%%'", "'%-'", "'%+'", "'%?'", null, null, 
			null, "'@'", null, "'$(('", "'))'", "'(('", null, null, "':^:'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "PATH", "SEMI", "NL", "LT", "LT_EQ", "GT", "GT_EQ", "NOT", "AND", 
			"OR", "NUMBER", "VARIABLE", "DOT", "SLASH", "INTEGER", "DECIMAL", "DQ_STRING", 
			"SQ_STRING", "ESC", "WS", "TRUE", "FALSE", "COMMENT", "LINE_COMMENT", 
			"LCURLY", "RCURLY", "QUOTE", "BACKQUOTE", "CONTINUE", "BREAK", "FOR", 
			"SELECT", "IN", "WHILE", "DONE", "UNTIL", "IF", "FI", "THEN", "ELSE", 
			"ELIF", "CASE", "ESAC", "PERC", "PLUS", "STAR", "POW", "DO", "EQ", "EQUALITY", 
			"RX_EQUALITY", "NOT_EQ", "MINUS", "PIPE", "AMP", "TILDE", "QUESTION", 
			"TIME", "LPAREN", "RPAREN", "LSQUARE", "RSQUARE", "REDIRECT_APPEND_OUT_2", 
			"REDIRECT_APPEND_OUT", "REDIRECT_READ_WRITE", "REDIRECT_BOTH", "REDIRECT_BOTH_2", 
			"REDIRECT_INPUT_FROM_FID", "COMMA", "MINUS_ASSIGN", "STAR_ASSIGN", "DIV_ASSIGN", 
			"MOD_ASSIGN", "DIGIT", "SPECIAL_UNIX", "SPECIAL_WINDOWS", "POS", "PERC_PERC", 
			"PERC_MINUS", "PERC_PLUS", "PERC_QUESTION", "ID", "LETTER_OR_DIGIT", 
			"P1", "AT", "TEXT", "DOLLAR_LPAREN_LPAREN", "RPAREN_RPAREN", "LPAREN_LPAREN", 
			"NOT_CURLY", "DECLARE_A", "DIVIDE", "RX_CHAR", "POSIX_CHAR_CLASS", "CHAR_CLASS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "ExprParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExprParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(30);
				statement();
				}
				}
				setState(33); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 585503135938517250L) != 0) || _la==ID || _la==TEXT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public Statement_typeContext statement_type() {
			return getRuleContext(Statement_typeContext.class,0);
		}
		public Statment_terminatorContext statment_terminator() {
			return getRuleContext(Statment_terminatorContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(ExprParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ExprParser.WS, i);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WS) {
				{
				{
				setState(35);
				match(WS);
				}
				}
				setState(40);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(41);
			statement_type();
			setState(45);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WS) {
				{
				{
				setState(42);
				match(WS);
				}
				}
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(48);
			statment_terminator();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Statement_typeContext extends ParserRuleContext {
		public CommandContext command() {
			return getRuleContext(CommandContext.class,0);
		}
		public AssingmentContext assingment() {
			return getRuleContext(AssingmentContext.class,0);
		}
		public Statement_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).enterStatement_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).exitStatement_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitStatement_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Statement_typeContext statement_type() throws RecognitionException {
		Statement_typeContext _localctx = new Statement_typeContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement_type);
		try {
			setState(52);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(50);
				command();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(51);
				assingment();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Statment_terminatorContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(ExprParser.EOF, 0); }
		public TerminalNode NL() { return getToken(ExprParser.NL, 0); }
		public TerminalNode SEMI() { return getToken(ExprParser.SEMI, 0); }
		public Statment_terminatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statment_terminator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).enterStatment_terminator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).exitStatment_terminator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitStatment_terminator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Statment_terminatorContext statment_terminator() throws RecognitionException {
		Statment_terminatorContext _localctx = new Statment_terminatorContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_statment_terminator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			_la = _input.LA(1);
			if ( !(((((_la - -1)) & ~0x3f) == 0 && ((1L << (_la - -1)) & 25L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CommandContext extends ParserRuleContext {
		public ArgumentContext arguments;
		public Command_nameContext command_name() {
			return getRuleContext(Command_nameContext.class,0);
		}
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(ExprParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ExprParser.WS, i);
		}
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).enterCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).exitCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_command);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			command_name();
			setState(66);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(60);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==WS) {
						{
						{
						setState(57);
						match(WS);
						}
						}
						setState(62);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(63);
					((CommandContext)_localctx).arguments = argument();
					}
					} 
				}
				setState(68);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).exitArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_argument);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Command_nameContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Command_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).enterCommand_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).exitCommand_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitCommand_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Command_nameContext command_name() throws RecognitionException {
		Command_nameContext _localctx = new Command_nameContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_command_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssingmentContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode EQ() { return getToken(ExprParser.EQ, 0); }
		public List<TerminalNode> WS() { return getTokens(ExprParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(ExprParser.WS, i);
		}
		public AssingmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assingment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).enterAssingment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).exitAssingment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitAssingment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssingmentContext assingment() throws RecognitionException {
		AssingmentContext _localctx = new AssingmentContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_assingment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			expr(0);
			setState(77);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WS) {
				{
				{
				setState(74);
				match(WS);
				}
				}
				setState(79);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(80);
			match(EQ);
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WS) {
				{
				{
				setState(81);
				match(WS);
				}
				}
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(87);
			expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext left;
		public ExprContext ternaryTest;
		public Token op;
		public ExprContext unaryMinus;
		public ExprContext unaryPlus;
		public ExprContext unaryNot;
		public ExprContext single;
		public ExprContext right;
		public ExprContext ternaryTrue;
		public ExprContext ternaryFalse;
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode MINUS() { return getToken(ExprParser.MINUS, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(ExprParser.PLUS, 0); }
		public TerminalNode NOT() { return getToken(ExprParser.NOT, 0); }
		public TerminalNode LPAREN() { return getToken(ExprParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ExprParser.RPAREN, 0); }
		public TerminalNode STAR() { return getToken(ExprParser.STAR, 0); }
		public TerminalNode SLASH() { return getToken(ExprParser.SLASH, 0); }
		public TerminalNode LT() { return getToken(ExprParser.LT, 0); }
		public TerminalNode GT() { return getToken(ExprParser.GT, 0); }
		public TerminalNode PERC() { return getToken(ExprParser.PERC, 0); }
		public TerminalNode POW() { return getToken(ExprParser.POW, 0); }
		public TerminalNode GT_EQ() { return getToken(ExprParser.GT_EQ, 0); }
		public TerminalNode LT_EQ() { return getToken(ExprParser.LT_EQ, 0); }
		public TerminalNode NOT_EQ() { return getToken(ExprParser.NOT_EQ, 0); }
		public TerminalNode EQUALITY() { return getToken(ExprParser.EQUALITY, 0); }
		public TerminalNode OR() { return getToken(ExprParser.OR, 0); }
		public TerminalNode AND() { return getToken(ExprParser.AND, 0); }
		public TerminalNode QUESTION() { return getToken(ExprParser.QUESTION, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 16;
		enterRecursionRule(_localctx, 16, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PATH:
			case NUMBER:
			case VARIABLE:
			case DQ_STRING:
			case SQ_STRING:
			case ESC:
			case TRUE:
			case FALSE:
			case ID:
			case TEXT:
				{
				setState(90);
				value();
				}
				break;
			case MINUS:
				{
				setState(91);
				((ExprContext)_localctx).op = match(MINUS);
				setState(92);
				((ExprContext)_localctx).unaryMinus = expr(12);
				}
				break;
			case PLUS:
				{
				setState(93);
				((ExprContext)_localctx).op = match(PLUS);
				setState(94);
				((ExprContext)_localctx).unaryPlus = expr(11);
				}
				break;
			case NOT:
				{
				setState(95);
				((ExprContext)_localctx).op = match(NOT);
				setState(96);
				((ExprContext)_localctx).unaryNot = expr(10);
				}
				break;
			case LPAREN:
				{
				setState(97);
				match(LPAREN);
				setState(98);
				((ExprContext)_localctx).single = expr(0);
				setState(99);
				match(RPAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(132);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(130);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(103);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(104);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==SLASH || _la==STAR) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(105);
						((ExprContext)_localctx).right = expr(10);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(106);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(107);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(108);
						((ExprContext)_localctx).right = expr(9);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(109);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(110);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==LT || _la==GT) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(111);
						((ExprContext)_localctx).right = expr(8);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(112);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(113);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PERC || _la==POW) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(114);
						((ExprContext)_localctx).right = expr(7);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(115);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(116);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4503599627370656L) != 0)) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(117);
						((ExprContext)_localctx).right = expr(6);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(118);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(119);
						((ExprContext)_localctx).op = match(EQUALITY);
						setState(120);
						((ExprContext)_localctx).right = expr(5);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(121);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(122);
						((ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==AND || _la==OR) ) {
							((ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(123);
						((ExprContext)_localctx).right = expr(4);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						_localctx.ternaryTest = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(124);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(125);
						match(QUESTION);
						setState(126);
						((ExprContext)_localctx).ternaryTrue = expr(0);
						setState(127);
						match(AND);
						setState(128);
						((ExprContext)_localctx).ternaryFalse = expr(3);
						}
						break;
					}
					} 
				}
				setState(134);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValueContext extends ParserRuleContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public Array_elementContext array_element() {
			return getRuleContext(Array_elementContext.class,0);
		}
		public TerminalNode VARIABLE() { return getToken(ExprParser.VARIABLE, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).exitValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_value);
		try {
			setState(138);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(135);
				constant();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(136);
				array_element();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(137);
				match(VARIABLE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Array_elementContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ExprParser.ID, 0); }
		public List<TerminalNode> LSQUARE() { return getTokens(ExprParser.LSQUARE); }
		public TerminalNode LSQUARE(int i) {
			return getToken(ExprParser.LSQUARE, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> RSQUARE() { return getTokens(ExprParser.RSQUARE); }
		public TerminalNode RSQUARE(int i) {
			return getToken(ExprParser.RSQUARE, i);
		}
		public Array_elementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).enterArray_element(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).exitArray_element(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitArray_element(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Array_elementContext array_element() throws RecognitionException {
		Array_elementContext _localctx = new Array_elementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_array_element);
		try {
			int _alt;
			setState(158);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(140);
				match(ID);
				setState(141);
				match(LSQUARE);
				setState(142);
				expr(0);
				setState(143);
				match(RSQUARE);
				setState(150);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(144);
						match(LSQUARE);
						setState(145);
						expr(0);
						setState(146);
						match(RSQUARE);
						}
						} 
					}
					setState(152);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(153);
				match(ID);
				setState(154);
				match(LSQUARE);
				setState(155);
				expr(0);
				setState(156);
				match(RSQUARE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(ExprParser.NUMBER, 0); }
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public BooleanContext boolean_() {
			return getRuleContext(BooleanContext.class,0);
		}
		public PathContext path() {
			return getRuleContext(PathContext.class,0);
		}
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).exitConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_constant);
		try {
			setState(164);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(160);
				match(NUMBER);
				}
				break;
			case DQ_STRING:
			case SQ_STRING:
			case ESC:
			case TEXT:
				enterOuterAlt(_localctx, 2);
				{
				setState(161);
				string();
				}
				break;
			case TRUE:
			case FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(162);
				boolean_();
				}
				break;
			case PATH:
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(163);
				path();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StringContext extends ParserRuleContext {
		public TerminalNode DQ_STRING() { return getToken(ExprParser.DQ_STRING, 0); }
		public TerminalNode SQ_STRING() { return getToken(ExprParser.SQ_STRING, 0); }
		public TerminalNode ESC() { return getToken(ExprParser.ESC, 0); }
		public TerminalNode TEXT() { return getToken(ExprParser.TEXT, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_string);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 917504L) != 0) || _la==TEXT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BooleanContext extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(ExprParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(ExprParser.FALSE, 0); }
		public BooleanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolean; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).enterBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).exitBoolean(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitBoolean(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanContext boolean_() throws RecognitionException {
		BooleanContext _localctx = new BooleanContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_boolean);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PathContext extends ParserRuleContext {
		public List<TerminalNode> PATH() { return getTokens(ExprParser.PATH); }
		public TerminalNode PATH(int i) {
			return getToken(ExprParser.PATH, i);
		}
		public List<TerminalNode> ID() { return getTokens(ExprParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(ExprParser.ID, i);
		}
		public PathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_path; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).enterPath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExprParserListener ) ((ExprParserListener)listener).exitPath(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExprParserVisitor ) return ((ExprParserVisitor<? extends T>)visitor).visitPath(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PathContext path() throws RecognitionException {
		PathContext _localctx = new PathContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_path);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(171); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(170);
					_la = _input.LA(1);
					if ( !(_la==PATH || _la==ID) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(173); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 8:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 9);
		case 1:
			return precpred(_ctx, 8);
		case 2:
			return precpred(_ctx, 7);
		case 3:
			return precpred(_ctx, 6);
		case 4:
			return precpred(_ctx, 5);
		case 5:
			return precpred(_ctx, 4);
		case 6:
			return precpred(_ctx, 3);
		case 7:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001_\u00b0\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001\u0000\u0004\u0000"+
		" \b\u0000\u000b\u0000\f\u0000!\u0001\u0001\u0005\u0001%\b\u0001\n\u0001"+
		"\f\u0001(\t\u0001\u0001\u0001\u0001\u0001\u0005\u0001,\b\u0001\n\u0001"+
		"\f\u0001/\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0003"+
		"\u00025\b\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0005"+
		"\u0004;\b\u0004\n\u0004\f\u0004>\t\u0004\u0001\u0004\u0005\u0004A\b\u0004"+
		"\n\u0004\f\u0004D\t\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0005\u0007L\b\u0007\n\u0007\f\u0007O\t\u0007"+
		"\u0001\u0007\u0001\u0007\u0005\u0007S\b\u0007\n\u0007\f\u0007V\t\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003\bf\b\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0005"+
		"\b\u0083\b\b\n\b\f\b\u0086\t\b\u0001\t\u0001\t\u0001\t\u0003\t\u008b\b"+
		"\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0005"+
		"\n\u0095\b\n\n\n\f\n\u0098\t\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n"+
		"\u0003\n\u009f\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003"+
		"\u000b\u00a5\b\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0004"+
		"\u000e\u00ac\b\u000e\u000b\u000e\f\u000e\u00ad\u0001\u000e\u0000\u0001"+
		"\u0010\u000f\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u001c\u0000\n\u0001\u0001\u0002\u0003\u0002\u0000\u000e\u000e"+
		"..\u0002\u0000--55\u0002\u0000\u0004\u0004\u0006\u0006\u0002\u0000,,/"+
		"/\u0003\u0000\u0005\u0005\u0007\u000744\u0001\u0000\t\n\u0002\u0000\u0011"+
		"\u0013VV\u0001\u0000\u0015\u0016\u0002\u0000\u0001\u0001RR\u00bc\u0000"+
		"\u001f\u0001\u0000\u0000\u0000\u0002&\u0001\u0000\u0000\u0000\u00044\u0001"+
		"\u0000\u0000\u0000\u00066\u0001\u0000\u0000\u0000\b8\u0001\u0000\u0000"+
		"\u0000\nE\u0001\u0000\u0000\u0000\fG\u0001\u0000\u0000\u0000\u000eI\u0001"+
		"\u0000\u0000\u0000\u0010e\u0001\u0000\u0000\u0000\u0012\u008a\u0001\u0000"+
		"\u0000\u0000\u0014\u009e\u0001\u0000\u0000\u0000\u0016\u00a4\u0001\u0000"+
		"\u0000\u0000\u0018\u00a6\u0001\u0000\u0000\u0000\u001a\u00a8\u0001\u0000"+
		"\u0000\u0000\u001c\u00ab\u0001\u0000\u0000\u0000\u001e \u0003\u0002\u0001"+
		"\u0000\u001f\u001e\u0001\u0000\u0000\u0000 !\u0001\u0000\u0000\u0000!"+
		"\u001f\u0001\u0000\u0000\u0000!\"\u0001\u0000\u0000\u0000\"\u0001\u0001"+
		"\u0000\u0000\u0000#%\u0005\u0014\u0000\u0000$#\u0001\u0000\u0000\u0000"+
		"%(\u0001\u0000\u0000\u0000&$\u0001\u0000\u0000\u0000&\'\u0001\u0000\u0000"+
		"\u0000\')\u0001\u0000\u0000\u0000(&\u0001\u0000\u0000\u0000)-\u0003\u0004"+
		"\u0002\u0000*,\u0005\u0014\u0000\u0000+*\u0001\u0000\u0000\u0000,/\u0001"+
		"\u0000\u0000\u0000-+\u0001\u0000\u0000\u0000-.\u0001\u0000\u0000\u0000"+
		".0\u0001\u0000\u0000\u0000/-\u0001\u0000\u0000\u000001\u0003\u0006\u0003"+
		"\u00001\u0003\u0001\u0000\u0000\u000025\u0003\b\u0004\u000035\u0003\u000e"+
		"\u0007\u000042\u0001\u0000\u0000\u000043\u0001\u0000\u0000\u00005\u0005"+
		"\u0001\u0000\u0000\u000067\u0007\u0000\u0000\u00007\u0007\u0001\u0000"+
		"\u0000\u00008B\u0003\f\u0006\u00009;\u0005\u0014\u0000\u0000:9\u0001\u0000"+
		"\u0000\u0000;>\u0001\u0000\u0000\u0000<:\u0001\u0000\u0000\u0000<=\u0001"+
		"\u0000\u0000\u0000=?\u0001\u0000\u0000\u0000><\u0001\u0000\u0000\u0000"+
		"?A\u0003\n\u0005\u0000@<\u0001\u0000\u0000\u0000AD\u0001\u0000\u0000\u0000"+
		"B@\u0001\u0000\u0000\u0000BC\u0001\u0000\u0000\u0000C\t\u0001\u0000\u0000"+
		"\u0000DB\u0001\u0000\u0000\u0000EF\u0003\u0010\b\u0000F\u000b\u0001\u0000"+
		"\u0000\u0000GH\u0003\u0010\b\u0000H\r\u0001\u0000\u0000\u0000IM\u0003"+
		"\u0010\b\u0000JL\u0005\u0014\u0000\u0000KJ\u0001\u0000\u0000\u0000LO\u0001"+
		"\u0000\u0000\u0000MK\u0001\u0000\u0000\u0000MN\u0001\u0000\u0000\u0000"+
		"NP\u0001\u0000\u0000\u0000OM\u0001\u0000\u0000\u0000PT\u00051\u0000\u0000"+
		"QS\u0005\u0014\u0000\u0000RQ\u0001\u0000\u0000\u0000SV\u0001\u0000\u0000"+
		"\u0000TR\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000\u0000UW\u0001\u0000"+
		"\u0000\u0000VT\u0001\u0000\u0000\u0000WX\u0003\u0010\b\u0000X\u000f\u0001"+
		"\u0000\u0000\u0000YZ\u0006\b\uffff\uffff\u0000Zf\u0003\u0012\t\u0000["+
		"\\\u00055\u0000\u0000\\f\u0003\u0010\b\f]^\u0005-\u0000\u0000^f\u0003"+
		"\u0010\b\u000b_`\u0005\b\u0000\u0000`f\u0003\u0010\b\nab\u0005;\u0000"+
		"\u0000bc\u0003\u0010\b\u0000cd\u0005<\u0000\u0000df\u0001\u0000\u0000"+
		"\u0000eY\u0001\u0000\u0000\u0000e[\u0001\u0000\u0000\u0000e]\u0001\u0000"+
		"\u0000\u0000e_\u0001\u0000\u0000\u0000ea\u0001\u0000\u0000\u0000f\u0084"+
		"\u0001\u0000\u0000\u0000gh\n\t\u0000\u0000hi\u0007\u0001\u0000\u0000i"+
		"\u0083\u0003\u0010\b\njk\n\b\u0000\u0000kl\u0007\u0002\u0000\u0000l\u0083"+
		"\u0003\u0010\b\tmn\n\u0007\u0000\u0000no\u0007\u0003\u0000\u0000o\u0083"+
		"\u0003\u0010\b\bpq\n\u0006\u0000\u0000qr\u0007\u0004\u0000\u0000r\u0083"+
		"\u0003\u0010\b\u0007st\n\u0005\u0000\u0000tu\u0007\u0005\u0000\u0000u"+
		"\u0083\u0003\u0010\b\u0006vw\n\u0004\u0000\u0000wx\u00052\u0000\u0000"+
		"x\u0083\u0003\u0010\b\u0005yz\n\u0003\u0000\u0000z{\u0007\u0006\u0000"+
		"\u0000{\u0083\u0003\u0010\b\u0004|}\n\u0002\u0000\u0000}~\u00059\u0000"+
		"\u0000~\u007f\u0003\u0010\b\u0000\u007f\u0080\u0005\t\u0000\u0000\u0080"+
		"\u0081\u0003\u0010\b\u0003\u0081\u0083\u0001\u0000\u0000\u0000\u0082g"+
		"\u0001\u0000\u0000\u0000\u0082j\u0001\u0000\u0000\u0000\u0082m\u0001\u0000"+
		"\u0000\u0000\u0082p\u0001\u0000\u0000\u0000\u0082s\u0001\u0000\u0000\u0000"+
		"\u0082v\u0001\u0000\u0000\u0000\u0082y\u0001\u0000\u0000\u0000\u0082|"+
		"\u0001\u0000\u0000\u0000\u0083\u0086\u0001\u0000\u0000\u0000\u0084\u0082"+
		"\u0001\u0000\u0000\u0000\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u0011"+
		"\u0001\u0000\u0000\u0000\u0086\u0084\u0001\u0000\u0000\u0000\u0087\u008b"+
		"\u0003\u0016\u000b\u0000\u0088\u008b\u0003\u0014\n\u0000\u0089\u008b\u0005"+
		"\f\u0000\u0000\u008a\u0087\u0001\u0000\u0000\u0000\u008a\u0088\u0001\u0000"+
		"\u0000\u0000\u008a\u0089\u0001\u0000\u0000\u0000\u008b\u0013\u0001\u0000"+
		"\u0000\u0000\u008c\u008d\u0005R\u0000\u0000\u008d\u008e\u0005=\u0000\u0000"+
		"\u008e\u008f\u0003\u0010\b\u0000\u008f\u0096\u0005>\u0000\u0000\u0090"+
		"\u0091\u0005=\u0000\u0000\u0091\u0092\u0003\u0010\b\u0000\u0092\u0093"+
		"\u0005>\u0000\u0000\u0093\u0095\u0001\u0000\u0000\u0000\u0094\u0090\u0001"+
		"\u0000\u0000\u0000\u0095\u0098\u0001\u0000\u0000\u0000\u0096\u0094\u0001"+
		"\u0000\u0000\u0000\u0096\u0097\u0001\u0000\u0000\u0000\u0097\u009f\u0001"+
		"\u0000\u0000\u0000\u0098\u0096\u0001\u0000\u0000\u0000\u0099\u009a\u0005"+
		"R\u0000\u0000\u009a\u009b\u0005=\u0000\u0000\u009b\u009c\u0003\u0010\b"+
		"\u0000\u009c\u009d\u0005>\u0000\u0000\u009d\u009f\u0001\u0000\u0000\u0000"+
		"\u009e\u008c\u0001\u0000\u0000\u0000\u009e\u0099\u0001\u0000\u0000\u0000"+
		"\u009f\u0015\u0001\u0000\u0000\u0000\u00a0\u00a5\u0005\u000b\u0000\u0000"+
		"\u00a1\u00a5\u0003\u0018\f\u0000\u00a2\u00a5\u0003\u001a\r\u0000\u00a3"+
		"\u00a5\u0003\u001c\u000e\u0000\u00a4\u00a0\u0001\u0000\u0000\u0000\u00a4"+
		"\u00a1\u0001\u0000\u0000\u0000\u00a4\u00a2\u0001\u0000\u0000\u0000\u00a4"+
		"\u00a3\u0001\u0000\u0000\u0000\u00a5\u0017\u0001\u0000\u0000\u0000\u00a6"+
		"\u00a7\u0007\u0007\u0000\u0000\u00a7\u0019\u0001\u0000\u0000\u0000\u00a8"+
		"\u00a9\u0007\b\u0000\u0000\u00a9\u001b\u0001\u0000\u0000\u0000\u00aa\u00ac"+
		"\u0007\t\u0000\u0000\u00ab\u00aa\u0001\u0000\u0000\u0000\u00ac\u00ad\u0001"+
		"\u0000\u0000\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001"+
		"\u0000\u0000\u0000\u00ae\u001d\u0001\u0000\u0000\u0000\u0010!&-4<BMTe"+
		"\u0082\u0084\u008a\u0096\u009e\u00a4\u00ad";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}