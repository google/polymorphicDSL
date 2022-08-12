lexer grammar PolymorphicDslCommonLexer;

NUMBER : [0-9]+ DECIMAL? ;
fragment DECIMAL : '.' [0-9]+ ;
WS : [ \t\r\n] -> skip ;
END_OF_FILE : EOF -> skip ;
