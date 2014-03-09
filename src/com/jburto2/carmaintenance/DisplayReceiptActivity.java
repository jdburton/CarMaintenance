package com.jburto2.carmaintenance;


import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
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
	
	
	DatabaseObject myDatabaseObject = new Receipt();

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
		
        List<DatabaseObject> locationList = dbSQLite.getAllLocations();
        
        String [] spinnerList = new String[locationList.size()+1];
        spinnerList[0] = "";
        
        for (int i = 0; i < locationList.size(); i++)
        {
        	spinnerList[i+1] = (((Location)locationList.get(i)).getLocationDescription());
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
			location = dbSQLite.getLocation(((TextView)v).getText().toString());
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
		
		super.drawTable();

		// http://stackoverflow.com/questions/5669747/android-how-to-use-dip-density-independent-pixel-in-code
	    final int sixty_dip = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());
	    final int eighty_dip = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80, getResources().getDisplayMetrics());
		
		
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
    	TableRow tableRow = LayoutUtils.createTableRow(this);
    	tableRow.setId(0);
    	
    	TextView textView = LayoutUtils.createTextView(this, "Receipt ID", 15,LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        
    	// 1
    	textView = LayoutUtils.createTextView(this, "Location ID", 15,LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        

    	// 2
    	
        textView = LayoutUtils.createTextView(this, "Location Descripton", 15, LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
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
        textView = LayoutUtils.createTextView(this, "Receipt Image", 15, LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
        tableRow.addView(textView);

        // 4
        textView = LayoutUtils.createTextView(this, "Date", 15, LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
        tableRow.addView(textView);
        

        
        // 6
        textView = LayoutUtils.createTextView(this, "Amount", 15,LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
        tableRow.addView(textView);

        // 7
        textView = LayoutUtils.createTextView(this, "Receipt Notes", 15,LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
        tableRow.addView(textView);
        
        // 8
        textView = LayoutUtils.createTextView(this, "Receipt Uri", 15, LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
        textView.setVisibility(View.GONE);
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

		
        // 9
        textView = LayoutUtils.createTextView(this, "Detail", 15,LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
        tableRow.addView(textView);
        // 10
        textView = LayoutUtils.createTextView(this, "Delete", 15,LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
        tableRow.addView(textView);
        
        // New Row Indicator = Must be last 
        textView = LayoutUtils.createTextView(this, "New Row", 15, LayoutUtils.DARK_GRAY,LayoutUtils.LIGHT_GRAY);
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
		


        
        //LinkedList<DatabaseObject>();
        //rowList.add(tableRow);

        List<DatabaseObject> receiptlist = null;
        
        if (vid < 0)
        {
        	receiptlist = dbSQLite.getAllReceipts();
        }
        else
        {
        	receiptlist = dbSQLite.getAllReceiptsByLocationId(vid);
        }
        
        
        
        int NUMBER_BUTTONS=2;
        for (int i = 0; i < receiptlist.size(); i++){
        	
            // First row: Entered data
        	tableRow = LayoutUtils.createTableRow(this);
        	tableRow.setId(i+1);

        	

        	
        	Receipt singlereceipt = (Receipt) receiptlist.get(i);
        	
        	Location location = null;

        	
        	try 
        	{

	        	location = dbSQLite.getLocationById(singlereceipt.getLocationID());

        	}
        	catch (Exception e)
        	{
        		displayMessageDialog("SQL fail",e.getMessage());
        		return;
        	}
        	
        	        	
            // Data
        	// 0
        	textView = LayoutUtils.createTextView(this, Integer.toString(singlereceipt.getID()), 15, LayoutUtils.DARK_GRAY,LayoutUtils.LIGHT_GRAY);
        	textView.setVisibility(View.GONE);
        	tableRow.addView(textView);
            
        	// 1
        	textView = LayoutUtils.createTextView(this, Integer.toString(singlereceipt.getLocationID()), 15, LayoutUtils.DARK_GRAY,LayoutUtils.LIGHT_GRAY);
        	textView.setVisibility(View.GONE);
        	tableRow.addView(textView);
     

        	// 2
            textView = LayoutUtils.createTextView(this, location.getLocationDescription(), 15, LayoutUtils.DARK_GRAY,LayoutUtils.LIGHT_GRAY);
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
            ImageButton imageButton = new ImageButton(this);    	
            imageButton.setClickable(true);
            

            imageButton.setLayoutParams(new TableRow.LayoutParams(sixty_dip,eighty_dip));
            
            imageButton.setOnClickListener(new View.OnClickListener() 
            {
    			
                @Override
                public void onClick(View v)
                {
    	
    	        	//("touched image!");
    				//send click through to parent.
    				TableRow tr = (TableRow)v.getParent();
    				TextView tv = (TextView)tr.getChildAt(7);
    				dispatchTakePictureIntent((ImageButton)v,tv);
    				
    	        	tr.performClick();
     
                }
            });
            
            //LayoutUtils.loadImage(this,singlereceipt.getFile(),imageViewHolder);
            try
            {
            	//imageButton.setImageURI(Uri.parse(singlereceipt.getFile()));

            	rotateAndSetImage(imageButton,singlereceipt.getFile());
            	
            }
            catch (Exception e)
            {
    			imageButton.setImageResource(R.drawable.ic_receipt);
    				
            }
            imageButton.setScaleType(ScaleType.CENTER_CROP);
            tableRow.addView(imageButton);
            
            // 4
            
            textView = LayoutUtils.createDateTextView(this, singlereceipt.getDate(), 15, LayoutUtils.DARK_GRAY,LayoutUtils.WHITE);
            tableRow.addView(textView);
            

            
            // 6
            EditText editText = LayoutUtils.createEditText(this, Integer.toString(singlereceipt.getAmount()), 15, LayoutUtils.DARK_GRAY,LayoutUtils.WHITE);
            tableRow.addView(editText);

            // 7
             editText = LayoutUtils.createEditText(this, singlereceipt.getNotes(), 15, LayoutUtils.DARK_GRAY,LayoutUtils.WHITE);
            tableRow.addView(editText);
            
            // 8
            textView = LayoutUtils.createTextView(this, singlereceipt.getFile(), 15, LayoutUtils.DARK_GRAY,LayoutUtils.LIGHT_GRAY);
            textView.setVisibility(View.GONE);
            tableRow.addView(textView);
            
            addSaveFunctionToRow(tableRow);
            
            
	        ImageButton button; 
	        
	        
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
	            	
	            	Receipt receipt = getReceiptFromTableRow(tr);
	            	String keys = LayoutUtils.getKeysFromTableRow(tr);
	            	
	            	

	            	try 
	            	{
	            		deleteFromDatabase(receipt);	
	            	}
	            	catch (Exception e)
	            	{
	            		displayMessageDialog(e.getMessage(),e.toString());
	            	}
	            	
	            	
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
    	Spinner spinner = null;
    	if (vid < 0)
    	{
            List<DatabaseObject> locationList = dbSQLite.getAllLocations();
            String [] locationArray = new String [locationList.size()];
            for (int i = 0; i < locationList.size(); i++)
            {
            	locationArray[i] = ((Location)locationList.get(i)).getLocationDescription();
            }
            spinner = LayoutUtils.createSpinner(this, locationArray);
            tableRow.addView(spinner);
    	}
    	else
    	{
    		Spinner vSpinner = (Spinner) findViewById(R.id.locationSpinner);
    		
	        textView = LayoutUtils.createTextView(this, vSpinner.getSelectedItem().toString() , 15, LayoutUtils.DARK_GRAY,LayoutUtils.WHITE);
	    	textView.setVisibility(View.GONE);
	        tableRow.addView(textView);


    	}
    	
        // 3
        ImageButton imageButton = new ImageButton(this);    	
        imageButton.setClickable(true);
        imageButton.setLayoutParams(new TableRow.LayoutParams(120,160));
        imageButton.setScaleType(ScaleType.CENTER_CROP);
        
        imageButton.setOnClickListener(new View.OnClickListener() 
        {
			
            @Override
            public void onClick(View v)
            {
	
				//send click through to parent.
				TableRow tr = (TableRow)v.getParent();
				TextView tv = (TextView)tr.getChildAt(7);
				dispatchTakePictureIntent((ImageButton)v,tv);
				
	        	tr.performClick();
 
            }
        });
        
        //imageButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_receipt));

		imageButton.setImageResource(R.drawable.ic_receipt);

        
        tableRow.addView(imageButton);
        // TODO: Override with image selection.
        
        
        // 4
        textView = LayoutUtils.createDateTextView(this, "", 15, LayoutUtils.DARK_GRAY,LayoutUtils.WHITE);
        //TODO: Override with date picker dialog.

        tableRow.addView(textView);
        
        // 5
        EditText editText = LayoutUtils.createEditText(this, "0", 15, LayoutUtils.DARK_GRAY,LayoutUtils.WHITE);
        tableRow.addView(editText);
        
        
        // 6
        editText = LayoutUtils.createEditText(this, "", 15, LayoutUtils.DARK_GRAY,LayoutUtils.WHITE);
        tableRow.addView(editText);
        
        // 7
        textView = LayoutUtils.createTextView(this, "", 15, LayoutUtils.DARK_GRAY,LayoutUtils.LIGHT_GRAY);
        textView.setVisibility(View.GONE);
        tableRow.addView(textView);

        addSaveFunctionToRow(tableRow);
        ImageButton button;
        
    	button = new ImageButton(this);
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
	
	private void fillReceiptIdFieldsFromSpinners(TableRow tr) throws Exception
	{
		String locationDescription = getLocationDescription(tr);
		
		Location v = dbSQLite.getLocation(locationDescription);
		((TextView)tr.getChildAt(1)).setText(Integer.toString(v.getID()));
		

	}
	
	private Receipt getReceiptFromTableRow(TableRow tr)
	{
		// ID - child 0
		int  receiptid =  Integer.parseInt(((TextView)tr.getChildAt(0)).getText().toString());
		
		// Location ID = child 1
		int locationid = Integer.parseInt(((TextView)tr.getChildAt(1)).getText().toString());
		// File = child 7
		String file = ((TextView)tr.getChildAt(7)).getText().toString();
		
		// Date = child 4
		String date = ((TextView)tr.getChildAt(4)).getText().toString();

		// Amount = child 5
		int amount = Integer.parseInt(((TextView)tr.getChildAt(5)).getText().toString());

		
		// Notes = child 6
		String notes = ((TextView)tr.getChildAt(6)).getText().toString();
		
		return new Receipt(receiptid,file,locationid,date,amount,notes);
	

	}
	

	protected void updateRow(TableRow tr)
	{
    	Receipt receipt = getReceiptFromTableRow(tr);
    	String keys = LayoutUtils.getKeysFromTableRow(tr);
    	//(keys);
    	

    	try 
    	{
    		updateInDatabase(receipt);	
    	}
    	catch (Exception e)
    	{
    		displayMessageDialog(e.getMessage(),e.toString());
    	}
    	

	}
	
	protected void saveNewRow(TableRow tr)
	{
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
		
		
		String keys = LayoutUtils.getKeysFromTableRow(tr);
		//(keys);
	
		try 
		{
			addToDatabase(receipt);	
		}
		catch (Exception e)
		{
			displayMessageDialog(e.getMessage(),e.toString());
		}
		
		drawTable();
	}
	
	private String getLocationDescription(TableRow tr) throws Exception
	{
		
		return getValueFromSpinner(tr,2);
	}

	public void onEditButtonClick(View v) 
	{
		Intent intent = new Intent(this,DisplayReceiptDetailActivity.class);
		
    	// Get the table row
    	TableRow tr = (TableRow)v.getParent();
    	
    	Receipt receipt = getReceiptFromTableRow(tr);
    	intent.putExtra("ReceiptClass", receipt);
    	try {
			intent.putExtra("LocationDescription",getLocationDescription(tr));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	
		startActivity(intent);
	}



	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return Receipt.TABLE_NAME;
	}
	
	


}
