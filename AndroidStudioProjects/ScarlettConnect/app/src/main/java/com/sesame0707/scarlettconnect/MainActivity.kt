package com.sesame0707.scarlettconnect

import android.net.DhcpInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.text.format.Formatter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.sesame0707.scarlettconnect.databinding.ActivityMainBinding
import java.net.Socket
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_dashboard, R.id.nav_settings, R.id.nav_about
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        // Setting up Wi-Fi
        val SDK_INT = Build.VERSION.SDK_INT
        if (SDK_INT > 8) {
            val policy = StrictMode.ThreadPolicy.Builder()
                .permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        // Dynamic IP acquirement
        var wifiManager: WifiManager
        var dhcp: DhcpInfo
        var targetIpAddress: String = "0.0.0.0"
        Toast.makeText(this@MainActivity, "Target IP address: $targetIpAddress", Toast.LENGTH_SHORT)
            .show()

        thread {
            while (true) {
                wifiManager = super.getSystemService(WIFI_SERVICE) as WifiManager
                dhcp = wifiManager.dhcpInfo
                targetIpAddress = Formatter.formatIpAddress(dhcp.gateway)
                Thread.sleep(1000)
            }
        }

        // Button onClick events
        fun sendWifiDirectPacket(apiNumber: Byte) {
            try {
                val client = Socket(targetIpAddress, 80)
                client.getOutputStream().write(byteArrayOf(apiNumber))
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

        val stopButton = findViewById<Button>(R.id.button_stop)
        stopButton.setOnClickListener {
            sendWifiDirectPacket(1)
        }

        val drivingLightsButton = findViewById<Button>(R.id.button_driving_lights)
        drivingLightsButton.setOnClickListener {
            sendWifiDirectPacket(2)
        }

        val rgbStripeButton = findViewById<Button>(R.id.button_rgb_stripe)
        rgbStripeButton.setOnClickListener {
            sendWifiDirectPacket(3)
        }

        val leftBlinkerButton = findViewById<Button>(R.id.button_left_blinker)
        leftBlinkerButton.setOnClickListener {
            sendWifiDirectPacket(4)
        }

        val rightBlinkerButton = findViewById<Button>(R.id.button_right_blinker)
        rightBlinkerButton.setOnClickListener {
            sendWifiDirectPacket(5)
        }

        val leftParkingButton = findViewById<Button>(R.id.button_left_parking)
        leftParkingButton.setOnClickListener {
            sendWifiDirectPacket(6)
        }

        val rightParkingButton = findViewById<Button>(R.id.button_right_parking)
        rightParkingButton.setOnClickListener {
            sendWifiDirectPacket(7)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}