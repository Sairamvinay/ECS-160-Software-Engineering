package ecs160.visitor.astvisitors;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class Grade1A extends ASTVisitor{
	private boolean grade;
	private String className;
	
	public Grade1A(String Cname) {
		className = Cname;
		grade = false;
	
	}
	@Override
	public boolean visit(MethodDeclaration node) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<ASTNode> mods = (List<ASTNode>) node.modifiers();	//Extract all the modifiers of the class
		
		if (node.isConstructor() && node.getName().toString().equals(className)) { 
			
			//look for the constructor call
			for (ASTNode m : mods) {
				//Check all modifiers; look for private Constructor
				if (m.toString().equals("private")) {	//look for the private modifier to the constructor
					grade = true;	//set grade to true once there is a private constructor for the className to check for
					
				}
			}
		}
		
		return false; //end search once done checking
	}
	public boolean getGrade() {
		return grade;
	}
	
	
}
