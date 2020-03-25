package com.example.reaction.ui.duels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.reaction.R
import com.example.reaction.game.Player

class DuelsFragment : Fragment() {
    companion object {
        fun newInstance()
            = DuelsFragment()
    }

    private lateinit var viewModel: DuelsViewModel
    private lateinit var player: Player

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.duels_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DuelsViewModel::class.java)
        //TODO: use viewModel
        player.load(activity!!.parent)
    }

    override fun onPause() {
        super.onPause()
        player.save(activity!!.parent)
    }
}