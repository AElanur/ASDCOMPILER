package nl.han.ica.icss.parser.builders;

import nl.han.ica.icss.ast.Stylerule;
import nl.han.ica.icss.parser.ASTListener;
import nl.han.ica.icss.parser.ICSSBaseListener;
import nl.han.ica.icss.parser.ICSSParser;

public class RuleBuilder extends ICSSBaseListener {
    private final ASTListener listener;

    public RuleBuilder(ASTListener listener) {
        this.listener = listener;
    }

//    @Override
//    public void enterStyleRule(ICSSParser.StyleRuleContext ctx) {
//        currentContainer.push(new Stylerule());
//    }
//
//    @Override
//    public void exitStyleRule(ICSSParser.StyleRuleContext ctx) {
//        var temp = currentContainer.pop();
//        currentContainer.peek().addChild(temp);
//    }
}
