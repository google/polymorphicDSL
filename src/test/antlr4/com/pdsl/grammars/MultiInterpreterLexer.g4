lexer grammar MultiInterpreterLexer;
import GherkinCommonLexer;

fragment WS : [\r\n\t ] ;
fragment END : WS* ([\r\n]+ | EOF) ;
fragment AND : 'And ';
fragment BUT : 'But ';
fragment WILD : '* ' ;
fragment GIVEN : 'Given ' | AND | BUT ;
fragment WHEN : 'When ' | AND | BUT ;
fragment THEN : 'Then ' | AND | BUT ;

fragment GHERKIN_STEP : WS* (GIVEN | WHEN | THEN );

FIRST_INTERPRETER : GHERKIN_STEP 'a test runner has more than one interpreter' END;
RECOGNIZE_INTERPRETER : GHERKIN_STEP 'the recognizer recognizes all sentences' END;
PARSE_SENTENCE : GHERKIN_STEP 'a sentence is parsed by only one interpreter' END;
IGNORE_INTERPRETER : GHERKIN_STEP 'the other parsers will ignore it' END;
PARSED_BY_ALL_INTERPRETERS : GHERKIN_STEP 'a sentence is parsed by all interpreters' END;
EXECUTED_BY_ALL_PARSERS : GHERKIN_STEP 'all parsers execute it' END;

