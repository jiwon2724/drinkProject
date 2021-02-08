package com.example.drinkproject.DRINK

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drinkproject.R
import com.example.drinkproject.RESOURCE.ADAPTER.CommentAdapter
import kotlinx.android.synthetic.main.activity_drink_comment_list.*

class DrinkCommentListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_comment_list)

        goBackButton.setOnClickListener {
            finish()
        }

        val testList = arrayListOf<String>("test1", "test2", "test3", "test4", "test5")
        commentListRecyclerView.layoutManager = LinearLayoutManager(this)
        commentListRecyclerView.adapter = CommentAdapter(this, testList)
    }
}