@file:Suppress("DEPRECATION")

package com.example.reaction.ui.shop

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.reaction.R
import com.example.reaction.util.PAGES_NUMBER
import com.example.reaction.util.ShopSliderAdapter


class ShopFragment : Fragment() {
    companion object {
        fun newInstance()
            = ShopFragment()
    }

    private lateinit var viewPager: ViewPager
    private lateinit var dotsLayout: LinearLayout
    private lateinit var sliderAdapter: ShopSliderAdapter
    private lateinit var sliderDots: Array<TextView?>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.shop_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dotsLayout = activity!!.findViewById(R.id.dotsLayout)
        viewPager = activity!!.findViewById(R.id.shopViewPager)
        sliderAdapter = ShopSliderAdapter(context!!, layoutInflater)

        viewPager.adapter = sliderAdapter

        addDotsIndicator()

        viewPager.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
            override fun onPageSelected(position: Int) {
                if (sliderDots.isNotEmpty()){
                    for (i in sliderDots.indices) {
                        if (i == position) sliderDots[i]!!.setTextColor(resources.getColor(R.color.colorWhite))
                        else sliderDots[i]!!.setTextColor(resources.getColor(R.color.colorTransparentWhite))
                    }
                }
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


    }

}