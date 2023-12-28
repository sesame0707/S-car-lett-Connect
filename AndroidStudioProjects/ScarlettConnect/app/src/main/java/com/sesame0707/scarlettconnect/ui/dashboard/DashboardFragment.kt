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
import java.lang.Float.min
import kotlin.concurrent.thread

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var commonMethods: CommonMethods? = null
    private var sliderAccelerateDecelerate: Slider? = null
    private var sliderLeftRight: Slider? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        CommonVariables.toast = Toast.makeText(context, null, Toast.LENGTH_SHORT)
        commonMethods = CommonMethods()

        val displayMetrics = requireContext().resources.displayMetrics
        val dpHeight = displayMetrics.heightPixels / displayMetrics.density
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        val sliderTrackHeight = (min(dpHeight, dpWidth) / 9).toInt()

        // Sliders' initialization
        sliderAccelerateDecelerate = _binding!!.sliderAccelerateDecelerate
        sliderAccelerateDecelerate!!.trackHeight = sliderTrackHeight
        sliderAccelerateDecelerate!!.value =
            CommonVariables.sliderAccelerateDecelerateCurrentValue.toFloat()

        sliderLeftRight = _binding!!.sliderLeftRight
        sliderLeftRight!!.trackHeight = sliderTrackHeight
        sliderLeftRight!!.value =
            CommonVariables.sliderLeftRightCurrentValue.toFloat()

        // Button onClick events
        val buttonStop: Button = _binding!!.buttonStop
        buttonStop.setOnClickListener {
            CommonVariables.toast =
                commonMethods!!.sendWifiDirectPacket(CommonVariables.toast, context, 1)

            // Setting sliderAccelerateDecelerate to neutral state
            while (sliderAccelerateDecelerate!!.value.toInt() != 0) {
                if (sliderAccelerateDecelerate!!.value > 0) {
                    sliderAccelerateDecelerate!!.value = sliderAccelerateDecelerate!!.value - 1
                } else {
                    sliderAccelerateDecelerate!!.value = sliderAccelerateDecelerate!!.value + 1
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

        // Thread for sending acknowledge Wi-Fi Direct packets
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

        // Slider onChange events
        sliderAccelerateDecelerate!!.addOnChangeListener(Slider.OnChangeListener { _, _, _ ->
            CommonVariables.sliderAccelerateDecelerateCurrentValue =
                sliderAccelerateDecelerate!!.value.toInt()
            if (CommonVariables.sliderAccelerateDecelerateCurrentValue > CommonVariables.sliderAccelerateDeceleratePreviousValue) {
                CommonVariables.toast =
                    commonMethods!!.sendWifiDirectPacket(CommonVariables.toast, context, 11)
            } else {
                CommonVariables.toast =
                    commonMethods!!.sendWifiDirectPacket(CommonVariables.toast, context, 12)
            }
            CommonVariables.sliderAccelerateDeceleratePreviousValue =
                CommonVariables.sliderAccelerateDecelerateCurrentValue
        })

        sliderLeftRight!!.addOnChangeListener(Slider.OnChangeListener { _, _, _ ->
            CommonVariables.sliderLeftRightCurrentValue =
                sliderLeftRight!!.value.toInt()
            if (CommonVariables.sliderLeftRightCurrentValue > CommonVariables.sliderLeftRightPreviousValue) {
                CommonVariables.toast =
                    commonMethods!!.sendWifiDirectPacket(CommonVariables.toast, context, 14)
            } else {
                CommonVariables.toast =
                    commonMethods!!.sendWifiDirectPacket(CommonVariables.toast, context, 13)
            }
            CommonVariables.sliderLeftRightPreviousValue =
                CommonVariables.sliderLeftRightCurrentValue
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}