grammar SimpleArithmetic;

INTEGER_VALUE : [0-9]+ ;
WS: [ \t]+ ;
PLUS : '+' ;
EQUALS : '=' ;
NEWLINE : [\r\n]+ ;
integerValue :INTEGER_VALUE ;

mathExpression : WS* integerValue WS* PLUS WS* integerValue EQUALS WS* integerValue (NEWLINE | EOF) ;

polymorphicDslAllRules : mathExpression+;