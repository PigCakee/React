package com.example.reaction.ui.duels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.reaction.R
import com.example.reaction.databinding.DuelsFragmentBinding
import kotlinx.android.synthetic.main.duels_fragment.*

class DuelsFragment : Fragment() {
    companion object {
        fun newInstance()
            = DuelsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: DuelsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.duels_fragment, container, false)
        val view: View = binding.root

        binding.viewModel = ViewModelProviders.of(this).get(DuelsViewModel::class.java)
        (binding.viewModel as DuelsViewModel).activity = activity
        (binding.viewModel as DuelsViewModel).context = this.context

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(DuelsViewModel::class.java)
        viewModel.avatarImageView = avatarImageView

        val liveData: LiveData<Boolean> = viewModel.changeFragment
        liveData.observe(viewLifecycleOwner, Observer {
            if (viewModel.changeFragment.value == true) {

                val bundle = Bundle()
                bundle.putInt("EnemyNumber", viewModel.number)

                val duelsGameFragment = DuelsGameFragment.newInstance()
                duelsGameFragment.arguments = bundle

                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, duelsGameFragment)
                    ?.addToBackStack("backStack")
                    ?.commit()
            }
        })

        avatarImageView.setImageResource(R.drawable.bandit_john)
    }
}