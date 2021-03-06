package com.jburto2.carmaintenance;


import java.util.List;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
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
 * @class DisplayItemActivity
 * 
 * @brief This class implements functionality for the main activity in Android Lookup. Start here.
 */

public class DisplayItemActivity extends DisplayTableActivity {
	
	
	DatabaseObject myDatabaseObject = new Item();

	
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
    	TableRow tableRow = LayoutUtils.createTableRow(this);
    	tableRow.setId(0);
    	
        
    	// 1
    	TextView textView = LayoutUtils.createTextView(this, "Item ID", 15,LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        

    	// 2
    	
        textView = LayoutUtils.createTextView(this, "Item Descripton", 15, LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);

        tableRow.addView(textView);
        
        
        textView = LayoutUtils.createTextView(this, "Mileage Interval", 15, LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);

        tableRow.addView(textView);
        
        textView = LayoutUtils.createTextView(this, "Time Interval (Months)", 15, LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);

        tableRow.addView(textView);

        // 3
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

	
        // 4
        //textView = LayoutUtils.createTextView(this, "Clear", 10,LayoutUtils.LIGHT_GRAY, LayoutUtils.DARK_GRAY);
        //tableRow.addView(textView);
        // 5
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
		


        
        //LinkedList<TableRow> rowList = new LinkedList<TableRow>();
        //rowList.add(tableRow);

        List<DatabaseObject> itemlist = null;
        


        itemlist = dbSQLite.getAllItems();
   
        int NUMBER_BUTTONS=2;
        for (int i = 0; i < itemlist.size(); i++){
        	
            // First row: Entered data
        	tableRow = LayoutUtils.createTableRow(this);
        	tableRow.setId(i+1);

        	

        	
        	Item item = (Item) itemlist.get(i);
        	

        	
        	        	
            // Data
        	// 0
        	textView = LayoutUtils.createTextView(this, Integer.toString(item.getID()), 15, LayoutUtils.DARK_GRAY,LayoutUtils.LIGHT_GRAY);
        	textView.setVisibility(View.GONE);
        	tableRow.addView(textView);
            

        	
        	// 1
            textView = LayoutUtils.createTextView(this, item.getItemDescription(), 15, LayoutUtils.DARK_GRAY,LayoutUtils.LIGHT_GRAY);
            tableRow.addView(textView);
            
            // 2
        	EditText editText = LayoutUtils.createEditText(this, Integer.toString(item.getMileageInterval()), 15, LayoutUtils.DARK_GRAY,LayoutUtils.WHITE);
        	editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        	tableRow.addView(editText);
        	
        	// 3
        	editText = LayoutUtils.createEditText(this, Integer.toString(item.getTimeInterval()), 15, LayoutUtils.DARK_GRAY,LayoutUtils.WHITE);
        	editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        	tableRow.addView(editText);
            

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
	            	String keys = LayoutUtils.getKeysFromTableRow(tr);
	            	//displayToast(keys);
	            	

	            	try 
	            	{
	            		deleteFromDatabase(item);	
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
    	TextView textView = LayoutUtils.createTextView(this, "-1", 15, LayoutUtils.DARK_GRAY,LayoutUtils.LIGHT_GRAY);
    	textView.setVisibility(View.GONE);
    	tableRow.addView(textView);
        
    
        
        // 1
        EditText editText = LayoutUtils.createEditText(this, "", 15, LayoutUtils.DARK_GRAY,LayoutUtils.WHITE);
        tableRow.addView(editText);
        
        // 2
     	editText = LayoutUtils.createEditText(this, "0", 15, LayoutUtils.DARK_GRAY,LayoutUtils.WHITE);
     	tableRow.addView(editText);
     	
     	// 3
     	editText = LayoutUtils.createEditText(this, "0", 15, LayoutUtils.DARK_GRAY,LayoutUtils.WHITE);
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
        textView = LayoutUtils.createTextView(this, "true", 15, LayoutUtils.DARK_GRAY,LayoutUtils.LIGHT_GRAY);
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
	

	
	protected void updateRow(TableRow tr)
	{
		
    	
    	Item item = getItemFromTableRow(tr);
    	String keys = LayoutUtils.getKeysFromTableRow(tr);
    	//displayToast(keys);
    	

    	try 
    	{
    		updateInDatabase(item);	
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
    	
    	
    	String keys = LayoutUtils.getKeysFromTableRow(tr);

    	try 
    	{
    		addToDatabase(item);	
    	}
    	catch (Exception e)
    	{
    		displayMessageDialog(e.getMessage(),e.toString());
    	}
    	
    	drawTable();
		
	}




	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return Item.TABLE_NAME;
	}



    
}
