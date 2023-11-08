
import allure

from jpype import *
from selenium import webdriver
from selenium.webdriver.common.by import By

# Local JAR (pdsl-g4-artifacts.jar) imports
from com.pdsl.python import GitHubParserListener, GitHubParser
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

@JImplements('com.pdsl.python.GitHubParserListener')
class GitHubParserBaseListenerImpl(object):
    test_profile = ''
    chrome_driver = webdriver.Chrome()

    @JOverride
    def visitTerminal(self, terminalNode):
        pass

    @allure.step
    @JOverride
    def visitErrorNode(self, errorNode):
        print(errorNode.toString())
        print(errorNode.getText())
        raise Exception('There was an error in the grammar! Check the G4 files for the issue!')

        pass

    @JOverride
    def enterEveryRule(self, parserRuleContext):
        pass

    @JOverride
    def exitEveryRule(self, parserRuleContext):
        pass

    @allure.step
    @JOverride
    def enterGivenTestProfile(self, ctx: GitHubParser.GivenTestProfileContext):
        self.test_profile = ctx.parameter().BODY().getText()
        allure.attach(self.test_profile, 'Test profile', allure.attachment_type.TEXT)

        assert self.test_profile is not None

        pass

    @JOverride
    def exitGivenTestProfile(self, ctx: GitHubParser.GivenTestProfileContext):
        pass

    @allure.step
    @JOverride
    def enterUserProfileValidation(self, ctx: GitHubParser.UserProfileValidationContext):
        self.chrome_driver.maximize_window()
        self.chrome_driver.get('https://github.com/{}'.format(self.test_profile))

        allure.attach(self.chrome_driver.get_screenshot_as_png(), name='GitHub Profile. {}'.format(self.test_profile),
                       attachment_type=allure.attachment_type.PNG)

        test_profile_actual = self.chrome_driver.find_element(By.CSS_SELECTOR, 'span[itemprop="additionalName"]').text
        assert test_profile_actual == self.test_profile

        pass

    @JOverride
    def exitUserProfileValidation(self, ctx: GitHubParser.UserProfileValidationContext):
        pass

    @allure.step
    @JOverride
    def enterUserProfileHasLogin(self, ctx: GitHubParser.UserProfileHasLoginContext):
        login = ctx.parameter().BODY().getText()
        element = WebDriverWait(self.chrome_driver, 20).until(EC.title_contains(login))
        assert element is not None

        pass

    @JOverride
    def exitUserProfileHasLogin(self, ctx: GitHubParser.UserProfileHasLoginContext):
        pass

    @allure.step
    @JOverride
    def enterUserProfileHasName(self, ctx: GitHubParser.UserProfileHasNameContext):
        name = ctx.parameter().BODY().getText()
        element = WebDriverWait(self.chrome_driver, 20).until(EC.title_contains(name))
        assert element is not None

        pass

    @JOverride
    def exitUserProfileHasName(self, ctx: GitHubParser.UserProfileHasNameContext):
        pass

    @allure.step
    @JOverride
    def enterUserProfileHasId(self, ctx: GitHubParser.UserProfileHasIdContext):
        id_ = ctx.parameter().BODY().getText()
        element = WebDriverWait(self.chrome_driver, 20).until(EC.presence_of_element_located((By.CSS_SELECTOR, 'img[src*="{}"][class="avatar avatar-user width-full border color-bg-default"]'.format(id_))))
        assert element is not None

        pass

    @JOverride
    def exitUserProfileHasId(self, ctx: GitHubParser.UserProfileHasIdContext):
        pass

    @allure.step
    @JOverride
    def enterUserProfileHasType(self, ctx: GitHubParser.UserProfileHasTypeContext):

        pass

    @JOverride
    def exitUserProfileHasType(self, ctx: GitHubParser.UserProfileHasTypeContext):
        pass

    @JOverride
    def enterPolymorphicDslAllRules(self, ctx: GitHubParser.PolymorphicDslAllRulesContext):
        pass

    @JOverride
    def exitPolymorphicDslAllRules(self, ctx: GitHubParser.PolymorphicDslAllRulesContext):
        pass

    @JOverride
    def enterPolymorphicDslSyntaxRule(self, ctx: GitHubParser.PolymorphicDslSyntaxRuleContext):
        pass

    @JOverride
    def exitPolymorphicDslSyntaxRule(self, ctx: GitHubParser.PolymorphicDslSyntaxRuleContext):
        pass

    @JOverride
    def enterParameter(self, ctx: GitHubParser.ParameterContext):
        pass

    @JOverride
    def exitParameter(self, ctx: GitHubParser.ParameterContext):
        pass
