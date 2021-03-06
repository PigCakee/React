package com.example.reaction.entities

import android.content.SharedPreferences
import android.util.Log

object Player  {
    private var instance: Player? = null
    var ratingTournament: Int = 0
    var ratingDuels: Int = 5
    var gun: Gun? = Gun.makeGunByNumber(0)
    var money: Int = 0
    var progress: Int = 0
    const val preferences: String = "playerSettings"

    @Synchronized
    private fun createInstance() {
        if (instance == null) {
            Log.d("Player", "Created")
            instance = Player
        }
    }

    fun getInstance(): Player{
        if (instance == null) createInstance()
        return instance!!
    }

    fun save(sharedPreferences: SharedPreferences){
        val editor = sharedPreferences.edit()
        editor?.putInt("PLAYER_RATING_TOURNAMENT", ratingTournament)
        editor?.putInt("PLAYER_RATING_DUELS", ratingDuels)
        editor?.putString("PLAYER_GUN", gun.toString())
        editor?.putInt("PLAYER_MONEY", money)
        editor?.putInt("PLAYER_PROGRESS", progress)
        editor?.apply() //сохранение игрока
        Log.d("PLAYER", "Player Saved")
    }

    fun load(sharedPreferences: SharedPreferences){
        ratingTournament = sharedPreferences.getInt("PLAYER_RATING_TOURNAMENT", 5)
        ratingDuels = sharedPreferences.getInt("PLAYER_RATING_DUELS", 5)
        val gunName = sharedPreferences.getString("PLAYER_GUN", "revolver0") ?: "revolver0"
        gun = Gun.makeGun(gunName)
        money = sharedPreferences.getInt("PLAYER_MONEY", 0)
        progress = sharedPreferences.getInt("PLAYER_PROGRESS", 0)
        Log.d("PLAYER", "Player Loaded")
    }
}