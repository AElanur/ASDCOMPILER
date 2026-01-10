package nl.han.ica.icss.parser.builders;

import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.Stylesheet;
import nl.han.ica.icss.parser.ASTListener;
import nl.han.ica.icss.parser.ICSSParser;

public class StylesheetBuilder {
    private final ASTListener listener;

    public StylesheetBuilder(ASTListener listener) {
        this.listener = listener;
    }

    public void enterStylesheet(ICSSParser.StylesheetContext ctx) {
        listener.getHANStack().push(new Stylesheet());
    }

    public void exitStylesheet(ICSSParser.StylesheetContext ctx) {
        AST ast = listener.getAst();
        ast.setRoot((Stylesheet) listener.getHANStack().pop());
    }
}
