lexer grammar SimpleParameterLexer;

fragment WS: (EOF | [\n\r\t ]+);
NUMBER: [0-9]+;
SENTENCE_PARAMETER: [\t ]* ('Given ' | 'And ') 'I have the number "';
END: '"' WS;