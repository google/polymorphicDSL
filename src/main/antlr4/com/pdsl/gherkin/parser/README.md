# Updating the Gherkin Lexer and Parser

Antlr does not have an elegant way of defining certain grammars concisely. As a result the lexer is quite large with "island grammars" in order to support multiple dialects.

In order to allow the lexer to be maintained a python helper script was developed to take json as input and regenerate the lexer

## Updating the Gherkin Standard

The source of truth comes from the cucumber framework. You can download the JSON file and then run the script
https://github.com/cucumber/common/blob/master/gherkin/gherkin-languages.json

### Adding new languages

Mofify gherkin-languages.json providing the necessary key-value pairs


### Run the Script

```
python3 gherkin_template_processor.py
antlr4 GherkinLexer.g4
javac *.java
```

### Test the new generated script

From this directory you can run the antlr test rig on one of the i18n files:

`grun Gherkin gherkinDocument -tokens ../../../../../../test/resources/testdata/good/i18n_fr.feature`

## Update the project

Currently the process is manual because the gherkin standard does not change often.
If this becomes too time consuming we can convert the project into multiple maven modules.

### Generate the sources

From the project root run the following command:

`mvn antlr4:antlr4`

### Move the sources into the main project

Manually move the generated files from target/generated-sources/antlr4 to the corresponding directory in src/main/java


