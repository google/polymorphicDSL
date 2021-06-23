// Generated from PdslFrameworkSpecificationParser.g4 by ANTLR 4.9
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
public class PdslFrameworkSpecificationParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		NL=1, INT=2, GIVEN_THE_TEST_RESOURCE=3, WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY=4, 
		TEST_SPECIFICATION_IS_PRODUCED=5, TEST_SPECIFICATION_HAS_AN_ID=6, TEST_SPECIFICATION_IN_EXPECTED_FORMAT=7, 
		TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY=8, TEST_CASE_IS_PRODUCED=9, 
		TEST_CASE_HAS_A_UNIQUE_ID=10, TEST_CASE_HAS_A_TITLE=11, TEST_CASE_HAS_PROPER_TEST_BODY=12, 
		POLYMORPHIC_DSL_TEST_EXECUTOR=13, PDSL_CAN_PROCESS_ALL_PHRASES=14, TEST_CASE_IS_PROCESSED=15, 
		TEST_RUN_RESULT_PRODUCED=16, PASSING_TEST_TOTAL=17, PASSING_PHRASE_TOTAL=18, 
		FAILING_TEST_TOTAL=19, TOTAL_PHRASES=20, DUPLICATE_TEST_TOTAL=21;
	public static final int
		RULE_givenTheTestResource = 0, RULE_whenTheTestResourceIsProcessedByFactory = 1, 
		RULE_testSpecificationIsProduced = 2, RULE_testSpecificationHasAnId = 3, 
		RULE_testSpecificationInExpectedFormat = 4, RULE_testSpecificationIsProcessedByTestCaseFactory = 5, 
		RULE_testCaseIsProduced = 6, RULE_testCaseHasUniqueId = 7, RULE_testCaseHasTitle = 8, 
		RULE_testCaseHasProperTestBody = 9, RULE_polymorphicDslTestExecutor = 10, 
		RULE_pdslCanProcessAllPhrases = 11, RULE_testCaseIsProcessed = 12, RULE_testRunResultProduced = 13, 
		RULE_passingTestTotal = 14, RULE_passingPhraseTotal = 15, RULE_failingTestTotal = 16, 
		RULE_totalPhrases = 17, RULE_duplicateTestTotal = 18, RULE_polymorphicDslAllRules = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"givenTheTestResource", "whenTheTestResourceIsProcessedByFactory", "testSpecificationIsProduced", 
			"testSpecificationHasAnId", "testSpecificationInExpectedFormat", "testSpecificationIsProcessedByTestCaseFactory", 
			"testCaseIsProduced", "testCaseHasUniqueId", "testCaseHasTitle", "testCaseHasProperTestBody", 
			"polymorphicDslTestExecutor", "pdslCanProcessAllPhrases", "testCaseIsProcessed", 
			"testRunResultProduced", "passingTestTotal", "passingPhraseTotal", "failingTestTotal", 
			"totalPhrases", "duplicateTestTotal", "polymorphicDslAllRules"
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
			null, "NL", "INT", "GIVEN_THE_TEST_RESOURCE", "WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY", 
			"TEST_SPECIFICATION_IS_PRODUCED", "TEST_SPECIFICATION_HAS_AN_ID", "TEST_SPECIFICATION_IN_EXPECTED_FORMAT", 
			"TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY", "TEST_CASE_IS_PRODUCED", 
			"TEST_CASE_HAS_A_UNIQUE_ID", "TEST_CASE_HAS_A_TITLE", "TEST_CASE_HAS_PROPER_TEST_BODY", 
			"POLYMORPHIC_DSL_TEST_EXECUTOR", "PDSL_CAN_PROCESS_ALL_PHRASES", "TEST_CASE_IS_PROCESSED", 
			"TEST_RUN_RESULT_PRODUCED", "PASSING_TEST_TOTAL", "PASSING_PHRASE_TOTAL", 
			"FAILING_TEST_TOTAL", "TOTAL_PHRASES", "DUPLICATE_TEST_TOTAL"
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
	public String getGrammarFileName() { return "PdslFrameworkSpecificationParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PdslFrameworkSpecificationParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class GivenTheTestResourceContext extends ParserRuleContext {
		public TerminalNode GIVEN_THE_TEST_RESOURCE() { return getToken(PdslFrameworkSpecificationParser.GIVEN_THE_TEST_RESOURCE, 0); }
		public GivenTheTestResourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenTheTestResource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterGivenTheTestResource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitGivenTheTestResource(this);
		}
	}

	public final GivenTheTestResourceContext givenTheTestResource() throws RecognitionException {
		GivenTheTestResourceContext _localctx = new GivenTheTestResourceContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_givenTheTestResource);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(GIVEN_THE_TEST_RESOURCE);
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
		public TerminalNode WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY() { return getToken(PdslFrameworkSpecificationParser.WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY, 0); }
		public WhenTheTestResourceIsProcessedByFactoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whenTheTestResourceIsProcessedByFactory; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterWhenTheTestResourceIsProcessedByFactory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitWhenTheTestResourceIsProcessedByFactory(this);
		}
	}

	public final WhenTheTestResourceIsProcessedByFactoryContext whenTheTestResourceIsProcessedByFactory() throws RecognitionException {
		WhenTheTestResourceIsProcessedByFactoryContext _localctx = new WhenTheTestResourceIsProcessedByFactoryContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_whenTheTestResourceIsProcessedByFactory);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
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
		public TerminalNode TEST_SPECIFICATION_IS_PRODUCED() { return getToken(PdslFrameworkSpecificationParser.TEST_SPECIFICATION_IS_PRODUCED, 0); }
		public TestSpecificationIsProducedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testSpecificationIsProduced; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterTestSpecificationIsProduced(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitTestSpecificationIsProduced(this);
		}
	}

	public final TestSpecificationIsProducedContext testSpecificationIsProduced() throws RecognitionException {
		TestSpecificationIsProducedContext _localctx = new TestSpecificationIsProducedContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_testSpecificationIsProduced);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
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

	public static class TestSpecificationHasAnIdContext extends ParserRuleContext {
		public TerminalNode TEST_SPECIFICATION_HAS_AN_ID() { return getToken(PdslFrameworkSpecificationParser.TEST_SPECIFICATION_HAS_AN_ID, 0); }
		public TestSpecificationHasAnIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testSpecificationHasAnId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterTestSpecificationHasAnId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitTestSpecificationHasAnId(this);
		}
	}

	public final TestSpecificationHasAnIdContext testSpecificationHasAnId() throws RecognitionException {
		TestSpecificationHasAnIdContext _localctx = new TestSpecificationHasAnIdContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_testSpecificationHasAnId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(TEST_SPECIFICATION_HAS_AN_ID);
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

	public static class TestSpecificationInExpectedFormatContext extends ParserRuleContext {
		public TerminalNode TEST_SPECIFICATION_IN_EXPECTED_FORMAT() { return getToken(PdslFrameworkSpecificationParser.TEST_SPECIFICATION_IN_EXPECTED_FORMAT, 0); }
		public TestSpecificationInExpectedFormatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testSpecificationInExpectedFormat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterTestSpecificationInExpectedFormat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitTestSpecificationInExpectedFormat(this);
		}
	}

	public final TestSpecificationInExpectedFormatContext testSpecificationInExpectedFormat() throws RecognitionException {
		TestSpecificationInExpectedFormatContext _localctx = new TestSpecificationInExpectedFormatContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_testSpecificationInExpectedFormat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			match(TEST_SPECIFICATION_IN_EXPECTED_FORMAT);
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
		public TerminalNode TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY() { return getToken(PdslFrameworkSpecificationParser.TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY, 0); }
		public TestSpecificationIsProcessedByTestCaseFactoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testSpecificationIsProcessedByTestCaseFactory; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterTestSpecificationIsProcessedByTestCaseFactory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitTestSpecificationIsProcessedByTestCaseFactory(this);
		}
	}

	public final TestSpecificationIsProcessedByTestCaseFactoryContext testSpecificationIsProcessedByTestCaseFactory() throws RecognitionException {
		TestSpecificationIsProcessedByTestCaseFactoryContext _localctx = new TestSpecificationIsProcessedByTestCaseFactoryContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_testSpecificationIsProcessedByTestCaseFactory);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
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

	public static class TestCaseIsProducedContext extends ParserRuleContext {
		public TerminalNode TEST_CASE_IS_PRODUCED() { return getToken(PdslFrameworkSpecificationParser.TEST_CASE_IS_PRODUCED, 0); }
		public TestCaseIsProducedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testCaseIsProduced; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterTestCaseIsProduced(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitTestCaseIsProduced(this);
		}
	}

	public final TestCaseIsProducedContext testCaseIsProduced() throws RecognitionException {
		TestCaseIsProducedContext _localctx = new TestCaseIsProducedContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_testCaseIsProduced);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(TEST_CASE_IS_PRODUCED);
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

	public static class TestCaseHasUniqueIdContext extends ParserRuleContext {
		public TerminalNode TEST_CASE_HAS_A_UNIQUE_ID() { return getToken(PdslFrameworkSpecificationParser.TEST_CASE_HAS_A_UNIQUE_ID, 0); }
		public TestCaseHasUniqueIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testCaseHasUniqueId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterTestCaseHasUniqueId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitTestCaseHasUniqueId(this);
		}
	}

	public final TestCaseHasUniqueIdContext testCaseHasUniqueId() throws RecognitionException {
		TestCaseHasUniqueIdContext _localctx = new TestCaseHasUniqueIdContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_testCaseHasUniqueId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			match(TEST_CASE_HAS_A_UNIQUE_ID);
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

	public static class TestCaseHasTitleContext extends ParserRuleContext {
		public TerminalNode TEST_CASE_HAS_A_TITLE() { return getToken(PdslFrameworkSpecificationParser.TEST_CASE_HAS_A_TITLE, 0); }
		public TestCaseHasTitleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testCaseHasTitle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterTestCaseHasTitle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitTestCaseHasTitle(this);
		}
	}

	public final TestCaseHasTitleContext testCaseHasTitle() throws RecognitionException {
		TestCaseHasTitleContext _localctx = new TestCaseHasTitleContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_testCaseHasTitle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(TEST_CASE_HAS_A_TITLE);
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

	public static class TestCaseHasProperTestBodyContext extends ParserRuleContext {
		public TerminalNode TEST_CASE_HAS_PROPER_TEST_BODY() { return getToken(PdslFrameworkSpecificationParser.TEST_CASE_HAS_PROPER_TEST_BODY, 0); }
		public TestCaseHasProperTestBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testCaseHasProperTestBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterTestCaseHasProperTestBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitTestCaseHasProperTestBody(this);
		}
	}

	public final TestCaseHasProperTestBodyContext testCaseHasProperTestBody() throws RecognitionException {
		TestCaseHasProperTestBodyContext _localctx = new TestCaseHasProperTestBodyContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_testCaseHasProperTestBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(TEST_CASE_HAS_PROPER_TEST_BODY);
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
		public TerminalNode POLYMORPHIC_DSL_TEST_EXECUTOR() { return getToken(PdslFrameworkSpecificationParser.POLYMORPHIC_DSL_TEST_EXECUTOR, 0); }
		public PolymorphicDslTestExecutorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_polymorphicDslTestExecutor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterPolymorphicDslTestExecutor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitPolymorphicDslTestExecutor(this);
		}
	}

	public final PolymorphicDslTestExecutorContext polymorphicDslTestExecutor() throws RecognitionException {
		PolymorphicDslTestExecutorContext _localctx = new PolymorphicDslTestExecutorContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_polymorphicDslTestExecutor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
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

	public static class PdslCanProcessAllPhrasesContext extends ParserRuleContext {
		public TerminalNode PDSL_CAN_PROCESS_ALL_PHRASES() { return getToken(PdslFrameworkSpecificationParser.PDSL_CAN_PROCESS_ALL_PHRASES, 0); }
		public PdslCanProcessAllPhrasesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pdslCanProcessAllPhrases; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterPdslCanProcessAllPhrases(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitPdslCanProcessAllPhrases(this);
		}
	}

	public final PdslCanProcessAllPhrasesContext pdslCanProcessAllPhrases() throws RecognitionException {
		PdslCanProcessAllPhrasesContext _localctx = new PdslCanProcessAllPhrasesContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_pdslCanProcessAllPhrases);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(PDSL_CAN_PROCESS_ALL_PHRASES);
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

	public static class TestCaseIsProcessedContext extends ParserRuleContext {
		public TerminalNode TEST_CASE_IS_PROCESSED() { return getToken(PdslFrameworkSpecificationParser.TEST_CASE_IS_PROCESSED, 0); }
		public TestCaseIsProcessedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testCaseIsProcessed; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterTestCaseIsProcessed(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitTestCaseIsProcessed(this);
		}
	}

	public final TestCaseIsProcessedContext testCaseIsProcessed() throws RecognitionException {
		TestCaseIsProcessedContext _localctx = new TestCaseIsProcessedContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_testCaseIsProcessed);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(TEST_CASE_IS_PROCESSED);
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
		public TerminalNode TEST_RUN_RESULT_PRODUCED() { return getToken(PdslFrameworkSpecificationParser.TEST_RUN_RESULT_PRODUCED, 0); }
		public TestRunResultProducedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testRunResultProduced; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterTestRunResultProduced(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitTestRunResultProduced(this);
		}
	}

	public final TestRunResultProducedContext testRunResultProduced() throws RecognitionException {
		TestRunResultProducedContext _localctx = new TestRunResultProducedContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_testRunResultProduced);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
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

	public static class PassingTestTotalContext extends ParserRuleContext {
		public TerminalNode PASSING_TEST_TOTAL() { return getToken(PdslFrameworkSpecificationParser.PASSING_TEST_TOTAL, 0); }
		public PassingTestTotalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_passingTestTotal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterPassingTestTotal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitPassingTestTotal(this);
		}
	}

	public final PassingTestTotalContext passingTestTotal() throws RecognitionException {
		PassingTestTotalContext _localctx = new PassingTestTotalContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_passingTestTotal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(PASSING_TEST_TOTAL);
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

	public static class PassingPhraseTotalContext extends ParserRuleContext {
		public TerminalNode PASSING_PHRASE_TOTAL() { return getToken(PdslFrameworkSpecificationParser.PASSING_PHRASE_TOTAL, 0); }
		public PassingPhraseTotalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_passingPhraseTotal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterPassingPhraseTotal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitPassingPhraseTotal(this);
		}
	}

	public final PassingPhraseTotalContext passingPhraseTotal() throws RecognitionException {
		PassingPhraseTotalContext _localctx = new PassingPhraseTotalContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_passingPhraseTotal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(PASSING_PHRASE_TOTAL);
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

	public static class FailingTestTotalContext extends ParserRuleContext {
		public TerminalNode FAILING_TEST_TOTAL() { return getToken(PdslFrameworkSpecificationParser.FAILING_TEST_TOTAL, 0); }
		public FailingTestTotalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_failingTestTotal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterFailingTestTotal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitFailingTestTotal(this);
		}
	}

	public final FailingTestTotalContext failingTestTotal() throws RecognitionException {
		FailingTestTotalContext _localctx = new FailingTestTotalContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_failingTestTotal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(FAILING_TEST_TOTAL);
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

	public static class TotalPhrasesContext extends ParserRuleContext {
		public TerminalNode TOTAL_PHRASES() { return getToken(PdslFrameworkSpecificationParser.TOTAL_PHRASES, 0); }
		public TotalPhrasesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_totalPhrases; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterTotalPhrases(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitTotalPhrases(this);
		}
	}

	public final TotalPhrasesContext totalPhrases() throws RecognitionException {
		TotalPhrasesContext _localctx = new TotalPhrasesContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_totalPhrases);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(TOTAL_PHRASES);
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

	public static class DuplicateTestTotalContext extends ParserRuleContext {
		public TerminalNode DUPLICATE_TEST_TOTAL() { return getToken(PdslFrameworkSpecificationParser.DUPLICATE_TEST_TOTAL, 0); }
		public DuplicateTestTotalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_duplicateTestTotal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterDuplicateTestTotal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitDuplicateTestTotal(this);
		}
	}

	public final DuplicateTestTotalContext duplicateTestTotal() throws RecognitionException {
		DuplicateTestTotalContext _localctx = new DuplicateTestTotalContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_duplicateTestTotal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(DUPLICATE_TEST_TOTAL);
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
		public WhenTheTestResourceIsProcessedByFactoryContext whenTheTestResourceIsProcessedByFactory() {
			return getRuleContext(WhenTheTestResourceIsProcessedByFactoryContext.class,0);
		}
		public TestSpecificationIsProducedContext testSpecificationIsProduced() {
			return getRuleContext(TestSpecificationIsProducedContext.class,0);
		}
		public TestSpecificationIsProcessedByTestCaseFactoryContext testSpecificationIsProcessedByTestCaseFactory() {
			return getRuleContext(TestSpecificationIsProcessedByTestCaseFactoryContext.class,0);
		}
		public TestCaseIsProducedContext testCaseIsProduced() {
			return getRuleContext(TestCaseIsProducedContext.class,0);
		}
		public TestCaseIsProcessedContext testCaseIsProcessed() {
			return getRuleContext(TestCaseIsProcessedContext.class,0);
		}
		public TestRunResultProducedContext testRunResultProduced() {
			return getRuleContext(TestRunResultProducedContext.class,0);
		}
		public List<GivenTheTestResourceContext> givenTheTestResource() {
			return getRuleContexts(GivenTheTestResourceContext.class);
		}
		public GivenTheTestResourceContext givenTheTestResource(int i) {
			return getRuleContext(GivenTheTestResourceContext.class,i);
		}
		public List<TestSpecificationHasAnIdContext> testSpecificationHasAnId() {
			return getRuleContexts(TestSpecificationHasAnIdContext.class);
		}
		public TestSpecificationHasAnIdContext testSpecificationHasAnId(int i) {
			return getRuleContext(TestSpecificationHasAnIdContext.class,i);
		}
		public List<TestSpecificationInExpectedFormatContext> testSpecificationInExpectedFormat() {
			return getRuleContexts(TestSpecificationInExpectedFormatContext.class);
		}
		public TestSpecificationInExpectedFormatContext testSpecificationInExpectedFormat(int i) {
			return getRuleContext(TestSpecificationInExpectedFormatContext.class,i);
		}
		public List<TestCaseHasUniqueIdContext> testCaseHasUniqueId() {
			return getRuleContexts(TestCaseHasUniqueIdContext.class);
		}
		public TestCaseHasUniqueIdContext testCaseHasUniqueId(int i) {
			return getRuleContext(TestCaseHasUniqueIdContext.class,i);
		}
		public List<TestCaseHasTitleContext> testCaseHasTitle() {
			return getRuleContexts(TestCaseHasTitleContext.class);
		}
		public TestCaseHasTitleContext testCaseHasTitle(int i) {
			return getRuleContext(TestCaseHasTitleContext.class,i);
		}
		public List<TestCaseHasProperTestBodyContext> testCaseHasProperTestBody() {
			return getRuleContexts(TestCaseHasProperTestBodyContext.class);
		}
		public TestCaseHasProperTestBodyContext testCaseHasProperTestBody(int i) {
			return getRuleContext(TestCaseHasProperTestBodyContext.class,i);
		}
		public List<PolymorphicDslTestExecutorContext> polymorphicDslTestExecutor() {
			return getRuleContexts(PolymorphicDslTestExecutorContext.class);
		}
		public PolymorphicDslTestExecutorContext polymorphicDslTestExecutor(int i) {
			return getRuleContext(PolymorphicDslTestExecutorContext.class,i);
		}
		public List<PdslCanProcessAllPhrasesContext> pdslCanProcessAllPhrases() {
			return getRuleContexts(PdslCanProcessAllPhrasesContext.class);
		}
		public PdslCanProcessAllPhrasesContext pdslCanProcessAllPhrases(int i) {
			return getRuleContext(PdslCanProcessAllPhrasesContext.class,i);
		}
		public List<PassingTestTotalContext> passingTestTotal() {
			return getRuleContexts(PassingTestTotalContext.class);
		}
		public PassingTestTotalContext passingTestTotal(int i) {
			return getRuleContext(PassingTestTotalContext.class,i);
		}
		public List<PassingPhraseTotalContext> passingPhraseTotal() {
			return getRuleContexts(PassingPhraseTotalContext.class);
		}
		public PassingPhraseTotalContext passingPhraseTotal(int i) {
			return getRuleContext(PassingPhraseTotalContext.class,i);
		}
		public List<FailingTestTotalContext> failingTestTotal() {
			return getRuleContexts(FailingTestTotalContext.class);
		}
		public FailingTestTotalContext failingTestTotal(int i) {
			return getRuleContext(FailingTestTotalContext.class,i);
		}
		public List<TotalPhrasesContext> totalPhrases() {
			return getRuleContexts(TotalPhrasesContext.class);
		}
		public TotalPhrasesContext totalPhrases(int i) {
			return getRuleContext(TotalPhrasesContext.class,i);
		}
		public List<DuplicateTestTotalContext> duplicateTestTotal() {
			return getRuleContexts(DuplicateTestTotalContext.class);
		}
		public DuplicateTestTotalContext duplicateTestTotal(int i) {
			return getRuleContext(DuplicateTestTotalContext.class,i);
		}
		public PolymorphicDslAllRulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_polymorphicDslAllRules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterPolymorphicDslAllRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitPolymorphicDslAllRules(this);
		}
	}

	public final PolymorphicDslAllRulesContext polymorphicDslAllRules() throws RecognitionException {
		PolymorphicDslAllRulesContext _localctx = new PolymorphicDslAllRulesContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_polymorphicDslAllRules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(78);
				givenTheTestResource();
				}
				}
				setState(81); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==GIVEN_THE_TEST_RESOURCE );
			setState(83);
			whenTheTestResourceIsProcessedByFactory();
			setState(84);
			testSpecificationIsProduced();
			setState(87); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(87);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case TEST_SPECIFICATION_HAS_AN_ID:
					{
					setState(85);
					testSpecificationHasAnId();
					}
					break;
				case TEST_SPECIFICATION_IN_EXPECTED_FORMAT:
					{
					setState(86);
					testSpecificationInExpectedFormat();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(89); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==TEST_SPECIFICATION_HAS_AN_ID || _la==TEST_SPECIFICATION_IN_EXPECTED_FORMAT );
			setState(91);
			testSpecificationIsProcessedByTestCaseFactory();
			setState(92);
			testCaseIsProduced();
			setState(96); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(96);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case TEST_CASE_HAS_A_UNIQUE_ID:
					{
					setState(93);
					testCaseHasUniqueId();
					}
					break;
				case TEST_CASE_HAS_A_TITLE:
					{
					setState(94);
					testCaseHasTitle();
					}
					break;
				case TEST_CASE_HAS_PROPER_TEST_BODY:
					{
					setState(95);
					testCaseHasProperTestBody();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(98); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEST_CASE_HAS_A_UNIQUE_ID) | (1L << TEST_CASE_HAS_A_TITLE) | (1L << TEST_CASE_HAS_PROPER_TEST_BODY))) != 0) );
			setState(101); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(100);
				polymorphicDslTestExecutor();
				}
				}
				setState(103); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==POLYMORPHIC_DSL_TEST_EXECUTOR );
			setState(106); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(105);
				pdslCanProcessAllPhrases();
				}
				}
				setState(108); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==PDSL_CAN_PROCESS_ALL_PHRASES );
			setState(110);
			testCaseIsProcessed();
			setState(111);
			testRunResultProduced();
			setState(117); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(117);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PASSING_TEST_TOTAL:
					{
					setState(112);
					passingTestTotal();
					}
					break;
				case PASSING_PHRASE_TOTAL:
					{
					setState(113);
					passingPhraseTotal();
					}
					break;
				case FAILING_TEST_TOTAL:
					{
					setState(114);
					failingTestTotal();
					}
					break;
				case TOTAL_PHRASES:
					{
					setState(115);
					totalPhrases();
					}
					break;
				case DUPLICATE_TEST_TOTAL:
					{
					setState(116);
					duplicateTestTotal();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(119); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PASSING_TEST_TOTAL) | (1L << PASSING_PHRASE_TOTAL) | (1L << FAILING_TEST_TOTAL) | (1L << TOTAL_PHRASES) | (1L << DUPLICATE_TEST_TOTAL))) != 0) );
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\27|\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22\4\23"+
		"\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7"+
		"\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17"+
		"\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\6\25R\n\25"+
		"\r\25\16\25S\3\25\3\25\3\25\3\25\6\25Z\n\25\r\25\16\25[\3\25\3\25\3\25"+
		"\3\25\3\25\6\25c\n\25\r\25\16\25d\3\25\6\25h\n\25\r\25\16\25i\3\25\6\25"+
		"m\n\25\r\25\16\25n\3\25\3\25\3\25\3\25\3\25\3\25\3\25\6\25x\n\25\r\25"+
		"\16\25y\3\25\2\2\26\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(\2\2\2"+
		"t\2*\3\2\2\2\4,\3\2\2\2\6.\3\2\2\2\b\60\3\2\2\2\n\62\3\2\2\2\f\64\3\2"+
		"\2\2\16\66\3\2\2\2\208\3\2\2\2\22:\3\2\2\2\24<\3\2\2\2\26>\3\2\2\2\30"+
		"@\3\2\2\2\32B\3\2\2\2\34D\3\2\2\2\36F\3\2\2\2 H\3\2\2\2\"J\3\2\2\2$L\3"+
		"\2\2\2&N\3\2\2\2(Q\3\2\2\2*+\7\5\2\2+\3\3\2\2\2,-\7\6\2\2-\5\3\2\2\2."+
		"/\7\7\2\2/\7\3\2\2\2\60\61\7\b\2\2\61\t\3\2\2\2\62\63\7\t\2\2\63\13\3"+
		"\2\2\2\64\65\7\n\2\2\65\r\3\2\2\2\66\67\7\13\2\2\67\17\3\2\2\289\7\f\2"+
		"\29\21\3\2\2\2:;\7\r\2\2;\23\3\2\2\2<=\7\16\2\2=\25\3\2\2\2>?\7\17\2\2"+
		"?\27\3\2\2\2@A\7\20\2\2A\31\3\2\2\2BC\7\21\2\2C\33\3\2\2\2DE\7\22\2\2"+
		"E\35\3\2\2\2FG\7\23\2\2G\37\3\2\2\2HI\7\24\2\2I!\3\2\2\2JK\7\25\2\2K#"+
		"\3\2\2\2LM\7\26\2\2M%\3\2\2\2NO\7\27\2\2O\'\3\2\2\2PR\5\2\2\2QP\3\2\2"+
		"\2RS\3\2\2\2SQ\3\2\2\2ST\3\2\2\2TU\3\2\2\2UV\5\4\3\2VY\5\6\4\2WZ\5\b\5"+
		"\2XZ\5\n\6\2YW\3\2\2\2YX\3\2\2\2Z[\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\]\3\2"+
		"\2\2]^\5\f\7\2^b\5\16\b\2_c\5\20\t\2`c\5\22\n\2ac\5\24\13\2b_\3\2\2\2"+
		"b`\3\2\2\2ba\3\2\2\2cd\3\2\2\2db\3\2\2\2de\3\2\2\2eg\3\2\2\2fh\5\26\f"+
		"\2gf\3\2\2\2hi\3\2\2\2ig\3\2\2\2ij\3\2\2\2jl\3\2\2\2km\5\30\r\2lk\3\2"+
		"\2\2mn\3\2\2\2nl\3\2\2\2no\3\2\2\2op\3\2\2\2pq\5\32\16\2qw\5\34\17\2r"+
		"x\5\36\20\2sx\5 \21\2tx\5\"\22\2ux\5$\23\2vx\5&\24\2wr\3\2\2\2ws\3\2\2"+
		"\2wt\3\2\2\2wu\3\2\2\2wv\3\2\2\2xy\3\2\2\2yw\3\2\2\2yz\3\2\2\2z)\3\2\2"+
		"\2\13SY[bdinwy";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}