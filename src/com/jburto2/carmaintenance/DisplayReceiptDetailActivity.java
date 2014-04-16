package com.jburto2.carmaintenance;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
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



public class DisplayReceiptDetailActivity extends DisplayDetailActivity {
	
	public static final int IMAGE_REQUEST_CODE = 1;
	private TextView imageTextViewHolder;
	private Bitmap bitmap;
	private ImageView imageViewHolder;
	
    private TextView receiptId;
    private TextView locationId;
    private TextView locationField;

    private TextView receiptFile;
    
    private ImageView receiptImage;
    
    private EditText receiptDate;
    private EditText receiptAmount;
    private EditText receiptNotes;
    
    DatabaseObject myDatabaseObject = new Receipt();

    
    
	@SuppressLint("NewApi")
	@Override
	/**
	 * @fn protected void onCreate(Bundle savedInstanceState)
	 *
	 * @param savedInstanceState
	 */
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_receipt);
		
        // Get the message from the intent
        Intent intent = getIntent();

        Receipt receipt = (Receipt)intent.getSerializableExtra("ReceiptClass");
        String locationDescription = intent.getStringExtra("LocationDescription");


        locationId = (TextView) findViewById(R.id.locationIdTextView);
        receiptId = (TextView) findViewById(R.id.receiptIdTextView);
        locationField = (TextView) findViewById(R.id.locationTextView);
        receiptFile = (TextView) findViewById(R.id.receiptTextView);
        receiptImage = (ImageView) findViewById(R.id.receiptImageView);
        receiptDate = (EditText) findViewById(R.id.dateEditText);
        
        receiptAmount = (EditText) findViewById(R.id.amountEditText);
        receiptNotes = (EditText) findViewById(R.id.noteEditText);
		
        receiptId.setText(Integer.toString(receipt.getID()));
        locationId.setText(Integer.toString(receipt.getLocationID()));
        
 
 
        try
        {
        	//receiptImage.setImageURI(Uri.parse(receipt.getFile()));
        	rotateAndSetImage(receiptImage,receipt.getFile());
        }
        catch (Exception e)
        {
        	System.err.println("Could not load image "+receiptFile);
        }
        
        locationField.setText(locationDescription);
        receiptFile.setText(receipt.getFile());
        receiptDate.setText(receipt.getDate());
        receiptAmount.setText(Integer.toString(receipt.getAmount()));
        receiptNotes.setText(receipt.getNotes());

	}






	
	public void onCameraButtonClick(View v) 
	{
		
		
		dispatchTakePictureIntent(receiptImage,receiptFile);
		
		
	}
	

	protected void update()
	{
		
		//Work work = new Work(Integer.parseInt(workId.getText().toString()),Integer.parseInt(vehicleId.getText().toString()),Integer.parseInt(itemId.getText().toString()),Integer.parseInt(receiptId.getText().toString()),Integer.parseInt(workMileage.getText().toString()),workDescription.getText().toString());
		Receipt receipt = new Receipt(Integer.parseInt(receiptId.getText().toString()),receiptFile.getText().toString(),Integer.parseInt(locationId.getText().toString()),receiptDate.getText().toString(),Integer.parseInt(receiptAmount.getText().toString()),receiptNotes.getText().toString());
		try 
		{
			db.updateReceipt(receipt);	
		}
		catch (Exception e)
		{
			LayoutUtils.displayMessageDialog(this,e.getMessage(),e.toString());
		}
		
		//displayToast("Updating...");
		//send click through to parent.
		
	}
	

}
