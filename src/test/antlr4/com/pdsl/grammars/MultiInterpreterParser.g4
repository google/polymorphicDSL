parser grammar MultiInterpreterParser ;

options {tokenVocab=MultiInterpreterLexer; }

firstInterpreter : FIRST_INTERPRETER;
recognizeInterpreter : RECOGNIZE_INTERPRETER;
parseSentence : PARSE_SENTENCE;
ignoreInterpreter : IGNORE_INTERPRETER;
parsedByAllInterpreters : PARSED_BY_ALL_INTERPRETERS;
executedByAllParsers : EXECUTED_BY_ALL_PARSERS;

parserOneAllRules : (
  firstInterpreter | recognizeInterpreter | parseSentence | ignoreInterpreter | parsedByAllInterpreters | executedByAllParsers
)+;