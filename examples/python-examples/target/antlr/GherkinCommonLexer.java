// Generated from ./tests/resources/antlr4/GherkinCommonLexer.g4 by ANTLR 4.9.1
package com.pdsl.python;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GherkinCommonLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		GHERKIN_STEP_KEYWORD=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"WS", "END", "AND", "BUT", "WILD", "GIVEN", "WHEN", "THEN", "GHERKIN_STEP_KEYWORD", 
			"NEWLINE"
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
			null, "GHERKIN_STEP_KEYWORD"
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


	public GherkinCommonLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "GherkinCommonLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\3^\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\3\7\3\33\n\3\f\3\16\3\36\13\3\3\3\6\3!\n\3\r\3\16\3\"\3"+
		"\3\5\3&\n\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7=\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bF\n"+
		"\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tO\n\t\3\n\7\nR\n\n\f\n\16\nU\13\n\3"+
		"\n\3\n\3\n\3\n\5\n[\n\n\3\13\3\13\2\2\f\3\2\5\2\7\2\t\2\13\2\r\2\17\2"+
		"\21\2\23\3\25\2\3\2\4\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2a\2\23\3\2\2\2"+
		"\3\27\3\2\2\2\5\34\3\2\2\2\7\'\3\2\2\2\t,\3\2\2\2\13\61\3\2\2\2\r<\3\2"+
		"\2\2\17E\3\2\2\2\21N\3\2\2\2\23S\3\2\2\2\25\\\3\2\2\2\27\30\t\2\2\2\30"+
		"\4\3\2\2\2\31\33\5\3\2\2\32\31\3\2\2\2\33\36\3\2\2\2\34\32\3\2\2\2\34"+
		"\35\3\2\2\2\35%\3\2\2\2\36\34\3\2\2\2\37!\t\3\2\2 \37\3\2\2\2!\"\3\2\2"+
		"\2\" \3\2\2\2\"#\3\2\2\2#&\3\2\2\2$&\7\2\2\3% \3\2\2\2%$\3\2\2\2&\6\3"+
		"\2\2\2\'(\7C\2\2()\7p\2\2)*\7f\2\2*+\7\"\2\2+\b\3\2\2\2,-\7D\2\2-.\7w"+
		"\2\2./\7v\2\2/\60\7\"\2\2\60\n\3\2\2\2\61\62\7,\2\2\62\63\7\"\2\2\63\f"+
		"\3\2\2\2\64\65\7I\2\2\65\66\7k\2\2\66\67\7x\2\2\678\7g\2\289\7p\2\29="+
		"\7\"\2\2:=\5\7\4\2;=\5\t\5\2<\64\3\2\2\2<:\3\2\2\2<;\3\2\2\2=\16\3\2\2"+
		"\2>?\7Y\2\2?@\7j\2\2@A\7g\2\2AB\7p\2\2BF\7\"\2\2CF\5\7\4\2DF\5\t\5\2E"+
		">\3\2\2\2EC\3\2\2\2ED\3\2\2\2F\20\3\2\2\2GH\7V\2\2HI\7j\2\2IJ\7g\2\2J"+
		"K\7p\2\2KO\7\"\2\2LO\5\7\4\2MO\5\t\5\2NG\3\2\2\2NL\3\2\2\2NM\3\2\2\2O"+
		"\22\3\2\2\2PR\5\3\2\2QP\3\2\2\2RU\3\2\2\2SQ\3\2\2\2ST\3\2\2\2TZ\3\2\2"+
		"\2US\3\2\2\2V[\5\r\7\2W[\5\17\b\2X[\5\21\t\2Y[\5\13\6\2ZV\3\2\2\2ZW\3"+
		"\2\2\2ZX\3\2\2\2ZY\3\2\2\2[\24\3\2\2\2\\]\t\3\2\2]\26\3\2\2\2\13\2\34"+
		"\"%<ENSZ\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}