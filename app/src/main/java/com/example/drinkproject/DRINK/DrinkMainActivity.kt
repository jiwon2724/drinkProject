package com.example.drinkproject.DRINK

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.drinkproject.R
import com.example.drinkproject.RESOURCE.ADAPTER.CombinationAdapter
import com.example.drinkproject.RESOURCE.ADAPTER.RecommendViewpagerAdapter
import com.example.drinkproject.RESOURCE.ADAPTER.TopViewpagerAdapter
import com.example.drinkproject.RESOURCE.LISTCLASS.combination
import com.example.drinkproject.RESOURCE.LISTCLASS.recommend
import kotlinx.android.synthetic.main.activity_drink_main.*
import java.util.*
import kotlin.collections.ArrayList

class DrinkMainActivity : AppCompatActivity(), View.OnClickListener {

    /** 배너 이미지 리스트 **/
    val bannerImageList = arrayListOf(
        R.drawable.top_viewpager_firstt,
        R.drawable.top_viewapger_second
    )
    /** 술 추천 리스트 **/
    val drinkIamgeList : ArrayList<recommend> = arrayListOf()
    /** 꿀조합 리스트 **/
    val combinationList : ArrayList<combination> = arrayListOf()

    val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_main)

        init()

        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin) // 페이지 간격 (Margin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pagerWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        bannerViewPager.adapter = TopViewpagerAdapter(this, bannerImageList, bannerViewPager)
        recommendViewPager.adapter = RecommendViewpagerAdapter(this, drinkIamgeList)

        /** left, right preview **/
        recommendViewPager.clipToPadding = false
        recommendViewPager.clipChildren = false
        recommendViewPager.offscreenPageLimit = 1 // 숫자는 최소 1이상이고 총계가 아닌 좌우 개수

        recommendViewPager.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }
        val lm = LinearLayoutManager(this)
        combinationRecyclerView.adapter = CombinationAdapter(this, combinationList)
        combinationRecyclerView.layoutManager = lm

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
        ssb.setSpan(AbsoluteSizeSpan(25, true), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        ssb.setSpan(StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return ssb
    }

    fun init(){
        sojuButton.setOnClickListener(this)
        beerButton.setOnClickListener(this)
        liquorButton.setOnClickListener(this)
        koreanTraditionalButton.setOnClickListener(this)

        /** 추천 술 임시 리스트 **/
        drinkIamgeList.add(recommend(R.drawable.stella, "스텔라", 4.9, 5.0, 38472))
        drinkIamgeList.add(recommend(R.drawable.desperados, "데스페라도스", 4.1, 5.9, 39))
        drinkIamgeList.add(recommend(R.drawable.kahlua, "깔루아", 4.5, 20.0, 10203))

        /** 꿀조합 임시 리스트 **/
        combinationList.add(
            combination(
                R.drawable.lemon,
                1,
                "jiwon2724",
                "블루레몬에이드밀키스주",
                "소주4 : 밀키스3 : 블루레몬에이드3",
                35,
                1004)
        )

        combinationList.add(
            combination(
                R.drawable.grape,
                2,
                "stopone3119",
                "탄산봉봉주",
                "포도봉봉4 : 소주3 : 탄산수3",
                12,
                333)
        )

        combinationList.add(
            combination(
                R.drawable.mango,
                3,
                "wonhye0763",
                "소망주",
                "소주3 : 망고링고7",
                7,
                91)
        )

        drinkChooseTextView.text = ssb(drinkChooseTextView.text.toString(), 0, 3)
        drinkRecommendTextView.text = ssb(drinkRecommendTextView.text.toString(), 0, 5)
        drinkRankTextView.text = ssb(drinkRankTextView.text.toString(), 0, 3)
    }

    /** 버튼 클릭 리스너 **/
    override fun onClick(view: View?) {
        when(view){
            sojuButton -> drinkListIntent("soju")
            beerButton -> drinkListIntent("beer")
            liquorButton -> drinkListIntent("liquor")
            koreanTraditionalButton -> drinkListIntent("makgeolli")
        }
    }

    fun drinkListIntent(drinkType : String?){
        val intent = Intent(this, DrinkListActivity::class.java)
        intent.putExtra("drinkType", drinkType)
        startActivity(intent)
    }
}