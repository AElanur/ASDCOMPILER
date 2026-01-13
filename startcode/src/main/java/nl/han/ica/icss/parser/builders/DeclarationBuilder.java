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
    
    public void enterDeclaration(ICSSParser.DeclarationContext ctx) {
        Declaration decl = new Declaration();
        stack.push(decl);
    }

    public void exitDeclaration(ICSSParser.DeclarationContext ctx) {
        Declaration decl = (Declaration) stack.pop();
        stack.peek().addChild(decl);
    }

    public void enterVariableAssignment(ICSSParser.VariableAssignmentContext ctx) {
        stack.push(new VariableAssignment());
    }

    public void exitVariableAssignment(ICSSParser.VariableAssignmentContext ctx) {
        var node = stack.pop();
        stack.peek().addChild(node);
    }
}
