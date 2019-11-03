package hw1;
import java.io.*;
public class Logger {
	public Logger() {
		;
	}
	public static void write(String filename,String message,boolean to_append) {
		
		try {
			File file = new File(filename);
			BufferedWriter buffer = new BufferedWriter(new FileWriter(filename,to_append));
			if (!file.exists()) {
				file.createNewFile();
			}
			buffer.write(message + '\n');
			buffer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: Exception Occured\n");
			e.printStackTrace();
		}
		
	}
}
