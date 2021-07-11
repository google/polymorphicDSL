// Generated from TestCaseFactoryLexer.g4 by ANTLR 4.9
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
public class TestCaseFactoryLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		GIVEN_AN_ARBITRARY_TEST_SPECIFICATION=1, GIVEN_SPECIFICATION_HAS_N_PHRASES_START=2, 
		GIVEN_THE_TEST_SPECIFICATION_CHILD_START=3, THEN_N_TEST_CASES_ARE_PRODUCED_END=4, 
		THEN_EACH_TEST_CASE_HAS_N_PHRASES_START=5, PHRASES_END=6, GIVEN_SPECIFIC_TEST_CASE_FACTORY_IS_USED_START=7, 
		GIVEN_SPECIFIC_TEST_CASE_FACTORY_IS_USED_END=8, WHEN_TEST_SPECIFICATON_PROCESSED_BY_FACTORY=9, 
		DOCSTRING=10, DATA_ROW=11, GHERKIN_STEP_KEYWORD=12, INT=13, TEXT_IN_DOUBLE_QUOTES=14, 
		END_QUOTE=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"GIVEN_AN_ARBITRARY_TEST_SPECIFICATION", "GIVEN_SPECIFICATION_HAS_N_PHRASES_START", 
			"GIVEN_THE_TEST_SPECIFICATION_CHILD_START", "THEN_N_TEST_CASES_ARE_PRODUCED_END", 
			"THEN_EACH_TEST_CASE_HAS_N_PHRASES_START", "PHRASES_END", "GIVEN_SPECIFIC_TEST_CASE_FACTORY_IS_USED_START", 
			"GIVEN_SPECIFIC_TEST_CASE_FACTORY_IS_USED_END", "WHEN_TEST_SPECIFICATON_PROCESSED_BY_FACTORY", 
			"WS", "END", "AND", "BUT", "WILD", "GIVEN", "WHEN", "THEN", "CAPTURE_DATA", 
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
			null, "GIVEN_AN_ARBITRARY_TEST_SPECIFICATION", "GIVEN_SPECIFICATION_HAS_N_PHRASES_START", 
			"GIVEN_THE_TEST_SPECIFICATION_CHILD_START", "THEN_N_TEST_CASES_ARE_PRODUCED_END", 
			"THEN_EACH_TEST_CASE_HAS_N_PHRASES_START", "PHRASES_END", "GIVEN_SPECIFIC_TEST_CASE_FACTORY_IS_USED_START", 
			"GIVEN_SPECIFIC_TEST_CASE_FACTORY_IS_USED_END", "WHEN_TEST_SPECIFICATON_PROCESSED_BY_FACTORY", 
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


	public TestCaseFactoryLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "TestCaseFactoryLexer.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\21\u0203\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\5\5\u00c1\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f"+
		"\7\f\u0157\n\f\f\f\16\f\u015a\13\f\3\f\6\f\u015d\n\f\r\f\16\f\u015e\3"+
		"\f\5\f\u0162\n\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u0179\n\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\5\21\u0182\n\21\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\5\22\u018b\n\22\3\23\3\23\3\23\3\23\3\24\7\24\u0192\n\24\f\24\16"+
		"\24\u0195\13\24\3\24\3\24\3\24\3\24\3\24\7\24\u019c\n\24\f\24\16\24\u019f"+
		"\13\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\7\25\u01a8\n\25\f\25\16\25\u01ab"+
		"\13\25\3\25\3\25\3\25\3\25\3\25\7\25\u01b2\n\25\f\25\16\25\u01b5\13\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\7\26\u01bf\n\26\f\26\16\26\u01c2"+
		"\13\26\3\27\3\27\3\27\5\27\u01c7\n\27\3\30\7\30\u01ca\n\30\f\30\16\30"+
		"\u01cd\13\30\3\30\7\30\u01d0\n\30\f\30\16\30\u01d3\13\30\3\30\3\30\3\31"+
		"\3\31\5\31\u01d9\n\31\3\32\7\32\u01dc\n\32\f\32\16\32\u01df\13\32\3\32"+
		"\3\32\6\32\u01e3\n\32\r\32\16\32\u01e4\3\32\3\32\3\33\7\33\u01ea\n\33"+
		"\f\33\16\33\u01ed\13\33\3\33\3\33\3\33\3\33\5\33\u01f3\n\33\3\34\6\34"+
		"\u01f6\n\34\r\34\16\34\u01f7\3\35\6\35\u01fb\n\35\r\35\16\35\u01fc\3\36"+
		"\3\36\3\36\3\37\3\37\4\u019d\u01b3\2 \3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\2\27\2\31\2\33\2\35\2\37\2!\2#\2%\2\'\2)\2+\2-\2/\2\61\f\63"+
		"\r\65\16\67\179\20;\21=\2\3\2\n\5\2\13\f\17\17\"\"\4\2\f\f\17\17\6\2\13"+
		"\f\17\17\"\"@@\5\2\f\f^^~~\6\2\f\f\17\17^^~~\4\2\13\13\"\"\3\2\62;\3\2"+
		"$$\2\u020f\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\61\3\2\2\2\2\63"+
		"\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\3?\3\2\2\2\5"+
		"Y\3\2\2\2\7v\3\2\2\2\t\u00b3\3\2\2\2\13\u00d2\3\2\2\2\r\u00e7\3\2\2\2"+
		"\17\u00f2\3\2\2\2\21\u00f5\3\2\2\2\23\u0113\3\2\2\2\25\u0153\3\2\2\2\27"+
		"\u0158\3\2\2\2\31\u0163\3\2\2\2\33\u0168\3\2\2\2\35\u016d\3\2\2\2\37\u0178"+
		"\3\2\2\2!\u0181\3\2\2\2#\u018a\3\2\2\2%\u018c\3\2\2\2\'\u0193\3\2\2\2"+
		")\u01a9\3\2\2\2+\u01bc\3\2\2\2-\u01c6\3\2\2\2/\u01cb\3\2\2\2\61\u01d8"+
		"\3\2\2\2\63\u01dd\3\2\2\2\65\u01eb\3\2\2\2\67\u01f5\3\2\2\29\u01fa\3\2"+
		"\2\2;\u01fe\3\2\2\2=\u0201\3\2\2\2?@\5\65\33\2@A\7c\2\2AB\7\"\2\2BC\7"+
		"$\2\2CD\7V\2\2DE\7g\2\2EF\7u\2\2FG\7v\2\2GH\7\"\2\2HI\7U\2\2IJ\7r\2\2"+
		"JK\7g\2\2KL\7e\2\2LM\7k\2\2MN\7h\2\2NO\7k\2\2OP\7e\2\2PQ\7c\2\2QR\7v\2"+
		"\2RS\7k\2\2ST\7q\2\2TU\7p\2\2UV\7$\2\2VW\3\2\2\2WX\5\27\f\2X\4\3\2\2\2"+
		"YZ\5\65\33\2Z[\7v\2\2[\\\7j\2\2\\]\7g\2\2]^\7\"\2\2^_\7V\2\2_`\7g\2\2"+
		"`a\7u\2\2ab\7v\2\2bc\7\"\2\2cd\7U\2\2de\7r\2\2ef\7g\2\2fg\7e\2\2gh\7k"+
		"\2\2hi\7h\2\2ij\7k\2\2jk\7e\2\2kl\7c\2\2lm\7v\2\2mn\7k\2\2no\7q\2\2op"+
		"\7p\2\2pq\7\"\2\2qr\7j\2\2rs\7c\2\2st\7u\2\2tu\7\"\2\2u\6\3\2\2\2vw\5"+
		"\65\33\2wx\7v\2\2xy\7j\2\2yz\7g\2\2z{\7\"\2\2{|\7V\2\2|}\7g\2\2}~\7u\2"+
		"\2~\177\7v\2\2\177\u0080\7\"\2\2\u0080\u0081\7U\2\2\u0081\u0082\7r\2\2"+
		"\u0082\u0083\7g\2\2\u0083\u0084\7e\2\2\u0084\u0085\7k\2\2\u0085\u0086"+
		"\7h\2\2\u0086\u0087\7k\2\2\u0087\u0088\7e\2\2\u0088\u0089\7c\2\2\u0089"+
		"\u008a\7v\2\2\u008a\u008b\7k\2\2\u008b\u008c\7q\2\2\u008c\u008d\7p\2\2"+
		"\u008d\u008e\7\"\2\2\u008e\u008f\7j\2\2\u008f\u0090\7c\2\2\u0090\u0091"+
		"\7u\2\2\u0091\u0092\7\"\2\2\u0092\u0093\7c\2\2\u0093\u0094\7\"\2\2\u0094"+
		"\u0095\7e\2\2\u0095\u0096\7j\2\2\u0096\u0097\7k\2\2\u0097\u0098\7n\2\2"+
		"\u0098\u0099\7f\2\2\u0099\u009a\7\"\2\2\u009a\u009b\7V\2\2\u009b\u009c"+
		"\7g\2\2\u009c\u009d\7u\2\2\u009d\u009e\7v\2\2\u009e\u009f\7\"\2\2\u009f"+
		"\u00a0\7U\2\2\u00a0\u00a1\7r\2\2\u00a1\u00a2\7g\2\2\u00a2\u00a3\7e\2\2"+
		"\u00a3\u00a4\7k\2\2\u00a4\u00a5\7h\2\2\u00a5\u00a6\7k\2\2\u00a6\u00a7"+
		"\7e\2\2\u00a7\u00a8\7c\2\2\u00a8\u00a9\7v\2\2\u00a9\u00aa\7k\2\2\u00aa"+
		"\u00ab\7q\2\2\u00ab\u00ac\7p\2\2\u00ac\u00ad\7\"\2\2\u00ad\u00ae\7y\2"+
		"\2\u00ae\u00af\7k\2\2\u00af\u00b0\7v\2\2\u00b0\u00b1\7j\2\2\u00b1\u00b2"+
		"\7\"\2\2\u00b2\b\3\2\2\2\u00b3\u00b4\5\65\33\2\u00b4\u00b5\7\"\2\2\u00b5"+
		"\u00b6\7v\2\2\u00b6\u00b7\7g\2\2\u00b7\u00b8\7u\2\2\u00b8\u00b9\7v\2\2"+
		"\u00b9\u00ba\7\"\2\2\u00ba\u00bb\7e\2\2\u00bb\u00bc\7c\2\2\u00bc\u00bd"+
		"\7u\2\2\u00bd\u00be\7g\2\2\u00be\u00c0\3\2\2\2\u00bf\u00c1\7u\2\2\u00c0"+
		"\u00bf\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c3\7\""+
		"\2\2\u00c3\u00c4\7c\2\2\u00c4\u00c5\7t\2\2\u00c5\u00c6\7g\2\2\u00c6\u00c7"+
		"\7\"\2\2\u00c7\u00c8\7r\2\2\u00c8\u00c9\7t\2\2\u00c9\u00ca\7q\2\2\u00ca"+
		"\u00cb\7f\2\2\u00cb\u00cc\7w\2\2\u00cc\u00cd\7e\2\2\u00cd\u00ce\7g\2\2"+
		"\u00ce\u00cf\7f\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\5\27\f\2\u00d1\n\3"+
		"\2\2\2\u00d2\u00d3\5\65\33\2\u00d3\u00d4\7g\2\2\u00d4\u00d5\7c\2\2\u00d5"+
		"\u00d6\7e\2\2\u00d6\u00d7\7j\2\2\u00d7\u00d8\7\"\2\2\u00d8\u00d9\7v\2"+
		"\2\u00d9\u00da\7g\2\2\u00da\u00db\7u\2\2\u00db\u00dc\7v\2\2\u00dc\u00dd"+
		"\7\"\2\2\u00dd\u00de\7e\2\2\u00de\u00df\7c\2\2\u00df\u00e0\7u\2\2\u00e0"+
		"\u00e1\7g\2\2\u00e1\u00e2\7\"\2\2\u00e2\u00e3\7j\2\2\u00e3\u00e4\7c\2"+
		"\2\u00e4\u00e5\7u\2\2\u00e5\u00e6\7\"\2\2\u00e6\f\3\2\2\2\u00e7\u00e8"+
		"\7\"\2\2\u00e8\u00e9\7r\2\2\u00e9\u00ea\7j\2\2\u00ea\u00eb\7t\2\2\u00eb"+
		"\u00ec\7c\2\2\u00ec\u00ed\7u\2\2\u00ed\u00ee\7g\2\2\u00ee\u00ef\7u\2\2"+
		"\u00ef\u00f0\3\2\2\2\u00f0\u00f1\5\27\f\2\u00f1\16\3\2\2\2\u00f2\u00f3"+
		"\5\65\33\2\u00f3\u00f4\7$\2\2\u00f4\20\3\2\2\2\u00f5\u00f6\7$\2\2\u00f6"+
		"\u00f7\7\"\2\2\u00f7\u00f8\7v\2\2\u00f8\u00f9\7g\2\2\u00f9\u00fa\7u\2"+
		"\2\u00fa\u00fb\7v\2\2\u00fb\u00fc\7\"\2\2\u00fc\u00fd\7e\2\2\u00fd\u00fe"+
		"\7c\2\2\u00fe\u00ff\7u\2\2\u00ff\u0100\7g\2\2\u0100\u0101\7\"\2\2\u0101"+
		"\u0102\7h\2\2\u0102\u0103\7c\2\2\u0103\u0104\7e\2\2\u0104\u0105\7v\2\2"+
		"\u0105\u0106\7q\2\2\u0106\u0107\7t\2\2\u0107\u0108\7{\2\2\u0108\u0109"+
		"\7\"\2\2\u0109\u010a\7k\2\2\u010a\u010b\7u\2\2\u010b\u010c\7\"\2\2\u010c"+
		"\u010d\7w\2\2\u010d\u010e\7u\2\2\u010e\u010f\7g\2\2\u010f\u0110\7f\2\2"+
		"\u0110\u0111\3\2\2\2\u0111\u0112\5\27\f\2\u0112\22\3\2\2\2\u0113\u0114"+
		"\5\65\33\2\u0114\u0115\7v\2\2\u0115\u0116\7j\2\2\u0116\u0117\7g\2\2\u0117"+
		"\u0118\7\"\2\2\u0118\u0119\7V\2\2\u0119\u011a\7g\2\2\u011a\u011b\7u\2"+
		"\2\u011b\u011c\7v\2\2\u011c\u011d\7\"\2\2\u011d\u011e\7U\2\2\u011e\u011f"+
		"\7r\2\2\u011f\u0120\7g\2\2\u0120\u0121\7e\2\2\u0121\u0122\7k\2\2\u0122"+
		"\u0123\7h\2\2\u0123\u0124\7k\2\2\u0124\u0125\7e\2\2\u0125\u0126\7c\2\2"+
		"\u0126\u0127\7v\2\2\u0127\u0128\7k\2\2\u0128\u0129\7q\2\2\u0129\u012a"+
		"\7p\2\2\u012a\u012b\7\"\2\2\u012b\u012c\7k\2\2\u012c\u012d\7u\2\2\u012d"+
		"\u012e\7\"\2\2\u012e\u012f\7r\2\2\u012f\u0130\7t\2\2\u0130\u0131\7q\2"+
		"\2\u0131\u0132\7e\2\2\u0132\u0133\7g\2\2\u0133\u0134\7u\2\2\u0134\u0135"+
		"\7u\2\2\u0135\u0136\7g\2\2\u0136\u0137\7f\2\2\u0137\u0138\7\"\2\2\u0138"+
		"\u0139\7d\2\2\u0139\u013a\7{\2\2\u013a\u013b\7\"\2\2\u013b\u013c\7v\2"+
		"\2\u013c\u013d\7j\2\2\u013d\u013e\7g\2\2\u013e\u013f\7\"\2\2\u013f\u0140"+
		"\7V\2\2\u0140\u0141\7g\2\2\u0141\u0142\7u\2\2\u0142\u0143\7v\2\2\u0143"+
		"\u0144\7\"\2\2\u0144\u0145\7E\2\2\u0145\u0146\7c\2\2\u0146\u0147\7u\2"+
		"\2\u0147\u0148\7g\2\2\u0148\u0149\7\"\2\2\u0149\u014a\7H\2\2\u014a\u014b"+
		"\7c\2\2\u014b\u014c\7e\2\2\u014c\u014d\7v\2\2\u014d\u014e\7q\2\2\u014e"+
		"\u014f\7t\2\2\u014f\u0150\7{\2\2\u0150\u0151\3\2\2\2\u0151\u0152\5\27"+
		"\f\2\u0152\24\3\2\2\2\u0153\u0154\t\2\2\2\u0154\26\3\2\2\2\u0155\u0157"+
		"\5\25\13\2\u0156\u0155\3\2\2\2\u0157\u015a\3\2\2\2\u0158\u0156\3\2\2\2"+
		"\u0158\u0159\3\2\2\2\u0159\u0161\3\2\2\2\u015a\u0158\3\2\2\2\u015b\u015d"+
		"\t\3\2\2\u015c\u015b\3\2\2\2\u015d\u015e\3\2\2\2\u015e\u015c\3\2\2\2\u015e"+
		"\u015f\3\2\2\2\u015f\u0162\3\2\2\2\u0160\u0162\7\2\2\3\u0161\u015c\3\2"+
		"\2\2\u0161\u0160\3\2\2\2\u0162\30\3\2\2\2\u0163\u0164\7C\2\2\u0164\u0165"+
		"\7p\2\2\u0165\u0166\7f\2\2\u0166\u0167\7\"\2\2\u0167\32\3\2\2\2\u0168"+
		"\u0169\7D\2\2\u0169\u016a\7w\2\2\u016a\u016b\7v\2\2\u016b\u016c\7\"\2"+
		"\2\u016c\34\3\2\2\2\u016d\u016e\7,\2\2\u016e\u016f\7\"\2\2\u016f\36\3"+
		"\2\2\2\u0170\u0171\7I\2\2\u0171\u0172\7k\2\2\u0172\u0173\7x\2\2\u0173"+
		"\u0174\7g\2\2\u0174\u0175\7p\2\2\u0175\u0179\7\"\2\2\u0176\u0179\5\31"+
		"\r\2\u0177\u0179\5\33\16\2\u0178\u0170\3\2\2\2\u0178\u0176\3\2\2\2\u0178"+
		"\u0177\3\2\2\2\u0179 \3\2\2\2\u017a\u017b\7Y\2\2\u017b\u017c\7j\2\2\u017c"+
		"\u017d\7g\2\2\u017d\u017e\7p\2\2\u017e\u0182\7\"\2\2\u017f\u0182\5\31"+
		"\r\2\u0180\u0182\5\33\16\2\u0181\u017a\3\2\2\2\u0181\u017f\3\2\2\2\u0181"+
		"\u0180\3\2\2\2\u0182\"\3\2\2\2\u0183\u0184\7V\2\2\u0184\u0185\7j\2\2\u0185"+
		"\u0186\7g\2\2\u0186\u0187\7p\2\2\u0187\u018b\7\"\2\2\u0188\u018b\5\31"+
		"\r\2\u0189\u018b\5\33\16\2\u018a\u0183\3\2\2\2\u018a\u0188\3\2\2\2\u018a"+
		"\u0189\3\2\2\2\u018b$\3\2\2\2\u018c\u018d\7>\2\2\u018d\u018e\n\4\2\2\u018e"+
		"\u018f\7@\2\2\u018f&\3\2\2\2\u0190\u0192\5\25\13\2\u0191\u0190\3\2\2\2"+
		"\u0192\u0195\3\2\2\2\u0193\u0191\3\2\2\2\u0193\u0194\3\2\2\2\u0194\u0196"+
		"\3\2\2\2\u0195\u0193\3\2\2\2\u0196\u0197\7$\2\2\u0197\u0198\7$\2\2\u0198"+
		"\u0199\7$\2\2\u0199\u019d\3\2\2\2\u019a\u019c\13\2\2\2\u019b\u019a\3\2"+
		"\2\2\u019c\u019f\3\2\2\2\u019d\u019e\3\2\2\2\u019d\u019b\3\2\2\2\u019e"+
		"\u01a0\3\2\2\2\u019f\u019d\3\2\2\2\u01a0\u01a1\7$\2\2\u01a1\u01a2\7$\2"+
		"\2\u01a2\u01a3\7$\2\2\u01a3\u01a4\3\2\2\2\u01a4\u01a5\5\27\f\2\u01a5("+
		"\3\2\2\2\u01a6\u01a8\5\25\13\2\u01a7\u01a6\3\2\2\2\u01a8\u01ab\3\2\2\2"+
		"\u01a9\u01a7\3\2\2\2\u01a9\u01aa\3\2\2\2\u01aa\u01ac\3\2\2\2\u01ab\u01a9"+
		"\3\2\2\2\u01ac\u01ad\7b\2\2\u01ad\u01ae\7b\2\2\u01ae\u01af\7b\2\2\u01af"+
		"\u01b3\3\2\2\2\u01b0\u01b2\13\2\2\2\u01b1\u01b0\3\2\2\2\u01b2\u01b5\3"+
		"\2\2\2\u01b3\u01b4\3\2\2\2\u01b3\u01b1\3\2\2\2\u01b4\u01b6\3\2\2\2\u01b5"+
		"\u01b3\3\2\2\2\u01b6\u01b7\7b\2\2\u01b7\u01b8\7b\2\2\u01b8\u01b9\7b\2"+
		"\2\u01b9\u01ba\3\2\2\2\u01ba\u01bb\5\27\f\2\u01bb*\3\2\2\2\u01bc\u01c0"+
		"\7^\2\2\u01bd\u01bf\t\5\2\2\u01be\u01bd\3\2\2\2\u01bf\u01c2\3\2\2\2\u01c0"+
		"\u01be\3\2\2\2\u01c0\u01c1\3\2\2\2\u01c1,\3\2\2\2\u01c2\u01c0\3\2\2\2"+
		"\u01c3\u01c7\5%\23\2\u01c4\u01c7\n\6\2\2\u01c5\u01c7\5+\26\2\u01c6\u01c3"+
		"\3\2\2\2\u01c6\u01c4\3\2\2\2\u01c6\u01c5\3\2\2\2\u01c7.\3\2\2\2\u01c8"+
		"\u01ca\5\25\13\2\u01c9\u01c8\3\2\2\2\u01ca\u01cd\3\2\2\2\u01cb\u01c9\3"+
		"\2\2\2\u01cb\u01cc\3\2\2\2\u01cc\u01d1\3\2\2\2\u01cd\u01cb\3\2\2\2\u01ce"+
		"\u01d0\5-\27\2\u01cf\u01ce\3\2\2\2\u01d0\u01d3\3\2\2\2\u01d1\u01cf\3\2"+
		"\2\2\u01d1\u01d2\3\2\2\2\u01d2\u01d4\3\2\2\2\u01d3\u01d1\3\2\2\2\u01d4"+
		"\u01d5\7~\2\2\u01d5\60\3\2\2\2\u01d6\u01d9\5\'\24\2\u01d7\u01d9\5)\25"+
		"\2\u01d8\u01d6\3\2\2\2\u01d8\u01d7\3\2\2\2\u01d9\62\3\2\2\2\u01da\u01dc"+
		"\5\25\13\2\u01db\u01da\3\2\2\2\u01dc\u01df\3\2\2\2\u01dd\u01db\3\2\2\2"+
		"\u01dd\u01de\3\2\2\2\u01de\u01e0\3\2\2\2\u01df\u01dd\3\2\2\2\u01e0\u01e2"+
		"\7~\2\2\u01e1\u01e3\5/\30\2\u01e2\u01e1\3\2\2\2\u01e3\u01e4\3\2\2\2\u01e4"+
		"\u01e2\3\2\2\2\u01e4\u01e5\3\2\2\2\u01e5\u01e6\3\2\2\2\u01e6\u01e7\5\27"+
		"\f\2\u01e7\64\3\2\2\2\u01e8\u01ea\t\7\2\2\u01e9\u01e8\3\2\2\2\u01ea\u01ed"+
		"\3\2\2\2\u01eb\u01e9\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ec\u01f2\3\2\2\2\u01ed"+
		"\u01eb\3\2\2\2\u01ee\u01f3\5\37\20\2\u01ef\u01f3\5!\21\2\u01f0\u01f3\5"+
		"#\22\2\u01f1\u01f3\5\35\17\2\u01f2\u01ee\3\2\2\2\u01f2\u01ef\3\2\2\2\u01f2"+
		"\u01f0\3\2\2\2\u01f2\u01f1\3\2\2\2\u01f3\66\3\2\2\2\u01f4\u01f6\t\b\2"+
		"\2\u01f5\u01f4\3\2\2\2\u01f6\u01f7\3\2\2\2\u01f7\u01f5\3\2\2\2\u01f7\u01f8"+
		"\3\2\2\2\u01f88\3\2\2\2\u01f9\u01fb\n\t\2\2\u01fa\u01f9\3\2\2\2\u01fb"+
		"\u01fc\3\2\2\2\u01fc\u01fa\3\2\2\2\u01fc\u01fd\3\2\2\2\u01fd:\3\2\2\2"+
		"\u01fe\u01ff\7$\2\2\u01ff\u0200\5\27\f\2\u0200<\3\2\2\2\u0201\u0202\t"+
		"\3\2\2\u0202>\3\2\2\2\31\2\u00c0\u0158\u015e\u0161\u0178\u0181\u018a\u0193"+
		"\u019d\u01a9\u01b3\u01c0\u01c6\u01cb\u01d1\u01d8\u01dd\u01e4\u01eb\u01f2"+
		"\u01f7\u01fc\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}