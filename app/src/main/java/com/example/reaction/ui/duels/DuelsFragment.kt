package com.example.reaction.ui.duels

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.reaction.R
import com.example.reaction.databinding.DuelsFragmentBinding
import com.example.reaction.game.Enemy
import com.example.reaction.game.Player

class DuelsFragment : Fragment() {
    companion object {
        fun newInstance()
            = DuelsFragment()
    }

    private var sharedPreferences: SharedPreferences? = null
    private var player = Player()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: DuelsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.duels_fragment, container, false)
        val view: View = binding.root

        binding.viewModel = ViewModelProviders.of(this).get(DuelsViewModel::class.java)
        (binding.viewModel as DuelsViewModel).activity = activity
        //(binding.viewModel as DuelsViewModel).playGame()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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