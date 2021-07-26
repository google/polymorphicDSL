grammar GherkinTags;

fragment A : 'A' | 'a';
fragment N : 'N' | 'n';
fragment D : 'D' | 'd';

fragment O : 'O' | 'o';
fragment T : 'T' | 't';

fragment R : 'R' | 'r';

TAG :'@' ~[@ )]+ ;
WS : [ \t]+ -> skip;
AND : A N D ;
OR : O R;
NOT : N O T;
L_PAREN : '(' ;
R_PAREN : ')' ;
//////////////////////// https://stackoverflow.com/questions/68248549/how-can-i-modify-my-ebnf-to-handle-cases-like-not-12-not       -1
not : (NOT)? expr ;
and : not (AND not)* ;
or : and (OR and)* EOF?;
expr : TAG | (L_PAREN or R_PAREN) ;

