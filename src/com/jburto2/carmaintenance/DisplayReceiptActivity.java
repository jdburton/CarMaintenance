package com.jburto2.carmaintenance;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
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
	
	public static final int IMAGE_REQUEST_CODE = 1;
	private TextView imageTextViewHolder;
	private Bitmap bitmap;
	private ImageButton imageButtonHolder;

	
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
		
		super.drawTable();

		
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
        textView = TableLayoutUtils.createTextView(this, "Receipt Image", 15, Color.rgb(200,200,200), Color.rgb(51, 51, 51));
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
        textView = TableLayoutUtils.createTextView(this, "Receipt Uri", 15, Color.rgb(200,200,200), Color.rgb(51, 51, 51));
        tableRow.addView(textView);

        // 9
        textView = TableLayoutUtils.createTextView(this, "Save", 15,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
        
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
        //textView = TableLayoutUtils.createTextView(this, "Edit", 10,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
        //tableRow.addView(textView);
        // 10
        textView = TableLayoutUtils.createTextView(this, "Delete", 15,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
        tableRow.addView(textView);
        
        // New Row Indicator = Must be last 
        textView = TableLayoutUtils.createTextView(this, "New Row", 15, TableLayoutUtils.DARK_GRAY,TableLayoutUtils.LIGHT_GRAY);
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
            ImageButton imageButton = new ImageButton(this);    	
            imageButton.setClickable(true);
            imageButton.setLayoutParams(new TableRow.LayoutParams(60,80));
            imageButton.setScaleType(ScaleType.CENTER_CROP);
            imageButton.setOnClickListener(new View.OnClickListener() 
            {
    			
                @Override
                public void onClick(View v)
                {
    	
    	        	//("touched image!");
    				//send click through to parent.
    				TableRow tr = (TableRow)v.getParent();
    				TextView tv = (TextView)tr.getChildAt(8);
    				onImageFieldClick((ImageButton)v,tv);
    				
    	        	tr.performClick();
     
                }
            });
            
            loadImage(singlereceipt.getFile(),imageButton);
            tableRow.addView(imageButton);
            
            // 4
            
            textView = TableLayoutUtils.createDateTextView(this, singlereceipt.getDate(), 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
            tableRow.addView(textView);
            
            // 5
            EditText editText = TableLayoutUtils.createEditText(this, Integer.toString(singlereceipt.getMileage()), 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
            tableRow.addView(editText);
            
            // 6
            editText = TableLayoutUtils.createEditText(this, Integer.toString(singlereceipt.getAmount()), 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
            tableRow.addView(editText);

            // 7
             editText = TableLayoutUtils.createEditText(this, singlereceipt.getNotes(), 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
            tableRow.addView(editText);
            
            // 8
            textView = TableLayoutUtils.createTextView(this, singlereceipt.getFile(), 15, Color.rgb(51, 51, 51),Color.rgb(200, 200, 200));
            tableRow.addView(textView);
            
            addSaveFunctionToRow(tableRow);
            
            
	        ImageButton button; 
	        

//	        button = new ImageButton(this);
//	        button.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
//	        button.setId(i*NUMBER_BUTTONS+1);
//	        button.setOnClickListener(new View.OnClickListener(){
//	            public void onClick(View v){
//	                 // Do some operation for minus after getting v.getId() to get the current row
//	            	// http://stackoverflow.com/questions/14112044/android-how-to-get-the-id-of-a-parent-view
//	            	("Button is"+v.getId()+ " on row "+ ((TableRow)v.getParent()).getId());
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
	            	
	            	Receipt receipt = getReceiptFromTableRow(tr);
	            	String keys = TableLayoutUtils.getKeysFromTableRow(tr);
	            	
	            	

	            	try 
	            	{
	            		db.deleteReceipt(receipt);	
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
	        textView = TableLayoutUtils.createTextView(this, "false", 15, TableLayoutUtils.DARK_GRAY,TableLayoutUtils.LIGHT_GRAY);
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
        ImageButton imageButton = new ImageButton(this);    	
        imageButton.setClickable(true);
        imageButton.setLayoutParams(new TableRow.LayoutParams(60,80));
        imageButton.setScaleType(ScaleType.CENTER_CROP);
        
        imageButton.setOnClickListener(new View.OnClickListener() 
        {
			
            @Override
            public void onClick(View v)
            {
	
				//send click through to parent.
				TableRow tr = (TableRow)v.getParent();
				TextView tv = (TextView)tr.getChildAt(8);
				onImageFieldClick((ImageButton)v,tv);
				
	        	tr.performClick();
 
            }
        });
        
        tableRow.addView(imageButton);
        // TODO: Override with image selection.
        
        
        // 4
        textView = TableLayoutUtils.createDateTextView(this, "", 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
        //TODO: Override with date picker dialog.

        tableRow.addView(textView);
        
        // 5
        EditText editText = TableLayoutUtils.createEditText(this, "0", 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
        tableRow.addView(editText);
        
        // 6
        editText = TableLayoutUtils.createEditText(this, "0", 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
        tableRow.addView(editText);
        
        // 7
        editText = TableLayoutUtils.createEditText(this, "", 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
        tableRow.addView(editText);
        
        // 8
        textView = TableLayoutUtils.createTextView(this, "", 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
        tableRow.addView(textView);

        addSaveFunctionToRow(tableRow);
        ImageButton button;

//        button = new ImageButton(this);
//        button.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
//        
//        button.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                 // Do some operation for minus after getting v.getId() to get the current row
//            	// http://stackoverflow.com/questions/14112044/android-how-to-get-the-id-of-a-parent-view
//            	("Button is"+v.getId()+ " on row "+ ((TableRow)v.getParent()).getId());
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
        
        // New Row Indicator = Must be last 
        textView = TableLayoutUtils.createTextView(this, "true", 15, TableLayoutUtils.DARK_GRAY,TableLayoutUtils.LIGHT_GRAY);
        textView.setVisibility(View.GONE);
        tableRow.addView(textView);
        
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
		// File = child 8
		String file = ((TextView)tr.getChildAt(8)).getText().toString();
		
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
	
	protected void updateRow(TableRow tr)
	{
    	Receipt receipt = getReceiptFromTableRow(tr);
    	String keys = TableLayoutUtils.getKeysFromTableRow(tr);
    	//(keys);
    	

    	try 
    	{
    		db.updateReceipt(receipt);	
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
		
		
		String keys = TableLayoutUtils.getKeysFromTableRow(tr);
		//(keys);
		
		
		
	
		try 
		{
			db.addReceipt(receipt);	
		}
		catch (Exception e)
		{
			displayMessageDialog(e.getMessage(),e.toString());
		}
		
		drawTable();
	}
	
	public void onLocationButtonClick(View v) {
		

	    Intent intent = new Intent(this, DisplayLocationActivity.class);
	    startActivity(intent);

	}
	
	// Working with an image picker from http://www.vogella.com/tutorials/AndroidCamera/article.html
	
	public void onImageFieldClick(ImageButton iv, TextView tv) {
		// save the view.
		imageButtonHolder = iv;
		imageTextViewHolder = tv;
		
		
		
		// create the intent
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		startActivityForResult(intent, IMAGE_REQUEST_CODE);
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK)
		{
			imageTextViewHolder.setText(data.getDataString());
			loadImage(data.getData(),imageButtonHolder);
		}
			
			

		
	}
	
	protected void loadImage(String uriString, ImageButton imageButton) 
	{
		try
		{
			Uri resource = Uri.parse(uriString);
			loadImage(resource, imageButton);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	protected void loadImage(Uri resource,ImageButton imageButton) 
	{
		InputStream stream  = null;
		try 
		{
		
			if (bitmap != null) 
			{
				bitmap.recycle();
			}
			stream = getContentResolver().openInputStream(resource);
			bitmap = BitmapFactory.decodeStream(stream);
	
			imageButton.setImageBitmap(bitmap);
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		catch (java.lang.OutOfMemoryError e)
		{
			e.printStackTrace();
			System.gc();
		}
		finally {
			
			if (stream != null)
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}
}
