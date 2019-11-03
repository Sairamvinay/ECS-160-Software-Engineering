package ecs160.visitor.astvisitors;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
//import org.eclipse.jdt.core.dom.IfStatement;
//import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.MethodDeclaration;


public class Grade1D extends ASTVisitor{
	private String className;
	private boolean grade;
	public Grade1D(String Cname) {
		className = Cname;
		
	}
	
	@Override
	public boolean visit(MethodDeclaration node) {
		//System.out.println(node.toString());
		@SuppressWarnings("unchecked")
		List<ASTNode> mods = (List<ASTNode>) node.modifiers();
		boolean hasPublic=false;
		boolean hasStatic=false;
		if (node.getReturnType2() != null && node.getReturnType2().toString().equals(className)) {
			for (ASTNode m : mods) {
				if (m.toString().equals("public")) {
					hasPublic = true;
						
				}
				
				if (m.toString().equals("static")) {
						
					hasStatic = true;
					
				}
				
			}
			
			if (hasStatic && hasPublic) {
				
				ConstructorIfChecker CIF = new ConstructorIfChecker(className);
				node.accept(CIF);
				grade = CIF.getBool();
			}
		}
		
		return false;
	}
	public boolean getGrade() {
		return grade;
	}
}
