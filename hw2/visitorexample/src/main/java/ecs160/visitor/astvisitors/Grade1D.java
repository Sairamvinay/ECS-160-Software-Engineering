package ecs160.visitor.astvisitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.IfStatement;

public class Grade1D extends ASTVisitor{
	private String className;
	private boolean grade;
	public Grade1D(String Cname) {
		className = Cname;
		
	}
	
	@Override
	public boolean visit(IfStatement node) {
//		System.out.println(node.getType().toString());
		return false;
	}
	public boolean getGrade() {
		return grade;
	}
}
