package ecs160.visitor.astvisitors;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;

public class Grade1C extends ASTVisitor{
	private String className;
	private boolean grade;
	public Grade1C(String Cname) {
		className = Cname;
		
	}
	
	@Override
	public boolean visit(FieldDeclaration node) {
		boolean hasPrivate= false;
		boolean hasStatic = false;
		boolean hasName = false;
		@SuppressWarnings("unchecked")
		List<ASTNode> mods = (List<ASTNode>) node.modifiers();
		
		if(node.getType() != null && node.getType().toString().contentEquals(className)) {
			hasName = true;
			
		}
		for (ASTNode m : mods) {
			if (m.toString().equals("private")) {
				hasPrivate = true;
					
			}
			
			if (m.toString().equals("static")) {
					
				hasStatic = true;
				
			}
		}
		
		if (hasPrivate && hasStatic && hasName) {
			grade = true;
		}
		return false;
}

	public boolean getGrade() {
		return grade;
	}
}
