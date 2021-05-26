// Generated from PolymorphicDslRegistryParser.g4 by ANTLR 4.9
package com.google.pdsl.grammars;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PolymorphicDslRegistryParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SUM=1, MINUS=2, PRODUCT=3, NUMBER=4, WS=5, END_OF_FILE=6, HELLO=7, WORLD=8;
	public static final int
		RULE_polymorphicDslAllRules = 0, RULE_mathematical_expression = 1, RULE_helloWorld = 2;
	private static String[] makeRuleNames() {
		return new String[] {
			"polymorphicDslAllRules", "mathematical_expression", "helloWorld"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, "'Hello, '", "'world!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SUM", "MINUS", "PRODUCT", "NUMBER", "WS", "END_OF_FILE", "HELLO", 
			"WORLD"
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
	public String getGrammarFileName() { return "PolymorphicDslRegistryParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PolymorphicDslRegistryParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class PolymorphicDslAllRulesContext extends ParserRuleContext {
		public List<Mathematical_expressionContext> mathematical_expression() {
			return getRuleContexts(Mathematical_expressionContext.class);
		}
		public Mathematical_expressionContext mathematical_expression(int i) {
			return getRuleContext(Mathematical_expressionContext.class,i);
		}
		public List<HelloWorldContext> helloWorld() {
			return getRuleContexts(HelloWorldContext.class);
		}
		public HelloWorldContext helloWorld(int i) {
			return getRuleContext(HelloWorldContext.class,i);
		}
		public PolymorphicDslAllRulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_polymorphicDslAllRules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PolymorphicDslRegistryParserListener ) ((PolymorphicDslRegistryParserListener)listener).enterPolymorphicDslAllRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PolymorphicDslRegistryParserListener ) ((PolymorphicDslRegistryParserListener)listener).exitPolymorphicDslAllRules(this);
		}
	}

	public final PolymorphicDslAllRulesContext polymorphicDslAllRules() throws RecognitionException {
		PolymorphicDslAllRulesContext _localctx = new PolymorphicDslAllRulesContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_polymorphicDslAllRules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(8);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case SUM:
				case MINUS:
				case PRODUCT:
					{
					setState(6);
					mathematical_expression();
					}
					break;
				case HELLO:
					{
					setState(7);
					helloWorld();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(10); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUM) | (1L << MINUS) | (1L << PRODUCT) | (1L << HELLO))) != 0) );
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

	public static class Mathematical_expressionContext extends ParserRuleContext {
		public TerminalNode SUM() { return getToken(PolymorphicDslRegistryParser.SUM, 0); }
		public TerminalNode MINUS() { return getToken(PolymorphicDslRegistryParser.MINUS, 0); }
		public TerminalNode PRODUCT() { return getToken(PolymorphicDslRegistryParser.PRODUCT, 0); }
		public Mathematical_expressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mathematical_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PolymorphicDslRegistryParserListener ) ((PolymorphicDslRegistryParserListener)listener).enterMathematical_expression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PolymorphicDslRegistryParserListener ) ((PolymorphicDslRegistryParserListener)listener).exitMathematical_expression(this);
		}
	}

	public final Mathematical_expressionContext mathematical_expression() throws RecognitionException {
		Mathematical_expressionContext _localctx = new Mathematical_expressionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_mathematical_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(12);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SUM) | (1L << MINUS) | (1L << PRODUCT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class HelloWorldContext extends ParserRuleContext {
		public TerminalNode HELLO() { return getToken(PolymorphicDslRegistryParser.HELLO, 0); }
		public TerminalNode WORLD() { return getToken(PolymorphicDslRegistryParser.WORLD, 0); }
		public List<TerminalNode> WS() { return getTokens(PolymorphicDslRegistryParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(PolymorphicDslRegistryParser.WS, i);
		}
		public TerminalNode EOF() { return getToken(PolymorphicDslRegistryParser.EOF, 0); }
		public HelloWorldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_helloWorld; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PolymorphicDslRegistryParserListener ) ((PolymorphicDslRegistryParserListener)listener).enterHelloWorld(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PolymorphicDslRegistryParserListener ) ((PolymorphicDslRegistryParserListener)listener).exitHelloWorld(this);
		}
	}

	public final HelloWorldContext helloWorld() throws RecognitionException {
		HelloWorldContext _localctx = new HelloWorldContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_helloWorld);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			match(HELLO);
			setState(15);
			match(WORLD);
			setState(19);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WS) {
				{
				{
				setState(16);
				match(WS);
				}
				}
				setState(21);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(23);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(22);
				match(EOF);
				}
				break;
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\n\34\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\3\2\3\2\6\2\13\n\2\r\2\16\2\f\3\3\3\3\3\4\3\4\3\4\7\4\24"+
		"\n\4\f\4\16\4\27\13\4\3\4\5\4\32\n\4\3\4\2\2\5\2\4\6\2\3\3\2\3\5\2\34"+
		"\2\n\3\2\2\2\4\16\3\2\2\2\6\20\3\2\2\2\b\13\5\4\3\2\t\13\5\6\4\2\n\b\3"+
		"\2\2\2\n\t\3\2\2\2\13\f\3\2\2\2\f\n\3\2\2\2\f\r\3\2\2\2\r\3\3\2\2\2\16"+
		"\17\t\2\2\2\17\5\3\2\2\2\20\21\7\t\2\2\21\25\7\n\2\2\22\24\7\7\2\2\23"+
		"\22\3\2\2\2\24\27\3\2\2\2\25\23\3\2\2\2\25\26\3\2\2\2\26\31\3\2\2\2\27"+
		"\25\3\2\2\2\30\32\7\2\2\3\31\30\3\2\2\2\31\32\3\2\2\2\32\7\3\2\2\2\6\n"+
		"\f\25\31";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}