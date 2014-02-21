package com.jburto2.carmaintenance;



public class Work extends DatabaseObject
{
	
	public static final String TABLE_NAME="Work";
	public static final String KEY_ID = "idWork";
	public static final String KEY_VEHICLE_IDVEHICLE = "Vehicle_idVehicle";
	public static final String KEY_ITEMS_IDITEMS = "Items_idItem";
	public static final String KEY_RECIEPT_IDRECEIPT = "Receipt_idReceipt";
	public static final String KEY_WORKNOTES = "WorkNotes";
	
	private String WorkNotes;
	private int idVehicle;
	private int idItem;
	private int idReceipt;
	
	
	public Work()
	{
	
		this(0,0,0,0,null);
				
	}
	
	public Work(int id, int vehicle_id, int item_id, int receipt_id, String notes)
	{
		this.id = id;
		this.idVehicle = vehicle_id;
		this.idItem = item_id;
		this.idReceipt = receipt_id;
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
}
