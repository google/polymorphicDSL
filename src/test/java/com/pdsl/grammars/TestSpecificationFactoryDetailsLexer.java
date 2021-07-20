// Generated from TestSpecificationFactoryDetailsLexer.g4 by ANTLR 4.9
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
public class TestSpecificationFactoryDetailsLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		GIVEN_SPECIFIC_TEST_SPECIFICATION_FACTORY=1, DOCSTRING=2, DATA_ROW=3, 
		GHERKIN_STEP_KEYWORD=4, INT=5, QUOTED_TEXT_END=6, QUOTED_TEXT=7;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"GIVEN_SPECIFIC_TEST_SPECIFICATION_FACTORY", "WS", "END", "AND", "BUT", 
			"WILD", "GIVEN", "WHEN", "THEN", "CAPTURE_DATA", "DOCSTRING_DOUBLE_QUOTES", 
			"DOCSTRING_BACKTICKS", "ESCAPE_SEQUENCE", "CELL_CHARACTER", "CELL_DATA", 
			"DOCSTRING", "DATA_ROW", "GHERKIN_STEP_KEYWORD", "INT", "QUOTED_TEXT_END", 
			"QUOTED_TEXT", "NEWLINE"
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
			null, "GIVEN_SPECIFIC_TEST_SPECIFICATION_FACTORY", "DOCSTRING", "DATA_ROW", 
			"GHERKIN_STEP_KEYWORD", "INT", "QUOTED_TEXT_END", "QUOTED_TEXT"
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


	public TestSpecificationFactoryDetailsLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "TestSpecificationFactoryDetailsLexer.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\t\u0121\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2e\n\2\3\3\3\3"+
		"\3\4\7\4j\n\4\f\4\16\4m\13\4\3\4\6\4p\n\4\r\4\16\4q\3\4\5\4u\n\4\3\5\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\5\b\u008c\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u0095\n\t\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\5\n\u009e\n\n\3\13\3\13\3\13\3\13\3\f\7\f\u00a5"+
		"\n\f\f\f\16\f\u00a8\13\f\3\f\3\f\3\f\3\f\3\f\7\f\u00af\n\f\f\f\16\f\u00b2"+
		"\13\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\7\r\u00bb\n\r\f\r\16\r\u00be\13\r\3"+
		"\r\3\r\3\r\3\r\3\r\7\r\u00c5\n\r\f\r\16\r\u00c8\13\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\16\3\16\7\16\u00d2\n\16\f\16\16\16\u00d5\13\16\3\17\3\17\3\17"+
		"\5\17\u00da\n\17\3\20\7\20\u00dd\n\20\f\20\16\20\u00e0\13\20\3\20\7\20"+
		"\u00e3\n\20\f\20\16\20\u00e6\13\20\3\20\3\20\3\21\3\21\5\21\u00ec\n\21"+
		"\3\22\7\22\u00ef\n\22\f\22\16\22\u00f2\13\22\3\22\3\22\6\22\u00f6\n\22"+
		"\r\22\16\22\u00f7\3\22\3\22\3\23\7\23\u00fd\n\23\f\23\16\23\u0100\13\23"+
		"\3\23\3\23\3\23\3\23\5\23\u0106\n\23\3\24\6\24\u0109\n\24\r\24\16\24\u010a"+
		"\3\25\3\25\7\25\u010f\n\25\f\25\16\25\u0112\13\25\3\25\3\25\3\25\3\26"+
		"\3\26\7\26\u0119\n\26\f\26\16\26\u011c\13\26\3\26\3\26\3\27\3\27\6\u00b0"+
		"\u00c6\u0110\u011a\2\30\3\3\5\2\7\2\t\2\13\2\r\2\17\2\21\2\23\2\25\2\27"+
		"\2\31\2\33\2\35\2\37\2!\4#\5%\6\'\7)\b+\t-\2\3\2\b\5\2\13\f\17\17\"\""+
		"\4\2\f\f\17\17\6\2\13\f\17\17\"\"@@\5\2\f\f^^~~\6\2\f\f\17\17^^~~\3\2"+
		"\62;\2\u012f\2\3\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\3/\3\2\2\2\5f\3\2\2\2\7k\3\2\2\2\tv\3\2\2\2\13"+
		"{\3\2\2\2\r\u0080\3\2\2\2\17\u008b\3\2\2\2\21\u0094\3\2\2\2\23\u009d\3"+
		"\2\2\2\25\u009f\3\2\2\2\27\u00a6\3\2\2\2\31\u00bc\3\2\2\2\33\u00cf\3\2"+
		"\2\2\35\u00d9\3\2\2\2\37\u00de\3\2\2\2!\u00eb\3\2\2\2#\u00f0\3\2\2\2%"+
		"\u00fe\3\2\2\2\'\u0108\3\2\2\2)\u010c\3\2\2\2+\u0116\3\2\2\2-\u011f\3"+
		"\2\2\2/\60\5%\23\2\60\61\7v\2\2\61\62\7j\2\2\62\63\7g\2\2\63\64\7\"\2"+
		"\2\64\65\7V\2\2\65\66\7g\2\2\66\67\7u\2\2\678\7v\2\289\7\"\2\29:\7U\2"+
		"\2:;\7r\2\2;<\7g\2\2<=\7e\2\2=>\7k\2\2>?\7h\2\2?@\7k\2\2@A\7e\2\2AB\7"+
		"c\2\2BC\7v\2\2CD\7k\2\2DE\7q\2\2EF\7p\2\2FG\7\"\2\2GH\7H\2\2HI\7c\2\2"+
		"IJ\7e\2\2JK\7v\2\2KL\7q\2\2LM\7t\2\2MN\7{\2\2NO\7\"\2\2OP\7d\2\2PQ\7g"+
		"\2\2QR\7k\2\2RS\7p\2\2ST\7i\2\2TU\7\"\2\2UV\7w\2\2VW\7u\2\2WX\7g\2\2X"+
		"Y\7f\2\2YZ\7\"\2\2Z[\7k\2\2[\\\7u\2\2\\]\7\"\2\2]d\3\2\2\2^_\7v\2\2_`"+
		"\7j\2\2`a\7g\2\2ae\7\"\2\2bc\7c\2\2ce\7\"\2\2d^\3\2\2\2db\3\2\2\2de\3"+
		"\2\2\2e\4\3\2\2\2fg\t\2\2\2g\6\3\2\2\2hj\5\5\3\2ih\3\2\2\2jm\3\2\2\2k"+
		"i\3\2\2\2kl\3\2\2\2lt\3\2\2\2mk\3\2\2\2np\t\3\2\2on\3\2\2\2pq\3\2\2\2"+
		"qo\3\2\2\2qr\3\2\2\2ru\3\2\2\2su\7\2\2\3to\3\2\2\2ts\3\2\2\2u\b\3\2\2"+
		"\2vw\7C\2\2wx\7p\2\2xy\7f\2\2yz\7\"\2\2z\n\3\2\2\2{|\7D\2\2|}\7w\2\2}"+
		"~\7v\2\2~\177\7\"\2\2\177\f\3\2\2\2\u0080\u0081\7,\2\2\u0081\u0082\7\""+
		"\2\2\u0082\16\3\2\2\2\u0083\u0084\7I\2\2\u0084\u0085\7k\2\2\u0085\u0086"+
		"\7x\2\2\u0086\u0087\7g\2\2\u0087\u0088\7p\2\2\u0088\u008c\7\"\2\2\u0089"+
		"\u008c\5\t\5\2\u008a\u008c\5\13\6\2\u008b\u0083\3\2\2\2\u008b\u0089\3"+
		"\2\2\2\u008b\u008a\3\2\2\2\u008c\20\3\2\2\2\u008d\u008e\7Y\2\2\u008e\u008f"+
		"\7j\2\2\u008f\u0090\7g\2\2\u0090\u0091\7p\2\2\u0091\u0095\7\"\2\2\u0092"+
		"\u0095\5\t\5\2\u0093\u0095\5\13\6\2\u0094\u008d\3\2\2\2\u0094\u0092\3"+
		"\2\2\2\u0094\u0093\3\2\2\2\u0095\22\3\2\2\2\u0096\u0097\7V\2\2\u0097\u0098"+
		"\7j\2\2\u0098\u0099\7g\2\2\u0099\u009a\7p\2\2\u009a\u009e\7\"\2\2\u009b"+
		"\u009e\5\t\5\2\u009c\u009e\5\13\6\2\u009d\u0096\3\2\2\2\u009d\u009b\3"+
		"\2\2\2\u009d\u009c\3\2\2\2\u009e\24\3\2\2\2\u009f\u00a0\7>\2\2\u00a0\u00a1"+
		"\n\4\2\2\u00a1\u00a2\7@\2\2\u00a2\26\3\2\2\2\u00a3\u00a5\5\5\3\2\u00a4"+
		"\u00a3\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2"+
		"\2\2\u00a7\u00a9\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9\u00aa\7$\2\2\u00aa"+
		"\u00ab\7$\2\2\u00ab\u00ac\7$\2\2\u00ac\u00b0\3\2\2\2\u00ad\u00af\13\2"+
		"\2\2\u00ae\u00ad\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b0"+
		"\u00ae\3\2\2\2\u00b1\u00b3\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3\u00b4\7$"+
		"\2\2\u00b4\u00b5\7$\2\2\u00b5\u00b6\7$\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b8"+
		"\5\7\4\2\u00b8\30\3\2\2\2\u00b9\u00bb\5\5\3\2\u00ba\u00b9\3\2\2\2\u00bb"+
		"\u00be\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00bf\3\2"+
		"\2\2\u00be\u00bc\3\2\2\2\u00bf\u00c0\7b\2\2\u00c0\u00c1\7b\2\2\u00c1\u00c2"+
		"\7b\2\2\u00c2\u00c6\3\2\2\2\u00c3\u00c5\13\2\2\2\u00c4\u00c3\3\2\2\2\u00c5"+
		"\u00c8\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c7\u00c9\3\2"+
		"\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00ca\7b\2\2\u00ca\u00cb\7b\2\2\u00cb\u00cc"+
		"\7b\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce\5\7\4\2\u00ce\32\3\2\2\2\u00cf"+
		"\u00d3\7^\2\2\u00d0\u00d2\t\5\2\2\u00d1\u00d0\3\2\2\2\u00d2\u00d5\3\2"+
		"\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\34\3\2\2\2\u00d5\u00d3"+
		"\3\2\2\2\u00d6\u00da\5\25\13\2\u00d7\u00da\n\6\2\2\u00d8\u00da\5\33\16"+
		"\2\u00d9\u00d6\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00d8\3\2\2\2\u00da\36"+
		"\3\2\2\2\u00db\u00dd\5\5\3\2\u00dc\u00db\3\2\2\2\u00dd\u00e0\3\2\2\2\u00de"+
		"\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e4\3\2\2\2\u00e0\u00de\3\2"+
		"\2\2\u00e1\u00e3\5\35\17\2\u00e2\u00e1\3\2\2\2\u00e3\u00e6\3\2\2\2\u00e4"+
		"\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e7\3\2\2\2\u00e6\u00e4\3\2"+
		"\2\2\u00e7\u00e8\7~\2\2\u00e8 \3\2\2\2\u00e9\u00ec\5\27\f\2\u00ea\u00ec"+
		"\5\31\r\2\u00eb\u00e9\3\2\2\2\u00eb\u00ea\3\2\2\2\u00ec\"\3\2\2\2\u00ed"+
		"\u00ef\5\5\3\2\u00ee\u00ed\3\2\2\2\u00ef\u00f2\3\2\2\2\u00f0\u00ee\3\2"+
		"\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f3\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f3"+
		"\u00f5\7~\2\2\u00f4\u00f6\5\37\20\2\u00f5\u00f4\3\2\2\2\u00f6\u00f7\3"+
		"\2\2\2\u00f7\u00f5\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9"+
		"\u00fa\5\7\4\2\u00fa$\3\2\2\2\u00fb\u00fd\5\5\3\2\u00fc\u00fb\3\2\2\2"+
		"\u00fd\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0105"+
		"\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\u0106\5\17\b\2\u0102\u0106\5\21\t\2"+
		"\u0103\u0106\5\23\n\2\u0104\u0106\5\r\7\2\u0105\u0101\3\2\2\2\u0105\u0102"+
		"\3\2\2\2\u0105\u0103\3\2\2\2\u0105\u0104\3\2\2\2\u0106&\3\2\2\2\u0107"+
		"\u0109\t\7\2\2\u0108\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u0108\3\2"+
		"\2\2\u010a\u010b\3\2\2\2\u010b(\3\2\2\2\u010c\u0110\7$\2\2\u010d\u010f"+
		"\13\2\2\2\u010e\u010d\3\2\2\2\u010f\u0112\3\2\2\2\u0110\u0111\3\2\2\2"+
		"\u0110\u010e\3\2\2\2\u0111\u0113\3\2\2\2\u0112\u0110\3\2\2\2\u0113\u0114"+
		"\7$\2\2\u0114\u0115\5\7\4\2\u0115*\3\2\2\2\u0116\u011a\7$\2\2\u0117\u0119"+
		"\13\2\2\2\u0118\u0117\3\2\2\2\u0119\u011c\3\2\2\2\u011a\u011b\3\2\2\2"+
		"\u011a\u0118\3\2\2\2\u011b\u011d\3\2\2\2\u011c\u011a\3\2\2\2\u011d\u011e"+
		"\7$\2\2\u011e,\3\2\2\2\u011f\u0120\t\3\2\2\u0120.\3\2\2\2\32\2dkqt\u008b"+
		"\u0094\u009d\u00a6\u00b0\u00bc\u00c6\u00d3\u00d9\u00de\u00e4\u00eb\u00f0"+
		"\u00f7\u00fe\u0105\u010a\u0110\u011a\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}