package com.example.drinkproject.DRINK

import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.drinkproject.R
import com.example.drinkproject.RESOURCE.ADAPTER.RecommendViewpagerAdapter
import com.example.drinkproject.RESOURCE.ADAPTER.TopViewpagerAdapter
import com.example.drinkproject.RESOURCE.LISTCLASS.recommend
import kotlinx.android.synthetic.main.activity_drink_main.*
import java.util.*
import kotlin.collections.ArrayList

class DrinkMainActivity : AppCompatActivity() {

    /** 배너 이미지 리스트 **/
    val bannerImageList = arrayListOf(
        R.drawable.top_viewpager_firstt,
        R.drawable.top_viewapger_second
    )

    /** 술 추천 이미지 리스트 **/
    val drinkIamgeList : ArrayList<recommend> = arrayListOf()

    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_main)

        init()

        bannerViewPager.adapter = TopViewpagerAdapter(this, bannerImageList, bannerViewPager)
        recommendViewPager.adapter = RecommendViewpagerAdapter(this, drinkIamgeList)



        /** 인디케이터 아직 미완 **/
//        indicator.setViewPager(topViewpager)
//        indicator.createIndicators(imgArrayList.size, 0)

        val headerRunnable = Runnable {
            bannerViewPager.currentItem = bannerViewPager.currentItem + 1
        }

        /** Viewpager 넘김 처리  **/
        bannerViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(headerRunnable)
                handler.postDelayed(headerRunnable, 3000)
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
        /** 추천 술 임시 리스트 **/
        drinkIamgeList.add(recommend(R.drawable.stella, "스텔라", 4.9, 5.0, 38472))
        drinkIamgeList.add(recommend(R.drawable.desperados, "데스페라도스", 4.1, 5.9, 39))
        drinkIamgeList.add(recommend(R.drawable.kahlua, "깔루아", 4.5, 20.0, 10203))

        drinkChooseTextView.text = ssb(drinkChooseTextView.text.toString(), 0, 3)
        drinkRecommendTextView.text = ssb(drinkRecommendTextView.text.toString(), 0, 5)
        drinkRankTextView.text = ssb(drinkRankTextView.text.toString(), 0, 3)
    }
}