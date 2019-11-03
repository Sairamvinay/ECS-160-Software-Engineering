package ecs160.visitor.astvisitors;


import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class Grade2B extends ASTVisitor{
	
	
	private String abstractClass;
	private String VarName;
	private int countCorrect;
	private List<String> methods;
	
	public Grade2B(String Cname,String Vname,List<String> methods) {
		abstractClass = Cname;
		VarName = Vname;
		this.methods = methods;
		
	}
	
	@Override
	public boolean visit(MethodDeclaration node) {
		if (node != null && methods.contains(node.getName().toString())) {
			CorrectCaller CC = (new CorrectCaller(abstractClass,node.getName().toString(),VarName));
			node.accept(CC);
			
			if (CC.foundMatch()) {
				countCorrect++;
			}
			 
		}
		
		return false;
		
	}
	
	public int getCount() {
		return countCorrect;
	}
	

}
