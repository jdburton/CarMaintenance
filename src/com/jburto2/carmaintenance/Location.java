package com.jburto2.carmaintenance;



public class Location extends DatabaseObject
{
	
	public static final String TABLE_NAME = "Location";
	public static final String KEY_ID = "idLocation";
	public static final String KEY_LOCATIONDESCRIPTION = "LocationDescription";
	
	

	private String LocationDescription;
	
	public Location()
	{
	
		this(0,null);
				
	}
	
	public Location(int id,String description)
	{
		this.id = id;
		this.LocationDescription=description;
				
	}
	
	public void setLocationDescription(String description)
	{
		this.LocationDescription=description;
	}
	
	public String getLocationDescription()
	{
		return this.LocationDescription;
	}
	
	

}
