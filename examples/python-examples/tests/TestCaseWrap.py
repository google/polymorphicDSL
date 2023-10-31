
from jpype import *
from com.pdsl.testcases import TestCase
from com.pdsl.gherkin.executors import GherkinTestExecutor
from com.pdsl.python import GitHubParserListener

class TestCaseWrap():
    def __init__(self, testCase: TestCase, executor: GherkinTestExecutor, parserListener: GitHubParserListener, testCaseTitle: str):
        self.testCase = testCase
        self.executor = executor
        self.parserListener = parserListener
        self.testCaseTitle = testCaseTitle