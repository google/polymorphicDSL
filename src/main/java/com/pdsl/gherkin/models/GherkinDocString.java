package com.pdsl.gherkin.models;

import java.util.Optional;

public class GherkinDocString {

    private final GherkinString rawString;

    public GherkinDocString(String rawString) {
        this.rawString = new GherkinString(rawString);
    }

    /**
     * Returns the docstring as matched by the lexer, including leading/trailing whitespace and the triple-quote block.
     *
     * @return GherkinString the full docstring
     */
    public GherkinString getGherkinString() {
        return rawString;
    }

    /**
     * Finds the content type from the text immediately following the first quote block.
     * <p>
     * Docstrings can have a "type" (e.g, xml, json, etc) which provide meta information about the content
     * This information exists on the first line of the docdocstring by convention in the format:
     * """<content type>
     * ....
     * ....
     * """"
     *
     * @return optional containing the content type if present, else an empty optional if the first line is whitespace
     */
    public Optional<String> getContentType() {
        String header = rawString.getRawString().split("\n", 2)[0] // Get the first line
                .replace("\"\"\"", "").trim(); // Remove the leading triple quotes and all whitespace
        return header.isEmpty() ? Optional.empty() : Optional.of(header);
    }

    /**
     * Gets rid of the triple quote and leading/trailing whitespace from the original docstring the lexer matched and
     * returns the result.
     *
     * @return Optional of the content of the doctring if not purely whitepsace else empty
     */
    public Optional<GherkinString> getTextBody() {
        String[] textBody = rawString.getRawString().split("\n", 2); // Get everything after the content type
        if (textBody.length < 2) {
            return Optional.empty();
        } else {
            String body = textBody[1].replace("\"\"\"", "").strip();
            return body.isEmpty() ? Optional.empty() : Optional.of(new GherkinString(body));
        }
    }
}
