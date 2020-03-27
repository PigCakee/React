package com.example.reaction.ui.duels

import android.app.Activity
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel

class DuelsViewModel : ViewModel() {
    var activity: Activity? = null
    fun playGame(){
        Log.d("View", "STart")
    }
}