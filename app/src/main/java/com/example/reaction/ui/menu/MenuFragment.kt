@file:Suppress("DEPRECATION")

package com.example.reaction.ui.menu

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.reaction.R
import com.example.reaction.game.Player
import com.example.reaction.ui.duels.DuelsFragment
import com.example.reaction.ui.multiplayer.MultiplayerFragment
import com.example.reaction.ui.shop.ShopFragment
import com.example.reaction.ui.tournament.TournamentFragment
import kotlinx.android.synthetic.main.menu_fragment.*

class MenuFragment : Fragment() {
    companion object {
        fun newInstance()
            = MenuFragment()
    }

    private lateinit var viewModel: MenuViewModel
    private var sharedPreferences: SharedPreferences? = null
    private var player = Player()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.menu_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MenuViewModel::class.java)
        // TODO: Use the ViewModel

        sharedPreferences = activity?.getSharedPreferences(player.preferences, Context.MODE_PRIVATE)
        if (sharedPreferences != null) {
            player.load(sharedPreferences!!)
        }

        val vibrator = activity?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val canVibrate: Boolean = vibrator.hasVibrator()
        val milliseconds = 15L

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

        tournamentTextView.setOnClickListener {
            vibrate()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, TournamentFragment.newInstance())
                ?.commitNow()
            //TODO change fragment
        }

        playWithFriendTextView.setOnClickListener {
            vibrate()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, MultiplayerFragment.newInstance())
                ?.commitNow()
        }

        duelsTextView.setOnClickListener {
            vibrate()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, DuelsFragment.newInstance())
                ?.commitNow()
            //TODO change fragment
        }

        shopTextView.setOnClickListener {
            vibrate()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, ShopFragment.newInstance())
                ?.commitNow()
            //TODO change fragment
        }
    }

    override fun onPause() {
        super.onPause()
        sharedPreferences = activity?.getSharedPreferences(player.preferences, Context.MODE_PRIVATE)
        if (sharedPreferences != null) {
            player.save(sharedPreferences!!)
        }
    }
}