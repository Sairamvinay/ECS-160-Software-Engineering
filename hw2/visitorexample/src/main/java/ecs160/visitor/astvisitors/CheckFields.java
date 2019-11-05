package ecs160.visitor.astvisitors;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;


public class CheckFields extends ASTVisitor{
	private String abstractName;
	private List<String> VarNames;
	public CheckFields(String abstractName) {
		this.abstractName = abstractName;
		VarNames = new ArrayList<String>(); 
	}
	
	@Override
	public boolean visit(FieldDeclaration node) {
		
		//if the variable declared is of type abstractName
		if (node.getType().toString().equals(abstractName)) {
			
			//this runs through all the variable names of same type inside the called class
			for (Object frag: node.fragments()){
				
				VarNames.add(frag.toString());	
		
			}
			
			
		}
		return false;
	}
	
	public List<String> getVarNames() {
		return VarNames; //return list of all variable names of type abstractName
	}
}
