lexer grammar DotNavigationLexer;

fragment WS: [\n\r\t ]+;
HEADER: 'digraph website {';
PAGE: (WS? '"' [a-zA-Z ]+ '"')  //In quoted text that allows a space
    | (WS? [a-zA-Z]+); //Otherwise a single word
LINK: WS '->';
ADMIN: WS ('[color="red"]' | '[label="admin only" color="red"]');
END_GRAPH: WS '}' WS? EOF;