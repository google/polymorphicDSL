parser grammar MultiInterpreter2Parser ;

options {tokenVocab=MultiInterpreter2Lexer; }

executeSentence : EXECUTE_SENTENCE;
parsedByAllInterpreters : PARSED_BY_ALL_INTERPRETERS;

parserTwoAllRules : (
executeSentence | parsedByAllInterpreters
)+;
