package com.sesame0707.scarlettconnect

import android.widget.Toast

object CommonVariables {
    var targetIpAddress = "0.0.0.0"
    var acknowledgePacketsFrequency: Long = 5000
    var toast: Toast? = null
    var sliderTrackHeight: Int = 0

    var sliderAccelerateDecelerateReset: Boolean = false
    var sliderAccelerateDeceleratePreviousValue: Int = 0
    var sliderAccelerateDecelerateCurrentValue: Int = 0

    var sliderLeftRightReset: Boolean = false
    var sliderLeftRightPreviousValue: Int = 0
    var sliderLeftRightCurrentValue: Int = 0
}