package com.example.reaction.ui.multiplayer

import android.app.Activity
import android.os.CountDownTimer
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.reaction.util.Vibrator
import kotlin.math.absoluteValue


class MultiplayerViewModel : ViewModel() {
    var activity: Activity? = null
    private var vibrator = Vibrator(activity)
    private val milliseconds = 10L
    var zeroTime: Long = 0

    var readyTextView = ObservableField<String>()
    //var readyTextViewColor: Color = //TODO implement color changes
    var readyTextViewClickable = ObservableField<Boolean>()

    var firstPlayerScore = ObservableField<String>()
    var secondPlayerScore = ObservableField<String>()

    private var firstPlayerTime: Long = Long.MAX_VALUE
    private var secondPlayerTime: Long = Long.MAX_VALUE

    var firstPlayerButtonClickable = ObservableField<Boolean>()
    var secondPlayerButtonClickable = ObservableField<Boolean>()

    var firstButtonText = ObservableField<String>()
    var secondButtonText = ObservableField<String>()

    var isTicking: Boolean = false

    fun playGame(){
        vibrator = Vibrator(activity)

        readyTextView.set("Start")
        readyTextViewClickable.set(true)

        firstPlayerScore.set("0")
        secondPlayerScore.set("0")

        firstPlayerButtonClickable.set(false)
        secondPlayerButtonClickable.set(false)

        firstButtonText.set("Tap")
        secondButtonText.set("Tap")

    }

    fun playRound(){
        vibrator.vibrate(milliseconds)

        //readyTextViewColor = R.color.colorPrimaryRed //TODO implement color changes

        firstPlayerButtonClickable.set(true)
        secondPlayerButtonClickable.set(true)

        firstButtonText.set("Tap")
        secondButtonText.set("Tap")

        readyTextView.set("Ready...")
        readyTextViewClickable.set(false)

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

    fun onFirstButtonCLick(){
        vibrator.vibrate(milliseconds)
        if (isTicking && secondPlayerScore.get() != null) secondPlayerScore.set((secondPlayerScore.get()!!.toInt() + 1).toString())
        else {
            firstPlayerTime = System.currentTimeMillis() - (50..100).random()
            firstPlayerButtonClickable.set(false)
            if (firstPlayerTime - zeroTime < secondPlayerTime) {
                firstPlayerScore.set("${(firstPlayerScore.get()!!.toInt() + 1)}")
            }
            firstButtonText.set("${(firstPlayerTime - zeroTime).absoluteValue / 1000}.${(firstPlayerTime - zeroTime).absoluteValue % 1000}")
        }
    }

    fun onSecondButtonClick(){
        vibrator.vibrate(milliseconds)
        if (isTicking && firstPlayerScore.get() != null) firstPlayerScore.set((firstPlayerScore.get()!!.toInt() + 1).toString())
        else {
            secondPlayerTime = System.currentTimeMillis() - (50..100).random()
            secondPlayerButtonClickable.set(false)
            if (secondPlayerTime - zeroTime < firstPlayerTime) {
                secondPlayerScore.set("${(secondPlayerScore.get()!!.toInt() + 1)}")
            }
            secondButtonText.set("${(secondPlayerTime - zeroTime).absoluteValue / 1000}.${(secondPlayerTime - zeroTime).absoluteValue % 1000}")
        }
    }
}