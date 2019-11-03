package hw1;

public class GotBack implements LBState{
	
	private static GotBack secret_inst = null;
	private GotBack() {
		Logger.write("Q2&3Log.txt", toString() + " Instance Created", true);
	}
	
	public static GotBack getInst() {
		if (secret_inst == null) {
			secret_inst = new GotBack();
			return secret_inst;
		}
		
		return secret_inst;
		
	}
	@Override
	public void shelf(LibraryBook lb) {
		// TODO Auto-generated method stub
		
		LBState new_state = OnShelf.getInst();
		Logger.write("Q2&3Log.txt","Leaving State " +toString() + " for State OnShelf", true);
		lb.changeState(new_state);
		
		
	}

	@Override
	public void issue(LibraryBook lb) throws BadOperationException{
		// TODO Auto-generated method stub
		throw new BadOperationException("Can't use issue in "+ toString() +" state");
	}

	@Override
	public void extend(LibraryBook lb) throws BadOperationException{
		// TODO Auto-generated method stub
		throw new BadOperationException("Can't use extend in " +  toString() + " state");
	}

	@Override
	public void returnIt(LibraryBook lb) throws BadOperationException{
		// TODO Auto-generated method stub
		throw new BadOperationException("Can't use returnIt in " +  toString() + " state");
	}
	
	public String toString(){
		return "GotBack";
	}

}
