// Generated from ./tests/resources/antlr4/GitHubParser.g4 by ANTLR 4.9.1
package com.pdsl.python;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GitHubParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GitHubParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GitHubParser#givenTestProfile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGivenTestProfile(GitHubParser.GivenTestProfileContext ctx);
	/**
	 * Visit a parse tree produced by {@link GitHubParser#userProfileValidation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserProfileValidation(GitHubParser.UserProfileValidationContext ctx);
	/**
	 * Visit a parse tree produced by {@link GitHubParser#userProfileHasLogin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserProfileHasLogin(GitHubParser.UserProfileHasLoginContext ctx);
	/**
	 * Visit a parse tree produced by {@link GitHubParser#userProfileHasName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserProfileHasName(GitHubParser.UserProfileHasNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link GitHubParser#userProfileHasId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserProfileHasId(GitHubParser.UserProfileHasIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link GitHubParser#userProfileHasType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserProfileHasType(GitHubParser.UserProfileHasTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GitHubParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(GitHubParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link GitHubParser#polymorphicDslAllRules}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPolymorphicDslAllRules(GitHubParser.PolymorphicDslAllRulesContext ctx);
	/**
	 * Visit a parse tree produced by {@link GitHubParser#polymorphicDslSyntaxRule}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPolymorphicDslSyntaxRule(GitHubParser.PolymorphicDslSyntaxRuleContext ctx);
}