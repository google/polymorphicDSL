// Generated from ./tests/resources/antlr4/GitHubParser.g4 by ANTLR 4.9.1
package com.pdsl.python;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GitHubParser}.
 */
public interface GitHubParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GitHubParser#givenTestProfile}.
	 * @param ctx the parse tree
	 */
	void enterGivenTestProfile(GitHubParser.GivenTestProfileContext ctx);
	/**
	 * Exit a parse tree produced by {@link GitHubParser#givenTestProfile}.
	 * @param ctx the parse tree
	 */
	void exitGivenTestProfile(GitHubParser.GivenTestProfileContext ctx);
	/**
	 * Enter a parse tree produced by {@link GitHubParser#userProfileValidation}.
	 * @param ctx the parse tree
	 */
	void enterUserProfileValidation(GitHubParser.UserProfileValidationContext ctx);
	/**
	 * Exit a parse tree produced by {@link GitHubParser#userProfileValidation}.
	 * @param ctx the parse tree
	 */
	void exitUserProfileValidation(GitHubParser.UserProfileValidationContext ctx);
	/**
	 * Enter a parse tree produced by {@link GitHubParser#userProfileHasLogin}.
	 * @param ctx the parse tree
	 */
	void enterUserProfileHasLogin(GitHubParser.UserProfileHasLoginContext ctx);
	/**
	 * Exit a parse tree produced by {@link GitHubParser#userProfileHasLogin}.
	 * @param ctx the parse tree
	 */
	void exitUserProfileHasLogin(GitHubParser.UserProfileHasLoginContext ctx);
	/**
	 * Enter a parse tree produced by {@link GitHubParser#userProfileHasName}.
	 * @param ctx the parse tree
	 */
	void enterUserProfileHasName(GitHubParser.UserProfileHasNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link GitHubParser#userProfileHasName}.
	 * @param ctx the parse tree
	 */
	void exitUserProfileHasName(GitHubParser.UserProfileHasNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link GitHubParser#userProfileHasId}.
	 * @param ctx the parse tree
	 */
	void enterUserProfileHasId(GitHubParser.UserProfileHasIdContext ctx);
	/**
	 * Exit a parse tree produced by {@link GitHubParser#userProfileHasId}.
	 * @param ctx the parse tree
	 */
	void exitUserProfileHasId(GitHubParser.UserProfileHasIdContext ctx);
	/**
	 * Enter a parse tree produced by {@link GitHubParser#userProfileHasType}.
	 * @param ctx the parse tree
	 */
	void enterUserProfileHasType(GitHubParser.UserProfileHasTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GitHubParser#userProfileHasType}.
	 * @param ctx the parse tree
	 */
	void exitUserProfileHasType(GitHubParser.UserProfileHasTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GitHubParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(GitHubParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link GitHubParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(GitHubParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link GitHubParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 */
	void enterPolymorphicDslAllRules(GitHubParser.PolymorphicDslAllRulesContext ctx);
	/**
	 * Exit a parse tree produced by {@link GitHubParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 */
	void exitPolymorphicDslAllRules(GitHubParser.PolymorphicDslAllRulesContext ctx);
	/**
	 * Enter a parse tree produced by {@link GitHubParser#polymorphicDslSyntaxRule}.
	 * @param ctx the parse tree
	 */
	void enterPolymorphicDslSyntaxRule(GitHubParser.PolymorphicDslSyntaxRuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link GitHubParser#polymorphicDslSyntaxRule}.
	 * @param ctx the parse tree
	 */
	void exitPolymorphicDslSyntaxRule(GitHubParser.PolymorphicDslSyntaxRuleContext ctx);
}