package com.jburto2.carmaintenance;


import java.util.List;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * 
 * @author jburton
 *
 * @class DisplayLocationActivity
 * 
 * @brief This class implements functionality for the main activity in Android Lookup. Start here.
 */

public class DisplayLocationActivity extends DisplayTableActivity  {
	
	DatabaseObject myDatabaseObject = new Location();
	
	@Override

	/**
	 * @fn protected void onCreate(Bundle savedInstanceState)
	 * @brief Method called when activity is created. Sets the content view to activity_main. 
	 * 
	 * @param savedInstanceState
	 */
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_location);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

		

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
	   
	    

        
        
        // Create the labels
    	// 0
    	TableRow tableRow = LayoutUtils.createTableRow(this);
    	tableRow.setId(0);
    	
        
    	// 1
    	TextView textView = LayoutUtils.createTextView(this, "Location ID", 15,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        

    	// 2
    	
        textView = LayoutUtils.createTextView(this, "Location Descripton", 15, Color.rgb(200,200,200), Color.rgb(51, 51, 51));

        tableRow.addView(textView);
        

        // 3
        textView = LayoutUtils.createTextView(this, "Save", 15,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
        
	    if (autoSave)
        {
        	textView.setVisibility(View.GONE);
        }
        else
        {
        	textView.setVisibility(View.VISIBLE);
        }
        tableRow.addView(textView);

	
        // 4
//        textView = LayoutUtils.createTextView(this, "Clear", 10,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
//        tableRow.addView(textView);
        // 5
        textView = LayoutUtils.createTextView(this, "Delete", 15,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
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

        List<DatabaseObject> locationlist = null;
        


        locationlist = dbSQLite.getAllLocations();
   
        int NUMBER_BUTTONS=2;
        for (int i = 0; i < locationlist.size(); i++){
        	
            // First row: Entered data
        	tableRow = LayoutUtils.createTableRow(this);
        	tableRow.setId(i+1);

        	

        	
        	Location location = (Location) locationlist.get(i);
        	

        	
        	        	
            // Data
        	// 0
        	textView = LayoutUtils.createTextView(this, Integer.toString(location.getID()), 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
        	textView.setVisibility(View.GONE);
        	tableRow.addView(textView);
            

        	
        	// 1
            textView = LayoutUtils.createTextView(this, location.getLocationDescription(), 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
            tableRow.addView(textView);
            
            addSaveFunctionToRow(tableRow);
            
	        ImageButton button;

//	        button = new ImageButton(this);
//	        button.setImageResource(android.R.drawable.ic_menu_edit);
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
	            	
	            	Location location = getLocationFromTableRow(tr);
	            	String keys = LayoutUtils.getKeysFromTableRow(tr);
	            	//displayToast(keys);
	            	

	            	try 
	            	{
	            		deleteFromDatabase(location);	
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
	    

        TableRow tableRow = LayoutUtils.createTableRow(this);
        
        // Data
    	// 0
    	TextView textView = LayoutUtils.createTextView(this, "-1", 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        
    
        
        // 1
        EditText editText = LayoutUtils.createEditText(this, "", 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
        tableRow.addView(editText);
        
        addSaveFunctionToRow(tableRow);
        
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
        
        ImageButton button = new ImageButton(this);
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
	
	
	protected Location getLocationFromTableRow(TableRow tr)
	{

		// Location ID = child 0
		int locationID = Integer.parseInt(((TextView)tr.getChildAt(0)).getText().toString());
		
		// Location Description = child 1
		String locationDescription = ((TextView)tr.getChildAt(1)).getText().toString();

		
		return new Location(locationID, locationDescription);
		
	

	}
	

	
	
	protected void updateRow(TableRow tr)
	{
    	Location location = getLocationFromTableRow(tr);
    	String keys = LayoutUtils.getKeysFromTableRow(tr);
    	//displayToast(keys);
    	

    	try 
    	{
    		updateInDatabase(location);	
    	}
    	catch (Exception e)
    	{
    		displayMessageDialog(e.getMessage(),e.toString());
    	}
		
	}
	
	protected void saveNewRow(TableRow tr) 
	{

    	Location location = getLocationFromTableRow(tr);
    	
    	
    	String keys = LayoutUtils.getKeysFromTableRow(tr);
    	//displayToast(keys);
    	try 
    	{
    		addToDatabase(location);	
    	}
    	catch (Exception e)
    	{
    		displayMessageDialog(e.getMessage(),e.toString());
    	}
    	
    	//displayToast(keys);
    	
    	drawTable();

		
	}




	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return Location.TABLE_NAME;
	}
	
	


    
    
}
