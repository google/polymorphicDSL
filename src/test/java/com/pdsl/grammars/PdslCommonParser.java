// Generated from PdslCommonParser.g4 by ANTLR 4.9
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
public class PdslCommonParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY=1, TEST_SPECIFICATION_IS_PRODUCED=2, 
		TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY=3, POLYMORPHIC_DSL_TEST_EXECUTOR=4, 
		TEST_CASE_IS_PROCESSED=5, TEST_RUN_RESULT_PRODUCED=6, DOCSTRING=7, DATA_ROW=8, 
		GHERKIN_STEP_KEYWORD=9, INT=10, TEXT_IN_DOUBLE_QUOTES=11, END_QUOTE=12, 
		DOC_STRING=13;
	public static final int
		RULE_whenTheTestResourceIsProcessedByFactory = 0, RULE_testSpecificationIsProduced = 1, 
		RULE_testSpecificationIsProcessedByTestCaseFactory = 2, RULE_polymorphicDslTestExecutor = 3, 
		RULE_testRunResultProduced = 4;
	private static String[] makeRuleNames() {
		return new String[] {
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
			null, "WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY", "TEST_SPECIFICATION_IS_PRODUCED", 
			"TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY", "POLYMORPHIC_DSL_TEST_EXECUTOR", 
			"TEST_CASE_IS_PROCESSED", "TEST_RUN_RESULT_PRODUCED", "DOCSTRING", "DATA_ROW", 
			"GHERKIN_STEP_KEYWORD", "INT", "TEXT_IN_DOUBLE_QUOTES", "END_QUOTE", 
			"DOC_STRING"
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
	public String getGrammarFileName() { return "PdslCommonParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PdslCommonParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class WhenTheTestResourceIsProcessedByFactoryContext extends ParserRuleContext {
		public TerminalNode WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY() { return getToken(PdslCommonParser.WHEN_THE_TEST_RESOURCE_IS_PROCESSED_BY_A_FACTORY, 0); }
		public WhenTheTestResourceIsProcessedByFactoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whenTheTestResourceIsProcessedByFactory; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslCommonParserListener ) ((PdslCommonParserListener)listener).enterWhenTheTestResourceIsProcessedByFactory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslCommonParserListener ) ((PdslCommonParserListener)listener).exitWhenTheTestResourceIsProcessedByFactory(this);
		}
	}

	public final WhenTheTestResourceIsProcessedByFactoryContext whenTheTestResourceIsProcessedByFactory() throws RecognitionException {
		WhenTheTestResourceIsProcessedByFactoryContext _localctx = new WhenTheTestResourceIsProcessedByFactoryContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_whenTheTestResourceIsProcessedByFactory);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
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
		public TerminalNode TEST_SPECIFICATION_IS_PRODUCED() { return getToken(PdslCommonParser.TEST_SPECIFICATION_IS_PRODUCED, 0); }
		public TestSpecificationIsProducedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testSpecificationIsProduced; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslCommonParserListener ) ((PdslCommonParserListener)listener).enterTestSpecificationIsProduced(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslCommonParserListener ) ((PdslCommonParserListener)listener).exitTestSpecificationIsProduced(this);
		}
	}

	public final TestSpecificationIsProducedContext testSpecificationIsProduced() throws RecognitionException {
		TestSpecificationIsProducedContext _localctx = new TestSpecificationIsProducedContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_testSpecificationIsProduced);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(12);
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
		public TerminalNode TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY() { return getToken(PdslCommonParser.TEST_SPECIFICATION_IS_PROCESSED_BY_TEST_CASE_FACTORY, 0); }
		public TestSpecificationIsProcessedByTestCaseFactoryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testSpecificationIsProcessedByTestCaseFactory; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslCommonParserListener ) ((PdslCommonParserListener)listener).enterTestSpecificationIsProcessedByTestCaseFactory(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslCommonParserListener ) ((PdslCommonParserListener)listener).exitTestSpecificationIsProcessedByTestCaseFactory(this);
		}
	}

	public final TestSpecificationIsProcessedByTestCaseFactoryContext testSpecificationIsProcessedByTestCaseFactory() throws RecognitionException {
		TestSpecificationIsProcessedByTestCaseFactoryContext _localctx = new TestSpecificationIsProcessedByTestCaseFactoryContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_testSpecificationIsProcessedByTestCaseFactory);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
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
		public TerminalNode POLYMORPHIC_DSL_TEST_EXECUTOR() { return getToken(PdslCommonParser.POLYMORPHIC_DSL_TEST_EXECUTOR, 0); }
		public PolymorphicDslTestExecutorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_polymorphicDslTestExecutor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslCommonParserListener ) ((PdslCommonParserListener)listener).enterPolymorphicDslTestExecutor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslCommonParserListener ) ((PdslCommonParserListener)listener).exitPolymorphicDslTestExecutor(this);
		}
	}

	public final PolymorphicDslTestExecutorContext polymorphicDslTestExecutor() throws RecognitionException {
		PolymorphicDslTestExecutorContext _localctx = new PolymorphicDslTestExecutorContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_polymorphicDslTestExecutor);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
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
		public TerminalNode TEST_RUN_RESULT_PRODUCED() { return getToken(PdslCommonParser.TEST_RUN_RESULT_PRODUCED, 0); }
		public TestRunResultProducedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testRunResultProduced; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PdslCommonParserListener ) ((PdslCommonParserListener)listener).enterTestRunResultProduced(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PdslCommonParserListener ) ((PdslCommonParserListener)listener).exitTestRunResultProduced(this);
		}
	}

	public final TestRunResultProducedContext testRunResultProduced() throws RecognitionException {
		TestRunResultProducedContext _localctx = new TestRunResultProducedContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_testRunResultProduced);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\17\27\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3"+
		"\6\2\2\7\2\4\6\b\n\2\2\2\21\2\f\3\2\2\2\4\16\3\2\2\2\6\20\3\2\2\2\b\22"+
		"\3\2\2\2\n\24\3\2\2\2\f\r\7\3\2\2\r\3\3\2\2\2\16\17\7\4\2\2\17\5\3\2\2"+
		"\2\20\21\7\5\2\2\21\7\3\2\2\2\22\23\7\6\2\2\23\t\3\2\2\2\24\25\7\b\2\2"+
		"\25\13\3\2\2\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}