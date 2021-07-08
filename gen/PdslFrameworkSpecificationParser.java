// Generated from /home/nboyer/repos/polymorphicDSL/src/test/resources/PdslFrameworkSpecificationParser.g4 by ANTLR 4.9.1
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
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		GHERKIN_STEP_KEYWORD=1, INT=2, GIVEN_THE_TEST_RESOURCE=3, WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY=4, 
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
		RULE_totalPhrases = 17, RULE_duplicateTestTotal = 18, RULE_polymorphicDslAllRules = 19, 
		RULE_polymorphicDslSyntaxRule = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"givenTheTestResource", "whenTheTestResourceIsProcessedByFactory", "testSpecificationIsProduced", 
			"testSpecificationHasAnId", "testSpecificationInExpectedFormat", "testSpecificationIsProcessedByTestCaseFactory", 
			"testCaseIsProduced", "testCaseHasUniqueId", "testCaseHasTitle", "testCaseHasProperTestBody", 
			"polymorphicDslTestExecutor", "pdslCanProcessAllPhrases", "testCaseIsProcessed", 
			"testRunResultProduced", "passingTestTotal", "passingPhraseTotal", "failingTestTotal", 
			"totalPhrases", "duplicateTestTotal", "polymorphicDslAllRules", "polymorphicDslSyntaxRule"
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
			null, "GHERKIN_STEP_KEYWORD", "INT", "GIVEN_THE_TEST_RESOURCE", "WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY", 
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PdslFrameworkSpecificationParserVisitor ) return ((PdslFrameworkSpecificationParserVisitor<? extends T>)visitor).visitGivenTheTestResource(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GivenTheTestResourceContext givenTheTestResource() throws RecognitionException {
		GivenTheTestResourceContext _localctx = new GivenTheTestResourceContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_givenTheTestResource);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PdslFrameworkSpecificationParserVisitor ) return ((PdslFrameworkSpecificationParserVisitor<? extends T>)visitor).visitWhenTheTestResourceIsProcessedByFactory(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhenTheTestResourceIsProcessedByFactoryContext whenTheTestResourceIsProcessedByFactory() throws RecognitionException {
		WhenTheTestResourceIsProcessedByFactoryContext _localctx = new WhenTheTestResourceIsProcessedByFactoryContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_whenTheTestResourceIsProcessedByFactory);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PdslFrameworkSpecificationParserVisitor ) return ((PdslFrameworkSpecificationParserVisitor<? extends T>)visitor).visitTestSpecificationIsProduced(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TestSpecificationIsProducedContext testSpecificationIsProduced() throws RecognitionException {
		TestSpecificationIsProducedContext _localctx = new TestSpecificationIsProducedContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_testSpecificationIsProduced);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PdslFrameworkSpecificationParserVisitor ) return ((PdslFrameworkSpecificationParserVisitor<? extends T>)visitor).visitTestSpecificationHasAnId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TestSpecificationHasAnIdContext testSpecificationHasAnId() throws RecognitionException {
		TestSpecificationHasAnIdContext _localctx = new TestSpecificationHasAnIdContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_testSpecificationHasAnId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PdslFrameworkSpecificationParserVisitor ) return ((PdslFrameworkSpecificationParserVisitor<? extends T>)visitor).visitTestSpecificationInExpectedFormat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TestSpecificationInExpectedFormatContext testSpecificationInExpectedFormat() throws RecognitionException {
		TestSpecificationInExpectedFormatContext _localctx = new TestSpecificationInExpectedFormatContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_testSpecificationInExpectedFormat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PdslFrameworkSpecificationParserVisitor ) return ((PdslFrameworkSpecificationParserVisitor<? extends T>)visitor).visitTestSpecificationIsProcessedByTestCaseFactory(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TestSpecificationIsProcessedByTestCaseFactoryContext testSpecificationIsProcessedByTestCaseFactory() throws RecognitionException {
		TestSpecificationIsProcessedByTestCaseFactoryContext _localctx = new TestSpecificationIsProcessedByTestCaseFactoryContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_testSpecificationIsProcessedByTestCaseFactory);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PdslFrameworkSpecificationParserVisitor ) return ((PdslFrameworkSpecificationParserVisitor<? extends T>)visitor).visitTestCaseIsProduced(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TestCaseIsProducedContext testCaseIsProduced() throws RecognitionException {
		TestCaseIsProducedContext _localctx = new TestCaseIsProducedContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_testCaseIsProduced);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PdslFrameworkSpecificationParserVisitor ) return ((PdslFrameworkSpecificationParserVisitor<? extends T>)visitor).visitTestCaseHasUniqueId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TestCaseHasUniqueIdContext testCaseHasUniqueId() throws RecognitionException {
		TestCaseHasUniqueIdContext _localctx = new TestCaseHasUniqueIdContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_testCaseHasUniqueId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PdslFrameworkSpecificationParserVisitor ) return ((PdslFrameworkSpecificationParserVisitor<? extends T>)visitor).visitTestCaseHasTitle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TestCaseHasTitleContext testCaseHasTitle() throws RecognitionException {
		TestCaseHasTitleContext _localctx = new TestCaseHasTitleContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_testCaseHasTitle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PdslFrameworkSpecificationParserVisitor ) return ((PdslFrameworkSpecificationParserVisitor<? extends T>)visitor).visitTestCaseHasProperTestBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TestCaseHasProperTestBodyContext testCaseHasProperTestBody() throws RecognitionException {
		TestCaseHasProperTestBodyContext _localctx = new TestCaseHasProperTestBodyContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_testCaseHasProperTestBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PdslFrameworkSpecificationParserVisitor ) return ((PdslFrameworkSpecificationParserVisitor<? extends T>)visitor).visitPolymorphicDslTestExecutor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PolymorphicDslTestExecutorContext polymorphicDslTestExecutor() throws RecognitionException {
		PolymorphicDslTestExecutorContext _localctx = new PolymorphicDslTestExecutorContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_polymorphicDslTestExecutor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PdslFrameworkSpecificationParserVisitor ) return ((PdslFrameworkSpecificationParserVisitor<? extends T>)visitor).visitPdslCanProcessAllPhrases(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PdslCanProcessAllPhrasesContext pdslCanProcessAllPhrases() throws RecognitionException {
		PdslCanProcessAllPhrasesContext _localctx = new PdslCanProcessAllPhrasesContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_pdslCanProcessAllPhrases);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PdslFrameworkSpecificationParserVisitor ) return ((PdslFrameworkSpecificationParserVisitor<? extends T>)visitor).visitTestCaseIsProcessed(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TestCaseIsProcessedContext testCaseIsProcessed() throws RecognitionException {
		TestCaseIsProcessedContext _localctx = new TestCaseIsProcessedContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_testCaseIsProcessed);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PdslFrameworkSpecificationParserVisitor ) return ((PdslFrameworkSpecificationParserVisitor<? extends T>)visitor).visitTestRunResultProduced(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TestRunResultProducedContext testRunResultProduced() throws RecognitionException {
		TestRunResultProducedContext _localctx = new TestRunResultProducedContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_testRunResultProduced);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PdslFrameworkSpecificationParserVisitor ) return ((PdslFrameworkSpecificationParserVisitor<? extends T>)visitor).visitPassingTestTotal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PassingTestTotalContext passingTestTotal() throws RecognitionException {
		PassingTestTotalContext _localctx = new PassingTestTotalContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_passingTestTotal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PdslFrameworkSpecificationParserVisitor ) return ((PdslFrameworkSpecificationParserVisitor<? extends T>)visitor).visitPassingPhraseTotal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PassingPhraseTotalContext passingPhraseTotal() throws RecognitionException {
		PassingPhraseTotalContext _localctx = new PassingPhraseTotalContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_passingPhraseTotal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PdslFrameworkSpecificationParserVisitor ) return ((PdslFrameworkSpecificationParserVisitor<? extends T>)visitor).visitFailingTestTotal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FailingTestTotalContext failingTestTotal() throws RecognitionException {
		FailingTestTotalContext _localctx = new FailingTestTotalContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_failingTestTotal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PdslFrameworkSpecificationParserVisitor ) return ((PdslFrameworkSpecificationParserVisitor<? extends T>)visitor).visitTotalPhrases(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TotalPhrasesContext totalPhrases() throws RecognitionException {
		TotalPhrasesContext _localctx = new TotalPhrasesContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_totalPhrases);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PdslFrameworkSpecificationParserVisitor ) return ((PdslFrameworkSpecificationParserVisitor<? extends T>)visitor).visitDuplicateTestTotal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DuplicateTestTotalContext duplicateTestTotal() throws RecognitionException {
		DuplicateTestTotalContext _localctx = new DuplicateTestTotalContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_duplicateTestTotal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PdslFrameworkSpecificationParserVisitor ) return ((PdslFrameworkSpecificationParserVisitor<? extends T>)visitor).visitPolymorphicDslAllRules(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PolymorphicDslAllRulesContext polymorphicDslAllRules() throws RecognitionException {
		PolymorphicDslAllRulesContext _localctx = new PolymorphicDslAllRulesContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_polymorphicDslAllRules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(100);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case GIVEN_THE_TEST_RESOURCE:
					{
					setState(81);
					givenTheTestResource();
					}
					break;
				case WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY:
					{
					setState(82);
					whenTheTestResourceIsProcessedByFactory();
					}
					break;
				case TEST_SPECIFICATION_IS_PRODUCED:
					{
					setState(83);
					testSpecificationIsProduced();
					}
					break;
				case TEST_SPECIFICATION_HAS_AN_ID:
					{
					setState(84);
					testSpecificationHasAnId();
					}
					break;
				case TEST_SPECIFICATION_IN_EXPECTED_FORMAT:
					{
					setState(85);
					testSpecificationInExpectedFormat();
					}
					break;
				case TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY:
					{
					setState(86);
					testSpecificationIsProcessedByTestCaseFactory();
					}
					break;
				case TEST_CASE_IS_PRODUCED:
					{
					setState(87);
					testCaseIsProduced();
					}
					break;
				case TEST_CASE_HAS_A_UNIQUE_ID:
					{
					setState(88);
					testCaseHasUniqueId();
					}
					break;
				case TEST_CASE_HAS_A_TITLE:
					{
					setState(89);
					testCaseHasTitle();
					}
					break;
				case TEST_CASE_HAS_PROPER_TEST_BODY:
					{
					setState(90);
					testCaseHasProperTestBody();
					}
					break;
				case POLYMORPHIC_DSL_TEST_EXECUTOR:
					{
					setState(91);
					polymorphicDslTestExecutor();
					}
					break;
				case PDSL_CAN_PROCESS_ALL_PHRASES:
					{
					setState(92);
					pdslCanProcessAllPhrases();
					}
					break;
				case TEST_CASE_IS_PROCESSED:
					{
					setState(93);
					testCaseIsProcessed();
					}
					break;
				case TEST_RUN_RESULT_PRODUCED:
					{
					setState(94);
					testRunResultProduced();
					}
					break;
				case PASSING_TEST_TOTAL:
					{
					setState(95);
					passingTestTotal();
					}
					break;
				case PASSING_PHRASE_TOTAL:
					{
					setState(96);
					passingPhraseTotal();
					}
					break;
				case FAILING_TEST_TOTAL:
					{
					setState(97);
					failingTestTotal();
					}
					break;
				case TOTAL_PHRASES:
					{
					setState(98);
					totalPhrases();
					}
					break;
				case DUPLICATE_TEST_TOTAL:
					{
					setState(99);
					duplicateTestTotal();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(102); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GIVEN_THE_TEST_RESOURCE) | (1L << WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY) | (1L << TEST_SPECIFICATION_IS_PRODUCED) | (1L << TEST_SPECIFICATION_HAS_AN_ID) | (1L << TEST_SPECIFICATION_IN_EXPECTED_FORMAT) | (1L << TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY) | (1L << TEST_CASE_IS_PRODUCED) | (1L << TEST_CASE_HAS_A_UNIQUE_ID) | (1L << TEST_CASE_HAS_A_TITLE) | (1L << TEST_CASE_HAS_PROPER_TEST_BODY) | (1L << POLYMORPHIC_DSL_TEST_EXECUTOR) | (1L << PDSL_CAN_PROCESS_ALL_PHRASES) | (1L << TEST_CASE_IS_PROCESSED) | (1L << TEST_RUN_RESULT_PRODUCED) | (1L << PASSING_TEST_TOTAL) | (1L << PASSING_PHRASE_TOTAL) | (1L << FAILING_TEST_TOTAL) | (1L << TOTAL_PHRASES) | (1L << DUPLICATE_TEST_TOTAL))) != 0) );
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
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PdslFrameworkSpecificationParserVisitor ) return ((PdslFrameworkSpecificationParserVisitor<? extends T>)visitor).visitPolymorphicDslSyntaxRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PolymorphicDslSyntaxRuleContext polymorphicDslSyntaxRule() throws RecognitionException {
		PolymorphicDslSyntaxRuleContext _localctx = new PolymorphicDslSyntaxRuleContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_polymorphicDslSyntaxRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(104);
				givenTheTestResource();
				}
				}
				setState(107); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==GIVEN_THE_TEST_RESOURCE );
			setState(109);
			whenTheTestResourceIsProcessedByFactory();
			setState(110);
			testSpecificationIsProduced();
			setState(113); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(113);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case TEST_SPECIFICATION_HAS_AN_ID:
					{
					setState(111);
					testSpecificationHasAnId();
					}
					break;
				case TEST_SPECIFICATION_IN_EXPECTED_FORMAT:
					{
					setState(112);
					testSpecificationInExpectedFormat();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(115); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==TEST_SPECIFICATION_HAS_AN_ID || _la==TEST_SPECIFICATION_IN_EXPECTED_FORMAT );
			setState(117);
			testSpecificationIsProcessedByTestCaseFactory();
			setState(118);
			testCaseIsProduced();
			setState(122); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(122);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case TEST_CASE_HAS_A_UNIQUE_ID:
					{
					setState(119);
					testCaseHasUniqueId();
					}
					break;
				case TEST_CASE_HAS_A_TITLE:
					{
					setState(120);
					testCaseHasTitle();
					}
					break;
				case TEST_CASE_HAS_PROPER_TEST_BODY:
					{
					setState(121);
					testCaseHasProperTestBody();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(124); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TEST_CASE_HAS_A_UNIQUE_ID) | (1L << TEST_CASE_HAS_A_TITLE) | (1L << TEST_CASE_HAS_PROPER_TEST_BODY))) != 0) );
			setState(127); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(126);
				polymorphicDslTestExecutor();
				}
				}
				setState(129); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==POLYMORPHIC_DSL_TEST_EXECUTOR );
			setState(132); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(131);
				pdslCanProcessAllPhrases();
				}
				}
				setState(134); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==PDSL_CAN_PROCESS_ALL_PHRASES );
			setState(136);
			testCaseIsProcessed();
			setState(137);
			testRunResultProduced();
			setState(143); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(143);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PASSING_TEST_TOTAL:
					{
					setState(138);
					passingTestTotal();
					}
					break;
				case PASSING_PHRASE_TOTAL:
					{
					setState(139);
					passingPhraseTotal();
					}
					break;
				case FAILING_TEST_TOTAL:
					{
					setState(140);
					failingTestTotal();
					}
					break;
				case TOTAL_PHRASES:
					{
					setState(141);
					totalPhrases();
					}
					break;
				case DUPLICATE_TEST_TOTAL:
					{
					setState(142);
					duplicateTestTotal();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(145); 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\27\u0096\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3"+
		"\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3"+
		"\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3"+
		"\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\3\25\3\25\3\25\3\25\6\25g\n\25\r\25\16\25h\3\26\6\26l\n\26\r"+
		"\26\16\26m\3\26\3\26\3\26\3\26\6\26t\n\26\r\26\16\26u\3\26\3\26\3\26\3"+
		"\26\3\26\6\26}\n\26\r\26\16\26~\3\26\6\26\u0082\n\26\r\26\16\26\u0083"+
		"\3\26\6\26\u0087\n\26\r\26\16\26\u0088\3\26\3\26\3\26\3\26\3\26\3\26\3"+
		"\26\6\26\u0092\n\26\r\26\16\26\u0093\3\26\2\2\27\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*\2\2\2\u00a0\2,\3\2\2\2\4.\3\2\2\2\6\60\3\2\2"+
		"\2\b\62\3\2\2\2\n\64\3\2\2\2\f\66\3\2\2\2\168\3\2\2\2\20:\3\2\2\2\22<"+
		"\3\2\2\2\24>\3\2\2\2\26@\3\2\2\2\30B\3\2\2\2\32D\3\2\2\2\34G\3\2\2\2\36"+
		"I\3\2\2\2 K\3\2\2\2\"M\3\2\2\2$O\3\2\2\2&Q\3\2\2\2(f\3\2\2\2*k\3\2\2\2"+
		",-\7\5\2\2-\3\3\2\2\2./\7\6\2\2/\5\3\2\2\2\60\61\7\7\2\2\61\7\3\2\2\2"+
		"\62\63\7\b\2\2\63\t\3\2\2\2\64\65\7\t\2\2\65\13\3\2\2\2\66\67\7\n\2\2"+
		"\67\r\3\2\2\289\7\13\2\29\17\3\2\2\2:;\7\f\2\2;\21\3\2\2\2<=\7\r\2\2="+
		"\23\3\2\2\2>?\7\16\2\2?\25\3\2\2\2@A\7\17\2\2A\27\3\2\2\2BC\7\20\2\2C"+
		"\31\3\2\2\2DE\7\21\2\2EF\7\2\2\3F\33\3\2\2\2GH\7\22\2\2H\35\3\2\2\2IJ"+
		"\7\23\2\2J\37\3\2\2\2KL\7\24\2\2L!\3\2\2\2MN\7\25\2\2N#\3\2\2\2OP\7\26"+
		"\2\2P%\3\2\2\2QR\7\27\2\2R\'\3\2\2\2Sg\5\2\2\2Tg\5\4\3\2Ug\5\6\4\2Vg\5"+
		"\b\5\2Wg\5\n\6\2Xg\5\f\7\2Yg\5\16\b\2Zg\5\20\t\2[g\5\22\n\2\\g\5\24\13"+
		"\2]g\5\26\f\2^g\5\30\r\2_g\5\32\16\2`g\5\34\17\2ag\5\36\20\2bg\5 \21\2"+
		"cg\5\"\22\2dg\5$\23\2eg\5&\24\2fS\3\2\2\2fT\3\2\2\2fU\3\2\2\2fV\3\2\2"+
		"\2fW\3\2\2\2fX\3\2\2\2fY\3\2\2\2fZ\3\2\2\2f[\3\2\2\2f\\\3\2\2\2f]\3\2"+
		"\2\2f^\3\2\2\2f_\3\2\2\2f`\3\2\2\2fa\3\2\2\2fb\3\2\2\2fc\3\2\2\2fd\3\2"+
		"\2\2fe\3\2\2\2gh\3\2\2\2hf\3\2\2\2hi\3\2\2\2i)\3\2\2\2jl\5\2\2\2kj\3\2"+
		"\2\2lm\3\2\2\2mk\3\2\2\2mn\3\2\2\2no\3\2\2\2op\5\4\3\2ps\5\6\4\2qt\5\b"+
		"\5\2rt\5\n\6\2sq\3\2\2\2sr\3\2\2\2tu\3\2\2\2us\3\2\2\2uv\3\2\2\2vw\3\2"+
		"\2\2wx\5\f\7\2x|\5\16\b\2y}\5\20\t\2z}\5\22\n\2{}\5\24\13\2|y\3\2\2\2"+
		"|z\3\2\2\2|{\3\2\2\2}~\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u0081\3\2\2"+
		"\2\u0080\u0082\5\26\f\2\u0081\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083"+
		"\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0086\3\2\2\2\u0085\u0087\5\30"+
		"\r\2\u0086\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0086\3\2\2\2\u0088"+
		"\u0089\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\5\32\16\2\u008b\u0091\5"+
		"\34\17\2\u008c\u0092\5\36\20\2\u008d\u0092\5 \21\2\u008e\u0092\5\"\22"+
		"\2\u008f\u0092\5$\23\2\u0090\u0092\5&\24\2\u0091\u008c\3\2\2\2\u0091\u008d"+
		"\3\2\2\2\u0091\u008e\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0090\3\2\2\2\u0092"+
		"\u0093\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094+\3\2\2\2"+
		"\rfhmsu|~\u0083\u0088\u0091\u0093";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}