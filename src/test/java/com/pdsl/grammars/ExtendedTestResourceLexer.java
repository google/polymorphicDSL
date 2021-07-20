// Generated from ExtendedTestResourceLexer.g4 by ANTLR 4.9
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
public class ExtendedTestResourceLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TEST_RESOURCE_VALIDITY=1, DOCSTRING=2, DATA_ROW=3, GHERKIN_STEP_KEYWORD=4, 
		INT=5, TEXT_IN_DOUBLE_QUOTES=6, END_QUOTE=7, DOC_STRING=8;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"TEST_RESOURCE_VALIDITY", "WS", "END", "AND", "BUT", "WILD", "GIVEN", 
			"WHEN", "THEN", "CAPTURE_DATA", "DOCSTRING_DOUBLE_QUOTES", "DOCSTRING_BACKTICKS", 
			"ESCAPE_SEQUENCE", "CELL_CHARACTER", "CELL_DATA", "DOCSTRING", "DATA_ROW", 
			"GHERKIN_STEP_KEYWORD", "INT", "TEXT_IN_DOUBLE_QUOTES", "END_QUOTE", 
			"DOC_STRING", "NEWLINE"
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


	public ExtendedTestResourceLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ExtendedTestResourceLexer.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\n\u0125\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2M\n\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\3\3\3\3\4\7\4Z\n\4\f\4\16\4]\13\4\3\4\6\4`\n\4\r\4\16\4a\3\4\5\4e\n"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\5\b|\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u0085\n\t\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u008e\n\n\3\13\3\13\3\13\3\13\3\f\7\f\u0095"+
		"\n\f\f\f\16\f\u0098\13\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00a2\n\f"+
		"\f\f\16\f\u00a5\13\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\7\r\u00ae\n\r\f\r\16"+
		"\r\u00b1\13\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00bb\n\r\3\r\7\r\u00be"+
		"\n\r\f\r\16\r\u00c1\13\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\7\16\u00cb"+
		"\n\16\f\16\16\16\u00ce\13\16\3\17\3\17\3\17\5\17\u00d3\n\17\3\20\7\20"+
		"\u00d6\n\20\f\20\16\20\u00d9\13\20\3\20\7\20\u00dc\n\20\f\20\16\20\u00df"+
		"\13\20\3\20\3\20\3\21\3\21\5\21\u00e5\n\21\3\22\7\22\u00e8\n\22\f\22\16"+
		"\22\u00eb\13\22\3\22\3\22\6\22\u00ef\n\22\r\22\16\22\u00f0\3\22\3\22\3"+
		"\23\7\23\u00f6\n\23\f\23\16\23\u00f9\13\23\3\23\3\23\3\23\3\23\5\23\u00ff"+
		"\n\23\3\24\6\24\u0102\n\24\r\24\16\24\u0103\3\25\6\25\u0107\n\25\r\25"+
		"\16\25\u0108\3\26\3\26\3\26\3\27\7\27\u010f\n\27\f\27\16\27\u0112\13\27"+
		"\3\27\3\27\3\27\3\27\3\27\7\27\u0119\n\27\f\27\16\27\u011c\13\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\30\3\30\5\u00a3\u00bf\u011a\2\31\3\3\5\2\7"+
		"\2\t\2\13\2\r\2\17\2\21\2\23\2\25\2\27\2\31\2\33\2\35\2\37\2!\4#\5%\6"+
		"\'\7)\b+\t-\n/\2\3\2\13\5\2\13\f\17\17\"\"\4\2\f\f\17\17\6\2\13\f\17\17"+
		"\"\"@@\3\2$$\3\2bb\5\2\f\f^^~~\6\2\f\f\17\17^^~~\4\2\13\13\"\"\3\2\62"+
		";\2\u0137\2\3\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)"+
		"\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\3\61\3\2\2\2\5V\3\2\2\2\7[\3\2\2\2\tf\3"+
		"\2\2\2\13k\3\2\2\2\rp\3\2\2\2\17{\3\2\2\2\21\u0084\3\2\2\2\23\u008d\3"+
		"\2\2\2\25\u008f\3\2\2\2\27\u0096\3\2\2\2\31\u00af\3\2\2\2\33\u00c8\3\2"+
		"\2\2\35\u00d2\3\2\2\2\37\u00d7\3\2\2\2!\u00e4\3\2\2\2#\u00e9\3\2\2\2%"+
		"\u00f7\3\2\2\2\'\u0101\3\2\2\2)\u0106\3\2\2\2+\u010a\3\2\2\2-\u0110\3"+
		"\2\2\2/\u0123\3\2\2\2\61\62\5%\23\2\62\63\7v\2\2\63\64\7j\2\2\64\65\7"+
		"g\2\2\65\66\7\"\2\2\66\67\7v\2\2\678\7g\2\289\7u\2\29:\7v\2\2:;\7\"\2"+
		"\2;<\7t\2\2<=\7g\2\2=>\7u\2\2>?\7q\2\2?@\7w\2\2@A\7t\2\2AB\7e\2\2BC\7"+
		"g\2\2CD\7\"\2\2DE\7k\2\2EF\7u\2\2FG\7\"\2\2GL\3\2\2\2HI\7P\2\2IJ\7Q\2"+
		"\2JK\7V\2\2KM\7\"\2\2LH\3\2\2\2LM\3\2\2\2MN\3\2\2\2NO\7x\2\2OP\7c\2\2"+
		"PQ\7n\2\2QR\7k\2\2RS\7f\2\2ST\3\2\2\2TU\5\7\4\2U\4\3\2\2\2VW\t\2\2\2W"+
		"\6\3\2\2\2XZ\5\5\3\2YX\3\2\2\2Z]\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\d\3\2\2"+
		"\2][\3\2\2\2^`\t\3\2\2_^\3\2\2\2`a\3\2\2\2a_\3\2\2\2ab\3\2\2\2be\3\2\2"+
		"\2ce\7\2\2\3d_\3\2\2\2dc\3\2\2\2e\b\3\2\2\2fg\7C\2\2gh\7p\2\2hi\7f\2\2"+
		"ij\7\"\2\2j\n\3\2\2\2kl\7D\2\2lm\7w\2\2mn\7v\2\2no\7\"\2\2o\f\3\2\2\2"+
		"pq\7,\2\2qr\7\"\2\2r\16\3\2\2\2st\7I\2\2tu\7k\2\2uv\7x\2\2vw\7g\2\2wx"+
		"\7p\2\2x|\7\"\2\2y|\5\t\5\2z|\5\13\6\2{s\3\2\2\2{y\3\2\2\2{z\3\2\2\2|"+
		"\20\3\2\2\2}~\7Y\2\2~\177\7j\2\2\177\u0080\7g\2\2\u0080\u0081\7p\2\2\u0081"+
		"\u0085\7\"\2\2\u0082\u0085\5\t\5\2\u0083\u0085\5\13\6\2\u0084}\3\2\2\2"+
		"\u0084\u0082\3\2\2\2\u0084\u0083\3\2\2\2\u0085\22\3\2\2\2\u0086\u0087"+
		"\7V\2\2\u0087\u0088\7j\2\2\u0088\u0089\7g\2\2\u0089\u008a\7p\2\2\u008a"+
		"\u008e\7\"\2\2\u008b\u008e\5\t\5\2\u008c\u008e\5\13\6\2\u008d\u0086\3"+
		"\2\2\2\u008d\u008b\3\2\2\2\u008d\u008c\3\2\2\2\u008e\24\3\2\2\2\u008f"+
		"\u0090\7>\2\2\u0090\u0091\n\4\2\2\u0091\u0092\7@\2\2\u0092\26\3\2\2\2"+
		"\u0093\u0095\5\5\3\2\u0094\u0093\3\2\2\2\u0095\u0098\3\2\2\2\u0096\u0094"+
		"\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0099\3\2\2\2\u0098\u0096\3\2\2\2\u0099"+
		"\u009a\7$\2\2\u009a\u009b\7$\2\2\u009b\u009c\7$\2\2\u009c\u00a3\3\2\2"+
		"\2\u009d\u00a2\5\25\13\2\u009e\u00a2\n\5\2\2\u009f\u00a0\7$\2\2\u00a0"+
		"\u00a2\n\5\2\2\u00a1\u009d\3\2\2\2\u00a1\u009e\3\2\2\2\u00a1\u009f\3\2"+
		"\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4"+
		"\u00a6\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a7\7$\2\2\u00a7\u00a8\7$\2"+
		"\2\u00a8\u00a9\7$\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\5\7\4\2\u00ab\30"+
		"\3\2\2\2\u00ac\u00ae\5\5\3\2\u00ad\u00ac\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af"+
		"\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b2\3\2\2\2\u00b1\u00af\3\2"+
		"\2\2\u00b2\u00b3\7b\2\2\u00b3\u00b4\7b\2\2\u00b4\u00b5\7b\2\2\u00b5\u00ba"+
		"\3\2\2\2\u00b6\u00bb\n\6\2\2\u00b7\u00bb\5\25\13\2\u00b8\u00b9\7b\2\2"+
		"\u00b9\u00bb\n\6\2\2\u00ba\u00b6\3\2\2\2\u00ba\u00b7\3\2\2\2\u00ba\u00b8"+
		"\3\2\2\2\u00bb\u00bf\3\2\2\2\u00bc\u00be\13\2\2\2\u00bd\u00bc\3\2\2\2"+
		"\u00be\u00c1\3\2\2\2\u00bf\u00c0\3\2\2\2\u00bf\u00bd\3\2\2\2\u00c0\u00c2"+
		"\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c2\u00c3\7b\2\2\u00c3\u00c4\7b\2\2\u00c4"+
		"\u00c5\7b\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\5\7\4\2\u00c7\32\3\2\2\2"+
		"\u00c8\u00cc\7^\2\2\u00c9\u00cb\t\7\2\2\u00ca\u00c9\3\2\2\2\u00cb\u00ce"+
		"\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\34\3\2\2\2\u00ce"+
		"\u00cc\3\2\2\2\u00cf\u00d3\5\25\13\2\u00d0\u00d3\n\b\2\2\u00d1\u00d3\5"+
		"\33\16\2\u00d2\u00cf\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d2\u00d1\3\2\2\2\u00d3"+
		"\36\3\2\2\2\u00d4\u00d6\5\5\3\2\u00d5\u00d4\3\2\2\2\u00d6\u00d9\3\2\2"+
		"\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00dd\3\2\2\2\u00d9\u00d7"+
		"\3\2\2\2\u00da\u00dc\5\35\17\2\u00db\u00da\3\2\2\2\u00dc\u00df\3\2\2\2"+
		"\u00dd\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00e0\3\2\2\2\u00df\u00dd"+
		"\3\2\2\2\u00e0\u00e1\7~\2\2\u00e1 \3\2\2\2\u00e2\u00e5\5\27\f\2\u00e3"+
		"\u00e5\5\31\r\2\u00e4\u00e2\3\2\2\2\u00e4\u00e3\3\2\2\2\u00e5\"\3\2\2"+
		"\2\u00e6\u00e8\5\5\3\2\u00e7\u00e6\3\2\2\2\u00e8\u00eb\3\2\2\2\u00e9\u00e7"+
		"\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00ec\3\2\2\2\u00eb\u00e9\3\2\2\2\u00ec"+
		"\u00ee\7~\2\2\u00ed\u00ef\5\37\20\2\u00ee\u00ed\3\2\2\2\u00ef\u00f0\3"+
		"\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2"+
		"\u00f3\5\7\4\2\u00f3$\3\2\2\2\u00f4\u00f6\t\t\2\2\u00f5\u00f4\3\2\2\2"+
		"\u00f6\u00f9\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00fe"+
		"\3\2\2\2\u00f9\u00f7\3\2\2\2\u00fa\u00ff\5\17\b\2\u00fb\u00ff\5\21\t\2"+
		"\u00fc\u00ff\5\23\n\2\u00fd\u00ff\5\r\7\2\u00fe\u00fa\3\2\2\2\u00fe\u00fb"+
		"\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe\u00fd\3\2\2\2\u00ff&\3\2\2\2\u0100"+
		"\u0102\t\n\2\2\u0101\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0101\3\2"+
		"\2\2\u0103\u0104\3\2\2\2\u0104(\3\2\2\2\u0105\u0107\n\5\2\2\u0106\u0105"+
		"\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109"+
		"*\3\2\2\2\u010a\u010b\7$\2\2\u010b\u010c\5\7\4\2\u010c,\3\2\2\2\u010d"+
		"\u010f\5\5\3\2\u010e\u010d\3\2\2\2\u010f\u0112\3\2\2\2\u0110\u010e\3\2"+
		"\2\2\u0110\u0111\3\2\2\2\u0111\u0113\3\2\2\2\u0112\u0110\3\2\2\2\u0113"+
		"\u0114\7$\2\2\u0114\u0115\7$\2\2\u0115\u0116\7$\2\2\u0116\u011a\3\2\2"+
		"\2\u0117\u0119\3\2\2\2\u0118\u0117\3\2\2\2\u0119\u011c\3\2\2\2\u011a\u011b"+
		"\3\2\2\2\u011a\u0118\3\2\2\2\u011b\u011d\3\2\2\2\u011c\u011a\3\2\2\2\u011d"+
		"\u011e\7$\2\2\u011e\u011f\7$\2\2\u011f\u0120\7$\2\2\u0120\u0121\3\2\2"+
		"\2\u0121\u0122\5\7\4\2\u0122.\3\2\2\2\u0123\u0124\t\3\2\2\u0124\60\3\2"+
		"\2\2\35\2L[ad{\u0084\u008d\u0096\u00a1\u00a3\u00af\u00ba\u00bf\u00cc\u00d2"+
		"\u00d7\u00dd\u00e4\u00e9\u00f0\u00f7\u00fe\u0103\u0108\u0110\u011a\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}