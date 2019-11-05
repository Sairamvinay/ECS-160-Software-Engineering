package ecs160.visitor.astvisitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;

public class ConstructorCaller extends ASTVisitor {
	private String className;
	private int numCalls;
	public ConstructorCaller(String Cname) {
		
		className = Cname;
		numCalls = 0;
	
	}
	
	@Override
	public boolean visit(ClassInstanceCreation node) {

		//if the class instance created is a constructor and the type is of className
		if (node.resolveConstructorBinding().isConstructor() && node.getType().toString().equals(className)) {
			
			numCalls++;	//increment the numCalls
			
		}
		
		
		
		return false;
	}
	
	public boolean getBool() {
		
		if (numCalls != 1) {	//iff the numCalls is 1, the exact one call is verified
			return false;
		}
		
		else {
			return true;
		}
	}
}
