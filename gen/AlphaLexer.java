// Generated from /home/net/repos/polymorphic-dsl/pdsl/src/test/resources/AlphaLexer.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AlphaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SUM=1, MINUS=2, PRODUCT=3, NUMBER=4, WS=5, END_OF_FILE=6;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"ADD", "SUBTRACT", "MULTIPLY", "DIVIDE", "SUM", "MINUS", "PRODUCT", "NUMBER", 
			"DECIMAL", "WS", "END_OF_FILE"
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
			null, "SUM", "MINUS", "PRODUCT", "NUMBER", "WS", "END_OF_FILE"
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


	public AlphaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "AlphaLexer.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\b\u0095\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\7\6#\n\6\f\6\16\6&\13"+
		"\6\3\6\3\6\7\6*\n\6\f\6\16\6-\13\6\3\6\3\6\7\6\61\n\6\f\6\16\6\64\13\6"+
		"\3\6\3\6\7\68\n\6\f\6\16\6;\13\6\3\6\5\6>\n\6\3\6\5\6A\n\6\3\7\7\7D\n"+
		"\7\f\7\16\7G\13\7\3\7\3\7\3\7\3\7\7\7M\n\7\f\7\16\7P\13\7\3\7\3\7\7\7"+
		"T\n\7\f\7\16\7W\13\7\3\7\5\7Z\n\7\3\7\5\7]\n\7\3\b\7\b`\n\b\f\b\16\bc"+
		"\13\b\3\b\3\b\7\bg\n\b\f\b\16\bj\13\b\3\b\3\b\7\bn\n\b\f\b\16\bq\13\b"+
		"\3\b\3\b\7\bu\n\b\f\b\16\bx\13\b\3\b\5\b{\n\b\3\b\5\b~\n\b\3\t\6\t\u0081"+
		"\n\t\r\t\16\t\u0082\3\t\5\t\u0086\n\t\3\n\3\n\6\n\u008a\n\n\r\n\16\n\u008b"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\2\2\r\3\2\5\2\7\2\t\2\13\3\r\4\17"+
		"\5\21\6\23\2\25\7\27\b\3\2\5\4\2\f\f\17\17\3\2\62;\5\2\13\f\17\17\"\""+
		"\2\u00a3\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\3\31\3\2\2\2\5\33\3\2\2\2\7\35\3\2\2\2\t\37\3\2\2\2\13"+
		"$\3\2\2\2\rE\3\2\2\2\17a\3\2\2\2\21\u0080\3\2\2\2\23\u0087\3\2\2\2\25"+
		"\u008d\3\2\2\2\27\u0091\3\2\2\2\31\32\7-\2\2\32\4\3\2\2\2\33\34\7/\2\2"+
		"\34\6\3\2\2\2\35\36\7,\2\2\36\b\3\2\2\2\37 \7\61\2\2 \n\3\2\2\2!#\5\25"+
		"\13\2\"!\3\2\2\2#&\3\2\2\2$\"\3\2\2\2$%\3\2\2\2%\'\3\2\2\2&$\3\2\2\2\'"+
		"+\5\21\t\2(*\5\25\13\2)(\3\2\2\2*-\3\2\2\2+)\3\2\2\2+,\3\2\2\2,.\3\2\2"+
		"\2-+\3\2\2\2.\62\5\3\2\2/\61\5\25\13\2\60/\3\2\2\2\61\64\3\2\2\2\62\60"+
		"\3\2\2\2\62\63\3\2\2\2\63\65\3\2\2\2\64\62\3\2\2\2\659\5\21\t\2\668\5"+
		"\25\13\2\67\66\3\2\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:=\3\2\2\2;9\3\2"+
		"\2\2<>\t\2\2\2=<\3\2\2\2=>\3\2\2\2>@\3\2\2\2?A\7\2\2\3@?\3\2\2\2@A\3\2"+
		"\2\2A\f\3\2\2\2BD\5\25\13\2CB\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2\2\2FH"+
		"\3\2\2\2GE\3\2\2\2HI\5\21\t\2IJ\5\25\13\2JN\5\5\3\2KM\5\25\13\2LK\3\2"+
		"\2\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2OQ\3\2\2\2PN\3\2\2\2QU\5\21\t\2RT\5"+
		"\25\13\2SR\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2VY\3\2\2\2WU\3\2\2\2X"+
		"Z\t\2\2\2YX\3\2\2\2YZ\3\2\2\2Z\\\3\2\2\2[]\7\2\2\3\\[\3\2\2\2\\]\3\2\2"+
		"\2]\16\3\2\2\2^`\5\25\13\2_^\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2bd\3"+
		"\2\2\2ca\3\2\2\2dh\5\21\t\2eg\5\25\13\2fe\3\2\2\2gj\3\2\2\2hf\3\2\2\2"+
		"hi\3\2\2\2ik\3\2\2\2jh\3\2\2\2ko\5\7\4\2ln\5\25\13\2ml\3\2\2\2nq\3\2\2"+
		"\2om\3\2\2\2op\3\2\2\2pr\3\2\2\2qo\3\2\2\2rv\5\21\t\2su\5\25\13\2ts\3"+
		"\2\2\2ux\3\2\2\2vt\3\2\2\2vw\3\2\2\2wz\3\2\2\2xv\3\2\2\2y{\t\2\2\2zy\3"+
		"\2\2\2z{\3\2\2\2{}\3\2\2\2|~\7\2\2\3}|\3\2\2\2}~\3\2\2\2~\20\3\2\2\2\177"+
		"\u0081\t\3\2\2\u0080\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0080\3\2\2"+
		"\2\u0082\u0083\3\2\2\2\u0083\u0085\3\2\2\2\u0084\u0086\5\23\n\2\u0085"+
		"\u0084\3\2\2\2\u0085\u0086\3\2\2\2\u0086\22\3\2\2\2\u0087\u0089\7\60\2"+
		"\2\u0088\u008a\t\3\2\2\u0089\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u0089"+
		"\3\2\2\2\u008b\u008c\3\2\2\2\u008c\24\3\2\2\2\u008d\u008e\t\4\2\2\u008e"+
		"\u008f\3\2\2\2\u008f\u0090\b\13\2\2\u0090\26\3\2\2\2\u0091\u0092\7\2\2"+
		"\3\u0092\u0093\3\2\2\2\u0093\u0094\b\f\2\2\u0094\30\3\2\2\2\27\2$+\62"+
		"9=@ENUY\\ahovz}\u0082\u0085\u008b\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}