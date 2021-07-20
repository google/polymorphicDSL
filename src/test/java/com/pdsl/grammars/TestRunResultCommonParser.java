// Generated from TestRunResultCommonParser.g4 by ANTLR 4.9
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
public class TestRunResultCommonParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		THEN_THE_TEST_RUN_RESULTS_HAVE_N_TESTS_START=1, FAILING_TESTS_END=2, PASSING_TESTS_END=3, 
		INTEGER_VALUE=4, DOCSTRING=5, DATA_ROW=6, GHERKIN_STEP_KEYWORD=7, INT=8, 
		QUOTED_TEXT_END=9, QUOTED_TEXT=10;
	public static final int
		RULE_integerValue = 0, RULE_thenTheTestRunResultsHaveSpecifiedPassingTests = 1, 
		RULE_thenTheTestRunResultsHaveSpecifiedFailingTests = 2;
	private static String[] makeRuleNames() {
		return new String[] {
			"integerValue", "thenTheTestRunResultsHaveSpecifiedPassingTests", "thenTheTestRunResultsHaveSpecifiedFailingTests"
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
			null, "THEN_THE_TEST_RUN_RESULTS_HAVE_N_TESTS_START", "FAILING_TESTS_END", 
			"PASSING_TESTS_END", "INTEGER_VALUE", "DOCSTRING", "DATA_ROW", "GHERKIN_STEP_KEYWORD", 
			"INT", "QUOTED_TEXT_END", "QUOTED_TEXT"
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
	public String getGrammarFileName() { return "TestRunResultCommonParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TestRunResultCommonParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class IntegerValueContext extends ParserRuleContext {
		public TerminalNode INTEGER_VALUE() { return getToken(TestRunResultCommonParser.INTEGER_VALUE, 0); }
		public IntegerValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultCommonParserListener ) ((TestRunResultCommonParserListener)listener).enterIntegerValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultCommonParserListener ) ((TestRunResultCommonParserListener)listener).exitIntegerValue(this);
		}
	}

	public final IntegerValueContext integerValue() throws RecognitionException {
		IntegerValueContext _localctx = new IntegerValueContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_integerValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(6);
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

	public static class ThenTheTestRunResultsHaveSpecifiedPassingTestsContext extends ParserRuleContext {
		public TerminalNode THEN_THE_TEST_RUN_RESULTS_HAVE_N_TESTS_START() { return getToken(TestRunResultCommonParser.THEN_THE_TEST_RUN_RESULTS_HAVE_N_TESTS_START, 0); }
		public IntegerValueContext integerValue() {
			return getRuleContext(IntegerValueContext.class,0);
		}
		public TerminalNode PASSING_TESTS_END() { return getToken(TestRunResultCommonParser.PASSING_TESTS_END, 0); }
		public ThenTheTestRunResultsHaveSpecifiedPassingTestsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thenTheTestRunResultsHaveSpecifiedPassingTests; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultCommonParserListener ) ((TestRunResultCommonParserListener)listener).enterThenTheTestRunResultsHaveSpecifiedPassingTests(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultCommonParserListener ) ((TestRunResultCommonParserListener)listener).exitThenTheTestRunResultsHaveSpecifiedPassingTests(this);
		}
	}

	public final ThenTheTestRunResultsHaveSpecifiedPassingTestsContext thenTheTestRunResultsHaveSpecifiedPassingTests() throws RecognitionException {
		ThenTheTestRunResultsHaveSpecifiedPassingTestsContext _localctx = new ThenTheTestRunResultsHaveSpecifiedPassingTestsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_thenTheTestRunResultsHaveSpecifiedPassingTests);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8);
			match(THEN_THE_TEST_RUN_RESULTS_HAVE_N_TESTS_START);
			setState(9);
			integerValue();
			setState(10);
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
		public TerminalNode THEN_THE_TEST_RUN_RESULTS_HAVE_N_TESTS_START() { return getToken(TestRunResultCommonParser.THEN_THE_TEST_RUN_RESULTS_HAVE_N_TESTS_START, 0); }
		public IntegerValueContext integerValue() {
			return getRuleContext(IntegerValueContext.class,0);
		}
		public TerminalNode PASSING_TESTS_END() { return getToken(TestRunResultCommonParser.PASSING_TESTS_END, 0); }
		public ThenTheTestRunResultsHaveSpecifiedFailingTestsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_thenTheTestRunResultsHaveSpecifiedFailingTests; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultCommonParserListener ) ((TestRunResultCommonParserListener)listener).enterThenTheTestRunResultsHaveSpecifiedFailingTests(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestRunResultCommonParserListener ) ((TestRunResultCommonParserListener)listener).exitThenTheTestRunResultsHaveSpecifiedFailingTests(this);
		}
	}

	public final ThenTheTestRunResultsHaveSpecifiedFailingTestsContext thenTheTestRunResultsHaveSpecifiedFailingTests() throws RecognitionException {
		ThenTheTestRunResultsHaveSpecifiedFailingTestsContext _localctx = new ThenTheTestRunResultsHaveSpecifiedFailingTestsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_thenTheTestRunResultsHaveSpecifiedFailingTests);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(12);
			match(THEN_THE_TEST_RUN_RESULTS_HAVE_N_TESTS_START);
			setState(13);
			integerValue();
			setState(14);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\f\23\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\2\2\5\2\4\6\2"+
		"\2\2\17\2\b\3\2\2\2\4\n\3\2\2\2\6\16\3\2\2\2\b\t\7\6\2\2\t\3\3\2\2\2\n"+
		"\13\7\3\2\2\13\f\5\2\2\2\f\r\7\5\2\2\r\5\3\2\2\2\16\17\7\3\2\2\17\20\5"+
		"\2\2\2\20\21\7\5\2\2\21\7\3\2\2\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}