@file:Suppress("DEPRECATION")

package com.example.reaction

import android.annotation.SuppressLint
import android.content.Context
import android.os.*
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.absoluteValue

class MultiplayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multiplayer)
        val flags = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

        window.decorView.systemUiVisibility = flags
        val decorView = window.decorView
        decorView
            .setOnSystemUiVisibilityChangeListener { visibility ->
                if (visibility and View.SYSTEM_UI_FLAG_FULLSCREEN == 0) {
                    decorView.systemUiVisibility = flags
                }
            }
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val button1: Button = findViewById(R.id.button3)
        val button2: Button = findViewById(R.id.button4)
        val player1ScoreTextView: TextView = findViewById(R.id.player1Score)
        val player2ScoreTextView: TextView = findViewById(R.id.player2Score)
        val countdownTextView: TextView = findViewById(R.id.countdownTextView)

        val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val canVibrate: Boolean = vibrator.hasVibrator()
        val milliseconds = 50L

        fun vibrate(){
            if (canVibrate) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    // API 26
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            milliseconds,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                } else {
                    // This method was deprecated in API level 26
                    vibrator.vibrate(milliseconds)
                }
            }
        }

        button1.rotationX = 180f
        button1.rotationY = 180f

        player1ScoreTextView.rotationX = 180f
        player1ScoreTextView.rotationY = 180f

        countdownTextView.setTextColor(resources.getColor(R.color.colorWhite))
        countdownTextView.text = resources.getString(R.string.start)

        var button1Time: Int
        var button2Time: Int
        var player1Score = 0
        var player2Score = 0

        countdownTextView.setOnClickListener {
            vibrate()

            button1.text = resources.getString(R.string.tap)
            button2.text = resources.getString(R.string.tap)


            object : CountDownTimer((2500..8000).random().toLong(), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    countdownTextView.setTextColor(resources.getColor(R.color.colorWhite))
                    countdownTextView.textSize = 36f
                    countdownTextView.text = resources.getString(R.string.ready)
                    countdownTextView.isClickable = false

                    button1.setOnClickListener {
                        player2Score++
                        player2ScoreTextView.text = player2Score.toString()
                    }

                    button2.setOnClickListener {
                        player1Score++
                        player1ScoreTextView.text = player1Score.toString()
                    }
                }

                @SuppressLint("SetTextI18n")
                override fun onFinish() {
                    val zeroTime = System.currentTimeMillis()

                    countdownTextView.setTextColor(resources.getColor(R.color.colorPrimaryRed))
                    countdownTextView.textSize = 72f
                    countdownTextView.text = resources.getString(R.string.go)

                    button1.setOnClickListener {
                        val currentTime = System.currentTimeMillis() - (50..80).random()
                        button1Time = (currentTime - zeroTime).toInt().absoluteValue
                        button1.text = "${button1Time / 1000}.${button1Time % 1000}"

                        if (button2.hasOnClickListeners()) {
                            player1Score += 1
                            player1ScoreTextView.text = player1Score.toString()
                        }
                        it.setOnClickListener(null)

                        vibrate()
                    }


                    button2.setOnClickListener  {
                        val currentTime = System.currentTimeMillis() - (50..70).random()
                        button2Time = (currentTime - zeroTime).toInt().absoluteValue
                        button2.text = "${button2Time / 1000}.${button2Time % 1000}"

                        if (button1.hasOnClickListeners()) {
                            player2Score += 1
                            player2ScoreTextView.text = player2Score.toString()
                        }
                        it.setOnClickListener(null)

                        vibrate()
                    }
                    countdownTextView.isClickable = true
                }
            }.start()
        }

    }
}

