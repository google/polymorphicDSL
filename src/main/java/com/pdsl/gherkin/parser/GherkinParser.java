// Generated from com/pdsl/gherkin/GherkinParser.g4 by ANTLR 4.9.1
package com.pdsl.gherkin.parser;

import com.pdsl.gherkin.parser.GherkinParserListener;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;
import com.pdsl.gherkin.parser.GherkinLexer;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GherkinParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LANGUAGE_HEADER=1, FEATURE_KEYWORD=2, SCENARIO_KEYWORD=3, SCENARIO_OUTLINE_KEYWORD=4, 
		BACKGROUND_KEYWORD=5, EXAMPLES_KEYWORD=6, RULE_KEYWORD=7, STARTING_STEP_KEYWORD=8, 
		ALTERNATIVE_STEP_KEYWORD=9, GIVEN_KEYWORD=10, WHEN_KEYWORD=11, THEN_KEYWORD=12, 
		WILD_KEYWORD=13, AND_KEYWORD=14, BUT_KEYWORD=15, DOCSTRING=16, TAGS=17, 
		FEATURE_TITLE=18, BACKGROUND_TITLE=19, EXAMPLES_TITLE=20, SCENARIO_TITLE=21, 
		SCENARIO_OUTLINE_TITLE=22, RULE_TITLE=23, GIVEN_STEP=24, WHEN_STEP=25, 
		THEN_STEP=26, AND_STEP=27, BUT_STEP=28, WILD_STEP=29, DATA_ROW=30, INVALID_LANGUAGE_HEADER=31, 
		COMMENT=32, LINE_END=33, NEWLINE=34, WS=35, LONG_DESCRIPTION=36;
	public static final int
		RULE_gherkinDocument = 0, RULE_feature = 1, RULE_ruleBlock = 2, RULE_background = 3, 
		RULE_scenario = 4, RULE_stepBody = 5, RULE_examplesBody = 6, RULE_startingStep = 7, 
		RULE_anyStep = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"gherkinDocument", "feature", "ruleBlock", "background", "scenario", 
			"stepBody", "examplesBody", "startingStep", "anyStep"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'Scenario Outline:'", "'Background:'", null, 
			"'Rule:'", null, null, "'Given '", "'When '", "'Then '", "'* '", "'And '", 
			"'But '"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LANGUAGE_HEADER", "FEATURE_KEYWORD", "SCENARIO_KEYWORD", "SCENARIO_OUTLINE_KEYWORD", 
			"BACKGROUND_KEYWORD", "EXAMPLES_KEYWORD", "RULE_KEYWORD", "STARTING_STEP_KEYWORD", 
			"ALTERNATIVE_STEP_KEYWORD", "GIVEN_KEYWORD", "WHEN_KEYWORD", "THEN_KEYWORD", 
			"WILD_KEYWORD", "AND_KEYWORD", "BUT_KEYWORD", "DOCSTRING", "TAGS", "FEATURE_TITLE", 
			"BACKGROUND_TITLE", "EXAMPLES_TITLE", "SCENARIO_TITLE", "SCENARIO_OUTLINE_TITLE", 
			"RULE_TITLE", "GIVEN_STEP", "WHEN_STEP", "THEN_STEP", "AND_STEP", "BUT_STEP", 
			"WILD_STEP", "DATA_ROW", "INVALID_LANGUAGE_HEADER", "COMMENT", "LINE_END", 
			"NEWLINE", "WS", "LONG_DESCRIPTION"
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
	public String getGrammarFileName() { return "GherkinParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GherkinParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class GherkinDocumentContext extends ParserRuleContext {
		public FeatureContext feature() {
			return getRuleContext(FeatureContext.class,0);
		}
		public TerminalNode LANGUAGE_HEADER() { return getToken(GherkinParser.LANGUAGE_HEADER, 0); }
		public List<TerminalNode> COMMENT() { return getTokens(GherkinParser.COMMENT); }
		public TerminalNode COMMENT(int i) {
			return getToken(GherkinParser.COMMENT, i);
		}
		public TerminalNode EOF() { return getToken(GherkinParser.EOF, 0); }
		public GherkinDocumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gherkinDocument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinParserListener) ((GherkinParserListener)listener).enterGherkinDocument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinParserListener) ((GherkinParserListener)listener).exitGherkinDocument(this);
		}
	}

	public final GherkinDocumentContext gherkinDocument() throws RecognitionException {
		GherkinDocumentContext _localctx = new GherkinDocumentContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_gherkinDocument);
		int _la;
		try {
			setState(34);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(19);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LANGUAGE_HEADER) {
					{
					setState(18);
					match(LANGUAGE_HEADER);
					}
				}

				setState(24);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMENT) {
					{
					{
					setState(21);
					match(COMMENT);
					}
					}
					setState(26);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(27);
				feature();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(29); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(28);
					match(COMMENT);
					}
					}
					setState(31); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==COMMENT );
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(33);
				match(EOF);
				}
				break;
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

	public static class FeatureContext extends ParserRuleContext {
		public TerminalNode FEATURE_TITLE() { return getToken(GherkinParser.FEATURE_TITLE, 0); }
		public List<TerminalNode> TAGS() { return getTokens(GherkinParser.TAGS); }
		public TerminalNode TAGS(int i) {
			return getToken(GherkinParser.TAGS, i);
		}
		public List<TerminalNode> LONG_DESCRIPTION() { return getTokens(GherkinParser.LONG_DESCRIPTION); }
		public TerminalNode LONG_DESCRIPTION(int i) {
			return getToken(GherkinParser.LONG_DESCRIPTION, i);
		}
		public BackgroundContext background() {
			return getRuleContext(BackgroundContext.class,0);
		}
		public List<RuleBlockContext> ruleBlock() {
			return getRuleContexts(RuleBlockContext.class);
		}
		public RuleBlockContext ruleBlock(int i) {
			return getRuleContext(RuleBlockContext.class,i);
		}
		public List<ScenarioContext> scenario() {
			return getRuleContexts(ScenarioContext.class);
		}
		public ScenarioContext scenario(int i) {
			return getRuleContext(ScenarioContext.class,i);
		}
		public FeatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_feature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinParserListener) ((GherkinParserListener)listener).enterFeature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinParserListener) ((GherkinParserListener)listener).exitFeature(this);
		}
	}

	public final FeatureContext feature() throws RecognitionException {
		FeatureContext _localctx = new FeatureContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_feature);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(36);
					match(TAGS);
					}
					} 
				}
				setState(41);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			setState(42);
			match(FEATURE_TITLE);
			setState(46);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(43);
					match(LONG_DESCRIPTION);
					}
					} 
				}
				setState(48);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BACKGROUND_TITLE) {
				{
				setState(49);
				background();
				}
			}

			setState(56);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TAGS) | (1L << SCENARIO_TITLE) | (1L << SCENARIO_OUTLINE_TITLE) | (1L << RULE_TITLE))) != 0)) {
				{
				setState(54);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case RULE_TITLE:
					{
					setState(52);
					ruleBlock();
					}
					break;
				case TAGS:
				case SCENARIO_TITLE:
				case SCENARIO_OUTLINE_TITLE:
					{
					setState(53);
					scenario();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class RuleBlockContext extends ParserRuleContext {
		public TerminalNode RULE_TITLE() { return getToken(GherkinParser.RULE_TITLE, 0); }
		public List<TerminalNode> LONG_DESCRIPTION() { return getTokens(GherkinParser.LONG_DESCRIPTION); }
		public TerminalNode LONG_DESCRIPTION(int i) {
			return getToken(GherkinParser.LONG_DESCRIPTION, i);
		}
		public BackgroundContext background() {
			return getRuleContext(BackgroundContext.class,0);
		}
		public List<ScenarioContext> scenario() {
			return getRuleContexts(ScenarioContext.class);
		}
		public ScenarioContext scenario(int i) {
			return getRuleContext(ScenarioContext.class,i);
		}
		public RuleBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ruleBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinParserListener) ((GherkinParserListener)listener).enterRuleBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinParserListener) ((GherkinParserListener)listener).exitRuleBlock(this);
		}
	}

	public final RuleBlockContext ruleBlock() throws RecognitionException {
		RuleBlockContext _localctx = new RuleBlockContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_ruleBlock);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			match(RULE_TITLE);
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LONG_DESCRIPTION) {
				{
				{
				setState(60);
				match(LONG_DESCRIPTION);
				}
				}
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BACKGROUND_TITLE) {
				{
				setState(66);
				background();
				}
			}

			setState(72);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(69);
					scenario();
					}
					} 
				}
				setState(74);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
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

	public static class BackgroundContext extends ParserRuleContext {
		public TerminalNode BACKGROUND_TITLE() { return getToken(GherkinParser.BACKGROUND_TITLE, 0); }
		public List<TerminalNode> LONG_DESCRIPTION() { return getTokens(GherkinParser.LONG_DESCRIPTION); }
		public TerminalNode LONG_DESCRIPTION(int i) {
			return getToken(GherkinParser.LONG_DESCRIPTION, i);
		}
		public StepBodyContext stepBody() {
			return getRuleContext(StepBodyContext.class,0);
		}
		public BackgroundContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_background; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinParserListener) ((GherkinParserListener)listener).enterBackground(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinParserListener) ((GherkinParserListener)listener).exitBackground(this);
		}
	}

	public final BackgroundContext background() throws RecognitionException {
		BackgroundContext _localctx = new BackgroundContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_background);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(BACKGROUND_TITLE);
			setState(79);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(76);
					match(LONG_DESCRIPTION);
					}
					} 
				}
				setState(81);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GIVEN_STEP) | (1L << WHEN_STEP) | (1L << THEN_STEP) | (1L << WILD_STEP))) != 0)) {
				{
				setState(82);
				stepBody();
				}
			}

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

	public static class ScenarioContext extends ParserRuleContext {
		public TerminalNode SCENARIO_TITLE() { return getToken(GherkinParser.SCENARIO_TITLE, 0); }
		public TerminalNode SCENARIO_OUTLINE_TITLE() { return getToken(GherkinParser.SCENARIO_OUTLINE_TITLE, 0); }
		public List<TerminalNode> TAGS() { return getTokens(GherkinParser.TAGS); }
		public TerminalNode TAGS(int i) {
			return getToken(GherkinParser.TAGS, i);
		}
		public List<TerminalNode> LONG_DESCRIPTION() { return getTokens(GherkinParser.LONG_DESCRIPTION); }
		public TerminalNode LONG_DESCRIPTION(int i) {
			return getToken(GherkinParser.LONG_DESCRIPTION, i);
		}
		public StepBodyContext stepBody() {
			return getRuleContext(StepBodyContext.class,0);
		}
		public List<ExamplesBodyContext> examplesBody() {
			return getRuleContexts(ExamplesBodyContext.class);
		}
		public ExamplesBodyContext examplesBody(int i) {
			return getRuleContext(ExamplesBodyContext.class,i);
		}
		public ScenarioContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scenario; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinParserListener) ((GherkinParserListener)listener).enterScenario(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinParserListener) ((GherkinParserListener)listener).exitScenario(this);
		}
	}

	public final ScenarioContext scenario() throws RecognitionException {
		ScenarioContext _localctx = new ScenarioContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_scenario);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(85);
					match(TAGS);
					}
					} 
				}
				setState(90);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			setState(91);
			_la = _input.LA(1);
			if ( !(_la==SCENARIO_TITLE || _la==SCENARIO_OUTLINE_TITLE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LONG_DESCRIPTION) {
				{
				{
				setState(92);
				match(LONG_DESCRIPTION);
				}
				}
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GIVEN_STEP) | (1L << WHEN_STEP) | (1L << THEN_STEP) | (1L << WILD_STEP))) != 0)) {
				{
				setState(98);
				stepBody();
				}
			}

			setState(104);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(101);
					examplesBody();
					}
					} 
				}
				setState(106);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
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

	public static class StepBodyContext extends ParserRuleContext {
		public StartingStepContext startingStep() {
			return getRuleContext(StartingStepContext.class,0);
		}
		public List<AnyStepContext> anyStep() {
			return getRuleContexts(AnyStepContext.class);
		}
		public AnyStepContext anyStep(int i) {
			return getRuleContext(AnyStepContext.class,i);
		}
		public StepBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stepBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinParserListener) ((GherkinParserListener)listener).enterStepBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinParserListener) ((GherkinParserListener)listener).exitStepBody(this);
		}
	}

	public final StepBodyContext stepBody() throws RecognitionException {
		StepBodyContext _localctx = new StepBodyContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_stepBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			startingStep();
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GIVEN_STEP) | (1L << WHEN_STEP) | (1L << THEN_STEP) | (1L << AND_STEP) | (1L << BUT_STEP) | (1L << WILD_STEP))) != 0)) {
				{
				{
				setState(108);
				anyStep();
				}
				}
				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class ExamplesBodyContext extends ParserRuleContext {
		public TerminalNode EXAMPLES_TITLE() { return getToken(GherkinParser.EXAMPLES_TITLE, 0); }
		public List<TerminalNode> TAGS() { return getTokens(GherkinParser.TAGS); }
		public TerminalNode TAGS(int i) {
			return getToken(GherkinParser.TAGS, i);
		}
		public List<TerminalNode> LONG_DESCRIPTION() { return getTokens(GherkinParser.LONG_DESCRIPTION); }
		public TerminalNode LONG_DESCRIPTION(int i) {
			return getToken(GherkinParser.LONG_DESCRIPTION, i);
		}
		public List<TerminalNode> DATA_ROW() { return getTokens(GherkinParser.DATA_ROW); }
		public TerminalNode DATA_ROW(int i) {
			return getToken(GherkinParser.DATA_ROW, i);
		}
		public ExamplesBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_examplesBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinParserListener) ((GherkinParserListener)listener).enterExamplesBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinParserListener) ((GherkinParserListener)listener).exitExamplesBody(this);
		}
	}

	public final ExamplesBodyContext examplesBody() throws RecognitionException {
		ExamplesBodyContext _localctx = new ExamplesBodyContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_examplesBody);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(114);
					match(TAGS);
					}
					} 
				}
				setState(119);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			setState(120);
			match(EXAMPLES_TITLE);
			setState(124);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=1 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(121);
					match(LONG_DESCRIPTION);
					}
					} 
				}
				setState(126);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DATA_ROW) {
				{
				{
				setState(127);
				match(DATA_ROW);
				}
				}
				setState(132);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class StartingStepContext extends ParserRuleContext {
		public TerminalNode GIVEN_STEP() { return getToken(GherkinParser.GIVEN_STEP, 0); }
		public TerminalNode WHEN_STEP() { return getToken(GherkinParser.WHEN_STEP, 0); }
		public TerminalNode THEN_STEP() { return getToken(GherkinParser.THEN_STEP, 0); }
		public TerminalNode WILD_STEP() { return getToken(GherkinParser.WILD_STEP, 0); }
		public TerminalNode DOCSTRING() { return getToken(GherkinParser.DOCSTRING, 0); }
		public List<TerminalNode> DATA_ROW() { return getTokens(GherkinParser.DATA_ROW); }
		public TerminalNode DATA_ROW(int i) {
			return getToken(GherkinParser.DATA_ROW, i);
		}
		public StartingStepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_startingStep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinParserListener) ((GherkinParserListener)listener).enterStartingStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinParserListener) ((GherkinParserListener)listener).exitStartingStep(this);
		}
	}

	public final StartingStepContext startingStep() throws RecognitionException {
		StartingStepContext _localctx = new StartingStepContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_startingStep);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GIVEN_STEP) | (1L << WHEN_STEP) | (1L << THEN_STEP) | (1L << WILD_STEP))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(141);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EOF:
			case TAGS:
			case EXAMPLES_TITLE:
			case SCENARIO_TITLE:
			case SCENARIO_OUTLINE_TITLE:
			case RULE_TITLE:
			case GIVEN_STEP:
			case WHEN_STEP:
			case THEN_STEP:
			case AND_STEP:
			case BUT_STEP:
			case WILD_STEP:
			case DATA_ROW:
				{
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DATA_ROW) {
					{
					{
					setState(134);
					match(DATA_ROW);
					}
					}
					setState(139);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case DOCSTRING:
				{
				setState(140);
				match(DOCSTRING);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
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

	public static class AnyStepContext extends ParserRuleContext {
		public TerminalNode GIVEN_STEP() { return getToken(GherkinParser.GIVEN_STEP, 0); }
		public TerminalNode WHEN_STEP() { return getToken(GherkinParser.WHEN_STEP, 0); }
		public TerminalNode THEN_STEP() { return getToken(GherkinParser.THEN_STEP, 0); }
		public TerminalNode AND_STEP() { return getToken(GherkinParser.AND_STEP, 0); }
		public TerminalNode BUT_STEP() { return getToken(GherkinParser.BUT_STEP, 0); }
		public TerminalNode WILD_STEP() { return getToken(GherkinParser.WILD_STEP, 0); }
		public TerminalNode DOCSTRING() { return getToken(GherkinParser.DOCSTRING, 0); }
		public List<TerminalNode> DATA_ROW() { return getTokens(GherkinParser.DATA_ROW); }
		public TerminalNode DATA_ROW(int i) {
			return getToken(GherkinParser.DATA_ROW, i);
		}
		public AnyStepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anyStep; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinParserListener) ((GherkinParserListener)listener).enterAnyStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GherkinParserListener) ((GherkinParserListener)listener).exitAnyStep(this);
		}
	}

	public final AnyStepContext anyStep() throws RecognitionException {
		AnyStepContext _localctx = new AnyStepContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_anyStep);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GIVEN_STEP) | (1L << WHEN_STEP) | (1L << THEN_STEP) | (1L << AND_STEP) | (1L << BUT_STEP) | (1L << WILD_STEP))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(151);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EOF:
			case TAGS:
			case EXAMPLES_TITLE:
			case SCENARIO_TITLE:
			case SCENARIO_OUTLINE_TITLE:
			case RULE_TITLE:
			case GIVEN_STEP:
			case WHEN_STEP:
			case THEN_STEP:
			case AND_STEP:
			case BUT_STEP:
			case WILD_STEP:
			case DATA_ROW:
				{
				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DATA_ROW) {
					{
					{
					setState(144);
					match(DATA_ROW);
					}
					}
					setState(149);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case DOCSTRING:
				{
				setState(150);
				match(DOCSTRING);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3&\u009c\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\5\2"+
		"\26\n\2\3\2\7\2\31\n\2\f\2\16\2\34\13\2\3\2\3\2\6\2 \n\2\r\2\16\2!\3\2"+
		"\5\2%\n\2\3\3\7\3(\n\3\f\3\16\3+\13\3\3\3\3\3\7\3/\n\3\f\3\16\3\62\13"+
		"\3\3\3\5\3\65\n\3\3\3\3\3\7\39\n\3\f\3\16\3<\13\3\3\4\3\4\7\4@\n\4\f\4"+
		"\16\4C\13\4\3\4\5\4F\n\4\3\4\7\4I\n\4\f\4\16\4L\13\4\3\5\3\5\7\5P\n\5"+
		"\f\5\16\5S\13\5\3\5\5\5V\n\5\3\6\7\6Y\n\6\f\6\16\6\\\13\6\3\6\3\6\7\6"+
		"`\n\6\f\6\16\6c\13\6\3\6\5\6f\n\6\3\6\7\6i\n\6\f\6\16\6l\13\6\3\7\3\7"+
		"\7\7p\n\7\f\7\16\7s\13\7\3\b\7\bv\n\b\f\b\16\by\13\b\3\b\3\b\7\b}\n\b"+
		"\f\b\16\b\u0080\13\b\3\b\7\b\u0083\n\b\f\b\16\b\u0086\13\b\3\t\3\t\7\t"+
		"\u008a\n\t\f\t\16\t\u008d\13\t\3\t\5\t\u0090\n\t\3\n\3\n\7\n\u0094\n\n"+
		"\f\n\16\n\u0097\13\n\3\n\5\n\u009a\n\n\3\n\b)\60QZw~\2\13\2\4\6\b\n\f"+
		"\16\20\22\2\5\3\2\27\30\4\2\32\34\37\37\3\2\32\37\2\u00ad\2$\3\2\2\2\4"+
		")\3\2\2\2\6=\3\2\2\2\bM\3\2\2\2\nZ\3\2\2\2\fm\3\2\2\2\16w\3\2\2\2\20\u0087"+
		"\3\2\2\2\22\u0091\3\2\2\2\24\26\7\3\2\2\25\24\3\2\2\2\25\26\3\2\2\2\26"+
		"\32\3\2\2\2\27\31\7\"\2\2\30\27\3\2\2\2\31\34\3\2\2\2\32\30\3\2\2\2\32"+
		"\33\3\2\2\2\33\35\3\2\2\2\34\32\3\2\2\2\35%\5\4\3\2\36 \7\"\2\2\37\36"+
		"\3\2\2\2 !\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"%\3\2\2\2#%\7\2\2\3$\25\3\2"+
		"\2\2$\37\3\2\2\2$#\3\2\2\2%\3\3\2\2\2&(\7\23\2\2\'&\3\2\2\2(+\3\2\2\2"+
		")*\3\2\2\2)\'\3\2\2\2*,\3\2\2\2+)\3\2\2\2,\60\7\24\2\2-/\7&\2\2.-\3\2"+
		"\2\2/\62\3\2\2\2\60\61\3\2\2\2\60.\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2"+
		"\63\65\5\b\5\2\64\63\3\2\2\2\64\65\3\2\2\2\65:\3\2\2\2\669\5\6\4\2\67"+
		"9\5\n\6\28\66\3\2\2\28\67\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;\5\3\2"+
		"\2\2<:\3\2\2\2=A\7\31\2\2>@\7&\2\2?>\3\2\2\2@C\3\2\2\2A?\3\2\2\2AB\3\2"+
		"\2\2BE\3\2\2\2CA\3\2\2\2DF\5\b\5\2ED\3\2\2\2EF\3\2\2\2FJ\3\2\2\2GI\5\n"+
		"\6\2HG\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2K\7\3\2\2\2LJ\3\2\2\2MQ\7"+
		"\25\2\2NP\7&\2\2ON\3\2\2\2PS\3\2\2\2QR\3\2\2\2QO\3\2\2\2RU\3\2\2\2SQ\3"+
		"\2\2\2TV\5\f\7\2UT\3\2\2\2UV\3\2\2\2V\t\3\2\2\2WY\7\23\2\2XW\3\2\2\2Y"+
		"\\\3\2\2\2Z[\3\2\2\2ZX\3\2\2\2[]\3\2\2\2\\Z\3\2\2\2]a\t\2\2\2^`\7&\2\2"+
		"_^\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2be\3\2\2\2ca\3\2\2\2df\5\f\7\2"+
		"ed\3\2\2\2ef\3\2\2\2fj\3\2\2\2gi\5\16\b\2hg\3\2\2\2il\3\2\2\2jh\3\2\2"+
		"\2jk\3\2\2\2k\13\3\2\2\2lj\3\2\2\2mq\5\20\t\2np\5\22\n\2on\3\2\2\2ps\3"+
		"\2\2\2qo\3\2\2\2qr\3\2\2\2r\r\3\2\2\2sq\3\2\2\2tv\7\23\2\2ut\3\2\2\2v"+
		"y\3\2\2\2wx\3\2\2\2wu\3\2\2\2xz\3\2\2\2yw\3\2\2\2z~\7\26\2\2{}\7&\2\2"+
		"|{\3\2\2\2}\u0080\3\2\2\2~\177\3\2\2\2~|\3\2\2\2\177\u0084\3\2\2\2\u0080"+
		"~\3\2\2\2\u0081\u0083\7 \2\2\u0082\u0081\3\2\2\2\u0083\u0086\3\2\2\2\u0084"+
		"\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\17\3\2\2\2\u0086\u0084\3\2\2"+
		"\2\u0087\u008f\t\3\2\2\u0088\u008a\7 \2\2\u0089\u0088\3\2\2\2\u008a\u008d"+
		"\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u0090\3\2\2\2\u008d"+
		"\u008b\3\2\2\2\u008e\u0090\7\22\2\2\u008f\u008b\3\2\2\2\u008f\u008e\3"+
		"\2\2\2\u0090\21\3\2\2\2\u0091\u0099\t\4\2\2\u0092\u0094\7 \2\2\u0093\u0092"+
		"\3\2\2\2\u0094\u0097\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096"+
		"\u009a\3\2\2\2\u0097\u0095\3\2\2\2\u0098\u009a\7\22\2\2\u0099\u0095\3"+
		"\2\2\2\u0099\u0098\3\2\2\2\u009a\23\3\2\2\2\34\25\32!$)\60\648:AEJQUZ"+
		"aejqw~\u0084\u008b\u008f\u0095\u0099";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}