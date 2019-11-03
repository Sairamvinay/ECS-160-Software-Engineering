package hw1;

public interface Subject {
	public void attach(Observer o);
	public void detach(Observer o);
	public void Notify();
	public String toString();
	public String getState();
}
