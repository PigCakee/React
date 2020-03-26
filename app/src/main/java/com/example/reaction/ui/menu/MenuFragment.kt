@file:Suppress("DEPRECATION")

package com.example.reaction.ui.menu

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
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
import com.example.reaction.util.Vibrator
import kotlinx.android.synthetic.main.menu_fragment.*

class MenuFragment : Fragment() {
    companion object {
        fun newInstance()
            = MenuFragment()
    }

    private lateinit var viewModel: MenuViewModel
    private var sharedPreferences: SharedPreferences? = null
    private var player = Player()
    private var milliseconds = 15L
    private val vibrator = Vibrator(activity)

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

        val vibrator = Vibrator(activity)

        tournamentTextView.setOnClickListener {
            vibrator.vibrate(milliseconds)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, TournamentFragment.newInstance())
                ?.commitNow()
            //TODO change fragment
        }

        playWithFriendTextView.setOnClickListener {
            vibrator.vibrate(milliseconds)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, MultiplayerFragment.newInstance())
                ?.commitNow()
        }

        duelsTextView.setOnClickListener {
            vibrator.vibrate(milliseconds)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, DuelsFragment.newInstance())
                ?.commitNow()
            //TODO change fragment
        }

        shopTextView.setOnClickListener {
            vibrator.vibrate(milliseconds)
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