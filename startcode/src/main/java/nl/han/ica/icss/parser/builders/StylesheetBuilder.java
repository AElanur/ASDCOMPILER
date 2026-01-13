package nl.han.ica.icss.parser.builders;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.Stylesheet;
import nl.han.ica.icss.parser.BuilderContext;
import nl.han.ica.icss.parser.ICSSBaseListener;
import nl.han.ica.icss.parser.ICSSParser;

public class StylesheetBuilder {
    private final BuilderContext builder;

    public StylesheetBuilder(BuilderContext builder) {
        this.builder = builder;
    }

    public void enterStylesheet(ICSSParser.StylesheetContext ctx) {
        System.out.println("I've entered enter stylesheet");
        builder.push(new Stylesheet());
    }

    public void exitStylesheet(ICSSParser.StylesheetContext ctx) {
        System.out.println("I've entered exit style sheet");
        AST ast = builder.getAST();
        ast.setRoot((Stylesheet) builder.pop());
    }
}
