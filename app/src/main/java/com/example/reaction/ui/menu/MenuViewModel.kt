package com.example.reaction.ui.menu

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.reaction.game.Player
import com.example.reaction.ui.shop.Shop

class MenuViewModel : ViewModel() {
    var activity: Activity? = null
    private var sharedPreferences: SharedPreferences? = null

    fun instantiateGameEntities(){
        val player = Player.getInstance()
        sharedPreferences = activity!!.getSharedPreferences(player.preferences, Context.MODE_PRIVATE)
        player.load(sharedPreferences!!)

        val shop = Shop.getInstance()
        sharedPreferences = activity!!.getSharedPreferences(shop.preferences, Context.MODE_PRIVATE)
        shop.load(sharedPreferences!!)
    }
}