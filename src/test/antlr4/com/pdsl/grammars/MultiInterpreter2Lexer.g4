lexer grammar MultiInterpreter2Lexer;
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

EXECUTE_SENTENCE : GHERKIN_STEP 'that sentence is only executed by that interpreter' END;
PARSED_BY_ALL_INTERPRETERS : GHERKIN_STEP 'a sentence is parsed by all interpreters' END;