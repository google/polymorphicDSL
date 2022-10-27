parser grammar DotTestCaseFactoryParser;

options {tokenVocab=DotTestCaseFactoryLexer;}

testSpecifications: HEADER specification+ nestedSpecificationRelationships+ results END_GRAPH EOF;
nestedSpecificationRelationships: linkedSpecification | multiLinkedSpecification;

linkedPhrase: LINK OPEN_QUOTE FILTERED_PHRASE_NAME CLOSE_QUOTE;
linkedSpecification: specificationLabel linkedPhrase+;
multiLinkedSpecification: specificationLabel NESTED_PHRASES_START childSpecification (SPACE childSpecification)+  END_MULTILINK_SPECIFICATION;
childSpecification: C_QUOTE CHILD_SPECIFICATION CHILD_SPEC_QUOTE;

specificationLabel: OPEN_QUOTE FILTERED_PHRASE_NAME CLOSE_QUOTE;
specification: specificationLabel FILTERED_PHRASE_BODY PHRASE (PIPE PHRASE)* END_PHRASE;

results: RESULT_SUBGRAPH
    specification+ // Name of test case outputs with the expected phrases
    nestedSpecificationRelationships+ // Which factories should produce the test case outputs
     END_GRAPH;
