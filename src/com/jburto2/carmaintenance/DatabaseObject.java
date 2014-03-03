package com.jburto2.carmaintenance;



/// Class Must be serializable to pass in intent. 
/// http://www.tutorialspoint.com/java/java_serialization.htm



public abstract class DatabaseObject implements java.io.Serializable
{
	
	public static final String KEY_ID = "id";
	public static final String TABLE_NAME = "table";
	
	protected int	id;

	
	public DatabaseObject()
	{
	
		this(0);
				
	}
	
	public DatabaseObject(int id)
	{
		this.id = id;

				
	}
	

	
	public void setID(int id)
	{
		this.id=id;
	}
	
	public int getID()
	{
		return this.id;
	}
	
}
