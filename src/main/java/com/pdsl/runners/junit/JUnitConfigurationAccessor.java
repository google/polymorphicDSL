package com.pdsl.runners.junit;

/**
 * A key that exists to allow instantiation of default classes used in com.pdsl.runners.PDslConfiguration which
 * are not available for end users to instantiate.
 *
 * Certain classes like EmptyRecognizerLexer, EmptyRecognizerParser, etc are not intended for end users and
 * are for the PDSL framework to use internally.
 */
public class JUnitConfigurationAccessor {
    //Using the default constructor allows the use of this class to be restricted to internal use of the PDSL framework.
    JUnitConfigurationAccessor(){}
}
