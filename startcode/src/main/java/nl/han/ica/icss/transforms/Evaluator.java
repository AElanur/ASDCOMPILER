package nl.han.ica.icss.transforms;

import nl.han.ica.datastructures.HANLinkedList;
import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.BoolLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;
import nl.han.ica.icss.ast.literals.ScalarLiteral;
import nl.han.ica.icss.ast.operations.AddOperation;
import nl.han.ica.icss.ast.operations.MultiplyOperation;
import nl.han.ica.icss.ast.operations.SubtractOperation;

import java.util.ArrayList;

public class Evaluator implements Transform {

    @Override
    public void apply(AST ast) {
        var globalVariables = new HANLinkedList<VariableAssignment>();
        for (var child : ast.root.getChildren()) {
            switch (child) {
                case VariableAssignment va -> {
                    globalVariables.addFirst((VariableAssignment) child);
                    transformVariableAssignment((VariableAssignment) child, globalVariables);
                }
                case Stylerule sr -> sr.body = transformRuleBody(sr.body, globalVariables);
                default -> throw new IllegalStateException("Unexpected AST node type: " + child.getClass().getSimpleName());
            }
        }
    }

    private ArrayList<ASTNode> transformRuleBody(ArrayList<ASTNode> body, HANLinkedList<VariableAssignment> scopeVars) {
        var temp = new ArrayList<ASTNode>();
        for (var child : body) {
            switch (child) {
                case VariableAssignment v -> {
                    scopeVars.addFirst((VariableAssignment) child);
                    transformVariableAssignment((VariableAssignment) child, scopeVars);
                }
                case Declaration d -> {
                    transformDeclaration((Declaration) child, scopeVars);
                    temp.add(child);
                }
                case IfClause i -> {
                    transformIfClause(i, scopeVars);  // Transform IN-PLACE
                    temp.add(i);
                }
                default -> throw new IllegalStateException("Unexpected value: " + child);
            }
        }
        return temp;
    }

    private void transformVariableAssignment(VariableAssignment variableAssignment, HANLinkedList<VariableAssignment> scopeVars) {
        variableAssignment.expression = transformExpression(variableAssignment.expression, scopeVars);
    }

    private void transformDeclaration(Declaration declaration, HANLinkedList<VariableAssignment> scopeVars) {
        declaration.expression = transformExpression(declaration.expression, scopeVars);
    }

    private void transformIfClause(IfClause ifClause, HANLinkedList<VariableAssignment> scopeVars) {
        ifClause.conditionalExpression = transformExpression(ifClause.conditionalExpression, scopeVars);

        assert ifClause.conditionalExpression != null;
        if (!((BoolLiteral) ifClause.conditionalExpression).value) {
            if (ifClause.elseClause == null) {
                ifClause.body = new ArrayList<>();
            } else {
                ifClause.body = ifClause.elseClause.body;
            }
        }
        ifClause.elseClause= null;
        transformRuleBody(ifClause.body, scopeVars);
    }

    private Literal transformExpression(Expression expression, HANLinkedList<VariableAssignment> scopeVars) {
        return switch (expression) {
            case Operation o -> transformOperation((Operation) expression, scopeVars);
            case VariableReference vr -> getVariableLiteral((VariableReference) expression, scopeVars);
            case Literal l -> (Literal) expression;
            default -> null;
        };
    }

    private Literal transformOperation(Operation operation, HANLinkedList<VariableAssignment> scopeVars) {
        Literal leftLiteral = transformExpression(operation.lhs, scopeVars);
        Literal rightLiteral = transformExpression(operation.rhs, scopeVars);

        var leftValue = getLiteralValue(leftLiteral);
        var rightValue= getLiteralValue(rightLiteral);

        return switch (operation) {
            case AddOperation ao -> createSumLiteral(leftLiteral, leftValue + rightValue);
            case SubtractOperation so -> createSumLiteral(leftLiteral, leftValue + rightValue);
            case MultiplyOperation mo -> {
                var type = leftLiteral instanceof ScalarLiteral ? rightLiteral : leftLiteral;
                yield createSumLiteral(type, leftValue * rightValue);
            }
            default -> null;
        };
    }

    private Literal getVariableLiteral(VariableReference variableReference, HANLinkedList<VariableAssignment> scopeVars) {
        var currentNode = scopeVars.getFirstNode();

        while (currentNode!= null) {
            var variableAssignment = currentNode.getValue();
            var name = variableAssignment.name.name;
            if (name.equals(variableReference.name)) {
                return (Literal) variableAssignment.expression;
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    private int getLiteralValue(Literal literal){
        return switch (literal) {
            case PercentageLiteral pel -> ((PercentageLiteral) literal).value;
            case PixelLiteral pil -> ((PixelLiteral) literal).value;
            default -> ((ScalarLiteral) literal).value;
        };
    }

    private Literal createSumLiteral(Literal type, int value) {
        return switch (type) {
            case PercentageLiteral pel -> new PercentageLiteral(value);
            case PixelLiteral pil -> new PixelLiteral(value);
            default -> new ScalarLiteral(value);
        };
    }
}
