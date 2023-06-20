parser grammar PdslAdvancedGherkinParser;

import AdvancedGherkinParser;

options{tokenVocab=AdvancedGherkinLexer;}

polymorphicDslSyntaxCheck: advancedGherkinAllRules+;
polymorphicDslAllRules: advancedGherkinAllRules;