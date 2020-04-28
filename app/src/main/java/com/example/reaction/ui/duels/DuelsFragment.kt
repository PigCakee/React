@file:Suppress("DEPRECATION")

package com.example.reaction.ui.duels

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.reaction.R
import com.example.reaction.databinding.DuelsFragmentBinding
import com.example.reaction.util.vibrator.Vibrator
import com.example.reaction.util.view.PAGES_NUMBER
import com.example.reaction.util.viewmodel.viewModel

class DuelsFragment : Fragment() {
    companion object {
        fun newInstance()
            = DuelsFragment()
    }

    private val model by viewModel<DuelsViewModel>()
    private lateinit var binding: DuelsFragmentBinding

    private lateinit var sliderAdapter: DuelsSliderAdapter
    private lateinit var sliderDots: Array<TextView?>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.duels_fragment, container, false)
        binding.model = model
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sliderAdapter = DuelsSliderAdapter(context!!, layoutInflater)
        binding.viewPager.adapter = sliderAdapter

        addDotsIndicator()

        model.setUpEnemies()

        handleViewPager()

        model.playGameCommand.observe(this){
            playGame()
        }

        model.vibratorCommand.observe(this){
            val vibrator: Vibrator = Vibrator.getInstance()
            vibrator.vibrate(model.milliseconds)
        }

        model.soundCommand.observe(this){
            //TODO play sound
        }
    }

    private fun handleViewPager() {
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                model.enemyNumber = position
                model.setUpEnemies()
                if (sliderDots.isNotEmpty()) {
                    for (i in sliderDots.indices) {
                        if (i == position) sliderDots[i]!!.setTextColor(resources.getColor(R.color.colorWhite))
                        else sliderDots[i]!!.setTextColor(resources.getColor(R.color.colorTransparentWhite))
                    }
                }
            }
        })
    }

    private fun playGame() {
        val bundle = Bundle()
        bundle.putInt("EnemyNumber", model.enemyNumber)

        val duelsGameFragment = DuelsGameFragment.newInstance()
        duelsGameFragment.arguments = bundle

        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, duelsGameFragment)
            ?.addToBackStack("backStack")
            ?.commit()
    }

    private fun addDotsIndicator(){
        sliderDots = arrayOfNulls(PAGES_NUMBER)
        for (i in sliderDots.indices) {
            sliderDots[i] = TextView(context)
            sliderDots[i]!!.text = Html.fromHtml("&#8226;")
            sliderDots[i]!!.textSize = 35f
            sliderDots[i]!!.setTextColor(activity!!.resources.getColor(R.color.colorTransparentWhite))

            binding.dots.addView(sliderDots[i])
        }

        sliderDots[0]!!.setTextColor(resources.getColor(R.color.colorWhite))
    }
}