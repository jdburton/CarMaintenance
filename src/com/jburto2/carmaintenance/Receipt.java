
package com.jburto2.carmaintenance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;



public class Receipt extends DatabaseObject
{
	
	public static final String TABLE_NAME="Receipt";
	public static final String KEY_ID = "idReceipt";
	public static final String KEY_RECEIPTFILE= "ReceiptFile";
	public static final String KEY_LOCATION_IDLOCATION = "Location_idLocation";
	public static final String KEY_RECEIPTDATE = "ReceiptDate";
	public static final String KEY_RECEIPTAMOUNT = "ReceiptAmount";
	public static final String KEY_RECEIPTNOTES = "ReceiptNotes";

	
	
	private String ReceiptFile;
	private int ReceiptAmount;
	private String ReceiptDate;
	
	private int Location_id;
	private String ReceiptNotes;
	
	
	public Receipt()
	{
	
		this(0,null,0,null,0,null);
				
	}
	
	public Receipt(String file, int location_id, String date, int amount, String notes)
	{
		this(0,file, location_id, date, amount,  notes);
	}
	public Receipt(int id, String file, int location_id, String date, int amount, String notes)
	{
		this.id = id;
		this.Location_id = location_id;
		
		this.ReceiptDate = date;
		this.ReceiptAmount = amount;
		this.ReceiptNotes = notes;
		this.ReceiptFile = file;		
	}
	
	public void setFile(String file)
	{
		this.ReceiptFile=file;
	}
	
	public String getFile()
	{
		return this.ReceiptFile;
	}
	
	

	public void setDate(String date)
	{
		this.ReceiptDate = date;
	}
	
	public String getDate()
	{
		return this.ReceiptDate;
	}
	
	public void setAmount(int amount)
	{
		this.ReceiptAmount = amount;
	}
	
	public int getAmount()
	{
		return this.ReceiptAmount;
	}
	public void setNotes(String notes)
	{
		this.ReceiptNotes = notes;
	}
	
	public String getNotes()
	{
		return this.ReceiptNotes;
	}
	public void setLocationID(int id)
	{
		this.Location_id = id;
	}
	public int getLocationID()
	{
		return this.Location_id;
	}
	
	@Override
	public List<NameValuePair> getParams() {
		// TODO Auto-generated method stub
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(KEY_ID, Integer.toString(id)));
        params.add(new BasicNameValuePair(KEY_RECEIPTFILE, ReceiptFile));
        params.add(new BasicNameValuePair(KEY_LOCATION_IDLOCATION, Integer.toString(Location_id)));
        params.add(new BasicNameValuePair(KEY_RECEIPTDATE, ReceiptDate));
        params.add(new BasicNameValuePair(KEY_RECEIPTAMOUNT, Integer.toString(ReceiptAmount)));
        params.add(new BasicNameValuePair(KEY_RECEIPTNOTES, ReceiptNotes));
		
		return params;
	}

	@Override
	public void setObjectFromJSON(JSONObject j) throws JSONException{
		// TODO Auto-generated method stub
		setID(Integer.parseInt(j.getString(KEY_ID)));
		setFile(j.getString(KEY_RECEIPTFILE));
		setLocationID(Integer.parseInt(j.getString(KEY_LOCATION_IDLOCATION)));
		setDate(j.getString(KEY_RECEIPTDATE));
		setAmount(Integer.parseInt(j.getString(KEY_RECEIPTAMOUNT)));
		setNotes(j.getString(KEY_RECEIPTNOTES));
		
	}

	@Override
	public void update(DatabaseHandler db) {
		// TODO Auto-generated method stub
		db.updateReceipt(this);
		
	}

	@Override
	public void add(DatabaseHandler db) throws Exception {
		// TODO Auto-generated method stub
		db.addReceipt(this);
		
	}

	@Override
	public void delete(DatabaseHandler db) {
		// TODO Auto-generated method stub
		db.deleteReceipt(this);
		
	}

	@Override
	public DatabaseObject selectByID(DatabaseHandler db) throws Exception {
		// TODO Auto-generated method stub
		return db.getReceiptById(id);
	}

	@Override
	public List<DatabaseObject> selectAll(DatabaseHandler db) {
		// TODO Auto-generated method stub
		return db.getAllReceipts();
	}
	
	
	@Override
	public String getTableName()
	{
		return TABLE_NAME;
	}

	
	
}
