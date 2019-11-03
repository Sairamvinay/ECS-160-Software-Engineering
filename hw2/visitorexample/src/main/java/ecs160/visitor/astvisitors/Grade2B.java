package ecs160.visitor.astvisitors;


import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class Grade2B extends ASTVisitor{
	
	
	private String abstractClass;
	private String VarName;
	private int countCorrect;
	public Grade2B(String Cname,String Vname) {
		abstractClass = Cname;
		VarName = Vname;
		
	}
	
	@Override
	public boolean visit(MethodDeclaration node) {
		if (node != null) {
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
