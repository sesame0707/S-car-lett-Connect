package com.sesame0707.scarlettconnect.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.slider.Slider
import com.sesame0707.scarlettconnect.CommonMethods
import com.sesame0707.scarlettconnect.CommonVariables
import com.sesame0707.scarlettconnect.databinding.FragmentDashboardBinding
import kotlin.concurrent.thread

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var commonMethods: CommonMethods? = null
    private var accelerateDecelerateSeekBar: Slider? = null
    private var leftRightSeekBar: Slider? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        CommonVariables.toast = Toast.makeText(context, null, Toast.LENGTH_SHORT)
        commonMethods = CommonMethods()
        accelerateDecelerateSeekBar = _binding!!.accelerateDecelerateSeekBar
        leftRightSeekBar = _binding!!.leftRightSeekBar

        // Button onClick events
        val buttonStop: Button = _binding!!.buttonStop
        buttonStop.setOnClickListener {
            CommonVariables.toast =
                commonMethods!!.sendWifiDirectPacket(CommonVariables.toast, context, 1)

            // Setting accelerateDecelerateSeekBar to neutral state
            while (accelerateDecelerateSeekBar!!.value.toInt() != 0) {
                if (accelerateDecelerateSeekBar!!.value > 0) {
                    accelerateDecelerateSeekBar!!.value = accelerateDecelerateSeekBar!!.value - 1
                } else {
                    accelerateDecelerateSeekBar!!.value = accelerateDecelerateSeekBar!!.value + 1
                }
            }

            // Setting leftRightSeekBar to neutral state
            while (leftRightSeekBar!!.value.toInt() != 0) {
                if (leftRightSeekBar!!.value > 0) {
                    leftRightSeekBar!!.value = leftRightSeekBar!!.value - 1
                } else {
                    leftRightSeekBar!!.value = leftRightSeekBar!!.value + 1
                }
            }
        }

        val buttonDrivingLights: Button = _binding!!.buttonDrivingLights
        buttonDrivingLights.setOnClickListener {
            CommonVariables.toast =
                commonMethods!!.sendWifiDirectPacket(CommonVariables.toast, context, 2)
        }

        val buttonRgbStripe: Button = _binding!!.buttonRgbStripe
        buttonRgbStripe.setOnClickListener {
            CommonVariables.toast =
                commonMethods!!.sendWifiDirectPacket(CommonVariables.toast, context, 3)
        }

        val buttonLeftBlinker: Button = _binding!!.buttonLeftBlinker
        buttonLeftBlinker.setOnClickListener {
            CommonVariables.toast =
                commonMethods!!.sendWifiDirectPacket(CommonVariables.toast, context, 4)
        }

        val buttonRightBlinker: Button = _binding!!.buttonRightBlinker
        buttonRightBlinker.setOnClickListener {
            CommonVariables.toast =
                commonMethods!!.sendWifiDirectPacket(CommonVariables.toast, context, 5)
        }

        val buttonLeftParking: Button = _binding!!.buttonLeftParking
        buttonLeftParking.setOnClickListener {
            CommonVariables.toast =
                commonMethods!!.sendWifiDirectPacket(CommonVariables.toast, context, 6)
        }

        val buttonRightParking: Button = _binding!!.buttonRightParking
        buttonRightParking.setOnClickListener {
            CommonVariables.toast =
                commonMethods!!.sendWifiDirectPacket(CommonVariables.toast, context, 7)
        }

        thread {
            while (true) {
                activity?.runOnUiThread {
                    CommonVariables.toast =
                        commonMethods!!.sendWifiDirectPacket(
                            CommonVariables.toast,
                            context,
                            21,
                            true
                        )
                }
                Thread.sleep(5000)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}