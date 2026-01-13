package nl.han.ica.icss.parser.listener;

import nl.han.ica.datastructures.HANStack;
import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.parser.ICSSBaseListener;
import nl.han.ica.icss.parser.ICSSParser;
import nl.han.ica.icss.parser.builders.*;

public class ASTListener extends ICSSBaseListener {
	private IHANStack<ASTNode> stack = new HANStack<>();
	private AST ast = new AST();
	private final DeclarationBuilder decalBuilder = new DeclarationBuilder(stack);
	private final ExpressionBuilder expBuilder = new ExpressionBuilder(stack);
	private final LiteralBuilder litBuilder = new LiteralBuilder(stack);
	private final RuleBuilder ruleBuilder = new RuleBuilder(stack);
	private final StylesheetBuilder styleBuilder = new StylesheetBuilder(stack, ast);

	public AST getAST() {
		return ast;
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

	@Override public void enterDeclaration(ICSSParser.DeclarationContext ctx) {
		decalBuilder.enterDeclaration(ctx);
	}

	@Override public void exitDeclaration(ICSSParser.DeclarationContext ctx) {
		decalBuilder.exitDeclaration(ctx);
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