package com.sesame0707.scarlettconnect.ui.settings

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.sesame0707.scarlettconnect.CommonMethods
import com.sesame0707.scarlettconnect.CommonVariables
import com.sesame0707.scarlettconnect.R

class SettingsFragment : PreferenceFragmentCompat() {

    private var preferenceConnect: Preference? = null
    private var preferenceCheckIp: Preference? = null
    private var commonMethods: CommonMethods? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)

        preferenceConnect = findPreference("preference_connect")
        preferenceCheckIp = findPreference("preference_check_ip")
        commonMethods = CommonMethods()

        preferenceConnect?.setOnPreferenceClickListener {
            val openWiFiSettingsIntent = Intent("android.settings.WIFI_SETTINGS")
            startActivity(openWiFiSettingsIntent)
            CommonVariables.toast = commonMethods!!.showToast(
                CommonVariables.toast,
                context,
                context?.getString(R.string.information_preference_connect),
                Toast.LENGTH_LONG
            )
            true // Return true if the click is handled.
        }

        preferenceCheckIp?.setOnPreferenceClickListener {
            CommonVariables.toast = commonMethods!!.showToast(
                CommonVariables.toast,
                context,
                CommonVariables.targetIpAddress
            )
            true // Return true if the click is handled.
        }
    }
}