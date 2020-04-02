package com.example.reaction.ui.duels

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.example.reaction.game.Enemy
import com.example.reaction.game.Gun
import com.example.reaction.game.Player
import com.example.reaction.util.Vibrator
import kotlin.math.absoluteValue

class DuelsGameViewModel : ViewModel() {
    var activity: Activity? = null
    var context: Context? = null

    var removeFragment: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    private var vibrator = Vibrator(activity)
    private val milliseconds = 10L

    private var sharedPreferences: SharedPreferences? = null
    private val player = Player.getInstance()
    private var gun = Gun.makeGun("default")

    var enemyScore = ObservableField<Int>()
    var playerScore = ObservableField<Int>()
    var enemyNumber: Int = 1

    var isTicking: Boolean = false
    var zeroTime: Long = 0

    var readyTextView = ObservableField<String>()
    //var readyTextViewColor: Color = //TODO implement color changes
    var readyTextViewClickable = ObservableField<Boolean>()

    private var playerTime: Long = Long.MAX_VALUE
    private var enemyTime: Long = Long.MAX_VALUE

    val playerButtonClickable: ObservableField<Boolean> = ObservableField()

    val playerButtonText: ObservableField<String> = ObservableField()
    val enemyButtonText: ObservableField<String> = ObservableField()

    var endgameLayoutVisibility: ObservableField<Int> = ObservableField()

    var winText: ObservableField<String> = ObservableField()

    private var enemyReaction: Long = Long.MAX_VALUE
    private var reward = 0

    fun playGame(){
        if (context != null) {
            sharedPreferences =
                activity?.getSharedPreferences(player.preferences, Context.MODE_PRIVATE)
            if (sharedPreferences != null) {
                player.load(sharedPreferences!!)
                Log.d("Guns", "playerGun${player.gun!!.delay} gun${gun.delay}")
                gun = player.gun!!
            }

            removeFragment.value = false
            endgameLayoutVisibility.set(View.INVISIBLE)

            val enemy: Enemy = Enemy.newEnemyByNumber(enemyNumber, context!!)
            enemyReaction = enemy.reaction
            reward = enemy.reward

            playerButtonText.set("Shoot")
            enemyButtonText.set("Enemy")

            enemyScore.set(0)
            playerScore.set(0)

            readyTextView.set("Start")
            readyTextViewClickable.set(true)
            playerButtonClickable.set(true)
        }
    }

    fun playRound(){
        //TODO set the layout
        vibrator.vibrate(milliseconds)
        if (context != null) {
            readyTextView.set("Ready...")
            readyTextViewClickable.set(false)

            playerButtonText.set("Shoot")
            enemyButtonText.set("Enemy")

            playerButtonClickable.set(true)

            playerTime = Long.MAX_VALUE
            enemyTime = Long.MAX_VALUE

            object : CountDownTimer((2500..8000).random().toLong(), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    isTicking = true
                }

                override fun onFinish() {
                    zeroTime = System.currentTimeMillis()
                    isTicking = false
                    readyTextView.set("Go!")
                    readyTextViewClickable.set(true)
                }
            }.start()
        }

    }


    fun onPlayerButtonClick(){
        playerTime = (System.currentTimeMillis() - (50..100).random() - zeroTime).absoluteValue + gun.delay
        enemyTime = (enemyReaction-50..enemyReaction+50).random()
        Log.d("RANGE1", "enemyTime${enemyTime}, playerTime${playerTime}, gunDelay${gun.delay}")
        vibrator.vibrate(milliseconds)
        if (isTicking && enemyScore.get() != null) {
            enemyScore.set((enemyScore.get()!! + 1))
            playerButtonClickable.set(false)
            //TODO animate miss shot
            //TODO animate enemy shooting down player
        }
        else {
            playerButtonClickable.set(false)

            if (playerTime < enemyTime) {
                playerScore.set((playerScore.get()!!.toInt() + 1))
            }
            playerButtonText.set("${playerTime / 1000}.${playerTime % 1000}")

            if (enemyTime < playerTime) {
                enemyScore.set(enemyScore.get()!! + 1)
            }
            enemyButtonText.set("${enemyTime/ 1000}.${enemyTime % 1000}")


            //TODO play shot sound
            //TODO animate shot
        }

        if (playerScore.get() == 1) {
            endgameLayoutVisibility.set(View.VISIBLE)
            winText.set("You won!")
            player.money += reward
            player.save(sharedPreferences!!)
        }

        if (enemyScore.get() == 1) {
            endgameLayoutVisibility.set(View.VISIBLE)
            winText.set("You lost")
        }
    }

    fun onTryAgainButtonCLick(){
        playGame()
    }

    fun onOkButtonClick(){
        removeFragment.value = true
        removeFragment.value = false
    }
}