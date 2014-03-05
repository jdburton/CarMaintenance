package com.jburto2.carmaintenance;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * 
 * @author jburton
 *
 * @class SettingsActivity
 * 
 * @brief This class controls activities that displays the information page.
 * 
 *  Derived from information at http://developer.android.com/guide/topics/ui/settings.html
 *  Also http://stackoverflow.com/questions/4966816/how-to-create-radiobutton-group-in-preference-xml-window
 *  and http://stackoverflow.com/questions/6148952/how-to-get-selected-text-and-value-android-listpreference
 *  
 */
public class SettingsActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {
	
	public static final String KEY_AUTOSAVE = "autoSave";
	public static final String KEY_HIGHLIGHT_COLOR = "highlightColor";
	
    @SuppressWarnings("deprecation")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
    	ListPreference highlightPreference = (ListPreference) findPreference(KEY_HIGHLIGHT_COLOR);
    	highlightPreference.setSummary(highlightPreference.getEntry());
    	LayoutUtils.setHighlightColor(Integer.parseInt(highlightPreference.getValue()));


        
    }
    
    @SuppressWarnings("deprecation")
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    	//LayoutUtils.displayToast(this, key);
        if (key.equals(SettingsActivity.KEY_AUTOSAVE)) {
        	

        }
        if (key.equals(SettingsActivity.KEY_HIGHLIGHT_COLOR))
        {
        	ListPreference highlightPreference = (ListPreference) findPreference(key);
        	highlightPreference.setSummary(highlightPreference.getEntry());
        	LayoutUtils.setHighlightColor(Integer.parseInt(highlightPreference.getValue()));
        	
        }
    }
    
    @SuppressWarnings("deprecation")
	@Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @SuppressWarnings("deprecation")
	@Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }

}