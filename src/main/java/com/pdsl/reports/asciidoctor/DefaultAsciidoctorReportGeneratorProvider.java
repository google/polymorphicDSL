package com.pdsl.reports.asciidoctor;

import com.pdsl.reports.MetadataTestRunResults;
import com.pdsl.reports.TraceableReportGenerator;
import org.asciidoctor.AttributesBuilder;
import org.asciidoctor.OptionsBuilder;
import org.asciidoctor.Placement;
import org.asciidoctor.SafeMode;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

/**
 * The standard report generator for producing Ascii-doctor based test reports.
 *
 * This is useful for better making sense of aggregated TestRunResults.
 */
public class DefaultAsciidoctorReportGeneratorProvider implements TraceableReportGenerator {

    private static final OptionsBuilder OPTIONS = OptionsBuilder.options().attributes(AttributesBuilder.attributes()
            .docType("html")
            .tableOfContents(true)
            .tableOfContents(Placement.LEFT)
            .sourceHighlighter("rouge")
    ).safe(SafeMode.UNSAFE);

    private final AsciidoctorReportGenerator asciidoctorReportGenerator;

    /**
     * Creates an Ascii-doctor based report generator.
     */
    public DefaultAsciidoctorReportGeneratorProvider() {
        String tempDir = System.getProperty("java.io.tmpdir");
        Path path = Path.of(String.format("%s/%s.adoc", tempDir, UUID.randomUUID()));
        asciidoctorReportGenerator = new AsciidoctorReportGenerator(path, "System Under Test", OPTIONS.get());
    }

    @Override
    public URL generateReport(Map<String, Map<String, Collection<MetadataTestRunResults>>> testRunResults) throws IOException {
        return asciidoctorReportGenerator.generateReport(testRunResults);
    }
}
