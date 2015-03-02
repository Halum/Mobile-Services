package com.halum.mobileservices;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by Halum on 1/23/2015.
 */
public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.settings);
    }
}
