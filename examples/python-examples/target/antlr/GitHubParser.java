// Generated from ./tests/resources/antlr4/GitHubParser.g4 by ANTLR 4.9.1
package com.pdsl.python;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GitHubParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		GIVEN_THE_TEST_RESOURCE=1, THEN_USER_PROFILE_VALIDITY=2, USER_PROFILE_HAS_LOGIN=3, 
		USER_PROFILE_HAS_NAME=4, USER_PROFILE_HAS_ID=5, USER_PROFILE_HAS_TYPE=6, 
		USER_PROFILE_HAS=7, GHERKIN_STEP_KEYWORD=8, BODY=9, CLOSE_QUOTE=10;
	public static final int
		RULE_givenTestProfile = 0, RULE_userProfileValidation = 1, RULE_userProfileHasLogin = 2, 
		RULE_userProfileHasName = 3, RULE_userProfileHasId = 4, RULE_userProfileHasType = 5, 
		RULE_parameter = 6, RULE_polymorphicDslAllRules = 7, RULE_polymorphicDslSyntaxRule = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"givenTestProfile", "userProfileValidation", "userProfileHasLogin", "userProfileHasName", 
			"userProfileHasId", "userProfileHasType", "parameter", "polymorphicDslAllRules", 
			"polymorphicDslSyntaxRule"
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

	@Override
	public String getGrammarFileName() { return "GitHubParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GitHubParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class GivenTestProfileContext extends ParserRuleContext {
		public TerminalNode GIVEN_THE_TEST_RESOURCE() { return getToken(GitHubParser.GIVEN_THE_TEST_RESOURCE, 0); }
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
		}
		public GivenTestProfileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_givenTestProfile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GitHubParserListener ) ((GitHubParserListener)listener).enterGivenTestProfile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GitHubParserListener ) ((GitHubParserListener)listener).exitGivenTestProfile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GitHubParserVisitor ) return ((GitHubParserVisitor<? extends T>)visitor).visitGivenTestProfile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GivenTestProfileContext givenTestProfile() throws RecognitionException {
		GivenTestProfileContext _localctx = new GivenTestProfileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_givenTestProfile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			match(GIVEN_THE_TEST_RESOURCE);
			setState(19);
			parameter();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UserProfileValidationContext extends ParserRuleContext {
		public TerminalNode THEN_USER_PROFILE_VALIDITY() { return getToken(GitHubParser.THEN_USER_PROFILE_VALIDITY, 0); }
		public UserProfileValidationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_userProfileValidation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GitHubParserListener ) ((GitHubParserListener)listener).enterUserProfileValidation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GitHubParserListener ) ((GitHubParserListener)listener).exitUserProfileValidation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GitHubParserVisitor ) return ((GitHubParserVisitor<? extends T>)visitor).visitUserProfileValidation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UserProfileValidationContext userProfileValidation() throws RecognitionException {
		UserProfileValidationContext _localctx = new UserProfileValidationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_userProfileValidation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			match(THEN_USER_PROFILE_VALIDITY);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UserProfileHasLoginContext extends ParserRuleContext {
		public TerminalNode USER_PROFILE_HAS_LOGIN() { return getToken(GitHubParser.USER_PROFILE_HAS_LOGIN, 0); }
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
		}
		public UserProfileHasLoginContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_userProfileHasLogin; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GitHubParserListener ) ((GitHubParserListener)listener).enterUserProfileHasLogin(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GitHubParserListener ) ((GitHubParserListener)listener).exitUserProfileHasLogin(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GitHubParserVisitor ) return ((GitHubParserVisitor<? extends T>)visitor).visitUserProfileHasLogin(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UserProfileHasLoginContext userProfileHasLogin() throws RecognitionException {
		UserProfileHasLoginContext _localctx = new UserProfileHasLoginContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_userProfileHasLogin);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(23);
			match(USER_PROFILE_HAS_LOGIN);
			setState(24);
			parameter();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UserProfileHasNameContext extends ParserRuleContext {
		public TerminalNode USER_PROFILE_HAS_NAME() { return getToken(GitHubParser.USER_PROFILE_HAS_NAME, 0); }
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
		}
		public UserProfileHasNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_userProfileHasName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GitHubParserListener ) ((GitHubParserListener)listener).enterUserProfileHasName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GitHubParserListener ) ((GitHubParserListener)listener).exitUserProfileHasName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GitHubParserVisitor ) return ((GitHubParserVisitor<? extends T>)visitor).visitUserProfileHasName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UserProfileHasNameContext userProfileHasName() throws RecognitionException {
		UserProfileHasNameContext _localctx = new UserProfileHasNameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_userProfileHasName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			match(USER_PROFILE_HAS_NAME);
			setState(27);
			parameter();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UserProfileHasIdContext extends ParserRuleContext {
		public TerminalNode USER_PROFILE_HAS_ID() { return getToken(GitHubParser.USER_PROFILE_HAS_ID, 0); }
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
		}
		public UserProfileHasIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_userProfileHasId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GitHubParserListener ) ((GitHubParserListener)listener).enterUserProfileHasId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GitHubParserListener ) ((GitHubParserListener)listener).exitUserProfileHasId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GitHubParserVisitor ) return ((GitHubParserVisitor<? extends T>)visitor).visitUserProfileHasId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UserProfileHasIdContext userProfileHasId() throws RecognitionException {
		UserProfileHasIdContext _localctx = new UserProfileHasIdContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_userProfileHasId);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			match(USER_PROFILE_HAS_ID);
			setState(30);
			parameter();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UserProfileHasTypeContext extends ParserRuleContext {
		public TerminalNode USER_PROFILE_HAS_TYPE() { return getToken(GitHubParser.USER_PROFILE_HAS_TYPE, 0); }
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
		}
		public UserProfileHasTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_userProfileHasType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GitHubParserListener ) ((GitHubParserListener)listener).enterUserProfileHasType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GitHubParserListener ) ((GitHubParserListener)listener).exitUserProfileHasType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GitHubParserVisitor ) return ((GitHubParserVisitor<? extends T>)visitor).visitUserProfileHasType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UserProfileHasTypeContext userProfileHasType() throws RecognitionException {
		UserProfileHasTypeContext _localctx = new UserProfileHasTypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_userProfileHasType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(USER_PROFILE_HAS_TYPE);
			setState(33);
			parameter();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterContext extends ParserRuleContext {
		public TerminalNode BODY() { return getToken(GitHubParser.BODY, 0); }
		public TerminalNode CLOSE_QUOTE() { return getToken(GitHubParser.CLOSE_QUOTE, 0); }
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GitHubParserListener ) ((GitHubParserListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GitHubParserListener ) ((GitHubParserListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GitHubParserVisitor ) return ((GitHubParserVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			match(BODY);
			setState(36);
			match(CLOSE_QUOTE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PolymorphicDslAllRulesContext extends ParserRuleContext {
		public List<GivenTestProfileContext> givenTestProfile() {
			return getRuleContexts(GivenTestProfileContext.class);
		}
		public GivenTestProfileContext givenTestProfile(int i) {
			return getRuleContext(GivenTestProfileContext.class,i);
		}
		public List<UserProfileValidationContext> userProfileValidation() {
			return getRuleContexts(UserProfileValidationContext.class);
		}
		public UserProfileValidationContext userProfileValidation(int i) {
			return getRuleContext(UserProfileValidationContext.class,i);
		}
		public List<UserProfileHasLoginContext> userProfileHasLogin() {
			return getRuleContexts(UserProfileHasLoginContext.class);
		}
		public UserProfileHasLoginContext userProfileHasLogin(int i) {
			return getRuleContext(UserProfileHasLoginContext.class,i);
		}
		public List<UserProfileHasNameContext> userProfileHasName() {
			return getRuleContexts(UserProfileHasNameContext.class);
		}
		public UserProfileHasNameContext userProfileHasName(int i) {
			return getRuleContext(UserProfileHasNameContext.class,i);
		}
		public List<UserProfileHasIdContext> userProfileHasId() {
			return getRuleContexts(UserProfileHasIdContext.class);
		}
		public UserProfileHasIdContext userProfileHasId(int i) {
			return getRuleContext(UserProfileHasIdContext.class,i);
		}
		public List<UserProfileHasTypeContext> userProfileHasType() {
			return getRuleContexts(UserProfileHasTypeContext.class);
		}
		public UserProfileHasTypeContext userProfileHasType(int i) {
			return getRuleContext(UserProfileHasTypeContext.class,i);
		}
		public PolymorphicDslAllRulesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_polymorphicDslAllRules; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GitHubParserListener ) ((GitHubParserListener)listener).enterPolymorphicDslAllRules(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GitHubParserListener ) ((GitHubParserListener)listener).exitPolymorphicDslAllRules(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GitHubParserVisitor ) return ((GitHubParserVisitor<? extends T>)visitor).visitPolymorphicDslAllRules(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PolymorphicDslAllRulesContext polymorphicDslAllRules() throws RecognitionException {
		PolymorphicDslAllRulesContext _localctx = new PolymorphicDslAllRulesContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_polymorphicDslAllRules);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(44);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case GIVEN_THE_TEST_RESOURCE:
					{
					setState(38);
					givenTestProfile();
					}
					break;
				case THEN_USER_PROFILE_VALIDITY:
					{
					setState(39);
					userProfileValidation();
					}
					break;
				case USER_PROFILE_HAS_LOGIN:
					{
					setState(40);
					userProfileHasLogin();
					}
					break;
				case USER_PROFILE_HAS_NAME:
					{
					setState(41);
					userProfileHasName();
					}
					break;
				case USER_PROFILE_HAS_ID:
					{
					setState(42);
					userProfileHasId();
					}
					break;
				case USER_PROFILE_HAS_TYPE:
					{
					setState(43);
					userProfileHasType();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(46); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GIVEN_THE_TEST_RESOURCE) | (1L << THEN_USER_PROFILE_VALIDITY) | (1L << USER_PROFILE_HAS_LOGIN) | (1L << USER_PROFILE_HAS_NAME) | (1L << USER_PROFILE_HAS_ID) | (1L << USER_PROFILE_HAS_TYPE))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PolymorphicDslSyntaxRuleContext extends ParserRuleContext {
		public GivenTestProfileContext givenTestProfile() {
			return getRuleContext(GivenTestProfileContext.class,0);
		}
		public UserProfileValidationContext userProfileValidation() {
			return getRuleContext(UserProfileValidationContext.class,0);
		}
		public List<UserProfileHasLoginContext> userProfileHasLogin() {
			return getRuleContexts(UserProfileHasLoginContext.class);
		}
		public UserProfileHasLoginContext userProfileHasLogin(int i) {
			return getRuleContext(UserProfileHasLoginContext.class,i);
		}
		public List<UserProfileHasNameContext> userProfileHasName() {
			return getRuleContexts(UserProfileHasNameContext.class);
		}
		public UserProfileHasNameContext userProfileHasName(int i) {
			return getRuleContext(UserProfileHasNameContext.class,i);
		}
		public List<UserProfileHasIdContext> userProfileHasId() {
			return getRuleContexts(UserProfileHasIdContext.class);
		}
		public UserProfileHasIdContext userProfileHasId(int i) {
			return getRuleContext(UserProfileHasIdContext.class,i);
		}
		public List<UserProfileHasTypeContext> userProfileHasType() {
			return getRuleContexts(UserProfileHasTypeContext.class);
		}
		public UserProfileHasTypeContext userProfileHasType(int i) {
			return getRuleContext(UserProfileHasTypeContext.class,i);
		}
		public PolymorphicDslSyntaxRuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_polymorphicDslSyntaxRule; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GitHubParserListener ) ((GitHubParserListener)listener).enterPolymorphicDslSyntaxRule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GitHubParserListener ) ((GitHubParserListener)listener).exitPolymorphicDslSyntaxRule(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GitHubParserVisitor ) return ((GitHubParserVisitor<? extends T>)visitor).visitPolymorphicDslSyntaxRule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PolymorphicDslSyntaxRuleContext polymorphicDslSyntaxRule() throws RecognitionException {
		PolymorphicDslSyntaxRuleContext _localctx = new PolymorphicDslSyntaxRuleContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_polymorphicDslSyntaxRule);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			givenTestProfile();
			setState(49);
			userProfileValidation();
			setState(54); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(54);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case USER_PROFILE_HAS_LOGIN:
					{
					setState(50);
					userProfileHasLogin();
					}
					break;
				case USER_PROFILE_HAS_NAME:
					{
					setState(51);
					userProfileHasName();
					}
					break;
				case USER_PROFILE_HAS_ID:
					{
					setState(52);
					userProfileHasId();
					}
					break;
				case USER_PROFILE_HAS_TYPE:
					{
					setState(53);
					userProfileHasType();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(56); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << USER_PROFILE_HAS_LOGIN) | (1L << USER_PROFILE_HAS_NAME) | (1L << USER_PROFILE_HAS_ID) | (1L << USER_PROFILE_HAS_TYPE))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\f=\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\3\2"+
		"\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\6\t/\n\t\r\t\16\t\60\3\n\3\n\3\n\3\n\3\n\3\n\6"+
		"\n9\n\n\r\n\16\n:\3\n\2\2\13\2\4\6\b\n\f\16\20\22\2\2\2=\2\24\3\2\2\2"+
		"\4\27\3\2\2\2\6\31\3\2\2\2\b\34\3\2\2\2\n\37\3\2\2\2\f\"\3\2\2\2\16%\3"+
		"\2\2\2\20.\3\2\2\2\22\62\3\2\2\2\24\25\7\3\2\2\25\26\5\16\b\2\26\3\3\2"+
		"\2\2\27\30\7\4\2\2\30\5\3\2\2\2\31\32\7\5\2\2\32\33\5\16\b\2\33\7\3\2"+
		"\2\2\34\35\7\6\2\2\35\36\5\16\b\2\36\t\3\2\2\2\37 \7\7\2\2 !\5\16\b\2"+
		"!\13\3\2\2\2\"#\7\b\2\2#$\5\16\b\2$\r\3\2\2\2%&\7\13\2\2&\'\7\f\2\2\'"+
		"\17\3\2\2\2(/\5\2\2\2)/\5\4\3\2*/\5\6\4\2+/\5\b\5\2,/\5\n\6\2-/\5\f\7"+
		"\2.(\3\2\2\2.)\3\2\2\2.*\3\2\2\2.+\3\2\2\2.,\3\2\2\2.-\3\2\2\2/\60\3\2"+
		"\2\2\60.\3\2\2\2\60\61\3\2\2\2\61\21\3\2\2\2\62\63\5\2\2\2\638\5\4\3\2"+
		"\649\5\6\4\2\659\5\b\5\2\669\5\n\6\2\679\5\f\7\28\64\3\2\2\28\65\3\2\2"+
		"\28\66\3\2\2\28\67\3\2\2\29:\3\2\2\2:8\3\2\2\2:;\3\2\2\2;\23\3\2\2\2\6"+
		".\608:";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}