package com.example.drinkproject.DRINK

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.drinkproject.R
import com.example.drinkproject.RESOURCE.ADAPTER.testAdapter
import kotlinx.android.synthetic.main.activity_drink_list.*

class DrinkListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_list)

        val arrayList : ArrayList<String> = arrayListOf("asd", "sss", "123", "123", "123")

        val adapter = testAdapter(this, arrayList)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
    }
}