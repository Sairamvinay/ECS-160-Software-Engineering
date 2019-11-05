package ecs160.visitor.astvisitors;


import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class Grade2B extends ASTVisitor{
	
	
	private List<String> VarNames;
	private int countCorrect;
	private List<String> abstractMethods;
	
	public Grade2B(List<String> Vnames,List<String> Amethods) {
		
		VarNames = Vnames;
		abstractMethods = Amethods;
		
	}
	
	@Override
	public boolean visit(MethodDeclaration node) {
		
		//if the method is not null and the Context method's name is inside list of all method names of abstractClass
		if (node != null && abstractMethods.contains(node.getName().toString())) {
			
			
			//Visit correct caller class which checks the abstractClass name and also the name of the current method visited and also the list of variable names
			//this visitor looks for all the method invocations inside the current method which is also present in the abstractClass
			CorrectCaller CC = new CorrectCaller(node.getName().toString(),VarNames);
			node.accept(CC);
			
			if (CC.foundMatch()) {
				//if there is a found match, then increment the number of correct matching calls
				countCorrect++;
			}
			 
		}
		
		return false;
		
	}
	
	public int getCount() {
		return countCorrect;
	}
	

}
