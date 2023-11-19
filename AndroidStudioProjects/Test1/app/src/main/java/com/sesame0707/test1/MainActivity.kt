package com.sesame0707.test1

import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.text.format.Formatter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.net.Socket


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val SDK_INT = Build.VERSION.SDK_INT
            if (SDK_INT > 8) {
                val policy = ThreadPolicy.Builder()
                    .permitAll().build()
                StrictMode.setThreadPolicy(policy)
            }

            // Dynamic IP acquirement
            val wifiManager = super.getSystemService(WIFI_SERVICE) as WifiManager
            val dhcp = wifiManager.dhcpInfo
            val targetIpAddress = Formatter.formatIpAddress(dhcp.gateway)
            Toast.makeText(this@MainActivity, "IP address: $targetIpAddress", Toast.LENGTH_SHORT)
                .show()

            try {
                val client = Socket(targetIpAddress, 80)
                client.getOutputStream().write(byteArrayOf(1))
                client.close()
                Toast.makeText(this@MainActivity, "Data is sent!", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(
                    this@MainActivity,
                    "Car is not connected!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}