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
import com.example.drinkproject.RESOURCE.SpannableString
import com.example.drinkproject.USER.UserInfoActivity
import com.example.drinkproject.databinding.ActivityDrinkMainBinding
import kotlinx.android.synthetic.main.activity_drink_main.*
import java.util.*
import kotlin.collections.ArrayList

class DrinkMainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var bidning : ActivityDrinkMainBinding

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
        bidning = ActivityDrinkMainBinding.inflate(layoutInflater)
        setContentView(bidning.root)

        init()

        bidning.userMenuButton.setOnClickListener {
            val intent = Intent(this, UserInfoActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.xml.slide_left, R.xml.no_change)
        }

        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin) // 페이지 간격 (Margin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pagerWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        bidning.bannerViewPager.adapter = TopViewpagerAdapter(this, bannerImageList, bannerViewPager)
        bidning.recommendViewPager.adapter = RecommendViewpagerAdapter(this, drinkIamgeList)

        /** left, right preview **/
        bidning.recommendViewPager.apply {
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 1 // 숫자는 최소 1이상이고 총계가 아닌 좌우 개수
            setPageTransformer { page, position ->
                page.translationX = position * -offsetPx
            }
        }

        bidning.combinationRecyclerView.apply {
            adapter = CombinationAdapter(this@DrinkMainActivity, combinationList)
            layoutManager = LinearLayoutManager(this@DrinkMainActivity)
        }

        val headerRunnable = Runnable { bidning.bannerViewPager.currentItem = bannerViewPager.currentItem + 1 }

        /** Viewpager 넘김 처리  **/
        bidning.bannerViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(headerRunnable)
                handler.postDelayed(headerRunnable, 3000)
            }
        })
    }

    fun init(){
        bidning.sojuButton.setOnClickListener(this)
        bidning.beerButton.setOnClickListener(this)
        bidning.liquorButton.setOnClickListener(this)
        bidning.koreanTraditionalButton.setOnClickListener(this)

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

        bidning.drinkChooseTextView.text = SpannableString(size = 25, str = bidning.drinkChooseTextView.text.toString(), start = 0, end = 3).ssb()
        bidning.drinkRecommendTextView.text = SpannableString(size = 25, str = bidning.drinkRecommendTextView.text.toString(), start = 0, end = 5).ssb()
        bidning.drinkRankTextView.text = SpannableString(size = 25, str = bidning.drinkRankTextView.text.toString(), start = 0, end = 3).ssb()

    }

    /** 버튼 클릭 리스너 **/
    override fun onClick(view: View?) {
        when(view){
            bidning.sojuButton -> drinkListIntent("soju")
            bidning.beerButton -> drinkListIntent("beer")
            bidning.liquorButton -> drinkListIntent("liquor")
            bidning.koreanTraditionalButton -> drinkListIntent("makgeolli")
        }
    }

    fun drinkListIntent(drinkType : String?){
        val intent = Intent(this, DrinkListActivity::class.java)
        intent.putExtra("drinkType", drinkType)
        startActivity(intent)
    }
}