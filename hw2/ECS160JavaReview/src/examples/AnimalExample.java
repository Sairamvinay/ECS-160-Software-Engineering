package examples;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import classes.*;

public class AnimalExample {
	
	
	public static void main(String args[])
	{
		//Not allowed
		//Animal a1 = new Animal();
		
		System.out.println("There are " + Sheep.countSheep() + " sheep.");
		
		Animal a2 = new Dog("Max");
		Animal a3 = new Sheep();
		Animal a4 = new GermanShepherd("Maggie");
		
		Dog d1 = new Dog("Charlie");
		Sheep s1 = new Sheep();
		GermanShepherd gs1 = new GermanShepherd("Lucy");
		
		Walker w1 = gs1;
		//Herbivore h1 = d1; //Dogs are not herbivores
		Walker w2 = new Dog("Charlie");
		Walker w3 = new GermanShepherd("Lucy");
		
		System.out.println("There are " + Sheep.countSheep() + " sheep.");
		
		System.out.println("------------------Animal/Dog----------------------");
		a2.eat();
		a2.move();
		//a2.walk(); //Animal doesn't implement Walker
		a2.move("a bench");
		
		
		System.out.println("------------------Animal/Sheep----------------------");
		a3.eat();
		a3.move();
		//a3.consumePlant(); //Animal doesn't implement Herbivore
		a3.move("the pasture");
		
		System.out.println("------------------Animal/GermanShepherd----------------------");
		a4.eat();
		a4.move();
		a4.move("the pasture");
		
		System.out.println("------------------Dog/Dog----------------------");
		d1.eat();
		d1.move();
		d1.walk("the park");
		d1.move("a bench");
		//System.out.println(d1.getName()); //Error Can't access protected method outside of class/package
		
		System.out.println("------------------Sheep/Sheep----------------------");
		s1.eat();
		s1.move();
		s1.consumePlant();
		s1.move("the pasture");
		
		System.out.println("------------------GermanShepherd/GermanShepherd----------------------");
		gs1.eat();
		gs1.move();
		gs1.move("the pasture");
		
		System.out.println("------------------Walker/GermanShepherd----------------------");
		
		w1.walk("the pasture");
		//w1.eat();  //Walker only understands the types defined in the interface.
		
		System.out.println("------------------Walker/Dog----------------------");
		
		w2.walk("the pasture");
		
		//All classes inherit from the Object class.  The default Object equality method
		//compares object instances.  You can override this, but should also override
		//toString() and hashcode().
		System.out.println("------------------Equality----------------------");
		
		int i = 4;
		int j = 4;
		int k = 5;
		System.out.println(i == j);
		System.out.println(i == k);
		
		System.out.println("d1 == w2: " + (d1 == w2)); // == for objects compares if they refer to the same instance.
		System.out.println("a2 equals a2: " + (a2.equals(a2))); 
		System.out.println("d1 equals w2: " + (d1.equals(w2))); //Default equality from Object still compares instances of object
		
		System.out.println("w1 == gs1: " + (w1 == gs1)); 
		System.out.println("gs1 == w3: " + (gs1 == w3));
		System.out.println("gs1 equals null: " + (gs1.equals(null)));
		System.out.println("gs1 equals w3: " + (gs1.equals(w3))); //Replace with our own equality method
		
		//Extended Demo
		//Data structures
		//Using templates.
		System.out.println("------------------Data Structures----------------------");

		List noTemplate = new ArrayList();
		noTemplate.add(d1);
		noTemplate.add(gs1);
		//Integer runTimeError = (Integer)noTemplate.get(0);
		for(Object o : noTemplate) //Cannot Assign to Walker because we haven't used templates, 
		{
			Walker w = (Walker)o;
			w.walk("the left");
		}
		
		List<Walker> walkers = new ArrayList<Walker>();
		//walkers.add(a2); //Can't add because animal doesn't implement walker, even though Dog does.
		walkers.add(d1);
		walkers.add(gs1);
		for(Walker a : walkers)
		{
			a.walk("the right");
		}
		
		//Java can also infer the type
		Set<Walker> walkers2 = new HashSet<>();
		walkers2.add(w3);
		walkers2.add(gs1);
		//If we don't override hashcode this is 2, instead of 1
		System.out.println(walkers2.size()); 
		for(Walker a : walkers2)
		{
			a.walk("the beach");
		}
	}

}
