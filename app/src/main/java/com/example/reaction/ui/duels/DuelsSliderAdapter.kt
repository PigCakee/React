package com.example.reaction.ui.duels

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.example.reaction.R
import com.example.reaction.databinding.DuelsSlideLayoutBinding

class DuelsSliderAdapter(private var context: Context, private var layoutInflater: LayoutInflater) : PagerAdapter() {

    private val slideImages
            = arrayOf(
        R.drawable.bandit_john,
        R.drawable.bandit_jack,
        R.drawable.bandit_dave
    )

    private val slideHeaders
            = arrayOf(
        "Bandit John",
        "Bandit Jack",
        "Bandit Dave"
    )

    private val slideDescriptions
            = arrayOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
    )

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as ConstraintLayout
    }

    override fun getCount(): Int {
        return slideImages.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = DataBindingUtil.inflate<DuelsSlideLayoutBinding>(
            LayoutInflater.from(this.context),
            R.layout.duels_slide_layout,
            container,
            false
        )

        binding.avatarImageView.setImageResource(slideImages[position])
        binding.headTextView.text = slideHeaders[position]
        binding.descTextView.text = slideDescriptions[position]

        container.addView(binding.root)

        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}