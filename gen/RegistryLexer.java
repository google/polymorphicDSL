// Generated from /home/net/repos/polymorphic-dsl/pdsl/src/test/resources/RegistryLexer.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RegistryLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SUM=1, MINUS=2, PRODUCT=3, NUMBER=4, WS=5, END_OF_FILE=6, HELLO=7, WORLD=8;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"ADD", "SUBTRACT", "MULTIPLY", "DIVIDE", "SUM", "MINUS", "PRODUCT", "NUMBER", 
			"DECIMAL", "WS", "END_OF_FILE", "HELLO", "WORLD"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, "'Hello, '", "'world!'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SUM", "MINUS", "PRODUCT", "NUMBER", "WS", "END_OF_FILE", "HELLO", 
			"WORLD"
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


	public RegistryLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "RegistryLexer.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\n\u00a8\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\7"+
		"\6\'\n\6\f\6\16\6*\13\6\3\6\3\6\7\6.\n\6\f\6\16\6\61\13\6\3\6\3\6\7\6"+
		"\65\n\6\f\6\16\68\13\6\3\6\3\6\7\6<\n\6\f\6\16\6?\13\6\3\6\5\6B\n\6\3"+
		"\6\5\6E\n\6\3\7\7\7H\n\7\f\7\16\7K\13\7\3\7\3\7\3\7\3\7\7\7Q\n\7\f\7\16"+
		"\7T\13\7\3\7\3\7\7\7X\n\7\f\7\16\7[\13\7\3\7\5\7^\n\7\3\7\5\7a\n\7\3\b"+
		"\7\bd\n\b\f\b\16\bg\13\b\3\b\3\b\7\bk\n\b\f\b\16\bn\13\b\3\b\3\b\7\br"+
		"\n\b\f\b\16\bu\13\b\3\b\3\b\7\by\n\b\f\b\16\b|\13\b\3\b\5\b\177\n\b\3"+
		"\b\5\b\u0082\n\b\3\t\6\t\u0085\n\t\r\t\16\t\u0086\3\t\5\t\u008a\n\t\3"+
		"\n\3\n\6\n\u008e\n\n\r\n\16\n\u008f\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3"+
		"\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\2\2\17\3\2\5\2\7\2\t\2\13\3\r\4\17\5\21\6\23\2\25\7\27\b\31\t\33\n\3"+
		"\2\5\4\2\f\f\17\17\3\2\62;\5\2\13\f\17\17\"\"\2\u00b6\2\13\3\2\2\2\2\r"+
		"\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\3\35\3\2\2\2\5\37\3\2\2\2\7!\3\2\2\2\t#\3\2\2\2\13("+
		"\3\2\2\2\rI\3\2\2\2\17e\3\2\2\2\21\u0084\3\2\2\2\23\u008b\3\2\2\2\25\u0091"+
		"\3\2\2\2\27\u0095\3\2\2\2\31\u0099\3\2\2\2\33\u00a1\3\2\2\2\35\36\7-\2"+
		"\2\36\4\3\2\2\2\37 \7/\2\2 \6\3\2\2\2!\"\7,\2\2\"\b\3\2\2\2#$\7\61\2\2"+
		"$\n\3\2\2\2%\'\5\25\13\2&%\3\2\2\2\'*\3\2\2\2(&\3\2\2\2()\3\2\2\2)+\3"+
		"\2\2\2*(\3\2\2\2+/\5\21\t\2,.\5\25\13\2-,\3\2\2\2.\61\3\2\2\2/-\3\2\2"+
		"\2/\60\3\2\2\2\60\62\3\2\2\2\61/\3\2\2\2\62\66\5\3\2\2\63\65\5\25\13\2"+
		"\64\63\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\679\3\2\2\28\66"+
		"\3\2\2\29=\5\21\t\2:<\5\25\13\2;:\3\2\2\2<?\3\2\2\2=;\3\2\2\2=>\3\2\2"+
		"\2>A\3\2\2\2?=\3\2\2\2@B\t\2\2\2A@\3\2\2\2AB\3\2\2\2BD\3\2\2\2CE\7\2\2"+
		"\3DC\3\2\2\2DE\3\2\2\2E\f\3\2\2\2FH\5\25\13\2GF\3\2\2\2HK\3\2\2\2IG\3"+
		"\2\2\2IJ\3\2\2\2JL\3\2\2\2KI\3\2\2\2LM\5\21\t\2MN\5\25\13\2NR\5\5\3\2"+
		"OQ\5\25\13\2PO\3\2\2\2QT\3\2\2\2RP\3\2\2\2RS\3\2\2\2SU\3\2\2\2TR\3\2\2"+
		"\2UY\5\21\t\2VX\5\25\13\2WV\3\2\2\2X[\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z]\3"+
		"\2\2\2[Y\3\2\2\2\\^\t\2\2\2]\\\3\2\2\2]^\3\2\2\2^`\3\2\2\2_a\7\2\2\3`"+
		"_\3\2\2\2`a\3\2\2\2a\16\3\2\2\2bd\5\25\13\2cb\3\2\2\2dg\3\2\2\2ec\3\2"+
		"\2\2ef\3\2\2\2fh\3\2\2\2ge\3\2\2\2hl\5\21\t\2ik\5\25\13\2ji\3\2\2\2kn"+
		"\3\2\2\2lj\3\2\2\2lm\3\2\2\2mo\3\2\2\2nl\3\2\2\2os\5\7\4\2pr\5\25\13\2"+
		"qp\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2tv\3\2\2\2us\3\2\2\2vz\5\21\t"+
		"\2wy\5\25\13\2xw\3\2\2\2y|\3\2\2\2zx\3\2\2\2z{\3\2\2\2{~\3\2\2\2|z\3\2"+
		"\2\2}\177\t\2\2\2~}\3\2\2\2~\177\3\2\2\2\177\u0081\3\2\2\2\u0080\u0082"+
		"\7\2\2\3\u0081\u0080\3\2\2\2\u0081\u0082\3\2\2\2\u0082\20\3\2\2\2\u0083"+
		"\u0085\t\3\2\2\u0084\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0084\3\2"+
		"\2\2\u0086\u0087\3\2\2\2\u0087\u0089\3\2\2\2\u0088\u008a\5\23\n\2\u0089"+
		"\u0088\3\2\2\2\u0089\u008a\3\2\2\2\u008a\22\3\2\2\2\u008b\u008d\7\60\2"+
		"\2\u008c\u008e\t\3\2\2\u008d\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u008d"+
		"\3\2\2\2\u008f\u0090\3\2\2\2\u0090\24\3\2\2\2\u0091\u0092\t\4\2\2\u0092"+
		"\u0093\3\2\2\2\u0093\u0094\b\13\2\2\u0094\26\3\2\2\2\u0095\u0096\7\2\2"+
		"\3\u0096\u0097\3\2\2\2\u0097\u0098\b\f\2\2\u0098\30\3\2\2\2\u0099\u009a"+
		"\7J\2\2\u009a\u009b\7g\2\2\u009b\u009c\7n\2\2\u009c\u009d\7n\2\2\u009d"+
		"\u009e\7q\2\2\u009e\u009f\7.\2\2\u009f\u00a0\7\"\2\2\u00a0\32\3\2\2\2"+
		"\u00a1\u00a2\7y\2\2\u00a2\u00a3\7q\2\2\u00a3\u00a4\7t\2\2\u00a4\u00a5"+
		"\7n\2\2\u00a5\u00a6\7f\2\2\u00a6\u00a7\7#\2\2\u00a7\34\3\2\2\2\27\2(/"+
		"\66=ADIRY]`elsz~\u0081\u0086\u0089\u008f\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}