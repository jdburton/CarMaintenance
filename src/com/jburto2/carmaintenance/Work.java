package com.jburto2.carmaintenance;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



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
	
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     *
 
    // Adding new work
    void addWork(Work work) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
       
        values.put(Work.KEY_VEHICLE_IDVEHICLE, work.getVehicleID()); // Work description
        values.put(Work.KEY_ITEMS_IDITEMS, work.getItemID()); // Work description
        values.put(Work.KEY_RECIEPT_IDRECEIPT, work.getReceiptID()); // Work description
        values.put(Work.KEY_WORKNOTES, work.getNotes()); // Work description
        
        // Inserting Row
        db.insert(Work.TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }
 
 
    // Getting single work
    Work getWorkById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        
 
        Cursor cursor = db.query(Work.TABLE_NAME, new String[] { 
        					Work.KEY_ID, 
        					Work.KEY_VEHICLE_IDVEHICLE,
        					Work.KEY_ITEMS_IDITEMS,
        					Work.KEY_RECIEPT_IDRECEIPT,
							Work.KEY_WORKNOTES  
							}, Work.KEY_ID + "=?",
                new String[] { Integer.toString(id) }, null, null, null, null);
        if (cursor != null )
        	
            cursor.moveToFirst();

        Work work = new Work(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),Integer.parseInt(cursor.getString(2)),Integer.parseInt(cursor.getString(3)),cursor.getString(4));
        // return work
        return work;
    }
    
    Work getWorkByVehicleItemReceipt(int vid,int iid, int rid) {
        SQLiteDatabase db = this.getReadableDatabase();
        
 
        Cursor cursor = db.query(Work.TABLE_NAME, new String[] { 
        					Work.KEY_ID, 
        					Work.KEY_VEHICLE_IDVEHICLE,
        					Work.KEY_ITEMS_IDITEMS,
        					Work.KEY_RECIEPT_IDRECEIPT,
							Work.KEY_WORKNOTES  
							}, 
							Work.KEY_VEHICLE_IDVEHICLE + "=? and "+Work.KEY_ITEMS_IDITEMS+"=? and "+Work.KEY_RECIEPT_IDRECEIPT+"=?",
                new String[] { Integer.toString(vid), Integer.toString(iid), Integer.toString(rid) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Work work = new Work(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1)),Integer.parseInt(cursor.getString(2)),Integer.parseInt(cursor.getString(3)),cursor.getString(4));
        // return work
        return work;
    }
     
    // Getting All Works
    public static List<DatabaseObject> getAll(DatabaseHandler dbh) {
        List<DatabaseObject> workList = new ArrayList<DatabaseObject>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Work.TABLE_NAME;
 
        SQLiteDatabase db = dbh.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Work work = new Work();
                
                work.setID(Integer.parseInt(cursor.getString(0)));
                work.setVehicleID(Integer.parseInt(cursor.getString(1)));
                work.setItemID(Integer.parseInt(cursor.getString(2)));
                work.setReceiptID(Integer.parseInt(cursor.getString(3)));
                work.setNotes(cursor.getString(4));
                // Adding work to list
                workList.add(work);
            } while (cursor.moveToNext());
        }
 
        // return work list
        return workList;
    }
 
    // Updating single work
    public int update(DatabaseHandler dbh) {
        SQLiteDatabase db = dbh.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(Work.KEY_ID, work.getID());
        values.put(Work.KEY_VEHICLE_IDVEHICLE, work.getVehicleID());
        values.put(Work.KEY_ITEMS_IDITEMS, work.getItemID());
        values.put(Work.KEY_RECIEPT_IDRECEIPT,work.getReceiptID());
        values.put(Work.KEY_WORKNOTES,work.getNotes());
 
        // updating row
        return db.update(Work.TABLE_NAME, values, Work.KEY_ID + " = ?",
                new String[] { String.valueOf(work.getID()) });
    }
 
    // Deleting single work
    public void delete(DatabaseHandler dbh) {
        SQLiteDatabase db = dbh.getWritableDatabase();
        db.delete(Work.TABLE_NAME, Work.KEY_ID + " = ?",
                new String[] { String.valueOf(work.getID()) });
        db.close();
    }
 
 
    // Getting works Count
    public int getCount(DatabaseHandler dbh) {
        String countQuery = "SELECT  * FROM " + Work.TABLE_NAME;
        SQLiteDatabase db = dbh.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
 */
}
