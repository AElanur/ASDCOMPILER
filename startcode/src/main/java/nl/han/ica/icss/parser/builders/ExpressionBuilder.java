package nl.han.ica.icss.parser.builders;

import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.parser.ICSSParser;

public class ExpressionBuilder {
    private IHANStack<ASTNode> stack;

    public ExpressionBuilder(IHANStack<ASTNode> stack) {
        this.stack = stack;
    }
    
    public void enterExpression(ICSSParser.ExpressionContext ctx) {
        if(ctx.getChildCount() == 3) {
            var operator = ctx.getChild(1).getText();
            switch (operator) {
                case "*" -> stack.push(new MultiplyOperation());
                case "+" -> stack.push(new AddOperation());
                case "-" -> stack.push(new SubtractOperation());
            }
        }
    }
    
    public void exitExpression(ICSSParser.ExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
            stack.peek().addChild(stack.pop());
        }
    }

    public void enterIfClause(ICSSParser.IfClauseContext ctx) {
        stack.push(new IfClause());
    }
    
    public void exitIfClause(ICSSParser.IfClauseContext ctx) {
        stack.peek().addChild(stack.pop());
    }
    
    public void enterElseClause(ICSSParser.ElseClauseContext ctx) {
        stack.push(new ElseClause());
    }

    public void exitElseClause(ICSSParser.ElseClauseContext ctx) {
        stack.peek().addChild(stack.pop());
    }

    public void enterPropertyName(ICSSParser.PropertyNameContext ctx) {
        stack.peek().addChild(new PropertyName(ctx.getText()));
    }
    
    public void enterVariableReference(ICSSParser.VariableReferenceContext ctx) {
        stack.peek().addChild(new VariableReference(ctx.getText()));
    }
}
