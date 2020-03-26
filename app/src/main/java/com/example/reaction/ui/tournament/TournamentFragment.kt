package com.example.reaction.ui.tournament

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
import com.example.reaction.util.Vibrator

class TournamentFragment : Fragment() {
    companion object {
        fun newInstance()
            = TournamentFragment()
    }

    private lateinit var viewModel: TournamentViewModel
    private var sharedPreferences: SharedPreferences? = null
    private var player = Player()
    private var milliseconds = 50L
    private val vibrator = Vibrator(activity)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.tournament_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TournamentViewModel::class.java)
        //TODO: use viewModel

        sharedPreferences = activity?.getSharedPreferences(player.preferences, Context.MODE_PRIVATE)
        if (sharedPreferences != null) {
            player.load(sharedPreferences!!)
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