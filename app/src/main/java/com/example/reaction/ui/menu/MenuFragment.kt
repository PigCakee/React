@file:Suppress("DEPRECATION")

package com.example.reaction.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.reaction.R
import com.example.reaction.ui.duels.DuelsFragment
import com.example.reaction.ui.multiplayer.MultiplayerFragment
import com.example.reaction.ui.shop.ShopFragment
import com.example.reaction.ui.tournament.TournamentFragment
import com.example.reaction.util.Vibrator
import kotlinx.android.synthetic.main.menu_fragment.*
import android.util.Log
import androidx.lifecycle.ViewModelProviders

class MenuFragment : Fragment() {
    companion object {
        fun newInstance()
            = MenuFragment()
    }

    private var milliseconds = 10L
    private var vibrator: Vibrator = Vibrator.getInstance()
    private lateinit var viewModel: MenuViewModel

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

        viewModel.activity = activity
        vibrator.activity = activity

        viewModel.instantiateGameEntities()

        tournamentTextView.setOnClickListener {
            vibrator.vibrate(milliseconds)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, TournamentFragment.newInstance())
                ?.addToBackStack("backStack")
                ?.commit()
        }

        playWithFriendTextView.setOnClickListener {
            vibrator.vibrate(milliseconds)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, MultiplayerFragment.newInstance())
                ?.addToBackStack("backStack")
                ?.commit()
        }

        duelsTextView.setOnClickListener {
            vibrator.vibrate(milliseconds)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, DuelsFragment.newInstance())
                ?.addToBackStack("backStack")
                ?.commit()
        }

        shopTextView.setOnClickListener {
            vibrator.vibrate(milliseconds)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, ShopFragment.newInstance())
                ?.addToBackStack("backStack")
                ?.commit()
        }
    }
}