package com.example.reaction.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.example.reaction.R

class ShopSliderAdapter(private var context: Context, private var layoutInflater: LayoutInflater) : PagerAdapter() {

    private val slideImages
            = arrayOf(
        R.drawable.revolver1,
        R.drawable.revolver2,
        R.drawable.revolver3
    )

    private val slideHeaders
            = arrayOf(
        "Revolver 1",
        "Revolver 2",
        "Revolver 3"
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
        layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = layoutInflater.inflate(R.layout.shop_slide_layout, container, false)

        val gunImageView: ImageView = view.findViewById(R.id.gunImageView)
        val gunTextView: TextView = view.findViewById(R.id.gunTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)

        gunImageView.setImageResource(slideImages[position])
        gunTextView.text = slideHeaders[position]
        descriptionTextView.text = slideDescriptions[position]

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}