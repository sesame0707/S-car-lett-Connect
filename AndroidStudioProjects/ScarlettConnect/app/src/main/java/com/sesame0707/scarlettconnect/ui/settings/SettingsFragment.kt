package com.sesame0707.scarlettconnect.ui.settings

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.sesame0707.scarlettconnect.R
import com.sesame0707.scarlettconnect.targetIpAddress

class SettingsFragment : PreferenceFragmentCompat() {
    private var prefSettingsConnect: Preference? = null
    private var prefSettingsCheckIp: Preference? = null
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        prefSettingsConnect = findPreference("pref_settings_connect")
        prefSettingsCheckIp = findPreference("pref_settings_check_ip")

        prefSettingsConnect?.setOnPreferenceClickListener {
            Log.d("Preferences", "pref_settings_connect was clicked")
            val openWiFiSettingsIntent = Intent("android.settings.WIFI_SETTINGS")
            startActivity(openWiFiSettingsIntent)
            Toast.makeText(
                context,
                "Choose a car and go back",
                Toast.LENGTH_LONG
            ).show()
            true // Return true if the click is handled.
        }

        prefSettingsCheckIp?.setOnPreferenceClickListener {
            Log.d("Preferences", "pref_settings_check_ip was clicked")
            Toast.makeText(
                context,
                targetIpAddress,
                Toast.LENGTH_SHORT
            ).show()
            true // Return true if the click is handled.
        }
    }
}