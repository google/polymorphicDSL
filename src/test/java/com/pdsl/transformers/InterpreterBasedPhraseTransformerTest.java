package com.pdsl.transformers;

import static com.google.common.truth.Truth.assertThat;
import com.pdsl.transformers.InterpreterBasedPhraseFilter;
import com.pdsl.transformers.LineDelimitedPhraseTransformer;
import com.pdsl.transformers.PhraseTransformer;
import com.pdsl.transformers.PolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    //TODO: Write tests to handle invalid paths throughout the factory
}
