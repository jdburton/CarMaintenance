package com.jburto2.carmaintenance;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * 
 * @author jburton
 *
 * @class MainActivity
 * 
 * @brief This class implements functionality for the main activity in CarMaintenance. Start here.
 */

public class MainActivity extends DisplayActivity {
	

	
	
	private void createData()
	{
		deleteAllRecordsFromTable(Work.TABLE_NAME);
		deleteAllRecordsFromTable(Receipt.TABLE_NAME);
		deleteAllRecordsFromTable(Location.TABLE_NAME);
	    deleteAllRecordsFromTable(Item.TABLE_NAME);
	    deleteAllRecordsFromTable(Vehicle.TABLE_NAME);
	    
	    
	    
	    try
	    {
			addToDatabase(new Vehicle(0,"Chevette"));
			addToDatabase(new Vehicle(0,"DeLorian"));
			addToDatabase(new Item(0,"Blinker Fluid",10000,12));
			addToDatabase(new Item(0,"Muffler bearings",20000,24));
			addToDatabase(new Item(0,"Flux Capacitor",75000,60));
			addToDatabase(new Location(0,"Jiffy Lube"));
	    }
	    catch (Exception e)
	    {
	    
	    }
		Location l = null;
		try {
		l = dbSQLite.getLocation("Jiffy Lube");
		}
		catch (Exception e)
		{
			displayMessageDialog("SQLite fail",e.getMessage());
			return;
		}
		
		//this(0,file, location_id, date, amount, mileage,  notes)
		try
		{
			System.err.println(0+","+"/mnt/sdcard/JPEG_20140306_022918_2121550581.jpg"+","+l.getID()+","+"2012-04-2"+","+1999+","+"Replaced blinker fluid. Checked muffler bearings.");
			addToDatabase(new Receipt(0,"/mnt/sdcard/JPEG_20140306_022918_2121550581.jpg",l.getID(),"2012-04-2",1999,"Replaced blinker fluid. Checked muffler bearings." ));
		}
		catch (Exception e)
		{
			displayMessageDialog("SQLite fail",e.getMessage());
		}
		Receipt r = null;
		Vehicle v = null;
		Item it = null;
		Item it2 = null;
		try {
			r = dbSQLite.getReceipt("/mnt/sdcard/JPEG_20140306_022918_2121550581.jpg");
			v = dbSQLite.getVehicle("Chevette");
			it = dbSQLite.getItem("Blinker Fluid");
			it2 = dbSQLite.getItem("Muffler bearings");
		}
		catch (Exception e)
		{
			
			displayMessageDialog("SQLite fail",e.getMessage());
			return;
		}
		try
		{
			addToDatabase(new Work(0,v.getID(),it.getID(),r.getID(),90210,"50ML Blinker Fluid"));
			addToDatabase(new Work(0,v.getID(),it2.getID(),r.getID(),90210,"Muffler bearings fine"));
		}
		catch (Exception e)
		{
			displayMessageDialog("SQLite fail",e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
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
		//deleteAndRebuildAllFromRemote();


		
	}
	
	/**
	 * @fn protected void onConfigurationChanged(Configuration config)
	 * @brief Method called when configuration changes. Load landscape config if in landscape mode, default otherwise. 
	 * 
	 * @param config Configuration.
	 */

	public void onConfigurationChanged(Configuration config)
	{
		//super.onConfigurationChanged(config);
		if (config.orientation == Configuration.ORIENTATION_LANDSCAPE)
		{
			setContentView(R.layout.activity_main_land);
		}
		else
		{
			setContentView(R.layout.activity_main);
		}
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
	
		Intent intent;
		
		/// Menu from http://developer.android.com/guide/topics/ui/menus.html#options-menu
	    switch (item.getItemId()) {
	   
	    
	    case R.id.action_about:
	    	intent = new Intent(this, DisplayInfoActivity.class);
	    	startActivity(intent);
	    	break;
	    
	    case R.id.action_settings:
	    	intent = new Intent(this, SettingsActivity.class);
	    	startActivity(intent);
	    	break;
	    	
	    case R.id.create_data:
	    	createData();
	    	break;
	    
	    case R.id.resync_from_remote:
	    	//LayoutUtils.displayYesNoDialog(this, "Do you want to download data from remote servers? This will overwrite all your local data?", "Re-sync");
	    	//if (LayoutUtils.getDialogResult())
	    	//{
	    		deleteAndRebuildAllFromRemote();
	    		
	    	//}
	
	    	
	    }
	    return true;
	}


    
    
}
