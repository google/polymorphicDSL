lexer grammar DotTestCaseFactoryLexer;

COMMENT: '//' ~[\n\r]+ ('\r\n' | '\n') -> channel(HIDDEN);
fragment WS: [\n\r\t ]+;
fragment DIGIT: [0-9]+;
HEADER: 'digraph test_specification {' WS+;
FILTERED_PHRASE_BODY:  WS? '[shape=record label="' -> mode(PHRASE_MODE);
OPEN_QUOTE: '"' -> mode(NAME_MODE);
LINK: WS? '-> ';
NESTED_PHRASES_START: LINK '{' -> mode(LINKED_SPECIFICATIONS_MODE);

END_GRAPH: WS? '}' WS;
RESULT_SUBGRAPH: WS* 'subgraph result {' WS+;

mode NAME_MODE;
    FILTERED_PHRASE_NAME: ~'"'+ ;
    CLOSE_QUOTE: '"' WS? -> mode(DEFAULT_MODE);

mode PHRASE_MODE;
    PHRASE: ~('"'|'|')+;
    PIPE: '|';
    END_PHRASE: '"]' [\n\r\t ]+ -> mode(DEFAULT_MODE);

mode LINKED_SPECIFICATIONS_MODE;
    END_MULTILINK_SPECIFICATION: '}' [\r\n\t ]+ -> mode(DEFAULT_MODE);
    SPACE: ' '+;
    C_QUOTE: '"' -> mode(CHILD_SPECIFICATION_MODE);

mode CHILD_SPECIFICATION_MODE;
    CHILD_SPECIFICATION: ~'"'+;
    CHILD_SPEC_QUOTE: '"' -> mode(LINKED_SPECIFICATIONS_MODE);