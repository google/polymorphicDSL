parser grammar MultiInterpreterRecognizerParser;
options {tokenVocab = MultiInterpreterRecognizerLexer;}
import MultiInterpreter2Parser, MultiInterpreterParser;

polymorphicDslAllRules:(
      parserOneAllRules
    | parserTwoAllRules
)+;