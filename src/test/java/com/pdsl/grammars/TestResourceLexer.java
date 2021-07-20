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
		END=1, GIVEN_THE_TEST_RESOURCE=2, GIVEN_THE_FOLLOWING_TEST_RESOURCE=3, 
		DOCSTRING=4, DATA_ROW=5, GHERKIN_STEP_KEYWORD=6, INT=7, QUOTED_TEXT_END=8, 
		QUOTED_TEXT=9;
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
			"INT", "QUOTED_TEXT_END", "QUOTED_TEXT", "NEWLINE"
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
			"QUOTED_TEXT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\13\u0120\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3"+
		"\4\3\5\7\5i\n\5\f\5\16\5l\13\5\3\5\6\5o\n\5\r\5\16\5p\3\5\5\5t\n\5\3\6"+
		"\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\5\t\u008b\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u0094\n\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u009d\n\13\3\f\3\f\3\f\3\f\3\r\7\r"+
		"\u00a4\n\r\f\r\16\r\u00a7\13\r\3\r\3\r\3\r\3\r\3\r\7\r\u00ae\n\r\f\r\16"+
		"\r\u00b1\13\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\7\16\u00ba\n\16\f\16\16\16"+
		"\u00bd\13\16\3\16\3\16\3\16\3\16\3\16\7\16\u00c4\n\16\f\16\16\16\u00c7"+
		"\13\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\7\17\u00d1\n\17\f\17\16"+
		"\17\u00d4\13\17\3\20\3\20\3\20\5\20\u00d9\n\20\3\21\7\21\u00dc\n\21\f"+
		"\21\16\21\u00df\13\21\3\21\7\21\u00e2\n\21\f\21\16\21\u00e5\13\21\3\21"+
		"\3\21\3\22\3\22\5\22\u00eb\n\22\3\23\7\23\u00ee\n\23\f\23\16\23\u00f1"+
		"\13\23\3\23\3\23\6\23\u00f5\n\23\r\23\16\23\u00f6\3\23\3\23\3\24\7\24"+
		"\u00fc\n\24\f\24\16\24\u00ff\13\24\3\24\3\24\3\24\3\24\5\24\u0105\n\24"+
		"\3\25\6\25\u0108\n\25\r\25\16\25\u0109\3\26\3\26\7\26\u010e\n\26\f\26"+
		"\16\26\u0111\13\26\3\26\3\26\3\26\3\27\3\27\7\27\u0118\n\27\f\27\16\27"+
		"\u011b\13\27\3\27\3\27\3\30\3\30\6\u00af\u00c5\u010f\u0119\2\31\3\4\5"+
		"\5\7\2\t\3\13\2\r\2\17\2\21\2\23\2\25\2\27\2\31\2\33\2\35\2\37\2!\2#\6"+
		"%\7\'\b)\t+\n-\13/\2\3\2\b\5\2\13\f\17\17\"\"\4\2\f\f\17\17\6\2\13\f\17"+
		"\17\"\"@@\5\2\f\f^^~~\6\2\f\f\17\17^^~~\3\2\62;\2\u012c\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-"+
		"\3\2\2\2\3\61\3\2\2\2\5E\3\2\2\2\7e\3\2\2\2\tj\3\2\2\2\13u\3\2\2\2\rz"+
		"\3\2\2\2\17\177\3\2\2\2\21\u008a\3\2\2\2\23\u0093\3\2\2\2\25\u009c\3\2"+
		"\2\2\27\u009e\3\2\2\2\31\u00a5\3\2\2\2\33\u00bb\3\2\2\2\35\u00ce\3\2\2"+
		"\2\37\u00d8\3\2\2\2!\u00dd\3\2\2\2#\u00ea\3\2\2\2%\u00ef\3\2\2\2\'\u00fd"+
		"\3\2\2\2)\u0107\3\2\2\2+\u010b\3\2\2\2-\u0115\3\2\2\2/\u011e\3\2\2\2\61"+
		"\62\5\'\24\2\62\63\7v\2\2\63\64\7j\2\2\64\65\7g\2\2\65\66\7\"\2\2\66\67"+
		"\7v\2\2\678\7g\2\289\7u\2\29:\7v\2\2:;\7\"\2\2;<\7t\2\2<=\7g\2\2=>\7u"+
		"\2\2>?\7q\2\2?@\7w\2\2@A\7t\2\2AB\7e\2\2BC\7g\2\2CD\7\"\2\2D\4\3\2\2\2"+
		"EF\5\'\24\2FG\7v\2\2GH\7j\2\2HI\7g\2\2IJ\7\"\2\2JK\7h\2\2KL\7q\2\2LM\7"+
		"n\2\2MN\7n\2\2NO\7q\2\2OP\7y\2\2PQ\7k\2\2QR\7p\2\2RS\7i\2\2ST\7\"\2\2"+
		"TU\7v\2\2UV\7g\2\2VW\7u\2\2WX\7v\2\2XY\7\"\2\2YZ\7t\2\2Z[\7g\2\2[\\\7"+
		"u\2\2\\]\7q\2\2]^\7w\2\2^_\7t\2\2_`\7e\2\2`a\7g\2\2ab\7<\2\2bc\3\2\2\2"+
		"cd\5\t\5\2d\6\3\2\2\2ef\t\2\2\2f\b\3\2\2\2gi\5\7\4\2hg\3\2\2\2il\3\2\2"+
		"\2jh\3\2\2\2jk\3\2\2\2ks\3\2\2\2lj\3\2\2\2mo\t\3\2\2nm\3\2\2\2op\3\2\2"+
		"\2pn\3\2\2\2pq\3\2\2\2qt\3\2\2\2rt\7\2\2\3sn\3\2\2\2sr\3\2\2\2t\n\3\2"+
		"\2\2uv\7C\2\2vw\7p\2\2wx\7f\2\2xy\7\"\2\2y\f\3\2\2\2z{\7D\2\2{|\7w\2\2"+
		"|}\7v\2\2}~\7\"\2\2~\16\3\2\2\2\177\u0080\7,\2\2\u0080\u0081\7\"\2\2\u0081"+
		"\20\3\2\2\2\u0082\u0083\7I\2\2\u0083\u0084\7k\2\2\u0084\u0085\7x\2\2\u0085"+
		"\u0086\7g\2\2\u0086\u0087\7p\2\2\u0087\u008b\7\"\2\2\u0088\u008b\5\13"+
		"\6\2\u0089\u008b\5\r\7\2\u008a\u0082\3\2\2\2\u008a\u0088\3\2\2\2\u008a"+
		"\u0089\3\2\2\2\u008b\22\3\2\2\2\u008c\u008d\7Y\2\2\u008d\u008e\7j\2\2"+
		"\u008e\u008f\7g\2\2\u008f\u0090\7p\2\2\u0090\u0094\7\"\2\2\u0091\u0094"+
		"\5\13\6\2\u0092\u0094\5\r\7\2\u0093\u008c\3\2\2\2\u0093\u0091\3\2\2\2"+
		"\u0093\u0092\3\2\2\2\u0094\24\3\2\2\2\u0095\u0096\7V\2\2\u0096\u0097\7"+
		"j\2\2\u0097\u0098\7g\2\2\u0098\u0099\7p\2\2\u0099\u009d\7\"\2\2\u009a"+
		"\u009d\5\13\6\2\u009b\u009d\5\r\7\2\u009c\u0095\3\2\2\2\u009c\u009a\3"+
		"\2\2\2\u009c\u009b\3\2\2\2\u009d\26\3\2\2\2\u009e\u009f\7>\2\2\u009f\u00a0"+
		"\n\4\2\2\u00a0\u00a1\7@\2\2\u00a1\30\3\2\2\2\u00a2\u00a4\5\7\4\2\u00a3"+
		"\u00a2\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6\3\2"+
		"\2\2\u00a6\u00a8\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00a9\7$\2\2\u00a9"+
		"\u00aa\7$\2\2\u00aa\u00ab\7$\2\2\u00ab\u00af\3\2\2\2\u00ac\u00ae\13\2"+
		"\2\2\u00ad\u00ac\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af\u00b0\3\2\2\2\u00af"+
		"\u00ad\3\2\2\2\u00b0\u00b2\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b3\7$"+
		"\2\2\u00b3\u00b4\7$\2\2\u00b4\u00b5\7$\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7"+
		"\5\t\5\2\u00b7\32\3\2\2\2\u00b8\u00ba\5\7\4\2\u00b9\u00b8\3\2\2\2\u00ba"+
		"\u00bd\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00be\3\2"+
		"\2\2\u00bd\u00bb\3\2\2\2\u00be\u00bf\7b\2\2\u00bf\u00c0\7b\2\2\u00c0\u00c1"+
		"\7b\2\2\u00c1\u00c5\3\2\2\2\u00c2\u00c4\13\2\2\2\u00c3\u00c2\3\2\2\2\u00c4"+
		"\u00c7\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6\u00c8\3\2"+
		"\2\2\u00c7\u00c5\3\2\2\2\u00c8\u00c9\7b\2\2\u00c9\u00ca\7b\2\2\u00ca\u00cb"+
		"\7b\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd\5\t\5\2\u00cd\34\3\2\2\2\u00ce"+
		"\u00d2\7^\2\2\u00cf\u00d1\t\5\2\2\u00d0\u00cf\3\2\2\2\u00d1\u00d4\3\2"+
		"\2\2\u00d2\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\36\3\2\2\2\u00d4\u00d2"+
		"\3\2\2\2\u00d5\u00d9\5\27\f\2\u00d6\u00d9\n\6\2\2\u00d7\u00d9\5\35\17"+
		"\2\u00d8\u00d5\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d8\u00d7\3\2\2\2\u00d9 "+
		"\3\2\2\2\u00da\u00dc\5\7\4\2\u00db\u00da\3\2\2\2\u00dc\u00df\3\2\2\2\u00dd"+
		"\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00e3\3\2\2\2\u00df\u00dd\3\2"+
		"\2\2\u00e0\u00e2\5\37\20\2\u00e1\u00e0\3\2\2\2\u00e2\u00e5\3\2\2\2\u00e3"+
		"\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e6\3\2\2\2\u00e5\u00e3\3\2"+
		"\2\2\u00e6\u00e7\7~\2\2\u00e7\"\3\2\2\2\u00e8\u00eb\5\31\r\2\u00e9\u00eb"+
		"\5\33\16\2\u00ea\u00e8\3\2\2\2\u00ea\u00e9\3\2\2\2\u00eb$\3\2\2\2\u00ec"+
		"\u00ee\5\7\4\2\u00ed\u00ec\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef\u00ed\3\2"+
		"\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f2\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f2"+
		"\u00f4\7~\2\2\u00f3\u00f5\5!\21\2\u00f4\u00f3\3\2\2\2\u00f5\u00f6\3\2"+
		"\2\2\u00f6\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8"+
		"\u00f9\5\t\5\2\u00f9&\3\2\2\2\u00fa\u00fc\5\7\4\2\u00fb\u00fa\3\2\2\2"+
		"\u00fc\u00ff\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u0104"+
		"\3\2\2\2\u00ff\u00fd\3\2\2\2\u0100\u0105\5\21\t\2\u0101\u0105\5\23\n\2"+
		"\u0102\u0105\5\25\13\2\u0103\u0105\5\17\b\2\u0104\u0100\3\2\2\2\u0104"+
		"\u0101\3\2\2\2\u0104\u0102\3\2\2\2\u0104\u0103\3\2\2\2\u0105(\3\2\2\2"+
		"\u0106\u0108\t\7\2\2\u0107\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u0107"+
		"\3\2\2\2\u0109\u010a\3\2\2\2\u010a*\3\2\2\2\u010b\u010f\7$\2\2\u010c\u010e"+
		"\13\2\2\2\u010d\u010c\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u0110\3\2\2\2"+
		"\u010f\u010d\3\2\2\2\u0110\u0112\3\2\2\2\u0111\u010f\3\2\2\2\u0112\u0113"+
		"\7$\2\2\u0113\u0114\5\t\5\2\u0114,\3\2\2\2\u0115\u0119\7$\2\2\u0116\u0118"+
		"\13\2\2\2\u0117\u0116\3\2\2\2\u0118\u011b\3\2\2\2\u0119\u011a\3\2\2\2"+
		"\u0119\u0117\3\2\2\2\u011a\u011c\3\2\2\2\u011b\u0119\3\2\2\2\u011c\u011d"+
		"\7$\2\2\u011d.\3\2\2\2\u011e\u011f\t\3\2\2\u011f\60\3\2\2\2\31\2jps\u008a"+
		"\u0093\u009c\u00a5\u00af\u00bb\u00c5\u00d2\u00d8\u00dd\u00e3\u00ea\u00ef"+
		"\u00f6\u00fd\u0104\u0109\u010f\u0119\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}