package nl.han.ica.icss.generator;


import nl.han.ica.icss.ast.*;
import nl.han.ica.icss.ast.literals.ColorLiteral;
import nl.han.ica.icss.ast.literals.PercentageLiteral;
import nl.han.ica.icss.ast.literals.PixelLiteral;

public class Generator {

	public String generate(AST ast) {
		var sb = new StringBuilder();
		generateStyleSheet(ast.root, sb);
		return sb.toString().trim();
	}

	private void generateStyleSheet(ASTNode styleSheet, StringBuilder sb) {
		for (var child : styleSheet.getChildren()) {
			if (child instanceof Stylerule rule) {
                generateStyleRule(rule, sb);
				sb.append("\n\n");
			}
		}
	}

	private void generateStyleRule(Stylerule stylerule, StringBuilder sb) {
		if (stylerule.selectors == null || stylerule.selectors.isEmpty()) {
			throw new IllegalStateException("Style rule requires at least one selector");
		}
        var selector = stylerule.selectors.getFirst().toString();
		sb.append(selector).append(" {\n");
		for (var childNode : stylerule.body) {
			switch (childNode) {
				case Declaration _ -> generateDeclaration((Declaration) childNode, sb);
				case IfClause _ -> generateIfClause((IfClause) childNode, sb);
				case ElseClause _ -> generateElseClause((ElseClause) childNode, sb);
				default -> System.out.println("Unidentified child: " + childNode);
			}
		}
		sb.append("}");
	}

	private void generateElseClause(ElseClause elseClause, StringBuilder sb) {
		sb.append("    } else {\n");
		for (var inner : elseClause.body) {
			generateDeclaration((Declaration) inner, sb);
		}
		sb.append("    }\n");
	}

	private void generateIfClause(IfClause ifClause, StringBuilder sb) {
		sb.append("    if[").append(ifClause.conditionalExpression.toString()).append("] {\n");

		// Controleer wat er binnen in de if clause is.
		for (var inner : ifClause.body) {
			switch (inner) {
				case Declaration d -> generateDeclaration(d, sb);
				case IfClause nested -> generateIfClause(nested, sb);
				case ElseClause elseClause -> generateElseClause(elseClause, sb);
				default -> System.out.println("Unidentified node");
			}
		}

		sb.append("    }\n");
	}

	private void generateDeclaration(Declaration declaration, StringBuilder sb) {
		sb.append("  ").append(declaration.property.name).append(": ").append(getLiteralValue(declaration.expression)).append(";").append("\n");
	}

	private String getLiteralValue(Expression literal){
		return switch (literal) {
			case PercentageLiteral _ -> ((PercentageLiteral) literal).value + "%";
			case PixelLiteral _ -> ((PixelLiteral) literal).value + "px";
			default -> String.valueOf(((ColorLiteral) literal).value);
		};
	}
}
