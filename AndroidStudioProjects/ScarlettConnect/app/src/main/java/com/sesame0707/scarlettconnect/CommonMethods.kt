package com.sesame0707.scarlettconnect

import android.content.Context
import android.util.DisplayMetrics
import android.widget.Toast
import java.lang.Float.min
import java.net.Socket

class CommonMethods {
    fun showToast(
        _toast: Toast?,
        context: Context?,
        toastMessage: String?,
        toastDuration: Int = Toast.LENGTH_SHORT
    ): Toast {
        var toast = _toast

        toast?.cancel()
        toast = Toast.makeText(context, toastMessage, toastDuration)
        toast!!.show()

        return toast
    }

    fun sendWifiDirectPacket(
        _toast: Toast?,
        context: Context?,
        apiNumber: Byte,
        silentPacket: Boolean = false
    ): Toast? {
        var toast = _toast

        try {
            val client = Socket(CommonVariables.targetIpAddress, 80)
            client.getOutputStream().write(byteArrayOf(apiNumber))
            client.close()
        } catch (e: Exception) {
            if (!silentPacket) {
                toast = showToast(
                    toast,
                    context,
                    context?.getString(R.string.error_send_wifi_direct_packet)
                )
            }
        }

        return toast
    }

    fun getSliderTrackHeight(displayMetrics: DisplayMetrics): Int {
        val dpHeight = displayMetrics.heightPixels / displayMetrics.density
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density

        return (min(dpHeight, dpWidth) / 9).toInt()
    }
}