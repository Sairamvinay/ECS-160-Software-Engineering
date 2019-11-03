package classes;

public abstract class Animal {
	
	
	//You can have constructors in an abstract class, but you
	//cannot invoke them directly.  They will be called by the
	//subclasses that extend this class and can be used to establish
	//common fields used by all the subclasses.
	public Animal()
	{
		System.out.println("Animal constructor.");
	}
	
	public abstract void eat();
	
	public void move()
	{
		System.out.println("The animal moves.");
	}
	
	public void move(String location)
	{
		System.out.println("The animal moved to " + location);
	}


}
