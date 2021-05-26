lexer grammar AlphaLexer;

import PolymorphicDslCommonLexer;

fragment ADD : '+' ;
fragment SUBTRACT : '-' ;
fragment MULTIPLY : '*' ;
fragment DIVIDE : '/' ;
SUM : WS* NUMBER WS* ADD WS* NUMBER WS* [\r\n]? EOF?;
MINUS : WS* NUMBER WS SUBTRACT WS* NUMBER WS* [\r\n]? EOF?;
PRODUCT : WS* NUMBER WS* MULTIPLY WS* NUMBER WS* [\r\n]? EOF?;
