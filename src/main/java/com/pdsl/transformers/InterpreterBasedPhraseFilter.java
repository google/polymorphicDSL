package com.pdsl.transformers;

import static com.pdsl.logging.AnsiTerminalColorHelper.*;

import com.google.common.base.Preconditions;
import com.pdsl.logging.AnsiTerminalColorHelper;
import com.pdsl.specifications.DefaultTestSpecification;
import com.pdsl.specifications.PolymorphicDslTransformationException;
import org.antlr.v4.Tool;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.jar.JarOutputStream;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;

public class InterpreterBasedPhraseFilter implements PolymorphicDslPhraseFilter {

    private final Logger logger = LoggerFactory.getLogger(LineDelimitedTestSpecificationFactory.class);

    private static final String PARSER_SUFFIX = "MetaParser";
    private final ParseTreeListener grammarListener;
    private final Class<?> grammarParser;
    private final Optional<Class<?>> subgrammarParser;
    private final Optional<ParseTreeListener> subgrammarLister;
    private final Class<?> grammarLexer;
    private final Optional<Class<?>> subgrammarLexer;
    private static final String allRulesMethodName = "polymorphicDslAllRules";

    @Override
    public Optional<List<ParseTree>> validateAndFilterPhrases(List<InputStream> testInput, String testId) {
        // Make copies of the input stream so we can read them more than once
        List<ByteArrayOutputStream> reusableCopies = new ArrayList<>(testInput.size());
        for (InputStream inputStream : testInput) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                inputStream.transferTo(baos);
            } catch (IOException e) {

            }
            reusableCopies.add(baos);
        }
        // Do an initial run with the grammar parser to make sure all phrases are in the overall grammar:
         for (ByteArrayOutputStream baos : reusableCopies) {
            Optional<Parser> parser = TestSpecificationHelper.parserOf(new ByteArrayInputStream(baos.toByteArray()), testId,
                    TestSpecificationHelper.ErrorListenerStrategy.GRAMMAR, grammarParser, grammarLexer);
            if (parser.isEmpty()) {
                throw new PolymorphicDslTransformationException("A phrase was found that does not belong in the grammar!\n\tPhrase: " + testInput);
            }
        }
        // By this point we have verified that all phrases are valid in our grammar.
        // Now we can filter out phrases that do not belong in the current context
        List<ParseTree> parseTrees = new ArrayList<>(testInput.size());
        int phrasesFilteredOut = 0;
        for (ByteArrayOutputStream baos : reusableCopies) {
            if (subgrammarParser.isEmpty()) {
                break;
            }
            Optional<Parser> parser = TestSpecificationHelper.parserOf(new ByteArrayInputStream(baos.toByteArray()), testId,
                    TestSpecificationHelper.ErrorListenerStrategy.SUBGRAMMAR,
                    subgrammarParser.get(),
                    subgrammarLexer.isPresent() ? subgrammarLexer.get() : grammarLexer);
            if (parser.isPresent()) {
                parser.get().setBuildParseTree(true);
                try {
                    Method activePhrasesRule = subgrammarParser.get().getMethod(allRulesMethodName,  (Class<?>[]) null);
                    parseTrees.add((ParseTree) activePhrasesRule.invoke(parser.get(), null));
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new PolymorphicDslTransformationException(
                            "Unable to create parse tree using the " + allRulesMethodName + " rule!", e);
                }
            } else {
                phrasesFilteredOut++;
            }
        }
        if (parseTrees.isEmpty()) { // Let the user know we couldn't parse
            String errorType = phrasesFilteredOut == testInput.size() ? "All phrases were filtered out of a test!" : "A test entirely failed to be parsed!";
            StringBuilder errorMessage = new StringBuilder(AnsiTerminalColorHelper.BRIGHT_YELLOW + errorType + RESET);
            errorMessage.append("\n\t" + BOLD + "Path: " + RESET + testId + "\n\t" +
                    BOLD + "Parser Context: " + RESET + grammarParser.getName() + "\n\t" +
                    BOLD + "Strategy: " + RESET + TestSpecificationHelper.ErrorListenerStrategy.GRAMMAR.name());
            logger.warn(errorMessage.toString());
            return Optional.empty();
        }
        return Optional.of(parseTrees);

    }

    private InterpreterBasedPhraseFilter(Builder builder) throws IOException {
        GeneratedCodeContainer grammarContainer = generatedPdslAllRulesListener(builder.grammarParentDirectory, builder.grammarName, builder.grammarPackagePath,
                builder.grammarLexer.isPresent() ? builder.grammarLexer.get() : builder.grammarName + "Lexer",
                builder.grammarLibrary.isPresent() ? builder.grammarLibrary.get() : builder.grammarParentDirectory);
        String parserName = grammarContainer.getParserClass().getName();
        Preconditions.checkArgument(parserName.contains(PARSER_SUFFIX), String.format("The parser %s did not end with %s!", parserName, PARSER_SUFFIX));
        grammarLexer = grammarContainer.getLexerClass();
        grammarParser = grammarContainer.getParserClass();
        grammarListener = grammarContainer.getParseTreeListener();
        if (builder.subgrammarName.isPresent()) {
            Path parentDirectory = builder.subgrammarParentDirectory.isPresent() ? builder.subgrammarParentDirectory.get() : builder.grammarParentDirectory;
            GeneratedCodeContainer subgrammarContainer =
                 generatedPdslAllRulesListener(
                         parentDirectory,
                    builder.subgrammarName.get(),
                    builder.subgrammarPackagepath.isPresent() ? builder.subgrammarPackagepath.get() : builder.grammarPackagePath,
                    builder.subgrammarLexer.isPresent() ? builder.subgrammarLexer.get() : builder.subgrammarName.get() + "Lexer",
                         builder.subgrammarLibrary.isPresent() ? builder.subgrammarLibrary.get() : parentDirectory
            );
            subgrammarLexer = Optional.of(subgrammarContainer.getLexerClass());
            subgrammarParser = Optional.of(subgrammarContainer.getParserClass());
            subgrammarLister = Optional.of(subgrammarContainer.getParseTreeListener());
        } else {
            subgrammarLister = Optional.empty();
            subgrammarLexer = Optional.empty();
            subgrammarParser = Optional.empty();
        }
    }

    private GeneratedCodeContainer generatedPdslAllRulesListener(Path grammarParentDirectory, String grammarName,
                                                            String packagePath, String lexer, Path library) throws IOException{
        Path parserPath = grammarParentDirectory.resolve(grammarName + "Parser.g4");
        List<String> ruleNames = getRuleNamesFromInterpreterFile(grammarParentDirectory, grammarName);
        // Write a new antlr parser that will match any rule names
        createAntlrSourceToMatchRules(ruleNames, grammarParentDirectory, grammarName, lexer, packagePath);
        generateParser(grammarParentDirectory, grammarName, packagePath, library);
        return loadGeneratedSources(grammarParentDirectory, grammarName, packagePath, lexer);
    }

    private void createAntlrSourceToMatchRules(List<String> ruleNames, Path grammarParentDirectory, String grammarName,
                                               String lexer, String packagePath) throws IOException {
        StringBuilder builder = new StringBuilder();
        String parserOfParserName = grammarParentDirectory.getFileName() + "MetaParser";
        builder.append("parser grammar " + grammarName + PARSER_SUFFIX + ";\n");
        builder.append("options {tokenVocab=" + lexer + ";}\n");
        builder.append("import " + grammarName + "Parser;\n");
        builder.append(allRulesMethodName + " : (\n");
        builder.append("\t" + ruleNames.get(0));
        ruleNames.subList(1, ruleNames.size()).stream()
                .forEach(rule -> builder.append( " |\n\t" + rule));
        builder.append("\t)+\n");
        builder.append("\t;\n");
        Files.writeString(grammarParentDirectory.resolve(grammarName + PARSER_SUFFIX + ".g4"), builder.toString(),
                StandardOpenOption.CREATE);
    }

    private enum FileType {
        JAVA,
        PARSER,
        CLASS;
    }


    private static class SourceFileFilter implements FileFilter {
        private final Pattern p;
        private final Pattern parserFiles;
        private FileType strategy;

        public void setStrategy(FileType strategy) {
            this.strategy = strategy;
        }

        public SourceFileFilter(FileType strategy, String grammarName) {
            this.p = Pattern.compile(grammarName + PARSER_SUFFIX +
                    (strategy.equals(FileType.JAVA) ? ".*\\.java" : ".*\\.class"));
            this.parserFiles = Pattern.compile(grammarName + PARSER_SUFFIX +
                    "(.*\\.interp | .*\\.tokens)");
            this.strategy = strategy;
        }

        @Override
        public boolean accept(File file) {
            if (strategy.equals(FileType.PARSER)) {
                return parserFiles.matcher(file.getName()).matches();
            } else {
                return p.matcher(file.getName()).matches();
            }
        }
    }

    /**
     * Reads the interpreter file to find all grammar rules and generates new ANTLR4 source code in a temporary directory
     * @param packagePath The package the source files should be contained in
     * @param grammarParentDirectory The location of the .g4 grammar the source is being generated from
     * @param grammarName The name of the grammar that is having an allRules rule created for
     * @param fileType The type of files being located
     * @return generated source files in the system temporary directory
     */
    private File[] getNewSourceFiles(File finalGeneratedCodeDirectory, String packagePath, Path grammarParentDirectory, String grammarName) {
        try {
            // Create a File object on the root of the directory containing the class file
            File sourceDirectory = new File(grammarParentDirectory.toString());
            if (!sourceDirectory.isDirectory()) {
                throw new IllegalArgumentException(sourceDirectory + " is not a directory.");
            }
            SourceFileFilter sourceFileFilter = new SourceFileFilter(FileType.JAVA, grammarName);
            File[] javaSourceFiles = sourceDirectory.listFiles(sourceFileFilter);
            logger.info("Writing generated code to " + finalGeneratedCodeDirectory.getPath());
            // Copy the files over to the temporary directory
            for (File sourceFile : javaSourceFiles) {
                logger.debug("Moving generated source file: " + sourceFile.getName());
                Files.move(sourceFile.toPath(), finalGeneratedCodeDirectory.toPath().resolve(sourceFile.getName()),
                        StandardCopyOption.REPLACE_EXISTING);
            }
            // Return the copies
            return finalGeneratedCodeDirectory.listFiles(sourceFileFilter);
        } catch (IOException e) {
            // Cleanup if possible
            finalGeneratedCodeDirectory.delete();
            throw new PolymorphicDslFileException("Error when generating new source files for grammar!", e);
        }
    }

    private File[] getCompiledClasses(File finalGeneratedCodeDirectory, String grammarName) {
        SourceFileFilter sourceFileFilter = new SourceFileFilter(FileType.CLASS, grammarName);
        File[] javaSourceFiles = finalGeneratedCodeDirectory.listFiles(sourceFileFilter);
        assert(javaSourceFiles != null && javaSourceFiles.length > 0) : "The compiled classes were not found!";
        logger.info("Writing generated code to " + finalGeneratedCodeDirectory.getPath());
        return javaSourceFiles;

    }

    private GeneratedCodeContainer loadGeneratedSources(Path grammarParentDirectory, String grammarName,
                                                        String packagePath, String lexerName) {
        boolean parserSuccessfullyProcessed = false;
        try {
            Path tmpDirsLocation = Path.of(String.format("%s/pdsltemp/%s/%s/", System.getProperty("java.io.tmpdir"),
                    UUID.randomUUID(), packagePath.replace(".", "/")));
            File finalGeneratedCodeLocation = new File(tmpDirsLocation.toString());
            finalGeneratedCodeLocation.mkdirs();
            // Write generated code
            File[] sourceFiles = getNewSourceFiles(finalGeneratedCodeLocation, packagePath, grammarParentDirectory,
                    grammarName);
            // Compile the generated code
            compileGeneratedSources(packagePath, sourceFiles);
            File[] compiledClasses = getCompiledClasses(finalGeneratedCodeLocation, grammarName);
            Path sourcePath = putCompiledClassesInJarFile(grammarName, packagePath, compiledClasses);
            logger.debug("Wrote generated grammars to " + sourcePath);
            // Load the generated classes
            URL[] urls = new URL[] { sourcePath.toUri().toURL()};
            ClassLoader cl = new URLClassLoader(urls);
            // Get the most important classes needed for filtering
            Class<?> parseTreeListenerClass = cl.loadClass(packagePath + "." + grammarName + "MetaParserBaseListener");
            Class<?> parser = cl.loadClass(packagePath + "." + grammarName + PARSER_SUFFIX);
            parserSuccessfullyProcessed = true;
            Class<?> lexer = cl.loadClass(packagePath + "." + lexerName);
            ParseTreeListener listener = (ParseTreeListener) parseTreeListenerClass.getDeclaredConstructor().newInstance();
            return new GeneratedCodeContainer(parser, lexer, listener);

        } catch (MalformedURLException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException
                | InstantiationException | InvocationTargetException e) {
            throw new PolymorphicDslFileException(this.getClass().getName() +
                    String.format(" was not able to load generated classes from created meta grammar!\n" +
                            "\tThe failure was loading the generated " + (parserSuccessfullyProcessed ? "lexer" : "parser") + "\n" +
                            "\tParser: %s\n\tLexer: %s", grammarName, lexerName), e);
        }
    }

    /**
     * Copies the source files to a temporary directory and compiles them
     * @param generatedFiles An array of .java source files
     */
    private void compileGeneratedSources(String packagePath, File[] generatedFiles) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> ds = new
                DiagnosticCollector<>();
        try(StandardJavaFileManager mgr =
                     compiler.getStandardFileManager( ds, null, null ) ) {
                Iterable<? extends JavaFileObject> sources =
                        mgr.getJavaFileObjectsFromFiles(Arrays.
                                asList(generatedFiles));
                JavaCompiler.CompilationTask task =
                        compiler.getTask(null, mgr, ds, null,
                                null, sources);
                task.call();

                for (Diagnostic<? extends JavaFileObject>
                        d : ds.getDiagnostics()) {
                    System.out.format("Line: %d, %s in %s",
                            d.getLineNumber(), d.getMessage(null),
                            d.getSource().getName());
                }
        } catch (IOException e) {
            throw new PolymorphicDslFileException("Could not compile generated source code for meta parser!", e);
        }
    }

    /**
     * Unfortunately a Java classloader will not find individual class files added at runtime if they are created on a
     * pre-existing classpath
     */
    private Path putCompiledClassesInJarFile(String grammarName, String packagePath, File[] compiledClasses) {
        String tmpDirsLocation = /*System.getProperty("java.io.tmpdir") + */"pdsltemp/" + packagePath.replace(".", "/") + "/";
        try {
            File tempJar = File.createTempFile(tmpDirsLocation + grammarName + "MetaGrammar", ".jar");
            FileOutputStream fout = new FileOutputStream(tempJar);
            String packageDirectory = packagePath.replace(".", "/") + "/";
            JarOutputStream jarOut = new JarOutputStream(fout);
            jarOut.putNextEntry(new ZipEntry(packageDirectory));
            for (File compiledClass : compiledClasses) {
                jarOut.putNextEntry(new ZipEntry(packageDirectory + compiledClass.getName()));
                jarOut.write(Files.readAllBytes(compiledClass.toPath()));
                jarOut.closeEntry();
            }
            jarOut.close();
            fout.close();
            return tempJar.toPath();
        } catch (IOException e) {
            throw new PolymorphicDslFileException("Could not put generated code into a jar in the temp directory!", e);
        }
    }

    private static class GeneratedCodeContainer {
        private Class<?> parserClass;
        private Class<?> lexerClass;
        private ParseTreeListener parserTreeListener;

        public Class<?> getParserClass() {
            return parserClass;
        }

        public Class<?> getLexerClass() {
            return lexerClass;
        }

        public ParseTreeListener getParseTreeListener() {
            return parserTreeListener;
        }

        public GeneratedCodeContainer(Class<?> parserClass, Class<?> lexerClass, ParseTreeListener parserTreeListener) {
            this.parserClass = parserClass;
            this.lexerClass = lexerClass;
            this.parserTreeListener = parserTreeListener;
        }
    }

    private void generateParser(Path grammarParentDirectory, String grammarName, String packagePath, Path library) {
        Tool tool = new Tool(new String[]{"-package", packagePath, "-lib", library.toString(), "-o",
                grammarParentDirectory.toString(), grammarParentDirectory.resolve(grammarName).toString()
                + PARSER_SUFFIX + ".g4"});
        tool.processGrammarsOnCommandLine();
    }

    private List<String> getRuleNamesFromInterpreterFile(Path grammarParentDirectory, String grammarName) throws IOException {
        Path interpreter = grammarParentDirectory.resolve(grammarName + "Parser.interp");
        List<String> interpreterRules = Files.readAllLines(interpreter);
        boolean searchingForParserRules = true;
        List<String> ruleNames = new ArrayList<>(16);
        for (String line : interpreterRules) {
            if (searchingForParserRules) {
                if (line.startsWith("rule names:")) {
                    searchingForParserRules = false; // Found the rules!
                }
            } else { // We found the rules and are getting all their names
                if (line.isBlank()) { // We processed all rule names
                    break;
                } else if (line.startsWith("polymorphicDslAllRules")) {
                    logger.warn("The grammar already contained a rule named polymorphicDslAllRules! Are you parsing a " +
                            "meta-grammar by mistake?");
                    continue;
                } else {
                    if (Character.isUpperCase(line.charAt(0))) {
                        continue; // Lexer rule, not parser rule
                    } else {
                        ruleNames.add(line);
                    }
                }
            }
        }
        Preconditions.checkArgument(!ruleNames.isEmpty(), ".interp file had no parser rules!");
        return ruleNames;
    }

    public static class Builder {
        private Path grammarParentDirectory;
        private Optional<Path> subgrammarParentDirectory = Optional.empty();
        private String grammarName;
        private Optional<String> subgrammarName = Optional.empty();
        private Optional<String> grammarLexer = Optional.empty();
        private Optional<String> subgrammarLexer = Optional.empty();
        private String grammarPackagePath;
        private Optional<String> subgrammarPackagepath = Optional.empty();
        private Optional<Path> grammarLibrary = Optional.empty();
        private Optional<Path> subgrammarLibrary = Optional.empty();

        public Builder(Path grammarParentDirectory, String grammarName, String grammarPackagePath) {
            Preconditions.checkNotNull(grammarParentDirectory, "Grammar parent directory cannot be null!");
            Preconditions.checkNotNull(grammarName, "A grammar must be specified");
            Preconditions.checkNotNull(grammarPackagePath, "A classpath is needed for the grammar(s)");
            this.grammarParentDirectory = grammarParentDirectory;
            this.grammarName = grammarName;
            this.grammarPackagePath = grammarPackagePath;
        }

        public Builder withSubgrammar(String subgrammarName) {
            this.subgrammarName = Optional.of(subgrammarName);
            return this;
        }

        public Builder withGrammarLexer(String grammarLexer) {
            Preconditions.checkArgument(grammarLexer.endsWith("Lexer"), "the lexer file name must end with the word 'Lexer', e.g, 'SomeLexer'");
            this.grammarLexer = Optional.of(grammarLexer);
            return this;
        }

        public Builder withSubgrammarLexer(String subgrammarLexer) {
            Preconditions.checkArgument(subgrammarLexer.endsWith("Lexer"), "All lexer names need to end with with the word 'Lexer' e.g, 'SomeLexer'");
            this.subgrammarLexer = Optional.of(subgrammarLexer);
            return this;
        }

        public Builder withSubgrammarPackagePath(String subgrammarPackagePath) {
            this.subgrammarPackagepath = Optional.of(subgrammarPackagePath);
            return this;
        }

        public Builder withSubgrammarParentDirectory(Path subgrammarParentDirectory) {
            this.subgrammarParentDirectory = Optional.of(subgrammarParentDirectory);
            return this;
        }

        public Builder withGrammarLibrary(Path grammarLibrary) {
            this.grammarLibrary = Optional.of(grammarLibrary);
            return this;
        }

        public Builder withSubgrammarLibrary(Path subgrammarLibrary) {
            this.subgrammarLibrary = Optional.of(subgrammarLibrary);
            return this;
        }

        public InterpreterBasedPhraseFilter build() throws IOException {
            return new InterpreterBasedPhraseFilter(this);
        }
    }

}
