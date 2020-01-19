package com.anatoliyvinokurov.slidinglayoutkotlin

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener

class MainActivity : AppCompatActivity() {

    private var slideViewPager: ViewPager? = null
    private var dotsLayout: LinearLayout? = null
    private var sliderAdapter: SliderAdapter? = null
    private var dots: Array<TextView?> = arrayOfNulls(3)

    private var button_prew: Button? = null
    private var button_next: Button? = null
    private var main_background: ImageView? = null

    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        slideViewPager = findViewById<View>(R.id.slideViewPager) as ViewPager
        dotsLayout = findViewById<View>(R.id.dotsLayout) as LinearLayout

        button_prew = findViewById<View>(R.id.button_prew) as Button
        button_next = findViewById<View>(R.id.button_next) as Button

        main_background = findViewById<ImageView>(R.id.main_background) as ImageView

        sliderAdapter = SliderAdapter(this)
        slideViewPager!!.setAdapter(sliderAdapter)

        addDotsIndicator(0)
        slideViewPager!!.addOnPageChangeListener(viewListener)

        button_next!!.setOnClickListener(View.OnClickListener {
            slideViewPager!!.setCurrentItem(
                currentPage + 1
            )
        })

        button_prew!!.setOnClickListener(View.OnClickListener {
            slideViewPager!!.setCurrentItem(
                currentPage - 1
            )
        })

    }

    fun addDotsIndicator(position: Int) {
        dots = arrayOfNulls<TextView>(3)
        dotsLayout!!.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i]!!.setText(Html.fromHtml("&#8226;"))
            dots[i]!!.setTextSize(35f)
            dots[i]!!.setTextColor(resources.getColor(R.color.colorTransparentWhite))
            dotsLayout!!.addView(dots[i]!!)
        }
        if (dots.size > 0) {
            dots[position]!!.setTextColor(resources.getColor(R.color.colorWhite))
        }
    }

    var viewListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            addDotsIndicator(position)
            currentPage = position
            when (position) {
                0 -> {
                    main_background!!.setImageResource(R.drawable.main_background_level1)
                    button_next!!.setEnabled(true)
                    button_prew!!.setEnabled(false)
                    button_prew!!.setVisibility(View.INVISIBLE)
                    button_next!!.setVisibility(View.VISIBLE)
                    button_prew!!.setText("")
                    button_next!!.setText("Next")
                }
                1 -> {
                    main_background!!.setImageResource(R.drawable.main_background_level2)
                    button_next!!.setEnabled(true)
                    button_prew!!.setEnabled(true)
                    button_prew!!.setVisibility(View.VISIBLE)
                    button_next!!.setVisibility(View.VISIBLE)
                    button_prew!!.setText("Back")
                    button_next!!.setText("Next")
                }
                2 -> {
                    main_background!!.setImageResource(R.drawable.main_background_level3)
                    button_next!!.setEnabled(true)
                    button_prew!!.setEnabled(true)
                    button_prew!!.setVisibility(View.VISIBLE)
                    button_next!!.setVisibility(View.INVISIBLE)
                    button_prew!!.setText("Back")
                    button_next!!.setText("")
                }
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }


}
