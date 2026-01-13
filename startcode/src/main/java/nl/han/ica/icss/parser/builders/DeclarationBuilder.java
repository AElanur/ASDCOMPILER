package nl.han.ica.icss.parser.builders;

import nl.han.ica.icss.ast.Declaration;
import nl.han.ica.icss.ast.VariableAssignment;
import nl.han.ica.icss.parser.BuilderContext;
import nl.han.ica.icss.parser.ICSSBaseListener;
import nl.han.ica.icss.parser.ICSSParser;

public class DeclarationBuilder {
    private final BuilderContext builder;

    public DeclarationBuilder(BuilderContext builder) {
        this.builder = builder;
    }
    
    public void enterDecleration(ICSSParser.DeclerationContext ctx) {
        builder.push(new Declaration());
    }
    
    public void exitDecleration(ICSSParser.DeclerationContext ctx) {
        builder.peek().addChild(builder.pop());
    }

    public void enterVariableAssignment(ICSSParser.VariableAssignmentContext ctx) {
        builder.push(new VariableAssignment());
    }

    public void exitVariableAssignment(ICSSParser.VariableAssignmentContext ctx) {
        builder.peek().addChild(builder.pop());
    }
}
