// Generated from GherkinTags.g4 by ANTLR 4.9
package com.pdsl.gherkin.filter;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GherkinTagsLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TAG=1, WS=2, AND=3, OR=4, NOT=5, L_PAREN=6, R_PAREN=7;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"A", "N", "D", "O", "T", "R", "TAG", "WS", "AND", "OR", "NOT", "L_PAREN", 
			"R_PAREN"
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


	public GherkinTagsLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "GherkinTags.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\tE\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6"+
		"\3\7\3\7\3\b\3\b\6\b,\n\b\r\b\16\b-\3\t\6\t\61\n\t\r\t\16\t\62\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\16\3\16\2\2"+
		"\17\3\2\5\2\7\2\t\2\13\2\r\2\17\3\21\4\23\5\25\6\27\7\31\b\33\t\3\2\n"+
		"\4\2CCcc\4\2PPpp\4\2FFff\4\2QQqq\4\2VVvv\4\2TTtt\5\2\"\"++BB\4\2\13\13"+
		"\"\"\2@\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2"+
		"\2\2\31\3\2\2\2\2\33\3\2\2\2\3\35\3\2\2\2\5\37\3\2\2\2\7!\3\2\2\2\t#\3"+
		"\2\2\2\13%\3\2\2\2\r\'\3\2\2\2\17)\3\2\2\2\21\60\3\2\2\2\23\66\3\2\2\2"+
		"\25:\3\2\2\2\27=\3\2\2\2\31A\3\2\2\2\33C\3\2\2\2\35\36\t\2\2\2\36\4\3"+
		"\2\2\2\37 \t\3\2\2 \6\3\2\2\2!\"\t\4\2\2\"\b\3\2\2\2#$\t\5\2\2$\n\3\2"+
		"\2\2%&\t\6\2\2&\f\3\2\2\2\'(\t\7\2\2(\16\3\2\2\2)+\7B\2\2*,\n\b\2\2+*"+
		"\3\2\2\2,-\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\20\3\2\2\2/\61\t\t\2\2\60/\3\2"+
		"\2\2\61\62\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63\64\3\2\2\2\64\65\b\t"+
		"\2\2\65\22\3\2\2\2\66\67\5\3\2\2\678\5\5\3\289\5\7\4\29\24\3\2\2\2:;\5"+
		"\t\5\2;<\5\r\7\2<\26\3\2\2\2=>\5\5\3\2>?\5\t\5\2?@\5\13\6\2@\30\3\2\2"+
		"\2AB\7*\2\2B\32\3\2\2\2CD\7+\2\2D\34\3\2\2\2\5\2-\62\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}