lexer grammar GherkinCommonLexer;

fragment WS : [\r\n\t ] ;
fragment END : WS* ([\r\n]+ | EOF) ;
fragment AND : 'And ';
fragment BUT : 'But ';
fragment WILD : '* ' ;
fragment GIVEN : 'Given ' | AND | BUT ;
fragment WHEN : 'When ' | AND | BUT ;
fragment THEN : 'Then ' | AND | BUT ;
fragment CAPTURE_DATA : '<' ~[>\t\r\n ]'>' ;
fragment DOCSTRING_DOUBLE_QUOTES : WS* '"""' .*?  '"""' END;
fragment DOCSTRING_BACKTICKS : WS* '```' .*? '```' END;
fragment ESCAPE_SEQUENCE : '\\' [\\|\n]* ;
fragment CELL_CHARACTER
	:	CAPTURE_DATA
	| ~[\r\n|\\]
	|	ESCAPE_SEQUENCE
	;
fragment CELL_DATA : WS* CELL_CHARACTER* '|';
DOCSTRING : DOCSTRING_DOUBLE_QUOTES | DOCSTRING_BACKTICKS ;
fragment DATA_ROW : WS* '|' CELL_DATA+ END ;

fragment GHERKIN_STEP_KEYWORD : WS* ( GIVEN | WHEN | THEN | WILD );
fragment INT : [0-9]+ ;
fragment QUOTED_TEXT_END : '"' ~["\\"\r\n]+ '"' END;
fragment QUOTED_TEXT : '"' ~["\\"\r\n]+ '"' ;
fragment NEWLINE : [\r\n];
