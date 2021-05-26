parser grammar AlphaParser;

options {tokenVocab=AlphaLexer; }

mathematical_expression : (SUM
                          | MINUS
                          | PRODUCT
                          )
                          ;

