package nl.han.ica.icss.parser.builders;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;
import nl.han.ica.icss.parser.BuilderContext;
import nl.han.ica.icss.parser.ICSSBaseListener;
import nl.han.ica.icss.parser.ICSSParser;

public class ExpressionBuilder {
    private final BuilderContext builder;

    public ExpressionBuilder(BuilderContext builder) {
        this.builder = builder;
    }
    
    public void enterExpression(ICSSParser.ExpressionContext ctx) {
        if(ctx.getChildCount() == 3) {
            var operator = ctx.getChild(1).getText();
            switch (operator) {
                case "*" -> builder.push(new MultiplyOperation());
                case "+" -> builder.push(new AddOperation());
                case "-" -> builder.push(new SubtractOperation());
            }
        }
    }
    
    public void exitExpression(ICSSParser.ExpressionContext ctx) {
        if (ctx.getChildCount() == 3) {
            builder.peek().addChild(builder.pop());
        }
    }

    public void enterIfClause(ICSSParser.IfClauseContext ctx) {
        builder.push(new IfClause());
    }
    
    public void exitIfClause(ICSSParser.IfClauseContext ctx) {
        builder.peek().addChild(builder.pop());
    }
    
    public void enterElseClause(ICSSParser.ElseClauseContext ctx) {
        builder.push(new ElseClause());
    }

    public void exitElseClause(ICSSParser.ElseClauseContext ctx) {
        builder.peek().addChild(builder.pop());
    }

    public void enterPropertyName(ICSSParser.PropertyNameContext ctx) {
        builder.peek().addChild(new PropertyName(ctx.getText()));
    }
    
    public void enterVariableReference(ICSSParser.VariableReferenceContext ctx) {
        builder.peek().addChild(new VariableReference(ctx.getText()));
    }
}
