// Generated from TestRunResultCommonLexer.g4 by ANTLR 4.9
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
public class TestRunResultCommonLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		THEN_THE_TEST_RUN_RESULTS_HAVE_N_TESTS_START=1, FAILING_TESTS_END=2, PASSING_TESTS_END=3, 
		INTEGER_VALUE=4, DOCSTRING=5, DATA_ROW=6, GHERKIN_STEP_KEYWORD=7, INT=8, 
		QUOTED_TEXT_END=9, QUOTED_TEXT=10;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"THEN_THE_TEST_RUN_RESULTS_HAVE_N_TESTS_START", "FAILING_TESTS_END", 
			"PASSING_TESTS_END", "INTEGER_VALUE", "WS", "END", "AND", "BUT", "WILD", 
			"GIVEN", "WHEN", "THEN", "CAPTURE_DATA", "DOCSTRING_DOUBLE_QUOTES", "DOCSTRING_BACKTICKS", 
			"ESCAPE_SEQUENCE", "CELL_CHARACTER", "CELL_DATA", "DOCSTRING", "DATA_ROW", 
			"GHERKIN_STEP_KEYWORD", "INT", "QUOTED_TEXT_END", "QUOTED_TEXT", "NEWLINE"
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


	public TestRunResultCommonLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "TestRunResultCommonLexer.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\f\u013d\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5"+
		"\2V\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5"+
		"\3g\n\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\5\4z\n\4\3\4\3\4\3\5\6\5\177\n\5\r\5\16\5\u0080\3\6\3\6\3\7\7\7"+
		"\u0086\n\7\f\7\16\7\u0089\13\7\3\7\6\7\u008c\n\7\r\7\16\7\u008d\3\7\5"+
		"\7\u0091\n\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00a8\n\13\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\5\f\u00b1\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00ba\n\r\3\16"+
		"\3\16\3\16\3\16\3\17\7\17\u00c1\n\17\f\17\16\17\u00c4\13\17\3\17\3\17"+
		"\3\17\3\17\3\17\7\17\u00cb\n\17\f\17\16\17\u00ce\13\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\20\7\20\u00d7\n\20\f\20\16\20\u00da\13\20\3\20\3\20"+
		"\3\20\3\20\3\20\7\20\u00e1\n\20\f\20\16\20\u00e4\13\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\21\3\21\7\21\u00ee\n\21\f\21\16\21\u00f1\13\21\3\22"+
		"\3\22\3\22\5\22\u00f6\n\22\3\23\7\23\u00f9\n\23\f\23\16\23\u00fc\13\23"+
		"\3\23\7\23\u00ff\n\23\f\23\16\23\u0102\13\23\3\23\3\23\3\24\3\24\5\24"+
		"\u0108\n\24\3\25\7\25\u010b\n\25\f\25\16\25\u010e\13\25\3\25\3\25\6\25"+
		"\u0112\n\25\r\25\16\25\u0113\3\25\3\25\3\26\7\26\u0119\n\26\f\26\16\26"+
		"\u011c\13\26\3\26\3\26\3\26\3\26\5\26\u0122\n\26\3\27\6\27\u0125\n\27"+
		"\r\27\16\27\u0126\3\30\3\30\7\30\u012b\n\30\f\30\16\30\u012e\13\30\3\30"+
		"\3\30\3\30\3\31\3\31\7\31\u0135\n\31\f\31\16\31\u0138\13\31\3\31\3\31"+
		"\3\32\3\32\6\u00cc\u00e2\u012c\u0136\2\33\3\3\5\4\7\5\t\6\13\2\r\2\17"+
		"\2\21\2\23\2\25\2\27\2\31\2\33\2\35\2\37\2!\2#\2%\2\'\7)\b+\t-\n/\13\61"+
		"\f\63\2\3\2\b\3\2\62;\5\2\13\f\17\17\"\"\4\2\f\f\17\17\6\2\13\f\17\17"+
		"\"\"@@\5\2\f\f^^~~\6\2\f\f\17\17^^~~\2\u014d\2\3\3\2\2\2\2\5\3\2\2\2\2"+
		"\7\3\2\2\2\2\t\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2"+
		"/\3\2\2\2\2\61\3\2\2\2\3\65\3\2\2\2\5W\3\2\2\2\7j\3\2\2\2\t~\3\2\2\2\13"+
		"\u0082\3\2\2\2\r\u0087\3\2\2\2\17\u0092\3\2\2\2\21\u0097\3\2\2\2\23\u009c"+
		"\3\2\2\2\25\u00a7\3\2\2\2\27\u00b0\3\2\2\2\31\u00b9\3\2\2\2\33\u00bb\3"+
		"\2\2\2\35\u00c2\3\2\2\2\37\u00d8\3\2\2\2!\u00eb\3\2\2\2#\u00f5\3\2\2\2"+
		"%\u00fa\3\2\2\2\'\u0107\3\2\2\2)\u010c\3\2\2\2+\u011a\3\2\2\2-\u0124\3"+
		"\2\2\2/\u0128\3\2\2\2\61\u0132\3\2\2\2\63\u013b\3\2\2\2\65\66\5+\26\2"+
		"\66\67\7v\2\2\678\7j\2\289\7g\2\29:\7\"\2\2:;\7v\2\2;<\7g\2\2<=\7u\2\2"+
		"=>\7v\2\2>?\7\"\2\2?@\7t\2\2@A\7w\2\2AB\7p\2\2BC\7\"\2\2CD\7t\2\2DE\7"+
		"g\2\2EF\7u\2\2FG\7w\2\2GH\7n\2\2HI\7v\2\2IJ\7u\2\2JK\7\"\2\2KU\3\2\2\2"+
		"LM\7j\2\2MN\7c\2\2NO\7x\2\2OP\7g\2\2PV\7\"\2\2QR\7j\2\2RS\7c\2\2ST\7u"+
		"\2\2TV\7\"\2\2UL\3\2\2\2UQ\3\2\2\2V\4\3\2\2\2WX\7\"\2\2XY\7h\2\2YZ\7c"+
		"\2\2Z[\7k\2\2[\\\7n\2\2\\]\7k\2\2]^\7p\2\2^_\7i\2\2_`\7\"\2\2`a\7v\2\2"+
		"ab\7g\2\2bc\7u\2\2cd\7v\2\2df\3\2\2\2eg\7u\2\2fe\3\2\2\2fg\3\2\2\2gh\3"+
		"\2\2\2hi\5\r\7\2i\6\3\2\2\2jk\7\"\2\2kl\7r\2\2lm\7c\2\2mn\7u\2\2no\7u"+
		"\2\2op\7k\2\2pq\7p\2\2qr\7i\2\2rs\7\"\2\2st\7v\2\2tu\7g\2\2uv\7u\2\2v"+
		"w\7v\2\2wy\3\2\2\2xz\7u\2\2yx\3\2\2\2yz\3\2\2\2z{\3\2\2\2{|\5\r\7\2|\b"+
		"\3\2\2\2}\177\t\2\2\2~}\3\2\2\2\177\u0080\3\2\2\2\u0080~\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\n\3\2\2\2\u0082\u0083\t\3\2\2\u0083\f\3\2\2\2\u0084"+
		"\u0086\5\13\6\2\u0085\u0084\3\2\2\2\u0086\u0089\3\2\2\2\u0087\u0085\3"+
		"\2\2\2\u0087\u0088\3\2\2\2\u0088\u0090\3\2\2\2\u0089\u0087\3\2\2\2\u008a"+
		"\u008c\t\4\2\2\u008b\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008b\3\2"+
		"\2\2\u008d\u008e\3\2\2\2\u008e\u0091\3\2\2\2\u008f\u0091\7\2\2\3\u0090"+
		"\u008b\3\2\2\2\u0090\u008f\3\2\2\2\u0091\16\3\2\2\2\u0092\u0093\7C\2\2"+
		"\u0093\u0094\7p\2\2\u0094\u0095\7f\2\2\u0095\u0096\7\"\2\2\u0096\20\3"+
		"\2\2\2\u0097\u0098\7D\2\2\u0098\u0099\7w\2\2\u0099\u009a\7v\2\2\u009a"+
		"\u009b\7\"\2\2\u009b\22\3\2\2\2\u009c\u009d\7,\2\2\u009d\u009e\7\"\2\2"+
		"\u009e\24\3\2\2\2\u009f\u00a0\7I\2\2\u00a0\u00a1\7k\2\2\u00a1\u00a2\7"+
		"x\2\2\u00a2\u00a3\7g\2\2\u00a3\u00a4\7p\2\2\u00a4\u00a8\7\"\2\2\u00a5"+
		"\u00a8\5\17\b\2\u00a6\u00a8\5\21\t\2\u00a7\u009f\3\2\2\2\u00a7\u00a5\3"+
		"\2\2\2\u00a7\u00a6\3\2\2\2\u00a8\26\3\2\2\2\u00a9\u00aa\7Y\2\2\u00aa\u00ab"+
		"\7j\2\2\u00ab\u00ac\7g\2\2\u00ac\u00ad\7p\2\2\u00ad\u00b1\7\"\2\2\u00ae"+
		"\u00b1\5\17\b\2\u00af\u00b1\5\21\t\2\u00b0\u00a9\3\2\2\2\u00b0\u00ae\3"+
		"\2\2\2\u00b0\u00af\3\2\2\2\u00b1\30\3\2\2\2\u00b2\u00b3\7V\2\2\u00b3\u00b4"+
		"\7j\2\2\u00b4\u00b5\7g\2\2\u00b5\u00b6\7p\2\2\u00b6\u00ba\7\"\2\2\u00b7"+
		"\u00ba\5\17\b\2\u00b8\u00ba\5\21\t\2\u00b9\u00b2\3\2\2\2\u00b9\u00b7\3"+
		"\2\2\2\u00b9\u00b8\3\2\2\2\u00ba\32\3\2\2\2\u00bb\u00bc\7>\2\2\u00bc\u00bd"+
		"\n\5\2\2\u00bd\u00be\7@\2\2\u00be\34\3\2\2\2\u00bf\u00c1\5\13\6\2\u00c0"+
		"\u00bf\3\2\2\2\u00c1\u00c4\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2"+
		"\2\2\u00c3\u00c5\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00c6\7$\2\2\u00c6"+
		"\u00c7\7$\2\2\u00c7\u00c8\7$\2\2\u00c8\u00cc\3\2\2\2\u00c9\u00cb\13\2"+
		"\2\2\u00ca\u00c9\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cc"+
		"\u00ca\3\2\2\2\u00cd\u00cf\3\2\2\2\u00ce\u00cc\3\2\2\2\u00cf\u00d0\7$"+
		"\2\2\u00d0\u00d1\7$\2\2\u00d1\u00d2\7$\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d4"+
		"\5\r\7\2\u00d4\36\3\2\2\2\u00d5\u00d7\5\13\6\2\u00d6\u00d5\3\2\2\2\u00d7"+
		"\u00da\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00db\3\2"+
		"\2\2\u00da\u00d8\3\2\2\2\u00db\u00dc\7b\2\2\u00dc\u00dd\7b\2\2\u00dd\u00de"+
		"\7b\2\2\u00de\u00e2\3\2\2\2\u00df\u00e1\13\2\2\2\u00e0\u00df\3\2\2\2\u00e1"+
		"\u00e4\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3\u00e5\3\2"+
		"\2\2\u00e4\u00e2\3\2\2\2\u00e5\u00e6\7b\2\2\u00e6\u00e7\7b\2\2\u00e7\u00e8"+
		"\7b\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ea\5\r\7\2\u00ea \3\2\2\2\u00eb\u00ef"+
		"\7^\2\2\u00ec\u00ee\t\6\2\2\u00ed\u00ec\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef"+
		"\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\"\3\2\2\2\u00f1\u00ef\3\2\2\2"+
		"\u00f2\u00f6\5\33\16\2\u00f3\u00f6\n\7\2\2\u00f4\u00f6\5!\21\2\u00f5\u00f2"+
		"\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f4\3\2\2\2\u00f6$\3\2\2\2\u00f7"+
		"\u00f9\5\13\6\2\u00f8\u00f7\3\2\2\2\u00f9\u00fc\3\2\2\2\u00fa\u00f8\3"+
		"\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u0100\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fd"+
		"\u00ff\5#\22\2\u00fe\u00fd\3\2\2\2\u00ff\u0102\3\2\2\2\u0100\u00fe\3\2"+
		"\2\2\u0100\u0101\3\2\2\2\u0101\u0103\3\2\2\2\u0102\u0100\3\2\2\2\u0103"+
		"\u0104\7~\2\2\u0104&\3\2\2\2\u0105\u0108\5\35\17\2\u0106\u0108\5\37\20"+
		"\2\u0107\u0105\3\2\2\2\u0107\u0106\3\2\2\2\u0108(\3\2\2\2\u0109\u010b"+
		"\5\13\6\2\u010a\u0109\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010a\3\2\2\2"+
		"\u010c\u010d\3\2\2\2\u010d\u010f\3\2\2\2\u010e\u010c\3\2\2\2\u010f\u0111"+
		"\7~\2\2\u0110\u0112\5%\23\2\u0111\u0110\3\2\2\2\u0112\u0113\3\2\2\2\u0113"+
		"\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0116\5\r"+
		"\7\2\u0116*\3\2\2\2\u0117\u0119\5\13\6\2\u0118\u0117\3\2\2\2\u0119\u011c"+
		"\3\2\2\2\u011a\u0118\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u0121\3\2\2\2\u011c"+
		"\u011a\3\2\2\2\u011d\u0122\5\25\13\2\u011e\u0122\5\27\f\2\u011f\u0122"+
		"\5\31\r\2\u0120\u0122\5\23\n\2\u0121\u011d\3\2\2\2\u0121\u011e\3\2\2\2"+
		"\u0121\u011f\3\2\2\2\u0121\u0120\3\2\2\2\u0122,\3\2\2\2\u0123\u0125\t"+
		"\2\2\2\u0124\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0124\3\2\2\2\u0126"+
		"\u0127\3\2\2\2\u0127.\3\2\2\2\u0128\u012c\7$\2\2\u0129\u012b\13\2\2\2"+
		"\u012a\u0129\3\2\2\2\u012b\u012e\3\2\2\2\u012c\u012d\3\2\2\2\u012c\u012a"+
		"\3\2\2\2\u012d\u012f\3\2\2\2\u012e\u012c\3\2\2\2\u012f\u0130\7$\2\2\u0130"+
		"\u0131\5\r\7\2\u0131\60\3\2\2\2\u0132\u0136\7$\2\2\u0133\u0135\13\2\2"+
		"\2\u0134\u0133\3\2\2\2\u0135\u0138\3\2\2\2\u0136\u0137\3\2\2\2\u0136\u0134"+
		"\3\2\2\2\u0137\u0139\3\2\2\2\u0138\u0136\3\2\2\2\u0139\u013a\7$\2\2\u013a"+
		"\62\3\2\2\2\u013b\u013c\t\4\2\2\u013c\64\3\2\2\2\35\2Ufy\u0080\u0087\u008d"+
		"\u0090\u00a7\u00b0\u00b9\u00c2\u00cc\u00d8\u00e2\u00ef\u00f5\u00fa\u0100"+
		"\u0107\u010c\u0113\u011a\u0121\u0126\u012c\u0136\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}