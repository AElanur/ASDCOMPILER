package nl.han.ica.icss.parser.builders;

import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;
import nl.han.ica.icss.parser.ICSSParser;

public class RuleBuilder {
    private IHANStack<ASTNode> stack;

    public RuleBuilder(IHANStack<ASTNode> stack) {
        this.stack = stack;
    }
    
    public void enterStyleRule() {
        Stylerule rule = new Stylerule();
        stack.push(rule);
    }

    public void exitStyleRule() {
        var node = stack.pop();
        stack.peek().addChild(node);
    }

    public void enterClassSelector(ICSSParser.ClassSelectorContext ctx) {
        stack.push(new ClassSelector(ctx.getText()));
    }

    public void exitClassSelector() {
        var temp = stack.pop();
        stack.peek().addChild(temp);
    }

    public void enterTagSelector(ICSSParser.TagSelectorContext ctx) {
        stack.push(new TagSelector(ctx.getText()));
    }

    public void exitTagSelector() {
        var node = stack.pop();
        stack.peek().addChild(node);
    }

    public void enterIdSelector(ICSSParser.IdSelectorContext ctx) {
        stack.push(new IdSelector(ctx.getText()));
    }

    public void exitIdSelector() {
        var node = stack.pop();
        stack.peek().addChild(node);
    }
}
