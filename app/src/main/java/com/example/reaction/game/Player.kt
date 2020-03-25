package com.example.reaction.game

import android.app.Activity
import android.content.Context
import android.util.Log

class Player (

) {
    private var ratingTournament: Int = 0
    private var ratingDuels: Int = 0
    private var gun: Gun? = null
    private var money: Int = 0
    private var progress: Int = 0
    private val preferences: String = "playerSettings"

    override fun toString(): String {
        return gun?.name ?: "ERROR_NO_GUN_NAME"
    }

    fun save(activity: Activity){
        val sharedPreferences = activity.getSharedPreferences(preferences, Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putInt("PLAYER_RATING_TOURNAMENT", ratingTournament)
        editor?.putInt("PLAYER_RATING_DUELS", ratingDuels)
        editor?.putString("PLAYER_GUN", gun.toString())
        editor?.putInt("PLAYER_MONEY", money)
        editor?.putInt("PLAYER_PROGRESS", progress)
        editor?.apply() //сохранение игрока
        Log.d("PLAYER", "Player Saved")
    }

    fun load(activity: Activity){
        val sharedPreferences = activity.getSharedPreferences(preferences, Context.MODE_PRIVATE)
        ratingTournament = sharedPreferences?.getInt("PLAYER_RATING_TOURNAMENT", 0) ?: 0
        ratingDuels = sharedPreferences?.getInt("PLAYER_RATING_DUELS", 0) ?: 0
        val gunName = sharedPreferences?.getString("PLAYER_GUN", "NO_NAME") ?: "NO_NAME"
        gun = Gun.makeGun(gunName)
        money = sharedPreferences?.getInt("PLAYER_MONEY", 0) ?: 0
        progress = sharedPreferences?.getInt("PLAYER_PROGRESS", 0) ?: 0
        Log.d("PLAYER", "Player Loaded")
    }
}

