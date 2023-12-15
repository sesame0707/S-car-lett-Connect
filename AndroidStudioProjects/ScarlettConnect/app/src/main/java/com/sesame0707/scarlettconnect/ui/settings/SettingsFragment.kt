package com.sesame0707.scarlettconnect.ui.settings

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.sesame0707.scarlettconnect.CommonMethods
import com.sesame0707.scarlettconnect.CommonVariables
import com.sesame0707.scarlettconnect.R

class SettingsFragment : PreferenceFragmentCompat() {

    private var prefSettingsConnect: Preference? = null
    private var prefSettingsCheckIp: Preference? = null
    private var commonMethods: CommonMethods? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        prefSettingsConnect = findPreference("pref_settings_connect")
        prefSettingsCheckIp = findPreference("pref_settings_check_ip")
        commonMethods = CommonMethods()

        prefSettingsConnect?.setOnPreferenceClickListener {
            Log.d("Preferences", "pref_settings_connect was clicked")
            val openWiFiSettingsIntent = Intent("android.settings.WIFI_SETTINGS")
            startActivity(openWiFiSettingsIntent)
            CommonVariables.toast = commonMethods!!.showToast(
                CommonVariables.toast,
                context,
                "Choose a car and go back",
                Toast.LENGTH_LONG
            )
            true // Return true if the click is handled.
        }

        prefSettingsCheckIp?.setOnPreferenceClickListener {
            Log.d("Preferences", "pref_settings_check_ip was clicked")
            CommonVariables.toast = commonMethods!!.showToast(
                CommonVariables.toast,
                context,
                CommonVariables.targetIpAddress
            )
            true // Return true if the click is handled.
        }
    }
}