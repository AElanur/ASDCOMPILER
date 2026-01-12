package nl.han.ica.icss.parser;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Stack;


import nl.han.ica.datastructures.HANStack;
import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.parser.builders.BuilderContext;
import nl.han.ica.icss.parser.builders.ExpressionBuilder;
import nl.han.ica.icss.parser.builders.RuleBuilder;
import nl.han.ica.icss.parser.builders.StylesheetBuilder;


public class ASTListener extends ICSSBaseListener {
	private final BuilderContext ctx = new BuilderContext();

	private final StylesheetBuilder styleBuilder = new StylesheetBuilder(this);
	private final RuleBuilder ruleBuilder = new RuleBuilder(this);
	private final ExpressionBuilder exprBuilder = new ExpressionBuilder(this);

	@Override
	public void enterStylesheet(ICSSParser.StylesheetContext ctx) {
		styleBuilder.enterStylesheet();
	}

	@Override public void exitStylesheet(ICSSParser.StylesheetContext ctx) {
		styleBuilder.exitStylesheet(ctx);
	}

	@Override public void enterStyleRule(ICSSParser.StyleRuleContext ctx) {
		ruleBuilder.enterStyleRule(ctx);
	}

	@Override public void exitStyleRule(ICSSParser.StyleRuleContext ctx) {
		ruleBuilder.exitSyleRule(ctx);
	}

	@Override public void enterVariableAssignment(ICSSParser.VariableAssignmentContext ctx) {
		ruleBuilder.enterVariableAssignment(ctx);
	}

	@Override public void exitVariableAssignment(ICSSParser.VariableAssignmentContext ctx) {
		ruleBuilder.exitVariableAssignment(ctx);
	}

	@Override public void enterVariableReference(ICSSParser.VariableReferenceContext ctx) {
		exprBuilder.enterVariableReference(ctx);
	}

	@Override public void enterDecleration(ICSSParser.DeclerationContext ctx) {
		ruleBuilder.enterDeclaration(ctx);
	}

	@Override public void exitDecleration(ICSSParser.DeclerationContext ctx) {
		ruleBuilder.exitDeclaration(ctx);
	}

	@Override public void enterPropertyName(ICSSParser.PropertyNameContext ctx) {
		exprBuilder.enterPropertyName(ctx);
	}

	@Override public void enterBoolLiteral(ICSSParser.BoolLiteralContext ctx) {
		exprBuilder.enterBoolLiteral(ctx);
	}

	@Override public void enterColorLiteral(ICSSParser.ColorLiteralContext ctx) {
		exprBuilder.enterColorLiteral(ctx);
	}

	@Override public void enterPercentageLiteral(ICSSParser.PercentageLiteralContext ctx) {
		exprBuilder.enterPercentageLiteral(ctx);
	}

	@Override public void enterPixelLiteral(ICSSParser.PixelLiteralContext ctx) {
		exprBuilder.enterPixelLiteral(ctx);
	}

	@Override public void enterScalarLiteral(ICSSParser.ScalarLiteralContext ctx) {
		exprBuilder.enterScalarLiteral(ctx);
	}

	@Override public void enterClassSelector(ICSSParser.ClassSelectorContext ctx) {
		ruleBuilder.enterClassSelector(ctx);
	}

	@Override public void exitClassSelector(ICSSParser.ClassSelectorContext ctx) {
		ruleBuilder.enterClassSelector(ctx);
	}

	@Override public void enterIdSelector(ICSSParser.IdSelectorContext ctx) {
		ruleBuilder.enterIdSelector(ctx);
	}

	@Override public void exitIdSelector(ICSSParser.IdSelectorContext ctx) {
		ruleBuilder.exitIdSelector(ctx);
	}

	@Override public void enterTagSelector(ICSSParser.TagSelectorContext ctx) {
		ruleBuilder.enterTagSelector(ctx);
	}

	@Override public void exitTagSelector(ICSSParser.TagSelectorContext ctx) {
		ruleBuilder.exitTagSelector(ctx);
	}

	@Override
	public void enterExpression(ICSSParser.ExpressionContext ctx) {
		exprBuilder.enterExpression(ctx);
	}
	@Override
	public void exitExpression(ICSSParser.ExpressionContext ctx) {
		exprBuilder.exitExpresssion(ctx);
	}

	@Override
	public void enterIfClause(ICSSParser.IfClauseContext ctx) {
		exprBuilder.enterIfClause(ctx);
	}

	@Override
	public void exitIfClause(ICSSParser.IfClauseContext ctx) {
		exprBuilder.exitIfClause(ctx);
	}

	@Override
	public void enterElseClause(ICSSParser.ElseClauseContext ctx) {
		exprBuilder.enterElseClause(ctx);
	}

	@Override
	public void exitElseClause(ICSSParser.ElseClauseContext ctx) {
		exprBuilder.exitElseClause(ctx);
	}

}