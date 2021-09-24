// Generated from DialectParser.g4 by ANTLR 4.9.2
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
public class DialectParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		GRAMMAR_STEP=1, SUBGRAMMAR_STEP=2, DOCSTRING=3, DATA_ROW=4, GHERKIN_STEP_KEYWORD=5, 
		INT=6, QUOTED_TEXT_END=7, QUOTED_TEXT=8;
	public static final int
		RULE_grammarStep = 0, RULE_subgrammarStep = 1, RULE_polymorphicDslSyntaxCheck = 2, 
		RULE_polymorphicDslAllRules = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"grammarStep", "subgrammarStep", "polymorphicDslSyntaxCheck", "polymorphicDslAllRules"
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
			null, "GRAMMAR_STEP", "SUBGRAMMAR_STEP", "DOCSTRING", "DATA_ROW", "GHERKIN_STEP_KEYWORD", 
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
	public String getGrammarFileName() { return "DialectParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public DialectParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class GrammarStepContext extends ParserRuleContext {
		public TerminalNode GRAMMAR_STEP() { return getToken(DialectParser.GRAMMAR_STEP, 0); }
		public GrammarStepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grammarStep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DialectParserListener ) ((DialectParserListener)listener).enterGrammarStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DialectParserListener ) ((DialectParserListener)listener).exitGrammarStep(this);
		}
	}

	public final GrammarStepContext grammarStep() throws RecognitionException {
		GrammarStepContext _localctx = new GrammarStepContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_grammarStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8);
			match(GRAMMAR_STEP);
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

	public static class SubgrammarStepContext extends ParserRuleContext {
		public TerminalNode SUBGRAMMAR_STEP() { return getToken(DialectParser.SUBGRAMMAR_STEP, 0); }
		public SubgrammarStepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subgrammarStep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DialectParserListener ) ((DialectParserListener)listener).enterSubgrammarStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DialectParserListener ) ((DialectParserListener)listener).exitSubgrammarStep(this);
		}
	}

	public final SubgrammarStepContext subgrammarStep() throws RecognitionException {
		SubgrammarStepContext _localctx = new SubgrammarStepContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_subgrammarStep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			match(SUBGRAMMAR_STEP);
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

	public static class PolymorphicDslSyntaxCheckContext extends ParserRuleContext {
		public List<GrammarStepContext> grammarStep() {
			return getRuleContexts(GrammarStepContext.class);
		}
		public GrammarStepContext grammarStep(int i) {
			return getRuleContext(GrammarStepContext.class,i);
		}
		public List<SubgrammarStepContext> subgrammarStep() {
			return getRuleContexts(SubgrammarStepContext.class);
		}
		public SubgrammarStepContext subgrammarStep(int i) {
			return getRuleContext(SubgrammarStepContext.class,i);
		}
		public PolymorphicDslSyntaxCheckContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_polymorphicDslSyntaxCheck; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DialectParserListener ) ((DialectParserListener)listener).enterPolymorphicDslSyntaxCheck(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DialectParserListener ) ((DialectParserListener)listener).exitPolymorphicDslSyntaxCheck(this);
		}
	}

	public final PolymorphicDslSyntaxCheckContext polymorphicDslSyntaxCheck() throws RecognitionException {
		PolymorphicDslSyntaxCheckContext _localctx = new PolymorphicDslSyntaxCheckContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_polymorphicDslSyntaxCheck);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(15); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(12);
				grammarStep();
				setState(13);
				subgrammarStep();
				}
				}
				setState(17); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==GRAMMAR_STEP );
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
		public List<SubgrammarStepContext> subgrammarStep() {
			return getRuleContexts(SubgrammarStepContext.class);
		}
		public SubgrammarStepContext subgrammarStep(int i) {
			return getRuleContext(SubgrammarStepContext.class,i);
		}
		public PolymorphicDslAllRulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_polymorphicDslAllRules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof DialectParserListener ) ((DialectParserListener)listener).enterPolymorphicDslAllRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof DialectParserListener ) ((DialectParserListener)listener).exitPolymorphicDslAllRules(this);
		}
	}

	public final PolymorphicDslAllRulesContext polymorphicDslAllRules() throws RecognitionException {
		PolymorphicDslAllRulesContext _localctx = new PolymorphicDslAllRulesContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_polymorphicDslAllRules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(19);
				subgrammarStep();
				}
				}
				setState(22); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==SUBGRAMMAR_STEP );
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\n\33\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\3\2\3\2\3\3\3\3\3\4\3\4\3\4\6\4\22\n\4\r\4\16\4\23"+
		"\3\5\6\5\27\n\5\r\5\16\5\30\3\5\2\2\6\2\4\6\b\2\2\2\30\2\n\3\2\2\2\4\f"+
		"\3\2\2\2\6\21\3\2\2\2\b\26\3\2\2\2\n\13\7\3\2\2\13\3\3\2\2\2\f\r\7\4\2"+
		"\2\r\5\3\2\2\2\16\17\5\2\2\2\17\20\5\4\3\2\20\22\3\2\2\2\21\16\3\2\2\2"+
		"\22\23\3\2\2\2\23\21\3\2\2\2\23\24\3\2\2\2\24\7\3\2\2\2\25\27\5\4\3\2"+
		"\26\25\3\2\2\2\27\30\3\2\2\2\30\26\3\2\2\2\30\31\3\2\2\2\31\t\3\2\2\2"+
		"\4\23\30";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}