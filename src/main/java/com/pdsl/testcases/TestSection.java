package com.pdsl.testcases;

import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

public interface TestSection {

    Optional<ByteArrayOutputStream> getMetaData();
    ParseTree getParseTree();
}
