package hw1;
//import java.io.*;
//look into getting the files written
public class PrintManager {
	private static PrintManager secret_inst = null;
	
	private PrintManager() {
		Logger.write("Q1Log.txt","Instance Created",false);

	}
	
	public static PrintManager ThePrintManager() {
		if (secret_inst == null) {
			secret_inst = new PrintManager();
			return secret_inst;
		}
		
		Logger.write("Q1Log.txt", "Previously Created instance returned",true);
		
		return secret_inst;
		
	}
}