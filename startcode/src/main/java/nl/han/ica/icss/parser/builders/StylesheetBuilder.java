package nl.han.ica.icss.parser.builders;

import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.Stylesheet;
import nl.han.ica.icss.parser.ICSSParser;

public class StylesheetBuilder {
    private IHANStack<ASTNode> stack;
    private AST ast;

    public StylesheetBuilder(IHANStack<ASTNode> stack, AST ast) {
        this.stack = stack;
        this.ast = ast;
    }

    public void enterStylesheet(ICSSParser.StylesheetContext ctx) {
        stack.push(new Stylesheet());
    }

    public void exitStylesheet(ICSSParser.StylesheetContext ctx) {
        ast.setRoot((Stylesheet) stack.pop());
    }
}
