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
    
    public void enterStyleRule(ICSSParser.StyleRuleContext ctx) {
        Stylerule rule = new Stylerule();
        stack.push(rule);
    }

    public void exitStyleRule(ICSSParser.StyleRuleContext ctx) {
        Stylerule rule = (Stylerule) stack.pop();
        stack.peek().addChild(rule);
    }

    public void enterClassSelector(ICSSParser.ClassSelectorContext ctx) {
       stack.push(new ClassSelector(ctx.getText()));
    }

    
    public void exitClassSelector(ICSSParser.ClassSelectorContext ctx) {
        var temp = stack.pop();
        stack.peek().addChild(temp);
    }

    
    public void enterTagSelector(ICSSParser.TagSelectorContext ctx) {
        stack.push(new TagSelector(ctx.getText()));
    }

    
    public void exitTagSelector(ICSSParser.TagSelectorContext ctx) {
        stack.peek().addChild(stack.pop());
    }

    
    public void enterIdSelector(ICSSParser.IdSelectorContext ctx) {
        stack.push(new IdSelector(ctx.getText()));
    }

    
    public void exitIdSelector(ICSSParser.IdSelectorContext ctx) {
        stack.peek().addChild(stack.pop());
    }
}
