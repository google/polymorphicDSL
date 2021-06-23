package com.pdsl.transformers;

import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

public interface PhraseTransformer {

    /**
     * Reads a file and determines which parts of it
     * @param path
     * @return
     */
    List<InputStream> testSpecificationPhrases(Path path);
}
