package com.example.reaction.ui.shop

import android.content.SharedPreferences
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.reaction.entities.Gun
import com.example.reaction.entities.Player
import com.example.reaction.entities.Shop
import com.example.reaction.util.livedata.SingleLiveEvent


class ShopViewModel : ViewModel() {
    var weaponSelected = 0

    private val player: Player = Player.getInstance()
    private var gun: Gun = Gun.makeGunByNumber(weaponSelected)
    private val shop: Shop = Shop.getInstance()

    val milliseconds = 15L
    val vibratorCommand = SingleLiveEvent<Void>()

    var sharedPreferences: SharedPreferences? = null
    var preferences = ""
    val preferencesCommand = SingleLiveEvent<Void>()

    val moneyCommand = SingleLiveEvent<Void>()
    val soundCommand = SingleLiveEvent<Void>()

    val buySelectButton: ObservableField<String> = ObservableField()

    fun onBuySelectButtonClick(){
        getPreferences(player.preferences)
        gun = Gun.makeGunByNumber(weaponSelected)

        if (shop.gunArray[weaponSelected] && buySelectButton.get()!! == "Select"){
            onSelect()
        }

        if (!shop.gunArray[weaponSelected] && player.money >= gun.cost){
            onBuy()
        }

        if (!shop.gunArray[weaponSelected] && player.money < gun.cost){
            notifyNoMoney()
        }
    }

    private fun onSelect() {
        vibratorCommand.call()
        player.gun = gun
        player.save(sharedPreferences!!)
        buySelectButton.set("Selected")
    }

    private fun onBuy() {
        player.money -= gun.cost
        onSelect()
        soundCommand.call()
        getPreferences(shop.preferences)
        shop.gunArray[weaponSelected] = true
        shop.save(sharedPreferences!!)
    }

    private fun notifyNoMoney(){
        vibratorCommand.call()
        moneyCommand.call()
    }

    private fun getPreferences(preferences: String){
        this.preferences = preferences
        preferencesCommand.call()
    }

    fun setupBuySelectButton(){
        if (shop.gunArray[weaponSelected]){
            val gun: Gun = Gun.makeGunByNumber(weaponSelected)

            if (player.gun.toString() == gun.toString()) buySelectButton.set("Selected")
            else buySelectButton.set("Select")

        } else buySelectButton.set("Buy")
    }
}