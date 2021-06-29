package com.pdsl.transformers;

import static com.google.common.truth.Truth.assertThat;
import com.pdsl.transformers.InterpreterBasedPhraseFilter;
import com.pdsl.transformers.LineDelimitedPhraseTransformer;
import com.pdsl.transformers.PhraseTransformer;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.*;

public class InterpreterBasedPhraseTransformerTest {

    @Test
    public void interpreterBasedPhraseTransformer_ableToAutomaticallyGenerateListenerForFiltering() throws IOException {
        PhraseTransformer lineDelimitedPhraseTransformer = new LineDelimitedPhraseTransformer();
        List<InputStream> input = lineDelimitedPhraseTransformer.testSpecificationPhrases(
                Path.of(System.getProperty("user.dir") + "/src/test/resources/sentences/valid.pdsl"));
        PolymorphicDslPhraseFilter phraseTransformer = new InterpreterBasedPhraseFilter.Builder(Path.of(System.getProperty("user.dir") + "/src/test/java/com/pdsl/grammars"),
                "Omega", "com.pdsl.grammars")
                .withGrammarLexer("AllGrammarsLexer")
                .withSubgrammar("Beta")
                .withSubgrammarLibrary(Path.of(System.getProperty("user.dir") + "/src/test/resources/"))
                .withGrammarLibrary(Path.of(System.getProperty("user.dir") + "/src/test/resources/"))
                .build();
        Optional<List<ParseTree>> parseTreeList = phraseTransformer.validateAndFilterPhrases(input, "valid.pdsl");
        assertThat(parseTreeList.isPresent()).isTrue();
        assertThat(parseTreeList.get().size()).isEqualTo(1);
        assertThat(parseTreeList.get().get(0).getText()).contains("Hello, world!");
    }

    @Test
    public void interpeterBasedPhraseTransformer_canGenerateCodeInSpecifiedParentDirectory() throws IOException {

        Path tmpDir = Files.createTempDirectory(String.format("pdsl_temp_test-%s", UUID.randomUUID()));
        File tempCodeLocation = new File(tmpDir.toString());
        tempCodeLocation.mkdirs();
        assertThat(tempCodeLocation.list().length).isEqualTo(0);
        PhraseTransformer lineDelimitedPhraseTransformer = new LineDelimitedPhraseTransformer();
        List<InputStream> input = lineDelimitedPhraseTransformer.testSpecificationPhrases(
                Path.of(System.getProperty("user.dir") + "/src/test/resources/sentences/valid.pdsl"));
        PolymorphicDslPhraseFilter phraseTransformer = new InterpreterBasedPhraseFilter.Builder(Path.of(System.getProperty("user.dir") + "/src/test/java/com/pdsl/grammars"),
                "Omega", "com.pdsl.grammars")
                .withGrammarLexer("AllGrammarsLexer")
                .withSubgrammar("Beta")
                .withSubgrammarLibrary(Path.of(System.getProperty("user.dir") + "/src/test/resources/"))
                .withGrammarLibrary(Path.of(System.getProperty("user.dir") + "/src/test/resources/"))
                .withCodeGenerationDirectory(tmpDir)
                .build();
        Optional<List<ParseTree>> parseTreeList = phraseTransformer.validateAndFilterPhrases(input, "valid.pdsl");
        assertThat(parseTreeList.isPresent()).isTrue();
        assertThat(parseTreeList.get().size()).isEqualTo(1);
        assertThat(parseTreeList.get().get(0).getText()).contains("Hello, world!");
        assertThat(tempCodeLocation.list().length).isGreaterThan(0);
    }
}
