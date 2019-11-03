package ecs160.visitor.astvisitors;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class Grade1A extends ASTVisitor{
	private boolean grade;
	
	
	public Grade1A(String Cname) {
		grade = false;
	
	}
	@Override
	public boolean visit(MethodDeclaration node) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<ASTNode> mods = (List<ASTNode>) node.modifiers();
		if (node.isConstructor()) {
			for (ASTNode m : mods) {
				if (m.toString().equals("private")) {
					grade = true;
					return true;
				}
			}
		}
		
		return false;
	}
	public boolean getGrade() {
		return grade;
	}
	
	
}
