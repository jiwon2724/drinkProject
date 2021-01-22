package com.example.drinkproject.DRINK

import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.drinkproject.R
import com.example.drinkproject.RESOURCE.ADAPTER.TopViewpagerAdapter
import kotlinx.android.synthetic.main.activity_drink_main.*
import java.util.*

class DrinkMainActivity : AppCompatActivity() {
    var page = 0
    val handler = Handler()
    val timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_main)
        init()

        val imgArrayList = arrayListOf(
            R.drawable.top_viewpager_firstt,
            R.drawable.top_viewapger_second,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_background,
            R.drawable.test_circle
        )

        topViewpager.adapter = TopViewpagerAdapter(this, imgArrayList)
        topViewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        /** Viewpager 넘김 처리 **/
        topViewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d("position : ", position.toString())
                val runnable = Runnable {
                    val numPages: Int = topViewpager.adapter!!.itemCount
                    page = page+1
                    when(page == topViewpager.adapter!!.itemCount){
                        true -> {
                            page = 0
                            topViewpager.setCurrentItem(page, true)
                        }
                        false -> {topViewpager.setCurrentItem(page, true)}
                    }

                }
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        handler.post(runnable)
                    }
                }, 3000)
            }
        })
    }

    fun ssb(str: String, start: Int, end: Int) : SpannableStringBuilder {
        val ssb = SpannableStringBuilder(str)
        ssb.setSpan(AbsoluteSizeSpan(30, true), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        ssb.setSpan(StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return ssb
    }

    fun init(){
        drinkChooseTextView.text = ssb(drinkChooseTextView.text.toString(), 0, 3)
        drinkRecommendTextView.text = ssb(drinkRecommendTextView.text.toString(), 0, 5)
        drinkRankTextView.text = ssb(drinkRankTextView.text.toString(), 0, 3)
    }
}