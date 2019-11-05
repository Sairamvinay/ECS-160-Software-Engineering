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

public class SingletonCheckerVisitor {
	
	private boolean isGradeA;
	private boolean isGradeB;
	private boolean isGradeC;
	private boolean isGradeD;
	
	private SingletonCheckerVisitor() {
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
	
	public static SingletonCheckerVisitor setUpGrader(String sourceFile, String className) {
		// TODO Auto-generated method stub
	    SingletonCheckerVisitor Inst = new SingletonCheckerVisitor();
		
    	CompilationUnit cu = fileParser(sourceFile);
    	Grade1A gradeA = new Grade1A(className);
    	Grade1B gradeB = new Grade1B(className);
    	Grade1C gradeC = new Grade1C(className);
    	Grade1D gradeD = new Grade1D(className);
  	
  	
    	cu.accept(gradeA);
    	cu.accept(gradeB);
    	cu.accept(gradeC);
    	cu.accept(gradeD);
    	
    	
    	Inst.isGradeA = gradeA.getGrade();
    	Inst.isGradeB = gradeB.getGrade();
    	Inst.isGradeC = gradeC.getGrade();
    	
    	Inst.isGradeD = gradeD.getGrade() && gradeA.getGrade() && gradeB.getGrade(); 
    	//grade is D is true iff public static method returning className type (gradeB),
    	//the class has a private constructor (gradeA)
    	//checks if only one call which is inside the if statement inside public static method.
    	
    	return Inst;
    	
	}

	public boolean gradeA() {
		// TODO Auto-generated method stub
		return isGradeA;
	}

	public boolean gradeB() {
		// TODO Auto-generated method stub
		return isGradeB;
	}

	public boolean gradeC() {
		// TODO Auto-generated method stub
		return isGradeC;
	}

	public boolean gradeD() {
		// TODO Auto-generated method stub
		return isGradeD;
	}

}
