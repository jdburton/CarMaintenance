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
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.planets_array, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
		
		createTable();
		
		//now let's create a table row.
		//Columns: item + | receipt + | mileage | location | notes | delete | clear | edit
		
		// get the items from the database
		//Columns: item + | receipt + | mileage | location | notes
		
		//now let's create a blank row of the table
	}
	
	

	public void onItemSelected(AdapterView<?> parent, View v, int position,
			long id) {
		
	}

	public void onNothingSelected(AdapterView<?> parent) {
	
	}
	
	@SuppressLint("NewApi")
	private void createTable(){
	
		DatabaseHandler db = new DatabaseHandler(this);
/*
		db.addVehicle(new Vehicle(0,"Crown Victoria"));
		db.addItem(new Item(0,"Blinker Fluid",10000,12));
		db.addItem(new Item(0,"Muffler bearings",20000,24));
		db.addLocation(new Location(0,"Jiffy Lube"));
		Location l = db.getLocation("Jiffy Lube");
		//this(0,file, location_id, date, amount, mileage,  notes)
		db.addReceipt(new Receipt(0,"none",l.getID(),"02/04/2012",1999,90210,"Replaced blinker fluid. Checked muffler bearings." ));
		Receipt r = db.getReceipt("none");
		Vehicle v = db.getVehicle("Crown Victoria");
		Item it = db.getItem("Blinker Fluid");
		Item it2 = db.getItem("Muffler bearings");
		db.addWork(new Work(0,v.getID(),it.getID(),r.getID(),"50ML Blinker Fluid"));
		db.addWork(new Work(0,v.getID(),it2.getID(),r.getID(),"Muffler bearings fine"));
	*/	
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tlGridTable);
        tableLayout.setBaselineAligned(false);
        tableLayout.setShowDividers(TableLayout.SHOW_DIVIDER_BEGINNING | TableLayout.SHOW_DIVIDER_END | TableLayout.SHOW_DIVIDER_MIDDLE);
        tableLayout.setDividerPadding(0);
        
        //LinkedList<TableRow> rowList = new LinkedList<TableRow>();
        //rowList.add(tableRow);

        List<Work> worklist = db.getAllWorks();
        
        
        int NUMBER_BUTTONS=2;
        for (int i = 0; i < worklist.size(); i++){
        	
            // First row: Entered data
        	TableRow tableRow = TableLayoutUtils.createTableRow(this);
        	tableRow.setId(i);

        	

        	
        	Work workitem = worklist.get(i);
        	Vehicle vehicle = db.getVehicleById(workitem.getVehicleID());
        	Item item = db.getItemById(workitem.getItemID());
        	Receipt receipt = db.getReceiptById(workitem.getReceiptID());
        	
        	        	
            // Data
            TextView textView = TableLayoutUtils.createTextView(this, vehicle.getVehicleDescription(), 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
            tableRow.addView(textView);

            textView = TableLayoutUtils.createTextView(this, item.getItemDescription(), 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
            tableRow.addView(textView);
            
            textView = TableLayoutUtils.createTextView(this, receipt.getFile(), 15,Color.rgb(51, 51, 51), Color.rgb(200,200,200));
            tableRow.addView(textView);
            
            EditText editText = TableLayoutUtils.createEditText(this, workitem.getNotes(), 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
            tableRow.addView(editText);
            
	        ImageButton button = new ImageButton(this);
	        //("@android:drawable.ic_input_add");
	        button.setImageResource(android.R.drawable.ic_menu_edit);
	        button.setId(i*NUMBER_BUTTONS);
	        
	        button.setOnClickListener(new View.OnClickListener(){
	            public void onClick(View v){
	                 // Do some operation for minus after getting v.getId() to get the current row
	            	/// http://stackoverflow.com/questions/14112044/android-how-to-get-the-id-of-a-parent-view
	            	/// http://stackoverflow.com/questions/15658144/how-to-identify-the-button-clicked-from-a-dynamically-generated-table
	            	TableRow tr = (TableRow)v.getParent();
	            	//displayToast("Button is"+v.getId()+ " on row "+ tr.getId());
	            	
	            	
	            	TextView tv = (TextView)tr.getChildAt(0);
	            	displayToast(tv.getText().toString());
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
	            	displayToast("Button is"+v.getId()+ " on row "+ ((TableRow)v.getParent()).getId());
	            	
	            }
	        
	            
	        });
	        
	        tableRow.addView(button);

	        tableLayout.addView(tableRow);
        
        }
        
    
        
        
        /// http://stackoverflow.com/questions/15658144/how-to-identify-the-button-clicked-from-a-dynamically-generated-table
        
        
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
