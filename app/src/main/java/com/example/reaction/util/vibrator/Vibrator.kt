@file:Suppress("DEPRECATION")

package com.example.reaction.util.vibrator

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

object Vibrator{
    var activity: Activity? = null
    private var instance: com.example.reaction.util.vibrator.Vibrator? = null

    @Synchronized
    private fun createInstance() {
        if (instance == null) {
            instance =
                Vibrator
        }
    }

    fun getInstance(): com.example.reaction.util.vibrator.Vibrator {
        if (instance == null) createInstance()
        return instance!!
    }

    fun vibrate(milliseconds: Long){

        val vibrator: Vibrator? = if (activity != null) activity!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        else null

        val canVibrate: Boolean = vibrator?.hasVibrator() ?: false

        if (canVibrate) { //after this check we are sure that vibrator != null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // API 26
                vibrator!!.vibrate(
                    VibrationEffect.createOneShot(
                        milliseconds,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            } else {
                // This method was deprecated in API level 26
                vibrator!!.vibrate(milliseconds)
            }
        }
    }
}