package com.jburto2.carmaintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;



public class Location extends DatabaseObject
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1408685571100251339L;
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
	

	@Override
	public List<NameValuePair> getParams() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(KEY_ID, Integer.toString(id)));
        params.add(new BasicNameValuePair(KEY_LOCATIONDESCRIPTION, LocationDescription));


		
		return params;
	}

	@Override
	public void setObjectFromJSON(JSONObject j) throws JSONException{
		// TODO Auto-generated method stub
		setID(Integer.parseInt(j.getString(KEY_ID)));
		setLocationDescription(j.getString(KEY_LOCATIONDESCRIPTION));
		
	}

	@Override
	public void update(DatabaseHandler db) {
		// TODO Auto-generated method stub
		db.updateLocation(this);
		
	}

	@Override
	public void add(DatabaseHandler db) throws Exception {
		// TODO Auto-generated method stub
		db.addLocation(this);
		
	}

	@Override
	public void delete(DatabaseHandler db) {
		// TODO Auto-generated method stub
		db.deleteLocation(this);
		
	}

	@Override
	public DatabaseObject selectByID(DatabaseHandler db) throws Exception {
		// TODO Auto-generated method stub
		return db.getLocationById(id);
	}

	@Override
	public List<DatabaseObject> selectAll(DatabaseHandler db) {
		// TODO Auto-generated method stub
		return db.getAllLocations();
	}
	
	
	@Override
	public String getTableName()
	{
		return TABLE_NAME;
	}


	

}
