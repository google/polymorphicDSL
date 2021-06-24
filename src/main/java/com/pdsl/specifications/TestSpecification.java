package com.pdsl.specifications;

import org.antlr.v4.runtime.tree.ParseTree;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * A subject under test that may or may not contain more detailed test items which is executed by a
 * {@code PolymorphicDslTestExecutor}.
 *
 * This is intended to be used for both high-level and abstract use cases such as a Test Plan or a very low level test case
 *
 * Because a TestItem can have an arbitrary number of child TestItems the user can specify however many degrees of granularity
 * needed to organize the test phrases that will be executed
 *
 * One practical benefit of this is that shared metadata between TestItems can be logged a single time so that the
 * {@code PolymorphicDslTestExecutor} does not spam information while running
 */
public interface TestSpecification {
    /**
     * Returns an information common to this TestItem and all child TestItems (if any).
     *
     * @return Optional containing an InputStream of shared test information or an empty optional if there is none
     */
    Optional<ByteArrayOutputStream> getMetaData();

    /**
     * Returns a collection of more detailed child TestItems.
     *
     * In the event this TestItem has no childTestItems then {@code getPhrases()} should return an optional with phrases that are present
     * @return An optional containing a collection of child TestItems if this TestItem is high level or empty if not
     */
    Optional<List<? extends TestSpecification>> nestedTestSpecifications();

    /**
     *  Returns a unique identifier associated with this TestItem.
     *  It is up to the implementer to decide if it will be unique or not
     * @return a (possibly unique) string identifier
     */
    String getId();

    /**
     * Returns a list of {@code ParseTree} "phrases" that will trigger code execution when consumed by a
     * {@PolymorphicDslTestExecutor}.
     *
     * If this TestItem has no child test items then this method <i>must</i> return an optional contiaining a list with
     * at least one element present
     * @return an Optional containing "test phrases" if this TestItem is a leaf node, or possibly an empty optional if
     * not
     */
    Optional<Iterator<ParseTree>> getPhraseIterator();
    Optional<List<ParseTree>> getPhrases();
}
