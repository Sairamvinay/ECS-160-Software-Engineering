package hw1;

public interface LBState {
	public void shelf(LibraryBook lb) throws BadOperationException;
	public void issue(LibraryBook lb) throws BadOperationException;
	public void extend(LibraryBook lb) throws BadOperationException;
	public void returnIt(LibraryBook lb) throws BadOperationException ;
}