from urllib import response

import allure
import requests
from jpype import *

# Local JAR (pdsl-g4-artifacts.jar) imports
from com.pdsl.python import GitHubParserListener, GitHubParser


@JImplements('com.pdsl.python.GitHubParserListener')
class GitHubParserBaseListenerImpl(object):
    test_profile = ''
    response

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

        url = 'https://api.github.com/users/{}'.format(self.test_profile)
        self.response = requests.get(url)

        allure.attach(str(self.response.json()), 'GET. Response for {}'.format(self.test_profile),
                      allure.attachment_type.JSON)

        assert self.response.status_code == 200
        assert self.response.json()['login'] is not None
        assert self.response.json()['id'] is not None
        assert self.response.json()['type'] is not None
        assert self.response.json()['name'] is not None
        assert self.response.json()['html_url'] is not None

        pass

    @JOverride
    def exitUserProfileValidation(self, ctx: GitHubParser.UserProfileValidationContext):
        pass

    @allure.step
    @JOverride
    def enterUserProfileHasLogin(self, ctx: GitHubParser.UserProfileHasLoginContext):
        login = ctx.parameter().BODY().getText()
        assert self.response.json()['login'] == login

        pass

    @JOverride
    def exitUserProfileHasLogin(self, ctx: GitHubParser.UserProfileHasLoginContext):
        pass

    @allure.step
    @JOverride
    def enterUserProfileHasName(self, ctx: GitHubParser.UserProfileHasNameContext):
        name = ctx.parameter().BODY().getText()
        assert self.response.json()['name'] == name

        pass

    @JOverride
    def exitUserProfileHasName(self, ctx: GitHubParser.UserProfileHasNameContext):
        pass

    @allure.step
    @JOverride
    def enterUserProfileHasId(self, ctx: GitHubParser.UserProfileHasIdContext):
        id_ = ctx.parameter().BODY().getText()
        assert str(self.response.json()['id']) == id_

        pass

    @JOverride
    def exitUserProfileHasId(self, ctx: GitHubParser.UserProfileHasIdContext):
        pass

    @allure.step
    @JOverride
    def enterUserProfileHasType(self, ctx: GitHubParser.UserProfileHasTypeContext):
        type_ = ctx.parameter().BODY().getText()
        assert self.response.json()['type'] == type_

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
