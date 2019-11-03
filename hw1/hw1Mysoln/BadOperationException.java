package hw1;

@SuppressWarnings("serial")
public class BadOperationException extends Exception{
	public BadOperationException(String error) {

		Logger.write("Q2&3Log.txt", "hw1.BadOperationException: "+ error, true);
	}
}
