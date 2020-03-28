package com.example.reaction.ui.duels

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.CountDownTimer
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.example.reaction.game.Enemy
import com.example.reaction.game.Player
import com.example.reaction.util.Vibrator
import kotlin.math.absoluteValue

class DuelsGameViewModel : ViewModel() {
    var activity: Activity? = null
    var context: Context? = null

    private var vibrator = Vibrator(activity)
    private val milliseconds = 10L

    private var sharedPreferences: SharedPreferences? = null
    val player = Player()

    var enemyScore = ObservableField<Int>()
    var playerScore = ObservableField<Int>()

    var enemyNumber: Int = 1

    var isTicking: Boolean = false

    var zeroTime: Long = 0

    var readyTextView = ObservableField<String>()
    //var readyTextViewColor: Color = //TODO implement color changes
    var readyTextViewClickable = ObservableField<Boolean>()

    private var canPlayRound: Boolean = true

    private var playerTime: Long = Long.MAX_VALUE
    private var enemyTime: Long = Long.MAX_VALUE

    val playerButtonClickable = ObservableField<Boolean>(true)

    val playerButtonText = ObservableField<String>("Shoot")
    val enemyButtonText = ObservableField<String>("Enemy")

    val canPlayLiveData = MutableLiveData<Boolean>(canPlayRound)

    val isWon = false

    fun playGame(){
        if (context != null) {
            sharedPreferences =
                activity?.getSharedPreferences(player.preferences, Context.MODE_PRIVATE)
            if (sharedPreferences != null) {
                player.load(sharedPreferences!!)
            }

            enemyScore.set(0)
            playerScore.set(0)

            readyTextView.set("Start")
            readyTextViewClickable.set(true)
        }
    }

    fun playRound(player: Player, enemyNumber: Int){
        //TODO set the layout
        if (canPlayRound) {
            vibrator.vibrate(milliseconds)

            val enemy: Enemy
            if (context != null) {
                enemy = Enemy.newEnemyByNumber(enemyNumber, context!!)

                readyTextView.set("Ready...")
                readyTextViewClickable.set(false)

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
                        onEnemyReaction(enemy)
                        readyTextViewClickable.set(canPlayRound)
                    }
                }.start()
            }
        }
    }

    fun onPlayerButtonClick(){
        playerTime = (System.currentTimeMillis() - (50..100).random() - zeroTime).absoluteValue
        vibrator.vibrate(milliseconds)
        if (isTicking && enemyScore.get() != null) {
            enemyScore.set((enemyScore.get()!! + 1))
            playerButtonClickable.set(false)

            if (enemyScore.get() == 5) canPlayRound = false

            //TODO play shot sound
            //TODO animate shot
        }
        else {
            playerButtonClickable.set(false)

            if (playerTime < enemyTime) {
                playerScore.set((playerScore.get()!!.toInt() + 1))
            }

            playerButtonText.set("${playerTime / 1000}.${playerTime % 1000}")

            if (playerScore.get() == 5) canPlayRound = false
        }
    }

    fun onEnemyReaction(enemy: Enemy){
        enemyTime = (enemy.reaction-50..enemy.reaction+50).random()
        object : CountDownTimer(enemyTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                Log.d("RANGE", "enemyTime${enemyTime}, playerTime${playerTime}")
                if (enemyTime < playerTime) {
                    enemyScore.set(enemyScore.get()!! + 1)
                }
                enemyButtonText.set("${enemyTime/ 1000}.${enemyTime % 1000}")

                if (enemyScore.get() == 5) canPlayRound = false
            }
        }.start()
        //TODO play shot sound
        //TODO animate shot
    }
}