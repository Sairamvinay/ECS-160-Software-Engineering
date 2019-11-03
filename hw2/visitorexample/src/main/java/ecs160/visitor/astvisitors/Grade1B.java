package ecs160.visitor.astvisitors;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class Grade1B extends ASTVisitor{
	private String className;
	private boolean grade = false;

	public Grade1B(String Cname) {
		className = Cname;
		
	}
	
	@Override
	public boolean visit(MethodDeclaration node) {
		
		
			
			@SuppressWarnings("unchecked")
			List<ASTNode> mods = (List<ASTNode>) node.modifiers();
			boolean hasPublic= false;
			boolean hasStatic = false;
			boolean returnsType = false;
			
			
			if (node.getReturnType2() != null && node.getReturnType2().toString().equals(className)) {
				returnsType = true;
			}
			
			for (ASTNode m : mods) {
				if (m.toString().equals("public")) {
					hasPublic = true;
						
				}
				
				if (m.toString().equals("static")) {
						
					hasStatic = true;
					
				}
				
			}
					
		
			if(returnsType && hasPublic && hasStatic) {
				grade = true;
				
			}
			
			
		return false;
	}
		
		
		
	
	public boolean getGrade() {
		return grade;
	}
}
