package com.jburto2.carmaintenance;


import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
 * @class MainActivity
 * 
 * @brief This class implements functionality for the main activity in Android Lookup. Start here.
 */

public class MainActivity extends Activity implements
AdapterView.OnItemSelectedListener {
	
	/**
	 * @var public final static String IP_ADDRESSES
	 * @brief Stores the ip_addresses returned from LookupAddressTask
	 */
	public final static String IP_ADDRESSES = "com.jburto2.androidlookup.IP_ADDRESSES";
	/**
	 * @var public final static String CNAME
	 * @brief Stores the CNAME returned from LookupCNAMETask
	 */
	public final static String CNAME = "com.jburto2.androidlookup.CNAME";
	/**
	 * @var public final static String LOOKUP_NAME
	 * @brief Stores the hostname that the user enters on the main screen.
	 */
	public final static String LOOKUP_NAME = "com.jburto2.androidlookup.LOOKUP_NAME";
	/**
	 * @var public final static String PING_RESULTS
	 * @brief Stores "Yes" or "No" result from LookupPingTask
	 */
	public final static String PING_RESULTS = "com.jburto2.androidlookup.PING_RESULTS";
	/**
	 * @var public final static String WHOIS_INFO
	 * @brief Stores the whois request returned from WHOIS_INFO
	 */
	public final static String WHOIS_INFO = "com.jburto2.androidlookup.WHOIS_INFO";
	public DatabaseHandler db = new DatabaseHandler(this);

	
	@Override

	/**
	 * @fn protected void onCreate(Bundle savedInstanceState)
	 * @brief Method called when activity is created. Sets the content view to activity_main. 
	 * 
	 * @param savedInstanceState
	 */
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
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
		
		drawTable();
		
		//now let's create a table row.
		//Columns: item + | receipt + | mileage | location | notes | delete | clear | edit
		
		// get the items from the database
		//Columns: item + | receipt + | mileage | location | notes
		
		//now let's create a blank row of the table
	}
	
	private void createData()
	{
	    db.deleteAllRecordsFromTable(Location.TABLE_NAME);
	    db.deleteAllRecordsFromTable(Item.TABLE_NAME);
	    db.deleteAllRecordsFromTable(Vehicle.TABLE_NAME);
	    db.deleteAllRecordsFromTable(Receipt.TABLE_NAME);
	    db.deleteAllRecordsFromTable(Work.TABLE_NAME);
		db.addVehicle(new Vehicle(0,"Chevette"));
		db.addVehicle(new Vehicle(0,"DeLorian"));
		db.addItem(new Item(0,"Blinker Fluid",10000,12));
		db.addItem(new Item(0,"Muffler bearings",20000,24));
		db.addItem(new Item(0,"Flux Capacitor",75000,60));
		db.addLocation(new Location(0,"Jiffy Lube"));
		Location l = null;
		try {
		l = db.getLocation("Jiffy Lube");
		}
		catch (Exception e)
		{
			displayMessageDialog("SQLite fail",e.getMessage());
			return;
		}
		
		//this(0,file, location_id, date, amount, mileage,  notes)
		db.addReceipt(new Receipt(0,"none",l.getID(),"02/04/2012",1999,90210,"Replaced blinker fluid. Checked muffler bearings." ));
		Receipt r = null;
		Vehicle v = null;
		Item it = null;
		Item it2 = null;
		try {
			r = db.getReceipt("none");
			v = db.getVehicle("Chevette");
			it = db.getItem("Blinker Fluid");
			it2 = db.getItem("Muffler bearings");
		}
		catch (Exception e)
		{
			
			displayMessageDialog("SQLite fail",e.getMessage());
			return;
		}
		
		db.addWork(new Work(0,v.getID(),it.getID(),r.getID(),"50ML Blinker Fluid"));
		db.addWork(new Work(0,v.getID(),it2.getID(),r.getID(),"Muffler bearings fine"));
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

	private void drawTable(){
		
		
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
	            	displayToast(keys);
	            	

	            	try 
	            	{
	            		db.updateWork(work);	
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
	            	
	            	Work work = getWorkFromTableRow(tr);
	            	String keys = TableLayoutUtils.getKeysFromTableRow(tr);
	            	displayToast(keys);
	            	

	            	try 
	            	{
	            		db.deleteWork(work);	
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
	private void addNewRow() {
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
            	displayToast(keys);
            	
            	
            	

            	try 
            	{
            		db.addWork(work);	
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
	
	private void fillWorkIdFieldsFromSpinners(TableRow tr) throws Exception
	{
		String vehicleID;
		String itemID;
		String receiptID;
		
		try 
		{
			vehicleID = ((Spinner)tr.getChildAt(4)).getSelectedItem().toString();
		}
		catch (java.lang.ClassCastException ce)
		{
			vehicleID = ((TextView)tr.getChildAt(4)).getText().toString();
		}
		
		
		
		
		Vehicle v = db.getVehicle(vehicleID);
		((TextView)tr.getChildAt(1)).setText(Integer.toString(v.getID()));
		
		try 
		{
			itemID = ((Spinner)tr.getChildAt(5)).getSelectedItem().toString();
		}
		catch (java.lang.ClassCastException ce)
		{
			itemID = ((TextView)tr.getChildAt(5)).getText().toString();
		}
	
		Item i = db.getItem(itemID);
		((TextView)tr.getChildAt(2)).setText(Integer.toString(i.getID()));
		
		try
		{
			receiptID = ((Spinner)tr.getChildAt(6)).getSelectedItem().toString();
		}
		catch (java.lang.ClassCastException ce)
		{
			receiptID = ((TextView)tr.getChildAt(6)).getText().toString();
		}
	
		Receipt r = db.getReceipt(receiptID);
		((TextView)tr.getChildAt(3)).setText(Integer.toString(r.getID()));
	}
	
	private Work getWorkFromTableRow(TableRow tr)
	{
		// ID - child 0
		int  workid =  Integer.parseInt(((TextView)tr.getChildAt(0)).getText().toString());
		
		// Vehicle ID = child 1
		int vehicleid = Integer.parseInt(((TextView)tr.getChildAt(1)).getText().toString());
		
		// Item Id = child 2
		int itemid = Integer.parseInt(((TextView)tr.getChildAt(2)).getText().toString());
		
		// Receipt Id = child 3
		int receiptid = Integer.parseInt(((TextView)tr.getChildAt(3)).getText().toString());
		
		// Notes = child 4
		String notes = ((TextView)tr.getChildAt(7)).getText().toString();
		
		return new Work(workid,vehicleid,itemid,receiptid,notes);
	

	}
	


	@Override
	
	/**
	 *
	 * @fn public boolean onCreateOptionsMenu(Menu menu)
	 * @brief Inflate the menu; this adds items to the action bar if it is present.
	 * @param menu Meny to be created.
	 * @return true
	 */
	
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	/**
	 * @fn public boolean onOptionsItemSelected(MenuItem item)
	 * @brief Handles menu item selection. 
	 * Only menu item here is the "action_about" for the info activity.
	 * @param item MenuItem that was selected
	 * @return true  
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
	
		 Intent intent = null;
		/// Menu from http://developer.android.com/guide/topics/ui/menus.html#options-menu
	    switch (item.getItemId()) {
	   
	    		
	    case R.id.action_about:
	    	intent = new Intent(this, DisplayInfoActivity.class);
	    	startActivity(intent);
	    	break;

	    case R.id.action_add:
	    	intent =  new Intent(this, DisplayWorkActivity.class);
	    	startActivity(intent);
	    	break;
	    }
	    return true;
	}

	
	/**
	 * @fn void clearHostNameMessage(View view)
	 * @brief Clear the fields. This should be the registered response to the "clear" button for the Hostname button.
	 * Learned how to clear a field from http://stackoverflow.com/questions/8758635/how-to-clear-the-edittext-when-onclick-on-button
	 * @param view
	 */
	public void clearHostNameMessage(View view) {
	
		
    
    }

	/**
	 * @fn public void lookupMessage(View view)
	 * @brief Looks up the url or the IP address. This should be the registered response to the "lookup" button.
	 * Note: The actual lookup is done on a separate thread.
	 * @param view
	 */
	
    public void lookupMessage(View view) 
    {

    	
    	
    }	
	/**
	 * @fn public void whoisMessage(View view)
	 * @brief Gets the whois information for the host. This should be the registered response to the "whois" button.
	 * Note: The actual lookup is done on a separate thread.
	 * @param view
	 */
	
    public void whoisMessage(View view) 
    {
 
    	
    }	

    /**
     * @fn public void displayToast(String message)
     * @brief Displays a popup "Toast" message to the user.
     * Displaying toasts from http://developer.android.com/guide/topics/ui/notifiers/toasts.html
     * @param message Message to display
     */
    public void displayToast(String message)
    {
    	Context context = this.getApplicationContext();
    	TableLayoutUtils.displayToast(context,message);
    }
    
    /**
     * @fn public void displayMessageDialog(String message, String title)
     * @brief Displays a message dialog to the user.
     * Displaying message dialogs from http://www.mkyong.com/android/android-alert-dialog-example/
     * @param message Message to display
     * @param title Title of dialog
     * 
     */
    
    public void displayMessageDialog(String message, String title)
    {
    	
		TableLayoutUtils.displayMessageDialog(this,message,title);
    }
    
    
    
}
