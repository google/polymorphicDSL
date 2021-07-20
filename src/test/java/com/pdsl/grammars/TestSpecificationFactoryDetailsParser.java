// Generated from TestSpecificationFactoryDetailsParser.g4 by ANTLR 4.9
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
public class TestSpecificationFactoryDetailsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		GIVEN_SPECIFIC_TEST_SPECIFICATION_FACTORY=1, DOCSTRING=2, DATA_ROW=3, 
		GHERKIN_STEP_KEYWORD=4, INT=5, QUOTED_TEXT_END=6, QUOTED_TEXT=7;
	public static final int
		RULE_givenSpecificTestSpecificationFactory = 0, RULE_gherkinStepKeyword = 1, 
		RULE_integerValue = 2, RULE_textInDoubleQuotes = 3, RULE_docstring = 4, 
		RULE_textInDoubleQuotesEnd = 5;
	private static String[] makeRuleNames() {
		return new String[] {
			"givenSpecificTestSpecificationFactory", "gherkinStepKeyword", "integerValue", 
			"textInDoubleQuotes", "docstring", "textInDoubleQuotesEnd"
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
			null, "GIVEN_SPECIFIC_TEST_SPECIFICATION_FACTORY", "DOCSTRING", "DATA_ROW", 
			"GHERKIN_STEP_KEYWORD", "INT", "QUOTED_TEXT_END", "QUOTED_TEXT"
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
	public String getGrammarFileName() { return "TestSpecificationFactoryDetailsParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TestSpecificationFactoryDetailsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class GivenSpecificTestSpecificationFactoryContext extends ParserRuleContext {
		public TerminalNode GIVEN_SPECIFIC_TEST_SPECIFICATION_FACTORY() { return getToken(TestSpecificationFactoryDetailsParser.GIVEN_SPECIFIC_TEST_SPECIFICATION_FACTORY, 0); }
		public TextInDoubleQuotesEndContext textInDoubleQuotesEnd() {
			return getRuleContext(TextInDoubleQuotesEndContext.class,0);
		}
		public GivenSpecificTestSpecificationFactoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenSpecificTestSpecificationFactory; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryDetailsParserListener ) ((TestSpecificationFactoryDetailsParserListener)listener).enterGivenSpecificTestSpecificationFactory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryDetailsParserListener ) ((TestSpecificationFactoryDetailsParserListener)listener).exitGivenSpecificTestSpecificationFactory(this);
		}
	}

	public final GivenSpecificTestSpecificationFactoryContext givenSpecificTestSpecificationFactory() throws RecognitionException {
		GivenSpecificTestSpecificationFactoryContext _localctx = new GivenSpecificTestSpecificationFactoryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_givenSpecificTestSpecificationFactory);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(12);
			match(GIVEN_SPECIFIC_TEST_SPECIFICATION_FACTORY);
			setState(13);
			textInDoubleQuotesEnd();
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
		public TerminalNode GHERKIN_STEP_KEYWORD() { return getToken(TestSpecificationFactoryDetailsParser.GHERKIN_STEP_KEYWORD, 0); }
		public GherkinStepKeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gherkinStepKeyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryDetailsParserListener ) ((TestSpecificationFactoryDetailsParserListener)listener).enterGherkinStepKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryDetailsParserListener ) ((TestSpecificationFactoryDetailsParserListener)listener).exitGherkinStepKeyword(this);
		}
	}

	public final GherkinStepKeywordContext gherkinStepKeyword() throws RecognitionException {
		GherkinStepKeywordContext _localctx = new GherkinStepKeywordContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_gherkinStepKeyword);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(15);
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
		public TerminalNode INT() { return getToken(TestSpecificationFactoryDetailsParser.INT, 0); }
		public IntegerValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryDetailsParserListener ) ((TestSpecificationFactoryDetailsParserListener)listener).enterIntegerValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryDetailsParserListener ) ((TestSpecificationFactoryDetailsParserListener)listener).exitIntegerValue(this);
		}
	}

	public final IntegerValueContext integerValue() throws RecognitionException {
		IntegerValueContext _localctx = new IntegerValueContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_integerValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17);
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
		public TerminalNode QUOTED_TEXT() { return getToken(TestSpecificationFactoryDetailsParser.QUOTED_TEXT, 0); }
		public TextInDoubleQuotesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textInDoubleQuotes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryDetailsParserListener ) ((TestSpecificationFactoryDetailsParserListener)listener).enterTextInDoubleQuotes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryDetailsParserListener ) ((TestSpecificationFactoryDetailsParserListener)listener).exitTextInDoubleQuotes(this);
		}
	}

	public final TextInDoubleQuotesContext textInDoubleQuotes() throws RecognitionException {
		TextInDoubleQuotesContext _localctx = new TextInDoubleQuotesContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_textInDoubleQuotes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19);
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
		public TerminalNode DOCSTRING() { return getToken(TestSpecificationFactoryDetailsParser.DOCSTRING, 0); }
		public DocstringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_docstring; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryDetailsParserListener ) ((TestSpecificationFactoryDetailsParserListener)listener).enterDocstring(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryDetailsParserListener ) ((TestSpecificationFactoryDetailsParserListener)listener).exitDocstring(this);
		}
	}

	public final DocstringContext docstring() throws RecognitionException {
		DocstringContext _localctx = new DocstringContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_docstring);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
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
		public TerminalNode QUOTED_TEXT_END() { return getToken(TestSpecificationFactoryDetailsParser.QUOTED_TEXT_END, 0); }
		public TextInDoubleQuotesEndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textInDoubleQuotesEnd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryDetailsParserListener ) ((TestSpecificationFactoryDetailsParserListener)listener).enterTextInDoubleQuotesEnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryDetailsParserListener ) ((TestSpecificationFactoryDetailsParserListener)listener).exitTextInDoubleQuotesEnd(this);
		}
	}

	public final TextInDoubleQuotesEndContext textInDoubleQuotesEnd() throws RecognitionException {
		TextInDoubleQuotesEndContext _localctx = new TextInDoubleQuotesEndContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_textInDoubleQuotesEnd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(23);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\t\34\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3"+
		"\5\3\6\3\6\3\7\3\7\3\7\2\2\b\2\4\6\b\n\f\2\2\2\25\2\16\3\2\2\2\4\21\3"+
		"\2\2\2\6\23\3\2\2\2\b\25\3\2\2\2\n\27\3\2\2\2\f\31\3\2\2\2\16\17\7\3\2"+
		"\2\17\20\5\f\7\2\20\3\3\2\2\2\21\22\7\6\2\2\22\5\3\2\2\2\23\24\7\7\2\2"+
		"\24\7\3\2\2\2\25\26\7\t\2\2\26\t\3\2\2\2\27\30\7\4\2\2\30\13\3\2\2\2\31"+
		"\32\7\b\2\2\32\r\3\2\2\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}