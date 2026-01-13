package nl.han.ica.icss.parser;

import nl.han.ica.datastructures.HANStack;
import nl.han.ica.datastructures.IHANStack;
import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ASTNode;

public class BuilderContext {
    private IHANStack<ASTNode> stack;
    private final AST ast = new AST();

    public BuilderContext() {
        this.stack = new HANStack<>();
    }

    public void push(ASTNode node) {
        stack.push(node);
    }

    public ASTNode pop() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty - cannot pop");
        }
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
