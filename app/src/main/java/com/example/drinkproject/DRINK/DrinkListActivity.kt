package com.example.drinkproject.DRINK

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drinkproject.R
import com.example.drinkproject.RESOURCE.ADAPTER.DrinkListAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_drink_list.*
import kotlinx.android.synthetic.main.activity_drink_main.*

class DrinkListActivity : AppCompatActivity(), View.OnClickListener, TabLayout.OnTabSelectedListener{

    var drinkType : String = ""
    val arrayList : ArrayList<String> = arrayListOf("asd", "sss", "123", "123", "123")
    var adapter = DrinkListAdapter(this, arrayList, 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_list)
        init()
        goBackImageButton.setOnClickListener { finish() }

        /** 임시 DrinkInfoActivity테스트  원래는 어댑터 아이템 클릭시로 이동 **/
        sortTextView.setOnClickListener {
            val intent = Intent(this, DrinkInfoActicity::class.java)
            startActivity(intent)
        }
    }

    /** 시작시 필요한 작업 세팅 **/
    fun init(){
        when(intent.hasExtra("drinkType")){
            true -> {
                drinkType = intent.getStringExtra("drinkType")!!
                tabItemClick()
            }
            false -> finish() // 들어온 값이 없을때 처리
        }

        dirnkTabLayout.addOnTabSelectedListener(this)
        gridButton.setOnClickListener(this)
        listButton.setOnClickListener(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    /** DirnkMainActivity에서 넘어온 주류로 탭 선택 **/
    fun tabItemClick(){
        when(drinkType) {
            "soju" -> dirnkTabLayout.selectTab(dirnkTabLayout.getTabAt(0))
            "beer" -> dirnkTabLayout.selectTab(dirnkTabLayout.getTabAt(1))
            "liquor" -> dirnkTabLayout.selectTab(dirnkTabLayout.getTabAt(2))
            "makgeolli" -> dirnkTabLayout.selectTab(dirnkTabLayout.getTabAt(3))
        }
    }

    override fun onClick(view: View?) {
        val listArrayList : ArrayList<String> = arrayListOf("asd", "sss", "123", "123", "123")
        when(view){
            gridButton -> {
                arrayList.clear()
                val adapter = DrinkListAdapter(this, listArrayList, 0)
                recyclerView.layoutManager = GridLayoutManager(this, 2)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }
            listButton -> {
                arrayList.clear()
                val adapter = DrinkListAdapter(this, listArrayList, 1)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }
    }

    /** 탭 클릭시 **/
    override fun onTabSelected(tab: TabLayout.Tab?) {
        Log.d("로그", "onTabSelected")
        // todo http 통신
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        Log.d("로그", "onTabUnselected")
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        Log.d("로그", "onTabReselected")
    }
}