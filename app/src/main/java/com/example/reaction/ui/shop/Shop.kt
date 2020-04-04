package com.example.reaction.ui.shop

import android.content.SharedPreferences

object Shop {
    var gunArray: Array<Boolean>
            = arrayOf(true, true, false)

    val preferences: String = "shopPrefs"
    private var instance: Shop? = null

    @Synchronized
    private fun createInstance(){
        if (instance == null) {
            instance = Shop
        }
    }

    fun getInstance(): Shop{
        if (instance == null) createInstance()
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
    }
}