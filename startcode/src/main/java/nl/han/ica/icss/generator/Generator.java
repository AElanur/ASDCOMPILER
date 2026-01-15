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
			System.err.println("Skipping rule with no selectors: " + stylerule);
			return;
		}
		var selector = stylerule.selectors.getFirst().toString();
		sb.append(selector).append(" {\n");
		for (var child : stylerule.body) {
			switch (child) {
				case Declaration d -> generateDeclaration((Declaration) child, sb);
				case IfClause ic -> generateIfClause((IfClause) child, sb);
				case ElseClause ec -> generateElseClause((ElseClause) child, sb);
				default -> System.out.println("Unidentified child.");
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
			case PercentageLiteral pel -> ((PercentageLiteral) literal).value + "%";
			case PixelLiteral pil -> ((PixelLiteral) literal).value + "px";
			default -> String.valueOf(((ColorLiteral) literal).value);
		};
	}
}
