package com.example.reaction.ui.shop

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.reaction.entities.Gun
import com.example.reaction.entities.Player
import com.example.reaction.entities.Shop
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

    fun onBuySelectButtonPress(){
        sharedPreferences = activity!!.getSharedPreferences(player.preferences, Context.MODE_PRIVATE)
        vibrator.activity = activity

        gun = Gun.makeGunByNumber(weaponSelected)
        if (shop.gunArray[weaponSelected] && buySelectButton.get()!! == "Select"){
            vibrator.vibrate(milliseconds)
            player.gun = gun
            player.save(sharedPreferences!!)
            Log.d("Gun", "Selected")

            buySelectButton.set("Selected")
        }

        if (!shop.gunArray[weaponSelected] && player.money >= gun.cost){
            vibrator.vibrate(milliseconds)
            player.money -= gun.cost
            player.gun = gun
            player.save(sharedPreferences!!)
            Log.d("Gun", "Purchased")
            //TODO play buy sound

            sharedPreferences = activity!!.getSharedPreferences(shop.preferences, Context.MODE_PRIVATE)

            shop.gunArray[weaponSelected] = true
            shop.save(sharedPreferences!!)

            buySelectButton.set("Selected")
        }

        if (!shop.gunArray[weaponSelected] && player.money < gun.cost){
            vibrator.vibrate(milliseconds)
            Log.d("Gun", "Not enough money")
            //TODO notify player that he has not enough money to buy
        }
    }
}