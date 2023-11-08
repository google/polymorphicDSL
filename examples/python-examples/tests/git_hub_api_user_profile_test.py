import pytest
import allure
import os

import jpype
import jpype.imports
from jpype import *


# JPYPE -----------------------------
print('\nCurrent directory: {}.'.format(os.getcwd()))
jars = []
jars.append('{}/resources/jar/pdsl-1.6.3-SNAPSHOT.jar'.format(os.getcwd()))
jars.append('{}/resources/jar/pdsl-g4-artifacts.jar'.format(os.getcwd()))

if not jpype.isJVMStarted():
    jpype.startJVM(jpype.getDefaultJVMPath(), '-ea', '-Djava.class.path={}'.format(os.pathsep.join(jars)), convertStrings = True)


# Local imports
from tests.git_hub_api_parser_base_listener_impl import GitHubParserBaseListenerImpl
from tests.TestCaseWrap import TestCaseWrap


print('JVM. Is running [{}]; version [{}].'.format(jpype.isJVMStarted(), jpype.getJVMVersion()))

# PDSL JAR imports
from com.pdsl.gherkin import PickleJarFactory, PdslGherkinInterpreterImpl, PdslGherkinListenerImpl, DefaultGherkinTestSpecificationFactory
from com.pdsl.transformers import DefaultPolymorphicDslPhraseFilter
from com.pdsl.testcases import PreorderTestCaseFactory, TestCase
from com.pdsl.gherkin.executors import GherkinTestExecutor




@allure.step
def pdsl(lexer, parser, resource):
    """
    Should to have all logic related to the collect TC's.
    Collect and return all test cases.
    Convert the test specifications into test cases:
    Collection<TestCase> testCases = testCaseFactory.processTestSpecification(testSpecifications);

    :param lexer: str of full qualified class name, example: 'com.pdsl.python.GitHubLexer'
    :param parser:  str of full qualified class name, example: 'com.pdsl.python.GitHubParser'
    :param resource: str - absolutely path for feature file, example:  'file:///usr/polymorphicDSL/examples/python-examples/tests/resources/features/GitHubUser.feature'
    :return:

        Data Provider output instance/object:
            - Collection<TestCase> - with single TC;
            - com.pdsl.gherkin.executors.GherkinTestExecutor - to run the single test in PyTest test;
            - ParserBaseListener implementation.
    """

    def wrap(data_provider_func):
        # Arrange
        pickleJarFactory = PickleJarFactory(PdslGherkinInterpreterImpl(), PdslGherkinListenerImpl(), java.nio.charset.StandardCharsets.UTF_8)
        phraseFilter = DefaultPolymorphicDslPhraseFilter(jpype.JClass(parser), jpype.JClass(lexer))
        testCaseFactory = PreorderTestCaseFactory()
        provider = DefaultGherkinTestSpecificationFactory.Builder(phraseFilter).withPickleJarFactory(pickleJarFactory).build()


        resourceRoot = '{}/resources/features'.format(os.getcwd())
        includesResource = 'file://{}/{}'.format(resourceRoot, resource)

        featureUrl = java.net.URL(includesResource)

        dslFiles = java.util.HashSet()
        dslFiles.add(featureUrl.toURI())
        print('URL collection size: {}.'.format(dslFiles.size()))

        # Act
        testSpecifications = provider.getTestSpecifications(dslFiles)

        if not testSpecifications.isPresent():
            raise Exception('Test resources [{}] could not be converted to a Test Specification'.format(includesResource))

        # Convert the test `testSpecifications` into test cases
        # Collection <com.pdsl.testcases.TestCase>
        testCases = testCaseFactory.processTestSpecification(testSpecifications.get())
        print('Total TC\'s collected [{}].'.format(testCases.size()))

        executor = GherkinTestExecutor(phraseFilter)
        parserBaseListener = GitHubParserBaseListenerImpl()

        # re-pack the `Collection <com.pdsl.testcases.TestCase>` to list of our wrapper
        testCasesWrap = []

        for testCase in testCases:
            testCasesWrap.append(TestCaseWrap(testCase, executor, parserBaseListener, testCase.getTestTitle()))

        return data_provider_func(testCasesWrap)
    return wrap


@pdsl(lexer='com.pdsl.python.GitHubLexer',
      parser='com.pdsl.python.GitHubParser',
      resource='GitHubUser.feature')
def data_provider(data):
    return data


# Act
@allure.feature('GitHub')
@allure.story('API')
@allure.severity(allure.severity_level.NORMAL)
@allure.title('{input.testCaseTitle}')
@pytest.mark.parametrize("input", data_provider)
def test_git_hub_user_profile(input):

    results = input.executor.runTests(java.util.List.of(input.testCase), input.parserListener)

    assert results.failingTestTotal() == 0
    assert results.passingTestTotal() > 0