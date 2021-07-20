// Generated from TestExecutorParser.g4 by ANTLR 4.9
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
public class TestExecutorParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		GIVEN_SPECIFIC_TEST_SPECIFICATION_FACTORY=1, END=2, GIVEN_THE_TEST_EXECUTOR_IS=3, 
		GIVEN_THE_GRAMMAR_PARSE_TREE_LISTENER=4, GIVEN_THE_SUBGRAMMAR_PARSE_TREE_LISTENER=5, 
		WHEN_THE_GHERKIN_TEST_EXECUTOR_RUNS_WITH_TAG_EXPRESSION=6, GIVEN_GRAMMAR_PHRASE_FILTER_IS=7, 
		GIVEN_SUBGRAMMAR_PHRASE_FILTER_IS=8, DOCSTRING=9, DATA_ROW=10, GHERKIN_STEP_KEYWORD=11, 
		INT=12, QUOTED_TEXT_END=13, QUOTED_TEXT=14, WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY=15, 
		TEST_SPECIFICATION_IS_PRODUCED=16, TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY=17, 
		POLYMORPHIC_DSL_TEST_EXECUTOR=18, TEST_CASE_IS_PROCESSED=19, TEST_RUN_RESULT_PRODUCED=20, 
		GIVEN_THE_TEST_RESOURCE=21, GIVEN_THE_FOLLOWING_TEST_RESOURCE=22, THEN_THE_TEST_RUN_RESULTS_HAVE_N_TESTS_START=23, 
		FAILING_TESTS_END=24, PASSING_TESTS_END=25, INTEGER_VALUE=26;
	public static final int
		RULE_givenTheTestExecutorIsSpecified = 0, RULE_givenTheGrammarListener = 1, 
		RULE_givenTheSubgrammarListener = 2, RULE_whenTheGherkinTestExecutorRunsWithTagExpression = 3, 
		RULE_givenTheGrammarPhraseFilter = 4, RULE_givenTheSubrammarPhraseFilter = 5, 
		RULE_givenTheTestResource = 6, RULE_givenTheRawResource = 7, RULE_gherkinStepKeyword = 8, 
		RULE_integerValue = 9, RULE_textInDoubleQuotes = 10, RULE_docstring = 11, 
		RULE_textInDoubleQuotesEnd = 12, RULE_whenTheTestResourceIsProcessedByFactory = 13, 
		RULE_testSpecificationIsProduced = 14, RULE_testSpecificationIsProcessedByTestCaseFactory = 15, 
		RULE_polymorphicDslTestExecutor = 16, RULE_testRunResultProduced = 17, 
		RULE_thenTheTestRunResultsHaveSpecifiedPassingTests = 18, RULE_thenTheTestRunResultsHaveSpecifiedFailingTests = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"givenTheTestExecutorIsSpecified", "givenTheGrammarListener", "givenTheSubgrammarListener", 
			"whenTheGherkinTestExecutorRunsWithTagExpression", "givenTheGrammarPhraseFilter", 
			"givenTheSubrammarPhraseFilter", "givenTheTestResource", "givenTheRawResource", 
			"gherkinStepKeyword", "integerValue", "textInDoubleQuotes", "docstring", 
			"textInDoubleQuotesEnd", "whenTheTestResourceIsProcessedByFactory", "testSpecificationIsProduced", 
			"testSpecificationIsProcessedByTestCaseFactory", "polymorphicDslTestExecutor", 
			"testRunResultProduced", "thenTheTestRunResultsHaveSpecifiedPassingTests", 
			"thenTheTestRunResultsHaveSpecifiedFailingTests"
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
			null, "GIVEN_SPECIFIC_TEST_SPECIFICATION_FACTORY", "END", "GIVEN_THE_TEST_EXECUTOR_IS", 
			"GIVEN_THE_GRAMMAR_PARSE_TREE_LISTENER", "GIVEN_THE_SUBGRAMMAR_PARSE_TREE_LISTENER", 
			"WHEN_THE_GHERKIN_TEST_EXECUTOR_RUNS_WITH_TAG_EXPRESSION", "GIVEN_GRAMMAR_PHRASE_FILTER_IS", 
			"GIVEN_SUBGRAMMAR_PHRASE_FILTER_IS", "DOCSTRING", "DATA_ROW", "GHERKIN_STEP_KEYWORD", 
			"INT", "QUOTED_TEXT_END", "QUOTED_TEXT", "WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY", 
			"TEST_SPECIFICATION_IS_PRODUCED", "TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY", 
			"POLYMORPHIC_DSL_TEST_EXECUTOR", "TEST_CASE_IS_PROCESSED", "TEST_RUN_RESULT_PRODUCED", 
			"GIVEN_THE_TEST_RESOURCE", "GIVEN_THE_FOLLOWING_TEST_RESOURCE", "THEN_THE_TEST_RUN_RESULTS_HAVE_N_TESTS_START", 
			"FAILING_TESTS_END", "PASSING_TESTS_END", "INTEGER_VALUE"
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
	public String getGrammarFileName() { return "TestExecutorParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TestExecutorParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class GivenTheTestExecutorIsSpecifiedContext extends ParserRuleContext {
		public TerminalNode GIVEN_THE_TEST_EXECUTOR_IS() { return getToken(TestExecutorParser.GIVEN_THE_TEST_EXECUTOR_IS, 0); }
		public TextInDoubleQuotesEndContext textInDoubleQuotesEnd() {
			return getRuleContext(TextInDoubleQuotesEndContext.class,0);
		}
		public GivenTheTestExecutorIsSpecifiedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenTheTestExecutorIsSpecified; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).enterGivenTheTestExecutorIsSpecified(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).exitGivenTheTestExecutorIsSpecified(this);
		}
	}

	public final GivenTheTestExecutorIsSpecifiedContext givenTheTestExecutorIsSpecified() throws RecognitionException {
		GivenTheTestExecutorIsSpecifiedContext _localctx = new GivenTheTestExecutorIsSpecifiedContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_givenTheTestExecutorIsSpecified);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(GIVEN_THE_TEST_EXECUTOR_IS);
			setState(41);
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

	public static class GivenTheGrammarListenerContext extends ParserRuleContext {
		public TerminalNode GIVEN_THE_GRAMMAR_PARSE_TREE_LISTENER() { return getToken(TestExecutorParser.GIVEN_THE_GRAMMAR_PARSE_TREE_LISTENER, 0); }
		public TextInDoubleQuotesEndContext textInDoubleQuotesEnd() {
			return getRuleContext(TextInDoubleQuotesEndContext.class,0);
		}
		public GivenTheGrammarListenerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenTheGrammarListener; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).enterGivenTheGrammarListener(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).exitGivenTheGrammarListener(this);
		}
	}

	public final GivenTheGrammarListenerContext givenTheGrammarListener() throws RecognitionException {
		GivenTheGrammarListenerContext _localctx = new GivenTheGrammarListenerContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_givenTheGrammarListener);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(GIVEN_THE_GRAMMAR_PARSE_TREE_LISTENER);
			setState(44);
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

	public static class GivenTheSubgrammarListenerContext extends ParserRuleContext {
		public TerminalNode GIVEN_THE_SUBGRAMMAR_PARSE_TREE_LISTENER() { return getToken(TestExecutorParser.GIVEN_THE_SUBGRAMMAR_PARSE_TREE_LISTENER, 0); }
		public TextInDoubleQuotesEndContext textInDoubleQuotesEnd() {
			return getRuleContext(TextInDoubleQuotesEndContext.class,0);
		}
		public GivenTheSubgrammarListenerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenTheSubgrammarListener; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).enterGivenTheSubgrammarListener(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).exitGivenTheSubgrammarListener(this);
		}
	}

	public final GivenTheSubgrammarListenerContext givenTheSubgrammarListener() throws RecognitionException {
		GivenTheSubgrammarListenerContext _localctx = new GivenTheSubgrammarListenerContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_givenTheSubgrammarListener);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(GIVEN_THE_SUBGRAMMAR_PARSE_TREE_LISTENER);
			setState(47);
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

	public static class WhenTheGherkinTestExecutorRunsWithTagExpressionContext extends ParserRuleContext {
		public TerminalNode WHEN_THE_GHERKIN_TEST_EXECUTOR_RUNS_WITH_TAG_EXPRESSION() { return getToken(TestExecutorParser.WHEN_THE_GHERKIN_TEST_EXECUTOR_RUNS_WITH_TAG_EXPRESSION, 0); }
		public TextInDoubleQuotesEndContext textInDoubleQuotesEnd() {
			return getRuleContext(TextInDoubleQuotesEndContext.class,0);
		}
		public WhenTheGherkinTestExecutorRunsWithTagExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whenTheGherkinTestExecutorRunsWithTagExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).enterWhenTheGherkinTestExecutorRunsWithTagExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).exitWhenTheGherkinTestExecutorRunsWithTagExpression(this);
		}
	}

	public final WhenTheGherkinTestExecutorRunsWithTagExpressionContext whenTheGherkinTestExecutorRunsWithTagExpression() throws RecognitionException {
		WhenTheGherkinTestExecutorRunsWithTagExpressionContext _localctx = new WhenTheGherkinTestExecutorRunsWithTagExpressionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_whenTheGherkinTestExecutorRunsWithTagExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			match(WHEN_THE_GHERKIN_TEST_EXECUTOR_RUNS_WITH_TAG_EXPRESSION);
			setState(50);
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

	public static class GivenTheGrammarPhraseFilterContext extends ParserRuleContext {
		public TerminalNode GIVEN_GRAMMAR_PHRASE_FILTER_IS() { return getToken(TestExecutorParser.GIVEN_GRAMMAR_PHRASE_FILTER_IS, 0); }
		public TextInDoubleQuotesEndContext textInDoubleQuotesEnd() {
			return getRuleContext(TextInDoubleQuotesEndContext.class,0);
		}
		public GivenTheGrammarPhraseFilterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenTheGrammarPhraseFilter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).enterGivenTheGrammarPhraseFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).exitGivenTheGrammarPhraseFilter(this);
		}
	}

	public final GivenTheGrammarPhraseFilterContext givenTheGrammarPhraseFilter() throws RecognitionException {
		GivenTheGrammarPhraseFilterContext _localctx = new GivenTheGrammarPhraseFilterContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_givenTheGrammarPhraseFilter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(GIVEN_GRAMMAR_PHRASE_FILTER_IS);
			setState(53);
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

	public static class GivenTheSubrammarPhraseFilterContext extends ParserRuleContext {
		public TerminalNode GIVEN_SUBGRAMMAR_PHRASE_FILTER_IS() { return getToken(TestExecutorParser.GIVEN_SUBGRAMMAR_PHRASE_FILTER_IS, 0); }
		public TextInDoubleQuotesEndContext textInDoubleQuotesEnd() {
			return getRuleContext(TextInDoubleQuotesEndContext.class,0);
		}
		public GivenTheSubrammarPhraseFilterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenTheSubrammarPhraseFilter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).enterGivenTheSubrammarPhraseFilter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).exitGivenTheSubrammarPhraseFilter(this);
		}
	}

	public final GivenTheSubrammarPhraseFilterContext givenTheSubrammarPhraseFilter() throws RecognitionException {
		GivenTheSubrammarPhraseFilterContext _localctx = new GivenTheSubrammarPhraseFilterContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_givenTheSubrammarPhraseFilter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(GIVEN_SUBGRAMMAR_PHRASE_FILTER_IS);
			setState(56);
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

	public static class GivenTheTestResourceContext extends ParserRuleContext {
		public TerminalNode GIVEN_THE_TEST_RESOURCE() { return getToken(TestExecutorParser.GIVEN_THE_TEST_RESOURCE, 0); }
		public TextInDoubleQuotesContext textInDoubleQuotes() {
			return getRuleContext(TextInDoubleQuotesContext.class,0);
		}
		public TerminalNode END() { return getToken(TestExecutorParser.END, 0); }
		public GivenTheTestResourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenTheTestResource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).enterGivenTheTestResource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).exitGivenTheTestResource(this);
		}
	}

	public final GivenTheTestResourceContext givenTheTestResource() throws RecognitionException {
		GivenTheTestResourceContext _localctx = new GivenTheTestResourceContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_givenTheTestResource);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(GIVEN_THE_TEST_RESOURCE);
			setState(59);
			textInDoubleQuotes();
			setState(60);
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
		public TerminalNode GIVEN_THE_FOLLOWING_TEST_RESOURCE() { return getToken(TestExecutorParser.GIVEN_THE_FOLLOWING_TEST_RESOURCE, 0); }
		public DocstringContext docstring() {
			return getRuleContext(DocstringContext.class,0);
		}
		public GivenTheRawResourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenTheRawResource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).enterGivenTheRawResource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).exitGivenTheRawResource(this);
		}
	}

	public final GivenTheRawResourceContext givenTheRawResource() throws RecognitionException {
		GivenTheRawResourceContext _localctx = new GivenTheRawResourceContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_givenTheRawResource);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(GIVEN_THE_FOLLOWING_TEST_RESOURCE);
			setState(63);
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
		public TerminalNode GHERKIN_STEP_KEYWORD() { return getToken(TestExecutorParser.GHERKIN_STEP_KEYWORD, 0); }
		public GherkinStepKeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gherkinStepKeyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).enterGherkinStepKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).exitGherkinStepKeyword(this);
		}
	}

	public final GherkinStepKeywordContext gherkinStepKeyword() throws RecognitionException {
		GherkinStepKeywordContext _localctx = new GherkinStepKeywordContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_gherkinStepKeyword);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
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
		public TerminalNode INT() { return getToken(TestExecutorParser.INT, 0); }
		public IntegerValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).enterIntegerValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).exitIntegerValue(this);
		}
	}

	public final IntegerValueContext integerValue() throws RecognitionException {
		IntegerValueContext _localctx = new IntegerValueContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_integerValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
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
		public TerminalNode QUOTED_TEXT() { return getToken(TestExecutorParser.QUOTED_TEXT, 0); }
		public TextInDoubleQuotesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textInDoubleQuotes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).enterTextInDoubleQuotes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).exitTextInDoubleQuotes(this);
		}
	}

	public final TextInDoubleQuotesContext textInDoubleQuotes() throws RecognitionException {
		TextInDoubleQuotesContext _localctx = new TextInDoubleQuotesContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_textInDoubleQuotes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
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
		public TerminalNode DOCSTRING() { return getToken(TestExecutorParser.DOCSTRING, 0); }
		public DocstringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_docstring; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).enterDocstring(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).exitDocstring(this);
		}
	}

	public final DocstringContext docstring() throws RecognitionException {
		DocstringContext _localctx = new DocstringContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_docstring);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
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
		public TerminalNode QUOTED_TEXT_END() { return getToken(TestExecutorParser.QUOTED_TEXT_END, 0); }
		public TextInDoubleQuotesEndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textInDoubleQuotesEnd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).enterTextInDoubleQuotesEnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).exitTextInDoubleQuotesEnd(this);
		}
	}

	public final TextInDoubleQuotesEndContext textInDoubleQuotesEnd() throws RecognitionException {
		TextInDoubleQuotesEndContext _localctx = new TextInDoubleQuotesEndContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_textInDoubleQuotesEnd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
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

	public static class WhenTheTestResourceIsProcessedByFactoryContext extends ParserRuleContext {
		public TerminalNode WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY() { return getToken(TestExecutorParser.WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY, 0); }
		public WhenTheTestResourceIsProcessedByFactoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whenTheTestResourceIsProcessedByFactory; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).enterWhenTheTestResourceIsProcessedByFactory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).exitWhenTheTestResourceIsProcessedByFactory(this);
		}
	}

	public final WhenTheTestResourceIsProcessedByFactoryContext whenTheTestResourceIsProcessedByFactory() throws RecognitionException {
		WhenTheTestResourceIsProcessedByFactoryContext _localctx = new WhenTheTestResourceIsProcessedByFactoryContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_whenTheTestResourceIsProcessedByFactory);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY);
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

	public static class TestSpecificationIsProducedContext extends ParserRuleContext {
		public TerminalNode TEST_SPECIFICATION_IS_PRODUCED() { return getToken(TestExecutorParser.TEST_SPECIFICATION_IS_PRODUCED, 0); }
		public TestSpecificationIsProducedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testSpecificationIsProduced; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).enterTestSpecificationIsProduced(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).exitTestSpecificationIsProduced(this);
		}
	}

	public final TestSpecificationIsProducedContext testSpecificationIsProduced() throws RecognitionException {
		TestSpecificationIsProducedContext _localctx = new TestSpecificationIsProducedContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_testSpecificationIsProduced);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(TEST_SPECIFICATION_IS_PRODUCED);
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

	public static class TestSpecificationIsProcessedByTestCaseFactoryContext extends ParserRuleContext {
		public TerminalNode TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY() { return getToken(TestExecutorParser.TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY, 0); }
		public TestSpecificationIsProcessedByTestCaseFactoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testSpecificationIsProcessedByTestCaseFactory; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).enterTestSpecificationIsProcessedByTestCaseFactory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).exitTestSpecificationIsProcessedByTestCaseFactory(this);
		}
	}

	public final TestSpecificationIsProcessedByTestCaseFactoryContext testSpecificationIsProcessedByTestCaseFactory() throws RecognitionException {
		TestSpecificationIsProcessedByTestCaseFactoryContext _localctx = new TestSpecificationIsProcessedByTestCaseFactoryContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_testSpecificationIsProcessedByTestCaseFactory);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY);
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

	public static class PolymorphicDslTestExecutorContext extends ParserRuleContext {
		public TerminalNode POLYMORPHIC_DSL_TEST_EXECUTOR() { return getToken(TestExecutorParser.POLYMORPHIC_DSL_TEST_EXECUTOR, 0); }
		public PolymorphicDslTestExecutorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_polymorphicDslTestExecutor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).enterPolymorphicDslTestExecutor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).exitPolymorphicDslTestExecutor(this);
		}
	}

	public final PolymorphicDslTestExecutorContext polymorphicDslTestExecutor() throws RecognitionException {
		PolymorphicDslTestExecutorContext _localctx = new PolymorphicDslTestExecutorContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_polymorphicDslTestExecutor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(POLYMORPHIC_DSL_TEST_EXECUTOR);
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

	public static class TestRunResultProducedContext extends ParserRuleContext {
		public TerminalNode TEST_RUN_RESULT_PRODUCED() { return getToken(TestExecutorParser.TEST_RUN_RESULT_PRODUCED, 0); }
		public TestRunResultProducedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testRunResultProduced; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).enterTestRunResultProduced(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).exitTestRunResultProduced(this);
		}
	}

	public final TestRunResultProducedContext testRunResultProduced() throws RecognitionException {
		TestRunResultProducedContext _localctx = new TestRunResultProducedContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_testRunResultProduced);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(TEST_RUN_RESULT_PRODUCED);
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

	public static class ThenTheTestRunResultsHaveSpecifiedPassingTestsContext extends ParserRuleContext {
		public TerminalNode THEN_THE_TEST_RUN_RESULTS_HAVE_N_TESTS_START() { return getToken(TestExecutorParser.THEN_THE_TEST_RUN_RESULTS_HAVE_N_TESTS_START, 0); }
		public IntegerValueContext integerValue() {
			return getRuleContext(IntegerValueContext.class,0);
		}
		public TerminalNode PASSING_TESTS_END() { return getToken(TestExecutorParser.PASSING_TESTS_END, 0); }
		public ThenTheTestRunResultsHaveSpecifiedPassingTestsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thenTheTestRunResultsHaveSpecifiedPassingTests; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).enterThenTheTestRunResultsHaveSpecifiedPassingTests(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).exitThenTheTestRunResultsHaveSpecifiedPassingTests(this);
		}
	}

	public final ThenTheTestRunResultsHaveSpecifiedPassingTestsContext thenTheTestRunResultsHaveSpecifiedPassingTests() throws RecognitionException {
		ThenTheTestRunResultsHaveSpecifiedPassingTestsContext _localctx = new ThenTheTestRunResultsHaveSpecifiedPassingTestsContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_thenTheTestRunResultsHaveSpecifiedPassingTests);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(THEN_THE_TEST_RUN_RESULTS_HAVE_N_TESTS_START);
			setState(86);
			integerValue();
			setState(87);
			match(PASSING_TESTS_END);
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

	public static class ThenTheTestRunResultsHaveSpecifiedFailingTestsContext extends ParserRuleContext {
		public TerminalNode THEN_THE_TEST_RUN_RESULTS_HAVE_N_TESTS_START() { return getToken(TestExecutorParser.THEN_THE_TEST_RUN_RESULTS_HAVE_N_TESTS_START, 0); }
		public IntegerValueContext integerValue() {
			return getRuleContext(IntegerValueContext.class,0);
		}
		public TerminalNode PASSING_TESTS_END() { return getToken(TestExecutorParser.PASSING_TESTS_END, 0); }
		public ThenTheTestRunResultsHaveSpecifiedFailingTestsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thenTheTestRunResultsHaveSpecifiedFailingTests; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).enterThenTheTestRunResultsHaveSpecifiedFailingTests(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestExecutorParserListener ) ((TestExecutorParserListener)listener).exitThenTheTestRunResultsHaveSpecifiedFailingTests(this);
		}
	}

	public final ThenTheTestRunResultsHaveSpecifiedFailingTestsContext thenTheTestRunResultsHaveSpecifiedFailingTests() throws RecognitionException {
		ThenTheTestRunResultsHaveSpecifiedFailingTestsContext _localctx = new ThenTheTestRunResultsHaveSpecifiedFailingTestsContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_thenTheTestRunResultsHaveSpecifiedFailingTests);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(THEN_THE_TEST_RUN_RESULTS_HAVE_N_TESTS_START);
			setState(90);
			integerValue();
			setState(91);
			match(PASSING_TESTS_END);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\34`\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22\4\23"+
		"\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5"+
		"\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22"+
		"\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\2\2\26\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\34\36 \"$&(\2\2\2K\2*\3\2\2\2\4-\3\2\2\2\6\60"+
		"\3\2\2\2\b\63\3\2\2\2\n\66\3\2\2\2\f9\3\2\2\2\16<\3\2\2\2\20@\3\2\2\2"+
		"\22C\3\2\2\2\24E\3\2\2\2\26G\3\2\2\2\30I\3\2\2\2\32K\3\2\2\2\34M\3\2\2"+
		"\2\36O\3\2\2\2 Q\3\2\2\2\"S\3\2\2\2$U\3\2\2\2&W\3\2\2\2([\3\2\2\2*+\7"+
		"\5\2\2+,\5\32\16\2,\3\3\2\2\2-.\7\6\2\2./\5\32\16\2/\5\3\2\2\2\60\61\7"+
		"\7\2\2\61\62\5\32\16\2\62\7\3\2\2\2\63\64\7\b\2\2\64\65\5\32\16\2\65\t"+
		"\3\2\2\2\66\67\7\t\2\2\678\5\32\16\28\13\3\2\2\29:\7\n\2\2:;\5\32\16\2"+
		";\r\3\2\2\2<=\7\27\2\2=>\5\26\f\2>?\7\4\2\2?\17\3\2\2\2@A\7\30\2\2AB\5"+
		"\30\r\2B\21\3\2\2\2CD\7\r\2\2D\23\3\2\2\2EF\7\16\2\2F\25\3\2\2\2GH\7\20"+
		"\2\2H\27\3\2\2\2IJ\7\13\2\2J\31\3\2\2\2KL\7\17\2\2L\33\3\2\2\2MN\7\21"+
		"\2\2N\35\3\2\2\2OP\7\22\2\2P\37\3\2\2\2QR\7\23\2\2R!\3\2\2\2ST\7\24\2"+
		"\2T#\3\2\2\2UV\7\26\2\2V%\3\2\2\2WX\7\31\2\2XY\5\24\13\2YZ\7\33\2\2Z\'"+
		"\3\2\2\2[\\\7\31\2\2\\]\5\24\13\2]^\7\33\2\2^)\3\2\2\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}