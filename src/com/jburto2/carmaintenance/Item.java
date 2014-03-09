package com.jburto2.carmaintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;



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

	@Override
	public List<NameValuePair> getParams() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(KEY_ID, Integer.toString(id)));
        params.add(new BasicNameValuePair(KEY_ITEMDESCRIPTION, ItemDescription));
        params.add(new BasicNameValuePair(KEY_ITEMMILEAGEINTERVAL, Integer.toString(mileageInterval)));
        params.add(new BasicNameValuePair(KEY_ITEMTIMEINTERVAL, Integer.toString(timeInterval)));

		
		return params;
	}

	@Override
	public void setObjectFromJSON(JSONObject j) throws JSONException{
		// TODO Auto-generated method stub
		setID(Integer.parseInt(j.getString(KEY_ID)));
		setItemDescription(j.getString(KEY_ITEMDESCRIPTION));
		setMileageInterval(Integer.parseInt(j.getString(KEY_ITEMMILEAGEINTERVAL)));
		setTimeInterval(Integer.parseInt(j.getString(KEY_ITEMTIMEINTERVAL)));
		
	}

	@Override
	public void update(DatabaseHandler db) {
		// TODO Auto-generated method stub
		db.updateItem(this);
		
	}

	@Override
	public void add(DatabaseHandler db) throws Exception {
		// TODO Auto-generated method stub
		db.addItem(this);
		
	}

	@Override
	public void delete(DatabaseHandler db) {
		// TODO Auto-generated method stub
		db.deleteItem(this);
		
	}

	@Override
	public Item selectByID(DatabaseHandler db) throws Exception {
		// TODO Auto-generated method stub
		return db.getItemById(id);
		
	}



	@Override
	public List<DatabaseObject> selectAll(DatabaseHandler db) {
		// TODO Auto-generated method stub
		return db.getAllItems();
		
	}
	
	@Override
	public String getTableName()
	{
		return TABLE_NAME;
	}
	
}
