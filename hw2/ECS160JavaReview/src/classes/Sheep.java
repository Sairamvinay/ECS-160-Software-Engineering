package classes;

public class Sheep extends Animal implements Walker, Herbivore{
	
	private static int sheepCount = 0;
	
	public static int countSheep()
	{
		return sheepCount;
	}
	
	public Sheep()
	{
		sheepCount += 1;
	}

	@Override
	public void consumePlant() {
		System.out.println("The sheep ate a plant.");	
	}

	@Override
	public void move(String location)
	{
		walk(location);
	}
	
	@Override
	public void walk(String location) {
		System.out.println("The sheep walked to " + location + ".");
		
	}

	@Override
	public void eat() {
		consumePlant();
		
	}

}
