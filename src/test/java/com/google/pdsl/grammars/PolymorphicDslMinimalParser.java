// Generated from PolymorphicDslMinimalParser.g4 by ANTLR 4.9
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
public class PolymorphicDslMinimalParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		MINIMAL=1, NUMBER=2, WS=3, END_OF_FILE=4;
	public static final int
		RULE_polymorphicDslAllRules = 0, RULE_minimal = 1;
	private static String[] makeRuleNames() {
		return new String[] {
			"polymorphicDslAllRules", "minimal"
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
			null, "MINIMAL", "NUMBER", "WS", "END_OF_FILE"
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
	public String getGrammarFileName() { return "PolymorphicDslMinimalParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PolymorphicDslMinimalParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class PolymorphicDslAllRulesContext extends ParserRuleContext {
		public MinimalContext minimal() {
			return getRuleContext(MinimalContext.class,0);
		}
		public PolymorphicDslAllRulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_polymorphicDslAllRules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PolymorphicDslMinimalParserListener ) ((PolymorphicDslMinimalParserListener)listener).enterPolymorphicDslAllRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PolymorphicDslMinimalParserListener ) ((PolymorphicDslMinimalParserListener)listener).exitPolymorphicDslAllRules(this);
		}
	}

	public final PolymorphicDslAllRulesContext polymorphicDslAllRules() throws RecognitionException {
		PolymorphicDslAllRulesContext _localctx = new PolymorphicDslAllRulesContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_polymorphicDslAllRules);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(4);
			minimal();
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

	public static class MinimalContext extends ParserRuleContext {
		public TerminalNode MINIMAL() { return getToken(PolymorphicDslMinimalParser.MINIMAL, 0); }
		public MinimalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_minimal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PolymorphicDslMinimalParserListener ) ((PolymorphicDslMinimalParserListener)listener).enterMinimal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PolymorphicDslMinimalParserListener ) ((PolymorphicDslMinimalParserListener)listener).exitMinimal(this);
		}
	}

	public final MinimalContext minimal() throws RecognitionException {
		MinimalContext _localctx = new MinimalContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_minimal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(6);
			match(MINIMAL);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\6\13\4\2\t\2\4\3"+
		"\t\3\3\2\3\2\3\3\3\3\3\3\2\2\4\2\4\2\2\2\b\2\6\3\2\2\2\4\b\3\2\2\2\6\7"+
		"\5\4\3\2\7\3\3\2\2\2\b\t\7\3\2\2\t\5\3\2\2\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}