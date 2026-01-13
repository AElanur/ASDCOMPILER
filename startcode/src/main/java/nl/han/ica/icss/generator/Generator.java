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
			if (child instanceof Stylerule) {
				Stylerule rule = (Stylerule) child;
				System.out.println("Generating rule for selectors: " + rule.selectors);
				System.out.println("Body contains: " + rule.body.size() + " children");
				generateStyleRule(rule, sb);
				sb.append("\n\n");
			}
		}
	}

	private void generateStyleRule(Stylerule stylerule, StringBuilder sb) {
		var selector = stylerule.selectors.get(0).toString();
		System.out.println("Here's the selector " + selector);
		sb.append(selector).append(" {\n");
		for (var child : stylerule.body) {
			generateDeclaration((Declaration) child, sb);
		}
		sb.append("}");
	}

	private void generateDeclaration(Declaration declaration, StringBuilder sb) {
		sb.append("  ").append(declaration.property.name).append(": ").append(getLiteralValue(declaration.expression)).append(";").append("\n");
	}

	private String getLiteralValue(Expression literal){
		if (literal instanceof PercentageLiteral) return ((PercentageLiteral) literal).value + "%";
		if (literal instanceof PixelLiteral) return ((PixelLiteral) literal).value + "px";
		return String.valueOf(((ColorLiteral) literal).value);
	}
}
