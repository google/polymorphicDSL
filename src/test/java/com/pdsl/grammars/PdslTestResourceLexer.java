// Generated from PdslTestResourceLexer.g4 by ANTLR 4.9
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
public class PdslTestResourceLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		END=1, GIVEN_THE_TEST_RESOURCE=2, GIVEN_THE_FOLLOWING_TEST_RESOURCE=3, 
		DOCSTRING=4, DATA_ROW=5, GHERKIN_STEP_KEYWORD=6, INT=7, QUOTED_TEXT_END=8, 
		QUOTED_TEXT=9, TEST_RESOURCE_VALIDITY=10;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"GIVEN_THE_TEST_RESOURCE", "GIVEN_THE_FOLLOWING_TEST_RESOURCE", "WS", 
			"END", "AND", "BUT", "WILD", "GIVEN", "WHEN", "THEN", "CAPTURE_DATA", 
			"DOCSTRING_DOUBLE_QUOTES", "DOCSTRING_BACKTICKS", "ESCAPE_SEQUENCE", 
			"CELL_CHARACTER", "CELL_DATA", "DOCSTRING", "DATA_ROW", "GHERKIN_STEP_KEYWORD", 
			"INT", "QUOTED_TEXT_END", "QUOTED_TEXT", "NEWLINE", "TEST_RESOURCE_VALIDITY"
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
			null, "END", "GIVEN_THE_TEST_RESOURCE", "GIVEN_THE_FOLLOWING_TEST_RESOURCE", 
			"DOCSTRING", "DATA_ROW", "GHERKIN_STEP_KEYWORD", "INT", "QUOTED_TEXT_END", 
			"QUOTED_TEXT", "TEST_RESOURCE_VALIDITY"
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


	public PdslTestResourceLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PdslTestResourceLexer.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\f\u0147\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\4\3\4\3\5\7\5k\n\5\f\5\16\5n\13\5\3\5\6\5q\n\5\r\5\16\5r\3\5\5\5v\n\5"+
		"\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\5\t\u008d\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0096\n\n"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u009f\n\13\3\f\3\f\3\f\3\f\3"+
		"\r\7\r\u00a6\n\r\f\r\16\r\u00a9\13\r\3\r\3\r\3\r\3\r\3\r\7\r\u00b0\n\r"+
		"\f\r\16\r\u00b3\13\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\7\16\u00bc\n\16\f\16"+
		"\16\16\u00bf\13\16\3\16\3\16\3\16\3\16\3\16\7\16\u00c6\n\16\f\16\16\16"+
		"\u00c9\13\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\7\17\u00d3\n\17\f"+
		"\17\16\17\u00d6\13\17\3\20\3\20\3\20\5\20\u00db\n\20\3\21\7\21\u00de\n"+
		"\21\f\21\16\21\u00e1\13\21\3\21\7\21\u00e4\n\21\f\21\16\21\u00e7\13\21"+
		"\3\21\3\21\3\22\3\22\5\22\u00ed\n\22\3\23\7\23\u00f0\n\23\f\23\16\23\u00f3"+
		"\13\23\3\23\3\23\6\23\u00f7\n\23\r\23\16\23\u00f8\3\23\3\23\3\24\7\24"+
		"\u00fe\n\24\f\24\16\24\u0101\13\24\3\24\3\24\3\24\3\24\5\24\u0107\n\24"+
		"\3\25\6\25\u010a\n\25\r\25\16\25\u010b\3\26\3\26\7\26\u0110\n\26\f\26"+
		"\16\26\u0113\13\26\3\26\3\26\3\26\3\27\3\27\7\27\u011a\n\27\f\27\16\27"+
		"\u011d\13\27\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3"+
		"\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3"+
		"\31\3\31\3\31\3\31\3\31\3\31\5\31\u013e\n\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\6\u00b1\u00c7\u0111\u011b\2\32\3\4\5\5\7\2\t\3\13\2\r"+
		"\2\17\2\21\2\23\2\25\2\27\2\31\2\33\2\35\2\37\2!\2#\6%\7\'\b)\t+\n-\13"+
		"/\2\61\f\3\2\b\5\2\13\f\17\17\"\"\4\2\f\f\17\17\6\2\13\f\17\17\"\"@@\5"+
		"\2\f\f^^~~\6\2\f\f\17\17^^~~\3\2\62;\2\u0154\2\3\3\2\2\2\2\5\3\2\2\2\2"+
		"#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2\61"+
		"\3\2\2\2\3\63\3\2\2\2\5G\3\2\2\2\7g\3\2\2\2\tl\3\2\2\2\13w\3\2\2\2\r|"+
		"\3\2\2\2\17\u0081\3\2\2\2\21\u008c\3\2\2\2\23\u0095\3\2\2\2\25\u009e\3"+
		"\2\2\2\27\u00a0\3\2\2\2\31\u00a7\3\2\2\2\33\u00bd\3\2\2\2\35\u00d0\3\2"+
		"\2\2\37\u00da\3\2\2\2!\u00df\3\2\2\2#\u00ec\3\2\2\2%\u00f1\3\2\2\2\'\u00ff"+
		"\3\2\2\2)\u0109\3\2\2\2+\u010d\3\2\2\2-\u0117\3\2\2\2/\u0120\3\2\2\2\61"+
		"\u0122\3\2\2\2\63\64\5\'\24\2\64\65\7v\2\2\65\66\7j\2\2\66\67\7g\2\2\67"+
		"8\7\"\2\289\7v\2\29:\7g\2\2:;\7u\2\2;<\7v\2\2<=\7\"\2\2=>\7t\2\2>?\7g"+
		"\2\2?@\7u\2\2@A\7q\2\2AB\7w\2\2BC\7t\2\2CD\7e\2\2DE\7g\2\2EF\7\"\2\2F"+
		"\4\3\2\2\2GH\5\'\24\2HI\7v\2\2IJ\7j\2\2JK\7g\2\2KL\7\"\2\2LM\7h\2\2MN"+
		"\7q\2\2NO\7n\2\2OP\7n\2\2PQ\7q\2\2QR\7y\2\2RS\7k\2\2ST\7p\2\2TU\7i\2\2"+
		"UV\7\"\2\2VW\7v\2\2WX\7g\2\2XY\7u\2\2YZ\7v\2\2Z[\7\"\2\2[\\\7t\2\2\\]"+
		"\7g\2\2]^\7u\2\2^_\7q\2\2_`\7w\2\2`a\7t\2\2ab\7e\2\2bc\7g\2\2cd\7<\2\2"+
		"de\3\2\2\2ef\5\t\5\2f\6\3\2\2\2gh\t\2\2\2h\b\3\2\2\2ik\5\7\4\2ji\3\2\2"+
		"\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2mu\3\2\2\2nl\3\2\2\2oq\t\3\2\2po\3\2\2"+
		"\2qr\3\2\2\2rp\3\2\2\2rs\3\2\2\2sv\3\2\2\2tv\7\2\2\3up\3\2\2\2ut\3\2\2"+
		"\2v\n\3\2\2\2wx\7C\2\2xy\7p\2\2yz\7f\2\2z{\7\"\2\2{\f\3\2\2\2|}\7D\2\2"+
		"}~\7w\2\2~\177\7v\2\2\177\u0080\7\"\2\2\u0080\16\3\2\2\2\u0081\u0082\7"+
		",\2\2\u0082\u0083\7\"\2\2\u0083\20\3\2\2\2\u0084\u0085\7I\2\2\u0085\u0086"+
		"\7k\2\2\u0086\u0087\7x\2\2\u0087\u0088\7g\2\2\u0088\u0089\7p\2\2\u0089"+
		"\u008d\7\"\2\2\u008a\u008d\5\13\6\2\u008b\u008d\5\r\7\2\u008c\u0084\3"+
		"\2\2\2\u008c\u008a\3\2\2\2\u008c\u008b\3\2\2\2\u008d\22\3\2\2\2\u008e"+
		"\u008f\7Y\2\2\u008f\u0090\7j\2\2\u0090\u0091\7g\2\2\u0091\u0092\7p\2\2"+
		"\u0092\u0096\7\"\2\2\u0093\u0096\5\13\6\2\u0094\u0096\5\r\7\2\u0095\u008e"+
		"\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0094\3\2\2\2\u0096\24\3\2\2\2\u0097"+
		"\u0098\7V\2\2\u0098\u0099\7j\2\2\u0099\u009a\7g\2\2\u009a\u009b\7p\2\2"+
		"\u009b\u009f\7\"\2\2\u009c\u009f\5\13\6\2\u009d\u009f\5\r\7\2\u009e\u0097"+
		"\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009d\3\2\2\2\u009f\26\3\2\2\2\u00a0"+
		"\u00a1\7>\2\2\u00a1\u00a2\n\4\2\2\u00a2\u00a3\7@\2\2\u00a3\30\3\2\2\2"+
		"\u00a4\u00a6\5\7\4\2\u00a5\u00a4\3\2\2\2\u00a6\u00a9\3\2\2\2\u00a7\u00a5"+
		"\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00aa\3\2\2\2\u00a9\u00a7\3\2\2\2\u00aa"+
		"\u00ab\7$\2\2\u00ab\u00ac\7$\2\2\u00ac\u00ad\7$\2\2\u00ad\u00b1\3\2\2"+
		"\2\u00ae\u00b0\13\2\2\2\u00af\u00ae\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1"+
		"\u00b2\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b4\3\2\2\2\u00b3\u00b1\3\2"+
		"\2\2\u00b4\u00b5\7$\2\2\u00b5\u00b6\7$\2\2\u00b6\u00b7\7$\2\2\u00b7\u00b8"+
		"\3\2\2\2\u00b8\u00b9\5\t\5\2\u00b9\32\3\2\2\2\u00ba\u00bc\5\7\4\2\u00bb"+
		"\u00ba\3\2\2\2\u00bc\u00bf\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00be\3\2"+
		"\2\2\u00be\u00c0\3\2\2\2\u00bf\u00bd\3\2\2\2\u00c0\u00c1\7b\2\2\u00c1"+
		"\u00c2\7b\2\2\u00c2\u00c3\7b\2\2\u00c3\u00c7\3\2\2\2\u00c4\u00c6\13\2"+
		"\2\2\u00c5\u00c4\3\2\2\2\u00c6\u00c9\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c7"+
		"\u00c5\3\2\2\2\u00c8\u00ca\3\2\2\2\u00c9\u00c7\3\2\2\2\u00ca\u00cb\7b"+
		"\2\2\u00cb\u00cc\7b\2\2\u00cc\u00cd\7b\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf"+
		"\5\t\5\2\u00cf\34\3\2\2\2\u00d0\u00d4\7^\2\2\u00d1\u00d3\t\5\2\2\u00d2"+
		"\u00d1\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d5\3\2"+
		"\2\2\u00d5\36\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7\u00db\5\27\f\2\u00d8\u00db"+
		"\n\6\2\2\u00d9\u00db\5\35\17\2\u00da\u00d7\3\2\2\2\u00da\u00d8\3\2\2\2"+
		"\u00da\u00d9\3\2\2\2\u00db \3\2\2\2\u00dc\u00de\5\7\4\2\u00dd\u00dc\3"+
		"\2\2\2\u00de\u00e1\3\2\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0"+
		"\u00e5\3\2\2\2\u00e1\u00df\3\2\2\2\u00e2\u00e4\5\37\20\2\u00e3\u00e2\3"+
		"\2\2\2\u00e4\u00e7\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6"+
		"\u00e8\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e8\u00e9\7~\2\2\u00e9\"\3\2\2\2"+
		"\u00ea\u00ed\5\31\r\2\u00eb\u00ed\5\33\16\2\u00ec\u00ea\3\2\2\2\u00ec"+
		"\u00eb\3\2\2\2\u00ed$\3\2\2\2\u00ee\u00f0\5\7\4\2\u00ef\u00ee\3\2\2\2"+
		"\u00f0\u00f3\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f4"+
		"\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f4\u00f6\7~\2\2\u00f5\u00f7\5!\21\2\u00f6"+
		"\u00f5\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f9\3\2"+
		"\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fb\5\t\5\2\u00fb&\3\2\2\2\u00fc\u00fe"+
		"\5\7\4\2\u00fd\u00fc\3\2\2\2\u00fe\u0101\3\2\2\2\u00ff\u00fd\3\2\2\2\u00ff"+
		"\u0100\3\2\2\2\u0100\u0106\3\2\2\2\u0101\u00ff\3\2\2\2\u0102\u0107\5\21"+
		"\t\2\u0103\u0107\5\23\n\2\u0104\u0107\5\25\13\2\u0105\u0107\5\17\b\2\u0106"+
		"\u0102\3\2\2\2\u0106\u0103\3\2\2\2\u0106\u0104\3\2\2\2\u0106\u0105\3\2"+
		"\2\2\u0107(\3\2\2\2\u0108\u010a\t\7\2\2\u0109\u0108\3\2\2\2\u010a\u010b"+
		"\3\2\2\2\u010b\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c*\3\2\2\2\u010d"+
		"\u0111\7$\2\2\u010e\u0110\13\2\2\2\u010f\u010e\3\2\2\2\u0110\u0113\3\2"+
		"\2\2\u0111\u0112\3\2\2\2\u0111\u010f\3\2\2\2\u0112\u0114\3\2\2\2\u0113"+
		"\u0111\3\2\2\2\u0114\u0115\7$\2\2\u0115\u0116\5\t\5\2\u0116,\3\2\2\2\u0117"+
		"\u011b\7$\2\2\u0118\u011a\13\2\2\2\u0119\u0118\3\2\2\2\u011a\u011d\3\2"+
		"\2\2\u011b\u011c\3\2\2\2\u011b\u0119\3\2\2\2\u011c\u011e\3\2\2\2\u011d"+
		"\u011b\3\2\2\2\u011e\u011f\7$\2\2\u011f.\3\2\2\2\u0120\u0121\t\3\2\2\u0121"+
		"\60\3\2\2\2\u0122\u0123\5\'\24\2\u0123\u0124\7v\2\2\u0124\u0125\7j\2\2"+
		"\u0125\u0126\7g\2\2\u0126\u0127\7\"\2\2\u0127\u0128\7v\2\2\u0128\u0129"+
		"\7g\2\2\u0129\u012a\7u\2\2\u012a\u012b\7v\2\2\u012b\u012c\7\"\2\2\u012c"+
		"\u012d\7t\2\2\u012d\u012e\7g\2\2\u012e\u012f\7u\2\2\u012f\u0130\7q\2\2"+
		"\u0130\u0131\7w\2\2\u0131\u0132\7t\2\2\u0132\u0133\7e\2\2\u0133\u0134"+
		"\7g\2\2\u0134\u0135\7\"\2\2\u0135\u0136\7k\2\2\u0136\u0137\7u\2\2\u0137"+
		"\u0138\7\"\2\2\u0138\u013d\3\2\2\2\u0139\u013a\7P\2\2\u013a\u013b\7Q\2"+
		"\2\u013b\u013c\7V\2\2\u013c\u013e\7\"\2\2\u013d\u0139\3\2\2\2\u013d\u013e"+
		"\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u0140\7x\2\2\u0140\u0141\7c\2\2\u0141"+
		"\u0142\7n\2\2\u0142\u0143\7k\2\2\u0143\u0144\7f\2\2\u0144\u0145\3\2\2"+
		"\2\u0145\u0146\5\t\5\2\u0146\62\3\2\2\2\32\2lru\u008c\u0095\u009e\u00a7"+
		"\u00b1\u00bd\u00c7\u00d4\u00da\u00df\u00e5\u00ec\u00f1\u00f8\u00ff\u0106"+
		"\u010b\u0111\u011b\u013d\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}