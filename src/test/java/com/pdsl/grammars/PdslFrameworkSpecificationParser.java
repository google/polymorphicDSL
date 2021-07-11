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
		THEN_TEST_RESOURCE_VALIDITY=1, TEST_SPECIFICATION_HAS_AN_ID=2, TEST_SPECIFICATION_IN_EXPECTED_FORMAT=3, 
		TEST_CASE_IS_PRODUCED=4, TEST_CASE_HAS_A_UNIQUE_ID=5, TEST_CASE_HAS_A_TITLE=6, 
		TEST_CASE_HAS_PROPER_TEST_BODY=7, PDSL_CAN_PROCESS_ALL_PHRASES=8, PASSING_TEST_TOTAL=9, 
		PASSING_PHRASE_TOTAL=10, FAILING_TEST_TOTAL=11, TOTAL_PHRASES=12, DUPLICATE_TEST_TOTAL=13, 
		DOCSTRING=14, DATA_ROW=15, GHERKIN_STEP_KEYWORD=16, INT=17, TEXT_IN_DOUBLE_QUOTES=18, 
		END_QUOTE=19, DOC_STRING=20, GIVEN_THE_TEST_RESOURCE=21, GIVEN_THE_FOLLOWING_TEST_RESOURCE=22, 
		WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY=23, TEST_SPECIFICATION_IS_PRODUCED=24, 
		TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY=25, POLYMORPHIC_DSL_TEST_EXECUTOR=26, 
		TEST_CASE_IS_PROCESSED=27, TEST_RUN_RESULT_PRODUCED=28;
	public static final int
		RULE_testSpecificationHasAnId = 0, RULE_testSpecificationInExpectedFormat = 1, 
		RULE_testCaseIsProduced = 2, RULE_testCaseHasUniqueId = 3, RULE_testCaseHasTitle = 4, 
		RULE_testCaseHasProperTestBody = 5, RULE_pdslCanProcessAllPhrases = 6, 
		RULE_testCaseIsProcessed = 7, RULE_passingTestTotal = 8, RULE_passingPhraseTotal = 9, 
		RULE_failingTestTotal = 10, RULE_totalPhrases = 11, RULE_duplicateTestTotal = 12, 
		RULE_polymorphicDslAllRules = 13, RULE_polymorphicDslSyntaxRule = 14, 
		RULE_docstring = 15, RULE_givenTheTestResource = 16, RULE_givenTheRawResource = 17, 
		RULE_gherkinStepKeyword = 18, RULE_integerValue = 19, RULE_textInDoubleQuotes = 20, 
		RULE_whenTheTestResourceIsProcessedByFactory = 21, RULE_testSpecificationIsProduced = 22, 
		RULE_testSpecificationIsProcessedByTestCaseFactory = 23, RULE_polymorphicDslTestExecutor = 24, 
		RULE_testRunResultProduced = 25;
	private static String[] makeRuleNames() {
		return new String[] {
			"testSpecificationHasAnId", "testSpecificationInExpectedFormat", "testCaseIsProduced", 
			"testCaseHasUniqueId", "testCaseHasTitle", "testCaseHasProperTestBody", 
			"pdslCanProcessAllPhrases", "testCaseIsProcessed", "passingTestTotal", 
			"passingPhraseTotal", "failingTestTotal", "totalPhrases", "duplicateTestTotal", 
			"polymorphicDslAllRules", "polymorphicDslSyntaxRule", "docstring", "givenTheTestResource", 
			"givenTheRawResource", "gherkinStepKeyword", "integerValue", "textInDoubleQuotes", 
			"whenTheTestResourceIsProcessedByFactory", "testSpecificationIsProduced", 
			"testSpecificationIsProcessedByTestCaseFactory", "polymorphicDslTestExecutor", 
			"testRunResultProduced"
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
			null, "THEN_TEST_RESOURCE_VALIDITY", "TEST_SPECIFICATION_HAS_AN_ID", 
			"TEST_SPECIFICATION_IN_EXPECTED_FORMAT", "TEST_CASE_IS_PRODUCED", "TEST_CASE_HAS_A_UNIQUE_ID", 
			"TEST_CASE_HAS_A_TITLE", "TEST_CASE_HAS_PROPER_TEST_BODY", "PDSL_CAN_PROCESS_ALL_PHRASES", 
			"PASSING_TEST_TOTAL", "PASSING_PHRASE_TOTAL", "FAILING_TEST_TOTAL", "TOTAL_PHRASES", 
			"DUPLICATE_TEST_TOTAL", "DOCSTRING", "DATA_ROW", "GHERKIN_STEP_KEYWORD", 
			"INT", "TEXT_IN_DOUBLE_QUOTES", "END_QUOTE", "DOC_STRING", "GIVEN_THE_TEST_RESOURCE", 
			"GIVEN_THE_FOLLOWING_TEST_RESOURCE", "WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY", 
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
		enterRule(_localctx, 0, RULE_testSpecificationHasAnId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
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
		enterRule(_localctx, 2, RULE_testSpecificationInExpectedFormat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
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
		enterRule(_localctx, 4, RULE_testCaseIsProduced);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
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
		enterRule(_localctx, 6, RULE_testCaseHasUniqueId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
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
		enterRule(_localctx, 8, RULE_testCaseHasTitle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
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
		enterRule(_localctx, 10, RULE_testCaseHasProperTestBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
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
		enterRule(_localctx, 12, RULE_pdslCanProcessAllPhrases);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
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
		public TerminalNode EOF() { return getToken(PdslFrameworkSpecificationParser.EOF, 0); }
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
		enterRule(_localctx, 14, RULE_testCaseIsProcessed);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(TEST_CASE_IS_PROCESSED);
			setState(67);
			match(EOF);
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
		enterRule(_localctx, 16, RULE_passingTestTotal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
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
		enterRule(_localctx, 18, RULE_passingPhraseTotal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
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
		enterRule(_localctx, 20, RULE_failingTestTotal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
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
		enterRule(_localctx, 22, RULE_totalPhrases);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
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
		enterRule(_localctx, 24, RULE_duplicateTestTotal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
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
		public List<GivenTheTestResourceContext> givenTheTestResource() {
			return getRuleContexts(GivenTheTestResourceContext.class);
		}
		public GivenTheTestResourceContext givenTheTestResource(int i) {
			return getRuleContext(GivenTheTestResourceContext.class,i);
		}
		public List<WhenTheTestResourceIsProcessedByFactoryContext> whenTheTestResourceIsProcessedByFactory() {
			return getRuleContexts(WhenTheTestResourceIsProcessedByFactoryContext.class);
		}
		public WhenTheTestResourceIsProcessedByFactoryContext whenTheTestResourceIsProcessedByFactory(int i) {
			return getRuleContext(WhenTheTestResourceIsProcessedByFactoryContext.class,i);
		}
		public List<TestSpecificationIsProducedContext> testSpecificationIsProduced() {
			return getRuleContexts(TestSpecificationIsProducedContext.class);
		}
		public TestSpecificationIsProducedContext testSpecificationIsProduced(int i) {
			return getRuleContext(TestSpecificationIsProducedContext.class,i);
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
		public List<TestSpecificationIsProcessedByTestCaseFactoryContext> testSpecificationIsProcessedByTestCaseFactory() {
			return getRuleContexts(TestSpecificationIsProcessedByTestCaseFactoryContext.class);
		}
		public TestSpecificationIsProcessedByTestCaseFactoryContext testSpecificationIsProcessedByTestCaseFactory(int i) {
			return getRuleContext(TestSpecificationIsProcessedByTestCaseFactoryContext.class,i);
		}
		public List<TestCaseIsProducedContext> testCaseIsProduced() {
			return getRuleContexts(TestCaseIsProducedContext.class);
		}
		public TestCaseIsProducedContext testCaseIsProduced(int i) {
			return getRuleContext(TestCaseIsProducedContext.class,i);
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
		public List<TestCaseIsProcessedContext> testCaseIsProcessed() {
			return getRuleContexts(TestCaseIsProcessedContext.class);
		}
		public TestCaseIsProcessedContext testCaseIsProcessed(int i) {
			return getRuleContext(TestCaseIsProcessedContext.class,i);
		}
		public List<TestRunResultProducedContext> testRunResultProduced() {
			return getRuleContexts(TestRunResultProducedContext.class);
		}
		public TestRunResultProducedContext testRunResultProduced(int i) {
			return getRuleContext(TestRunResultProducedContext.class,i);
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
		enterRule(_localctx, 26, RULE_polymorphicDslAllRules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(98);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case GIVEN_THE_TEST_RESOURCE:
					{
					setState(79);
					givenTheTestResource();
					}
					break;
				case WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY:
					{
					setState(80);
					whenTheTestResourceIsProcessedByFactory();
					}
					break;
				case TEST_SPECIFICATION_IS_PRODUCED:
					{
					setState(81);
					testSpecificationIsProduced();
					}
					break;
				case TEST_SPECIFICATION_HAS_AN_ID:
					{
					setState(82);
					testSpecificationHasAnId();
					}
					break;
				case TEST_SPECIFICATION_IN_EXPECTED_FORMAT:
					{
					setState(83);
					testSpecificationInExpectedFormat();
					}
					break;
				case TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY:
					{
					setState(84);
					testSpecificationIsProcessedByTestCaseFactory();
					}
					break;
				case TEST_CASE_IS_PRODUCED:
					{
					setState(85);
					testCaseIsProduced();
					}
					break;
				case TEST_CASE_HAS_A_UNIQUE_ID:
					{
					setState(86);
					testCaseHasUniqueId();
					}
					break;
				case TEST_CASE_HAS_A_TITLE:
					{
					setState(87);
					testCaseHasTitle();
					}
					break;
				case TEST_CASE_HAS_PROPER_TEST_BODY:
					{
					setState(88);
					testCaseHasProperTestBody();
					}
					break;
				case POLYMORPHIC_DSL_TEST_EXECUTOR:
					{
					setState(89);
					polymorphicDslTestExecutor();
					}
					break;
				case PDSL_CAN_PROCESS_ALL_PHRASES:
					{
					setState(90);
					pdslCanProcessAllPhrases();
					}
					break;
				case TEST_CASE_IS_PROCESSED:
					{
					setState(91);
					testCaseIsProcessed();
					}
					break;
				case TEST_RUN_RESULT_PRODUCED:
					{
					setState(92);
					testRunResultProduced();
					}
					break;
				case PASSING_TEST_TOTAL:
					{
					setState(93);
					passingTestTotal();
					}
					break;
				case PASSING_PHRASE_TOTAL:
					{
					setState(94);
					passingPhraseTotal();
					}
					break;
				case FAILING_TEST_TOTAL:
					{
					setState(95);
					failingTestTotal();
					}
					break;
				case TOTAL_PHRASES:
					{
					setState(96);
					totalPhrases();
					}
					break;
				case DUPLICATE_TEST_TOTAL:
					{
					setState(97);
					duplicateTestTotal();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(100); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEST_SPECIFICATION_HAS_AN_ID) | (1L << TEST_SPECIFICATION_IN_EXPECTED_FORMAT) | (1L << TEST_CASE_IS_PRODUCED) | (1L << TEST_CASE_HAS_A_UNIQUE_ID) | (1L << TEST_CASE_HAS_A_TITLE) | (1L << TEST_CASE_HAS_PROPER_TEST_BODY) | (1L << PDSL_CAN_PROCESS_ALL_PHRASES) | (1L << PASSING_TEST_TOTAL) | (1L << PASSING_PHRASE_TOTAL) | (1L << FAILING_TEST_TOTAL) | (1L << TOTAL_PHRASES) | (1L << DUPLICATE_TEST_TOTAL) | (1L << GIVEN_THE_TEST_RESOURCE) | (1L << WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY) | (1L << TEST_SPECIFICATION_IS_PRODUCED) | (1L << TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY) | (1L << POLYMORPHIC_DSL_TEST_EXECUTOR) | (1L << TEST_CASE_IS_PROCESSED) | (1L << TEST_RUN_RESULT_PRODUCED))) != 0) );
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

	public static class PolymorphicDslSyntaxRuleContext extends ParserRuleContext {
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
		public PolymorphicDslSyntaxRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_polymorphicDslSyntaxRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterPolymorphicDslSyntaxRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitPolymorphicDslSyntaxRule(this);
		}
	}

	public final PolymorphicDslSyntaxRuleContext polymorphicDslSyntaxRule() throws RecognitionException {
		PolymorphicDslSyntaxRuleContext _localctx = new PolymorphicDslSyntaxRuleContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_polymorphicDslSyntaxRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(102);
				givenTheTestResource();
				}
				}
				setState(105); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==GIVEN_THE_TEST_RESOURCE );
			setState(107);
			whenTheTestResourceIsProcessedByFactory();
			setState(108);
			testSpecificationIsProduced();
			setState(111); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(111);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case TEST_SPECIFICATION_HAS_AN_ID:
					{
					setState(109);
					testSpecificationHasAnId();
					}
					break;
				case TEST_SPECIFICATION_IN_EXPECTED_FORMAT:
					{
					setState(110);
					testSpecificationInExpectedFormat();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(113); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==TEST_SPECIFICATION_HAS_AN_ID || _la==TEST_SPECIFICATION_IN_EXPECTED_FORMAT );
			setState(115);
			testSpecificationIsProcessedByTestCaseFactory();
			setState(116);
			testCaseIsProduced();
			setState(120); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(120);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case TEST_CASE_HAS_A_UNIQUE_ID:
					{
					setState(117);
					testCaseHasUniqueId();
					}
					break;
				case TEST_CASE_HAS_A_TITLE:
					{
					setState(118);
					testCaseHasTitle();
					}
					break;
				case TEST_CASE_HAS_PROPER_TEST_BODY:
					{
					setState(119);
					testCaseHasProperTestBody();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(122); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEST_CASE_HAS_A_UNIQUE_ID) | (1L << TEST_CASE_HAS_A_TITLE) | (1L << TEST_CASE_HAS_PROPER_TEST_BODY))) != 0) );
			setState(125); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(124);
				polymorphicDslTestExecutor();
				}
				}
				setState(127); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==POLYMORPHIC_DSL_TEST_EXECUTOR );
			setState(130); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(129);
				pdslCanProcessAllPhrases();
				}
				}
				setState(132); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==PDSL_CAN_PROCESS_ALL_PHRASES );
			setState(134);
			testCaseIsProcessed();
			setState(135);
			testRunResultProduced();
			setState(141); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(141);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PASSING_TEST_TOTAL:
					{
					setState(136);
					passingTestTotal();
					}
					break;
				case PASSING_PHRASE_TOTAL:
					{
					setState(137);
					passingPhraseTotal();
					}
					break;
				case FAILING_TEST_TOTAL:
					{
					setState(138);
					failingTestTotal();
					}
					break;
				case TOTAL_PHRASES:
					{
					setState(139);
					totalPhrases();
					}
					break;
				case DUPLICATE_TEST_TOTAL:
					{
					setState(140);
					duplicateTestTotal();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(143); 
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

	public static class DocstringContext extends ParserRuleContext {
		public TerminalNode DOCSTRING() { return getToken(PdslFrameworkSpecificationParser.DOCSTRING, 0); }
		public DocstringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_docstring; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterDocstring(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitDocstring(this);
		}
	}

	public final DocstringContext docstring() throws RecognitionException {
		DocstringContext _localctx = new DocstringContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_docstring);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
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

	public static class GivenTheTestResourceContext extends ParserRuleContext {
		public TerminalNode GIVEN_THE_TEST_RESOURCE() { return getToken(PdslFrameworkSpecificationParser.GIVEN_THE_TEST_RESOURCE, 0); }
		public TextInDoubleQuotesContext textInDoubleQuotes() {
			return getRuleContext(TextInDoubleQuotesContext.class,0);
		}
		public TerminalNode END_QUOTE() { return getToken(PdslFrameworkSpecificationParser.END_QUOTE, 0); }
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
		enterRule(_localctx, 32, RULE_givenTheTestResource);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(147);
			match(GIVEN_THE_TEST_RESOURCE);
			setState(148);
			textInDoubleQuotes();
			setState(149);
			match(END_QUOTE);
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
		public TerminalNode GIVEN_THE_FOLLOWING_TEST_RESOURCE() { return getToken(PdslFrameworkSpecificationParser.GIVEN_THE_FOLLOWING_TEST_RESOURCE, 0); }
		public DocstringContext docstring() {
			return getRuleContext(DocstringContext.class,0);
		}
		public GivenTheRawResourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenTheRawResource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterGivenTheRawResource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitGivenTheRawResource(this);
		}
	}

	public final GivenTheRawResourceContext givenTheRawResource() throws RecognitionException {
		GivenTheRawResourceContext _localctx = new GivenTheRawResourceContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_givenTheRawResource);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			match(GIVEN_THE_FOLLOWING_TEST_RESOURCE);
			setState(152);
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
		public TerminalNode GHERKIN_STEP_KEYWORD() { return getToken(PdslFrameworkSpecificationParser.GHERKIN_STEP_KEYWORD, 0); }
		public GherkinStepKeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gherkinStepKeyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterGherkinStepKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitGherkinStepKeyword(this);
		}
	}

	public final GherkinStepKeywordContext gherkinStepKeyword() throws RecognitionException {
		GherkinStepKeywordContext _localctx = new GherkinStepKeywordContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_gherkinStepKeyword);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
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
		public TerminalNode INT() { return getToken(PdslFrameworkSpecificationParser.INT, 0); }
		public IntegerValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterIntegerValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitIntegerValue(this);
		}
	}

	public final IntegerValueContext integerValue() throws RecognitionException {
		IntegerValueContext _localctx = new IntegerValueContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_integerValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
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
		public TerminalNode TEXT_IN_DOUBLE_QUOTES() { return getToken(PdslFrameworkSpecificationParser.TEXT_IN_DOUBLE_QUOTES, 0); }
		public TextInDoubleQuotesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textInDoubleQuotes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).enterTextInDoubleQuotes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslFrameworkSpecificationParserListener ) ((PdslFrameworkSpecificationParserListener)listener).exitTextInDoubleQuotes(this);
		}
	}

	public final TextInDoubleQuotesContext textInDoubleQuotes() throws RecognitionException {
		TextInDoubleQuotesContext _localctx = new TextInDoubleQuotesContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_textInDoubleQuotes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(TEXT_IN_DOUBLE_QUOTES);
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
		enterRule(_localctx, 42, RULE_whenTheTestResourceIsProcessedByFactory);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
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
		enterRule(_localctx, 44, RULE_testSpecificationIsProduced);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
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
		enterRule(_localctx, 46, RULE_testSpecificationIsProcessedByTestCaseFactory);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
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
		enterRule(_localctx, 48, RULE_polymorphicDslTestExecutor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
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
		enterRule(_localctx, 50, RULE_testRunResultProduced);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\36\u00ad\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3"+
		"\b\3\b\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\6\17e\n\17\r\17\16\17f\3\20\6\20j\n\20\r\20\16\20k"+
		"\3\20\3\20\3\20\3\20\6\20r\n\20\r\20\16\20s\3\20\3\20\3\20\3\20\3\20\6"+
		"\20{\n\20\r\20\16\20|\3\20\6\20\u0080\n\20\r\20\16\20\u0081\3\20\6\20"+
		"\u0085\n\20\r\20\16\20\u0086\3\20\3\20\3\20\3\20\3\20\3\20\3\20\6\20\u0090"+
		"\n\20\r\20\16\20\u0091\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3"+
		"\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3"+
		"\33\3\33\3\33\2\2\34\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60"+
		"\62\64\2\2\2\u00b2\2\66\3\2\2\2\48\3\2\2\2\6:\3\2\2\2\b<\3\2\2\2\n>\3"+
		"\2\2\2\f@\3\2\2\2\16B\3\2\2\2\20D\3\2\2\2\22G\3\2\2\2\24I\3\2\2\2\26K"+
		"\3\2\2\2\30M\3\2\2\2\32O\3\2\2\2\34d\3\2\2\2\36i\3\2\2\2 \u0093\3\2\2"+
		"\2\"\u0095\3\2\2\2$\u0099\3\2\2\2&\u009c\3\2\2\2(\u009e\3\2\2\2*\u00a0"+
		"\3\2\2\2,\u00a2\3\2\2\2.\u00a4\3\2\2\2\60\u00a6\3\2\2\2\62\u00a8\3\2\2"+
		"\2\64\u00aa\3\2\2\2\66\67\7\4\2\2\67\3\3\2\2\289\7\5\2\29\5\3\2\2\2:;"+
		"\7\6\2\2;\7\3\2\2\2<=\7\7\2\2=\t\3\2\2\2>?\7\b\2\2?\13\3\2\2\2@A\7\t\2"+
		"\2A\r\3\2\2\2BC\7\n\2\2C\17\3\2\2\2DE\7\35\2\2EF\7\2\2\3F\21\3\2\2\2G"+
		"H\7\13\2\2H\23\3\2\2\2IJ\7\f\2\2J\25\3\2\2\2KL\7\r\2\2L\27\3\2\2\2MN\7"+
		"\16\2\2N\31\3\2\2\2OP\7\17\2\2P\33\3\2\2\2Qe\5\"\22\2Re\5,\27\2Se\5.\30"+
		"\2Te\5\2\2\2Ue\5\4\3\2Ve\5\60\31\2We\5\6\4\2Xe\5\b\5\2Ye\5\n\6\2Ze\5\f"+
		"\7\2[e\5\62\32\2\\e\5\16\b\2]e\5\20\t\2^e\5\64\33\2_e\5\22\n\2`e\5\24"+
		"\13\2ae\5\26\f\2be\5\30\r\2ce\5\32\16\2dQ\3\2\2\2dR\3\2\2\2dS\3\2\2\2"+
		"dT\3\2\2\2dU\3\2\2\2dV\3\2\2\2dW\3\2\2\2dX\3\2\2\2dY\3\2\2\2dZ\3\2\2\2"+
		"d[\3\2\2\2d\\\3\2\2\2d]\3\2\2\2d^\3\2\2\2d_\3\2\2\2d`\3\2\2\2da\3\2\2"+
		"\2db\3\2\2\2dc\3\2\2\2ef\3\2\2\2fd\3\2\2\2fg\3\2\2\2g\35\3\2\2\2hj\5\""+
		"\22\2ih\3\2\2\2jk\3\2\2\2ki\3\2\2\2kl\3\2\2\2lm\3\2\2\2mn\5,\27\2nq\5"+
		".\30\2or\5\2\2\2pr\5\4\3\2qo\3\2\2\2qp\3\2\2\2rs\3\2\2\2sq\3\2\2\2st\3"+
		"\2\2\2tu\3\2\2\2uv\5\60\31\2vz\5\6\4\2w{\5\b\5\2x{\5\n\6\2y{\5\f\7\2z"+
		"w\3\2\2\2zx\3\2\2\2zy\3\2\2\2{|\3\2\2\2|z\3\2\2\2|}\3\2\2\2}\177\3\2\2"+
		"\2~\u0080\5\62\32\2\177~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\177\3\2\2\2"+
		"\u0081\u0082\3\2\2\2\u0082\u0084\3\2\2\2\u0083\u0085\5\16\b\2\u0084\u0083"+
		"\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087"+
		"\u0088\3\2\2\2\u0088\u0089\5\20\t\2\u0089\u008f\5\64\33\2\u008a\u0090"+
		"\5\22\n\2\u008b\u0090\5\24\13\2\u008c\u0090\5\26\f\2\u008d\u0090\5\30"+
		"\r\2\u008e\u0090\5\32\16\2\u008f\u008a\3\2\2\2\u008f\u008b\3\2\2\2\u008f"+
		"\u008c\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u008e\3\2\2\2\u0090\u0091\3\2"+
		"\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\37\3\2\2\2\u0093\u0094"+
		"\7\20\2\2\u0094!\3\2\2\2\u0095\u0096\7\27\2\2\u0096\u0097\5*\26\2\u0097"+
		"\u0098\7\25\2\2\u0098#\3\2\2\2\u0099\u009a\7\30\2\2\u009a\u009b\5 \21"+
		"\2\u009b%\3\2\2\2\u009c\u009d\7\22\2\2\u009d\'\3\2\2\2\u009e\u009f\7\23"+
		"\2\2\u009f)\3\2\2\2\u00a0\u00a1\7\24\2\2\u00a1+\3\2\2\2\u00a2\u00a3\7"+
		"\31\2\2\u00a3-\3\2\2\2\u00a4\u00a5\7\32\2\2\u00a5/\3\2\2\2\u00a6\u00a7"+
		"\7\33\2\2\u00a7\61\3\2\2\2\u00a8\u00a9\7\34\2\2\u00a9\63\3\2\2\2\u00aa"+
		"\u00ab\7\36\2\2\u00ab\65\3\2\2\2\rdfkqsz|\u0081\u0086\u008f\u0091";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}