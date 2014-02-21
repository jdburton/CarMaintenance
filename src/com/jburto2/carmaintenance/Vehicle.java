package com.jburto2.carmaintenance;



public class Vehicle extends DatabaseObject
{
	public static final String TABLE_NAME = "Vehicle";
	public static final String KEY_ID = "idVehicle";
	public static final String KEY_VEHICLEDESCRIPTION = "VehicleDescription";
	
	
	
	private String VehicleDescription;
	
	public Vehicle()
	{
	
		this(0,null);
				
	}
	
	public Vehicle(int id,String description)
	{
		this.id = id;
		this.VehicleDescription=description;
				
	}
	
	public void setVehicleDescription(String description)
	{
		this.VehicleDescription=description;
	}
	
	public String getVehicleDescription()
	{
		return this.VehicleDescription;
	}
	

}
