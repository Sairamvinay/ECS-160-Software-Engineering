package classes;

public class Dog extends Animal implements Walker{
	
	private String name;
	
	public Dog(String n)
	{
		name = n;
	}

	@Override
	public void eat() {
		System.out.println(name + " ate some dog food.");
		
	}
	
	@Override
	public void move(String location)
	{
		walk(location);
	}
	
	@Override
    public void walk(String location)
    {
    		System.out.println(name + " walked on four legs to " + location + ".");
    }
    
    protected String getName()
    {
    		return name;
    }
    
    public final void bark()
    {
    		System.out.println("Woof");
    }




}
