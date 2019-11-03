package hw1;

public interface Observer {
	
	public void Update(Subject s);
	public String toString();
	public boolean equals(Observer o);
	public void addSubject(Subject s);
	public void remSubject(Subject s);
}
