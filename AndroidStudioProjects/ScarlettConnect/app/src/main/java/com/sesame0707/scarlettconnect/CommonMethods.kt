package com.sesame0707.scarlettconnect

import android.content.Context
import android.widget.Toast
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
                toast = showToast(toast, context, "Car is not connected!")
            }
        }

        return toast
    }
}