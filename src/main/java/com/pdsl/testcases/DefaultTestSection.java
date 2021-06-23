package com.pdsl.testcases;

import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Optional;

public class DefaultTestSection implements TestSection {

    private Optional<ByteArrayOutputStream> metaData;
    private ParseTree parseTree;

    public DefaultTestSection(ByteArrayOutputStream metaData, ParseTree parseTree) {
        this.metaData = Optional.ofNullable(metaData);
        this.parseTree = parseTree;
    }

    public DefaultTestSection(ParseTree parseTree) {
        this.metaData = Optional.empty();
        this.parseTree = parseTree;
    }

    @Override
    public Optional<ByteArrayOutputStream> getMetaData() {
        return metaData;
    }

    @Override
    public ParseTree getParseTree() {
        return parseTree;
    }
}
