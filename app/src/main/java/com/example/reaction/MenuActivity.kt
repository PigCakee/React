package com.example.reaction

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.reaction.game.Gun
import com.example.reaction.game.Player
import com.example.reaction.ui.menu.MenuFragment
import com.example.reaction.ui.multiplayer.MultiplayerFragment
import kotlinx.android.synthetic.main.menu_activity.*


@Suppress("DEPRECATION")
class MenuActivity : AppCompatActivity() {

    private var sharedPreferences: SharedPreferences? = null
    private val preferences: String = "playerSettings"
    private lateinit var player: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_activity)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        supportFragmentManager.beginTransaction() //set up and display menu fragment
            .replace(R.id.container, MenuFragment.newInstance())
            .commitNow()
    }

    override fun onResume() {
        super.onResume()
        player = loadPlayer()
    }

    override fun onPause() {
        super.onPause()
        savePlayer(player)
    }

    private fun savePlayer(player: Player){
        sharedPreferences = getSharedPreferences(preferences, Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putInt("PLAYER_RATING_TOURNAMENT", player.ratingTournament)
        editor?.putInt("PLAYER_RATING_DUELS", player.ratingDuels)
        editor?.putString("PLAYER_GUN", player.gun.toString())
        editor?.putInt("PLAYER_MONEY", player.money)
        editor?.putInt("PLAYER_PROGRESS", player.progress)
        editor?.apply() //сохранение игрока
        Log.d("PLAYER", "Player Saved")
    }

    private fun loadPlayer(): Player{
        val player = Player()
        sharedPreferences = getSharedPreferences(preferences, Context.MODE_PRIVATE)
        val sPref = sharedPreferences
        player.ratingTournament = sPref?.getInt("PLAYER_RATING_TOURNAMENT", 0) ?: 0
        player.ratingDuels = sPref?.getInt("PLAYER_RATING_DUELS", 0) ?: 0
        val gunName = sPref?.getString("PLAYER_GUN", "NO_NAME") ?: "NO_NAME"
        player.gun = Gun.makeGun(gunName)
        player.money = sPref?.getInt("PLAYER_MONEY", 0) ?: 0
        player.progress = sPref?.getInt("PLAYER_PROGRESS", 0) ?: 0
        Log.d("PLAYER", "Player Loaded")
        return player
    }
}

