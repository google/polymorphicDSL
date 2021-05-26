package com.google.pdsl;

import com.google.pdsl.grammars.MinimalLexer;
import com.google.pdsl.grammars.PolymorphicDslMinimalParser;
import org.antlr.v4.Tool;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.tool.ANTLRMessage;
import org.antlr.v4.tool.DefaultToolListener;
import org.junit.Test;
import org.stringtemplate.v4.ST;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PdslParserTest {

    @Test
    public void proofOfConcept() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        // Get all rules from a pre-existing parser
        List<String> interpreter = Files.readAllLines(Path.of("src/test/resources/gen/AlphaParser.interp"));
        boolean foundRules = false;
        List<String> allRules = new LinkedList<>();
        for (String line : interpreter) {
            if (foundRules && !line.isBlank()) {
                allRules.add(line);
            } else if (!foundRules) {
                if (line.startsWith("rule names:")) { //All subsequent non-blank lines are rules
                    foundRules = true;
                }
                continue; // Either we are still looking for the rule section or the next line will be the first rule
            } else { // We read all the rules and have hit an empty line indicating it is time to stop
                break;
            }
        }

        // Create a new ANTLR4 parser that matches any of these rules
        createAllRulesParser("PdslTestAlphaParser", "AlphaParser", "AlphaLexer", allRules, "src/test/resources/gen/");
        runAntlrToolOnGeneratedFile();
        //antlrTool.processGrammarsOnCommandLine();
        // Return the new parser
        Parser polymorphicDslMinimalParser = new PolymorphicDslMinimalParser(
                new CommonTokenStream(
                        new MinimalLexer(
                                CharStreams.fromString("Given the minimalism"))));
        polymorphicDslMinimalParser.setErrorHandler(new BailErrorStrategy());

        // Call method via reflection
        ParseTree parseTree = (ParseTree) polymorphicDslMinimalParser.getClass().getMethod("polymorphicDslAllRules").invoke(polymorphicDslMinimalParser, null);
    }

    private void createAllRulesParser(String newParserName, String oldParserName, String lexerName, List<String> allRules, String fileLocation) throws IOException {
        String fileContent = String.format(String.join("\n",
                "parser grammar %s;", //Parser name
                "import %s;", // Import parser we based the rules off of
                "options {tokenVocab=RegistryLexer;}", // The lexer used in the parser we based the rules off of
                "",
                "polymorphicDslAllRules:",
                "\t(",
                "\t\t" + String.join("\n\t\t| ", allRules), // All of the rules separated by newline and OR orule
                "\t)+",
                "\t;"
        ), newParserName, oldParserName, lexerName);
        if (!fileLocation.endsWith("/")) {
            fileLocation += "/";
        }
        // Write the file
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation + newParserName + ".g4"));
        writer.write(fileContent);
        writer.close();
    }

    private void runAntlrToolOnGeneratedFile() {

    }

    /** Run ANTLR on stuff in workdir and error queue back */
    /*
    public static ErrorQueue antlrOnString(String workdir,
                                           String targetName,
                                           String grammarFileName,
                                           boolean defaultListener,
                                           String... extraOptions)
    {
        final List<String> options = new ArrayList<>();
        Collections.addAll(options, extraOptions);
        if ( targetName!=null ) {
            options.add("-Dlanguage="+targetName);
        }
        if ( !options.contains("-o") ) {
            options.add("-o");
            options.add(workdir);
        }
        if ( !options.contains("-lib") ) {
            options.add("-lib");
            options.add(workdir);
        }
        if ( !options.contains("-encoding") ) {
            options.add("-encoding");
            options.add("UTF-8");
        }
        options.add(new File(workdir,grammarFileName).toString());

        final String[] optionsA = new String[options.size()];
        options.toArray(optionsA);
        Tool antlr = new Tool(optionsA);
        ErrorQueue equeue = new ErrorQueue(antlr);
        antlr.addListener(equeue);
        if (defaultListener) {
            antlr.addListener(new DefaultToolListener(antlr));
        }
        synchronized (antlrLock) {
            antlr.processGrammarsOnCommandLine();
        }

        List<String> errors = new ArrayList<>();

        if ( !defaultListener && !equeue.errors.isEmpty() ) {
            for (int i = 0; i < equeue.errors.size(); i++) {
                ANTLRMessage msg = equeue.errors.get(i);
                ST msgST = antlr.errMgr.getMessageTemplate(msg);
                errors.add(msgST.render());
            }
        }
        if ( !defaultListener && !equeue.warnings.isEmpty() ) {
            for (int i = 0; i < equeue.warnings.size(); i++) {
                ANTLRMessage msg = equeue.warnings.get(i);
                // antlrToolErrors.append(msg); warnings are hushed
            }
        }

        return equeue;
    }
*/
}
