package com.example.reaction.ui.duels

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reaction.game.Enemy
import com.example.reaction.game.Player
import com.example.reaction.util.Vibrator


class DuelsViewModel : ViewModel() {
    var activity: Activity? = null
    var context: Context? = null

    private var vibrator = Vibrator.getInstance()
    private val milliseconds = 10L

    val changeFragment: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    var avatarImageView: ImageView? = null
    var number = 0

    private var player = Player.getInstance()

    fun playGame(number: Int){
        vibrator.activity = activity
        //TODO if rating < enemy visualize it
        if (context != null) {
            vibrator.vibrate(milliseconds)
            val enemy = Enemy.newEnemyByNumber(number, context!!)

            Log.d("PLAYER_RATING_DUELS", player.ratingDuels.toString())
            if (player.ratingDuels >= enemy.requiredRating) {
                changeFragment.value = true
                changeFragment.value = false
            }
        }
    }

    fun onRightButtonClick(){
        //TODO add turning sound
        vibrator.vibrate(milliseconds)

    }

    fun onLeftButtonClick(){
        //TODO add turning sound
        vibrator.vibrate(milliseconds)

    }
}