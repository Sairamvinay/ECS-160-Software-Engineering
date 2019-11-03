package hw1;

public class Borrowed implements LBState{
	
	private static Borrowed secret_inst = null;
	private Borrowed() {
		Logger.write("Q2&3Log.txt", toString() + " Instance Created", true);
	}
	
	public static Borrowed getInst() {
		if (secret_inst == null) {
			secret_inst = new Borrowed();
			return secret_inst;
		}
		
		return secret_inst;
		
	}
	@Override
	public void shelf(LibraryBook lb) throws BadOperationException {
		// TODO Auto-generated method stub
		throw new BadOperationException("Can't use shelf in "+toString()+ " State");
	}

	@Override
	public void issue(LibraryBook lb) throws BadOperationException {
		// TODO Auto-generated method stub
		throw new BadOperationException("Can't use issue in "+toString()+ " State");
	}

	@Override
	public void extend(LibraryBook lb) {
		// TODO Auto-generated method stub
		
		LBState new_state = Borrowed.getInst();
		Logger.write("Q2&3Log.txt","Leaving State " +toString() + " for State Borrowed",true);
		lb.changeState(new_state);
		
		
	}

	@Override
	public void returnIt(LibraryBook lb) {
		// TODO Auto-generated method stub
		
		
		LBState new_state = GotBack.getInst();
		Logger.write("Q2&3Log.txt","Leaving State " +toString() + " for State GotBack",true);
		lb.changeState(new_state);
		
		
	}
	
	public String toString(){
		return "Borrowed";
	}

	
}
