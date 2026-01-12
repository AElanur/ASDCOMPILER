package nl.han.ica.icss.parser.builders;

import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.parser.ASTListener;
import nl.han.ica.icss.parser.ICSSParser;

public class ExpressionBuilder {
    private IHANStack<ASTNode> container;

    public ExpressionBuilder(ASTListener listener) {
        this.container = listener.getHANStack();
    }

    public void enterExpression(ICSSParser.ExpressionContext ctx) {
        if(ctx.getChildCount() == 3) {
            var operator = ctx.getChild(1).getText();
            switch (operator) {
                case "*" -> container.push(new MultiplyOperation());
                case "+" -> container.push(new AddOperation());
                case "-" -> container.push(new SubtractOperation());
            }
        }
    }

    public void exitExpresssion(ICSSParser.ExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
            container.peek().addChild(container.pop());
        }
    }

    public void enterIfClause(ICSSParser.IfClauseContext ctx) {
        container.push(new IfClause());
    }

    public void exitIfClause(ICSSParser.IfClauseContext ctx) {
        var temp = container.pop();
        container.peek().addChild(temp);
    }

    public void enterElseClause(ICSSParser.ElseClauseContext ctx) {
        container.push(new ElseClause());
    }

    public void exitElseClause(ICSSParser.ElseClauseContext ctx) {
        var temp = container.pop();
        container.peek().addChild(temp);
    }

    public void enterPropertyName(ICSSParser.PropertyNameContext ctx) {
        container.peek().addChild(new PropertyName(ctx.getText()));
    }

    public void enterBoolLiteral(ICSSParser.BoolLiteralContext ctx) {
        container.peek().addChild(new BoolLiteral(ctx.getText()));
    }

    public void enterColorLiteral(ICSSParser.ColorLiteralContext ctx) {
        container.peek().addChild(new ColorLiteral(ctx.getText()));
    }

    public void enterPercentageLiteral(ICSSParser.PercentageLiteralContext ctx) {
        container.peek().addChild((new PercentageLiteral(ctx.getText())));
    }

    public void enterPixelLiteral(ICSSParser.PixelLiteralContext ctx) {
        container.peek().addChild(new PixelLiteral(ctx.getText()));
    }

    public void enterScalarLiteral(ICSSParser.ScalarLiteralContext ctx) {
        container.peek().addChild(new ScalarLiteral(ctx.getText()));
    }

    public void enterVariableReference(ICSSParser.VariableReferenceContext ctx) {
        container.peek().addChild(new VariableReference(ctx.getText()));
    }
}
