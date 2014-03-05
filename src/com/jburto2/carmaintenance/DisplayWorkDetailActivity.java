package com.jburto2.carmaintenance;


import java.io.IOException;
import java.io.InputStream;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author jburton
 *
 * @class DisplayInfoActivity
 * 
 * @brief This class controls activities that displays the information page. 
 */

public class DisplayWorkDetailActivity extends DisplayDetailActivity {
	
	public static final int IMAGE_REQUEST_CODE = 1;
	private TextView imageTextViewHolder;
	private Bitmap bitmap;
	private ImageView imageViewHolder;

	@SuppressLint("NewApi")
	@Override
	/**
	 * @fn protected void onCreate(Bundle savedInstanceState)
	 * @brief Method called when activity is created. 
	 * This method sets the content view to activity_display_info, then creates an WebView view on which it displays the help information.
	 * The help information is stored in an an HTML file in the assets directory. R.string.about_text gives the location of the file.
	 * 
	 * More on scrolling from 	http://stackoverflow.com/questions/16623337/how-to-scroll-table-layout-in-horizontal-and-vertical-in-android 
	 * 
	 * @param savedInstanceState
	 */
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_work);
		
        // Get the message from the intent
        Intent intent = getIntent();
        Work work = (Work)intent.getSerializableExtra("WorkClass");
        String vehicleDescription = intent.getStringExtra("VehicleDescription");
        String receiptLocation = intent.getStringExtra("ReceiptFile");
        String itemDescription = intent.getStringExtra("ItemDescription");

        TextView vehicleId = (TextView) findViewById(R.id.vehicleIdTextView);
        TextView itemId = (TextView) findViewById(R.id.itemIdTextView);
        TextView receiptId = (TextView) findViewById(R.id.receiptIdTextView);

        TextView vehicleField = (TextView) findViewById(R.id.vehicleTextView);
        TextView itemField = (TextView) findViewById(R.id.itemTextView);
        TextView receiptField = (TextView) findViewById(R.id.receiptTextView);

        ImageView receiptImage = (ImageView) findViewById(R.id.receiptImageView);
        
        EditText workDescription = (EditText) findViewById(R.id.noteEditText);
		
        vehicleId.setText(Integer.toString(work.getVehicleID()));
        itemId.setText(Integer.toString(work.getItemID()));
        receiptId.setText(Integer.toString(work.getReceiptID()));
        //loadImage(receiptLocation,)
        try
        {
        	receiptImage.setImageURI(Uri.parse(receiptLocation));
        }
        catch (Exception e)
        {
        	System.err.println("Could not load image "+receiptLocation);
        }
        
        vehicleField.setText(vehicleDescription);
        itemField.setText(itemDescription);
        receiptField.setText(receiptLocation);
        
        workDescription.setText(work.getNotes());

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
		case R.id.action_cancel:


			NavUtils.navigateUpFromSameTask(this);
			break;
		case R.id.action_save:
			LayoutUtils.displayToast(this, "Saving...");
			break;
			
	    case R.id.action_about:
	    	Intent intent = new Intent(this, DisplayInfoActivity.class);
	    	startActivity(intent);
	    	break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void onVehicleButtonClick(View v) {
		

	    Intent intent = new Intent(this, DisplayVehicleActivity.class);
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
	

}
