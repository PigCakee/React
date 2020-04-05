package com.example.reaction.ui.duels

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.example.reaction.R
import com.example.reaction.databinding.DuelsFragmentBinding
import com.example.reaction.util.DuelsSliderAdapter
import com.example.reaction.util.PAGES_NUMBER
import kotlinx.android.synthetic.main.duels_slide_layout.*

class DuelsFragment : Fragment() {
    companion object {
        fun newInstance()
            = DuelsFragment()
    }

    private lateinit var viewPager: ViewPager
    private lateinit var dotsLayout: LinearLayout
    private lateinit var sliderAdapter: DuelsSliderAdapter
    private lateinit var sliderDots: Array<TextView?>

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

        val viewModel: DuelsViewModel = ViewModelProviders.of(this).get(DuelsViewModel::class.java)
        viewModel.avatarImageView = avatarImageView

        dotsLayout = activity!!.findViewById(R.id.dotsDuelsLayout)
        viewPager = activity!!.findViewById(R.id.duelsViewPager)
        sliderAdapter = DuelsSliderAdapter(context!!, layoutInflater)

        viewPager.adapter = sliderAdapter

        addDotsIndicator()
        viewModel.setUpEnemies()

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
            override fun onPageSelected(position: Int) {
                viewModel.number = position
                viewModel.setUpEnemies()
                if (sliderDots.isNotEmpty()){
                    for (i in sliderDots.indices) {
                        if (i == position) sliderDots[i]!!.setTextColor(resources.getColor(R.color.colorWhite))
                        else sliderDots[i]!!.setTextColor(resources.getColor(R.color.colorTransparentWhite))
                    }
                }
            }
        })


        val liveData: LiveData<Boolean> = viewModel.changeFragment
        Log.d("Message", viewModel.changeFragment.value.toString())
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
    }

    private fun addDotsIndicator(){
        sliderDots = arrayOfNulls(PAGES_NUMBER)
        for (i in sliderDots.indices) {
            sliderDots[i] = TextView(context)
            sliderDots[i]!!.text = Html.fromHtml("&#8226;")
            sliderDots[i]!!.textSize = 35f
            sliderDots[i]!!.setTextColor(activity!!.resources.getColor(R.color.colorTransparentWhite))

            dotsLayout.addView(sliderDots[i])
        }

        sliderDots[0]!!.setTextColor(resources.getColor(R.color.colorWhite))
    }
}