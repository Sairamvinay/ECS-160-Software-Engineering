package ecs160.visitor.astvisitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
//import org.eclipse.jdt.core.dom.IfStatement;

public class ConstructorCaller extends ASTVisitor {
	private String className;
	private boolean insideIfClassCreation;
	public ConstructorCaller(String Cname) {
		className = Cname;
		insideIfClassCreation = false;
	}
	
	@Override
	public boolean visit(ClassInstanceCreation node) {

		//System.out.println(node.getType().toString());
		if (node.resolveConstructorBinding().isConstructor() && node.getType().toString().equals(className)) {
			insideIfClassCreation = true;
			
		}
		return false;
	}
	
	public boolean getBool() {
		return insideIfClassCreation;
	}
}
