// Generated from PolymorphicDslBetaParser.g4 by ANTLR 4.9
package com.pdsl.grammars;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PolymorphicDslBetaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		HELLO=1, WORLD=2, END_OF_LINE=3;
	public static final int
		RULE_polymorphicDslAllRules = 0, RULE_helloWorld = 1;
	private static String[] makeRuleNames() {
		return new String[] {
			"polymorphicDslAllRules", "helloWorld"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Hello, '", "'world!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "HELLO", "WORLD", "END_OF_LINE"
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
	public String getGrammarFileName() { return "PolymorphicDslBetaParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PolymorphicDslBetaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class PolymorphicDslAllRulesContext extends ParserRuleContext {
		public HelloWorldContext helloWorld() {
			return getRuleContext(HelloWorldContext.class,0);
		}
		public TerminalNode EOF() { return getToken(PolymorphicDslBetaParser.EOF, 0); }
		public PolymorphicDslAllRulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_polymorphicDslAllRules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PolymorphicDslBetaParserListener ) ((PolymorphicDslBetaParserListener)listener).enterPolymorphicDslAllRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PolymorphicDslBetaParserListener ) ((PolymorphicDslBetaParserListener)listener).exitPolymorphicDslAllRules(this);
		}
	}

	public final PolymorphicDslAllRulesContext polymorphicDslAllRules() throws RecognitionException {
		PolymorphicDslAllRulesContext _localctx = new PolymorphicDslAllRulesContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_polymorphicDslAllRules);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(4);
			helloWorld();
			setState(6);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(5);
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

	public static class HelloWorldContext extends ParserRuleContext {
		public TerminalNode HELLO() { return getToken(PolymorphicDslBetaParser.HELLO, 0); }
		public TerminalNode WORLD() { return getToken(PolymorphicDslBetaParser.WORLD, 0); }
		public HelloWorldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_helloWorld; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PolymorphicDslBetaParserListener ) ((PolymorphicDslBetaParserListener)listener).enterHelloWorld(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PolymorphicDslBetaParserListener ) ((PolymorphicDslBetaParserListener)listener).exitHelloWorld(this);
		}
	}

	public final HelloWorldContext helloWorld() throws RecognitionException {
		HelloWorldContext _localctx = new HelloWorldContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_helloWorld);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8);
			match(HELLO);
			setState(9);
			match(WORLD);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\5\16\4\2\t\2\4\3"+
		"\t\3\3\2\3\2\5\2\t\n\2\3\3\3\3\3\3\3\3\2\2\4\2\4\2\2\2\f\2\6\3\2\2\2\4"+
		"\n\3\2\2\2\6\b\5\4\3\2\7\t\7\2\2\3\b\7\3\2\2\2\b\t\3\2\2\2\t\3\3\2\2\2"+
		"\n\13\7\3\2\2\13\f\7\4\2\2\f\5\3\2\2\2\3\b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}