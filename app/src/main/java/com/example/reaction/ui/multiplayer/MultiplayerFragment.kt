package com.example.reaction.ui.multiplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.reaction.R

class MultiplayerFragment : Fragment() {

    companion object {
        fun newInstance() =
            MultiplayerFragment()
    }

    private lateinit var viewModel: MultiplayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.multiplayer_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MultiplayerViewModel::class.java)
        // TODO: Use the ViewModel
    }
}