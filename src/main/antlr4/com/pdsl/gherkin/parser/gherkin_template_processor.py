import json

print("Make sure script is run using python3")

def generate_keyword_body(keywords):
    first_keyword = keywords[0].replace("'", "\\'")
    body = "\n\t("
    body += "'" +  keywords[0].replace("'", "\\'") + "'"
    
    if len(body) > 1:
        for keyword in range(1,len(keywords)): 
            body += "\n\t\t| '{k}'".format(k=keywords[keyword].replace("'", "\\'"))
    body += "\n\t) "
    return body
 
language_data = json.load(open('languages/gherkin-languages.json'))
template = """lexer grammar GherkinLexer;

fragment LANGUAGE_KEYWORD : WS* '#' WS* 'language' WS* ':' WS*;
LANGUAGE_HEADER : LANGUAGE_KEYWORD 'en' LINE_END -> mode(DEFAULT_MODE) ;
"""

for language in language_data.keys():
    l = language_data[language]
    ul = language.upper().replace("-", "_")
    template += "\n" + ul + "_LANGUAGE_HEADER : LANGUAGE_KEYWORD '" + language + "' LINE_END -> mode(" + ul + "), type(LANGUAGE_HEADER) ;"

template += "\n//////////////////////////////////////////////////////////////////////////"

template += '''
FEATURE_KEYWORD : ('Feature'
	| 'Business Need'
	| 'Ability') ':' -> channel(HIDDEN) ;
SCENARIO_KEYWORD : ('Scenario' | 'Example') ':' -> channel(HIDDEN) ;
SCENARIO_OUTLINE_KEYWORD : 'Scenario Outline:' -> channel(HIDDEN);
BACKGROUND_KEYWORD : 'Background:' ;
EXAMPLES_KEYWORD : 'Examples:' | 'Scenarios:';
RULE_KEYWORD : 'Rule:' ;
STARTING_STEP_KEYWORD : GIVEN_KEYWORD
	| WHEN_KEYWORD
	| THEN_KEYWORD
	| WILD_KEYWORD
	;
ALTERNATIVE_STEP_KEYWORD : AND_KEYWORD
	| BUT_KEYWORD
	| GIVEN_KEYWORD
	;
GIVEN_KEYWORD : 'Given ' ;
WHEN_KEYWORD : 'When ' ;
THEN_KEYWORD : 'Then ' ;
WILD_KEYWORD : '* ' ;
AND_KEYWORD : 'And ';
BUT_KEYWORD : 'But ';
fragment CAPTURE_DATA : '<' ~[>\\t\\r\\n ]'>' ;
fragment DOCSTRING_DOUBLE_QUOTES : WS* '"""' (CAPTURE_DATA | ~'"' | '"' ~'"')*?  '"""' LINE_END ;
fragment DOCSTRING_BACKTICKS : WS* '```' (~'`' | CAPTURE_DATA | '`' ~'`').*? '```' LINE_END;
fragment TAG : '@'~[ \\r\\n\\t@]+ ;
fragment ESCAPE_SEQUENCE : '\\\\' [\\\\|\\n]* ;
fragment CELL_CHARACTER
	:	CAPTURE_DATA
	| ~[\\r\\n|\\\\]
	|	ESCAPE_SEQUENCE
	;
fragment CELL_DATA : WS* CELL_CHARACTER* '|';

DOCSTRING : DOCSTRING_DOUBLE_QUOTES | DOCSTRING_BACKTICKS ;
TAGS : WS* TAG (WS* TAG)* (COMMENT? | LINE_END);
FEATURE_TITLE : WS* FEATURE_KEYWORD ~[\\r\\n]* LINE_END ;
BACKGROUND_TITLE : WS* BACKGROUND_KEYWORD ~[\\r\\n]* COMMENT? LINE_END ;
EXAMPLES_TITLE : WS* EXAMPLES_KEYWORD ~[\\r\\n]* COMMENT? LINE_END ;
SCENARIO_TITLE : WS* SCENARIO_KEYWORD ~[\\r\\n]* LINE_END ;
SCENARIO_OUTLINE_TITLE : WS* SCENARIO_OUTLINE_KEYWORD (CAPTURE_DATA | ~[\\r\\n])* LINE_END ;
RULE_TITLE : WS* RULE_KEYWORD ~[\\r\\n]* LINE_END ;

GIVEN_STEP : WS* GIVEN_KEYWORD ~[ @\\r\\n|] ~[\\r\\n]* LINE_END;
WHEN_STEP : WS* WHEN_KEYWORD ~[ @\\r\\n|] ~[\\r\\n]* LINE_END;
THEN_STEP : WS* THEN_KEYWORD ~[ @\\r\\n|] ~[\\r\\n]* LINE_END;
AND_STEP : WS* AND_KEYWORD ~[ @\\r\\n|] ~[\\r\\n]* LINE_END;
BUT_STEP : WS* BUT_KEYWORD ~[ @\\r\\n|] ~[\\r\\n]* LINE_END;
WILD_STEP : WS* WILD_KEYWORD ~[ @\\r\\n|] ~[\\r\\n]* LINE_END;

DATA_ROW : WS* '|' CELL_DATA+ LINE_END ;
INVALID_LANGUAGE_HEADER : LANGUAGE_KEYWORD ~[\\r\\n]* LINE_END ;
COMMENT : WS* '#' ~[\\r\\n]* LINE_END -> channel(HIDDEN) ;
LINE_END : WS* (NEWLINE+ | EOF) -> skip;
NEWLINE : [\\r\\n] -> skip ;
WS : [ \\t] -> skip;
LONG_DESCRIPTION : WS* ~[ @\\r\\n|] ~[\\r\\n]* LINE_END ;
///////////////////////////////////////////////////
'''
for language in language_data.keys():
    print(language)
    ul = language.upper().replace('-', '_')
    ld = language_data[language]
    template += "\n//" + ld["name"]
    template += "\n//" + ld["native"]
    template += "\nmode " + ul + ";"
    # Feature
    template += "\n\t" + ul + "_FEATURE : ( "
    template += generate_keyword_body(ld["feature"]) + "':'"
    template += "\n\t\t) -> type(FEATURE_KEYWORD) ;"
    # Background
    template += """

    {lc}_BACKGROUND : (
    """.format(lc=ul)
    template += generate_keyword_body(ld["background"]) + "':'"
    template += "\n\t\t) -> type(BACKGROUND_KEYWORD);"

    # Scenario
    template += "\n\t" + ul + "_SCENARIO : (\n"
    template += generate_keyword_body(ld["scenario"]) + "':'"
    template += "\n\t) -> type(SCENARIO_KEYWORD);\n"

    # Scenario Outline
    template += "\n\t" + ul + "_SCENARIO_OUTLINE : (\n"
    template += generate_keyword_body(ld["scenarioOutline"])
    template += "\n\t) -> type(SCENARIO_OUTLINE_KEYWORD);\n"

    # Examples
    template += "\n\t" + ul + "_EXAMPLES : (\n"
    template += generate_keyword_body(ld["examples"]) + "':'"
    template += "  ) -> type(EXAMPLES_KEYWORD) ;\n"

    # Rule
    template += "\n\t" + ul + "_RULE : (\n"
    template += generate_keyword_body(ld["rule"]) + "':'"
    template += "  ) -> type(RULE_KEYWORD) ;\n"

    # Given
    template += "\n\t" + ul + "_GIVEN : (\n"
    given_keywords = ld["given"]
    if "* " in given_keywords: given_keywords.remove("* ")
    template += generate_keyword_body(given_keywords)
    template += "  ) -> type(GIVEN_KEYWORD) ;\n"

    # When
    template += "\n\t" + ul + "_WHEN : (\n"
    when_keywords = ld["when"]
    if "* " in when_keywords: when_keywords.remove("* ")
    template += generate_keyword_body(when_keywords)
    template += "  ) -> type(WHEN_KEYWORD) ;\n"

    # Then
    template += "\n\t" + ul + "_THEN : (\n"
    then_keywords = ld["then"]
    if "* " in then_keywords: then_keywords.remove("* ")
    template += generate_keyword_body(then_keywords)
    template += "  ) -> type(THEN_KEYWORD) ;\n"

    # And
    template += "\n\t" + ul + "_AND : (\n"
    and_keywords = ld["and"]
    if "* " in and_keywords: and_keywords.remove("* ")
    template += generate_keyword_body(and_keywords)
    template += "  ) -> type(AND_KEYWORD) ;\n"

    # But
    template += "\n\t" + ul + "_BUT : (\n"
    but_keywords = ld["but"]
    if "* " in but_keywords: but_keywords.remove("* ")
    template += generate_keyword_body(but_keywords)
    template += "  ) -> type(BUT_KEYWORD) ;\n"

    # Starting step
    template += "\n\t" + ul + """_STARTING_STEP_KEYWORD : (\n
                {lc}_GIVEN
		| {lc}_WHEN
		| {lc}_THEN
		| WILD_KEYWORD
		) -> type(STARTING_STEP_KEYWORD);

    """.format(lc=ul)
    template += "\n\t" + ul + """_ALTERNATIVE_STEP_KEYWORD : (\n
                {lc}_AND
		| {lc}_BUT
		) -> type(ALTERNATIVE_STEP_KEYWORD);

    """.format(lc=ul)

    template += """
    {language_code}_FEATURE_TITLE : WS* {language_code}_FEATURE ~[\\r\\n]* WS* LINE_END -> type(FEATURE_TITLE) ;

    {language_code}_BACKGROUND_TITLE : WS* {language_code}_BACKGROUND ~[\\r\\n]* COMMENT? LINE_END -> type(BACKGROUND_TITLE) ;

    {language_code}_EXAMPLES_TITLE : WS* {language_code}_EXAMPLES ~[\\r\\n]* COMMENT? LINE_END -> type(EXAMPLES_TITLE);

    {language_code}_SCENARIO_TITLE : WS* {language_code}_SCENARIO ~[\\r\\n]* LINE_END -> type(SCENARIO_TITLE);

    {language_code}_SCENARIO_OUTLINE_TITLE : WS* {language_code}_SCENARIO_OUTLINE ~[\\r\\n]* LINE_END -> type(SCENARIO_OUTLINE_TITLE) ;

    {language_code}_RULE_TITLE : WS* {language_code}_RULE ~[\\r\\n]* LINE_END -> type(RULE_TITLE);

        {language_code}_GIVEN_STEP : WS* {language_code}_GIVEN ~[ @\\r\\n|] ~[\\r\\n]* LINE_END -> type(GIVEN_STEP);
	{language_code}_WHEN_STEP : WS* {language_code}_WHEN ~[ @\\r\\n|] ~[\\r\\n]* LINE_END -> type(WHEN_STEP);
	{language_code}_THEN_STEP : WS* {language_code}_THEN ~[ @\\r\\n|] ~[\\r\\n]* LINE_END -> type(THEN_STEP);
	{language_code}_AND_STEP : WS* {language_code}_AND ~[ @\\r\\n|] ~[\\r\\n]* LINE_END -> type(AND_STEP);
	{language_code}_BUT_STEP : WS* {language_code}_BUT ~[ @\\r\\n|] ~[\\r\\n]* LINE_END -> type(BUT_STEP);

""".format(language_code = ul)

    f = open("GherkinLexer.g4", "w")
    f.write(template)
    f.close()
print("GherkinLexer.g4 regenerated")
 
