package hw1;

import java.util.HashMap; 

public class SourceObserver implements Observer{
	
	
	private HashMap<Subject, String> subjects = new HashMap<Subject, String>();
	private String name;
	public SourceObserver(String Oname){
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
		
		Logger.write("Q2&3Log.txt",toString() + " OBSERVED "+ s.toString() + " LEAVING STATE: "+ subjects.get(s),true);
		subjects.replace(s,subjects.get(s),s.getState());
	}
	
	
	//Create add subject and remove subject methods
	@Override
	public boolean equals(Observer o) {
		
		if (!(o instanceof SourceObserver)) {
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
