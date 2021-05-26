// Generated from RegistryLexer.g4 by ANTLR 4.9
package com.pdsl.grammars;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RegistryLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SUM=1, MINUS=2, PRODUCT=3, NUMBER=4, WS=5, END_OF_FILE=6, HELLO=7, WORLD=8, 
		END_OF_LINE=9;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"ADD", "SUBTRACT", "MULTIPLY", "DIVIDE", "SUM", "MINUS", "PRODUCT", "NUMBER", 
			"DECIMAL", "WS", "END_OF_FILE", "HELLO", "WORLD", "END_OF_LINE"
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
			"WORLD", "END_OF_LINE"
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


	public RegistryLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "RegistryLexer.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\13\u00ae\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\3\3\3\3\4\3\4\3"+
		"\5\3\5\3\6\7\6)\n\6\f\6\16\6,\13\6\3\6\3\6\7\6\60\n\6\f\6\16\6\63\13\6"+
		"\3\6\3\6\7\6\67\n\6\f\6\16\6:\13\6\3\6\3\6\7\6>\n\6\f\6\16\6A\13\6\3\6"+
		"\5\6D\n\6\3\6\5\6G\n\6\3\7\7\7J\n\7\f\7\16\7M\13\7\3\7\3\7\3\7\3\7\7\7"+
		"S\n\7\f\7\16\7V\13\7\3\7\3\7\7\7Z\n\7\f\7\16\7]\13\7\3\7\5\7`\n\7\3\7"+
		"\5\7c\n\7\3\b\7\bf\n\b\f\b\16\bi\13\b\3\b\3\b\7\bm\n\b\f\b\16\bp\13\b"+
		"\3\b\3\b\7\bt\n\b\f\b\16\bw\13\b\3\b\3\b\7\b{\n\b\f\b\16\b~\13\b\3\b\5"+
		"\b\u0081\n\b\3\b\5\b\u0084\n\b\3\t\6\t\u0087\n\t\r\t\16\t\u0088\3\t\5"+
		"\t\u008c\n\t\3\n\3\n\6\n\u0090\n\n\r\n\16\n\u0091\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\17\3\17\3\17\3\17\2\2\20\3\2\5\2\7\2\t\2\13\3\r\4\17\5"+
		"\21\6\23\2\25\7\27\b\31\t\33\n\35\13\3\2\5\4\2\f\f\17\17\3\2\62;\5\2\13"+
		"\f\17\17\"\"\2\u00bc\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\3"+
		"\37\3\2\2\2\5!\3\2\2\2\7#\3\2\2\2\t%\3\2\2\2\13*\3\2\2\2\rK\3\2\2\2\17"+
		"g\3\2\2\2\21\u0086\3\2\2\2\23\u008d\3\2\2\2\25\u0093\3\2\2\2\27\u0097"+
		"\3\2\2\2\31\u009b\3\2\2\2\33\u00a3\3\2\2\2\35\u00aa\3\2\2\2\37 \7-\2\2"+
		" \4\3\2\2\2!\"\7/\2\2\"\6\3\2\2\2#$\7,\2\2$\b\3\2\2\2%&\7\61\2\2&\n\3"+
		"\2\2\2\')\5\25\13\2(\'\3\2\2\2),\3\2\2\2*(\3\2\2\2*+\3\2\2\2+-\3\2\2\2"+
		",*\3\2\2\2-\61\5\21\t\2.\60\5\25\13\2/.\3\2\2\2\60\63\3\2\2\2\61/\3\2"+
		"\2\2\61\62\3\2\2\2\62\64\3\2\2\2\63\61\3\2\2\2\648\5\3\2\2\65\67\5\25"+
		"\13\2\66\65\3\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2\2\29;\3\2\2\2:8\3\2"+
		"\2\2;?\5\21\t\2<>\5\25\13\2=<\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@C"+
		"\3\2\2\2A?\3\2\2\2BD\t\2\2\2CB\3\2\2\2CD\3\2\2\2DF\3\2\2\2EG\7\2\2\3F"+
		"E\3\2\2\2FG\3\2\2\2G\f\3\2\2\2HJ\5\25\13\2IH\3\2\2\2JM\3\2\2\2KI\3\2\2"+
		"\2KL\3\2\2\2LN\3\2\2\2MK\3\2\2\2NO\5\21\t\2OP\5\25\13\2PT\5\5\3\2QS\5"+
		"\25\13\2RQ\3\2\2\2SV\3\2\2\2TR\3\2\2\2TU\3\2\2\2UW\3\2\2\2VT\3\2\2\2W"+
		"[\5\21\t\2XZ\5\25\13\2YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\_\3\2"+
		"\2\2][\3\2\2\2^`\t\2\2\2_^\3\2\2\2_`\3\2\2\2`b\3\2\2\2ac\7\2\2\3ba\3\2"+
		"\2\2bc\3\2\2\2c\16\3\2\2\2df\5\25\13\2ed\3\2\2\2fi\3\2\2\2ge\3\2\2\2g"+
		"h\3\2\2\2hj\3\2\2\2ig\3\2\2\2jn\5\21\t\2km\5\25\13\2lk\3\2\2\2mp\3\2\2"+
		"\2nl\3\2\2\2no\3\2\2\2oq\3\2\2\2pn\3\2\2\2qu\5\7\4\2rt\5\25\13\2sr\3\2"+
		"\2\2tw\3\2\2\2us\3\2\2\2uv\3\2\2\2vx\3\2\2\2wu\3\2\2\2x|\5\21\t\2y{\5"+
		"\25\13\2zy\3\2\2\2{~\3\2\2\2|z\3\2\2\2|}\3\2\2\2}\u0080\3\2\2\2~|\3\2"+
		"\2\2\177\u0081\t\2\2\2\u0080\177\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0083"+
		"\3\2\2\2\u0082\u0084\7\2\2\3\u0083\u0082\3\2\2\2\u0083\u0084\3\2\2\2\u0084"+
		"\20\3\2\2\2\u0085\u0087\t\3\2\2\u0086\u0085\3\2\2\2\u0087\u0088\3\2\2"+
		"\2\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008b\3\2\2\2\u008a\u008c"+
		"\5\23\n\2\u008b\u008a\3\2\2\2\u008b\u008c\3\2\2\2\u008c\22\3\2\2\2\u008d"+
		"\u008f\7\60\2\2\u008e\u0090\t\3\2\2\u008f\u008e\3\2\2\2\u0090\u0091\3"+
		"\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\24\3\2\2\2\u0093"+
		"\u0094\t\4\2\2\u0094\u0095\3\2\2\2\u0095\u0096\b\13\2\2\u0096\26\3\2\2"+
		"\2\u0097\u0098\7\2\2\3\u0098\u0099\3\2\2\2\u0099\u009a\b\f\2\2\u009a\30"+
		"\3\2\2\2\u009b\u009c\7J\2\2\u009c\u009d\7g\2\2\u009d\u009e\7n\2\2\u009e"+
		"\u009f\7n\2\2\u009f\u00a0\7q\2\2\u00a0\u00a1\7.\2\2\u00a1\u00a2\7\"\2"+
		"\2\u00a2\32\3\2\2\2\u00a3\u00a4\7y\2\2\u00a4\u00a5\7q\2\2\u00a5\u00a6"+
		"\7t\2\2\u00a6\u00a7\7n\2\2\u00a7\u00a8\7f\2\2\u00a8\u00a9\7#\2\2\u00a9"+
		"\34\3\2\2\2\u00aa\u00ab\7\2\2\3\u00ab\u00ac\3\2\2\2\u00ac\u00ad\b\17\2"+
		"\2\u00ad\36\3\2\2\2\27\2*\618?CFKT[_bgnu|\u0080\u0083\u0088\u008b\u0091"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}