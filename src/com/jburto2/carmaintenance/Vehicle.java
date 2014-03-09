package com.jburto2.carmaintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;



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
	
	@Override
	public List<NameValuePair> getParams() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(KEY_ID, Integer.toString(id)));
        params.add(new BasicNameValuePair(KEY_VEHICLEDESCRIPTION, VehicleDescription));


		
		return params;
	}

	@Override
	public void setObjectFromJSON(JSONObject j) throws JSONException{
		// TODO Auto-generated method stub
		setID(Integer.parseInt(j.getString(KEY_ID)));
		setVehicleDescription(j.getString(KEY_VEHICLEDESCRIPTION));
		
	}

	@Override
	public void update(DatabaseHandler db) {
		// TODO Auto-generated method stub
		db.updateVehicle(this);
		
	}

	@Override
	public void add(DatabaseHandler db) throws Exception {
		// TODO Auto-generated method stub
		db.addVehicle(this);
		
	}

	@Override
	public void delete(DatabaseHandler db) {
		// TODO Auto-generated method stub
		db.deleteVehicle(this);
		
	}

	@Override
	public DatabaseObject selectByID(DatabaseHandler db) throws Exception {
		// TODO Auto-generated method stub
		return db.getVehicleById(id);
	}

	@Override
	public List<DatabaseObject> selectAll(DatabaseHandler db) {
		// TODO Auto-generated method stub
		return db.getAllVehicles();
	}
	
	
	@Override
	public String getTableName()
	{
		return TABLE_NAME;
	}



}
