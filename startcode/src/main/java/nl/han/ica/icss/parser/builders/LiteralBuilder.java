package nl.han.ica.icss.parser.builders;

import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.parser.ICSSParser;

public class LiteralBuilder {
    private IHANStack<ASTNode> stack;

    public LiteralBuilder(IHANStack<ASTNode> stack) {
        this.stack = stack;
    }
    
    public void enterBoolLiteral(ICSSParser.BoolLiteralContext ctx) {
        stack.peek().addChild(new BoolLiteral(ctx.getText()));
    }

    public void enterColorLiteral(ICSSParser.ColorLiteralContext ctx) {
        stack.peek().addChild(new ColorLiteral(ctx.getText()));
    }

    public void enterPercentageLiteral(ICSSParser.PercentageLiteralContext ctx) {
        stack.peek().addChild((new PercentageLiteral(ctx.getText())));
    }

    public void enterPixelLiteral(ICSSParser.PixelLiteralContext ctx) {
        PixelLiteral literal = new PixelLiteral(ctx.getText());
        stack.peek().addChild(literal);
    }

    public void enterScalarLiteral(ICSSParser.ScalarLiteralContext ctx) {
        stack.peek().addChild(new ScalarLiteral(ctx.getText()));
    }
}
