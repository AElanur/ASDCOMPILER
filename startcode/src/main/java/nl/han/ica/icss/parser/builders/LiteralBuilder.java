package nl.han.ica.icss.parser.builders;

import nl.han.ica.icss.ast.literals.*;
import nl.han.ica.icss.parser.BuilderContext;
import nl.han.ica.icss.parser.ICSSBaseListener;
import nl.han.ica.icss.parser.ICSSParser;

public class LiteralBuilder {
    private final BuilderContext builder;

    public LiteralBuilder(BuilderContext builder) {
        this.builder = builder;
    }
    
    public void enterBoolLiteral(ICSSParser.BoolLiteralContext ctx) {
        builder.peek().addChild(new BoolLiteral(ctx.getText()));
    }

    public void enterColorLiteral(ICSSParser.ColorLiteralContext ctx) {
        builder.peek().addChild(new ColorLiteral(ctx.getText()));
    }

    public void enterPercentageLiteral(ICSSParser.PercentageLiteralContext ctx) {
        builder.peek().addChild((new PercentageLiteral(ctx.getText())));
    }

    public void enterPixelLiteral(ICSSParser.PixelLiteralContext ctx) {
        builder.peek().addChild(new PixelLiteral(ctx.getText()));
    }

    public void enterScalarLiteral(ICSSParser.ScalarLiteralContext ctx) {
        builder.peek().addChild(new ScalarLiteral(ctx.getText()));
    }
}
