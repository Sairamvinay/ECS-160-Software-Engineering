package ecs160.visitor.astvisitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IfStatement;
//import org.eclipse.jdt.core.dom.MethodDeclaration;
//import org.eclipse.jdt.core.dom.MethodInvocation;

public class ConstructorIfChecker extends ASTVisitor {
	
	private String className;
	private boolean insideIf;
	public ConstructorIfChecker(String Cname) {
		className = Cname;
		insideIf = false;
	}

	@Override
	public boolean visit(IfStatement node) {
		
		ConstructorCaller ConCall = new ConstructorCaller(className);
		node.accept(ConCall);
		insideIf = ConCall.getBool();
		return false;
	}
	
	public boolean getBool() {
		return insideIf;
	}
}
