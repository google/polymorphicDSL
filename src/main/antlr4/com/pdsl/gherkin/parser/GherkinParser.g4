parser grammar GherkinParser; 

options { tokenVocab=GherkinLexer; } 

gherkinDocument : LANGUAGE_HEADER? COMMENT* feature
	| COMMENT+
	| EOF
	;
feature : TAGS*? FEATURE_TITLE LONG_DESCRIPTION*?  background?  (ruleBlock | scenario)*;
ruleBlock : RULE_TITLE  LONG_DESCRIPTION*  background? scenario*;
background : BACKGROUND_TITLE  LONG_DESCRIPTION*? stepBody? ;
scenario : TAGS*? (SCENARIO_TITLE | SCENARIO_OUTLINE_TITLE) LONG_DESCRIPTION* stepBody? examplesBody*;
stepBody :  startingStep  (anyStep)* ;

examplesBody : TAGS*?  EXAMPLES_TITLE  LONG_DESCRIPTION*?  DATA_ROW* ;
startingStep : (GIVEN_STEP | WHEN_STEP | THEN_STEP | WILD_STEP) (DATA_ROW* | DOCSTRING) ;
anyStep : (GIVEN_STEP | WHEN_STEP | THEN_STEP | AND_STEP | BUT_STEP | WILD_STEP) (DATA_ROW* | DOCSTRING) ;

fullFeatureBlock: LANGUAGE_HEADER? COMMENT* TAGS*? FEATURE_TITLE LONG_DESCRIPTION*?;

validationMissingFeature : LANGUAGE_HEADER? COMMENT* TAGS*? LONG_DESCRIPTION*?;
validationMissingScenario : fullFeatureBlock TAGS*? LONG_DESCRIPTION* stepBody? examplesBody*;
vadalitionScenarioWithoutSteps: fullFeatureBlock TAGS*? (SCENARIO_TITLE | SCENARIO_OUTLINE_TITLE);
validationPresenceExamplesForScenario: fullFeatureBlock TAGS*? SCENARIO_TITLE LONG_DESCRIPTION* stepBody? TAGS*? EXAMPLES_TITLE LONG_DESCRIPTION*?;
validationMissingExamplesRowsForScenarioOutline: fullFeatureBlock TAGS*? SCENARIO_OUTLINE_TITLE LONG_DESCRIPTION* stepBody? TAGS*? EXAMPLES_TITLE;
validationFeatureTagsWithSpaces: LANGUAGE_HEADER? COMMENT* TAGS_WITH_SPACES FEATURE_TITLE LONG_DESCRIPTION*?;
validationScenarioTagsWithSpaces: fullFeatureBlock TAGS_WITH_SPACES SCENARIO_TITLE LONG_DESCRIPTION* stepBody?;
validationAndAfterScenario: fullFeatureBlock TAGS*? (SCENARIO_TITLE | SCENARIO_OUTLINE_TITLE) LONG_DESCRIPTION* AND_STEP stepBody?;
validationSeveralFeatureDeclaration: fullFeatureBlock fullFeatureBlock;
validationTagsBetweenSteps: fullFeatureBlock TAGS*? (SCENARIO_TITLE | SCENARIO_OUTLINE_TITLE) startingStep TAGS+ (anyStep)*;
validationStepsBetweenFeatureAndScenario: fullFeatureBlock stepBody TAGS*? (SCENARIO_TITLE | SCENARIO_OUTLINE_TITLE);

validation : (
    validationStepsBetweenFeatureAndScenario
    | validationTagsBetweenSteps
    | vadalitionScenarioWithoutSteps
    | validationSeveralFeatureDeclaration
    | validationScenarioTagsWithSpaces
    | validationFeatureTagsWithSpaces
    | validationPresenceExamplesForScenario
    | validationMissingExamplesRowsForScenarioOutline
    | validationAndAfterScenario
    | validationMissingScenario
    | validationMissingFeature
);
