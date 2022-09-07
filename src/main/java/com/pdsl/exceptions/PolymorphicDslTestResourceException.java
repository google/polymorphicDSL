package com.pdsl.exceptions;

/** Error thrown when PDSL is unable to acquire, process or otherwise interact meaningfully with Test Resources.
 *
 * Test Resources are URIs that test cases are derived from. Concrete examples of a Test Resource include a Gherkin
 * file on a hard drive or a data stream from some HTTP endpoint.
 */
public class PolymorphicDslTestResourceException extends RuntimeException {
    public PolymorphicDslTestResourceException(String error) {
        super(error);
    }
    public PolymorphicDslTestResourceException(String error, Exception e) {
        super(error, e);
    }
}
