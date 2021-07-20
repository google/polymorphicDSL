// Generated from PdslTestResourceParser.g4 by ANTLR 4.9
package com.pdsl.grammars;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PdslTestResourceParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		END=1, GIVEN_THE_TEST_RESOURCE=2, GIVEN_THE_FOLLOWING_TEST_RESOURCE=3, 
		DOCSTRING=4, DATA_ROW=5, GHERKIN_STEP_KEYWORD=6, INT=7, QUOTED_TEXT_END=8, 
		QUOTED_TEXT=9, TEST_RESOURCE_VALIDITY=10;
	public static final int
		RULE_polymorphicDslAllRules = 0, RULE_givenTheTestResource = 1, RULE_givenTheRawResource = 2, 
		RULE_gherkinStepKeyword = 3, RULE_integerValue = 4, RULE_textInDoubleQuotes = 5, 
		RULE_docstring = 6, RULE_textInDoubleQuotesEnd = 7, RULE_testResourceValidity = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"polymorphicDslAllRules", "givenTheTestResource", "givenTheRawResource", 
			"gherkinStepKeyword", "integerValue", "textInDoubleQuotes", "docstring", 
			"textInDoubleQuotesEnd", "testResourceValidity"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "END", "GIVEN_THE_TEST_RESOURCE", "GIVEN_THE_FOLLOWING_TEST_RESOURCE", 
			"DOCSTRING", "DATA_ROW", "GHERKIN_STEP_KEYWORD", "INT", "QUOTED_TEXT_END", 
			"QUOTED_TEXT", "TEST_RESOURCE_VALIDITY"
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
	public String getGrammarFileName() { return "PdslTestResourceParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PdslTestResourceParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class PolymorphicDslAllRulesContext extends ParserRuleContext {
		public List<GivenTheTestResourceContext> givenTheTestResource() {
			return getRuleContexts(GivenTheTestResourceContext.class);
		}
		public GivenTheTestResourceContext givenTheTestResource(int i) {
			return getRuleContext(GivenTheTestResourceContext.class,i);
		}
		public List<GivenTheRawResourceContext> givenTheRawResource() {
			return getRuleContexts(GivenTheRawResourceContext.class);
		}
		public GivenTheRawResourceContext givenTheRawResource(int i) {
			return getRuleContext(GivenTheRawResourceContext.class,i);
		}
		public List<TestResourceValidityContext> testResourceValidity() {
			return getRuleContexts(TestResourceValidityContext.class);
		}
		public TestResourceValidityContext testResourceValidity(int i) {
			return getRuleContext(TestResourceValidityContext.class,i);
		}
		public PolymorphicDslAllRulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_polymorphicDslAllRules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslTestResourceParserListener ) ((PdslTestResourceParserListener)listener).enterPolymorphicDslAllRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslTestResourceParserListener ) ((PdslTestResourceParserListener)listener).exitPolymorphicDslAllRules(this);
		}
	}

	public final PolymorphicDslAllRulesContext polymorphicDslAllRules() throws RecognitionException {
		PolymorphicDslAllRulesContext _localctx = new PolymorphicDslAllRulesContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_polymorphicDslAllRules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(21);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case GIVEN_THE_TEST_RESOURCE:
					{
					setState(18);
					givenTheTestResource();
					}
					break;
				case GIVEN_THE_FOLLOWING_TEST_RESOURCE:
					{
					setState(19);
					givenTheRawResource();
					}
					break;
				case TEST_RESOURCE_VALIDITY:
					{
					setState(20);
					testResourceValidity();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(23); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GIVEN_THE_TEST_RESOURCE) | (1L << GIVEN_THE_FOLLOWING_TEST_RESOURCE) | (1L << TEST_RESOURCE_VALIDITY))) != 0) );
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

	public static class GivenTheTestResourceContext extends ParserRuleContext {
		public TerminalNode GIVEN_THE_TEST_RESOURCE() { return getToken(PdslTestResourceParser.GIVEN_THE_TEST_RESOURCE, 0); }
		public TextInDoubleQuotesContext textInDoubleQuotes() {
			return getRuleContext(TextInDoubleQuotesContext.class,0);
		}
		public TerminalNode END() { return getToken(PdslTestResourceParser.END, 0); }
		public GivenTheTestResourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenTheTestResource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslTestResourceParserListener ) ((PdslTestResourceParserListener)listener).enterGivenTheTestResource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslTestResourceParserListener ) ((PdslTestResourceParserListener)listener).exitGivenTheTestResource(this);
		}
	}

	public final GivenTheTestResourceContext givenTheTestResource() throws RecognitionException {
		GivenTheTestResourceContext _localctx = new GivenTheTestResourceContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_givenTheTestResource);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			match(GIVEN_THE_TEST_RESOURCE);
			setState(26);
			textInDoubleQuotes();
			setState(27);
			match(END);
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

	public static class GivenTheRawResourceContext extends ParserRuleContext {
		public TerminalNode GIVEN_THE_FOLLOWING_TEST_RESOURCE() { return getToken(PdslTestResourceParser.GIVEN_THE_FOLLOWING_TEST_RESOURCE, 0); }
		public DocstringContext docstring() {
			return getRuleContext(DocstringContext.class,0);
		}
		public GivenTheRawResourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenTheRawResource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslTestResourceParserListener ) ((PdslTestResourceParserListener)listener).enterGivenTheRawResource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslTestResourceParserListener ) ((PdslTestResourceParserListener)listener).exitGivenTheRawResource(this);
		}
	}

	public final GivenTheRawResourceContext givenTheRawResource() throws RecognitionException {
		GivenTheRawResourceContext _localctx = new GivenTheRawResourceContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_givenTheRawResource);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			match(GIVEN_THE_FOLLOWING_TEST_RESOURCE);
			setState(30);
			docstring();
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

	public static class GherkinStepKeywordContext extends ParserRuleContext {
		public TerminalNode GHERKIN_STEP_KEYWORD() { return getToken(PdslTestResourceParser.GHERKIN_STEP_KEYWORD, 0); }
		public GherkinStepKeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gherkinStepKeyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslTestResourceParserListener ) ((PdslTestResourceParserListener)listener).enterGherkinStepKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslTestResourceParserListener ) ((PdslTestResourceParserListener)listener).exitGherkinStepKeyword(this);
		}
	}

	public final GherkinStepKeywordContext gherkinStepKeyword() throws RecognitionException {
		GherkinStepKeywordContext _localctx = new GherkinStepKeywordContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_gherkinStepKeyword);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(GHERKIN_STEP_KEYWORD);
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

	public static class IntegerValueContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(PdslTestResourceParser.INT, 0); }
		public IntegerValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslTestResourceParserListener ) ((PdslTestResourceParserListener)listener).enterIntegerValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslTestResourceParserListener ) ((PdslTestResourceParserListener)listener).exitIntegerValue(this);
		}
	}

	public final IntegerValueContext integerValue() throws RecognitionException {
		IntegerValueContext _localctx = new IntegerValueContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_integerValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(INT);
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

	public static class TextInDoubleQuotesContext extends ParserRuleContext {
		public TerminalNode QUOTED_TEXT() { return getToken(PdslTestResourceParser.QUOTED_TEXT, 0); }
		public TextInDoubleQuotesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textInDoubleQuotes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslTestResourceParserListener ) ((PdslTestResourceParserListener)listener).enterTextInDoubleQuotes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslTestResourceParserListener ) ((PdslTestResourceParserListener)listener).exitTextInDoubleQuotes(this);
		}
	}

	public final TextInDoubleQuotesContext textInDoubleQuotes() throws RecognitionException {
		TextInDoubleQuotesContext _localctx = new TextInDoubleQuotesContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_textInDoubleQuotes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(QUOTED_TEXT);
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

	public static class DocstringContext extends ParserRuleContext {
		public TerminalNode DOCSTRING() { return getToken(PdslTestResourceParser.DOCSTRING, 0); }
		public DocstringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_docstring; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslTestResourceParserListener ) ((PdslTestResourceParserListener)listener).enterDocstring(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslTestResourceParserListener ) ((PdslTestResourceParserListener)listener).exitDocstring(this);
		}
	}

	public final DocstringContext docstring() throws RecognitionException {
		DocstringContext _localctx = new DocstringContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_docstring);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			match(DOCSTRING);
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

	public static class TextInDoubleQuotesEndContext extends ParserRuleContext {
		public TerminalNode QUOTED_TEXT_END() { return getToken(PdslTestResourceParser.QUOTED_TEXT_END, 0); }
		public TextInDoubleQuotesEndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textInDoubleQuotesEnd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslTestResourceParserListener ) ((PdslTestResourceParserListener)listener).enterTextInDoubleQuotesEnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslTestResourceParserListener ) ((PdslTestResourceParserListener)listener).exitTextInDoubleQuotesEnd(this);
		}
	}

	public final TextInDoubleQuotesEndContext textInDoubleQuotesEnd() throws RecognitionException {
		TextInDoubleQuotesEndContext _localctx = new TextInDoubleQuotesEndContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_textInDoubleQuotesEnd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(QUOTED_TEXT_END);
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

	public static class TestResourceValidityContext extends ParserRuleContext {
		public TerminalNode TEST_RESOURCE_VALIDITY() { return getToken(PdslTestResourceParser.TEST_RESOURCE_VALIDITY, 0); }
		public TestResourceValidityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testResourceValidity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslTestResourceParserListener ) ((PdslTestResourceParserListener)listener).enterTestResourceValidity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslTestResourceParserListener ) ((PdslTestResourceParserListener)listener).exitTestResourceValidity(this);
		}
	}

	public final TestResourceValidityContext testResourceValidity() throws RecognitionException {
		TestResourceValidityContext _localctx = new TestResourceValidityContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_testResourceValidity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			match(TEST_RESOURCE_VALIDITY);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\f/\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\3\2"+
		"\6\2\30\n\2\r\2\16\2\31\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3"+
		"\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\2\2\13\2\4\6\b\n\f\16\20\22\2\2\2("+
		"\2\27\3\2\2\2\4\33\3\2\2\2\6\37\3\2\2\2\b\"\3\2\2\2\n$\3\2\2\2\f&\3\2"+
		"\2\2\16(\3\2\2\2\20*\3\2\2\2\22,\3\2\2\2\24\30\5\4\3\2\25\30\5\6\4\2\26"+
		"\30\5\22\n\2\27\24\3\2\2\2\27\25\3\2\2\2\27\26\3\2\2\2\30\31\3\2\2\2\31"+
		"\27\3\2\2\2\31\32\3\2\2\2\32\3\3\2\2\2\33\34\7\4\2\2\34\35\5\f\7\2\35"+
		"\36\7\3\2\2\36\5\3\2\2\2\37 \7\5\2\2 !\5\16\b\2!\7\3\2\2\2\"#\7\b\2\2"+
		"#\t\3\2\2\2$%\7\t\2\2%\13\3\2\2\2&\'\7\13\2\2\'\r\3\2\2\2()\7\6\2\2)\17"+
		"\3\2\2\2*+\7\n\2\2+\21\3\2\2\2,-\7\f\2\2-\23\3\2\2\2\4\27\31";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}