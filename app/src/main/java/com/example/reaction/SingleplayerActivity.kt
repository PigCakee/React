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

class SingleplayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singleplayer)

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
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val computerButton: Button = findViewById(R.id.computerButton)
        val playerButton: Button = findViewById(R.id.playerButton)
        val playerScoreTextView: TextView = findViewById(R.id.playerScore)
        val computerScoreTextView: TextView = findViewById(R.id.computerScore)
        val countdownTextView: TextView = findViewById(R.id.countdownTextView2)

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

        countdownTextView.setTextColor(resources.getColor(R.color.colorWhite))
        countdownTextView.text = resources.getString(R.string.start)

        var playerTime: Int
        var computerTime: Int
        var playerScore = 0
        var computerScore = 0

        countdownTextView.setOnClickListener {
            vibrate()

            computerButton.text = resources.getString(R.string.computerButton)
            playerButton.text = resources.getString(R.string.tap)

            object : CountDownTimer((2500..8000).random().toLong(), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    countdownTextView.setTextColor(resources.getColor(R.color.colorWhite))
                    countdownTextView.textSize = 36f
                    countdownTextView.text = resources.getString(R.string.ready)
                    countdownTextView.isClickable = false

                    playerButton.setOnClickListener {
                        computerScore++
                        computerScoreTextView.text = computerScore.toString()
                    }

                }

                @SuppressLint("SetTextI18n")
                override fun onFinish() {
                    val zeroTime = System.currentTimeMillis()

                    countdownTextView.setTextColor(resources.getColor(R.color.colorPrimaryRed))
                    countdownTextView.textSize = 72f
                    countdownTextView.text = resources.getString(R.string.go)

                    playerButton.setOnClickListener {
                        val currentTime = System.currentTimeMillis() - (50..80).random()
                        playerTime = (currentTime - zeroTime).toInt().absoluteValue
                        computerTime = (200..500).random()

                        if (playerTime < computerTime) {
                            playerScore += 1
                            playerScoreTextView.text = playerScore.toString()
                        } else {
                            computerScore += 1
                            computerScoreTextView.text = computerScore.toString()
                        }

                        playerButton.text = "${playerTime / 1000}.${playerTime % 1000}"
                        computerButton.text = "${computerTime / 1000}.${computerTime % 1000}"

                        it.setOnClickListener(null)

                        vibrate()
                    }

                    countdownTextView.isClickable = true
                }
            }.start()
        }
    }
}