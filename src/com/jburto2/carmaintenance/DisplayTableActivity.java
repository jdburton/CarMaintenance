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
import android.view.ViewGroup;
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

public abstract class DisplayTableActivity extends Activity  {
	
	public DatabaseHandler db = new DatabaseHandler(this);
	protected boolean autoSave = false;

	
	@Override

	/**
	 * @fn protected void onCreate(Bundle savedInstanceState)
	 * @brief Method called when activity is created. Sets the content view to activity_main. 
	 * 
	 * @param savedInstanceState
	 */
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);



	}
	


	
	@SuppressLint("NewApi")

	protected void drawTable(){
	    /// Preferences from http://developer.android.com/guide/topics/data/data-storage.html#pref
	    SharedPreferences settings = getSharedPreferences("CarMaintenance",MODE_PRIVATE);
	    //boolean autoSave = settings.valueOf("autoSave", false);
		
		

	}
	
	@SuppressLint("NewApi")
	protected void addNewRow() {
		
	    /// Preferences from http://developer.android.com/guide/topics/data/data-storage.html#pref
	    SharedPreferences settings = getSharedPreferences("CarMaintenance",MODE_PRIVATE);
	    //boolean autoSave = settings.valueOf("autoSave", false);
		
	}
	
	

	

	protected void deleteAllRows()
	{
		
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
		
		getMenuInflater().inflate(R.menu.table_menu, menu);
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
	   
		case android.R.id.home:

			NavUtils.navigateUpFromSameTask(this);
			return true; 
			
	    case R.id.action_about:
	    	intent = new Intent(this, DisplayInfoActivity.class);
	    	startActivity(intent);
	    	break;

	    case R.id.action_add:
	    	addNewRow();
	    	break;
	    	
	    case R.id.action_delete:
	    	deleteAllRows();
	    	break;
	    }
	    return true;
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
    
    public void displayMessageDialog(String title, String message )
    {
    	
		TableLayoutUtils.displayMessageDialog(this,title,message);
    }
    
    protected void updateRow(TableRow tr)
    {
    	
    }
    
    protected void saveNewRow(TableRow tr) 
    {
    	
    }

    protected void addSaveFunctionToRow(TableRow tableRow)
    {
    	
    	// Create the save/update button.
    	ImageButton button;
   
	    
    	
        button= new ImageButton(this);
        button.setImageResource(android.R.drawable.ic_menu_save);
        button.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v)
            {
            	// Get the table row
        		TableRow tr = (TableRow)v.getParent();
        		
				String flag = ((TextView)tr.getChildAt(tr.getChildCount()-1)).getText().toString();

				if (Boolean.valueOf(flag))
				{
					saveNewRow(tr);
				}
				else
				{
					updateRow(tr);
				}

            	tr.performClick();
            	
           }
        });
        
        /// if autosave is enabled, must hide the button and override the row listener. Otherwise, enable the button.
	    if (autoSave)
	    {
	    	button.setVisibility(View.GONE);
	    	button.setEnabled(false);
	    	tableRow.setOnClickListener(new View.OnClickListener() 
	    	{
				
				@Override
				public void onClick(View v) 
				{
					// TODO Auto-generated method stub
					
					// get the table Row
					//TableRow tr = (TableRow)v;
					// get the table layout above
					
					boolean update;
					
					// if the current row isn't selected, we're going to need to update.
					if (! v.isSelected())
					{
						//displayToast(context,"Row change!");
						update = true;
					}
					else
					{
						update = false;
					}
					
					ViewGroup tl = (ViewGroup)v.getParent();
					
					for (int index = 0; index < tl.getChildCount(); index++)
					{
						// unselect all children
						TableRow unselected;
						try 
						{
							unselected = (TableRow)tl.getChildAt(index);
						}
						catch (java.lang.ClassCastException cce)
						{
							continue;
						}
						
						// if we need to update
						if ( unselected.isSelected() && update == true )
						{
							// Check the new row flag at the end.
							String flag = ((TextView)unselected.getChildAt(unselected.getChildCount()-1)).getText().toString();

							if (Boolean.valueOf(flag))
							{
								saveNewRow(unselected);
							}
							else
							{
								updateRow(unselected);
							}
						}
						unselected.setSelected(false);
						unselected.setBackgroundColor(TableLayoutUtils.DARK_GRAY);
					}
					
					
					//displayToast(context,"Table row "+v.getId()+"clicked");
					v.setBackgroundColor(TableLayoutUtils.HIGHLIGHT_COLOR);
					v.setSelected(true);
					
					
				}
			} );
	
	    }
	    
	    tableRow.addView(button);

    }
}
    

    
    
    
