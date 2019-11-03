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
		isGradeA = false;
		scoreGradeB = 0;
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
		CompilationUnit cu1 = fileParser(contextPath);
		CompilationUnit cu2 = fileParser(abstractPath);
		MethodCounter mc1 = new MethodCounter();
		MethodCounter mc2 = new MethodCounter();
		CheckFields CF = new CheckFields(abstractName);
				
		cu1.accept(mc1);
		cu2.accept(mc2);
		cu1.accept(CF);
		
		String VarName = CF.getVarName();
		Grade2B GradeB = new Grade2B(abstractName,VarName,mc2.getMethods());
		
		cu1.accept(GradeB);
		
		Grade2A GradeA = new Grade2A();
		
		Inst.isGradeA = GradeA.getGrade(mc1.getMethods(),mc2.getMethods());
		Inst.scoreGradeB = GradeB.getCount();
		
		
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
