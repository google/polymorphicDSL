package com.pdsl.testcases;

import com.pdsl.specifications.Phrase;

import java.io.InputStream;
import java.util.Optional;

public interface TestSection {

    Optional<InputStream> getMetaData();

    Phrase getPhrase();
}
