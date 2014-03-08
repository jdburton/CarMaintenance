package com.jburto2.carmaintenance;

import java.io.IOException;
import java.io.InputStream;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

/**
 * 
 * @author jburton
 *
 * @class DisplayInfoActivity
 * 
 * @brief This class controls activities that displays the information page. 
 */

public abstract class DisplayDetailActivity extends DisplayActivity {
	
	protected DatabaseHandler db = new DatabaseHandler(this);

	@SuppressLint("NewApi")
	@Override
	/**
	 * @fn protected void onCreate(Bundle savedInstanceState)
	 * @brief Method called when activity is created. 
ore on scrolling from 	http://stackoverflow.com/questions/16623337/how-to-scroll-table-layout-in-horizontal-and-vertical-in-android 
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
	/**
	 * @fn public boolean onCreateOptionsMenu(Menu menu) 
	 * @brief Inflate the menu; this adds items to the action bar if it is present.
	 * @param menu The menu
	 * @return true
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.detail_menu, menu);
		return true;
	}

	@Override
	/**
	 * @fn onOptionsItemSelected(MenuItem item) 
	 * This ID represents the Home or Up button. In the case of this
	 * activity, the Up button is shown. Use NavUtils to allow users
	 * to navigate up one level in the application structure. For
	 * more details, see the Navigation pattern on Android Design:
	 * 
	 * http://developer.android.com/design/patterns/navigation.html#up-vs-back	
	 * 
	 * @param item The MenuItem
	 * @return If Home or Up, navigate up and return true.
	 * @return Otherwise, parent class functionality.
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				if (autoSave)
				{
					LayoutUtils.displayToast(this, "Saving...");
					update();
				}
			case R.id.action_cancel:
				
				super.onBackPressed();
				break;
			case R.id.action_save:
				LayoutUtils.displayToast(this, "Saving...");
				update();
				break;
				
		    case R.id.action_about:
		    	Intent intent = new Intent(this, DisplayInfoActivity.class);
		    	startActivity(intent);
		    	break;
			    
		    case R.id.action_settings:
		    	intent = new Intent(this, SettingsActivity.class);
		    	startActivity(intent);
		    	break;
		}
		return super.onOptionsItemSelected(item);
	}

	
	// Working with an image picker from http://www.vogella.com/tutorials/AndroidCamera/article.html

	protected void loadImage(String uriString, ImageView imageView) 
	{
		try
		{
			Uri resource = Uri.parse(uriString);
			loadImage(resource, imageView);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	protected void loadImage(Uri resource,ImageView imageView) 
	{
		InputStream stream  = null;
		Bitmap bitmap = null;
		try 
		{

			stream = getContentResolver().openInputStream(resource);
			bitmap = BitmapFactory.decodeStream(stream);
	
			imageView.setImageBitmap(bitmap);
			
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
	

	
	protected abstract void update();
	
}
