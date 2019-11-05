package ecs160.visitor.astvisitors;

import java.util.List;

public class Grade2A {
	public Grade2A() {
		;
	}
	
	//Accepts 2 list of strings of method names
	public boolean getGrade(List <String> contextMethods,List <String> abstractMethods) {
		
		//look through all abstract class methods
		for (String methName : abstractMethods) {
			
			//if any of abstract method not inside context class, return false (straight away since not a proper subset)
			if (!contextMethods.contains(methName)) {
				
				return false;
			}
		}
		
		//if all checks pass, return true
		return true;
	}
	
}
