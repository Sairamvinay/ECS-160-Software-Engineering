package hw1;

import java.util.HashMap;


public class DestObserver implements Observer{
	
	private String name;
	private HashMap<Subject, String> subjects = new HashMap<Subject, String>();
	public DestObserver(String Oname) {
		name = Oname;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
	
	@Override
	public void Update(Subject s) {
		// TODO Auto-generated method stub
		if (s.getState() == subjects.get(s)) {
			return;
		}
		Logger.write("Q2&3Log.txt",toString() + " OBSERVED "+ s.toString() + " REACHING STATE: "+ s.getState(),true);
		subjects.replace(s,subjects.get(s),s.getState());
	}

	
	@Override
	public boolean equals(Observer o) {
		
		if (!(o instanceof DestObserver)) {
			return false;
		}
		if (o.toString() == name && this == o) {
			return true;
		}
		
		return false;
		
	}
	
	@Override
	public void addSubject(Subject s) {
		subjects.put(s, "UNOBSERVED");
	}
	@Override
	public void remSubject(Subject s) {
		subjects.remove(s);
	}
	
}
