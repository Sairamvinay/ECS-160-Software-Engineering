package ecs160.visitor.astvisitors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class MethodCounter extends ASTVisitor {
	
	List <String> methods = new ArrayList<String>();
	
	
	public MethodCounter() {
		;
	}
	
	@Override
	public boolean visit(MethodDeclaration node) {
		
	
		if (node != null){
			
			
			String methName = node.getName().toString();
			methods.add(methName);
			
		}
		return false;
	}
	
	public List<String> getMethods(){
		
		
		return methods;
	}
 
}
