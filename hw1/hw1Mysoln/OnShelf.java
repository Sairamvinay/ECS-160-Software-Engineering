package hw1;

public class OnShelf implements LBState {
	
	private static OnShelf secret_inst = null;
	private OnShelf() {
		Logger.write("Q2&3Log.txt", toString() + " Instance Created", false);
		
	}
	
	public static OnShelf getInst() {
		if (secret_inst == null) {
			secret_inst = new OnShelf();
			return secret_inst;
		}
		
		return secret_inst;
		
	}
	@Override
	public void shelf(LibraryBook lb) throws BadOperationException{
		// TODO Auto-generated method stub
		throw new BadOperationException("Can't use shelf in "+toString()+ " State");
	}

	@Override
	public void issue(LibraryBook lb) {
		// TODO Auto-generated method stub
		
		
		LBState new_state = Borrowed.getInst();
		Logger.write("Q2&3Log.txt", "Leaving State " +toString() + " for State Borrowed", true);
		lb.changeState(new_state);
		
	}

	@Override
	public void extend(LibraryBook lb) throws BadOperationException {
		// TODO Auto-generated method stub
		throw new BadOperationException("Can't use extend in " +  toString() + " state");

	}

	@Override
	public void returnIt(LibraryBook lb) throws BadOperationException {
		// TODO Auto-generated method stub
		throw new BadOperationException("Can't use returnIt in " +  toString() + " state");
	}
	
	public String toString(){
		return "OnShelf";
	}

}
