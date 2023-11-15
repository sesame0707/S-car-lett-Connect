package com.sesame0707.test1

import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.widget.Button
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
//            Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
            val client = Socket("192.168.4.3", 80)
            client.getOutputStream().write(byteArrayOf(77, 100))
            client.close()
        }
    }
}