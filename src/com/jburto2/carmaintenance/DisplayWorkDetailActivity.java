package com.jburto2.carmaintenance;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.EditText;
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
	
	TextView workId;
    TextView vehicleId;
    TextView itemId;
    TextView receiptId;

    TextView vehicleField;
    TextView itemField;
    TextView receiptField;
    TextView receiptDate;

    ImageView receiptImage;
    
    EditText workMileage;
    EditText workDescription;
    
    DatabaseObject myDatabaseObject = new Work();

    
    
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
		setContentView(R.layout.activity_detail_work);
		
        // Get the message from the intent
        Intent intent = getIntent();
        Work work = (Work)intent.getSerializableExtra("WorkClass");
        String vehicleDescription = intent.getStringExtra("VehicleDescription");
        Receipt receipt = (Receipt)intent.getSerializableExtra("ReceiptClass");
        String receiptFile = receipt.getFile();
        String itemDescription = intent.getStringExtra("ItemDescription");

        workId = (TextView) findViewById(R.id.workIdTextView);
        vehicleId = (TextView) findViewById(R.id.vehicleIdTextView);
        itemId = (TextView) findViewById(R.id.itemIdTextView);
        receiptId = (TextView) findViewById(R.id.receiptIdTextView);
        receiptDate = (TextView) findViewById(R.id.dateTextView);

        vehicleField = (TextView) findViewById(R.id.vehicleTextView);
        itemField = (TextView) findViewById(R.id.itemTextView);
        receiptField = (TextView) findViewById(R.id.receiptTextView);

        receiptImage = (ImageView) findViewById(R.id.receiptImageView);
        
        workMileage = (EditText) findViewById(R.id.mileageEditText);
        workDescription = (EditText) findViewById(R.id.noteEditText);
		
        workId.setText(Integer.toString(work.getID()));
        vehicleId.setText(Integer.toString(work.getVehicleID()));
        itemId.setText(Integer.toString(work.getItemID()));
        receiptId.setText(Integer.toString(work.getReceiptID()));
        
        //loadImage(receiptFile,)
        try
        {
        	
        	rotateAndSetImage(receiptImage,receipt.getFile());

        }
        catch (Exception e)
        {
        	System.err.println("Could not load image "+receiptFile);
        }
        
        vehicleField.setText(vehicleDescription);
        itemField.setText(itemDescription);
        receiptField.setText(receiptFile);
        workMileage.setText(Integer.toString(work.getMileage()));
        workDescription.setText(work.getNotes());
        receiptDate.setText(receipt.getDate());

	}


	

	protected void update()
	{
		
		Work work = new Work(Integer.parseInt(workId.getText().toString()),Integer.parseInt(vehicleId.getText().toString()),Integer.parseInt(itemId.getText().toString()),Integer.parseInt(receiptId.getText().toString()),Integer.parseInt(workMileage.getText().toString()),workDescription.getText().toString());

		try 
		{
			
			updateInDatabase(work);

		}
		catch (Exception e)
		{
			LayoutUtils.displayMessageDialog(this,e.getMessage(),e.toString());
		}
		
		//displayToast("Updating...");
		//send click through to parent.
		
	}
	

}
