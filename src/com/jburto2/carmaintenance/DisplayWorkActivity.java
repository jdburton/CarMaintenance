package com.jburto2.carmaintenance;


import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * 
 * @author jburton
 *
 * @class DisplayWorkActivity
 * 
 * @brief This class implements functionality to display the work table 
 */

public class DisplayWorkActivity extends DisplayTableActivity implements
AdapterView.OnItemSelectedListener {
	

	
	@Override

	/**
	 * @fn protected void onCreate(Bundle savedInstanceState)
	 * @brief Method called when activity is created. Sets the content view to activity_main. 
	 * 
	 * @param savedInstanceState
	 */
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

		setContentView(R.layout.activity_display_work);
		
		Spinner spinner = (Spinner) findViewById(R.id.vehicleSpinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		
        List<Vehicle> vehicleList = db.getAllVehicles();
        
        String [] spinnerList = new String[vehicleList.size()+1];
        spinnerList[0] = "";
        
        for (int i = 0; i < vehicleList.size(); i++)
        {
        	spinnerList[i+1] = (vehicleList.get(i).getVehicleDescription());
        }
        
        // Create spinner from array http://stackoverflow.com/questions/2784081/android-create-spinner-programmatically-from-array
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,spinnerList);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
		
		
		drawTable();
		

	}
	
	

	public void onItemSelected(AdapterView<?> parent, View v, int position,
			long id) {
		
		// when an item is selected, put the id in a hidden text view. 
		
		TextView htv = (TextView) findViewById(R.id.vehicleIDTextView);
		Vehicle vehicle = null;
		try 
		{
			vehicle = db.getVehicle(((TextView)v).getText().toString());
			htv.setText(Integer.toString(vehicle.getID()));
		}
		catch (Exception e)
		{
			htv.setText("-1");
			
		}
		
		drawTable();
		
	}

	public void onNothingSelected(AdapterView<?> parent) {
		// when an item is selected, set id to -1 
		TextView htv = (TextView) findViewById(R.id.vehicleIDTextView);
		htv.setText("-1");
		drawTable();
	
	}
	
	@SuppressLint("NewApi")

	protected void drawTable(){
		
		
		// Get the Table Layout 
	    TableLayout tableLayout = (TableLayout) findViewById(R.id.tlGridTable);
	    //TODO: Next 3 lines in XML
	    tableLayout.setBaselineAligned(false);
	    tableLayout.setShowDividers(TableLayout.SHOW_DIVIDER_BEGINNING | TableLayout.SHOW_DIVIDER_END | TableLayout.SHOW_DIVIDER_MIDDLE);
	    tableLayout.setDividerPadding(2);
	    tableLayout.removeAllViews();
	    
	    TextView vehicleID = (TextView) findViewById(R.id.vehicleIDTextView);
	    
	    int vid = -1;
	    try
	    {
	    	vid = Integer.parseInt(vehicleID.getText().toString());
	    }
	    catch (Exception e)
	    {
	    	
	    }		
		

        
        
        // Create the labels
    	// 0
    	TableRow tableRow = TableLayoutUtils.createTableRow(this);
    	tableRow.setId(0);
    	
    	TextView textView = TableLayoutUtils.createTextView(this, "Work ID", 15,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        
    	// 1
    	textView = TableLayoutUtils.createTextView(this, "Vehicle ID", 15,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        
    	// 2
    	textView = TableLayoutUtils.createTextView(this,"Item ID", 15,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        
    	// 3 
    	textView = TableLayoutUtils.createTextView(this, "Receipt ID", 15, Color.rgb(200,200,200), Color.rgb(51, 51, 51));
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
    	
    	// 4
    	
        textView = TableLayoutUtils.createTextView(this, "Vehicle Descripton", 15, Color.rgb(200,200,200), Color.rgb(51, 51, 51));
        if (vid < 0)
        {
        	textView.setVisibility(View.VISIBLE);
        }
        else
        {
        	textView.setVisibility(View.GONE);
        }
        tableRow.addView(textView);
        
        // 5
        textView = TableLayoutUtils.createTextView(this, "Maintenance Item", 15, Color.rgb(200,200,200), Color.rgb(51, 51, 51));
        tableRow.addView(textView);
        
        // 6
        textView = TableLayoutUtils.createTextView(this, "Receipt File", 15,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
        tableRow.addView(textView);
        
        // 7
        textView = TableLayoutUtils.createTextView(this, "Work Notes", 15,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
        tableRow.addView(textView);

        // 8
        textView = TableLayoutUtils.createTextView(this, "Save", 15,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
        tableRow.addView(textView);
	
        // 9
//        textView = TableLayoutUtils.createTextView(this, "Clear", 10,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
//        tableRow.addView(textView);
        // 10
        textView = TableLayoutUtils.createTextView(this, "Delete", 10,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
        tableRow.addView(textView);
        
        // Override the on click listener to do nothing.
        tableRow.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
        
        tableLayout.addView(tableRow);
		


        
        //LinkedList<TableRow> rowList = new LinkedList<TableRow>();
        //rowList.add(tableRow);

        List<Work> worklist = null;
        
        if (vid < 0)
        {
        	worklist = db.getAllWorks();
        }
        else
        {
        	worklist = db.getAllWorksByVehicleId(vid);
        }
        
        
        
        int NUMBER_BUTTONS=2;
        for (int i = 0; i < worklist.size(); i++){
        	
            // First row: Entered data
        	tableRow = TableLayoutUtils.createTableRow(this);
        	tableRow.setId(i+1);

        	

        	
        	Work workitem = worklist.get(i);
        	
        	Vehicle vehicle = null;
        	Item item = null;
        	Receipt receipt = null;
        	
        	try 
        	{

	        	vehicle = db.getVehicleById(workitem.getVehicleID());
	        	item = db.getItemById(workitem.getItemID());
	        	receipt = db.getReceiptById(workitem.getReceiptID());
        	}
        	catch (Exception e)
        	{
        		displayMessageDialog("SQL fail",e.getMessage());
        		return;
        	}
        	
        	        	
            // Data
        	// 0
        	textView = TableLayoutUtils.createTextView(this, Integer.toString(workitem.getID()), 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
        	textView.setVisibility(View.GONE);
        	tableRow.addView(textView);
            
        	// 1
        	textView = TableLayoutUtils.createTextView(this, Integer.toString(workitem.getVehicleID()), 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
        	textView.setVisibility(View.GONE);
        	tableRow.addView(textView);
            
        	// 2
        	textView = TableLayoutUtils.createTextView(this, Integer.toString(workitem.getItemID()), 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
        	textView.setVisibility(View.GONE);
        	tableRow.addView(textView);
            
        	// 3 
        	textView = TableLayoutUtils.createTextView(this, Integer.toString(workitem.getReceiptID()), 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
        	textView.setVisibility(View.GONE);
        	tableRow.addView(textView);
        	
        	// 4
            textView = TableLayoutUtils.createTextView(this, vehicle.getVehicleDescription(), 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
            if (vid < 0)
            {
            	textView.setVisibility(View.VISIBLE);
            }
            else
            {
            	textView.setVisibility(View.GONE);
            }
            tableRow.addView(textView);
            
            // 5
            textView = TableLayoutUtils.createTextView(this, item.getItemDescription(), 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
            tableRow.addView(textView);
            
            // 6
            textView = TableLayoutUtils.createTextView(this, receipt.getFile(), 15,Color.rgb(51, 51, 51), Color.rgb(200,200,200));
            tableRow.addView(textView);
            
            // 7
            EditText editText = TableLayoutUtils.createEditText(this, workitem.getNotes(), 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
            tableRow.addView(editText);
            
	        ImageButton button = new ImageButton(this);
	        //("@android:drawable.ic_input_add");
	        button.setImageResource(android.R.drawable.ic_menu_save);
	        button.setId(i*NUMBER_BUTTONS);
	        
	        button.setOnClickListener(new View.OnClickListener(){
	            public void onClick(View v){
	            	
	            	 //save button.
	            	
	            	
	            	// Get the table row
	            	TableRow tr = (TableRow)v.getParent();
	            	
	            	Work work = getWorkFromTableRow(tr);
	            	String keys = TableLayoutUtils.getKeysFromTableRow(tr);
	            	//displayToast(keys);
	            	

	            	try 
	            	{
	            		db.updateWork(work);	
	            	}
	            	catch (Exception e)
	            	{
	            		displayMessageDialog(e.getMessage(),e.toString());
	            	}
	            	
	            	//displayToast(keys);
	            	//send click through to parent.
	            	tr.performClick();
	            	
	            	
	            }
	        
	            
	        });
	        tableRow.addView(button);
//	        button = new ImageButton(this);
//	        button.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
//	        button.setId(i*NUMBER_BUTTONS+1);
//	        button.setOnClickListener(new View.OnClickListener(){
//	            public void onClick(View v){
//	                 // Do some operation for minus after getting v.getId() to get the current row
//	            	// http://stackoverflow.com/questions/14112044/android-how-to-get-the-id-of-a-parent-view
//	            	displayToast("Button is"+v.getId()+ " on row "+ ((TableRow)v.getParent()).getId());
//	            	//send click through to parent.
//	            	/// http://stackoverflow.com/questions/8135032/does-making-parent-clickable-make-all-child-element-clickable-as-well
//		            ViewParent tr = v.getParent();
//	            	v.performClick();
//
//	            	
//	            }
//
//	        
//	            
//	        });
//	        
//	        tableRow.addView(button);
	        
	        button = new ImageButton(this);
	        button.setImageResource(android.R.drawable.ic_menu_delete);
	        button.setId(i*NUMBER_BUTTONS+1);
	        button.setOnClickListener(new View.OnClickListener(){
	            public void onClick(View v){
	                 // Do some operation for minus after getting v.getId() to get the current row
	            	// http://stackoverflow.com/questions/14112044/android-how-to-get-the-id-of-a-parent-view
	            	 //save button.
	            	TableRow tr = (TableRow)v.getParent();
	            	
	            	Work work = getWorkFromTableRow(tr);
	            	String keys = TableLayoutUtils.getKeysFromTableRow(tr);
	            	//displayToast(keys);
	            	

	            	try 
	            	{
	            		db.deleteWork(work);	
	            	}
	            	catch (Exception e)
	            	{
	            		displayMessageDialog(e.getMessage(),e.toString());
	            	}
	            	
	            	//displayToast(keys);
	            	//send click through to parent.
	            	tr.performClick();
	            	
	            	//send click through to parent.
	            	//tr.performClick();
	            	//remove the parent
	            	TableLayout tl = (TableLayout)tr.getParent();
	            	tl.removeView(tr);
	            	
	            	
	            }
	        
	            
	        });
	        
	        tableRow.addView(button);

	        tableLayout.addView(tableRow);
	        

        
        }
        
     

        
        
	}
	
	@SuppressLint("NewApi")
	protected void addNewRow() {
		// Get the Table Layout 
	    TableLayout tableLayout = (TableLayout) findViewById(R.id.tlGridTable);
	    //TODO: Next 3 lines in XML
	    tableLayout.setBaselineAligned(false);
	    tableLayout.setShowDividers(TableLayout.SHOW_DIVIDER_BEGINNING | TableLayout.SHOW_DIVIDER_END | TableLayout.SHOW_DIVIDER_MIDDLE);
	    tableLayout.setDividerPadding(2);
	    
	    
	    TextView vehicleID = (TextView) findViewById(R.id.vehicleIDTextView);
	    
	    int vid = -1;
	    try
	    {
	    	vid = Integer.parseInt(vehicleID.getText().toString());
	    }
	    catch (Exception e)
	    {
	    	
	    }		
        // now add the new row
        TableRow tableRow = TableLayoutUtils.createTableRow(this);
        
        // Data
    	// 0
    	TextView textView = TableLayoutUtils.createTextView(this, "-1", 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        
    	// 1
    	textView = TableLayoutUtils.createTextView(this, Integer.toString(vid), 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        
    	// 2
    	textView = TableLayoutUtils.createTextView(this, "-1", 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        
    	// 3 
    	textView = TableLayoutUtils.createTextView(this, "-1", 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
    	
    	// 4
    	Spinner spinner = null;
    	if (vid < 0)
    	{
            List<Vehicle> vehicleList = db.getAllVehicles();
            String [] vehicleArray = new String [vehicleList.size()];
            for (int i = 0; i < vehicleList.size(); i++)
            {
            	vehicleArray[i] = vehicleList.get(i).getVehicleDescription();
            }
            spinner = TableLayoutUtils.createSpinner(this, vehicleArray, Color.rgb(225, 225, 255) );
            tableRow.addView(spinner);
    	}
    	else
    	{
    		Spinner vSpinner = (Spinner) findViewById(R.id.vehicleSpinner);
    		
	        textView = TableLayoutUtils.createTextView(this, vSpinner.getSelectedItem().toString() , 15, Color.rgb(51, 51, 51),Color.rgb(255,255,255));
	    	textView.setVisibility(View.GONE);
	        tableRow.addView(textView);


    	}
    		
        // 5
        List<Item> itemList = db.getAllItems();
        String [] itemArray = new String [itemList.size()];
        for (int i = 0; i < itemList.size(); i++)
        {
        	itemArray[i] = itemList.get(i).getItemDescription();
        }
        spinner = TableLayoutUtils.createSpinner(this, itemArray, Color.rgb(225, 225, 255) );
        tableRow.addView(spinner);
        

        // 6
        List<Receipt> receiptList = db.getAllReceipts();
        String [] receiptArray = new String [receiptList.size()];
        for (int i = 0; i < receiptList.size(); i++)
        {
        	receiptArray[i] = receiptList.get(i).getFile();
        }
        spinner = TableLayoutUtils.createSpinner(this, receiptArray, Color.rgb(255, 255, 255) );
        tableRow.addView(spinner);
        
        // 7
        EditText editText = TableLayoutUtils.createEditText(this, "", 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
        tableRow.addView(editText);
        
        ImageButton button = new ImageButton(this);
        
        button.setImageResource(android.R.drawable.ic_menu_save);
        //button.setId(i*NUMBER_BUTTONS);
        
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
            	
            	 //save button.
            	
            	
            	// Get the table row
            	TableRow tr = (TableRow)v.getParent();
            	
            	//fill the hidden fields from the database.
            	try {
            		fillWorkIdFieldsFromSpinners(tr);
            	}
            	catch (Exception e)
            	{
            		displayMessageDialog(e.toString(),e.getMessage());
            		tr.performClick();
            		return;
            	}

            	Work work = getWorkFromTableRow(tr);
            	
            	
            	String keys = TableLayoutUtils.getKeysFromTableRow(tr);
            	//displayToast(keys);
            	
            	
            	

            	try 
            	{
            		db.addWork(work);	
            	}
            	catch (Exception e)
            	{
            		displayMessageDialog(e.getMessage(),e.toString());
            	}
            	
            	//displayToast(keys);
            	//send click through to parent.
            	tr.performClick();
            	drawTable();
            	
            }
        
            
        });
        tableRow.addView(button);
//        button = new ImageButton(this);
//        button.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
//        
//        button.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                 // Do some operation for minus after getting v.getId() to get the current row
//            	// http://stackoverflow.com/questions/14112044/android-how-to-get-the-id-of-a-parent-view
//            	displayToast("Button is"+v.getId()+ " on row "+ ((TableRow)v.getParent()).getId());
//            	//send click through to parent.
//            	/// http://stackoverflow.com/questions/8135032/does-making-parent-clickable-make-all-child-element-clickable-as-well
//	            ViewParent tr = v.getParent();
//            	v.performClick();
//
//            	
//            }
//
//        
//            
//        });
//        
//        tableRow.addView(button);
        
        button = new ImageButton(this);
        button.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
        
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                 // Do some operation for minus after getting v.getId() to get the current row
            	// http://stackoverflow.com/questions/14112044/android-how-to-get-the-id-of-a-parent-view
            	 //save button.
            	TableRow tr = (TableRow)v.getParent();
            	
            	
            	//send click through to parent.
            	//tr.performClick();
            	//remove the parent
            	TableLayout tl = (TableLayout)tr.getParent();
            	tl.removeView(tr);
            	
            	
            }
        
            
        });
        tableRow.addView(button);
        
        tableLayout.addView(tableRow);
	}
	
	private void fillWorkIdFieldsFromSpinners(TableRow tr) throws Exception
	{
		String vehicleDescription;
		String itemDescription;
		String receiptFile;
		
		try 
		{
			vehicleDescription = ((Spinner)tr.getChildAt(4)).getSelectedItem().toString();
		}
		catch (java.lang.ClassCastException ce)
		{
			vehicleDescription = ((TextView)tr.getChildAt(4)).getText().toString();
		}
		
		
		
		
		Vehicle v = db.getVehicle(vehicleDescription);
		((TextView)tr.getChildAt(1)).setText(Integer.toString(v.getID()));
		
		try 
		{
			itemDescription = ((Spinner)tr.getChildAt(5)).getSelectedItem().toString();
		}
		catch (java.lang.ClassCastException ce)
		{
			itemDescription = ((TextView)tr.getChildAt(5)).getText().toString();
		}
	
		Item i = db.getItem(itemDescription);
		((TextView)tr.getChildAt(2)).setText(Integer.toString(i.getID()));
		
		try
		{
			receiptFile = ((Spinner)tr.getChildAt(6)).getSelectedItem().toString();
		}
		catch (java.lang.ClassCastException ce)
		{
			receiptFile = ((TextView)tr.getChildAt(6)).getText().toString();
		}
	
		Receipt r = db.getReceipt(receiptFile);
		((TextView)tr.getChildAt(3)).setText(Integer.toString(r.getID()));
	}
	
	private Work getWorkFromTableRow(TableRow tr)
	{
		// ID - child 0
		int  workid =  Integer.parseInt(((TextView)tr.getChildAt(0)).getText().toString());
		
		// Vehicle ID = child 1
		int vehicleDescription = Integer.parseInt(((TextView)tr.getChildAt(1)).getText().toString());
		
		// Item Id = child 2
		int itemid = Integer.parseInt(((TextView)tr.getChildAt(2)).getText().toString());
		
		// Receipt Id = child 3
		int receiptid = Integer.parseInt(((TextView)tr.getChildAt(3)).getText().toString());
		
		// Notes = child 4
		String notes = ((TextView)tr.getChildAt(7)).getText().toString();
		
		return new Work(workid,vehicleDescription,itemid,receiptid,notes);
	

	}
	
	protected void deleteAllRows()
	{
		TableLayoutUtils.displayYesNoDialog(this, "Delete All", "Delete All Rows in Work Table?");
		if(TableLayoutUtils.getDialogResult())
		{
			displayToast("Deleted all rows!");
			db.deleteAllRecordsFromTable(Work.TABLE_NAME);
			drawTable();
		}
	}
	
	public void onVehicleButtonClick(View v) {
		

	    Intent intent = new Intent(this, DisplayVehicleActivity.class);
	    startActivity(intent);

	}
	

    
}
