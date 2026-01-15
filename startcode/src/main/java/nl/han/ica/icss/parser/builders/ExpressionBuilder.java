package nl.han.ica.icss.parser.builders;

import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.parser.ICSSParser;

public class ExpressionBuilder {
    private IHANStack<ASTNode> stack;
    private final int sumExpression = 3;

    public ExpressionBuilder(IHANStack<ASTNode> stack) {
        this.stack = stack;
    }
    
    public void enterExpression(ICSSParser.ExpressionContext ctx) {
        if(ctx.getChildCount() == sumExpression) {
            var operator = ctx.getChild(1).getText();
            stack.push(switch (operator) {
                case "*" -> new MultiplyOperation();
                case "+" -> new AddOperation();
                case "-" -> new SubtractOperation();
                default -> throw new IllegalStateException("Unexpected value: " + operator);
            });
        }
    }
    
    public void exitExpression(ICSSParser.ExpressionContext ctx) {
        if (ctx.getChildCount() == sumExpression) {
            var node = stack.pop();
            stack.peek().addChild(node);
        }
    }

    public void enterIfClause(ICSSParser.IfClauseContext ctx) {
        stack.push(new IfClause());
    }
    
    public void exitIfClause(ICSSParser.IfClauseContext ctx) {
        var ifClause = stack.pop();
        stack.peek().addChild(ifClause);
    }
    
    public void enterElseClause(ICSSParser.ElseClauseContext ctx) {
        var elseClause = new ElseClause();
        stack.push(elseClause);
    }

    public void exitElseClause(ICSSParser.ElseClauseContext ctx) {
        var elseClause = stack.pop();
        stack.peek().addChild(elseClause);
    }

    public void enterPropertyName(ICSSParser.PropertyNameContext ctx) {
        stack.peek().addChild(new PropertyName(ctx.getText()));
    }
    
    public void enterVariableReference(ICSSParser.VariableReferenceContext ctx) {
        VariableReference variableRef = new VariableReference(ctx.getText());
        stack.peek().addChild(variableRef);
    }
}
