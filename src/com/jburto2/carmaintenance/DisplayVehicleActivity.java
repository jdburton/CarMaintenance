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
 * @class DisplayVehicleActivity
 * 
 * @brief This class implements functionality for the main activity in Android Lookup. Start here.
 */

public class DisplayVehicleActivity extends DisplayTableActivity  {
	
	

	
	@Override

	/**
	 * @fn protected void onCreate(Bundle savedInstanceState)
	 * @brief Method called when activity is created. Sets the content view to activity_main. 
	 * 
	 * @param savedInstanceState
	 */
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_vehicle);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

		
		/*
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
		
		createData();
		*/
		drawTable();
		
		//now let's create a table row.
		//Columns: item + | receipt + | mileage | location | notes | delete | clear | edit
		
		// get the items from the database
		//Columns: item + | receipt + | mileage | location | notes
		
		//now let's create a blank row of the table
	}
	

	
//
//	public void onItemSelected(AdapterView<?> parent, View v, int position,
//			long id) {
//		
//		// when an item is selected, put the id in a hidden text view. 
//		
//		TextView htv = (TextView) findViewById(R.id.vehicleIDTextView);
//		Vehicle vehicle = null;
//		try 
//		{
//			vehicle = db.getVehicle(((TextView)v).getText().toString());
//			htv.setText(Integer.toString(vehicle.getID()));
//		}
//		catch (Exception e)
//		{
//			htv.setText("-1");
//			
//		}
//		
//		drawTable();
//		
//	}
//
//	public void onNothingSelected(AdapterView<?> parent) {
//		// when an item is selected, set id to -1 
//		TextView htv = (TextView) findViewById(R.id.vehicleIDTextView);
//		htv.setText("-1");
//		drawTable();
//	
//	}
	
	@SuppressLint("NewApi")

	protected void drawTable(){
		
		
		// Get the Table Layout 
	    TableLayout tableLayout = (TableLayout) findViewById(R.id.tlGridTable);
	    //TODO: Next 3 lines in XML
	    tableLayout.setBaselineAligned(false);
	    tableLayout.setShowDividers(TableLayout.SHOW_DIVIDER_BEGINNING | TableLayout.SHOW_DIVIDER_END | TableLayout.SHOW_DIVIDER_MIDDLE);
	    tableLayout.setDividerPadding(2);
	    tableLayout.removeAllViews();
	    

        
        
        // Create the labels
    	// 0
    	TableRow tableRow = TableLayoutUtils.createTableRow(this);
    	tableRow.setId(0);
    	
        
    	// 1
    	TextView textView = TableLayoutUtils.createTextView(this, "Vehicle ID", 15,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        

    	// 2
    	
        textView = TableLayoutUtils.createTextView(this, "Vehicle Descripton", 15, Color.rgb(200,200,200), Color.rgb(51, 51, 51));

        tableRow.addView(textView);
        

        // 3
        textView = TableLayoutUtils.createTextView(this, "Save", 15,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
        tableRow.addView(textView);
	
        // 4
        textView = TableLayoutUtils.createTextView(this, "Clear", 10,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
        tableRow.addView(textView);
        // 5
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

        List<Vehicle> vehiclelist = null;
        


        vehiclelist = db.getAllVehicles();
   
        int NUMBER_BUTTONS=2;
        for (int i = 0; i < vehiclelist.size(); i++){
        	
            // First row: Entered data
        	tableRow = TableLayoutUtils.createTableRow(this);
        	tableRow.setId(i+1);

        	

        	
        	Vehicle vehicle = vehiclelist.get(i);
        	

        	
        	        	
            // Data
        	// 0
        	textView = TableLayoutUtils.createTextView(this, Integer.toString(vehicle.getID()), 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
        	textView.setVisibility(View.GONE);
        	tableRow.addView(textView);
            

        	
        	// 1
            textView = TableLayoutUtils.createTextView(this, vehicle.getVehicleDescription(), 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
            tableRow.addView(textView);
            
	        ImageButton button = new ImageButton(this);
	        //("@android:drawable.ic_input_add");
	        button.setImageResource(android.R.drawable.ic_menu_save);
	        button.setId(i*NUMBER_BUTTONS);
	        
	        button.setOnClickListener(new View.OnClickListener(){
	            public void onClick(View v){
	            	
	            	 //save button.
	            	
	            	
	            	// Get the table row
	            	TableRow tr = (TableRow)v.getParent();
	            	
	            	Vehicle vehicle = getVehicleFromTableRow(tr);
	            	String keys = TableLayoutUtils.getKeysFromTableRow(tr);
	            	displayToast(keys);
	            	

	            	try 
	            	{
	            		db.updateVehicle(vehicle);	
	            	}
	            	catch (Exception e)
	            	{
	            		displayMessageDialog(e.getMessage(),e.toString());
	            	}
	            	
	            	displayToast(keys);
	            	//send click through to parent.
	            	tr.performClick();
	            	
	            	
	            }
	        
	            
	        });
	        tableRow.addView(button);
	        button = new ImageButton(this);
	        button.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
	        button.setId(i*NUMBER_BUTTONS+1);
	        button.setOnClickListener(new View.OnClickListener(){
	            public void onClick(View v){
	                 // Do some operation for minus after getting v.getId() to get the current row
	            	// http://stackoverflow.com/questions/14112044/android-how-to-get-the-id-of-a-parent-view
	            	displayToast("Button is"+v.getId()+ " on row "+ ((TableRow)v.getParent()).getId());
	            	//send click through to parent.
	            	/// http://stackoverflow.com/questions/8135032/does-making-parent-clickable-make-all-child-element-clickable-as-well
		            ViewParent tr = v.getParent();
	            	v.performClick();

	            	
	            }

	        
	            
	        });
	        
	        tableRow.addView(button);
	        
	        button = new ImageButton(this);
	        button.setImageResource(android.R.drawable.ic_menu_delete);
	        button.setId(i*NUMBER_BUTTONS+1);
	        button.setOnClickListener(new View.OnClickListener(){
	            public void onClick(View v){
	                 // Do some operation for minus after getting v.getId() to get the current row
	            	// http://stackoverflow.com/questions/14112044/android-how-to-get-the-id-of-a-parent-view
	            	 //save button.
	            	TableRow tr = (TableRow)v.getParent();
	            	
	            	Vehicle vehicle = getVehicleFromTableRow(tr);
	            	String keys = TableLayoutUtils.getKeysFromTableRow(tr);
	            	displayToast(keys);
	            	

	            	try 
	            	{
	            		db.deleteVehicle(vehicle);	
	            	}
	            	catch (Exception e)
	            	{
	            		displayMessageDialog(e.getMessage(),e.toString());
	            	}
	            	
	            	displayToast(keys);
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
	    

        TableRow tableRow = TableLayoutUtils.createTableRow(this);
        
        // Data
    	// 0
    	TextView textView = TableLayoutUtils.createTextView(this, "-1", 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        
    
        
        // 1
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
            	

            	Vehicle vehicle = getVehicleFromTableRow(tr);
            	
            	
            	String keys = TableLayoutUtils.getKeysFromTableRow(tr);
            	displayToast(keys);
            	
            	
            	

            	try 
            	{
            		db.addVehicle(vehicle);	
            	}
            	catch (Exception e)
            	{
            		displayMessageDialog(e.getMessage(),e.toString());
            	}
            	
            	displayToast(keys);
            	//send click through to parent.
            	tr.performClick();
            	drawTable();
            	
            }
        
            
        });
        tableRow.addView(button);
        button = new ImageButton(this);
        button.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
        
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                 // Do some operation for minus after getting v.getId() to get the current row
            	// http://stackoverflow.com/questions/14112044/android-how-to-get-the-id-of-a-parent-view
            	displayToast("Button is"+v.getId()+ " on row "+ ((TableRow)v.getParent()).getId());
            	//send click through to parent.
            	/// http://stackoverflow.com/questions/8135032/does-making-parent-clickable-make-all-child-element-clickable-as-well
	            ViewParent tr = v.getParent();
            	v.performClick();

            	
            }

        
            
        });
        
        tableRow.addView(button);
        
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
	
	
	protected Vehicle getVehicleFromTableRow(TableRow tr)
	{

		// Vehicle ID = child 0
		int vehicleID = Integer.parseInt(((TextView)tr.getChildAt(0)).getText().toString());
		
		// Vehicle Description = child 1
		String vehicleDescription = ((TextView)tr.getChildAt(1)).getText().toString();

		
		return new Vehicle(vehicleID, vehicleDescription);
		
	

	}
	

	protected void deleteAllRows()
	{
		TableLayoutUtils.displayYesNoDialog(this, "Delete All", "Delete All Rows in Vehicle Table?");
		if(TableLayoutUtils.getDialogResult())
		{
			displayToast("Deleted all rows!");
			db.deleteAllRecordsFromTable(Vehicle.TABLE_NAME);
			drawTable();
		}
	}
	


    
}
