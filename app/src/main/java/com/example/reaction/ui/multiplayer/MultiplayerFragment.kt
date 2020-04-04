@file:Suppress("DEPRECATION")

package com.example.reaction.ui.multiplayer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.reaction.R
import com.example.reaction.databinding.MultiplayerFragmentBinding

class MultiplayerFragment : Fragment() {

    companion object {
        fun newInstance() =
            MultiplayerFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Multiplayer", "Started")
        val binding: MultiplayerFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.multiplayer_fragment, container, false)
        val view: View = binding.root
        binding.multiplayerViewModel = ViewModelProviders.of(this).get(MultiplayerViewModel::class.java)
        (binding.multiplayerViewModel as MultiplayerViewModel).activity = activity
        (binding.multiplayerViewModel as MultiplayerViewModel).context = context
        (binding.multiplayerViewModel as MultiplayerViewModel).playGame()
        return view
    }
}