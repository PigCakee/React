package com.example.reaction.ui.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.reaction.R
import com.example.reaction.game.Player

class ShopFragment : Fragment() {
    companion object {
        fun newInstance()
            = ShopFragment()
    }

    private lateinit var viewModel: ShopViewModel
    private lateinit var player: Player

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.shop_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ShopViewModel::class.java)
        //TODO: use viewModel
        player.load(activity!!.parent)
    }

    override fun onPause() {
        super.onPause()
        player.save(activity!!.parent)
    }
}