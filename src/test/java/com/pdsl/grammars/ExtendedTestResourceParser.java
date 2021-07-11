// Generated from ExtendedTestResourceParser.g4 by ANTLR 4.9
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
public class ExtendedTestResourceParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TEST_RESOURCE_VALIDITY=1, DOCSTRING=2, DATA_ROW=3, GHERKIN_STEP_KEYWORD=4, 
		INT=5, TEXT_IN_DOUBLE_QUOTES=6, END_QUOTE=7, DOC_STRING=8;
	public static final int
		RULE_testResourceValidity = 0;
	private static String[] makeRuleNames() {
		return new String[] {
			"testResourceValidity"
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
			null, "TEST_RESOURCE_VALIDITY", "DOCSTRING", "DATA_ROW", "GHERKIN_STEP_KEYWORD", 
			"INT", "TEXT_IN_DOUBLE_QUOTES", "END_QUOTE", "DOC_STRING"
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
	public String getGrammarFileName() { return "ExtendedTestResourceParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExtendedTestResourceParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class TestResourceValidityContext extends ParserRuleContext {
		public TerminalNode TEST_RESOURCE_VALIDITY() { return getToken(ExtendedTestResourceParser.TEST_RESOURCE_VALIDITY, 0); }
		public TestResourceValidityContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_testResourceValidity; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExtendedTestResourceParserListener ) ((ExtendedTestResourceParserListener)listener).enterTestResourceValidity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExtendedTestResourceParserListener ) ((ExtendedTestResourceParserListener)listener).exitTestResourceValidity(this);
		}
	}

	public final TestResourceValidityContext testResourceValidity() throws RecognitionException {
		TestResourceValidityContext _localctx = new TestResourceValidityContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_testResourceValidity);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(2);
			match(TEST_RESOURCE_VALIDITY);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\n\7\4\2\t\2\3\2\3"+
		"\2\3\2\2\2\3\2\2\2\2\5\2\4\3\2\2\2\4\5\7\3\2\2\5\3\3\2\2\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}