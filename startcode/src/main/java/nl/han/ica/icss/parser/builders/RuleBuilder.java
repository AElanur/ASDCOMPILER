package nl.han.ica.icss.parser.builders;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.Stylerule;
import nl.han.ica.icss.parser.ASTListener;
import nl.han.ica.icss.parser.ICSSBaseListener;
import nl.han.ica.icss.parser.ICSSParser;

public class RuleBuilder {
    private final ASTListener listener;

    public RuleBuilder(ASTListener listener) {
        this.listener = listener;
    }

    public void enterStyleRule(ICSSParser.StyleRuleContext ctx) {
        listener.getHANStack().push(new Stylerule());
    }

    public void exitSyleRule(ICSSParser.StyleRuleContext ctx) {
        var temp = listener.getHANStack().pop();
        listener.getHANStack().peek().addChild(temp);
    }
}
