
package com.aniruddhc.xPlod.preferences;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.aniruddhc.xPlod.R;

public class SettingsFragment extends PreferenceFragment {

    public SettingsFragment() {
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // Load settings XML
        int preferencesResId = R.xml.settings;
        addPreferencesFromResource(preferencesResId);
        super.onActivityCreated(savedInstanceState);
    }
}
