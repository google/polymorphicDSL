Feature: JUnit Runner

	This document tracks highly specific JUnit specific features. For more general, broadly useful information about the Java Test Runner see "JavaTestRunner.feature"

	Scenario: Ignoring tests
		Tests with an @Ignore annotation will not be executed by the framework


		Given a PDSL test
		But the @PdslTest is marked with @Ignore:
			"""
			@RunWith(PdslJUnit4ConfigurableRunner.class)
			@PdslConfiguration(
				specificationFactoryProvider = SomeSpecificationFactoryProvider.class,
				testCaseFactoryProvider = SomeTestCaseFactoryProvider.class
			)
			public class YourTestClass {


				@Ignore
				@PdslTest(
					includedResources = "resource1",
					parser = SomeParser.class,
					lexer = SomeLexer.class,
					listener = FrameworkSpecificationListenerProvider.class
				)
				public void someTest(){}
			}
			"""

		When the test runner executes
		Then the test is ignored

	Scenario: Compatibility with JUnit @Test
		PDSL can mix @PdslTest test methods with regular JUnit @Test methods. 
		Technically PDSL could be used instead of JUnit4 for testing classes that
		ONLY have @Test methods, although it is unclear why someone would want to do this.

		Given a PDSL test
		But a @Test is specified:
			"""
			@RunWith(PdslJUnit4ConfigurableRunner.class)
			@PdslConfiguration(
				specificationFactoryProvider = SomeSpecificationFactoryProvider.class,
				testCaseFactoryProvider = SomeTestCaseFactoryProvider.class
			)
			public class YourTestClass {

				@Test(timeout=400)
				public void someTest(){}
			}
			"""
		When the test runner executes
		Then the test executes

	Scenario: Hooks
		PDSL supports standard JUnit4 hooks, such as @Before, @After, etc.

		Given a PDSL test
		And the PDSL test has hooks:
		"""
			@RunWith(PdslJUnit4ConfigurableRunner.class)
			@PdslConfiguration(
				specificationFactoryProvider = SomeSpecificationFactoryProvider.class,
				testCaseFactoryProvider = SomeTestCaseFactoryProvider.class
			)
			public class YourTestClass {

				@BeforeClass
				public static void beforeAll(){/*...*/}
				@Before
				public static void before(){/*...*/}
				@AfterClass
				public static void afterAll(){/*...*/}
				@After
				public static void after(){/*...*/}

				@Rule
				public ExpectedException thrown = ExpectedException.none();	

				public void someTest(){}
			}
		"""
		When the test runner executes
		Then all tests pass
		And all hooks triggered
		And all rules executed as necessary
