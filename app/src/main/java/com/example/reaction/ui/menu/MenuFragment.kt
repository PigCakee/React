package com.example.reaction.ui.menu

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.reaction.R
import com.example.reaction.ui.multiplayer.MultiplayerFragment
import kotlinx.android.synthetic.main.menu_fragment.*

class MenuFragment : Fragment() {
    companion object {
        fun newInstance()
            = MenuFragment()
    }

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
        // TODO: Use the ViewModel

        val vibrator = activity?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val canVibrate: Boolean = vibrator.hasVibrator()
        val milliseconds = 15L

        fun vibrate(){
            if (canVibrate) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    // API 26
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            milliseconds,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                } else {
                    // This method was deprecated in API level 26
                    vibrator.vibrate(milliseconds)
                }
            }
        }

        tournamentTextView.setOnClickListener {
            vibrate()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, MultiplayerFragment.newInstance())
                ?.commitNow()
            //TODO change fragment
        }

        playWithFriendTextView.setOnClickListener {
            vibrate()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, MultiplayerFragment.newInstance())
                ?.commitNow()
        }

        duelsTextView.setOnClickListener {
            vibrate()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, MultiplayerFragment.newInstance())
                ?.commitNow()
            //TODO change fragment
        }

        shopTextView.setOnClickListener {
            vibrate()
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, MultiplayerFragment.newInstance())
                ?.commitNow()
            //TODO change fragment
        }
    }

}