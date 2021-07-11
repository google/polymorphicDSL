// Generated from GherkinCommonLexer.g4 by ANTLR 4.9
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
public class GherkinCommonLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DOCSTRING=1, DATA_ROW=2, GHERKIN_STEP_KEYWORD=3, INT=4, TEXT_IN_DOUBLE_QUOTES=5, 
		END_QUOTE=6;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
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
			null, "DOCSTRING", "DATA_ROW", "GHERKIN_STEP_KEYWORD", "INT", "TEXT_IN_DOUBLE_QUOTES", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\b\u00dd\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\3\7\3\61\n\3\f\3\16"+
		"\3\64\13\3\3\3\6\3\67\n\3\r\3\16\38\3\3\5\3<\n\3\3\4\3\4\3\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7S\n"+
		"\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\\\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5"+
		"\te\n\t\3\n\3\n\3\n\3\n\3\13\7\13l\n\13\f\13\16\13o\13\13\3\13\3\13\3"+
		"\13\3\13\3\13\7\13v\n\13\f\13\16\13y\13\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\f\7\f\u0082\n\f\f\f\16\f\u0085\13\f\3\f\3\f\3\f\3\f\3\f\7\f\u008c"+
		"\n\f\f\f\16\f\u008f\13\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\7\r\u0099\n\r"+
		"\f\r\16\r\u009c\13\r\3\16\3\16\3\16\5\16\u00a1\n\16\3\17\7\17\u00a4\n"+
		"\17\f\17\16\17\u00a7\13\17\3\17\7\17\u00aa\n\17\f\17\16\17\u00ad\13\17"+
		"\3\17\3\17\3\20\3\20\5\20\u00b3\n\20\3\21\7\21\u00b6\n\21\f\21\16\21\u00b9"+
		"\13\21\3\21\3\21\6\21\u00bd\n\21\r\21\16\21\u00be\3\21\3\21\3\22\7\22"+
		"\u00c4\n\22\f\22\16\22\u00c7\13\22\3\22\3\22\3\22\3\22\5\22\u00cd\n\22"+
		"\3\23\6\23\u00d0\n\23\r\23\16\23\u00d1\3\24\6\24\u00d5\n\24\r\24\16\24"+
		"\u00d6\3\25\3\25\3\25\3\26\3\26\4w\u008d\2\27\3\2\5\2\7\2\t\2\13\2\r\2"+
		"\17\2\21\2\23\2\25\2\27\2\31\2\33\2\35\2\37\3!\4#\5%\6\'\7)\b+\2\3\2\t"+
		"\5\2\13\f\17\17\"\"\4\2\f\f\17\17\6\2\13\f\17\17\"\"@@\5\2\f\f^^~~\6\2"+
		"\f\f\17\17^^~~\3\2\62;\3\2$$\2\u00e8\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\3-\3\2\2\2\5\62\3\2\2\2\7=\3\2\2"+
		"\2\tB\3\2\2\2\13G\3\2\2\2\rR\3\2\2\2\17[\3\2\2\2\21d\3\2\2\2\23f\3\2\2"+
		"\2\25m\3\2\2\2\27\u0083\3\2\2\2\31\u0096\3\2\2\2\33\u00a0\3\2\2\2\35\u00a5"+
		"\3\2\2\2\37\u00b2\3\2\2\2!\u00b7\3\2\2\2#\u00c5\3\2\2\2%\u00cf\3\2\2\2"+
		"\'\u00d4\3\2\2\2)\u00d8\3\2\2\2+\u00db\3\2\2\2-.\t\2\2\2.\4\3\2\2\2/\61"+
		"\5\3\2\2\60/\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63;\3\2"+
		"\2\2\64\62\3\2\2\2\65\67\t\3\2\2\66\65\3\2\2\2\678\3\2\2\28\66\3\2\2\2"+
		"89\3\2\2\29<\3\2\2\2:<\7\2\2\3;\66\3\2\2\2;:\3\2\2\2<\6\3\2\2\2=>\7C\2"+
		"\2>?\7p\2\2?@\7f\2\2@A\7\"\2\2A\b\3\2\2\2BC\7D\2\2CD\7w\2\2DE\7v\2\2E"+
		"F\7\"\2\2F\n\3\2\2\2GH\7,\2\2HI\7\"\2\2I\f\3\2\2\2JK\7I\2\2KL\7k\2\2L"+
		"M\7x\2\2MN\7g\2\2NO\7p\2\2OS\7\"\2\2PS\5\7\4\2QS\5\t\5\2RJ\3\2\2\2RP\3"+
		"\2\2\2RQ\3\2\2\2S\16\3\2\2\2TU\7Y\2\2UV\7j\2\2VW\7g\2\2WX\7p\2\2X\\\7"+
		"\"\2\2Y\\\5\7\4\2Z\\\5\t\5\2[T\3\2\2\2[Y\3\2\2\2[Z\3\2\2\2\\\20\3\2\2"+
		"\2]^\7V\2\2^_\7j\2\2_`\7g\2\2`a\7p\2\2ae\7\"\2\2be\5\7\4\2ce\5\t\5\2d"+
		"]\3\2\2\2db\3\2\2\2dc\3\2\2\2e\22\3\2\2\2fg\7>\2\2gh\n\4\2\2hi\7@\2\2"+
		"i\24\3\2\2\2jl\5\3\2\2kj\3\2\2\2lo\3\2\2\2mk\3\2\2\2mn\3\2\2\2np\3\2\2"+
		"\2om\3\2\2\2pq\7$\2\2qr\7$\2\2rs\7$\2\2sw\3\2\2\2tv\13\2\2\2ut\3\2\2\2"+
		"vy\3\2\2\2wx\3\2\2\2wu\3\2\2\2xz\3\2\2\2yw\3\2\2\2z{\7$\2\2{|\7$\2\2|"+
		"}\7$\2\2}~\3\2\2\2~\177\5\5\3\2\177\26\3\2\2\2\u0080\u0082\5\3\2\2\u0081"+
		"\u0080\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2"+
		"\2\2\u0084\u0086\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u0087\7b\2\2\u0087"+
		"\u0088\7b\2\2\u0088\u0089\7b\2\2\u0089\u008d\3\2\2\2\u008a\u008c\13\2"+
		"\2\2\u008b\u008a\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008e\3\2\2\2\u008d"+
		"\u008b\3\2\2\2\u008e\u0090\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0091\7b"+
		"\2\2\u0091\u0092\7b\2\2\u0092\u0093\7b\2\2\u0093\u0094\3\2\2\2\u0094\u0095"+
		"\5\5\3\2\u0095\30\3\2\2\2\u0096\u009a\7^\2\2\u0097\u0099\t\5\2\2\u0098"+
		"\u0097\3\2\2\2\u0099\u009c\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2"+
		"\2\2\u009b\32\3\2\2\2\u009c\u009a\3\2\2\2\u009d\u00a1\5\23\n\2\u009e\u00a1"+
		"\n\6\2\2\u009f\u00a1\5\31\r\2\u00a0\u009d\3\2\2\2\u00a0\u009e\3\2\2\2"+
		"\u00a0\u009f\3\2\2\2\u00a1\34\3\2\2\2\u00a2\u00a4\5\3\2\2\u00a3\u00a2"+
		"\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6"+
		"\u00ab\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00aa\5\33\16\2\u00a9\u00a8\3"+
		"\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac"+
		"\u00ae\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\u00af\7~\2\2\u00af\36\3\2\2\2"+
		"\u00b0\u00b3\5\25\13\2\u00b1\u00b3\5\27\f\2\u00b2\u00b0\3\2\2\2\u00b2"+
		"\u00b1\3\2\2\2\u00b3 \3\2\2\2\u00b4\u00b6\5\3\2\2\u00b5\u00b4\3\2\2\2"+
		"\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00ba"+
		"\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00bc\7~\2\2\u00bb\u00bd\5\35\17\2"+
		"\u00bc\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf"+
		"\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1\5\5\3\2\u00c1\"\3\2\2\2\u00c2"+
		"\u00c4\5\3\2\2\u00c3\u00c2\3\2\2\2\u00c4\u00c7\3\2\2\2\u00c5\u00c3\3\2"+
		"\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00cc\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c8"+
		"\u00cd\5\r\7\2\u00c9\u00cd\5\17\b\2\u00ca\u00cd\5\21\t\2\u00cb\u00cd\5"+
		"\13\6\2\u00cc\u00c8\3\2\2\2\u00cc\u00c9\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc"+
		"\u00cb\3\2\2\2\u00cd$\3\2\2\2\u00ce\u00d0\t\7\2\2\u00cf\u00ce\3\2\2\2"+
		"\u00d0\u00d1\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2&\3"+
		"\2\2\2\u00d3\u00d5\n\b\2\2\u00d4\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6"+
		"\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7(\3\2\2\2\u00d8\u00d9\7$\2\2\u00d9"+
		"\u00da\5\5\3\2\u00da*\3\2\2\2\u00db\u00dc\t\3\2\2\u00dc,\3\2\2\2\30\2"+
		"\628;R[dmw\u0083\u008d\u009a\u00a0\u00a5\u00ab\u00b2\u00b7\u00be\u00c5"+
		"\u00cc\u00d1\u00d6\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}