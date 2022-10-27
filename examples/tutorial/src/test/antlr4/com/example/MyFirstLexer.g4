lexer grammar MyFirstLexer;

GIVEN_I_HAVE: WS* 'Given I have "';
THEN_I_HAVE_MORE_THAN: WS* 'Then I have more than "';
PICKLES: '" pickle' 's'? WS*;
NUMBER: [0-9]+;
fragment WS: [\r\n\t ]+;