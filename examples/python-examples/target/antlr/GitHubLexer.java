// Generated from ./tests/resources/antlr4/GitHubLexer.g4 by ANTLR 4.9.1
package com.pdsl.python;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GitHubLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		GIVEN_THE_TEST_RESOURCE=1, THEN_USER_PROFILE_VALIDITY=2, USER_PROFILE_HAS_LOGIN=3, 
		USER_PROFILE_HAS_NAME=4, USER_PROFILE_HAS_ID=5, USER_PROFILE_HAS_TYPE=6, 
		USER_PROFILE_HAS=7, GHERKIN_STEP_KEYWORD=8, BODY=9, CLOSE_QUOTE=10;
	public static final int
		QUOTED_MODE=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "QUOTED_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"GIVEN_THE_TEST_RESOURCE", "THEN_USER_PROFILE_VALIDITY", "USER_PROFILE_HAS_LOGIN", 
			"USER_PROFILE_HAS_NAME", "USER_PROFILE_HAS_ID", "USER_PROFILE_HAS_TYPE", 
			"USER_PROFILE_HAS", "WS", "END", "AND", "BUT", "WILD", "GIVEN", "WHEN", 
			"THEN", "GHERKIN_STEP_KEYWORD", "NEWLINE", "BODY", "CLOSE_QUOTE"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, "'the user profile has the '"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "GIVEN_THE_TEST_RESOURCE", "THEN_USER_PROFILE_VALIDITY", "USER_PROFILE_HAS_LOGIN", 
			"USER_PROFILE_HAS_NAME", "USER_PROFILE_HAS_ID", "USER_PROFILE_HAS_TYPE", 
			"USER_PROFILE_HAS", "GHERKIN_STEP_KEYWORD", "BODY", "CLOSE_QUOTE"
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


	public GitHubLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "GitHubLexer.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\f\u0106\b\1\b\1\4"+
		"\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n"+
		"\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\5\3\\\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\7\n\u00b8\n\n\f\n\16\n\u00bb\13\n\3\n"+
		"\6\n\u00be\n\n\r\n\16\n\u00bf\3\n\5\n\u00c3\n\n\3\13\3\13\3\13\3\13\3"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\5\16\u00da\n\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00e3\n"+
		"\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00ec\n\20\3\21\7\21\u00ef"+
		"\n\21\f\21\16\21\u00f2\13\21\3\21\3\21\3\21\3\21\5\21\u00f8\n\21\3\22"+
		"\3\22\3\23\6\23\u00fd\n\23\r\23\16\23\u00fe\3\24\3\24\5\24\u0103\n\24"+
		"\3\24\3\24\2\2\25\4\3\6\4\b\5\n\6\f\7\16\b\20\t\22\2\24\2\26\2\30\2\32"+
		"\2\34\2\36\2 \2\"\n$\2&\13(\f\4\2\3\5\5\2\13\f\17\17\"\"\4\2\f\f\17\17"+
		"\3\2$$\2\u010b\2\4\3\2\2\2\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3\2"+
		"\2\2\2\16\3\2\2\2\2\20\3\2\2\2\2\"\3\2\2\2\3&\3\2\2\2\3(\3\2\2\2\4*\3"+
		"\2\2\2\6A\3\2\2\2\bo\3\2\2\2\n{\3\2\2\2\f\u0086\3\2\2\2\16\u008f\3\2\2"+
		"\2\20\u009a\3\2\2\2\22\u00b4\3\2\2\2\24\u00b9\3\2\2\2\26\u00c4\3\2\2\2"+
		"\30\u00c9\3\2\2\2\32\u00ce\3\2\2\2\34\u00d9\3\2\2\2\36\u00e2\3\2\2\2 "+
		"\u00eb\3\2\2\2\"\u00f0\3\2\2\2$\u00f9\3\2\2\2&\u00fc\3\2\2\2(\u0100\3"+
		"\2\2\2*+\5\"\21\2+,\7v\2\2,-\7j\2\2-.\7g\2\2./\7\"\2\2/\60\7v\2\2\60\61"+
		"\7g\2\2\61\62\7u\2\2\62\63\7v\2\2\63\64\7\"\2\2\64\65\7t\2\2\65\66\7g"+
		"\2\2\66\67\7u\2\2\678\7q\2\289\7w\2\29:\7t\2\2:;\7e\2\2;<\7g\2\2<=\7\""+
		"\2\2=>\7$\2\2>?\3\2\2\2?@\b\2\2\2@\5\3\2\2\2AB\5\"\21\2BC\7v\2\2CD\7j"+
		"\2\2DE\7g\2\2EF\7\"\2\2FG\7w\2\2GH\7u\2\2HI\7g\2\2IJ\7t\2\2JK\7\"\2\2"+
		"KL\7r\2\2LM\7t\2\2MN\7q\2\2NO\7h\2\2OP\7k\2\2PQ\7n\2\2QR\7g\2\2RS\7\""+
		"\2\2ST\7k\2\2TU\7u\2\2UV\7\"\2\2V[\3\2\2\2WX\7P\2\2XY\7Q\2\2YZ\7V\2\2"+
		"Z\\\7\"\2\2[W\3\2\2\2[\\\3\2\2\2\\]\3\2\2\2]^\7g\2\2^_\7z\2\2_`\7k\2\2"+
		"`a\7u\2\2ab\7v\2\2bc\7\"\2\2cd\7k\2\2de\7p\2\2ef\7\"\2\2fg\7I\2\2gh\7"+
		"k\2\2hi\7v\2\2ij\7J\2\2jk\7w\2\2kl\7d\2\2lm\3\2\2\2mn\5\24\n\2n\7\3\2"+
		"\2\2op\5\"\21\2pq\5\20\b\2qr\7n\2\2rs\7q\2\2st\7i\2\2tu\7k\2\2uv\7p\2"+
		"\2vw\7\"\2\2wx\7$\2\2xy\3\2\2\2yz\b\4\2\2z\t\3\2\2\2{|\5\"\21\2|}\5\20"+
		"\b\2}~\7p\2\2~\177\7c\2\2\177\u0080\7o\2\2\u0080\u0081\7g\2\2\u0081\u0082"+
		"\7\"\2\2\u0082\u0083\7$\2\2\u0083\u0084\3\2\2\2\u0084\u0085\b\5\2\2\u0085"+
		"\13\3\2\2\2\u0086\u0087\5\"\21\2\u0087\u0088\5\20\b\2\u0088\u0089\7k\2"+
		"\2\u0089\u008a\7f\2\2\u008a\u008b\7\"\2\2\u008b\u008c\7$\2\2\u008c\u008d"+
		"\3\2\2\2\u008d\u008e\b\6\2\2\u008e\r\3\2\2\2\u008f\u0090\5\"\21\2\u0090"+
		"\u0091\5\20\b\2\u0091\u0092\7v\2\2\u0092\u0093\7{\2\2\u0093\u0094\7r\2"+
		"\2\u0094\u0095\7g\2\2\u0095\u0096\7\"\2\2\u0096\u0097\7$\2\2\u0097\u0098"+
		"\3\2\2\2\u0098\u0099\b\7\2\2\u0099\17\3\2\2\2\u009a\u009b\7v\2\2\u009b"+
		"\u009c\7j\2\2\u009c\u009d\7g\2\2\u009d\u009e\7\"\2\2\u009e\u009f\7w\2"+
		"\2\u009f\u00a0\7u\2\2\u00a0\u00a1\7g\2\2\u00a1\u00a2\7t\2\2\u00a2\u00a3"+
		"\7\"\2\2\u00a3\u00a4\7r\2\2\u00a4\u00a5\7t\2\2\u00a5\u00a6\7q\2\2\u00a6"+
		"\u00a7\7h\2\2\u00a7\u00a8\7k\2\2\u00a8\u00a9\7n\2\2\u00a9\u00aa\7g\2\2"+
		"\u00aa\u00ab\7\"\2\2\u00ab\u00ac\7j\2\2\u00ac\u00ad\7c\2\2\u00ad\u00ae"+
		"\7u\2\2\u00ae\u00af\7\"\2\2\u00af\u00b0\7v\2\2\u00b0\u00b1\7j\2\2\u00b1"+
		"\u00b2\7g\2\2\u00b2\u00b3\7\"\2\2\u00b3\21\3\2\2\2\u00b4\u00b5\t\2\2\2"+
		"\u00b5\23\3\2\2\2\u00b6\u00b8\5\22\t\2\u00b7\u00b6\3\2\2\2\u00b8\u00bb"+
		"\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00c2\3\2\2\2\u00bb"+
		"\u00b9\3\2\2\2\u00bc\u00be\t\3\2\2\u00bd\u00bc\3\2\2\2\u00be\u00bf\3\2"+
		"\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1"+
		"\u00c3\7\2\2\3\u00c2\u00bd\3\2\2\2\u00c2\u00c1\3\2\2\2\u00c3\25\3\2\2"+
		"\2\u00c4\u00c5\7C\2\2\u00c5\u00c6\7p\2\2\u00c6\u00c7\7f\2\2\u00c7\u00c8"+
		"\7\"\2\2\u00c8\27\3\2\2\2\u00c9\u00ca\7D\2\2\u00ca\u00cb\7w\2\2\u00cb"+
		"\u00cc\7v\2\2\u00cc\u00cd\7\"\2\2\u00cd\31\3\2\2\2\u00ce\u00cf\7,\2\2"+
		"\u00cf\u00d0\7\"\2\2\u00d0\33\3\2\2\2\u00d1\u00d2\7I\2\2\u00d2\u00d3\7"+
		"k\2\2\u00d3\u00d4\7x\2\2\u00d4\u00d5\7g\2\2\u00d5\u00d6\7p\2\2\u00d6\u00da"+
		"\7\"\2\2\u00d7\u00da\5\26\13\2\u00d8\u00da\5\30\f\2\u00d9\u00d1\3\2\2"+
		"\2\u00d9\u00d7\3\2\2\2\u00d9\u00d8\3\2\2\2\u00da\35\3\2\2\2\u00db\u00dc"+
		"\7Y\2\2\u00dc\u00dd\7j\2\2\u00dd\u00de\7g\2\2\u00de\u00df\7p\2\2\u00df"+
		"\u00e3\7\"\2\2\u00e0\u00e3\5\26\13\2\u00e1\u00e3\5\30\f\2\u00e2\u00db"+
		"\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e2\u00e1\3\2\2\2\u00e3\37\3\2\2\2\u00e4"+
		"\u00e5\7V\2\2\u00e5\u00e6\7j\2\2\u00e6\u00e7\7g\2\2\u00e7\u00e8\7p\2\2"+
		"\u00e8\u00ec\7\"\2\2\u00e9\u00ec\5\26\13\2\u00ea\u00ec\5\30\f\2\u00eb"+
		"\u00e4\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb\u00ea\3\2\2\2\u00ec!\3\2\2\2"+
		"\u00ed\u00ef\5\22\t\2\u00ee\u00ed\3\2\2\2\u00ef\u00f2\3\2\2\2\u00f0\u00ee"+
		"\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f7\3\2\2\2\u00f2\u00f0\3\2\2\2\u00f3"+
		"\u00f8\5\34\16\2\u00f4\u00f8\5\36\17\2\u00f5\u00f8\5 \20\2\u00f6\u00f8"+
		"\5\32\r\2\u00f7\u00f3\3\2\2\2\u00f7\u00f4\3\2\2\2\u00f7\u00f5\3\2\2\2"+
		"\u00f7\u00f6\3\2\2\2\u00f8#\3\2\2\2\u00f9\u00fa\t\3\2\2\u00fa%\3\2\2\2"+
		"\u00fb\u00fd\n\4\2\2\u00fc\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u00fc"+
		"\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\'\3\2\2\2\u0100\u0102\7$\2\2\u0101"+
		"\u0103\5\24\n\2\u0102\u0101\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0104\3"+
		"\2\2\2\u0104\u0105\b\24\3\2\u0105)\3\2\2\2\17\2\3[\u00b9\u00bf\u00c2\u00d9"+
		"\u00e2\u00eb\u00f0\u00f7\u00fe\u0102\4\4\3\2\4\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}