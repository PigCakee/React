package com.example.reaction.ui.duels

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reaction.R
import com.example.reaction.game.Enemy
import com.example.reaction.game.Player
import com.example.reaction.util.Vibrator


class DuelsViewModel : ViewModel() {
    var activity: Activity? = null
    var context: Context? = null

    private var vibrator = Vibrator(activity)
    private val milliseconds = 10L

    val changeFragment: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    var avatarImageView: ImageView? = null
    var number = 1
    private var borders = Pair(1, 3)

    private var sharedPreferences: SharedPreferences? = null
    private var player = Player()

    fun playGame(number: Int){
        if (context != null) {
            vibrator.vibrate(milliseconds)
            val enemy = Enemy.newEnemyByNumber(number, context!!)

            sharedPreferences =
                activity?.getSharedPreferences(player.preferences, Context.MODE_PRIVATE)
            if (sharedPreferences != null) {
                player.load(sharedPreferences!!)
            }
            Log.d("PLAYER_RATING_DUELS", player.ratingDuels.toString())
            if (player.ratingDuels >= enemy.requiredRating) {
                this.number = 1
                changeFragment.value = true
                changeFragment.value = false
            }
        }
    }

    fun onRightButtonClick(){
        //TODO add turning sound
        vibrator.vibrate(milliseconds)
        if (borders.second > number){
            number++
            if (avatarImageView != null){
                val imageView = avatarImageView
                imageView?.setImageResource(
                    when (number){
                        1 -> R.drawable.bandit_john
                        2 -> R.drawable.bandit_jack
                        3 -> R.drawable.bandit_dave
                        else -> R.drawable.bandit_jack
                    }
                )
            }
        }
    }

    fun onLeftButtonClick(){
        //TODO add turning sound
        vibrator.vibrate(milliseconds)
        if (borders.first < number){
            number--
            if (avatarImageView != null){
                val imageView = avatarImageView
                imageView?.setImageResource(
                    when (number){
                        1 -> R.drawable.bandit_john
                        2 -> R.drawable.bandit_jack
                        3 -> R.drawable.bandit_dave
                        else -> R.drawable.bandit_jack
                    }
                )
            }
        }
    }
}