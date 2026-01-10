package nl.han.ica.icss.parser.builders;

import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.ElseClause;
import nl.han.ica.icss.ast.IfClause;
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
                case "*":
                    container.push(new MultiplyOperation());
                    break;
                case "+":
                    container.push(new AddOperation());
                    break;
                case "-":
                    container.push(new SubtractOperation());
                    break;
            }
        }
    }

    public void exitExpresssion(ICSSParser.ExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
            var temp = container.pop();
            container.peek().addChild(temp);
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
}
