// Generated from TestCaseFactoryParser.g4 by ANTLR 4.9
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
public class TestCaseFactoryParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		GIVEN_AN_ARBITRARY_TEST_SPECIFICATION=1, GIVEN_SPECIFICATION_HAS_N_PHRASES_START=2, 
		GIVEN_THE_TEST_SPECIFICATION_CHILD_START=3, THEN_N_TEST_CASES_ARE_PRODUCED_END=4, 
		THEN_EACH_TEST_CASE_HAS_N_PHRASES_START=5, PHRASES_END=6, GIVEN_SPECIFIC_TEST_CASE_FACTORY_IS_USED_START=7, 
		GIVEN_SPECIFIC_TEST_CASE_FACTORY_IS_USED_END=8, WHEN_TEST_SPECIFICATON_PROCESSED_BY_FACTORY=9, 
		DOCSTRING=10, DATA_ROW=11, GHERKIN_STEP_KEYWORD=12, INT=13, TEXT_IN_DOUBLE_QUOTES=14, 
		END_QUOTE=15;
	public static final int
		RULE_givenAnArbitraryTestSpecification = 0, RULE_givenSpecificationHasPhrase = 1, 
		RULE_givenTheTestSpecificationChild = 2, RULE_thenTestCasesAreProduced = 3, 
		RULE_thenEachTestCaseHasPhrases = 4, RULE_givenSpecificTestCaseFactoryIsUsed = 5, 
		RULE_whenTestSpecificationProcessedByFactory = 6, RULE_polymorphicDslAllRules = 7, 
		RULE_gherkinStepKeyword = 8, RULE_integerValue = 9, RULE_textInDoubleQuotes = 10, 
		RULE_docstring = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"givenAnArbitraryTestSpecification", "givenSpecificationHasPhrase", "givenTheTestSpecificationChild", 
			"thenTestCasesAreProduced", "thenEachTestCaseHasPhrases", "givenSpecificTestCaseFactoryIsUsed", 
			"whenTestSpecificationProcessedByFactory", "polymorphicDslAllRules", 
			"gherkinStepKeyword", "integerValue", "textInDoubleQuotes", "docstring"
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
			null, "GIVEN_AN_ARBITRARY_TEST_SPECIFICATION", "GIVEN_SPECIFICATION_HAS_N_PHRASES_START", 
			"GIVEN_THE_TEST_SPECIFICATION_CHILD_START", "THEN_N_TEST_CASES_ARE_PRODUCED_END", 
			"THEN_EACH_TEST_CASE_HAS_N_PHRASES_START", "PHRASES_END", "GIVEN_SPECIFIC_TEST_CASE_FACTORY_IS_USED_START", 
			"GIVEN_SPECIFIC_TEST_CASE_FACTORY_IS_USED_END", "WHEN_TEST_SPECIFICATON_PROCESSED_BY_FACTORY", 
			"DOCSTRING", "DATA_ROW", "GHERKIN_STEP_KEYWORD", "INT", "TEXT_IN_DOUBLE_QUOTES", 
			"END_QUOTE"
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
	public String getGrammarFileName() { return "TestCaseFactoryParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TestCaseFactoryParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class GivenAnArbitraryTestSpecificationContext extends ParserRuleContext {
		public TerminalNode GIVEN_AN_ARBITRARY_TEST_SPECIFICATION() { return getToken(TestCaseFactoryParser.GIVEN_AN_ARBITRARY_TEST_SPECIFICATION, 0); }
		public GivenAnArbitraryTestSpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenAnArbitraryTestSpecification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).enterGivenAnArbitraryTestSpecification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).exitGivenAnArbitraryTestSpecification(this);
		}
	}

	public final GivenAnArbitraryTestSpecificationContext givenAnArbitraryTestSpecification() throws RecognitionException {
		GivenAnArbitraryTestSpecificationContext _localctx = new GivenAnArbitraryTestSpecificationContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_givenAnArbitraryTestSpecification);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			match(GIVEN_AN_ARBITRARY_TEST_SPECIFICATION);
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

	public static class GivenSpecificationHasPhraseContext extends ParserRuleContext {
		public TerminalNode GHERKIN_STEP_KEYWORD() { return getToken(TestCaseFactoryParser.GHERKIN_STEP_KEYWORD, 0); }
		public TerminalNode GIVEN_SPECIFICATION_HAS_N_PHRASES_START() { return getToken(TestCaseFactoryParser.GIVEN_SPECIFICATION_HAS_N_PHRASES_START, 0); }
		public IntegerValueContext integerValue() {
			return getRuleContext(IntegerValueContext.class,0);
		}
		public TerminalNode PHRASES_END() { return getToken(TestCaseFactoryParser.PHRASES_END, 0); }
		public GivenSpecificationHasPhraseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenSpecificationHasPhrase; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).enterGivenSpecificationHasPhrase(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).exitGivenSpecificationHasPhrase(this);
		}
	}

	public final GivenSpecificationHasPhraseContext givenSpecificationHasPhrase() throws RecognitionException {
		GivenSpecificationHasPhraseContext _localctx = new GivenSpecificationHasPhraseContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_givenSpecificationHasPhrase);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			match(GHERKIN_STEP_KEYWORD);
			setState(27);
			match(GIVEN_SPECIFICATION_HAS_N_PHRASES_START);
			setState(28);
			integerValue();
			setState(29);
			match(PHRASES_END);
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

	public static class GivenTheTestSpecificationChildContext extends ParserRuleContext {
		public TerminalNode GIVEN_THE_TEST_SPECIFICATION_CHILD_START() { return getToken(TestCaseFactoryParser.GIVEN_THE_TEST_SPECIFICATION_CHILD_START, 0); }
		public IntegerValueContext integerValue() {
			return getRuleContext(IntegerValueContext.class,0);
		}
		public TerminalNode PHRASES_END() { return getToken(TestCaseFactoryParser.PHRASES_END, 0); }
		public GivenTheTestSpecificationChildContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenTheTestSpecificationChild; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).enterGivenTheTestSpecificationChild(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).exitGivenTheTestSpecificationChild(this);
		}
	}

	public final GivenTheTestSpecificationChildContext givenTheTestSpecificationChild() throws RecognitionException {
		GivenTheTestSpecificationChildContext _localctx = new GivenTheTestSpecificationChildContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_givenTheTestSpecificationChild);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			match(GIVEN_THE_TEST_SPECIFICATION_CHILD_START);
			setState(32);
			integerValue();
			setState(33);
			match(PHRASES_END);
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

	public static class ThenTestCasesAreProducedContext extends ParserRuleContext {
		public TerminalNode GHERKIN_STEP_KEYWORD() { return getToken(TestCaseFactoryParser.GHERKIN_STEP_KEYWORD, 0); }
		public IntegerValueContext integerValue() {
			return getRuleContext(IntegerValueContext.class,0);
		}
		public TerminalNode THEN_N_TEST_CASES_ARE_PRODUCED_END() { return getToken(TestCaseFactoryParser.THEN_N_TEST_CASES_ARE_PRODUCED_END, 0); }
		public ThenTestCasesAreProducedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thenTestCasesAreProduced; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).enterThenTestCasesAreProduced(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).exitThenTestCasesAreProduced(this);
		}
	}

	public final ThenTestCasesAreProducedContext thenTestCasesAreProduced() throws RecognitionException {
		ThenTestCasesAreProducedContext _localctx = new ThenTestCasesAreProducedContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_thenTestCasesAreProduced);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			match(GHERKIN_STEP_KEYWORD);
			setState(36);
			integerValue();
			setState(37);
			match(THEN_N_TEST_CASES_ARE_PRODUCED_END);
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

	public static class ThenEachTestCaseHasPhrasesContext extends ParserRuleContext {
		public TerminalNode THEN_EACH_TEST_CASE_HAS_N_PHRASES_START() { return getToken(TestCaseFactoryParser.THEN_EACH_TEST_CASE_HAS_N_PHRASES_START, 0); }
		public IntegerValueContext integerValue() {
			return getRuleContext(IntegerValueContext.class,0);
		}
		public TerminalNode PHRASES_END() { return getToken(TestCaseFactoryParser.PHRASES_END, 0); }
		public ThenEachTestCaseHasPhrasesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thenEachTestCaseHasPhrases; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).enterThenEachTestCaseHasPhrases(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).exitThenEachTestCaseHasPhrases(this);
		}
	}

	public final ThenEachTestCaseHasPhrasesContext thenEachTestCaseHasPhrases() throws RecognitionException {
		ThenEachTestCaseHasPhrasesContext _localctx = new ThenEachTestCaseHasPhrasesContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_thenEachTestCaseHasPhrases);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			match(THEN_EACH_TEST_CASE_HAS_N_PHRASES_START);
			setState(40);
			integerValue();
			setState(41);
			match(PHRASES_END);
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

	public static class GivenSpecificTestCaseFactoryIsUsedContext extends ParserRuleContext {
		public TerminalNode GIVEN_SPECIFIC_TEST_CASE_FACTORY_IS_USED_START() { return getToken(TestCaseFactoryParser.GIVEN_SPECIFIC_TEST_CASE_FACTORY_IS_USED_START, 0); }
		public TextInDoubleQuotesContext textInDoubleQuotes() {
			return getRuleContext(TextInDoubleQuotesContext.class,0);
		}
		public TerminalNode GIVEN_SPECIFIC_TEST_CASE_FACTORY_IS_USED_END() { return getToken(TestCaseFactoryParser.GIVEN_SPECIFIC_TEST_CASE_FACTORY_IS_USED_END, 0); }
		public GivenSpecificTestCaseFactoryIsUsedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenSpecificTestCaseFactoryIsUsed; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).enterGivenSpecificTestCaseFactoryIsUsed(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).exitGivenSpecificTestCaseFactoryIsUsed(this);
		}
	}

	public final GivenSpecificTestCaseFactoryIsUsedContext givenSpecificTestCaseFactoryIsUsed() throws RecognitionException {
		GivenSpecificTestCaseFactoryIsUsedContext _localctx = new GivenSpecificTestCaseFactoryIsUsedContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_givenSpecificTestCaseFactoryIsUsed);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(GIVEN_SPECIFIC_TEST_CASE_FACTORY_IS_USED_START);
			setState(44);
			textInDoubleQuotes();
			setState(45);
			match(GIVEN_SPECIFIC_TEST_CASE_FACTORY_IS_USED_END);
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

	public static class WhenTestSpecificationProcessedByFactoryContext extends ParserRuleContext {
		public TerminalNode WHEN_TEST_SPECIFICATON_PROCESSED_BY_FACTORY() { return getToken(TestCaseFactoryParser.WHEN_TEST_SPECIFICATON_PROCESSED_BY_FACTORY, 0); }
		public WhenTestSpecificationProcessedByFactoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whenTestSpecificationProcessedByFactory; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).enterWhenTestSpecificationProcessedByFactory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).exitWhenTestSpecificationProcessedByFactory(this);
		}
	}

	public final WhenTestSpecificationProcessedByFactoryContext whenTestSpecificationProcessedByFactory() throws RecognitionException {
		WhenTestSpecificationProcessedByFactoryContext _localctx = new WhenTestSpecificationProcessedByFactoryContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_whenTestSpecificationProcessedByFactory);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			match(WHEN_TEST_SPECIFICATON_PROCESSED_BY_FACTORY);
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
		public List<GivenAnArbitraryTestSpecificationContext> givenAnArbitraryTestSpecification() {
			return getRuleContexts(GivenAnArbitraryTestSpecificationContext.class);
		}
		public GivenAnArbitraryTestSpecificationContext givenAnArbitraryTestSpecification(int i) {
			return getRuleContext(GivenAnArbitraryTestSpecificationContext.class,i);
		}
		public List<GivenSpecificationHasPhraseContext> givenSpecificationHasPhrase() {
			return getRuleContexts(GivenSpecificationHasPhraseContext.class);
		}
		public GivenSpecificationHasPhraseContext givenSpecificationHasPhrase(int i) {
			return getRuleContext(GivenSpecificationHasPhraseContext.class,i);
		}
		public List<GivenTheTestSpecificationChildContext> givenTheTestSpecificationChild() {
			return getRuleContexts(GivenTheTestSpecificationChildContext.class);
		}
		public GivenTheTestSpecificationChildContext givenTheTestSpecificationChild(int i) {
			return getRuleContext(GivenTheTestSpecificationChildContext.class,i);
		}
		public List<ThenTestCasesAreProducedContext> thenTestCasesAreProduced() {
			return getRuleContexts(ThenTestCasesAreProducedContext.class);
		}
		public ThenTestCasesAreProducedContext thenTestCasesAreProduced(int i) {
			return getRuleContext(ThenTestCasesAreProducedContext.class,i);
		}
		public List<GivenSpecificTestCaseFactoryIsUsedContext> givenSpecificTestCaseFactoryIsUsed() {
			return getRuleContexts(GivenSpecificTestCaseFactoryIsUsedContext.class);
		}
		public GivenSpecificTestCaseFactoryIsUsedContext givenSpecificTestCaseFactoryIsUsed(int i) {
			return getRuleContext(GivenSpecificTestCaseFactoryIsUsedContext.class,i);
		}
		public List<ThenEachTestCaseHasPhrasesContext> thenEachTestCaseHasPhrases() {
			return getRuleContexts(ThenEachTestCaseHasPhrasesContext.class);
		}
		public ThenEachTestCaseHasPhrasesContext thenEachTestCaseHasPhrases(int i) {
			return getRuleContext(ThenEachTestCaseHasPhrasesContext.class,i);
		}
		public PolymorphicDslAllRulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_polymorphicDslAllRules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).enterPolymorphicDslAllRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).exitPolymorphicDslAllRules(this);
		}
	}

	public final PolymorphicDslAllRulesContext polymorphicDslAllRules() throws RecognitionException {
		PolymorphicDslAllRulesContext _localctx = new PolymorphicDslAllRulesContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_polymorphicDslAllRules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(55);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(49);
					givenAnArbitraryTestSpecification();
					}
					break;
				case 2:
					{
					setState(50);
					givenSpecificationHasPhrase();
					}
					break;
				case 3:
					{
					setState(51);
					givenTheTestSpecificationChild();
					}
					break;
				case 4:
					{
					setState(52);
					thenTestCasesAreProduced();
					}
					break;
				case 5:
					{
					setState(53);
					givenSpecificTestCaseFactoryIsUsed();
					}
					break;
				case 6:
					{
					setState(54);
					thenEachTestCaseHasPhrases();
					}
					break;
				}
				}
				setState(57); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GIVEN_AN_ARBITRARY_TEST_SPECIFICATION) | (1L << GIVEN_THE_TEST_SPECIFICATION_CHILD_START) | (1L << THEN_EACH_TEST_CASE_HAS_N_PHRASES_START) | (1L << GIVEN_SPECIFIC_TEST_CASE_FACTORY_IS_USED_START) | (1L << GHERKIN_STEP_KEYWORD))) != 0) );
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
		public TerminalNode GHERKIN_STEP_KEYWORD() { return getToken(TestCaseFactoryParser.GHERKIN_STEP_KEYWORD, 0); }
		public GherkinStepKeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gherkinStepKeyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).enterGherkinStepKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).exitGherkinStepKeyword(this);
		}
	}

	public final GherkinStepKeywordContext gherkinStepKeyword() throws RecognitionException {
		GherkinStepKeywordContext _localctx = new GherkinStepKeywordContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_gherkinStepKeyword);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
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
		public TerminalNode INT() { return getToken(TestCaseFactoryParser.INT, 0); }
		public IntegerValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).enterIntegerValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).exitIntegerValue(this);
		}
	}

	public final IntegerValueContext integerValue() throws RecognitionException {
		IntegerValueContext _localctx = new IntegerValueContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_integerValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
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
		public TerminalNode TEXT_IN_DOUBLE_QUOTES() { return getToken(TestCaseFactoryParser.TEXT_IN_DOUBLE_QUOTES, 0); }
		public TextInDoubleQuotesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textInDoubleQuotes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).enterTextInDoubleQuotes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).exitTextInDoubleQuotes(this);
		}
	}

	public final TextInDoubleQuotesContext textInDoubleQuotes() throws RecognitionException {
		TextInDoubleQuotesContext _localctx = new TextInDoubleQuotesContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_textInDoubleQuotes);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
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

	public static class DocstringContext extends ParserRuleContext {
		public TerminalNode DOCSTRING() { return getToken(TestCaseFactoryParser.DOCSTRING, 0); }
		public DocstringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_docstring; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).enterDocstring(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestCaseFactoryParserListener ) ((TestCaseFactoryParserListener)listener).exitDocstring(this);
		}
	}

	public final DocstringContext docstring() throws RecognitionException {
		DocstringContext _localctx = new DocstringContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_docstring);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\21F\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\6"+
		"\t:\n\t\r\t\16\t;\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\r\2\2\16\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\2\2\2?\2\32\3\2\2\2\4\34\3\2\2\2\6!\3\2\2\2\b%"+
		"\3\2\2\2\n)\3\2\2\2\f-\3\2\2\2\16\61\3\2\2\2\209\3\2\2\2\22=\3\2\2\2\24"+
		"?\3\2\2\2\26A\3\2\2\2\30C\3\2\2\2\32\33\7\3\2\2\33\3\3\2\2\2\34\35\7\16"+
		"\2\2\35\36\7\4\2\2\36\37\5\24\13\2\37 \7\b\2\2 \5\3\2\2\2!\"\7\5\2\2\""+
		"#\5\24\13\2#$\7\b\2\2$\7\3\2\2\2%&\7\16\2\2&\'\5\24\13\2\'(\7\6\2\2(\t"+
		"\3\2\2\2)*\7\7\2\2*+\5\24\13\2+,\7\b\2\2,\13\3\2\2\2-.\7\t\2\2./\5\26"+
		"\f\2/\60\7\n\2\2\60\r\3\2\2\2\61\62\7\13\2\2\62\17\3\2\2\2\63:\5\2\2\2"+
		"\64:\5\4\3\2\65:\5\6\4\2\66:\5\b\5\2\67:\5\f\7\28:\5\n\6\29\63\3\2\2\2"+
		"9\64\3\2\2\29\65\3\2\2\29\66\3\2\2\29\67\3\2\2\298\3\2\2\2:;\3\2\2\2;"+
		"9\3\2\2\2;<\3\2\2\2<\21\3\2\2\2=>\7\16\2\2>\23\3\2\2\2?@\7\17\2\2@\25"+
		"\3\2\2\2AB\7\20\2\2B\27\3\2\2\2CD\7\f\2\2D\31\3\2\2\2\49;";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}