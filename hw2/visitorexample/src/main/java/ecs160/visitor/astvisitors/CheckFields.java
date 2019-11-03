package ecs160.visitor.astvisitors;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;


public class CheckFields extends ASTVisitor{
	private String abstractName;
	private String VarName;
	public CheckFields(String abstractName) {
		this.abstractName = abstractName;
	}
	
	@Override
	public boolean visit(FieldDeclaration node) {
		
		if (node.getType().toString().equals(abstractName)) {
			
			for (Object frag: node.fragments()){
	
				VarName = frag.toString();
			}
			
		}
		return false;
	}
	
	public String getVarName() {
		return VarName;
	}
}
