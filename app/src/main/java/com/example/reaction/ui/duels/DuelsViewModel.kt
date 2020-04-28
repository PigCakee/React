package com.example.reaction.ui.duels

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.ImageView
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.reaction.entities.Enemy
import com.example.reaction.entities.Player
import com.example.reaction.util.vibrator.Vibrator


class DuelsViewModel : ViewModel() {
    var activity: Activity? = null
    var context: Context? = null

    private var vibrator: Vibrator = Vibrator.getInstance()
    private val milliseconds = 10L

    val changeFragment: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    var avatarImageView: ImageView? = null
    var number = 0

    private var player: Player = Player.getInstance()

    val isLocked = ObservableField<String>()

    val playButtonClickable = ObservableField<Boolean>()

    fun setUpEnemies(){
        val enemy: Enemy = Enemy.newEnemyByNumber(number)
        if (player.ratingDuels >= enemy.requiredRating){
            isLocked.set("Unlocked")
            playButtonClickable.set(true)
        } else {
            isLocked.set("Locked")
            playButtonClickable.set(false)
        }
    }

    fun playGame(number: Int){
        Log.d("EnemySelected", number.toString())
        vibrator.activity = activity
        if (context != null) {
            vibrator.vibrate(milliseconds)

            Log.d("PLAYER_RATING_DUELS", player.ratingDuels.toString())
            changeFragment.value = true
            changeFragment.value = false
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