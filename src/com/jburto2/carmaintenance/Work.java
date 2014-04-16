package com.jburto2.carmaintenance;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;



public class Work extends DatabaseObject
{
	
	public static final String TABLE_NAME="Work";
	public static final String KEY_ID = "idWork";
	public static final String KEY_VEHICLE_IDVEHICLE = "Vehicle_idVehicle";
	public static final String KEY_ITEMS_IDITEMS = "Items_idItem";
	public static final String KEY_RECEIPT_IDRECEIPT = "Receipt_idReceipt";
	public static final String KEY_WORKMILEAGE = "WorkMileage";
	public static final String KEY_WORKNOTES = "WorkNotes";
	


	
	
	private String WorkNotes;
	private int idVehicle;
	private int idItem;
	private int idReceipt;
	private int WorkMileage;
	
	
	public Work()
	{
	
		this(0,0,0,0,0,null);
				
	}
	
	public Work(int id, int vehicle_id, int item_id, int receipt_id, int mileage, String notes)
	{
		this.id = id;
		this.idVehicle = vehicle_id;
		this.idItem = item_id;
		this.idReceipt = receipt_id;
		this.WorkMileage = mileage;
		this.WorkNotes = notes;
		
	}
	

	
	public void setNotes(String notes)
	{
		this.WorkNotes = notes;
	}
	
	public String getNotes()
	{
		return this.WorkNotes;
	}
	
	public void setVehicleID(int id)
	{
		this.idVehicle=id;
	}
	
	public int getVehicleID()
	{
		return this.idVehicle;
	}
	
	public void setItemID(int id)
	{
		this.idItem=id;
	}
	
	public int getItemID()
	{
		return this.idItem;
	}
	public void setReceiptID(int id)
	{
		this.idReceipt=id;
	}
	
	public int getReceiptID()
	{
		return this.idReceipt;
	}
	
	public void setMileage(int mileage)
	{
		this.WorkMileage = mileage;
	}
	
	public int getMileage()
	{
		return this.WorkMileage;
	}
	
	@Override
	public List<NameValuePair> getParams() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(KEY_ID, Integer.toString(id)));
        params.add(new BasicNameValuePair(KEY_VEHICLE_IDVEHICLE, Integer.toString(idVehicle)));
        params.add(new BasicNameValuePair(KEY_ITEMS_IDITEMS, Integer.toString(idItem)));
        params.add(new BasicNameValuePair(KEY_RECEIPT_IDRECEIPT, Integer.toString(idReceipt)));
        params.add(new BasicNameValuePair(KEY_WORKMILEAGE, Integer.toString(WorkMileage)));
        params.add(new BasicNameValuePair(KEY_WORKNOTES, WorkNotes));
		
		return params;
	}

	@Override
	public void setObjectFromJSON(JSONObject j) throws JSONException{
		// TODO Auto-generated method stub
		setID(Integer.parseInt(j.getString(KEY_ID)));
		setVehicleID(Integer.parseInt(j.getString(KEY_VEHICLE_IDVEHICLE)));
		setItemID(Integer.parseInt(j.getString(KEY_ITEMS_IDITEMS)));
		setReceiptID(Integer.parseInt(j.getString(KEY_RECEIPT_IDRECEIPT)));
		setMileage(Integer.parseInt(j.getString(KEY_WORKMILEAGE)));
		setNotes(j.getString(KEY_WORKNOTES));
		
	}

	@Override
	public void update(DatabaseHandler db) {
		// TODO Auto-generated method stub
		db.updateWork(this);
	}

	@Override
	public void add(DatabaseHandler db) throws Exception {
		// TODO Auto-generated method stub
		db.addWork(this);
		
	}

	@Override
	public void delete(DatabaseHandler db) {
		// TODO Auto-generated method stub
		db.deleteWork(this);
		
	}

	@Override
	public DatabaseObject selectByID(DatabaseHandler db) throws Exception {
		// TODO Auto-generated method stub
		return db.getWorkById(id);
		
	}

	@Override
	public List<DatabaseObject> selectAll(DatabaseHandler db) {
		// TODO Auto-generated method stub
		return db.getAllWorks();
	}

	
	@Override
	public String getTableName()
	{
		return TABLE_NAME;
	}
	

}
