package com.example.reaction

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.reaction.ui.menu.MenuFragment

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_activity)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)


        supportFragmentManager.beginTransaction() //set up and display menu fragment
            .replace(R.id.container, MenuFragment.newInstance())
            .addToBackStack("backStack")
            .commit()
    }

    //TODO set the UI (big play button and screen picture
}

