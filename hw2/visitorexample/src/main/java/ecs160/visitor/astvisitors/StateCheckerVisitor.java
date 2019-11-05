package ecs160.visitor.astvisitors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;


import ecs160.visitor.utilities.UtilReader;

public class StateCheckerVisitor {

	private boolean isGradeA;
	private int scoreGradeB;
	
	private StateCheckerVisitor() {
		;
	}
	
	
	private static CompilationUnit fileParser(String sourceFile) {
		
		File file = new File(sourceFile);
		String text = "";
		try {
			text = UtilReader.read(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ASTParser parser = ASTParser.newParser(AST.JLS12); //Create a parser for a version of the Java language (12 here)
    	Map<String, String> options = JavaCore.getOptions(); //get the options for a type of Eclipse plugin that is the basis of Java plugins
    	options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_12); //Specify that we are on Java 12 and add it to the options...
    	parser.setCompilerOptions(options); //forward all these options to our parser
    	parser.setKind(ASTParser.K_COMPILATION_UNIT); //What kind of constructions will be parsed by this parser.  K_COMPILATION_UNIT means we are parsing whole files.
    	parser.setResolveBindings(true); //Enable looking for bindings/connections from this file to other parts of the program.
    	parser.setBindingsRecovery(true); //Also attempt to recover incomplete bindings (only can be set to true if above line is set to true).
    	String[] classpath = { System.getProperty("java.home") + "/lib/rt.jar" }; //Link to your Java installation.
    	parser.setEnvironment(classpath, new String[] { "" }, new String[] { "UTF-8" }, true);
    	parser.setSource(text.toCharArray()); //Load in the text of the file to parse.
    	parser.setUnitName(file.getAbsolutePath()); //Load in the absolute path of the file to parse
    	CompilationUnit cu = (CompilationUnit) parser.createAST(null); //Create the tree and link to the root node.
    	return cu;
	}
	
	public static StateCheckerVisitor setUpGrader(String contextPath, String contextName, String abstractPath, String abstractName) {
		// TODO Auto-generated method stub
		
		StateCheckerVisitor Inst = new StateCheckerVisitor();
		//2 CU units needed
		CompilationUnit contextVisitor = fileParser(contextPath); 
		CompilationUnit abstractVisitor = fileParser(abstractPath);	
		
		
		MethodCounter contextMethodCounter = new MethodCounter(); //a visitor to count to all methods inside context
		MethodCounter abstractMethodCounter = new MethodCounter();//a visitor to count to all methods inside abstract
		
		CheckFields CF = new CheckFields(abstractName);	//This visitor extracts all variables of abstractName inside contextClass
				
		contextVisitor.accept(contextMethodCounter);
		abstractVisitor.accept(abstractMethodCounter);
		
		contextVisitor.accept(CF);
		
		
		Grade2B GradeB = new Grade2B(CF.getVarNames(),abstractMethodCounter.getMethods()); //Create an instance of a visitor to calculate Grade B
		
		contextVisitor.accept(GradeB); //visit context Class to look into all its methods
		
		Grade2A GradeA = new Grade2A(); //not a visitor, it checks if intersection of abstract and context class methods is abstract class methods
		
		Inst.isGradeA = GradeA.getGrade(contextMethodCounter.getMethods(),abstractMethodCounter.getMethods()); //pass 2 lists of string of method names to find intersection
		Inst.scoreGradeB = GradeB.getCount(); //get the number of matching correct calls
		
		
		return Inst;
	}



	public boolean gradeA() {
		// TODO Auto-generated method stub
		return isGradeA;
	}

	public int gradeB() {
		// TODO Auto-generated method stub
		return scoreGradeB;
	}

}
