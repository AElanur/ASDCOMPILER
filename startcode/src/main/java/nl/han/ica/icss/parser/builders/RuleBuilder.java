package nl.han.ica.icss.parser.builders;

import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.selectors.ClassSelector;
import nl.han.ica.icss.ast.selectors.IdSelector;
import nl.han.ica.icss.ast.selectors.TagSelector;
import nl.han.ica.icss.parser.ICSSParser;

public class RuleBuilder {
    private final BuilderContext ctx;

    public RuleBuilder(BuilderContext ctx) {
        this.ctx = ctx;
    }

    public void enterStyleRule() {
        ctx.push(new Stylerule());
    }

    public void exitSyleRule() {
        ctx.peek().addChild(ctx.pop());
    }

    public void enterVariableAssignment() {
        ctx.push(new VariableAssignment());
    }

    public void exitVariableAssignment() {
        ctx.peek().addChild(ctx.pop());
    }

    public void enterDeclaration() {
        ctx.push(new Declaration());
    }

    public void exitDeclaration() {
        ctx.peek().addChild(ctx.pop());
    }

    public void enterClassSelector(ICSSParser.ClassSelectorContext ctx) {
//        ctx.push(new ClassSelector(ctx.getText()));
    }

    public void exitClassSelector() {
        ctx.peek().addChild(ctx.pop());
    }

//    public void enterTagSelector() {
//        ctx.push(new TagSelector(ctx.getText()));
//    }

    public void exitTagSelector(ICSSParser.TagSelectorContext ctx) {
        var temp = container.pop();
        container.peek().addChild(temp);
    }

    public void enterIdSelector(ICSSParser.IdSelectorContext ctx) {
        container.push(new IdSelector(ctx.getText()));
    }

    public void exitIdSelector(ICSSParser.IdSelectorContext ctx) {
        var temp = container.pop();
        container.peek().addChild(temp);
    }
}
