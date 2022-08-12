lexer grammar BetaLexer;

HELLO : 'Hello, ' ;
WORLD : 'world!' ;
END_OF_LINE : EOF -> skip;
