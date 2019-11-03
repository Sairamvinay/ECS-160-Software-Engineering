package hw1;

import java.util.ArrayList;
import java.util.List;

public class LibraryBook implements Subject{
	
	private LBState currState;
	private String name;
	private List<Observer> observers = new ArrayList<Observer>();
	
	
	public LibraryBook(String Bname) {
		
		currState = OnShelf.getInst();
		name = Bname;
	}
	
	
	public void shelf(){
		try {
			currState.shelf(this);
	
		}catch (BadOperationException e){
			;
		}
	}

	public void issue(){
		try{
			currState.issue(this);

		}catch (BadOperationException e){
			;
		}
	}
	public void extend(){
		try {
		currState.extend(this);
	
		}catch (BadOperationException e){
			;
		}
	}
	public void returnIt(){
		try {
			currState.returnIt(this);
			
		}catch (BadOperationException e){
			;
		}
		
	}
	
	public void changeState(LBState inst) {
		currState = inst;
		Notify();
	}
	
	@Override
	public void attach(Observer o) {
		// TODO Auto-generated method stub
		if (!observers.contains(o)) {
			observers.add(o);
			Logger.write("Q2&3Log.txt", o.toString() + " is now watching "+name, true);
			o.addSubject(this);
		}
	
	}


	@Override
	public void detach(Observer o) {
		// TODO Auto-generated method stub
		if (observers.contains(o)) {
			observers.remove(o);
			Logger.write("Q2&3Log.txt", o.toString() + " is no longer watching "+name, true);
			o.remSubject(this);
		}
	}


	@Override
	public void Notify() {
		// TODO Auto-generated method stub
		
		for (Observer Ob : observers) {
	         Ob.Update(this);
	      }
		
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public String getState() {
		return currState.toString();
	}
}
