package ecs160.visitor.astvisitors;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;


public class Grade1D extends ASTVisitor{
	private String className;
	private boolean grade;
	public Grade1D(String Cname) {
		className = Cname;
		
	}
	
	@Override
	public boolean visit(MethodDeclaration node) {

		@SuppressWarnings("unchecked")
		List<ASTNode> mods = (List<ASTNode>) node.modifiers();
		boolean hasPublic=false;
		boolean hasStatic=false;
		if (node.getReturnType2() != null && node.getReturnType2().toString().equals(className)) {
			//Filter the method which returns object of className type 
			for (ASTNode m : mods) {
				if (m.toString().equals("public")) {

					hasPublic = true;	//if method is public
						
				}
				
				if (m.toString().equals("static")) {
						
					hasStatic = true;	//if method is static
					
				}
				
			}
			
			if (hasStatic && hasPublic) {
				
				//if public and static method exits of returning object of className type
				IfChecker CIF = new IfChecker(className); //Check if constructor call inside an if statement
				ConstructorCaller CC = new ConstructorCaller(className);	//Check if numCalls to Constructor is 1 (call only once condition) inside the entire public static method	(need to avoid case when call is also outside the IF statement)			
				node.accept(CIF);	//checks for IF statement and constructor calls inside IF
				node.accept(CC); //accepts only ClassInstanceCreation node, to check how many constructor calls
				grade = CIF.getBool() && CC.getBool();	//true only if call inside IF statement and only one call throughout this method which is inside IF
			}
		}
		
		return false;
	}
	
	public boolean getGrade() {
		return grade;
	}
}
