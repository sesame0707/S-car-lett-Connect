package com.sesame0707.scarlettconnect.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.sesame0707.scarlettconnect.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}