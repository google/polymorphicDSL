// Generated from TestRunResultsParser.g4 by ANTLR 4.9
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
public class TestRunResultsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WHEN_THE_TEST_RESOURCE_IS_CONVERTED_TO_A_SINGLE_TEST_CASE_BY_SOME_METHOD=1, 
		THEN_TEST_TEST_CASE_COLLECTION_HAS_N_TEST_CASES_START=2, THEN_TEST_TEST_CASE_COLLECTION_HAS_N_TEST_CASES_END=3, 
		INTEGER_VALUE=4, GIVEN_THE_GRAMMAR_PARSE_TREE_LISTENER=5, THEN_A_SINGLE_TEST_CASE_IS_PRODUCED=6, 
		THEN_THE_TEST_RUN_RESULT_HAS=7, FILTERED_DUPLICATE_TESTS_END=8, PASSING_PHRASES_END=9, 
		TOTAL_PHRASES_END=10, WHEN_THE_TEST_METADATA_IS_RETRIEVED_FROM_THE_TEST_RUN_RESULT=11, 
		WHEN_THE_ONLY_TEST_METADATA_ITEM_IS_EXAMINED=12, THEN_THE_TEST_METADATA_HAS_ONE_ITEM_IN_IT=13, 
		THEN_THE_TEST_METADATA_HAS_SPECIFIED_ITEMS_IN_IT_END=14, THEN_THE_TEST_METADATA_HAS_THE_PHRASE_THAT_FAILED=15, 
		THEN_THE_TEST_METADATA_HAS_AN_EXCEPTION_EXPLAINING_WHY_THE_TEST_FAILED=16, 
		THEN_THE_TEST_METADATA_FAILING_PHRASE_IS=17, DOCSTRING=18, DATA_ROW=19, 
		GHERKIN_STEP_KEYWORD=20, INT=21, QUOTED_TEXT_END=22, QUOTED_TEXT=23;
	public static final int
		RULE_integerValue = 0, RULE_convertTestResourcesToCollectionWithSingleTestCase = 1, 
		RULE_thenTestCaseCollectionHasSpecifiedTestCases = 2, RULE_givenTheSpecifiedGrammarParseTreeListener = 3, 
		RULE_thenSingleTestCaseIsProduced = 4, RULE_thenTheTestRunResultHasSpecifiedFilteredDuplicateTests = 5, 
		RULE_thenTheTestRunResultHasSpecifiedPassingPhrases = 6, RULE_thenTheTestRunResultHasSpecifiedTotalPhrases = 7, 
		RULE_whenTheTestMetadataIsRetrievedFromTheTestRunResult = 8, RULE_whenTheOnlyTestMetadataItemIsExamined = 9, 
		RULE_thenTheTestMetadataHasAnExceptionExplainingWhyTheTestFailed = 10, 
		RULE_thenTheTestMetadataHasThePhraseThatFailed = 11, RULE_thenTheTestMetadataFailingPhraseIsSpecifiedText = 12, 
		RULE_thenTheTestMetadataHasOneItemInIt = 13, RULE_gherkinStepKeyword = 14, 
		RULE_textInDoubleQuotes = 15, RULE_docstring = 16, RULE_textInDoubleQuotesEnd = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"integerValue", "convertTestResourcesToCollectionWithSingleTestCase", 
			"thenTestCaseCollectionHasSpecifiedTestCases", "givenTheSpecifiedGrammarParseTreeListener", 
			"thenSingleTestCaseIsProduced", "thenTheTestRunResultHasSpecifiedFilteredDuplicateTests", 
			"thenTheTestRunResultHasSpecifiedPassingPhrases", "thenTheTestRunResultHasSpecifiedTotalPhrases", 
			"whenTheTestMetadataIsRetrievedFromTheTestRunResult", "whenTheOnlyTestMetadataItemIsExamined", 
			"thenTheTestMetadataHasAnExceptionExplainingWhyTheTestFailed", "thenTheTestMetadataHasThePhraseThatFailed", 
			"thenTheTestMetadataFailingPhraseIsSpecifiedText", "thenTheTestMetadataHasOneItemInIt", 
			"gherkinStepKeyword", "textInDoubleQuotes", "docstring", "textInDoubleQuotesEnd"
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
			null, "WHEN_THE_TEST_RESOURCE_IS_CONVERTED_TO_A_SINGLE_TEST_CASE_BY_SOME_METHOD", 
			"THEN_TEST_TEST_CASE_COLLECTION_HAS_N_TEST_CASES_START", "THEN_TEST_TEST_CASE_COLLECTION_HAS_N_TEST_CASES_END", 
			"INTEGER_VALUE", "GIVEN_THE_GRAMMAR_PARSE_TREE_LISTENER", "THEN_A_SINGLE_TEST_CASE_IS_PRODUCED", 
			"THEN_THE_TEST_RUN_RESULT_HAS", "FILTERED_DUPLICATE_TESTS_END", "PASSING_PHRASES_END", 
			"TOTAL_PHRASES_END", "WHEN_THE_TEST_METADATA_IS_RETRIEVED_FROM_THE_TEST_RUN_RESULT", 
			"WHEN_THE_ONLY_TEST_METADATA_ITEM_IS_EXAMINED", "THEN_THE_TEST_METADATA_HAS_ONE_ITEM_IN_IT", 
			"THEN_THE_TEST_METADATA_HAS_SPECIFIED_ITEMS_IN_IT_END", "THEN_THE_TEST_METADATA_HAS_THE_PHRASE_THAT_FAILED", 
			"THEN_THE_TEST_METADATA_HAS_AN_EXCEPTION_EXPLAINING_WHY_THE_TEST_FAILED", 
			"THEN_THE_TEST_METADATA_FAILING_PHRASE_IS", "DOCSTRING", "DATA_ROW", 
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
	public String getGrammarFileName() { return "TestRunResultsParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TestRunResultsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class IntegerValueContext extends ParserRuleContext {
		public TerminalNode INTEGER_VALUE() { return getToken(TestRunResultsParser.INTEGER_VALUE, 0); }
		public IntegerValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).enterIntegerValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).exitIntegerValue(this);
		}
	}

	public final IntegerValueContext integerValue() throws RecognitionException {
		IntegerValueContext _localctx = new IntegerValueContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_integerValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(INTEGER_VALUE);
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

	public static class ConvertTestResourcesToCollectionWithSingleTestCaseContext extends ParserRuleContext {
		public TerminalNode WHEN_THE_TEST_RESOURCE_IS_CONVERTED_TO_A_SINGLE_TEST_CASE_BY_SOME_METHOD() { return getToken(TestRunResultsParser.WHEN_THE_TEST_RESOURCE_IS_CONVERTED_TO_A_SINGLE_TEST_CASE_BY_SOME_METHOD, 0); }
		public ConvertTestResourcesToCollectionWithSingleTestCaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_convertTestResourcesToCollectionWithSingleTestCase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).enterConvertTestResourcesToCollectionWithSingleTestCase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).exitConvertTestResourcesToCollectionWithSingleTestCase(this);
		}
	}

	public final ConvertTestResourcesToCollectionWithSingleTestCaseContext convertTestResourcesToCollectionWithSingleTestCase() throws RecognitionException {
		ConvertTestResourcesToCollectionWithSingleTestCaseContext _localctx = new ConvertTestResourcesToCollectionWithSingleTestCaseContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_convertTestResourcesToCollectionWithSingleTestCase);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			match(WHEN_THE_TEST_RESOURCE_IS_CONVERTED_TO_A_SINGLE_TEST_CASE_BY_SOME_METHOD);
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

	public static class ThenTestCaseCollectionHasSpecifiedTestCasesContext extends ParserRuleContext {
		public TerminalNode THEN_TEST_TEST_CASE_COLLECTION_HAS_N_TEST_CASES_START() { return getToken(TestRunResultsParser.THEN_TEST_TEST_CASE_COLLECTION_HAS_N_TEST_CASES_START, 0); }
		public IntegerValueContext integerValue() {
			return getRuleContext(IntegerValueContext.class,0);
		}
		public TerminalNode THEN_TEST_TEST_CASE_COLLECTION_HAS_N_TEST_CASES_END() { return getToken(TestRunResultsParser.THEN_TEST_TEST_CASE_COLLECTION_HAS_N_TEST_CASES_END, 0); }
		public ThenTestCaseCollectionHasSpecifiedTestCasesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thenTestCaseCollectionHasSpecifiedTestCases; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).enterThenTestCaseCollectionHasSpecifiedTestCases(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).exitThenTestCaseCollectionHasSpecifiedTestCases(this);
		}
	}

	public final ThenTestCaseCollectionHasSpecifiedTestCasesContext thenTestCaseCollectionHasSpecifiedTestCases() throws RecognitionException {
		ThenTestCaseCollectionHasSpecifiedTestCasesContext _localctx = new ThenTestCaseCollectionHasSpecifiedTestCasesContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_thenTestCaseCollectionHasSpecifiedTestCases);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(THEN_TEST_TEST_CASE_COLLECTION_HAS_N_TEST_CASES_START);
			setState(41);
			integerValue();
			setState(42);
			match(THEN_TEST_TEST_CASE_COLLECTION_HAS_N_TEST_CASES_END);
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

	public static class GivenTheSpecifiedGrammarParseTreeListenerContext extends ParserRuleContext {
		public TerminalNode GIVEN_THE_GRAMMAR_PARSE_TREE_LISTENER() { return getToken(TestRunResultsParser.GIVEN_THE_GRAMMAR_PARSE_TREE_LISTENER, 0); }
		public TextInDoubleQuotesEndContext textInDoubleQuotesEnd() {
			return getRuleContext(TextInDoubleQuotesEndContext.class,0);
		}
		public GivenTheSpecifiedGrammarParseTreeListenerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenTheSpecifiedGrammarParseTreeListener; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).enterGivenTheSpecifiedGrammarParseTreeListener(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).exitGivenTheSpecifiedGrammarParseTreeListener(this);
		}
	}

	public final GivenTheSpecifiedGrammarParseTreeListenerContext givenTheSpecifiedGrammarParseTreeListener() throws RecognitionException {
		GivenTheSpecifiedGrammarParseTreeListenerContext _localctx = new GivenTheSpecifiedGrammarParseTreeListenerContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_givenTheSpecifiedGrammarParseTreeListener);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(GIVEN_THE_GRAMMAR_PARSE_TREE_LISTENER);
			setState(45);
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

	public static class ThenSingleTestCaseIsProducedContext extends ParserRuleContext {
		public TerminalNode THEN_A_SINGLE_TEST_CASE_IS_PRODUCED() { return getToken(TestRunResultsParser.THEN_A_SINGLE_TEST_CASE_IS_PRODUCED, 0); }
		public ThenSingleTestCaseIsProducedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thenSingleTestCaseIsProduced; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).enterThenSingleTestCaseIsProduced(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).exitThenSingleTestCaseIsProduced(this);
		}
	}

	public final ThenSingleTestCaseIsProducedContext thenSingleTestCaseIsProduced() throws RecognitionException {
		ThenSingleTestCaseIsProducedContext _localctx = new ThenSingleTestCaseIsProducedContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_thenSingleTestCaseIsProduced);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			match(THEN_A_SINGLE_TEST_CASE_IS_PRODUCED);
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

	public static class ThenTheTestRunResultHasSpecifiedFilteredDuplicateTestsContext extends ParserRuleContext {
		public TerminalNode THEN_THE_TEST_RUN_RESULT_HAS() { return getToken(TestRunResultsParser.THEN_THE_TEST_RUN_RESULT_HAS, 0); }
		public IntegerValueContext integerValue() {
			return getRuleContext(IntegerValueContext.class,0);
		}
		public TerminalNode FILTERED_DUPLICATE_TESTS_END() { return getToken(TestRunResultsParser.FILTERED_DUPLICATE_TESTS_END, 0); }
		public ThenTheTestRunResultHasSpecifiedFilteredDuplicateTestsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thenTheTestRunResultHasSpecifiedFilteredDuplicateTests; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).enterThenTheTestRunResultHasSpecifiedFilteredDuplicateTests(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).exitThenTheTestRunResultHasSpecifiedFilteredDuplicateTests(this);
		}
	}

	public final ThenTheTestRunResultHasSpecifiedFilteredDuplicateTestsContext thenTheTestRunResultHasSpecifiedFilteredDuplicateTests() throws RecognitionException {
		ThenTheTestRunResultHasSpecifiedFilteredDuplicateTestsContext _localctx = new ThenTheTestRunResultHasSpecifiedFilteredDuplicateTestsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_thenTheTestRunResultHasSpecifiedFilteredDuplicateTests);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			match(THEN_THE_TEST_RUN_RESULT_HAS);
			setState(50);
			integerValue();
			setState(51);
			match(FILTERED_DUPLICATE_TESTS_END);
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

	public static class ThenTheTestRunResultHasSpecifiedPassingPhrasesContext extends ParserRuleContext {
		public TerminalNode THEN_THE_TEST_RUN_RESULT_HAS() { return getToken(TestRunResultsParser.THEN_THE_TEST_RUN_RESULT_HAS, 0); }
		public IntegerValueContext integerValue() {
			return getRuleContext(IntegerValueContext.class,0);
		}
		public TerminalNode PASSING_PHRASES_END() { return getToken(TestRunResultsParser.PASSING_PHRASES_END, 0); }
		public ThenTheTestRunResultHasSpecifiedPassingPhrasesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thenTheTestRunResultHasSpecifiedPassingPhrases; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).enterThenTheTestRunResultHasSpecifiedPassingPhrases(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).exitThenTheTestRunResultHasSpecifiedPassingPhrases(this);
		}
	}

	public final ThenTheTestRunResultHasSpecifiedPassingPhrasesContext thenTheTestRunResultHasSpecifiedPassingPhrases() throws RecognitionException {
		ThenTheTestRunResultHasSpecifiedPassingPhrasesContext _localctx = new ThenTheTestRunResultHasSpecifiedPassingPhrasesContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_thenTheTestRunResultHasSpecifiedPassingPhrases);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			match(THEN_THE_TEST_RUN_RESULT_HAS);
			setState(54);
			integerValue();
			setState(55);
			match(PASSING_PHRASES_END);
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

	public static class ThenTheTestRunResultHasSpecifiedTotalPhrasesContext extends ParserRuleContext {
		public TerminalNode THEN_THE_TEST_RUN_RESULT_HAS() { return getToken(TestRunResultsParser.THEN_THE_TEST_RUN_RESULT_HAS, 0); }
		public IntegerValueContext integerValue() {
			return getRuleContext(IntegerValueContext.class,0);
		}
		public TerminalNode TOTAL_PHRASES_END() { return getToken(TestRunResultsParser.TOTAL_PHRASES_END, 0); }
		public ThenTheTestRunResultHasSpecifiedTotalPhrasesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thenTheTestRunResultHasSpecifiedTotalPhrases; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).enterThenTheTestRunResultHasSpecifiedTotalPhrases(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).exitThenTheTestRunResultHasSpecifiedTotalPhrases(this);
		}
	}

	public final ThenTheTestRunResultHasSpecifiedTotalPhrasesContext thenTheTestRunResultHasSpecifiedTotalPhrases() throws RecognitionException {
		ThenTheTestRunResultHasSpecifiedTotalPhrasesContext _localctx = new ThenTheTestRunResultHasSpecifiedTotalPhrasesContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_thenTheTestRunResultHasSpecifiedTotalPhrases);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(THEN_THE_TEST_RUN_RESULT_HAS);
			setState(58);
			integerValue();
			setState(59);
			match(TOTAL_PHRASES_END);
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

	public static class WhenTheTestMetadataIsRetrievedFromTheTestRunResultContext extends ParserRuleContext {
		public TerminalNode WHEN_THE_TEST_METADATA_IS_RETRIEVED_FROM_THE_TEST_RUN_RESULT() { return getToken(TestRunResultsParser.WHEN_THE_TEST_METADATA_IS_RETRIEVED_FROM_THE_TEST_RUN_RESULT, 0); }
		public WhenTheTestMetadataIsRetrievedFromTheTestRunResultContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whenTheTestMetadataIsRetrievedFromTheTestRunResult; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).enterWhenTheTestMetadataIsRetrievedFromTheTestRunResult(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).exitWhenTheTestMetadataIsRetrievedFromTheTestRunResult(this);
		}
	}

	public final WhenTheTestMetadataIsRetrievedFromTheTestRunResultContext whenTheTestMetadataIsRetrievedFromTheTestRunResult() throws RecognitionException {
		WhenTheTestMetadataIsRetrievedFromTheTestRunResultContext _localctx = new WhenTheTestMetadataIsRetrievedFromTheTestRunResultContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_whenTheTestMetadataIsRetrievedFromTheTestRunResult);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(WHEN_THE_TEST_METADATA_IS_RETRIEVED_FROM_THE_TEST_RUN_RESULT);
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

	public static class WhenTheOnlyTestMetadataItemIsExaminedContext extends ParserRuleContext {
		public TerminalNode WHEN_THE_ONLY_TEST_METADATA_ITEM_IS_EXAMINED() { return getToken(TestRunResultsParser.WHEN_THE_ONLY_TEST_METADATA_ITEM_IS_EXAMINED, 0); }
		public WhenTheOnlyTestMetadataItemIsExaminedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whenTheOnlyTestMetadataItemIsExamined; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).enterWhenTheOnlyTestMetadataItemIsExamined(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).exitWhenTheOnlyTestMetadataItemIsExamined(this);
		}
	}

	public final WhenTheOnlyTestMetadataItemIsExaminedContext whenTheOnlyTestMetadataItemIsExamined() throws RecognitionException {
		WhenTheOnlyTestMetadataItemIsExaminedContext _localctx = new WhenTheOnlyTestMetadataItemIsExaminedContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_whenTheOnlyTestMetadataItemIsExamined);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(WHEN_THE_ONLY_TEST_METADATA_ITEM_IS_EXAMINED);
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

	public static class ThenTheTestMetadataHasAnExceptionExplainingWhyTheTestFailedContext extends ParserRuleContext {
		public TerminalNode THEN_THE_TEST_METADATA_HAS_AN_EXCEPTION_EXPLAINING_WHY_THE_TEST_FAILED() { return getToken(TestRunResultsParser.THEN_THE_TEST_METADATA_HAS_AN_EXCEPTION_EXPLAINING_WHY_THE_TEST_FAILED, 0); }
		public ThenTheTestMetadataHasAnExceptionExplainingWhyTheTestFailedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thenTheTestMetadataHasAnExceptionExplainingWhyTheTestFailed; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).enterThenTheTestMetadataHasAnExceptionExplainingWhyTheTestFailed(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).exitThenTheTestMetadataHasAnExceptionExplainingWhyTheTestFailed(this);
		}
	}

	public final ThenTheTestMetadataHasAnExceptionExplainingWhyTheTestFailedContext thenTheTestMetadataHasAnExceptionExplainingWhyTheTestFailed() throws RecognitionException {
		ThenTheTestMetadataHasAnExceptionExplainingWhyTheTestFailedContext _localctx = new ThenTheTestMetadataHasAnExceptionExplainingWhyTheTestFailedContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_thenTheTestMetadataHasAnExceptionExplainingWhyTheTestFailed);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(THEN_THE_TEST_METADATA_HAS_AN_EXCEPTION_EXPLAINING_WHY_THE_TEST_FAILED);
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

	public static class ThenTheTestMetadataHasThePhraseThatFailedContext extends ParserRuleContext {
		public TerminalNode THEN_THE_TEST_METADATA_HAS_THE_PHRASE_THAT_FAILED() { return getToken(TestRunResultsParser.THEN_THE_TEST_METADATA_HAS_THE_PHRASE_THAT_FAILED, 0); }
		public ThenTheTestMetadataHasThePhraseThatFailedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thenTheTestMetadataHasThePhraseThatFailed; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).enterThenTheTestMetadataHasThePhraseThatFailed(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).exitThenTheTestMetadataHasThePhraseThatFailed(this);
		}
	}

	public final ThenTheTestMetadataHasThePhraseThatFailedContext thenTheTestMetadataHasThePhraseThatFailed() throws RecognitionException {
		ThenTheTestMetadataHasThePhraseThatFailedContext _localctx = new ThenTheTestMetadataHasThePhraseThatFailedContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_thenTheTestMetadataHasThePhraseThatFailed);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(THEN_THE_TEST_METADATA_HAS_THE_PHRASE_THAT_FAILED);
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

	public static class ThenTheTestMetadataFailingPhraseIsSpecifiedTextContext extends ParserRuleContext {
		public TerminalNode THEN_THE_TEST_METADATA_FAILING_PHRASE_IS() { return getToken(TestRunResultsParser.THEN_THE_TEST_METADATA_FAILING_PHRASE_IS, 0); }
		public TextInDoubleQuotesEndContext textInDoubleQuotesEnd() {
			return getRuleContext(TextInDoubleQuotesEndContext.class,0);
		}
		public ThenTheTestMetadataFailingPhraseIsSpecifiedTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thenTheTestMetadataFailingPhraseIsSpecifiedText; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).enterThenTheTestMetadataFailingPhraseIsSpecifiedText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).exitThenTheTestMetadataFailingPhraseIsSpecifiedText(this);
		}
	}

	public final ThenTheTestMetadataFailingPhraseIsSpecifiedTextContext thenTheTestMetadataFailingPhraseIsSpecifiedText() throws RecognitionException {
		ThenTheTestMetadataFailingPhraseIsSpecifiedTextContext _localctx = new ThenTheTestMetadataFailingPhraseIsSpecifiedTextContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_thenTheTestMetadataFailingPhraseIsSpecifiedText);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(THEN_THE_TEST_METADATA_FAILING_PHRASE_IS);
			setState(70);
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

	public static class ThenTheTestMetadataHasOneItemInItContext extends ParserRuleContext {
		public TerminalNode THEN_THE_TEST_METADATA_HAS_ONE_ITEM_IN_IT() { return getToken(TestRunResultsParser.THEN_THE_TEST_METADATA_HAS_ONE_ITEM_IN_IT, 0); }
		public ThenTheTestMetadataHasOneItemInItContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thenTheTestMetadataHasOneItemInIt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).enterThenTheTestMetadataHasOneItemInIt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).exitThenTheTestMetadataHasOneItemInIt(this);
		}
	}

	public final ThenTheTestMetadataHasOneItemInItContext thenTheTestMetadataHasOneItemInIt() throws RecognitionException {
		ThenTheTestMetadataHasOneItemInItContext _localctx = new ThenTheTestMetadataHasOneItemInItContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_thenTheTestMetadataHasOneItemInIt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(THEN_THE_TEST_METADATA_HAS_ONE_ITEM_IN_IT);
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
		public TerminalNode GHERKIN_STEP_KEYWORD() { return getToken(TestRunResultsParser.GHERKIN_STEP_KEYWORD, 0); }
		public GherkinStepKeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gherkinStepKeyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).enterGherkinStepKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).exitGherkinStepKeyword(this);
		}
	}

	public final GherkinStepKeywordContext gherkinStepKeyword() throws RecognitionException {
		GherkinStepKeywordContext _localctx = new GherkinStepKeywordContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_gherkinStepKeyword);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
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

	public static class TextInDoubleQuotesContext extends ParserRuleContext {
		public TerminalNode QUOTED_TEXT() { return getToken(TestRunResultsParser.QUOTED_TEXT, 0); }
		public TextInDoubleQuotesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textInDoubleQuotes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).enterTextInDoubleQuotes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).exitTextInDoubleQuotes(this);
		}
	}

	public final TextInDoubleQuotesContext textInDoubleQuotes() throws RecognitionException {
		TextInDoubleQuotesContext _localctx = new TextInDoubleQuotesContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_textInDoubleQuotes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
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
		public TerminalNode DOCSTRING() { return getToken(TestRunResultsParser.DOCSTRING, 0); }
		public DocstringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_docstring; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).enterDocstring(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).exitDocstring(this);
		}
	}

	public final DocstringContext docstring() throws RecognitionException {
		DocstringContext _localctx = new DocstringContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_docstring);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
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
		public TerminalNode QUOTED_TEXT_END() { return getToken(TestRunResultsParser.QUOTED_TEXT_END, 0); }
		public TextInDoubleQuotesEndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textInDoubleQuotesEnd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).enterTextInDoubleQuotesEnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultsParserListener ) ((TestRunResultsParserListener)listener).exitTextInDoubleQuotesEnd(this);
		}
	}

	public final TextInDoubleQuotesEndContext textInDoubleQuotesEnd() throws RecognitionException {
		TextInDoubleQuotesEndContext _localctx = new TextInDoubleQuotesEndContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_textInDoubleQuotesEnd);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\31U\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22\4\23"+
		"\t\23\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r"+
		"\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\23"+
		"\2\2\24\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$\2\2\2B\2&\3\2\2\2\4"+
		"(\3\2\2\2\6*\3\2\2\2\b.\3\2\2\2\n\61\3\2\2\2\f\63\3\2\2\2\16\67\3\2\2"+
		"\2\20;\3\2\2\2\22?\3\2\2\2\24A\3\2\2\2\26C\3\2\2\2\30E\3\2\2\2\32G\3\2"+
		"\2\2\34J\3\2\2\2\36L\3\2\2\2 N\3\2\2\2\"P\3\2\2\2$R\3\2\2\2&\'\7\6\2\2"+
		"\'\3\3\2\2\2()\7\3\2\2)\5\3\2\2\2*+\7\4\2\2+,\5\2\2\2,-\7\5\2\2-\7\3\2"+
		"\2\2./\7\7\2\2/\60\5$\23\2\60\t\3\2\2\2\61\62\7\b\2\2\62\13\3\2\2\2\63"+
		"\64\7\t\2\2\64\65\5\2\2\2\65\66\7\n\2\2\66\r\3\2\2\2\678\7\t\2\289\5\2"+
		"\2\29:\7\13\2\2:\17\3\2\2\2;<\7\t\2\2<=\5\2\2\2=>\7\f\2\2>\21\3\2\2\2"+
		"?@\7\r\2\2@\23\3\2\2\2AB\7\16\2\2B\25\3\2\2\2CD\7\22\2\2D\27\3\2\2\2E"+
		"F\7\21\2\2F\31\3\2\2\2GH\7\23\2\2HI\5$\23\2I\33\3\2\2\2JK\7\17\2\2K\35"+
		"\3\2\2\2LM\7\26\2\2M\37\3\2\2\2NO\7\31\2\2O!\3\2\2\2PQ\7\24\2\2Q#\3\2"+
		"\2\2RS\7\30\2\2S%\3\2\2\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}