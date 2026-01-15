package nl.han.ica.icss.parser.builders;

import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.Declaration;
import nl.han.ica.icss.ast.VariableAssignment;
import nl.han.ica.icss.parser.ICSSParser;

public class DeclarationBuilder {
    private IHANStack<ASTNode> stack;

    public DeclarationBuilder(IHANStack<ASTNode> stack) {
        this.stack = stack;
    }
    
    public void enterDeclaration() {
        Declaration decl = new Declaration();
        stack.push(decl);
    }

    public void exitDeclaration() {
        var declaration = stack.pop();
        stack.peek().addChild(declaration);
    }

    public void enterVariableAssignment() {
        stack.push(new VariableAssignment());
    }

    public void exitVariableAssignment() {
        var node = stack.pop();
        stack.peek().addChild(node);
    }
}
