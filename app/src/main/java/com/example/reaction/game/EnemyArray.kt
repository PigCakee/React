package com.example.reaction.game

import android.content.SharedPreferences
import android.util.Log

object EnemyArray {
    const val preferences = "enemyPrefs"

    val array: Array<Boolean> //Массив для хранения информации о том, побежден ли соперник или нет
            = arrayOf(false, false, false)

    private var instance: EnemyArray? = null

    @Synchronized
    private fun createInstance(){
        if (instance == null) {
            instance = EnemyArray
            Log.d("EnemyArray", "Created")
        }
    }

    fun getInstance(): EnemyArray{
        if (instance == null) createInstance()
        return instance!!
    }

    fun save(sharedPreferences: SharedPreferences){
        val editor = sharedPreferences.edit()
        for (i in array.indices){
            editor?.putBoolean("$i", array[i])
        }
        editor?.apply()
    }

    fun load(sharedPreferences: SharedPreferences){
        for (i in array.indices){
            array[i] = sharedPreferences.getBoolean("$i", false)
        }
    }
}