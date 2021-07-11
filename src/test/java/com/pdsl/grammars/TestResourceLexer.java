// Generated from TestResourceLexer.g4 by ANTLR 4.9
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
public class TestResourceLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		GIVEN_THE_TEST_RESOURCE=1, GIVEN_THE_FOLLOWING_TEST_RESOURCE=2, DOCSTRING=3, 
		DATA_ROW=4, GHERKIN_STEP_KEYWORD=5, INT=6, TEXT_IN_DOUBLE_QUOTES=7, END_QUOTE=8;
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
			"INT", "TEXT_IN_DOUBLE_QUOTES", "END_QUOTE", "NEWLINE"
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
			null, "GIVEN_THE_TEST_RESOURCE", "GIVEN_THE_FOLLOWING_TEST_RESOURCE", 
			"DOCSTRING", "DATA_ROW", "GHERKIN_STEP_KEYWORD", "INT", "TEXT_IN_DOUBLE_QUOTES", 
			"END_QUOTE"
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


	public TestResourceLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "TestResourceLexer.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\n\u0116\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4"+
		"\3\5\7\5j\n\5\f\5\16\5m\13\5\3\5\6\5p\n\5\r\5\16\5q\3\5\5\5u\n\5\3\6\3"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\5\t\u008c\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0095\n\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u009e\n\13\3\f\3\f\3\f\3\f\3\r\7\r"+
		"\u00a5\n\r\f\r\16\r\u00a8\13\r\3\r\3\r\3\r\3\r\3\r\7\r\u00af\n\r\f\r\16"+
		"\r\u00b2\13\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\7\16\u00bb\n\16\f\16\16\16"+
		"\u00be\13\16\3\16\3\16\3\16\3\16\3\16\7\16\u00c5\n\16\f\16\16\16\u00c8"+
		"\13\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\7\17\u00d2\n\17\f\17\16"+
		"\17\u00d5\13\17\3\20\3\20\3\20\5\20\u00da\n\20\3\21\7\21\u00dd\n\21\f"+
		"\21\16\21\u00e0\13\21\3\21\7\21\u00e3\n\21\f\21\16\21\u00e6\13\21\3\21"+
		"\3\21\3\22\3\22\5\22\u00ec\n\22\3\23\7\23\u00ef\n\23\f\23\16\23\u00f2"+
		"\13\23\3\23\3\23\6\23\u00f6\n\23\r\23\16\23\u00f7\3\23\3\23\3\24\7\24"+
		"\u00fd\n\24\f\24\16\24\u0100\13\24\3\24\3\24\3\24\3\24\5\24\u0106\n\24"+
		"\3\25\6\25\u0109\n\25\r\25\16\25\u010a\3\26\6\26\u010e\n\26\r\26\16\26"+
		"\u010f\3\27\3\27\3\27\3\30\3\30\4\u00b0\u00c6\2\31\3\3\5\4\7\2\t\2\13"+
		"\2\r\2\17\2\21\2\23\2\25\2\27\2\31\2\33\2\35\2\37\2!\2#\5%\6\'\7)\b+\t"+
		"-\n/\2\3\2\n\5\2\13\f\17\17\"\"\4\2\f\f\17\17\6\2\13\f\17\17\"\"@@\5\2"+
		"\f\f^^~~\6\2\f\f\17\17^^~~\4\2\13\13\"\"\3\2\62;\3\2$$\2\u0121\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2"+
		"\2\2-\3\2\2\2\3\61\3\2\2\2\5F\3\2\2\2\7f\3\2\2\2\tk\3\2\2\2\13v\3\2\2"+
		"\2\r{\3\2\2\2\17\u0080\3\2\2\2\21\u008b\3\2\2\2\23\u0094\3\2\2\2\25\u009d"+
		"\3\2\2\2\27\u009f\3\2\2\2\31\u00a6\3\2\2\2\33\u00bc\3\2\2\2\35\u00cf\3"+
		"\2\2\2\37\u00d9\3\2\2\2!\u00de\3\2\2\2#\u00eb\3\2\2\2%\u00f0\3\2\2\2\'"+
		"\u00fe\3\2\2\2)\u0108\3\2\2\2+\u010d\3\2\2\2-\u0111\3\2\2\2/\u0114\3\2"+
		"\2\2\61\62\5\'\24\2\62\63\7v\2\2\63\64\7j\2\2\64\65\7g\2\2\65\66\7\"\2"+
		"\2\66\67\7v\2\2\678\7g\2\289\7u\2\29:\7v\2\2:;\7\"\2\2;<\7t\2\2<=\7g\2"+
		"\2=>\7u\2\2>?\7q\2\2?@\7w\2\2@A\7t\2\2AB\7e\2\2BC\7g\2\2CD\7\"\2\2DE\7"+
		"$\2\2E\4\3\2\2\2FG\5\'\24\2GH\7v\2\2HI\7j\2\2IJ\7g\2\2JK\7\"\2\2KL\7h"+
		"\2\2LM\7q\2\2MN\7n\2\2NO\7n\2\2OP\7q\2\2PQ\7y\2\2QR\7k\2\2RS\7p\2\2ST"+
		"\7i\2\2TU\7\"\2\2UV\7v\2\2VW\7g\2\2WX\7u\2\2XY\7v\2\2YZ\7\"\2\2Z[\7t\2"+
		"\2[\\\7g\2\2\\]\7u\2\2]^\7q\2\2^_\7w\2\2_`\7t\2\2`a\7e\2\2ab\7g\2\2bc"+
		"\7<\2\2cd\3\2\2\2de\5\t\5\2e\6\3\2\2\2fg\t\2\2\2g\b\3\2\2\2hj\5\7\4\2"+
		"ih\3\2\2\2jm\3\2\2\2ki\3\2\2\2kl\3\2\2\2lt\3\2\2\2mk\3\2\2\2np\t\3\2\2"+
		"on\3\2\2\2pq\3\2\2\2qo\3\2\2\2qr\3\2\2\2ru\3\2\2\2su\7\2\2\3to\3\2\2\2"+
		"ts\3\2\2\2u\n\3\2\2\2vw\7C\2\2wx\7p\2\2xy\7f\2\2yz\7\"\2\2z\f\3\2\2\2"+
		"{|\7D\2\2|}\7w\2\2}~\7v\2\2~\177\7\"\2\2\177\16\3\2\2\2\u0080\u0081\7"+
		",\2\2\u0081\u0082\7\"\2\2\u0082\20\3\2\2\2\u0083\u0084\7I\2\2\u0084\u0085"+
		"\7k\2\2\u0085\u0086\7x\2\2\u0086\u0087\7g\2\2\u0087\u0088\7p\2\2\u0088"+
		"\u008c\7\"\2\2\u0089\u008c\5\13\6\2\u008a\u008c\5\r\7\2\u008b\u0083\3"+
		"\2\2\2\u008b\u0089\3\2\2\2\u008b\u008a\3\2\2\2\u008c\22\3\2\2\2\u008d"+
		"\u008e\7Y\2\2\u008e\u008f\7j\2\2\u008f\u0090\7g\2\2\u0090\u0091\7p\2\2"+
		"\u0091\u0095\7\"\2\2\u0092\u0095\5\13\6\2\u0093\u0095\5\r\7\2\u0094\u008d"+
		"\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0093\3\2\2\2\u0095\24\3\2\2\2\u0096"+
		"\u0097\7V\2\2\u0097\u0098\7j\2\2\u0098\u0099\7g\2\2\u0099\u009a\7p\2\2"+
		"\u009a\u009e\7\"\2\2\u009b\u009e\5\13\6\2\u009c\u009e\5\r\7\2\u009d\u0096"+
		"\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009c\3\2\2\2\u009e\26\3\2\2\2\u009f"+
		"\u00a0\7>\2\2\u00a0\u00a1\n\4\2\2\u00a1\u00a2\7@\2\2\u00a2\30\3\2\2\2"+
		"\u00a3\u00a5\5\7\4\2\u00a4\u00a3\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6\u00a4"+
		"\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a9\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9"+
		"\u00aa\7$\2\2\u00aa\u00ab\7$\2\2\u00ab\u00ac\7$\2\2\u00ac\u00b0\3\2\2"+
		"\2\u00ad\u00af\13\2\2\2\u00ae\u00ad\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0"+
		"\u00b1\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1\u00b3\3\2\2\2\u00b2\u00b0\3\2"+
		"\2\2\u00b3\u00b4\7$\2\2\u00b4\u00b5\7$\2\2\u00b5\u00b6\7$\2\2\u00b6\u00b7"+
		"\3\2\2\2\u00b7\u00b8\5\t\5\2\u00b8\32\3\2\2\2\u00b9\u00bb\5\7\4\2\u00ba"+
		"\u00b9\3\2\2\2\u00bb\u00be\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2"+
		"\2\2\u00bd\u00bf\3\2\2\2\u00be\u00bc\3\2\2\2\u00bf\u00c0\7b\2\2\u00c0"+
		"\u00c1\7b\2\2\u00c1\u00c2\7b\2\2\u00c2\u00c6\3\2\2\2\u00c3\u00c5\13\2"+
		"\2\2\u00c4\u00c3\3\2\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c6"+
		"\u00c4\3\2\2\2\u00c7\u00c9\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00ca\7b"+
		"\2\2\u00ca\u00cb\7b\2\2\u00cb\u00cc\7b\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce"+
		"\5\t\5\2\u00ce\34\3\2\2\2\u00cf\u00d3\7^\2\2\u00d0\u00d2\t\5\2\2\u00d1"+
		"\u00d0\3\2\2\2\u00d2\u00d5\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2"+
		"\2\2\u00d4\36\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d6\u00da\5\27\f\2\u00d7\u00da"+
		"\n\6\2\2\u00d8\u00da\5\35\17\2\u00d9\u00d6\3\2\2\2\u00d9\u00d7\3\2\2\2"+
		"\u00d9\u00d8\3\2\2\2\u00da \3\2\2\2\u00db\u00dd\5\7\4\2\u00dc\u00db\3"+
		"\2\2\2\u00dd\u00e0\3\2\2\2\u00de\u00dc\3\2\2\2\u00de\u00df\3\2\2\2\u00df"+
		"\u00e4\3\2\2\2\u00e0\u00de\3\2\2\2\u00e1\u00e3\5\37\20\2\u00e2\u00e1\3"+
		"\2\2\2\u00e3\u00e6\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5"+
		"\u00e7\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e7\u00e8\7~\2\2\u00e8\"\3\2\2\2"+
		"\u00e9\u00ec\5\31\r\2\u00ea\u00ec\5\33\16\2\u00eb\u00e9\3\2\2\2\u00eb"+
		"\u00ea\3\2\2\2\u00ec$\3\2\2\2\u00ed\u00ef\5\7\4\2\u00ee\u00ed\3\2\2\2"+
		"\u00ef\u00f2\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f3"+
		"\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f3\u00f5\7~\2\2\u00f4\u00f6\5!\21\2\u00f5"+
		"\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f7\u00f8\3\2"+
		"\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fa\5\t\5\2\u00fa&\3\2\2\2\u00fb\u00fd"+
		"\t\7\2\2\u00fc\u00fb\3\2\2\2\u00fd\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe"+
		"\u00ff\3\2\2\2\u00ff\u0105\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\u0106\5\21"+
		"\t\2\u0102\u0106\5\23\n\2\u0103\u0106\5\25\13\2\u0104\u0106\5\17\b\2\u0105"+
		"\u0101\3\2\2\2\u0105\u0102\3\2\2\2\u0105\u0103\3\2\2\2\u0105\u0104\3\2"+
		"\2\2\u0106(\3\2\2\2\u0107\u0109\t\b\2\2\u0108\u0107\3\2\2\2\u0109\u010a"+
		"\3\2\2\2\u010a\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b*\3\2\2\2\u010c"+
		"\u010e\n\t\2\2\u010d\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u010d\3\2"+
		"\2\2\u010f\u0110\3\2\2\2\u0110,\3\2\2\2\u0111\u0112\7$\2\2\u0112\u0113"+
		"\5\t\5\2\u0113.\3\2\2\2\u0114\u0115\t\3\2\2\u0115\60\3\2\2\2\30\2kqt\u008b"+
		"\u0094\u009d\u00a6\u00b0\u00bc\u00c6\u00d3\u00d9\u00de\u00e4\u00eb\u00f0"+
		"\u00f7\u00fe\u0105\u010a\u010f\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}