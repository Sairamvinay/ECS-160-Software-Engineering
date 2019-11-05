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
		
		@SuppressWarnings("unchecked")
		List<ASTNode> mods = (List<ASTNode>) node.modifiers();
		
		if(node.getType() != null && node.getType().toString().equals(className)) {	
			//checks for variable type as className for the object  
			
		
			for (ASTNode m : mods) {
				if (m.toString().equals("private")) { //if the variable is a private variable
					hasPrivate = true;
					
				}
			
				if (m.toString().equals("static")) {	//if the variable is a static instance variable
					
					hasStatic = true;
				
				}
			}
		
			if (hasPrivate && hasStatic) {	//if private and static of className type
				grade = true;
			}
		}
		return false;
}

	public boolean getGrade() {
		return grade;
	}
}
