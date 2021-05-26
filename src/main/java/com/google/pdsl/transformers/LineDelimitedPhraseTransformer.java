package com.google.pdsl.transformers;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Parses each line as it's own phrase.
 *
 * All lines are separated by newlines or carriage returns.
 * The newlines are not preserved in the final output.
 */
public class LineDelimitedPhraseTransformer implements PhraseTransformer {

    @Override
    public List<InputStream> testSpecificationPhrases(Path path) {
        try {
            List<String> allLines = Files.readAllLines(path);
            return allLines.stream()
                    .map(s -> new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8)))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new PolymorphicDslFileException("Polymorphic Dsl framework could not find the supplied file!", e);
        }
    }
}
