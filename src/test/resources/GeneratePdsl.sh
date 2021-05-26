#!/bin/bash

echo "Removing old grammar files"
#rm ../java/com/google/pdsl/grammars/*

echo "\n\n\nCreating Full Step Registry";
# First the registry lexer and parser need to be compiled (in that order)
# This is to create the .interp file which we extract all the rule names from
echo "(Skipping because this should have been manually done)"
echo "antlr4 RegistryLexer.g4 -package com.google.pdsl.grammars"
echo "antlr4 RegistryParser.g4 -package com.google.pdsl.grammars"
echo "antlr4 PolymorphicDslRegistryParser.g4 -package com.google.pdsl.grammars"

echo "\n\n\nCompiling grammar into newly created PolyMorphicDsl grammar"
#Second, manually get the rules from the .interp file and creat a PDSL file, then compile that
# (The manual extraction should be moved to the manual tool we created)

echo "\n\n\nMoving StepRegistry generated files"
mv *.java ../java/com/google/pdsl/grammars/
mv *.tokens ../java/com/google/pdsl/grammars/
mv *.interp ../java/com/google/pdsl/grammars/
