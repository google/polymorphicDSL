lexer grammar BadLexer;

/* Contrast this lexer with PowerfulParameterLexer

Despite the fact that it has the same tokens, the fact that it is not split into different 'modes' causes a problem.
A docstring and quoted string are so similar it causes ambiguity:
"Some phrase"

"""
some phrase
"""

This ambiguity causes it to produce an error node. These can be very difficult to debug (and it is a good idea to make
sure you always throw an exception with the visitErrorNode in any listener you produce for this reason).

The solution is to make your lexer ignore all of the other similar tokens when it is parsing a specific type. This
is done by making the 'Island Grammars' seen in PowerfulParameterLexer.g4
*/
fragment WS:[\n\r\t ]+ EOF?;
GIVEN_THE_PARAMETER: WS? ('Given ' | 'And ') 'the parameter';
OPEN_QUOTE: ' "' ;
DOCSTRING_START: ':' WS '"""' ;

CLOSE_QUOTE: '"' WS? -> mode(DEFAULT_MODE);
BODY: ~'"'+;

END_DOCSTRING: '"""' WS -> mode(DEFAULT_MODE);
DOCSTRING: .+?;
