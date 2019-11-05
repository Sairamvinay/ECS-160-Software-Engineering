package ecs160.visitor.astvisitors;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodInvocation;

public class CorrectCaller extends ASTVisitor {
	

	private String methodName;
	private List<String> VarNames;
	private boolean foundMatch;
	public CorrectCaller(String methodName,List<String> Vnames) {
		
		
		this.methodName = methodName;
		VarNames = Vnames;
		foundMatch = false;
		
	}
	
	@Override
	public boolean visit(MethodInvocation node) {
		if (node != null) {
			
			//if the current method invocation has a method name matching with the method inside ContextClass which is invoking this
			if (methodName.equals(node.getName().toString())) {
				
				//not foundMatch is to eliminate the edge case of more than one correct call inside the context class method
				//if the expression calling it (possibly the variable of abstract class) is present in the type of all abstractClass variables inside contextClass
				//in other words, if this variable's name is indeed of abstractClass type
				if (!foundMatch && VarNames.contains(node.getExpression().toString())) {
					
					foundMatch = true; //once foundMatch, it increments. 
				}
				
			}
			
			
		}
		return false;
	}
	
	public boolean foundMatch() {
		
		return foundMatch;
	}
	
	
}
