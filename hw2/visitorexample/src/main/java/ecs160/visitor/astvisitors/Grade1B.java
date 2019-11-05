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
			boolean hasPublic= false;	//checks for a public modifier inside the method
			boolean hasStatic = false;	//checks for a static modifier inside the same method
			
			
			
			if (node.getReturnType2() != null && node.getReturnType2().toString().equals(className)) {
				//checks if the returning type is className
				
				//look through all modifier of method which return className object
			
			
				for (ASTNode m : mods) {
					if (m.toString().equals("public")) {
						
						//found a public member
						hasPublic = true;
						
					}
				
					if (m.toString().equals("static")) {
						
						//found a static member
						hasStatic = true;
					
					}
				
				}
					
				//if this method is public static and it returns className object
				
				if(hasPublic && hasStatic) {	
					
					grade = true;
				
				}
			}
			
			
		return false;
	}
		
		
		
	
	public boolean getGrade() {
		return grade;
	}
}
