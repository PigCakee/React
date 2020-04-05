package com.example.reaction.ui.shop

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.reaction.game.Gun
import com.example.reaction.game.Player
import com.example.reaction.util.Vibrator

class ShopViewModel : ViewModel() {
    var weaponSelected = 0
    private val player: Player = Player.getInstance()
    private var gun: Gun = Gun.makeGunByNumber(weaponSelected)

    private val milliseconds = 15L
    private val vibrator: Vibrator = Vibrator.getInstance()

    private val shop: Shop = Shop.getInstance()

    private var sharedPreferences: SharedPreferences? = null
    var activity: Activity? = null

    val buySelectButton: ObservableField<String> = ObservableField()

    fun setupBuySelectButton(){
        if (shop.gunArray[weaponSelected]){
            val gun: Gun = Gun.makeGunByNumber(weaponSelected)

            if (player.gun.toString() == gun.toString()) buySelectButton.set("Selected")
            else buySelectButton.set("Select")

        } else buySelectButton.set("Buy")
    }

    fun buyWeapon(){
        sharedPreferences = activity!!.getSharedPreferences(player.preferences, Context.MODE_PRIVATE)
        vibrator.activity = activity
        vibrator.vibrate(milliseconds)
        gun = Gun.makeGunByNumber(weaponSelected)
        if (player.money > gun.cost) {
            Log.d("Gun", "Purchased")
            player.gun = gun
            player.money -= gun.cost
            player.save(sharedPreferences!!)
            //TODO play buy sound
        } else {
            //TODO notify player that he has not enough money to buy
        }
    }

    fun onSelectButtonPress(){
        sharedPreferences = activity!!.getSharedPreferences(player.preferences, Context.MODE_PRIVATE)

        gun = Gun.makeGunByNumber(weaponSelected)
        player.gun = gun
        player.save(sharedPreferences!!)
    }
}