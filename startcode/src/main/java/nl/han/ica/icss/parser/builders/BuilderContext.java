package nl.han.ica.icss.parser.builders;

import nl.han.ica.datastructures.HANStack;
import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ASTNode;

public class BuilderContext {
    private final IHANStack<ASTNode> stack = new HANStack<>();
    private final AST ast = new AST();

    public void push(ASTNode node) {
        stack.push(node);
    }

    public ASTNode pop() {
        return stack.pop();
    }

    public ASTNode peek() {
        return stack.peek();
    }

    public void addToParent(ASTNode node) {
        stack.peek().addChild(node);
    }

    public AST getAST(){
        return ast;
    }
}
