package com.sesame0707.scarlettconnect.ui.settings

import android.os.Bundle
import android.util.Log
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.sesame0707.scarlettconnect.R

class SettingsFragment : PreferenceFragmentCompat() {
    private var prefSettingsConnect: Preference? = null
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        prefSettingsConnect = findPreference("pref_settings_connect")

        prefSettingsConnect?.setOnPreferenceClickListener {
            Log.d("Preferences", "Feedback was clicked")
            true // Return true if the click is handled.
        }
//        prefSettingsConnect?.setOnPreferenceChangeListener { preference, newValue ->   true
//
//        Log.d(
//            "TAG",
//            "(BOM) onCreate(SettingsFragment:PreferenceManager.findPreference):SettingsFragment"
//        )
//        true
    }
}