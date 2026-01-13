package nl.han.ica.icss.parser.builders;

import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;
import nl.han.ica.icss.parser.BuilderContext;
import nl.han.ica.icss.parser.ICSSBaseListener;
import nl.han.ica.icss.parser.ICSSParser;

public class RuleBuilder {
    private final BuilderContext builder;

    public RuleBuilder(BuilderContext builder) {
        this.builder = builder;
    }
    
    public void enterStyleRule(ICSSParser.StyleRuleContext ctx) {
        builder.push(new Stylerule());
    }

    
    public void exitStyleRule(ICSSParser.StyleRuleContext ctx) {
        builder.peek().addChild(builder.pop());
    }

    
    public void enterClassSelector(ICSSParser.ClassSelectorContext ctx) {
       builder.push(new ClassSelector(ctx.getText()));
    }

    
    public void exitClassSelector(ICSSParser.ClassSelectorContext ctx) {
        builder.peek().addChild(builder.pop());
    }

    
    public void enterTagSelector(ICSSParser.TagSelectorContext ctx) {
        builder.push(new TagSelector(ctx.getText()));
    }

    
    public void exitTagSelector(ICSSParser.TagSelectorContext ctx) {
        builder.addToParent(builder.pop());
    }

    
    public void enterIdSelector(ICSSParser.IdSelectorContext ctx) {
        System.out.println("I've entered ID selector");
        builder.push(new IdSelector(ctx.getText()));
    }

    
    public void exitIdSelector(ICSSParser.IdSelectorContext ctx) {
        builder.addToParent(builder.pop());
    }
}
