package classes;

public class GermanShepherd extends Dog {
	

	public GermanShepherd(String n) {
		super(n); //SUPER_CONSTRUCTOR_INVOCATION

	}
	
	@Override
	public void move()
	{
		System.out.println(getName() + " runs around in circles.");
	}
	
	@Override
	protected String getName()
	{
		//Why doesn't method invocation call this one?
		return super.getName() + ", the german shepherd,";
	}
	
//	  //Final methods are non-virtual and cannot be overridden.
//    public final void bark()
//    {
//    		System.out.println("Woof");
//    }
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj == this)
		{
			return true;
		}
		else if(obj == null)
		{
			return false;
		}
		else
		{
			System.out.println("continuing");
		}
		
		if(!(obj instanceof GermanShepherd))
		{
			return false;
		}
		
		GermanShepherd other = (GermanShepherd)obj;
		
		return other.getName().equals(this.getName());
		
	}
	
	@Override
	public int hashCode()
	{
		return this.getName().hashCode();
	}

}
