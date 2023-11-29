package com.sesame0707.scarlettconnect.ui.dashboard

import android.net.DhcpInfo
import android.os.Bundle
import android.text.format.Formatter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.sesame0707.scarlettconnect.databinding.FragmentDashboardBinding
import java.net.Socket

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Button onClick events
        val buttonStop: Button = _binding!!.buttonStop
        buttonStop.setOnClickListener {
            sendWifiDirectPacket(1)
        }

        val buttonDrivingLights: Button = _binding!!.buttonDrivingLights
        buttonDrivingLights.setOnClickListener {
            sendWifiDirectPacket(2)
        }

        val buttonRgbStripe: Button = _binding!!.buttonRgbStripe
        buttonRgbStripe.setOnClickListener {
            sendWifiDirectPacket(3)
        }

        val buttonLeftBlinker: Button = _binding!!.buttonLeftBlinker
        buttonLeftBlinker.setOnClickListener {
            sendWifiDirectPacket(4)
        }

        val buttonRightBlinker: Button = _binding!!.buttonRightBlinker
        buttonRightBlinker.setOnClickListener {
            sendWifiDirectPacket(5)
        }

        val buttonLeftParking: Button = _binding!!.buttonLeftParking
        buttonLeftParking.setOnClickListener {
            sendWifiDirectPacket(6)
        }

        val buttonRightParking: Button = _binding!!.buttonRightParking
        buttonRightParking.setOnClickListener {
            sendWifiDirectPacket(7)
        }

        return root
    }

    fun sendWifiDirectPacket(apiNumber: Byte) {
        lateinit var dhcp: DhcpInfo

        try {
            val targetIpAddress = Formatter.formatIpAddress(dhcp.gateway)
            val client = Socket(targetIpAddress, 80)
            client.getOutputStream().write(byteArrayOf(apiNumber))
            client.close()
//            Toast.makeText(context, "Data is sent!", Toast.LENGTH_SHORT).show()
//            Toast.makeText(
//                context,
//                "Target IP address: $targetIpAddress",
//                Toast.LENGTH_SHORT
//            ).show()
        } catch (e: Exception) {
            Toast.makeText(
                context,
                "Car is not connected!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}