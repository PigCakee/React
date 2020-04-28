@file:Suppress("DEPRECATION")

package com.example.reaction.ui.shop

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.reaction.R
import com.example.reaction.databinding.ShopFragmentBinding
import com.example.reaction.util.vibrator.Vibrator
import com.example.reaction.util.view.PAGES_NUMBER
import com.example.reaction.util.viewmodel.viewModel


class ShopFragment : Fragment() {
    companion object {
        fun newInstance()
            = ShopFragment()
    }

    private lateinit var sliderAdapter: ShopSliderAdapter
    private lateinit var sliderDots: Array<TextView?>

    private val model by viewModel<ShopViewModel>()
    private lateinit var binding: ShopFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.shop_fragment, container, false)
        binding.model = model
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sliderAdapter = ShopSliderAdapter(context!!, layoutInflater)
        binding.shopViewPager.adapter = sliderAdapter

        addDotsIndicator()
        model.setupBuySelectButton()

        binding.shopViewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
            override fun onPageSelected(position: Int) {
                if (sliderDots.isNotEmpty()){
                    model.weaponSelected = position
                    model.setupBuySelectButton()
                    for (i in sliderDots.indices) {
                        if (i == position) sliderDots[i]!!.setTextColor(resources.getColor(R.color.colorWhite))
                        else sliderDots[i]!!.setTextColor(resources.getColor(R.color.colorTransparentWhite))
                    }
                }
            }
        })

        model.vibratorCommand.observe(this){
            val vibrator: Vibrator = Vibrator.getInstance()
            vibrator.vibrate(model.milliseconds)
        }

        model.preferencesCommand.observe(this){
            model.sharedPreferences = activity!!.getSharedPreferences(model.preferences, Context.MODE_PRIVATE)
        }

        model.moneyCommand.observe(this){
            Toast.makeText(context, "You have not enough money to buy!", Toast.LENGTH_SHORT).show()
        }

        model.soundCommand.observe(this){
            //Todo play sound
        }
    }

    private fun addDotsIndicator(){
        sliderDots = arrayOfNulls(PAGES_NUMBER)
        for (i in sliderDots.indices) {
            sliderDots[i] = TextView(context)
            sliderDots[i]!!.text = Html.fromHtml("&#8226;")
            sliderDots[i]!!.textSize = 35f
            sliderDots[i]!!.setTextColor(activity!!.resources.getColor(R.color.colorTransparentWhite))

            binding.dotsLayout.addView(sliderDots[i])
        }

        sliderDots[0]!!.setTextColor(resources.getColor(R.color.colorWhite))
    }

}