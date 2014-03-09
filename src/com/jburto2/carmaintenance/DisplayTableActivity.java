package com.jburto2.carmaintenance;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

public abstract class DisplayTableActivity extends DisplayActivity  {
	
	

	
	protected abstract String getTableName(); 

	
	@SuppressLint("NewApi")
	@Override
	/**
	 * @fn protected void onCreate(Bundle savedInstanceState)
	 * @brief Method called when activity is created.  
	 * 
	 * @param savedInstanceState
	 */
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }


	}
	
	/**
	 * @fn private void setupActionBar()
	 * 
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 * This enables the up/home button to allow users to return to the main screen.
	 * 
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	
	@Override
	protected void onPause()
	{
		super.onPause();
		
		if (autoSave)
		{			
		saveAllRows();
		}
	}
	
	@Override
    protected void onResume() {
        super.onResume();

        // if any settings have changed, redraw the table on resume.
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        if (autoSave != settings.getBoolean(SettingsActivity.KEY_AUTOSAVE, false) || colorTheme != LayoutUtils.HIGHLIGHT_COLOR )
        {
	        drawTable();
        }
    }

	
	@SuppressLint("NewApi")

	protected void drawTable(){
	    //displayToast("Autosave is "+autoSave);
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
	    autoSave = settings.getBoolean(SettingsActivity.KEY_AUTOSAVE, false);
	    
		

	}
	
	@SuppressLint("NewApi")
	protected void addNewRow() {
		
	    /// Preferences from http://developer.android.com/guide/topics/data/data-storage.html#pref
	    SharedPreferences settings =  PreferenceManager.getDefaultSharedPreferences(this);
	    autoSave = settings.getBoolean(SettingsActivity.KEY_AUTOSAVE, false);
		
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
	    	
	    case R.id.action_save:
	    	displayToast("Saving all rows...");
	    	saveAllRows();
	    	break;
	    	
	    case R.id.action_settings:
	    	intent = new Intent(this, SettingsActivity.class);
	    	startActivity(intent);
	    	break;
	    }
	    return true;
	}


    
    protected abstract void updateRow(TableRow tr);
    
    protected abstract void saveNewRow(TableRow tr);

	protected void deleteAllRows()
	{
		LayoutUtils.displayYesNoDialog(this, "Delete All", "Delete All Rows in "+getTableName()+" Table?");
		if(LayoutUtils.getDialogResult())
		{
			displayToast("Deleted all rows!");
			dbSQLite.deleteAllRecordsFromTable(getTableName());
			drawTable();
		}
	}
	
    
    protected void saveAllRows()
    {
    	// Grid table MUST be named tlGridTable!
    	ViewGroup tl = (ViewGroup) findViewById(R.id.tlGridTable);
    	saveAllRows(tl);
    }
    
    protected void saveAllRows(ViewGroup tl)
    {
    	
    	// skip first row of labels
		for (int index = 1; index < tl.getChildCount(); index++)
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
			
			unselected.setSelected(false);
			unselected.setBackgroundColor(LayoutUtils.DARK_GRAY);
		}
    	
    	
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
					tr.performClick();
				}

            	
            	
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
						return;
					}
					
					ViewGroup tl = (ViewGroup)v.getParent();
					//skip the first row of lables
					for (int index = 1; index < tl.getChildCount(); index++)
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
						if ( unselected.isSelected())
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
						unselected.setBackgroundColor(LayoutUtils.DARK_GRAY);
					}

					
					
					//displayToast(context,"Table row "+v.getId()+"clicked");
					v.setBackgroundColor(LayoutUtils.HIGHLIGHT_COLOR);
					v.setSelected(true);
					
					
				}
			} );
	
	    }
	    
	    tableRow.addView(button);

    }
    
    protected String getValueFromSpinner(TableRow tr, int index)
    {
		String spinnerValue;
		try 
		{
			spinnerValue = ((Spinner)tr.getChildAt(index)).getSelectedItem().toString();
		}
		catch (java.lang.ClassCastException ce)
		{
			spinnerValue = ((TextView)tr.getChildAt(index)).getText().toString();
		}
		
		return spinnerValue;
	}
}
    

    
    
    
