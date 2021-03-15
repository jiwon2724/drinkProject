package com.example.drinkproject.DRINK

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drinkproject.R
import com.example.drinkproject.RESOURCE.ADAPTER.CommentAdapter
import com.example.drinkproject.databinding.ActivityDrinkCommentListBinding
import kotlinx.android.synthetic.main.activity_drink_comment_list.*

class DrinkCommentListActivity : AppCompatActivity() {

    lateinit var bidning : ActivityDrinkCommentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bidning = ActivityDrinkCommentListBinding.inflate(layoutInflater)
        setContentView(bidning.root)

        bidning.goBackButton.setOnClickListener { finish() }

        val testList = arrayListOf<String>("test1", "test2", "test3", "test4", "test5")
        bidning.commentListRecyclerView.layoutManager = LinearLayoutManager(this)
        bidning.commentListRecyclerView.adapter = CommentAdapter(this, testList)
    }
}