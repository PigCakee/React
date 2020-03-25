package com.example.reaction.ui.singleplayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.reaction.R

class SingleplayerFragment : Fragment() {
    companion object {
        fun newInstance()
            = SingleplayerFragment()
    }

    private lateinit var viewModel: SingleplayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.singleplayer_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SingleplayerViewModel::class.java)
        //TODO: use viewModel
    }
}