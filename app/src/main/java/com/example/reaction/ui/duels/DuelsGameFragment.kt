@file:Suppress("DEPRECATION")

package com.example.reaction.ui.duels

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.reaction.R
import com.example.reaction.databinding.DuelsGameFragmentBinding
import java.lang.Exception

class DuelsGameFragment : Fragment() {
    companion object {
        fun newInstance()
                = DuelsGameFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: DuelsGameFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.duels_game_fragment, container, false)
        val view = binding.root

        binding.viewModel = ViewModelProviders.of(this).get(DuelsGameViewModel::class.java)

        val viewModel = (binding.viewModel as DuelsGameViewModel)

        viewModel.activity = activity
        viewModel.context = this.context
        viewModel.enemyNumber = arguments?.getInt("EnemyNumber") ?: throw Exception("No enemy number")
        viewModel.playGame()

        val liveData: LiveData<Boolean> = viewModel.canPlayLiveData
        liveData.observe(viewLifecycleOwner, Observer{
            if (viewModel.canPlayLiveData.value == false){
                if (viewModel.isWon) {
                    Log.d("Game", "Won!")
                    //TODO start a win-window
                } else {
                    Log.d("Game", "Lost...")
                    //TODO start a lost-window
                }
            }
        })

        return view
    }
}