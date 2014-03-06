
package com.jburto2.carmaintenance;

import java.util.Date;



public class Receipt extends DatabaseObject
{
	
	public static final String TABLE_NAME="Receipt";
	public static final String KEY_ID = "idReceipt";
	public static final String KEY_RECEIPTFILE= "ReceiptFile";
	public static final String KEY_LOCATION_IDLOCATION = "Location_idLocation";
	public static final String KEY_RECEIPTDATE = "ReceiptDate";
	public static final String KEY_RECEIPTAMOUNT = "ReceiptAmount";
	public static final String KEY_RECEIPTMILEAGE = "ReceiptMileage";
	public static final String KEY_RECEIPTNOTES = "ReceiptNotes";
	
	private String ReceiptFile;
	private int ReceiptAmount;
	private String ReceiptDate;
	private int ReceiptMileage;
	private int Location_id;
	private String ReceiptNotes;
	
	
	public Receipt()
	{
	
		this(0,null,0,null,0,0,null);
				
	}
	
	public Receipt(String file, int location_id, String date, int amount, int mileage, String notes)
	{
		this(0,file, location_id, date, amount, mileage,  notes);
	}
	public Receipt(int id, String file, int location_id, String date, int amount, int mileage, String notes)
	{
		this.id = id;
		this.Location_id = location_id;
		this.ReceiptMileage = mileage;
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
	
	
	public void setMileage(int mileage)
	{
		this.ReceiptMileage = mileage;
	}
	
	public int getMileage()
	{
		return this.ReceiptMileage;
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
	
}
