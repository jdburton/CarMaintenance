package com.jburto2.carmaintenance;



public abstract class DatabaseObject 
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
