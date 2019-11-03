package ecs160.visitor.astvisitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodInvocation;

public class CorrectCaller extends ASTVisitor {
	
	private String abstractName;
	private String methodName;
	private String VarName;
	private boolean foundMatch;
	public CorrectCaller(String Cname,String methodName,String Vname) {
		abstractName = Cname;
		this.methodName = methodName;
		VarName = Vname;
		
	}
	
	@Override
	public boolean visit(MethodInvocation node) {
		if (node != null) {
			
			
			if (methodName.equals(node.getName().toString())) {
				
				
				
				
//				System.out.println(methodName + " is the method name");
//				System.out.println("The method is called by " + node.getExpression().toString());
//				System.out.println("The variable name I need to check for is "+ VarName);
				
				if (node.getExpression().toString().equals(VarName)) {
					//System.out.println("Match Found!");
					foundMatch = true;
				}
				
			}
			
			
		}
		return false;
	}
	
	public boolean foundMatch() {
		
		return foundMatch;
	}
	
	
}
