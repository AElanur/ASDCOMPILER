package nl.han.ica.icss.parser.builders;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.Stylesheet;
import nl.han.ica.icss.parser.ASTListener;
import nl.han.ica.icss.parser.ICSSParser;

public class StylesheetBuilder {
    private final BuilderContext ctx;

    public StylesheetBuilder(BuilderContext ctx) {
        this.ctx = ctx;
    }

    public void enterStylesheet() {
        ctx.push(new Stylesheet());
    }

    public void exitStylesheet() {
        AST ast = ctx.getAST();
        ast.setRoot((Stylesheet) ctx.pop());
    }
}
