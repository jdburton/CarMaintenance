package com.jburto2.carmaintenance;


import java.util.List;
import java.util.StringTokenizer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
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
		//spinner.setBackgroundColor(LayoutUtils.SPINNER_COLOR);
		
		
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
		
	    
	    super.drawTable();
		
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
    	TableRow tableRow = LayoutUtils.createTableRow(this);
    	tableRow.setId(0);
    	
    	TextView textView = LayoutUtils.createTextView(this, "Work ID", 15,LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        
    	// 1
    	textView = LayoutUtils.createTextView(this, "Vehicle ID", 15,LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        
    	// 2
    	textView = LayoutUtils.createTextView(this,"Item ID", 15,LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        
    	// 3 
    	textView = LayoutUtils.createTextView(this, "Receipt ID", 15, LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
    	
    	// 4
    	
        textView = LayoutUtils.createTextView(this, "Vehicle Descripton", 15, LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
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
        textView = LayoutUtils.createTextView(this, "Maintenance Item", 15, LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
        tableRow.addView(textView);
        
        // 6
        textView = LayoutUtils.createTextView(this, "Receipt File", 15,LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
        tableRow.addView(textView);
        
        // 7
        textView = LayoutUtils.createTextView(this, "Mileage", 15,LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
        tableRow.addView(textView);
        
        // 8
        textView = LayoutUtils.createTextView(this, "Work Notes", 15,LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
        tableRow.addView(textView);

	    // 9
	    textView = LayoutUtils.createTextView(this, "Save", 15,LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
	    if (autoSave)
        {
        	textView.setVisibility(View.GONE);
        }
        else
        {
        	textView.setVisibility(View.VISIBLE);
        }
	    tableRow.addView(textView);
	    
        // 10
        textView = LayoutUtils.createTextView(this, "Detail", 15,LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
        tableRow.addView(textView);
        // 11
        textView = LayoutUtils.createTextView(this, "Delete", 15,LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
        tableRow.addView(textView);
        
        // 12 - Must be last 
        textView = LayoutUtils.createTextView(this, "New Row", 15,LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
        textView.setVisibility(View.GONE);
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
        	tableRow = LayoutUtils.createTableRow(this);
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
        	textView = LayoutUtils.createTextView(this, Integer.toString(workitem.getID()), 15, LayoutUtils.DARK_GRAY,LayoutUtils.LIGHT_GRAY);
        	textView.setVisibility(View.GONE);
        	tableRow.addView(textView);
            
        	// 1
        	textView = LayoutUtils.createTextView(this, Integer.toString(workitem.getVehicleID()), 15, LayoutUtils.DARK_GRAY,LayoutUtils.LIGHT_GRAY);
        	textView.setVisibility(View.GONE);
        	tableRow.addView(textView);
            
        	// 2
        	textView = LayoutUtils.createTextView(this, Integer.toString(workitem.getItemID()), 15, LayoutUtils.DARK_GRAY,LayoutUtils.LIGHT_GRAY);
        	textView.setVisibility(View.GONE);
        	tableRow.addView(textView);
            
        	// 3 
        	textView = LayoutUtils.createTextView(this, Integer.toString(workitem.getReceiptID()), 15, LayoutUtils.DARK_GRAY,LayoutUtils.LIGHT_GRAY);
        	textView.setVisibility(View.GONE);
        	tableRow.addView(textView);
        	
        	// 4
            textView = LayoutUtils.createTextView(this, vehicle.getVehicleDescription(), 15, LayoutUtils.DARK_GRAY,LayoutUtils.LIGHT_GRAY);
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
            textView = LayoutUtils.createTextView(this, item.getItemDescription(), 15, LayoutUtils.DARK_GRAY,LayoutUtils.LIGHT_GRAY);
            tableRow.addView(textView);
            
            // 6
        	int locationID =  receipt.getLocationID();
        	Location location;
        	String locationDescr;
			try {
				location = db.getLocationById(locationID);
				locationDescr = location.getLocationDescription();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				locationDescr = "Not Found";
			}
        	
        	
            textView = LayoutUtils.createTextView(this, locationDescr + " | "+receipt.getDate(), 15,LayoutUtils.DARK_GRAY, LayoutUtils.LIGHT_GRAY);
            tableRow.addView(textView);
            
//            // 6
//            ImageView imageView = new ImageView(this);    	
//            
//            imageView.setLayoutParams(new TableRow.LayoutParams(60,80));
//            imageView.setScaleType(ScaleType.CENTER_CROP);
//
//            
//            //LayoutUtils.loadImage(this,singlereceipt.getFile(),imageViewHolder);
//            try
//            {
//            	imageView.setImageURI(Uri.parse(receipt.getFile()));
//            	imageView.setTag(receipt.getFile());
//            }
//            catch (Exception e)
//            {
//    			imageView.setImageResource(R.drawable.ic_receipt);
//    			imageView.setTag("default");
//    				
//            }
//            
//            tableRow.addView(imageView);
            
            // 7
            EditText editText = LayoutUtils.createEditText(this, Integer.toString(workitem.getMileage()), 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
            tableRow.addView(editText);
            
            // 8
            editText = LayoutUtils.createEditText(this, workitem.getNotes(), 15, LayoutUtils.DARK_GRAY,LayoutUtils.WHITE);
            tableRow.addView(editText);
            
            // Add either the save button or autosave functionality
            addSaveFunctionToRow(tableRow);
            
	        
	        ImageButton button = new ImageButton(this);
	        
	    	button = new ImageButton(this);
	    	button.setImageResource(android.R.drawable.ic_menu_edit);
	    	button.setOnClickListener(new View.OnClickListener(){
		    	public void onClick(View v){
		    		onEditButtonClick(v);
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
	            	String keys = LayoutUtils.getKeysFromTableRow(tr);
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
	        
	        // New Row Indicator = Must be last 
            textView = LayoutUtils.createTextView(this, "false", 15, LayoutUtils.DARK_GRAY,LayoutUtils.LIGHT_GRAY);
	        textView.setVisibility(View.GONE);
	        
	        tableRow.addView(textView);
	        
	        tableLayout.addView(tableRow);
        
        }
        
 	}
	
	@SuppressLint("NewApi")
	protected void addNewRow() {
		
		super.addNewRow();
		
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
        TableRow tableRow = LayoutUtils.createTableRow(this);
        
        // Data
    	// 0
    	TextView textView = LayoutUtils.createTextView(this, "-1", 15, LayoutUtils.DARK_GRAY,LayoutUtils.LIGHT_GRAY);
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        
    	// 1
    	textView = LayoutUtils.createTextView(this, Integer.toString(vid), 15, LayoutUtils.DARK_GRAY,LayoutUtils.LIGHT_GRAY);
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        
    	// 2
    	textView = LayoutUtils.createTextView(this, "-1", 15, LayoutUtils.DARK_GRAY,LayoutUtils.LIGHT_GRAY);
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        
    	// 3 
    	textView = LayoutUtils.createTextView(this, "-1", 15, LayoutUtils.DARK_GRAY,LayoutUtils.LIGHT_GRAY);
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
            spinner = LayoutUtils.createSpinner(this, vehicleArray);
            tableRow.addView(spinner);
    	}
    	else
    	{
    		Spinner vSpinner = (Spinner) findViewById(R.id.vehicleSpinner);
    		
	        textView = LayoutUtils.createTextView(this, vSpinner.getSelectedItem().toString() , 15, LayoutUtils.DARK_GRAY,LayoutUtils.WHITE);
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
        spinner = LayoutUtils.createSpinner(this, itemArray);
        tableRow.addView(spinner);
        

        // 6
        List<Receipt> receiptList = db.getAllReceipts();
        String [] receiptArray = new String [receiptList.size()];
        for (int i = 0; i < receiptList.size(); i++)
        {
        	
        	int locationID =  receiptList.get(i).getLocationID();
        	Location location;
        	String locationDescr;
			try {
				location = db.getLocationById(locationID);
				locationDescr = location.getLocationDescription();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				locationDescr = "Not Found";
			}
        	
        	receiptArray[i] = locationDescr + " | "+receiptList.get(i).getDate(); 
        }
        spinner = LayoutUtils.createSpinner(this, receiptArray);
        
        tableRow.addView(spinner);
        
        // 7
        EditText editText = LayoutUtils.createEditText(this, "0", 15, LayoutUtils.DARK_GRAY,LayoutUtils.WHITE);
        tableRow.addView(editText);
        
        
        // 8
        editText = LayoutUtils.createEditText(this, "", 15, LayoutUtils.DARK_GRAY,LayoutUtils.WHITE);
        tableRow.addView(editText);

        //Save button or autosave
        addSaveFunctionToRow(tableRow);
        
        //Edit button. Not enabled.
        ImageButton button = new ImageButton(this);
        button.setImageResource(android.R.drawable.ic_menu_edit);
        
        button.setEnabled(false);
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
        
        // New Row Indicator = Must be last 
        textView = LayoutUtils.createTextView(this, "true", 15, LayoutUtils.DARK_GRAY,LayoutUtils.LIGHT_GRAY);
        textView.setVisibility(View.GONE);
        tableRow.addView(textView);
        
        tableLayout.addView(tableRow);
	}
	
	private String getVehicleDescription(TableRow tr) throws Exception
	{
		return getValueFromSpinner(tr,4);

	}
		
	private String getItemDescription(TableRow tr) throws Exception
	{
		return getValueFromSpinner(tr,5);

	}	
	
	private String getReceiptField(TableRow tr) throws Exception
	{
		return getValueFromSpinner(tr,6);
	}
	
	private String getReceiptFile(TableRow tr) throws Exception
	{
		return getReceiptFromTable(tr).getFile();
	}

	private Receipt getReceiptFromTable(TableRow tr) throws Exception
	{
		Receipt receipt;
		
		String receiptField = getReceiptField(tr);
		
		/// http://stackoverflow.com/questions/12375003/java-string-split-with-multicharacter-delimiter
		String [] result = receiptField.split(" \\| ");
		String locationDescr = result[0];
		String date = result[1];
		
		Location location = db.getLocation(locationDescr);
		
		
		receipt = db.getReceiptByLocationAndDate(location.getID(),date);
		return receipt;
	    
	}
	
	private void fillWorkIdFieldsFromSpinners(TableRow tr) throws Exception
	{
		String vehicleDescription;
		String itemDescription;
		
			
		vehicleDescription = getVehicleDescription(tr);
		
		Vehicle v = db.getVehicle(vehicleDescription);
		
		((TextView)tr.getChildAt(1)).setText(Integer.toString(v.getID()));
		
		
		itemDescription = getItemDescription(tr);
		Item i = db.getItem(itemDescription);
		((TextView)tr.getChildAt(2)).setText(Integer.toString(i.getID()));
		
		Receipt r = getReceiptFromTable(tr); 
		if (r != null)
		{
			((TextView)tr.getChildAt(3)).setText(Integer.toString(r.getID()));
		}

		
		
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
		
		// Mileage = child 5
		int mileage = Integer.parseInt(((TextView)tr.getChildAt(7)).getText().toString());
				
		
		// Notes = child 4
		String notes = ((TextView)tr.getChildAt(8)).getText().toString();
		
		return new Work(workid,vehicleDescription,itemid,receiptid,mileage,notes);
	

	}
	
	protected void deleteAllRows()
	{
		LayoutUtils.displayYesNoDialog(this, "Delete All", "Delete All Rows in Work Table?");
		if(LayoutUtils.getDialogResult())
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
	
	public void onEditButtonClick(View v) 
	{
		Intent intent = new Intent(this,DisplayWorkDetailActivity.class);
		
    	// Get the table row
    	TableRow tr = (TableRow)v.getParent();
    	
    	Work work = getWorkFromTableRow(tr);
    	//String keys = LayoutUtils.getKeysFromTableRow(tr);
    	
    	/// http://stackoverflow.com/questions/2736389/how-to-pass-object-from-one-activity-to-another-in-android
    	intent.putExtra("WorkClass", work);
    	
    			
    	try
    	{
    	Receipt receipt = db.getReceiptById(work.getReceiptID());
    	intent.putExtra("ReceiptClass", receipt);
        intent.putExtra("VehicleDescription",getVehicleDescription(tr));
        intent.putExtra("ItemDescription", getItemDescription(tr));
        
        

    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	
 
    	
		startActivity(intent);
	}
	
	protected void updateRow(TableRow tr)
	{
		
		
		Work work = getWorkFromTableRow(tr);
		String keys = LayoutUtils.getKeysFromTableRow(tr);
		//displayToast(keys);
		
	
		try 
		{
			db.updateWork(work);	
		}
		catch (Exception e)
		{
			displayMessageDialog(e.getMessage(),e.toString());
		}
		
		//displayToast("Updating...");
		//send click through to parent.
		
	}
	
	protected void saveNewRow(TableRow tr) 
	{
     	 //save button.
    	
    	
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
    	
    	
    	String keys = LayoutUtils.getKeysFromTableRow(tr);
    	//displayToast(keys);
    	try 
    	{
    		db.addWork(work);	
    	}
    	catch (android.database.sqlite.SQLiteConstraintException e)
    	{
    		displayMessageDialog("Could not add new record","Record " +keys+ " is already in the database.");
    	}
    	catch (Exception e)
    	
    	{
    		displayMessageDialog(e.getMessage(),e.toString());
    	}
    	
    	//displayToast(keys);
    	//send click through to parent.
    	displayToast("Saving...");
    	
    	drawTable();
    	

	}
	

    
}
