package com.jburto2.carmaintenance;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * 
 * @author jburton
 *
 * @class MainActivity
 * 
 * @brief This class implements functionality for the main activity in CarMaintenance. Start here.
 */

public class MainActivity extends Activity {
	

	private DatabaseHandler db = new DatabaseHandler(this);
	
	private void createData()
	{
	    db.deleteAllRecordsFromTable(Location.TABLE_NAME);
	    db.deleteAllRecordsFromTable(Item.TABLE_NAME);
	    db.deleteAllRecordsFromTable(Vehicle.TABLE_NAME);
	    db.deleteAllRecordsFromTable(Receipt.TABLE_NAME);
	    db.deleteAllRecordsFromTable(Work.TABLE_NAME);
		db.addVehicle(new Vehicle(0,"Chevette"));
		db.addVehicle(new Vehicle(0,"DeLorian"));
		db.addItem(new Item(0,"Blinker Fluid",10000,12));
		db.addItem(new Item(0,"Muffler bearings",20000,24));
		db.addItem(new Item(0,"Flux Capacitor",75000,60));
		db.addLocation(new Location(0,"Jiffy Lube"));
		Location l = null;
		try {
		l = db.getLocation("Jiffy Lube");
		}
		catch (Exception e)
		{
			displayMessageDialog("SQLite fail",e.getMessage());
			return;
		}
		
		//this(0,file, location_id, date, amount, mileage,  notes)
		db.addReceipt(new Receipt(0,"none",l.getID(),"02/04/2012",1999,90210,"Replaced blinker fluid. Checked muffler bearings." ));
		Receipt r = null;
		Vehicle v = null;
		Item it = null;
		Item it2 = null;
		try {
			r = db.getReceipt("none");
			v = db.getVehicle("Chevette");
			it = db.getItem("Blinker Fluid");
			it2 = db.getItem("Muffler bearings");
		}
		catch (Exception e)
		{
			
			displayMessageDialog("SQLite fail",e.getMessage());
			return;
		}
		
		db.addWork(new Work(0,v.getID(),it.getID(),r.getID(),"50ML Blinker Fluid"));
		db.addWork(new Work(0,v.getID(),it2.getID(),r.getID(),"Muffler bearings fine"));
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
	

	/**
	 * @fn public boolean onOptionsItemSelected(MenuItem item)
	 * @brief Handles menu item selection. 
	 * Only menu item here is the "action_about" for the info activity.
	 * @param item MenuItem that was selected
	 * @return true  
	 */
	public void onAboutButtonClick(View v) {
	

	    Intent intent = new Intent(this, DisplayInfoActivity.class);
	    startActivity(intent);

	}
	public void onVehicleButtonClick(View v) {
		

	    Intent intent = new Intent(this, DisplayVehicleActivity.class);
	    startActivity(intent);

	}
	public void onLocationButtonClick(View v) {
		
	    Intent intent = new Intent(this, DisplayLocationActivity.class);
	    startActivity(intent);

	}
	public void onWorkButtonClick(View v) {
		

	    Intent intent = new Intent(this, DisplayWorkActivity.class);
	    startActivity(intent);

	}

	
	public void onReceiptButtonClick(View v) {
		

	    Intent intent = new Intent(this, DisplayReceiptActivity.class);
	    startActivity(intent);

	}
	
	public void onItemButtonClick(View v) {
		

	    Intent intent = new Intent(this, DisplayItemActivity.class);
	    startActivity(intent);

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
	
		/// Menu from http://developer.android.com/guide/topics/ui/menus.html#options-menu
	    switch (item.getItemId()) {
	   
	    case R.id.action_about:
	    	Intent intent = new Intent(this, DisplayInfoActivity.class);
	    	startActivity(intent);

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
		TableLayoutUtils.displayToast(context, message);
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
    	
		TableLayoutUtils.displayMessageDialog(this, message, title);
    }
    
    
    
}
