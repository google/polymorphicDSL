#!/bin/bash

clear
PROJECT_DIR="$PWD"
G4_JAR_NAME="pdsl-g4-artifacts.jar"

echo "Project dir: $PROJECT_DIR"


echo "1. Clean ANTLR4/Java artifacts ..."
rm -rfv "$PROJECT_DIR/target/antlr"
rm -fv "$PROJECT_DIR/tests/resources/jar/$G4_JAR_NAME"


echo "2. Generate ANTLR4/Java files ..."
antlr4 -v 4.9.1 -long-messages -Xlog -visitor -listener -Xexact-output-dir -Dlanguage=Java -o $PROJECT_DIR/target/antlr -package com.pdsl.python ./tests/resources/antlr4/*.g4
echo "Move logs."
mv -fuv $PROJECT_DIR/*.log $PROJECT_DIR/target/antlr


echo "3. Compile Java classes ..."
javac -cp "$PROJECT_DIR/tests/resources/jar/antlr4-4.9.1-complete.jar" -d "$PROJECT_DIR/target/antlr/compiled" $PROJECT_DIR/target/antlr/*.java


echo "4. Generate the JAR"
cd "$PROJECT_DIR/target/antlr/compiled"
jar cvf $G4_JAR_NAME *
mv -fvu $G4_JAR_NAME "$PROJECT_DIR/tests/resources/jar/"
cd $PROJECT_DIR


echo "5. Run test ..."
cd tests
pytest -s -v git_hub_api_user_profile_test.py --alluredir=$PROJECT_DIR/target/allure_results --clean-alluredir --allure-link-pattern=issue:https://buganizer.corp.google.com/issues/{}
pytest -s -v git_hub_ui_user_profile_test.py --alluredir=$PROJECT_DIR/target/allure_results --allure-link-pattern=issue:https://buganizer.corp.google.com/issues/{}


echo "6. Allure version: $(allure --version)"
echo "Generate Allure report"
allure generate -c --output "$PROJECT_DIR/target/allure-report"  "$PROJECT_DIR/target/allure_results"

cd $PROJECT_DIR