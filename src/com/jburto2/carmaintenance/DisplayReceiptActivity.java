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
 * @class DisplayReceiptActivity
 * 
 * @brief This class implements functionality for the main activity in Android Lookup. Start here.
 */

public class DisplayReceiptActivity extends DisplayTableActivity implements
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

		setContentView(R.layout.activity_display_receipt);
		
		Spinner spinner = (Spinner) findViewById(R.id.locationSpinner);
		// Create an ArrayAdapter using the string array and a default spinner layout
		
        List<Location> locationList = db.getAllLocations();
        
        String [] spinnerList = new String[locationList.size()+1];
        spinnerList[0] = "";
        
        for (int i = 0; i < locationList.size(); i++)
        {
        	spinnerList[i+1] = (locationList.get(i).getLocationDescription());
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
		
		TextView htv = (TextView) findViewById(R.id.locationIDTextView);
		Location location = null;
		try 
		{
			location = db.getLocation(((TextView)v).getText().toString());
			htv.setText(Integer.toString(location.getID()));
		}
		catch (Exception e)
		{
			htv.setText("-1");
			
		}
		
		drawTable();
		
	}

	public void onNothingSelected(AdapterView<?> parent) {
		// when an item is selected, set id to -1 
		TextView htv = (TextView) findViewById(R.id.locationIDTextView);
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
	    
	    TextView locationID = (TextView) findViewById(R.id.locationIDTextView);
	    
	    int vid = -1;
	    try
	    {
	    	vid = Integer.parseInt(locationID.getText().toString());
	    }
	    catch (Exception e)
	    {
	    	
	    }		
		

        
        
        // Create the labels
    	// 0
    	TableRow tableRow = TableLayoutUtils.createTableRow(this);
    	tableRow.setId(0);
    	
    	TextView textView = TableLayoutUtils.createTextView(this, "Receipt ID", 15,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        
    	// 1
    	textView = TableLayoutUtils.createTextView(this, "Location ID", 15,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        

    	// 2
    	
        textView = TableLayoutUtils.createTextView(this, "Location Descripton", 15, Color.rgb(200,200,200), Color.rgb(51, 51, 51));
        if (vid < 0)
        {
        	textView.setVisibility(View.VISIBLE);
        }
        else
        {
        	textView.setVisibility(View.GONE);
        }
        tableRow.addView(textView);
        
        // 3
        textView = TableLayoutUtils.createTextView(this, "Receipt Img.", 15, Color.rgb(200,200,200), Color.rgb(51, 51, 51));
        tableRow.addView(textView);

        // 4
        textView = TableLayoutUtils.createTextView(this, "Date", 15, Color.rgb(200,200,200), Color.rgb(51, 51, 51));
        tableRow.addView(textView);
        
        // 5
        textView = TableLayoutUtils.createTextView(this, "Mileage", 15,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
        tableRow.addView(textView);
        
        // 6
        textView = TableLayoutUtils.createTextView(this, "Amount", 15,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
        tableRow.addView(textView);

        // 7
        textView = TableLayoutUtils.createTextView(this, "Receipt Notes", 15,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
        tableRow.addView(textView);

        // 8
        textView = TableLayoutUtils.createTextView(this, "Save", 15,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
        tableRow.addView(textView);
	
        // 9
        textView = TableLayoutUtils.createTextView(this, "Clear", 10,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
        tableRow.addView(textView);
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

        List<Receipt> receiptlist = null;
        
        if (vid < 0)
        {
        	receiptlist = db.getAllReceipts();
        }
        else
        {
        	receiptlist = db.getAllReceiptsByLocationId(vid);
        }
        
        
        
        int NUMBER_BUTTONS=2;
        for (int i = 0; i < receiptlist.size(); i++){
        	
            // First row: Entered data
        	tableRow = TableLayoutUtils.createTableRow(this);
        	tableRow.setId(i+1);

        	

        	
        	Receipt singlereceipt = receiptlist.get(i);
        	
        	Location location = null;

        	
        	try 
        	{

	        	location = db.getLocationById(singlereceipt.getLocationID());

        	}
        	catch (Exception e)
        	{
        		displayMessageDialog("SQL fail",e.getMessage());
        		return;
        	}
        	
        	        	
            // Data
        	// 0
        	textView = TableLayoutUtils.createTextView(this, Integer.toString(singlereceipt.getID()), 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
        	textView.setVisibility(View.GONE);
        	tableRow.addView(textView);
            
        	// 1
        	textView = TableLayoutUtils.createTextView(this, Integer.toString(singlereceipt.getLocationID()), 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
        	textView.setVisibility(View.GONE);
        	tableRow.addView(textView);
     

        	// 2
            textView = TableLayoutUtils.createTextView(this, location.getLocationDescription(), 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
            if (vid < 0)
            {
            	textView.setVisibility(View.VISIBLE);
            }
            else
            {
            	textView.setVisibility(View.GONE);
            }
            tableRow.addView(textView);
            
            // 3
            EditText editText = TableLayoutUtils.createEditText(this, singlereceipt.getFile(), 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
            tableRow.addView(editText);
            // TODO: Override with image
            
            // 4
            editText = TableLayoutUtils.createEditText(this, singlereceipt.getDate(), 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
            tableRow.addView(editText);
            
            // 5
             editText = TableLayoutUtils.createEditText(this, Integer.toString(singlereceipt.getMileage()), 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
            tableRow.addView(editText);
            
            // 6
            editText = TableLayoutUtils.createEditText(this, Integer.toString(singlereceipt.getAmount()), 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
            tableRow.addView(editText);

            // 7
             editText = TableLayoutUtils.createEditText(this, singlereceipt.getNotes(), 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
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
	            	
	            	Receipt receipt = getReceiptFromTableRow(tr);
	            	String keys = TableLayoutUtils.getKeysFromTableRow(tr);
	            	displayToast(keys);
	            	

	            	try 
	            	{
	            		db.updateReceipt(receipt);	
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
	            	
	            	Receipt receipt = getReceiptFromTableRow(tr);
	            	String keys = TableLayoutUtils.getKeysFromTableRow(tr);
	            	displayToast(keys);
	            	

	            	try 
	            	{
	            		db.deleteReceipt(receipt);	
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
	    
	    
	    TextView locationID = (TextView) findViewById(R.id.locationIDTextView);
	    
	    int vid = -1;
	    try
	    {
	    	vid = Integer.parseInt(locationID.getText().toString());
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
        

    	// 4
    	Spinner spinner = null;
    	if (vid < 0)
    	{
            List<Location> locationList = db.getAllLocations();
            String [] locationArray = new String [locationList.size()];
            for (int i = 0; i < locationList.size(); i++)
            {
            	locationArray[i] = locationList.get(i).getLocationDescription();
            }
            spinner = TableLayoutUtils.createSpinner(this, locationArray, Color.rgb(225, 225, 255) );
            tableRow.addView(spinner);
    	}
    	else
    	{
    		Spinner vSpinner = (Spinner) findViewById(R.id.locationSpinner);
    		
	        textView = TableLayoutUtils.createTextView(this, vSpinner.getSelectedItem().toString() , 15, Color.rgb(51, 51, 51),Color.rgb(255,255,255));
	    	textView.setVisibility(View.GONE);
	        tableRow.addView(textView);


    	}
    	
        // 3
        EditText pictureEditText = TableLayoutUtils.createEditText(this, "", 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
        tableRow.addView(pictureEditText);
        // TODO: Override with image selection.
        
        
        // 4
        EditText editText = TableLayoutUtils.createEditText(this, "", 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
        //TODO: Override with date picker dialog.
        tableRow.addView(editText);
        
        // 5
         editText = TableLayoutUtils.createEditText(this, "0", 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
        tableRow.addView(editText);
        
        // 6
        editText = TableLayoutUtils.createEditText(this, "0", 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
        tableRow.addView(editText);


        
        // 7
        editText = TableLayoutUtils.createEditText(this, "", 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
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
            		fillReceiptIdFieldsFromSpinners(tr);
            	}
            	catch (Exception e)
            	{
            		displayMessageDialog(e.toString(),e.getMessage());
            		tr.performClick();
            		return;
            	}

            	Receipt receipt = getReceiptFromTableRow(tr);
            	
            	
            	String keys = TableLayoutUtils.getKeysFromTableRow(tr);
            	displayToast(keys);
            	
            	
            	

            	try 
            	{
            		db.addReceipt(receipt);	
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
	
	private void fillReceiptIdFieldsFromSpinners(TableRow tr) throws Exception
	{
		String locationDescription;

		
		try 
		{
			locationDescription = ((Spinner)tr.getChildAt(2)).getSelectedItem().toString();
		}
		catch (java.lang.ClassCastException ce)
		{
			locationDescription = ((TextView)tr.getChildAt(2)).getText().toString();
		}
		
		
		
		
		Location v = db.getLocation(locationDescription);
		((TextView)tr.getChildAt(1)).setText(Integer.toString(v.getID()));
		

	}
	
	private Receipt getReceiptFromTableRow(TableRow tr)
	{
		// ID - child 0
		int  receiptid =  Integer.parseInt(((TextView)tr.getChildAt(0)).getText().toString());
		
		// Location ID = child 1
		int locationid = Integer.parseInt(((TextView)tr.getChildAt(1)).getText().toString());
		// File = child 3
		String file = ((TextView)tr.getChildAt(3)).getText().toString();
		
		// Date = child 4
		String date = ((TextView)tr.getChildAt(4)).getText().toString();

		// Amount = child 4
		int amount = Integer.parseInt(((TextView)tr.getChildAt(5)).getText().toString());

		// Mileage = child 5
		int mileage = Integer.parseInt(((TextView)tr.getChildAt(6)).getText().toString());
		
		// Notes = child 6
		String notes = ((TextView)tr.getChildAt(7)).getText().toString();
		
		return new Receipt(receiptid,file,locationid,date,mileage,amount,notes);
	

	}
	
	protected void deleteAllRows()
	{
		TableLayoutUtils.displayYesNoDialog(this, "Delete All", "Delete All Rows in Receipt Table?");
		if(TableLayoutUtils.getDialogResult())
		{
			displayToast("Deleted all rows!");
			db.deleteAllRecordsFromTable(Receipt.TABLE_NAME);
			drawTable();
		}
	}

    
}
