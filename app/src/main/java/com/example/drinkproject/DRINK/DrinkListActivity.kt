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
import com.example.drinkproject.databinding.ActivityDrinkListBinding
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_drink_list.*
import kotlinx.android.synthetic.main.activity_drink_main.*

class DrinkListActivity : AppCompatActivity(), View.OnClickListener, TabLayout.OnTabSelectedListener {

    lateinit var binding : ActivityDrinkListBinding

    var drinkType : String = ""
    val arrayList : ArrayList<String> = arrayListOf("asd", "sss", "123", "123", "123") // recyclerview test
    var adapter = DrinkListAdapter(this, arrayList, 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrinkListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        binding.goBackImageButton.setOnClickListener { finish() }

        /** 임시 DrinkInfoActivity테스트  원래는 어댑터 아이템 클릭시로 이동 **/
        binding.sortTextView.setOnClickListener {
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

        binding.dirnkTabLayout.addOnTabSelectedListener(this)
        binding.gridButton.setOnClickListener(this)
        binding.listButton.setOnClickListener(this)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
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
            binding.gridButton -> {
                arrayList.clear()
                val adapter = DrinkListAdapter(this, listArrayList, 0)
                binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
                binding.recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }
            binding.listButton -> {
                arrayList.clear()
                val adapter = DrinkListAdapter(this, listArrayList, 1)
                binding.recyclerView.layoutManager = LinearLayoutManager(this)
                binding.recyclerView.adapter = adapter
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