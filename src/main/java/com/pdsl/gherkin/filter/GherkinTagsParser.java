// Generated from GherkinTags.g4 by ANTLR 4.9
package com.pdsl.gherkin.filter;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GherkinTagsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TAG=1, WS=2, AND=3, OR=4, NOT=5, L_PAREN=6, R_PAREN=7;
	public static final int
		RULE_not = 0, RULE_and = 1, RULE_or = 2, RULE_expr = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"not", "and", "or", "expr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, "'('", "')'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "TAG", "WS", "AND", "OR", "NOT", "L_PAREN", "R_PAREN"
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
	public String getGrammarFileName() { return "GherkinTags.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GherkinTagsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class NotContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode NOT() { return getToken(GherkinTagsParser.NOT, 0); }
		public NotContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_not; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinTagsListener ) ((GherkinTagsListener)listener).enterNot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinTagsListener ) ((GherkinTagsListener)listener).exitNot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GherkinTagsVisitor ) return ((GherkinTagsVisitor<? extends T>)visitor).visitNot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NotContext not() throws RecognitionException {
		NotContext _localctx = new NotContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_not);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(9);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(8);
				match(NOT);
				}
			}

			setState(11);
			expr();
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

	public static class AndContext extends ParserRuleContext {
		public List<NotContext> not() {
			return getRuleContexts(NotContext.class);
		}
		public NotContext not(int i) {
			return getRuleContext(NotContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(GherkinTagsParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(GherkinTagsParser.AND, i);
		}
		public AndContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_and; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinTagsListener ) ((GherkinTagsListener)listener).enterAnd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinTagsListener ) ((GherkinTagsListener)listener).exitAnd(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GherkinTagsVisitor ) return ((GherkinTagsVisitor<? extends T>)visitor).visitAnd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AndContext and() throws RecognitionException {
		AndContext _localctx = new AndContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_and);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(13);
			not();
			setState(18);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(14);
				match(AND);
				setState(15);
				not();
				}
				}
				setState(20);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class OrContext extends ParserRuleContext {
		public List<AndContext> and() {
			return getRuleContexts(AndContext.class);
		}
		public AndContext and(int i) {
			return getRuleContext(AndContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(GherkinTagsParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(GherkinTagsParser.OR, i);
		}
		public TerminalNode EOF() { return getToken(GherkinTagsParser.EOF, 0); }
		public OrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_or; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinTagsListener ) ((GherkinTagsListener)listener).enterOr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinTagsListener ) ((GherkinTagsListener)listener).exitOr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GherkinTagsVisitor ) return ((GherkinTagsVisitor<? extends T>)visitor).visitOr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrContext or() throws RecognitionException {
		OrContext _localctx = new OrContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_or);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			and();
			setState(26);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OR) {
				{
				{
				setState(22);
				match(OR);
				setState(23);
				and();
				}
				}
				setState(28);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(30);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EOF) {
				{
				setState(29);
				match(EOF);
				}
			}

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

	public static class ExprContext extends ParserRuleContext {
		public TerminalNode TAG() { return getToken(GherkinTagsParser.TAG, 0); }
		public TerminalNode L_PAREN() { return getToken(GherkinTagsParser.L_PAREN, 0); }
		public OrContext or() {
			return getRuleContext(OrContext.class,0);
		}
		public TerminalNode R_PAREN() { return getToken(GherkinTagsParser.R_PAREN, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinTagsListener ) ((GherkinTagsListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinTagsListener ) ((GherkinTagsListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GherkinTagsVisitor ) return ((GherkinTagsVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_expr);
		try {
			setState(37);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TAG:
				enterOuterAlt(_localctx, 1);
				{
				setState(32);
				match(TAG);
				}
				break;
			case L_PAREN:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(33);
				match(L_PAREN);
				setState(34);
				or();
				setState(35);
				match(R_PAREN);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\t*\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\3\2\5\2\f\n\2\3\2\3\2\3\3\3\3\3\3\7\3\23\n\3\f\3\16"+
		"\3\26\13\3\3\4\3\4\3\4\7\4\33\n\4\f\4\16\4\36\13\4\3\4\5\4!\n\4\3\5\3"+
		"\5\3\5\3\5\3\5\5\5(\n\5\3\5\2\2\6\2\4\6\b\2\2\2*\2\13\3\2\2\2\4\17\3\2"+
		"\2\2\6\27\3\2\2\2\b\'\3\2\2\2\n\f\7\7\2\2\13\n\3\2\2\2\13\f\3\2\2\2\f"+
		"\r\3\2\2\2\r\16\5\b\5\2\16\3\3\2\2\2\17\24\5\2\2\2\20\21\7\5\2\2\21\23"+
		"\5\2\2\2\22\20\3\2\2\2\23\26\3\2\2\2\24\22\3\2\2\2\24\25\3\2\2\2\25\5"+
		"\3\2\2\2\26\24\3\2\2\2\27\34\5\4\3\2\30\31\7\6\2\2\31\33\5\4\3\2\32\30"+
		"\3\2\2\2\33\36\3\2\2\2\34\32\3\2\2\2\34\35\3\2\2\2\35 \3\2\2\2\36\34\3"+
		"\2\2\2\37!\7\2\2\3 \37\3\2\2\2 !\3\2\2\2!\7\3\2\2\2\"(\7\3\2\2#$\7\b\2"+
		"\2$%\5\6\4\2%&\7\t\2\2&(\3\2\2\2\'\"\3\2\2\2\'#\3\2\2\2(\t\3\2\2\2\7\13"+
		"\24\34 \'";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}