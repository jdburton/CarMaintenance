package com.jburto2.carmaintenance;


import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
 * @class DisplayItemActivity
 * 
 * @brief This class implements functionality for the main activity in Android Lookup. Start here.
 */

public class DisplayItemActivity extends DisplayTableActivity {
	

	
	@Override

	/**
	 * @fn protected void onCreate(Bundle savedInstanceState)
	 * @brief Method called when activity is created. Sets the content view to activity_main. 
	 * 
	 * @param savedInstanceState
	 */
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_item);
		

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
    	TableRow tableRow = TableLayoutUtils.createTableRow(this);
    	tableRow.setId(0);
    	
        
    	// 1
    	TextView textView = TableLayoutUtils.createTextView(this, "Item ID", 15,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        

    	// 2
    	
        textView = TableLayoutUtils.createTextView(this, "Item Descripton", 15, Color.rgb(200,200,200), Color.rgb(51, 51, 51));

        tableRow.addView(textView);
        
        
        textView = TableLayoutUtils.createTextView(this, "Mileage Interval", 15, Color.rgb(200,200,200), Color.rgb(51, 51, 51));

        tableRow.addView(textView);
        
        textView = TableLayoutUtils.createTextView(this, "Time Interval (Months)", 15, Color.rgb(200,200,200), Color.rgb(51, 51, 51));

        tableRow.addView(textView);

        // 3
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

	
        // 4
        //textView = TableLayoutUtils.createTextView(this, "Clear", 10,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
        //tableRow.addView(textView);
        // 5
        textView = TableLayoutUtils.createTextView(this, "Delete", 10,Color.rgb(200,200,200), Color.rgb(51, 51, 51));
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

        List<Item> itemlist = null;
        


        itemlist = db.getAllItems();
   
        int NUMBER_BUTTONS=2;
        for (int i = 0; i < itemlist.size(); i++){
        	
            // First row: Entered data
        	tableRow = TableLayoutUtils.createTableRow(this);
        	tableRow.setId(i+1);

        	

        	
        	Item item = itemlist.get(i);
        	

        	
        	        	
            // Data
        	// 0
        	textView = TableLayoutUtils.createTextView(this, Integer.toString(item.getID()), 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
        	textView.setVisibility(View.GONE);
        	tableRow.addView(textView);
            

        	
        	// 1
            textView = TableLayoutUtils.createTextView(this, item.getItemDescription(), 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
            tableRow.addView(textView);
            
            // 2
        	textView = TableLayoutUtils.createTextView(this, Integer.toString(item.getMileageInterval()), 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
        	tableRow.addView(textView);
        	
        	// 3
        	textView = TableLayoutUtils.createTextView(this, Integer.toString(item.getTimeInterval()), 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
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
	            	
	            	Item item = getItemFromTableRow(tr);
	            	String keys = TableLayoutUtils.getKeysFromTableRow(tr);
	            	//displayToast(keys);
	            	

	            	try 
	            	{
	            		db.deleteItem(item);	
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
	    

        TableRow tableRow = TableLayoutUtils.createTableRow(this);
        
        // Data
    	// 0
    	TextView textView = TableLayoutUtils.createTextView(this, "-1", 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        
    
        
        // 1
        EditText editText = TableLayoutUtils.createEditText(this, "", 15, Color.rgb(51, 51, 51),Color.rgb(255, 255, 255));
        tableRow.addView(editText);
        
        // 2
     	editText = TableLayoutUtils.createEditText(this, "0", 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
     	tableRow.addView(editText);
     	
     	// 3
     	editText = TableLayoutUtils.createEditText(this, "0", 15, Color.rgb(51, 51, 51),Color.rgb(200,200,200));
     	tableRow.addView(editText);
         
     	addSaveFunctionToRow(tableRow);
        
        ImageButton button;
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
        
        // New Row Indicator = Must be last 
        textView = TableLayoutUtils.createTextView(this, "true", 15, TableLayoutUtils.DARK_GRAY,TableLayoutUtils.LIGHT_GRAY);
        textView.setVisibility(View.GONE);
        tableRow.addView(textView);
        
        tableLayout.addView(tableRow);
	}
	
	
	private Item getItemFromTableRow(TableRow tr)
	{

		// Item ID = child 0
		int itemID = Integer.parseInt(((TextView)tr.getChildAt(0)).getText().toString());
		
		// Item Description = child 1
		String itemDescription = ((TextView)tr.getChildAt(1)).getText().toString();

		int mileageInterval = Integer.parseInt(((TextView)tr.getChildAt(2)).getText().toString());
		
		int timeInterval = Integer.parseInt(((TextView)tr.getChildAt(3)).getText().toString());
		
		return new Item(itemID, itemDescription,mileageInterval,timeInterval);
		
	

	}
	

	protected void deleteAllRows()
	{
		TableLayoutUtils.displayYesNoDialog(this, "Delete All", "Delete All Rows in Item Table?");
		if(TableLayoutUtils.getDialogResult())
		{
			displayToast("Deleted all rows!");
			db.deleteAllRecordsFromTable(Item.TABLE_NAME);
			drawTable();
		}
	}
	
	protected void updateRow(TableRow tr)
	{
		
    	
    	Item item = getItemFromTableRow(tr);
    	String keys = TableLayoutUtils.getKeysFromTableRow(tr);
    	//displayToast(keys);
    	

    	try 
    	{
    		db.updateItem(item);	
    	}
    	catch (Exception e)
    	{
    		displayMessageDialog(e.getMessage(),e.toString());
    	}
    	
    	//displayToast(keys);
    	//send click through to parent.
	}
	
	protected void saveNewRow(TableRow tr) 
	{

    	Item item = getItemFromTableRow(tr);
    	
    	
    	String keys = TableLayoutUtils.getKeysFromTableRow(tr);

    	try 
    	{
    		db.addItem(item);	
    	}
    	catch (Exception e)
    	{
    		displayMessageDialog(e.getMessage(),e.toString());
    	}
    	
    	drawTable();
		
	}



    
}
