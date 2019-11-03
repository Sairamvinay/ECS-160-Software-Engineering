package ecs160.visitor.astvisitors;

import java.util.List;

public class Grade2A {
	public Grade2A() {
		;
	}
	
	public boolean getGrade(List <String> contextMethods,List <String> abstractMethods) {
		
		

		for (String methName : abstractMethods) {
			
			
			if (!contextMethods.contains(methName)) {
				
				return false;
			}
		}
		
		return true;
	}
	
}
