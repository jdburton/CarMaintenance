package com.jburto2.carmaintenance;



public class Item extends DatabaseObject
{
	
	public static final String TABLE_NAME = "Item";
	public static final String KEY_ID = "idItem";
	public static final String KEY_ITEMDESCRIPTION = "ItemDescription";
	public static final String KEY_ITEMMILEAGEINTERVAL = "ItemMileageInterval";
	public static final String KEY_ITEMTIMEINTERVAL =  "ItemTimeInterval";
	
	
	private String ItemDescription;
	private int mileageInterval;
	private int timeInterval;
	
	public Item()
	{
	
		this(0,null,0,0);
				
	}
	
	public Item(int id,String description, int mInterval, int tInterval)
	{
		this.id = id;
		this.ItemDescription=description;
		this.mileageInterval = mInterval;
		this.timeInterval = tInterval;
				
	}
	
	public void setItemDescription(String description)
	{
		this.ItemDescription=description;
	}
	
	public String getItemDescription()
	{
		return this.ItemDescription;
	}
	
	
	public void setMileageInterval(int interval)
	{
		this.mileageInterval = interval;
	}
	
	public int getMileageInterval()
	{
		return this.mileageInterval;
	}
	public void setTimeInterval(int interval)
	{
		this.timeInterval = interval;
	}
	
	public int getTimeInterval()
	{
		return this.timeInterval;
	}
	
}
