package com.example.reaction

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


@Suppress("DEPRECATION")
class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_activity)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val canVibrate: Boolean = vibrator.hasVibrator()
        val milliseconds = 25L

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

        val gamemodeTextView: TextView = findViewById(R.id.gamemodeTextView)
        val playButton: ImageView = findViewById(R.id.playButton)
        val gamemodeSwitch: Switch = findViewById(R.id.gamemodeSwitch)

        playButton.setOnClickListener {
            vibrate()
            if (gamemodeTextView.text == resources.getString(R.string.singleplayer)){
                val intent = Intent(this, SingleplayerActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, MultiplayerActivity::class.java)
                startActivity(intent)
            }
        }

        gamemodeSwitch.setOnClickListener {
            vibrate()
            if (gamemodeTextView.text == resources.getString(R.string.singleplayer)){
                gamemodeTextView.text = resources.getString(R.string.multiplayer)
            } else {
                gamemodeTextView.text = resources.getString(R.string.singleplayer)
            }
        }
    }
}