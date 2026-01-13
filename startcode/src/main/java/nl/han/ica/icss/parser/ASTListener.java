package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.parser.builders.*;

public class ASTListener extends ICSSBaseListener {
	private final BuilderContext ctx = new BuilderContext();
	private final DeclarationBuilder decalBuilder = new DeclarationBuilder(ctx);
	private final ExpressionBuilder expBuilder = new ExpressionBuilder(ctx);
	private final LiteralBuilder litBuilder = new LiteralBuilder(ctx);
	private final RuleBuilder ruleBuilder = new RuleBuilder(ctx);
	private final StylesheetBuilder styleBuilder = new StylesheetBuilder(ctx);

	public AST getAST() {
		return ctx.getAST();
	}

	@Override
	public void enterStylesheet(ICSSParser.StylesheetContext ctx) {
		styleBuilder.enterStylesheet(ctx);
	}

	@Override public void exitStylesheet(ICSSParser.StylesheetContext ctx) {
		styleBuilder.exitStylesheet(ctx);
	}

	@Override public void enterStyleRule(ICSSParser.StyleRuleContext ctx) {
		ruleBuilder.enterStyleRule(ctx);
	}

	@Override public void exitStyleRule(ICSSParser.StyleRuleContext ctx) {
		ruleBuilder.exitStyleRule(ctx);
	}

	@Override public void enterVariableAssignment(ICSSParser.VariableAssignmentContext ctx) {
		decalBuilder.enterVariableAssignment(ctx);
	}

	@Override public void exitVariableAssignment(ICSSParser.VariableAssignmentContext ctx) {
		decalBuilder.exitVariableAssignment(ctx);
	}

	@Override public void enterVariableReference(ICSSParser.VariableReferenceContext ctx) {
		expBuilder.enterVariableReference(ctx);
	}

	@Override public void enterDecleration(ICSSParser.DeclerationContext ctx) {
		decalBuilder.enterDecleration(ctx);
	}

	@Override public void exitDecleration(ICSSParser.DeclerationContext ctx) {
		decalBuilder.exitDecleration(ctx);
	}

	@Override public void enterPropertyName(ICSSParser.PropertyNameContext ctx) {
		expBuilder.enterPropertyName(ctx);
	}

	@Override public void enterBoolLiteral(ICSSParser.BoolLiteralContext ctx) {
		litBuilder.enterBoolLiteral(ctx);
	}

	@Override public void enterColorLiteral(ICSSParser.ColorLiteralContext ctx) {
		litBuilder.enterColorLiteral(ctx);
	}

	@Override public void enterPercentageLiteral(ICSSParser.PercentageLiteralContext ctx) {
		litBuilder.enterPercentageLiteral(ctx);
	}

	@Override public void enterPixelLiteral(ICSSParser.PixelLiteralContext ctx) {
		litBuilder.enterPixelLiteral(ctx);
	}

	@Override public void enterScalarLiteral(ICSSParser.ScalarLiteralContext ctx) {
		litBuilder.enterScalarLiteral(ctx);
	}

	@Override public void enterClassSelector(ICSSParser.ClassSelectorContext ctx) {
		ruleBuilder.enterClassSelector(ctx);
	}

	@Override public void exitClassSelector(ICSSParser.ClassSelectorContext ctx) {
		ruleBuilder.exitClassSelector(ctx);
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
		expBuilder.enterExpression(ctx);
	}
	@Override
	public void exitExpression(ICSSParser.ExpressionContext ctx) {
		expBuilder.exitExpression(ctx);
	}

	@Override
	public void enterIfClause(ICSSParser.IfClauseContext ctx) {
		expBuilder.enterIfClause(ctx);
	}

	@Override
	public void exitIfClause(ICSSParser.IfClauseContext ctx) {
		expBuilder.exitIfClause(ctx);
	}

	@Override
	public void enterElseClause(ICSSParser.ElseClauseContext ctx) {
		expBuilder.enterElseClause(ctx);
	}

	@Override
	public void exitElseClause(ICSSParser.ElseClauseContext ctx) {
		expBuilder.exitElseClause(ctx);
	}
}