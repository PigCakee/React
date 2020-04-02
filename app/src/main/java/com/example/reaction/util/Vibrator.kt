package com.example.reaction.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

class Vibrator(activity: Activity?){
    private val vibrator = if (activity != null) activity.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                                        else null
    private val canVibrate: Boolean = vibrator?.hasVibrator() ?: false

    fun vibrate(milliseconds: Long){
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
    //TODO make vibrator a singleton
}