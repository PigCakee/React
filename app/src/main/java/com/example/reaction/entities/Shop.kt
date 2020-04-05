package com.example.reaction.entities

import android.content.SharedPreferences
import android.util.Log

object Shop {
    var gunArray: Array<Boolean>
            = arrayOf(true, true, false)

    const val preferences: String = "shopPrefs"
    private var instance: Shop? = null

    @Synchronized
    private fun createInstance(){
        Log.d("Shop", "Created")
        if (instance == null) {
            instance = Shop
        }
    }

    fun getInstance(): Shop {
        if (instance == null) createInstance()
        Log.d("Shop", "Loaded")
        return instance!!
    }

    fun save(sharedPreferences: SharedPreferences){
        val editor = sharedPreferences.edit()
        for (i in gunArray.indices){
            editor?.putBoolean("$i", gunArray[i])
        }
        editor?.apply()
    }

    fun load(sharedPreferences: SharedPreferences){
        for (i in gunArray.indices){
            gunArray[i] = sharedPreferences.getBoolean("$i", false)
        }
        gunArray[0] = true
    }
}