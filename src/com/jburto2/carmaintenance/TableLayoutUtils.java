/**
 * 
 */
package com.jburto2.carmaintenance;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author jburton
 * @class TableLayoutUtils
 * 
 * @brief A series of static methods that create widgets for a table layout.
 *
 */
@SuppressLint("NewApi")
public class TableLayoutUtils 
{
	/**
	 * @fn public static TableRow createTableRow(Context context)
	 * Creates a table row with no margins and no padding that will expand to the entire length of the parent TableLayout.
	 * @param context
	 * @return Created TableRow.
	 */
	public static TableRow createTableRow(final Context context)
	{
		TableRow tableRow = new TableRow(context);
		///http://stackoverflow.com/questions/2481455/set-margins-in-a-linearlayout-programmatically
		TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(2, 2, 2, 2);
		tableRow.setLayoutParams(layoutParams);
    	tableRow.setFocusable(true);
    	tableRow.setClickable(true);
    	tableRow.requestDisallowInterceptTouchEvent(false);
    	//tableRow.setBackgroundColor(Color.rgb(51,51,51));

    	tableRow.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				// get the table Row
				//TableRow tr = (TableRow)v;
				// get the table layout above
				ViewGroup tl = (ViewGroup)v.getParent();
				
				for (int index = 0; index < tl.getChildCount(); index++)
				{
					// unselect all children
					View unselected = tl.getChildAt(index);
					unselected.setSelected(false);
					unselected.setBackgroundColor(Color.rgb(51, 51, 51));
				}
				
				
				displayToast(context,"Table row "+v.getId()+"clicked");
				v.setBackgroundColor(Color.rgb(0,0,255));
				v.setSelected(true);
				

				
			}
		} );
    	tableRow.setFocusable(true);
    	tableRow.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (hasFocus)
				{
					// get the table Row
					//TableRow tr = (TableRow)v;
					// get the table layout above
					ViewGroup tl = (ViewGroup)v.getParent();
					
					for (int index = 0; index < tl.getChildCount(); index++)
					{
						// unselect all children
						View unselected = tl.getChildAt(index);
						unselected.setSelected(false);
						unselected.setBackgroundColor(Color.rgb(51, 51, 51));
					}
					
					
					//displayToast(context,"Table row "+v.getId()+"clicked");
					v.setBackgroundColor(Color.rgb(0,255,0));
					v.setSelected(true);
				}
				else
				{
					v.setBackgroundColor(Color.rgb(255,0,0));
				}

				
			}
				
			
		});
    

		
		return tableRow;
		
	}
	/**
	 * @fn public static View createHorizontalLine(Context context,int color)
	 * @brief Creates a horizontal line of height 1 that goes across the parent.
	 * @param context 
	 * @param color Numerical representation of color. Use android.graphics.Color.rgb(red,green,blue)
	 * @return Created horizontal line View Object.
	 */
	public static View createHorizontalLine(Context context,int color)
	{
		/// Creating lines http://stackoverflow.com/questions/5092116/how-can-i-add-separating-lines-between-my-tablerows-that-are-created-programmati
		View h_line = new View(context);
        h_line.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1));
        h_line.setBackgroundColor(color);
        h_line.setPadding(0, 0, 0, 0);
        return h_line;
	}
	/**
	 * @fn public static View createVerticalLine(Context context,int color)
	 * @brief Creates a vertical line of width 1 that goes across the parent.
	 * @param context 
	 * @param color Numerical representation of color. Use android.graphics.Color.rgb(red,green,blue)
	 * @return Created vertical line View Object.
	 */
	
	public static View createVerticalLine(Context context,int color)
	{
		/// Creating lines http://stackoverflow.com/questions/5092116/how-can-i-add-separating-lines-between-my-tablerows-that-are-created-programmati
		View v_line = new View(context);
        v_line.setLayoutParams(new TableRow.LayoutParams(1,TableRow.LayoutParams.MATCH_PARENT));
        v_line.setBackgroundColor(color);
        v_line.setPadding(0, 0, 0, 0);
        return v_line;
	}
	/**
	 * @fn public static TextView createTextView(Context context,String message,int size,int textColor,int backgroundColor)
	 * @brief Creates a textview object.
	 * @param context 
	 * @param message Message to be displayed in the text view
	 * @param size Text size
	 * @param textColor Numerical representation of color. Use android.graphics.Color.rgb(red,green,blue)
	 * @param backgroundColor Numerical representation of color. Use android.graphics.Color.rgb(red,green,blue)
	 * @return Created TextView Object.
	 */
	
	public static TextView createTextView(Context context,String message,int size,int textColor,int backgroundColor)
	{
		/// http://stackoverflow.com/questions/11504635/layout-margin-for-text-view-programmatically
		TextView textView = new TextView(context);
		TableRow.LayoutParams tvlp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(tvlp);
        tvlp.setMargins(2, 2, 2, 2);
        textView.setText(message);
        textView.setTextSize(size);
        textView.setBackgroundColor(backgroundColor);
        textView.setTextColor(textColor);
        /// http://stackoverflow.com/questions/432037/how-do-i-center-text-horizontally-and-vertical-in-a-textview-in-android
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        


        
        return textView;
	}
	
	/**
	 * @fn public static EditText createEditText(Context context,String message,int size,int textColor,int backgroundColor)
	 * @brief Creates a textview object.
	 * @param context 
	 * @param message Message to be displayed in the text view
	 * @param size Text size
	 * @param textColor Numerical representation of color. Use android.graphics.Color.rgb(red,green,blue)
	 * @param backgroundColor Numerical representation of color. Use android.graphics.Color.rgb(red,green,blue)
	 * @return Created EditText Object.
	 */
	
	public static EditText createEditText(final Context context,String message,int size,int textColor,int backgroundColor)
	{
		/// http://stackoverflow.com/questions/11504635/layout-margin-for-text-view-programmatically
		EditText editText = new EditText(context);
		TableRow.LayoutParams tvlp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT);
        editText.setLayoutParams(tvlp);
        tvlp.setMargins(2, 2, 2, 2);
        editText.setText(message);
        editText.setTextSize(size);
        editText.setBackgroundColor(backgroundColor);
        editText.setTextColor(textColor);
        /// http://stackoverflow.com/questions/432037/how-do-i-center-text-horizontally-and-vertical-in-a-textview-in-android
        editText.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        

        
        //Send click event to the parent if the focus changed.
        /// http://stackoverflow.com/questions/8397609/onclicklistener-listens-only-on-the-second-time
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus)
	            {
	                
					
					ViewGroup vp = (ViewGroup) v.getParent();
					vp.performClick();
	            }
				
			}
		});

        
        
        return editText;
	}
	
	/**
	 * @fn public static Spinner createSpinner(Context context,String message,int size,int textColor,int backgroundColor)
	 * @brief Creates a Spinner object.
	 * @param context 
	 * @param message Message to be displayed in the text view
	 * @param size Text size
	 * @param textColor Numerical representation of color. Use android.graphics.Color.rgb(red,green,blue)
	 * @param backgroundColor Numerical representation of color. Use android.graphics.Color.rgb(red,green,blue)
	 * @return Created Spinner Object.
	 */
	
	public static Spinner createSpinner(Context context, ArrayAdapter<CharSequence> adapter,	int backgroundColor)
	{
		/// http://stackoverflow.com/questions/11504635/layout-margin-for-text-view-programmatically
		Spinner spinner = new Spinner(context);
		TableRow.LayoutParams tvlp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.MATCH_PARENT);
        spinner.setLayoutParams(tvlp);
        tvlp.setMargins(2, 2, 2, 2);
        //spinner.setText(message);
        //spinner.setTextSize(size);
        spinner.setBackgroundColor(backgroundColor);
        

    	// Specify the layout to use when the list of choices appears
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	// Apply the adapter to the spinner
    	spinner.setAdapter(adapter);
    	//spinner.setOnItemSelectedListener(context);
    	
        //spinner.setTextColor(textColor);
        /// http://stackoverflow.com/questions/432037/how-do-i-center-text-horizontally-and-vertical-in-a-textview-in-android
        spinner.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        


        
        return spinner;
	}
	/**
	 * @fn public static WebView createHtmlView(Context context,String html)
	 * @brief This creates a new WebView object to display the HTML code in string.
	 * @param context
	 * @param html HTML code to be displayed. This is a string of the code itself, not a link to a file.
	 * @return Created WebView object.
	 */

	public static WebView createHtmlView(Context context,String html)
	{
	    WebView webview = new WebView(context);
	    webview.loadData(html, "text/html", null);
	    webview.setPadding(5, 0, 5, 0);
	    return webview;
	}
	

    /**
     * @fn public void displayToast(String message)
     * @brief Displays a popup "Toast" message to the user.
     * Displaying toasts from http://developer.android.com/guide/topics/ui/notifiers/toasts.html
     * @param message Message to display
     */
	
	public static void displayToast(Context context, String message)
	{
		
		int duration = Toast.LENGTH_SHORT;
		
		Toast toast = Toast.makeText(context, message, duration);
		toast.show();
	}
	
	   /**
     * @fn public void displayMessageDialog(String message, String title)
     * @brief Displays a message dialog to the user.
     * Displaying message dialogs from http://www.mkyong.com/android/android-alert-dialog-example/
     * @param message Message to display
     * @param title Title of dialog
     * 
     */
    
    public static void displayMessageDialog(Context context, String message, String title)
    {
    	

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		// set title
		if (title == null)
		{
			title = "Message";
		}
		alertDialogBuilder.setTitle(title);
 
		// set dialog message
		alertDialogBuilder
				.setMessage(message)
				.setCancelable(false)
				.setPositiveButton("OK",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				  })
				;
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
    }
}
