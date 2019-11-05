package ecs160.visitor.astvisitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IfStatement;
//import org.eclipse.jdt.core.dom.MethodDeclaration;
//import org.eclipse.jdt.core.dom.MethodInvocation;

public class IfChecker extends ASTVisitor {
	
	private String className;
	private boolean insideIf;
	
	public IfChecker(String Cname) {
		className = Cname;
		insideIf = false;
	}

	@Override
	public boolean visit(IfStatement node) {
		
		
		
		ConstructorCaller ConCall = new ConstructorCaller(className); //need to check for the only one call INSIDE IF statement
		node.accept(ConCall);
		insideIf = ConCall.getBool();
		return false;
	}
	
	public boolean getBool() {
		return insideIf;
	}
}
