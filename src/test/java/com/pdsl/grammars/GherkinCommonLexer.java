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
		DOCSTRING=1, DATA_ROW=2, GHERKIN_STEP_KEYWORD=3, INT=4, QUOTED_TEXT=5;
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
			"INT", "QUOTED_TEXT", "NEWLINE"
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
			null, "DOCSTRING", "DATA_ROW", "GHERKIN_STEP_KEYWORD", "INT", "QUOTED_TEXT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\7\u00dc\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\3\7\3/\n\3\f\3\16\3\62\13\3\3"+
		"\3\6\3\65\n\3\r\3\16\3\66\3\3\5\3:\n\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7Q\n\7\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\5\bZ\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\tc\n\t\3\n"+
		"\3\n\3\n\3\n\3\13\7\13j\n\13\f\13\16\13m\13\13\3\13\3\13\3\13\3\13\3\13"+
		"\7\13t\n\13\f\13\16\13w\13\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\7\f\u0080"+
		"\n\f\f\f\16\f\u0083\13\f\3\f\3\f\3\f\3\f\3\f\7\f\u008a\n\f\f\f\16\f\u008d"+
		"\13\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\7\r\u0097\n\r\f\r\16\r\u009a\13"+
		"\r\3\16\3\16\3\16\5\16\u009f\n\16\3\17\7\17\u00a2\n\17\f\17\16\17\u00a5"+
		"\13\17\3\17\7\17\u00a8\n\17\f\17\16\17\u00ab\13\17\3\17\3\17\3\20\3\20"+
		"\5\20\u00b1\n\20\3\21\7\21\u00b4\n\21\f\21\16\21\u00b7\13\21\3\21\3\21"+
		"\6\21\u00bb\n\21\r\21\16\21\u00bc\3\21\3\21\3\22\7\22\u00c2\n\22\f\22"+
		"\16\22\u00c5\13\22\3\22\3\22\3\22\3\22\5\22\u00cb\n\22\3\23\6\23\u00ce"+
		"\n\23\r\23\16\23\u00cf\3\24\3\24\7\24\u00d4\n\24\f\24\16\24\u00d7\13\24"+
		"\3\24\3\24\3\25\3\25\5u\u008b\u00d5\2\26\3\2\5\2\7\2\t\2\13\2\r\2\17\2"+
		"\21\2\23\2\25\2\27\2\31\2\33\2\35\2\37\3!\4#\5%\6\'\7)\2\3\2\b\5\2\13"+
		"\f\17\17\"\"\4\2\f\f\17\17\6\2\13\f\17\17\"\"@@\5\2\f\f^^~~\6\2\f\f\17"+
		"\17^^~~\3\2\62;\2\u00e7\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\3+\3\2\2\2\5\60\3\2\2\2\7;\3\2\2\2\t@\3\2\2\2\13E\3\2\2\2"+
		"\rP\3\2\2\2\17Y\3\2\2\2\21b\3\2\2\2\23d\3\2\2\2\25k\3\2\2\2\27\u0081\3"+
		"\2\2\2\31\u0094\3\2\2\2\33\u009e\3\2\2\2\35\u00a3\3\2\2\2\37\u00b0\3\2"+
		"\2\2!\u00b5\3\2\2\2#\u00c3\3\2\2\2%\u00cd\3\2\2\2\'\u00d1\3\2\2\2)\u00da"+
		"\3\2\2\2+,\t\2\2\2,\4\3\2\2\2-/\5\3\2\2.-\3\2\2\2/\62\3\2\2\2\60.\3\2"+
		"\2\2\60\61\3\2\2\2\619\3\2\2\2\62\60\3\2\2\2\63\65\t\3\2\2\64\63\3\2\2"+
		"\2\65\66\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\67:\3\2\2\28:\7\2\2\39\64"+
		"\3\2\2\298\3\2\2\2:\6\3\2\2\2;<\7C\2\2<=\7p\2\2=>\7f\2\2>?\7\"\2\2?\b"+
		"\3\2\2\2@A\7D\2\2AB\7w\2\2BC\7v\2\2CD\7\"\2\2D\n\3\2\2\2EF\7,\2\2FG\7"+
		"\"\2\2G\f\3\2\2\2HI\7I\2\2IJ\7k\2\2JK\7x\2\2KL\7g\2\2LM\7p\2\2MQ\7\"\2"+
		"\2NQ\5\7\4\2OQ\5\t\5\2PH\3\2\2\2PN\3\2\2\2PO\3\2\2\2Q\16\3\2\2\2RS\7Y"+
		"\2\2ST\7j\2\2TU\7g\2\2UV\7p\2\2VZ\7\"\2\2WZ\5\7\4\2XZ\5\t\5\2YR\3\2\2"+
		"\2YW\3\2\2\2YX\3\2\2\2Z\20\3\2\2\2[\\\7V\2\2\\]\7j\2\2]^\7g\2\2^_\7p\2"+
		"\2_c\7\"\2\2`c\5\7\4\2ac\5\t\5\2b[\3\2\2\2b`\3\2\2\2ba\3\2\2\2c\22\3\2"+
		"\2\2de\7>\2\2ef\n\4\2\2fg\7@\2\2g\24\3\2\2\2hj\5\3\2\2ih\3\2\2\2jm\3\2"+
		"\2\2ki\3\2\2\2kl\3\2\2\2ln\3\2\2\2mk\3\2\2\2no\7$\2\2op\7$\2\2pq\7$\2"+
		"\2qu\3\2\2\2rt\13\2\2\2sr\3\2\2\2tw\3\2\2\2uv\3\2\2\2us\3\2\2\2vx\3\2"+
		"\2\2wu\3\2\2\2xy\7$\2\2yz\7$\2\2z{\7$\2\2{|\3\2\2\2|}\5\5\3\2}\26\3\2"+
		"\2\2~\u0080\5\3\2\2\177~\3\2\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2\2\2"+
		"\u0081\u0082\3\2\2\2\u0082\u0084\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0085"+
		"\7b\2\2\u0085\u0086\7b\2\2\u0086\u0087\7b\2\2\u0087\u008b\3\2\2\2\u0088"+
		"\u008a\13\2\2\2\u0089\u0088\3\2\2\2\u008a\u008d\3\2\2\2\u008b\u008c\3"+
		"\2\2\2\u008b\u0089\3\2\2\2\u008c\u008e\3\2\2\2\u008d\u008b\3\2\2\2\u008e"+
		"\u008f\7b\2\2\u008f\u0090\7b\2\2\u0090\u0091\7b\2\2\u0091\u0092\3\2\2"+
		"\2\u0092\u0093\5\5\3\2\u0093\30\3\2\2\2\u0094\u0098\7^\2\2\u0095\u0097"+
		"\t\5\2\2\u0096\u0095\3\2\2\2\u0097\u009a\3\2\2\2\u0098\u0096\3\2\2\2\u0098"+
		"\u0099\3\2\2\2\u0099\32\3\2\2\2\u009a\u0098\3\2\2\2\u009b\u009f\5\23\n"+
		"\2\u009c\u009f\n\6\2\2\u009d\u009f\5\31\r\2\u009e\u009b\3\2\2\2\u009e"+
		"\u009c\3\2\2\2\u009e\u009d\3\2\2\2\u009f\34\3\2\2\2\u00a0\u00a2\5\3\2"+
		"\2\u00a1\u00a0\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4"+
		"\3\2\2\2\u00a4\u00a9\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a8\5\33\16\2"+
		"\u00a7\u00a6\3\2\2\2\u00a8\u00ab\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa"+
		"\3\2\2\2\u00aa\u00ac\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ac\u00ad\7~\2\2\u00ad"+
		"\36\3\2\2\2\u00ae\u00b1\5\25\13\2\u00af\u00b1\5\27\f\2\u00b0\u00ae\3\2"+
		"\2\2\u00b0\u00af\3\2\2\2\u00b1 \3\2\2\2\u00b2\u00b4\5\3\2\2\u00b3\u00b2"+
		"\3\2\2\2\u00b4\u00b7\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6"+
		"\u00b8\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8\u00ba\7~\2\2\u00b9\u00bb\5\35"+
		"\17\2\u00ba\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc"+
		"\u00bd\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bf\5\5\3\2\u00bf\"\3\2\2\2"+
		"\u00c0\u00c2\5\3\2\2\u00c1\u00c0\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3\u00c1"+
		"\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00ca\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6"+
		"\u00cb\5\r\7\2\u00c7\u00cb\5\17\b\2\u00c8\u00cb\5\21\t\2\u00c9\u00cb\5"+
		"\13\6\2\u00ca\u00c6\3\2\2\2\u00ca\u00c7\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca"+
		"\u00c9\3\2\2\2\u00cb$\3\2\2\2\u00cc\u00ce\t\7\2\2\u00cd\u00cc\3\2\2\2"+
		"\u00ce\u00cf\3\2\2\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0&\3"+
		"\2\2\2\u00d1\u00d5\7$\2\2\u00d2\u00d4\13\2\2\2\u00d3\u00d2\3\2\2\2\u00d4"+
		"\u00d7\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d6\u00d8\3\2"+
		"\2\2\u00d7\u00d5\3\2\2\2\u00d8\u00d9\7$\2\2\u00d9(\3\2\2\2\u00da\u00db"+
		"\t\3\2\2\u00db*\3\2\2\2\30\2\60\669PYbku\u0081\u008b\u0098\u009e\u00a3"+
		"\u00a9\u00b0\u00b5\u00bc\u00c3\u00ca\u00cf\u00d5\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}