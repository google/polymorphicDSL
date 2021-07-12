// Generated from TestSpecificationFactoryParser.g4 by ANTLR 4.9
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
public class TestSpecificationFactoryParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		END=1, GIVEN_SPECIFIC_TEST_SPECIFICATION_FACTORY=2, GIVEN_SPECIFIC_GRAMMAR=3, 
		GIVEN_SPECIFIC_SUBGRAMMAR=4, THEN_TEST_SPECIFICATION_FAILS_DUE_TO_MISSING_SCENARIO=5, 
		THEN_TEST_SPECIFICATION_FAILS_BECAUSE_OF_MISSING_STEP_BODY=6, GIVEN_NONEXISTENT_URL=7, 
		THEN_NO_SUCH_RESOURCE_ERROR=8, TEST_SPECIFICATION_TOTAL_PHRASES_START=9, 
		TEST_SPECIFICATION_TOTAL_PHRASES_END=10, TEST_SPECIFICATION_MAY_BE_PRODUCED=11, 
		TEST_SPECIFICATION_IS_PROCESSED_BY_THE_FACTORY=12, DOCSTRING=13, DATA_ROW=14, 
		GHERKIN_STEP_KEYWORD=15, INT=16, QUOTED_TEXT_END=17, QUOTED_TEXT=18, THEN_TEST_RESOURCE_VALIDITY=19, 
		TEST_SPECIFICATION_HAS_AN_ID=20, TEST_SPECIFICATION_IN_EXPECTED_FORMAT=21, 
		TEST_CASE_IS_PRODUCED=22, TEST_CASE_HAS_A_UNIQUE_ID=23, TEST_CASE_HAS_A_TITLE=24, 
		TEST_CASE_HAS_PROPER_TEST_BODY=25, PDSL_CAN_PROCESS_ALL_PHRASES=26, PASSING_TEST_TOTAL=27, 
		PASSING_PHRASE_TOTAL=28, FAILING_TEST_TOTAL=29, TOTAL_PHRASES=30, DUPLICATE_TEST_TOTAL=31, 
		GIVEN_THE_TEST_RESOURCE=32, GIVEN_THE_FOLLOWING_TEST_RESOURCE=33, WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY=34, 
		TEST_SPECIFICATION_IS_PRODUCED=35, TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY=36, 
		POLYMORPHIC_DSL_TEST_EXECUTOR=37, TEST_CASE_IS_PROCESSED=38, TEST_RUN_RESULT_PRODUCED=39;
	public static final int
		RULE_givenSpecificTestSpecificationFactory = 0, RULE_givenSpecificGrammar = 1, 
		RULE_givenSpecificSubgrammar = 2, RULE_thenTestSpecificationFailsDueToMissingScenario = 3, 
		RULE_thenTestSpecificationFailsBecauseOfMissingStepBody = 4, RULE_givenNonExistentUrl = 5, 
		RULE_thenNoSuchResourceError = 6, RULE_thenTestSpecificationHasTotalPhrases = 7, 
		RULE_testSpecificationMayBeProduced = 8, RULE_testResourceProcessedByFactory = 9, 
		RULE_polymorphicDslAllRules = 10, RULE_gherkinStepKeyword = 11, RULE_integerValue = 12, 
		RULE_textInDoubleQuotes = 13, RULE_docstring = 14, RULE_textInDoubleQuotesEnd = 15, 
		RULE_givenTheTestResource = 16, RULE_givenTheRawResource = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"givenSpecificTestSpecificationFactory", "givenSpecificGrammar", "givenSpecificSubgrammar", 
			"thenTestSpecificationFailsDueToMissingScenario", "thenTestSpecificationFailsBecauseOfMissingStepBody", 
			"givenNonExistentUrl", "thenNoSuchResourceError", "thenTestSpecificationHasTotalPhrases", 
			"testSpecificationMayBeProduced", "testResourceProcessedByFactory", "polymorphicDslAllRules", 
			"gherkinStepKeyword", "integerValue", "textInDoubleQuotes", "docstring", 
			"textInDoubleQuotesEnd", "givenTheTestResource", "givenTheRawResource"
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
			null, "END", "GIVEN_SPECIFIC_TEST_SPECIFICATION_FACTORY", "GIVEN_SPECIFIC_GRAMMAR", 
			"GIVEN_SPECIFIC_SUBGRAMMAR", "THEN_TEST_SPECIFICATION_FAILS_DUE_TO_MISSING_SCENARIO", 
			"THEN_TEST_SPECIFICATION_FAILS_BECAUSE_OF_MISSING_STEP_BODY", "GIVEN_NONEXISTENT_URL", 
			"THEN_NO_SUCH_RESOURCE_ERROR", "TEST_SPECIFICATION_TOTAL_PHRASES_START", 
			"TEST_SPECIFICATION_TOTAL_PHRASES_END", "TEST_SPECIFICATION_MAY_BE_PRODUCED", 
			"TEST_SPECIFICATION_IS_PROCESSED_BY_THE_FACTORY", "DOCSTRING", "DATA_ROW", 
			"GHERKIN_STEP_KEYWORD", "INT", "QUOTED_TEXT_END", "QUOTED_TEXT", "THEN_TEST_RESOURCE_VALIDITY", 
			"TEST_SPECIFICATION_HAS_AN_ID", "TEST_SPECIFICATION_IN_EXPECTED_FORMAT", 
			"TEST_CASE_IS_PRODUCED", "TEST_CASE_HAS_A_UNIQUE_ID", "TEST_CASE_HAS_A_TITLE", 
			"TEST_CASE_HAS_PROPER_TEST_BODY", "PDSL_CAN_PROCESS_ALL_PHRASES", "PASSING_TEST_TOTAL", 
			"PASSING_PHRASE_TOTAL", "FAILING_TEST_TOTAL", "TOTAL_PHRASES", "DUPLICATE_TEST_TOTAL", 
			"GIVEN_THE_TEST_RESOURCE", "GIVEN_THE_FOLLOWING_TEST_RESOURCE", "WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY", 
			"TEST_SPECIFICATION_IS_PRODUCED", "TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY", 
			"POLYMORPHIC_DSL_TEST_EXECUTOR", "TEST_CASE_IS_PROCESSED", "TEST_RUN_RESULT_PRODUCED"
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
	public String getGrammarFileName() { return "TestSpecificationFactoryParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TestSpecificationFactoryParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class GivenSpecificTestSpecificationFactoryContext extends ParserRuleContext {
		public TerminalNode GIVEN_SPECIFIC_TEST_SPECIFICATION_FACTORY() { return getToken(TestSpecificationFactoryParser.GIVEN_SPECIFIC_TEST_SPECIFICATION_FACTORY, 0); }
		public TextInDoubleQuotesEndContext textInDoubleQuotesEnd() {
			return getRuleContext(TextInDoubleQuotesEndContext.class,0);
		}
		public GivenSpecificTestSpecificationFactoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenSpecificTestSpecificationFactory; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).enterGivenSpecificTestSpecificationFactory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).exitGivenSpecificTestSpecificationFactory(this);
		}
	}

	public final GivenSpecificTestSpecificationFactoryContext givenSpecificTestSpecificationFactory() throws RecognitionException {
		GivenSpecificTestSpecificationFactoryContext _localctx = new GivenSpecificTestSpecificationFactoryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_givenSpecificTestSpecificationFactory);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(GIVEN_SPECIFIC_TEST_SPECIFICATION_FACTORY);
			setState(37);
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

	public static class GivenSpecificGrammarContext extends ParserRuleContext {
		public TerminalNode GIVEN_SPECIFIC_GRAMMAR() { return getToken(TestSpecificationFactoryParser.GIVEN_SPECIFIC_GRAMMAR, 0); }
		public TextInDoubleQuotesEndContext textInDoubleQuotesEnd() {
			return getRuleContext(TextInDoubleQuotesEndContext.class,0);
		}
		public GivenSpecificGrammarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenSpecificGrammar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).enterGivenSpecificGrammar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).exitGivenSpecificGrammar(this);
		}
	}

	public final GivenSpecificGrammarContext givenSpecificGrammar() throws RecognitionException {
		GivenSpecificGrammarContext _localctx = new GivenSpecificGrammarContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_givenSpecificGrammar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			match(GIVEN_SPECIFIC_GRAMMAR);
			setState(40);
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

	public static class GivenSpecificSubgrammarContext extends ParserRuleContext {
		public TerminalNode GIVEN_SPECIFIC_SUBGRAMMAR() { return getToken(TestSpecificationFactoryParser.GIVEN_SPECIFIC_SUBGRAMMAR, 0); }
		public TextInDoubleQuotesEndContext textInDoubleQuotesEnd() {
			return getRuleContext(TextInDoubleQuotesEndContext.class,0);
		}
		public GivenSpecificSubgrammarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenSpecificSubgrammar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).enterGivenSpecificSubgrammar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).exitGivenSpecificSubgrammar(this);
		}
	}

	public final GivenSpecificSubgrammarContext givenSpecificSubgrammar() throws RecognitionException {
		GivenSpecificSubgrammarContext _localctx = new GivenSpecificSubgrammarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_givenSpecificSubgrammar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			match(GIVEN_SPECIFIC_SUBGRAMMAR);
			setState(43);
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

	public static class ThenTestSpecificationFailsDueToMissingScenarioContext extends ParserRuleContext {
		public TerminalNode THEN_TEST_SPECIFICATION_FAILS_DUE_TO_MISSING_SCENARIO() { return getToken(TestSpecificationFactoryParser.THEN_TEST_SPECIFICATION_FAILS_DUE_TO_MISSING_SCENARIO, 0); }
		public ThenTestSpecificationFailsDueToMissingScenarioContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thenTestSpecificationFailsDueToMissingScenario; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).enterThenTestSpecificationFailsDueToMissingScenario(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).exitThenTestSpecificationFailsDueToMissingScenario(this);
		}
	}

	public final ThenTestSpecificationFailsDueToMissingScenarioContext thenTestSpecificationFailsDueToMissingScenario() throws RecognitionException {
		ThenTestSpecificationFailsDueToMissingScenarioContext _localctx = new ThenTestSpecificationFailsDueToMissingScenarioContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_thenTestSpecificationFailsDueToMissingScenario);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(THEN_TEST_SPECIFICATION_FAILS_DUE_TO_MISSING_SCENARIO);
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

	public static class ThenTestSpecificationFailsBecauseOfMissingStepBodyContext extends ParserRuleContext {
		public TerminalNode THEN_TEST_SPECIFICATION_FAILS_BECAUSE_OF_MISSING_STEP_BODY() { return getToken(TestSpecificationFactoryParser.THEN_TEST_SPECIFICATION_FAILS_BECAUSE_OF_MISSING_STEP_BODY, 0); }
		public ThenTestSpecificationFailsBecauseOfMissingStepBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thenTestSpecificationFailsBecauseOfMissingStepBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).enterThenTestSpecificationFailsBecauseOfMissingStepBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).exitThenTestSpecificationFailsBecauseOfMissingStepBody(this);
		}
	}

	public final ThenTestSpecificationFailsBecauseOfMissingStepBodyContext thenTestSpecificationFailsBecauseOfMissingStepBody() throws RecognitionException {
		ThenTestSpecificationFailsBecauseOfMissingStepBodyContext _localctx = new ThenTestSpecificationFailsBecauseOfMissingStepBodyContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_thenTestSpecificationFailsBecauseOfMissingStepBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			match(THEN_TEST_SPECIFICATION_FAILS_BECAUSE_OF_MISSING_STEP_BODY);
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

	public static class GivenNonExistentUrlContext extends ParserRuleContext {
		public TerminalNode GIVEN_NONEXISTENT_URL() { return getToken(TestSpecificationFactoryParser.GIVEN_NONEXISTENT_URL, 0); }
		public GivenNonExistentUrlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenNonExistentUrl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).enterGivenNonExistentUrl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).exitGivenNonExistentUrl(this);
		}
	}

	public final GivenNonExistentUrlContext givenNonExistentUrl() throws RecognitionException {
		GivenNonExistentUrlContext _localctx = new GivenNonExistentUrlContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_givenNonExistentUrl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			match(GIVEN_NONEXISTENT_URL);
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

	public static class ThenNoSuchResourceErrorContext extends ParserRuleContext {
		public TerminalNode THEN_NO_SUCH_RESOURCE_ERROR() { return getToken(TestSpecificationFactoryParser.THEN_NO_SUCH_RESOURCE_ERROR, 0); }
		public ThenNoSuchResourceErrorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thenNoSuchResourceError; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).enterThenNoSuchResourceError(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).exitThenNoSuchResourceError(this);
		}
	}

	public final ThenNoSuchResourceErrorContext thenNoSuchResourceError() throws RecognitionException {
		ThenNoSuchResourceErrorContext _localctx = new ThenNoSuchResourceErrorContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_thenNoSuchResourceError);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(THEN_NO_SUCH_RESOURCE_ERROR);
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

	public static class ThenTestSpecificationHasTotalPhrasesContext extends ParserRuleContext {
		public TerminalNode TEST_SPECIFICATION_TOTAL_PHRASES_START() { return getToken(TestSpecificationFactoryParser.TEST_SPECIFICATION_TOTAL_PHRASES_START, 0); }
		public IntegerValueContext integerValue() {
			return getRuleContext(IntegerValueContext.class,0);
		}
		public TerminalNode TEST_SPECIFICATION_TOTAL_PHRASES_END() { return getToken(TestSpecificationFactoryParser.TEST_SPECIFICATION_TOTAL_PHRASES_END, 0); }
		public ThenTestSpecificationHasTotalPhrasesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thenTestSpecificationHasTotalPhrases; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).enterThenTestSpecificationHasTotalPhrases(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).exitThenTestSpecificationHasTotalPhrases(this);
		}
	}

	public final ThenTestSpecificationHasTotalPhrasesContext thenTestSpecificationHasTotalPhrases() throws RecognitionException {
		ThenTestSpecificationHasTotalPhrasesContext _localctx = new ThenTestSpecificationHasTotalPhrasesContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_thenTestSpecificationHasTotalPhrases);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			match(TEST_SPECIFICATION_TOTAL_PHRASES_START);
			setState(54);
			integerValue();
			setState(55);
			match(TEST_SPECIFICATION_TOTAL_PHRASES_END);
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

	public static class TestSpecificationMayBeProducedContext extends ParserRuleContext {
		public TerminalNode TEST_SPECIFICATION_MAY_BE_PRODUCED() { return getToken(TestSpecificationFactoryParser.TEST_SPECIFICATION_MAY_BE_PRODUCED, 0); }
		public TestSpecificationMayBeProducedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testSpecificationMayBeProduced; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).enterTestSpecificationMayBeProduced(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).exitTestSpecificationMayBeProduced(this);
		}
	}

	public final TestSpecificationMayBeProducedContext testSpecificationMayBeProduced() throws RecognitionException {
		TestSpecificationMayBeProducedContext _localctx = new TestSpecificationMayBeProducedContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_testSpecificationMayBeProduced);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(TEST_SPECIFICATION_MAY_BE_PRODUCED);
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

	public static class TestResourceProcessedByFactoryContext extends ParserRuleContext {
		public TerminalNode TEST_SPECIFICATION_IS_PROCESSED_BY_THE_FACTORY() { return getToken(TestSpecificationFactoryParser.TEST_SPECIFICATION_IS_PROCESSED_BY_THE_FACTORY, 0); }
		public TestResourceProcessedByFactoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testResourceProcessedByFactory; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).enterTestResourceProcessedByFactory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).exitTestResourceProcessedByFactory(this);
		}
	}

	public final TestResourceProcessedByFactoryContext testResourceProcessedByFactory() throws RecognitionException {
		TestResourceProcessedByFactoryContext _localctx = new TestResourceProcessedByFactoryContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_testResourceProcessedByFactory);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(TEST_SPECIFICATION_IS_PROCESSED_BY_THE_FACTORY);
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

	public static class PolymorphicDslAllRulesContext extends ParserRuleContext {
		public List<GivenTheRawResourceContext> givenTheRawResource() {
			return getRuleContexts(GivenTheRawResourceContext.class);
		}
		public GivenTheRawResourceContext givenTheRawResource(int i) {
			return getRuleContext(GivenTheRawResourceContext.class,i);
		}
		public List<GivenSpecificTestSpecificationFactoryContext> givenSpecificTestSpecificationFactory() {
			return getRuleContexts(GivenSpecificTestSpecificationFactoryContext.class);
		}
		public GivenSpecificTestSpecificationFactoryContext givenSpecificTestSpecificationFactory(int i) {
			return getRuleContext(GivenSpecificTestSpecificationFactoryContext.class,i);
		}
		public List<GivenSpecificGrammarContext> givenSpecificGrammar() {
			return getRuleContexts(GivenSpecificGrammarContext.class);
		}
		public GivenSpecificGrammarContext givenSpecificGrammar(int i) {
			return getRuleContext(GivenSpecificGrammarContext.class,i);
		}
		public List<GivenSpecificSubgrammarContext> givenSpecificSubgrammar() {
			return getRuleContexts(GivenSpecificSubgrammarContext.class);
		}
		public GivenSpecificSubgrammarContext givenSpecificSubgrammar(int i) {
			return getRuleContext(GivenSpecificSubgrammarContext.class,i);
		}
		public List<ThenTestSpecificationFailsDueToMissingScenarioContext> thenTestSpecificationFailsDueToMissingScenario() {
			return getRuleContexts(ThenTestSpecificationFailsDueToMissingScenarioContext.class);
		}
		public ThenTestSpecificationFailsDueToMissingScenarioContext thenTestSpecificationFailsDueToMissingScenario(int i) {
			return getRuleContext(ThenTestSpecificationFailsDueToMissingScenarioContext.class,i);
		}
		public List<ThenTestSpecificationFailsBecauseOfMissingStepBodyContext> thenTestSpecificationFailsBecauseOfMissingStepBody() {
			return getRuleContexts(ThenTestSpecificationFailsBecauseOfMissingStepBodyContext.class);
		}
		public ThenTestSpecificationFailsBecauseOfMissingStepBodyContext thenTestSpecificationFailsBecauseOfMissingStepBody(int i) {
			return getRuleContext(ThenTestSpecificationFailsBecauseOfMissingStepBodyContext.class,i);
		}
		public List<GivenNonExistentUrlContext> givenNonExistentUrl() {
			return getRuleContexts(GivenNonExistentUrlContext.class);
		}
		public GivenNonExistentUrlContext givenNonExistentUrl(int i) {
			return getRuleContext(GivenNonExistentUrlContext.class,i);
		}
		public List<ThenNoSuchResourceErrorContext> thenNoSuchResourceError() {
			return getRuleContexts(ThenNoSuchResourceErrorContext.class);
		}
		public ThenNoSuchResourceErrorContext thenNoSuchResourceError(int i) {
			return getRuleContext(ThenNoSuchResourceErrorContext.class,i);
		}
		public List<ThenTestSpecificationHasTotalPhrasesContext> thenTestSpecificationHasTotalPhrases() {
			return getRuleContexts(ThenTestSpecificationHasTotalPhrasesContext.class);
		}
		public ThenTestSpecificationHasTotalPhrasesContext thenTestSpecificationHasTotalPhrases(int i) {
			return getRuleContext(ThenTestSpecificationHasTotalPhrasesContext.class,i);
		}
		public List<TestSpecificationMayBeProducedContext> testSpecificationMayBeProduced() {
			return getRuleContexts(TestSpecificationMayBeProducedContext.class);
		}
		public TestSpecificationMayBeProducedContext testSpecificationMayBeProduced(int i) {
			return getRuleContext(TestSpecificationMayBeProducedContext.class,i);
		}
		public List<TestResourceProcessedByFactoryContext> testResourceProcessedByFactory() {
			return getRuleContexts(TestResourceProcessedByFactoryContext.class);
		}
		public TestResourceProcessedByFactoryContext testResourceProcessedByFactory(int i) {
			return getRuleContext(TestResourceProcessedByFactoryContext.class,i);
		}
		public PolymorphicDslAllRulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_polymorphicDslAllRules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).enterPolymorphicDslAllRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).exitPolymorphicDslAllRules(this);
		}
	}

	public final PolymorphicDslAllRulesContext polymorphicDslAllRules() throws RecognitionException {
		PolymorphicDslAllRulesContext _localctx = new PolymorphicDslAllRulesContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_polymorphicDslAllRules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(72);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case GIVEN_THE_FOLLOWING_TEST_RESOURCE:
					{
					setState(61);
					givenTheRawResource();
					}
					break;
				case GIVEN_SPECIFIC_TEST_SPECIFICATION_FACTORY:
					{
					setState(62);
					givenSpecificTestSpecificationFactory();
					}
					break;
				case GIVEN_SPECIFIC_GRAMMAR:
					{
					setState(63);
					givenSpecificGrammar();
					}
					break;
				case GIVEN_SPECIFIC_SUBGRAMMAR:
					{
					setState(64);
					givenSpecificSubgrammar();
					}
					break;
				case THEN_TEST_SPECIFICATION_FAILS_DUE_TO_MISSING_SCENARIO:
					{
					setState(65);
					thenTestSpecificationFailsDueToMissingScenario();
					}
					break;
				case THEN_TEST_SPECIFICATION_FAILS_BECAUSE_OF_MISSING_STEP_BODY:
					{
					setState(66);
					thenTestSpecificationFailsBecauseOfMissingStepBody();
					}
					break;
				case GIVEN_NONEXISTENT_URL:
					{
					setState(67);
					givenNonExistentUrl();
					}
					break;
				case THEN_NO_SUCH_RESOURCE_ERROR:
					{
					setState(68);
					thenNoSuchResourceError();
					}
					break;
				case TEST_SPECIFICATION_TOTAL_PHRASES_START:
					{
					setState(69);
					thenTestSpecificationHasTotalPhrases();
					}
					break;
				case TEST_SPECIFICATION_MAY_BE_PRODUCED:
					{
					setState(70);
					testSpecificationMayBeProduced();
					}
					break;
				case TEST_SPECIFICATION_IS_PROCESSED_BY_THE_FACTORY:
					{
					setState(71);
					testResourceProcessedByFactory();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(74); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GIVEN_SPECIFIC_TEST_SPECIFICATION_FACTORY) | (1L << GIVEN_SPECIFIC_GRAMMAR) | (1L << GIVEN_SPECIFIC_SUBGRAMMAR) | (1L << THEN_TEST_SPECIFICATION_FAILS_DUE_TO_MISSING_SCENARIO) | (1L << THEN_TEST_SPECIFICATION_FAILS_BECAUSE_OF_MISSING_STEP_BODY) | (1L << GIVEN_NONEXISTENT_URL) | (1L << THEN_NO_SUCH_RESOURCE_ERROR) | (1L << TEST_SPECIFICATION_TOTAL_PHRASES_START) | (1L << TEST_SPECIFICATION_MAY_BE_PRODUCED) | (1L << TEST_SPECIFICATION_IS_PROCESSED_BY_THE_FACTORY) | (1L << GIVEN_THE_FOLLOWING_TEST_RESOURCE))) != 0) );
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
		public TerminalNode GHERKIN_STEP_KEYWORD() { return getToken(TestSpecificationFactoryParser.GHERKIN_STEP_KEYWORD, 0); }
		public GherkinStepKeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gherkinStepKeyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).enterGherkinStepKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).exitGherkinStepKeyword(this);
		}
	}

	public final GherkinStepKeywordContext gherkinStepKeyword() throws RecognitionException {
		GherkinStepKeywordContext _localctx = new GherkinStepKeywordContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_gherkinStepKeyword);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
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
		public TerminalNode INT() { return getToken(TestSpecificationFactoryParser.INT, 0); }
		public IntegerValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).enterIntegerValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).exitIntegerValue(this);
		}
	}

	public final IntegerValueContext integerValue() throws RecognitionException {
		IntegerValueContext _localctx = new IntegerValueContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_integerValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
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
		public TerminalNode QUOTED_TEXT() { return getToken(TestSpecificationFactoryParser.QUOTED_TEXT, 0); }
		public TextInDoubleQuotesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textInDoubleQuotes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).enterTextInDoubleQuotes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).exitTextInDoubleQuotes(this);
		}
	}

	public final TextInDoubleQuotesContext textInDoubleQuotes() throws RecognitionException {
		TextInDoubleQuotesContext _localctx = new TextInDoubleQuotesContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_textInDoubleQuotes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
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
		public TerminalNode DOCSTRING() { return getToken(TestSpecificationFactoryParser.DOCSTRING, 0); }
		public DocstringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_docstring; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).enterDocstring(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).exitDocstring(this);
		}
	}

	public final DocstringContext docstring() throws RecognitionException {
		DocstringContext _localctx = new DocstringContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_docstring);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
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
		public TerminalNode QUOTED_TEXT_END() { return getToken(TestSpecificationFactoryParser.QUOTED_TEXT_END, 0); }
		public TextInDoubleQuotesEndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textInDoubleQuotesEnd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).enterTextInDoubleQuotesEnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).exitTextInDoubleQuotesEnd(this);
		}
	}

	public final TextInDoubleQuotesEndContext textInDoubleQuotesEnd() throws RecognitionException {
		TextInDoubleQuotesEndContext _localctx = new TextInDoubleQuotesEndContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_textInDoubleQuotesEnd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
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

	public static class GivenTheTestResourceContext extends ParserRuleContext {
		public TerminalNode GIVEN_THE_TEST_RESOURCE() { return getToken(TestSpecificationFactoryParser.GIVEN_THE_TEST_RESOURCE, 0); }
		public TextInDoubleQuotesContext textInDoubleQuotes() {
			return getRuleContext(TextInDoubleQuotesContext.class,0);
		}
		public TerminalNode END() { return getToken(TestSpecificationFactoryParser.END, 0); }
		public GivenTheTestResourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenTheTestResource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).enterGivenTheTestResource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).exitGivenTheTestResource(this);
		}
	}

	public final GivenTheTestResourceContext givenTheTestResource() throws RecognitionException {
		GivenTheTestResourceContext _localctx = new GivenTheTestResourceContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_givenTheTestResource);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(GIVEN_THE_TEST_RESOURCE);
			setState(87);
			textInDoubleQuotes();
			setState(88);
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
		public TerminalNode GIVEN_THE_FOLLOWING_TEST_RESOURCE() { return getToken(TestSpecificationFactoryParser.GIVEN_THE_FOLLOWING_TEST_RESOURCE, 0); }
		public DocstringContext docstring() {
			return getRuleContext(DocstringContext.class,0);
		}
		public GivenTheRawResourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenTheRawResource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).enterGivenTheRawResource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestSpecificationFactoryParserListener ) ((TestSpecificationFactoryParserListener)listener).exitGivenTheRawResource(this);
		}
	}

	public final GivenTheRawResourceContext givenTheRawResource() throws RecognitionException {
		GivenTheRawResourceContext _localctx = new GivenTheRawResourceContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_givenTheRawResource);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(GIVEN_THE_FOLLOWING_TEST_RESOURCE);
			setState(91);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3)`\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4\f"+
		"\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22\4\23\t"+
		"\23\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3"+
		"\b\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\6\fK\n\f\r\f\16\fL\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3"+
		"\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\2\2\24\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"$\2\2\2X\2&\3\2\2\2\4)\3\2\2\2\6,\3\2\2\2\b"+
		"/\3\2\2\2\n\61\3\2\2\2\f\63\3\2\2\2\16\65\3\2\2\2\20\67\3\2\2\2\22;\3"+
		"\2\2\2\24=\3\2\2\2\26J\3\2\2\2\30N\3\2\2\2\32P\3\2\2\2\34R\3\2\2\2\36"+
		"T\3\2\2\2 V\3\2\2\2\"X\3\2\2\2$\\\3\2\2\2&\'\7\4\2\2\'(\5 \21\2(\3\3\2"+
		"\2\2)*\7\5\2\2*+\5 \21\2+\5\3\2\2\2,-\7\6\2\2-.\5 \21\2.\7\3\2\2\2/\60"+
		"\7\7\2\2\60\t\3\2\2\2\61\62\7\b\2\2\62\13\3\2\2\2\63\64\7\t\2\2\64\r\3"+
		"\2\2\2\65\66\7\n\2\2\66\17\3\2\2\2\678\7\13\2\289\5\32\16\29:\7\f\2\2"+
		":\21\3\2\2\2;<\7\r\2\2<\23\3\2\2\2=>\7\16\2\2>\25\3\2\2\2?K\5$\23\2@K"+
		"\5\2\2\2AK\5\4\3\2BK\5\6\4\2CK\5\b\5\2DK\5\n\6\2EK\5\f\7\2FK\5\16\b\2"+
		"GK\5\20\t\2HK\5\22\n\2IK\5\24\13\2J?\3\2\2\2J@\3\2\2\2JA\3\2\2\2JB\3\2"+
		"\2\2JC\3\2\2\2JD\3\2\2\2JE\3\2\2\2JF\3\2\2\2JG\3\2\2\2JH\3\2\2\2JI\3\2"+
		"\2\2KL\3\2\2\2LJ\3\2\2\2LM\3\2\2\2M\27\3\2\2\2NO\7\21\2\2O\31\3\2\2\2"+
		"PQ\7\22\2\2Q\33\3\2\2\2RS\7\24\2\2S\35\3\2\2\2TU\7\17\2\2U\37\3\2\2\2"+
		"VW\7\23\2\2W!\3\2\2\2XY\7\"\2\2YZ\5\34\17\2Z[\7\3\2\2[#\3\2\2\2\\]\7#"+
		"\2\2]^\5\36\20\2^%\3\2\2\2\4JL";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}