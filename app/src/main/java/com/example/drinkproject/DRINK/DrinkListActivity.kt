package com.example.drinkproject.DRINK

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drinkproject.R
import com.example.drinkproject.RESOURCE.ADAPTER.DrinkListAdapter
import kotlinx.android.synthetic.main.activity_drink_list.*

class DrinkListActivity : AppCompatActivity(), View.OnClickListener {

    var drinkType : String =""
    val arrayList : ArrayList<String> = arrayListOf("asd", "sss", "123", "123", "123")
    var adapter = DrinkListAdapter(this, arrayList, 1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_list)
        init()
    }

    fun init(){
        when(intent.hasExtra("drinkType")){
            true -> { drinkType = intent.getStringExtra("drinkType")!! }
            false -> finish() // 들어온 값이 없을때 처리
        }

        gridButton.setOnClickListener(this)
        listButton.setOnClickListener(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
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
}