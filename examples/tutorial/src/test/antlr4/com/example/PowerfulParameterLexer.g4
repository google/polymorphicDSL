lexer grammar PowerfulParameterLexer;

fragment WS:[\n\r\t ]+ EOF?;
GIVEN_THE_PARAMETER: WS? ('Given ' | 'And ') 'the parameter';
OPEN_QUOTE: ' "' -> mode(QUOTED_MODE);
DOCSTRING_START: ':' WS '"""' -> mode(DOCSTRING_MODE);

mode QUOTED_MODE;
    CLOSE_QUOTE: '"' WS? -> mode(DEFAULT_MODE);
    BODY: ~'"'+;

mode DOCSTRING_MODE;
    END_DOCSTRING: '"""' WS -> mode(DEFAULT_MODE);
    DOCSTRING: .+?;