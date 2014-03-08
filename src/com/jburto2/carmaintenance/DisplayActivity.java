/**
 * 
 */
package com.jburto2.carmaintenance;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author jburton
 *
 */
public class DisplayActivity extends Activity {
	
	public static final int IMAGE_REQUEST_CODE = 1;
	public static final int REQUEST_IMAGE_CAPTURE = 2;
	
	protected TextView imageTextViewHolder;
	private Bitmap bitmap;
	protected ImageView imageViewHolder;
	protected Uri photoUri;

	protected boolean autoSave = false;
	protected int colorTheme = LayoutUtils.HIGHLIGHT_COLOR;
	protected DatabaseHandler db = new DatabaseHandler(this);
	
	@Override

	/**
	 * @fn protected void onCreate(Bundle savedInstanceState)
	 * @brief Method called when activity is created. Sets the content view to activity_main. 
	 * 
	 * @param savedInstanceState
	 */
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    /// Preferences from http://developer.android.com/guide/topics/data/data-storage.html#pref
	    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
	    autoSave = settings.getBoolean(SettingsActivity.KEY_AUTOSAVE, false);
	    LayoutUtils.setHighlightColor(Integer.parseInt(settings.getString(SettingsActivity.KEY_HIGHLIGHT_COLOR, "2")));
	    colorTheme = LayoutUtils.HIGHLIGHT_COLOR;


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
    	LayoutUtils.displayToast(context,message);
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
    	
		LayoutUtils.displayMessageDialog(this,title,message);
    }
    
	
    public void dispatchSelectImageIntent(ImageView iv, TextView tv)
    {
    	/// Working with an image picker from http://www.vogella.com/tutorials/AndroidCamera/article.html

    	// save the view.
    	imageViewHolder = iv;
    	imageTextViewHolder = tv;

    	// create the intent
    	Intent intent = new Intent();
    	intent.setType("image/*");
    	intent.setAction(Intent.ACTION_GET_CONTENT);
    	intent.addCategory(Intent.CATEGORY_OPENABLE);
    	startActivityForResult(intent, IMAGE_REQUEST_CODE);
    	
    }
    
    public void dispatchTakePictureIntent(ImageView iv, TextView tv) {
		
		// save the view.
		imageViewHolder = iv;
		imageTextViewHolder = tv;
		
		/// Started with this http://developer.android.com/training/camera/photobasics.html
		/// But ran into this problem http://stackoverflow.com/questions/13912095/java-lang-nullpointerexception-on-bundle-extras-data-getextras
		
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) 
        {
            // Create the File where the photo should go
            File photoFile = null;
            try 
            {
                photoFile = createImageFile();
            } catch (IOException ex) 
            {
                // Error occurred while creating the File
                
            }
            // Continue only if the File was successfully created
            if (photoFile != null)
            {
            	photoUri = Uri.fromFile(photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,  photoUri                     );
                //takePictureIntent.putExtra("filename", "file:"+photoFile.getAbsolutePath());
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

	
	private File createImageFile() throws IOException {
	    // Create an image file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    String imageFileName = "JPEG_" + timeStamp + "_";
	    File storageDir = this.getExternalFilesDir("receipts");
	    File image = File.createTempFile(
	        imageFileName,  /* prefix */
	        ".jpg",         /* suffix */
	        storageDir      /* directory */
	    );

	    // Save a file: path for use with ACTION_VIEW intent
	    return image;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		super.onActivityResult(requestCode, resultCode, data);

		
		if (requestCode == IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK)
		{
			imageTextViewHolder.setText(data.getDataString());
			//LayoutUtils.loadImage(this,data.getData(),imageViewHolder);
			try
			{
				rotateAndSetImage(imageViewHolder,data.getData().getPath());
			}
			catch (Exception e)
			{
				imageViewHolder.setImageResource(R.drawable.ic_receipt);
			}
			
			
		}
		else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) 
		{
			/// Fixed null pointer exceptions from - http://www.androidhive.info/2013/09/android-working-with-camera-api/
	        try {
	            rotateAndSetImage(imageViewHolder,photoUri.getPath());
	            imageTextViewHolder.setText(photoUri.getPath());
	        } catch (NullPointerException e) {
	            e.printStackTrace();
	        }
	        
	    }
		else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_CANCELED)
		{
			File file = new File(photoUri.getPath());
			file.delete();
		}

		
	}
	
	protected void rotateAndSetImage(ImageView imageView, String path)
	{
    	
        // bitmap factory
        BitmapFactory.Options options = new BitmapFactory.Options();

        // downsizing image as it throws OutOfMemory Exception for larger
        // images
        options.inSampleSize = 4;

        
        final Bitmap bitmap = BitmapFactory.decodeFile(path,
                options);
	    // is this damn thing sideways? Should be in portrait mode
	    /// Rotating images from http://www.higherpass.com/Android/Tutorials/Working-With-Images-In-Android/3/
	    System.err.println("Bitmap: w="+bitmap.getWidth()+" h="+bitmap.getHeight());
	    if (bitmap.getWidth() <= bitmap.getHeight())
	    {
	    	imageView.setImageBitmap(bitmap);
	    } 
	    else
	    {
	        Matrix mat = new Matrix();
	        mat.postRotate(90);
	        Bitmap bMapRotate = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mat, true);
	        imageView.setImageBitmap(bMapRotate);
	    }
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
	

	public void onDateFieldClick(View v) {
		
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		LayoutUtils.displayDatePickerDialog(this, v, year, month, day);


	}
	
	@Override
	protected void onStop()
	{
		super.onStop();
		db.close();
	}



}
