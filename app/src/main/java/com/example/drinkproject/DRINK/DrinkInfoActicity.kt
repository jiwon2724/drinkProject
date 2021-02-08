package com.example.drinkproject.DRINK

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drinkproject.R
import com.example.drinkproject.RESOURCE.ADAPTER.CommentAdapter
import kotlinx.android.synthetic.main.activity_drink_info_acticity.*

class DrinkInfoActicity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_info_acticity)

        goBackBUtton.setOnClickListener {
            finish()
        }

        commentMoreTextView.setOnClickListener {
            val intent = Intent(this, DrinkCommentListActivity::class.java)
            //todo 주류 정보 넘기기
            startActivity(intent)
        }

        evaluationButton.setOnClickListener {
            val intent = Intent(this, DrinkCommentEditActivity::class.java)
            //todo 주류 정보 넘기기
            startActivity(intent)
        }
        /** test Recyclerview**/
        val testList = arrayListOf<String>("test11", "test22", "test33")
        drinkInfoCommentRecyclerView.layoutManager = LinearLayoutManager(this)
        drinkInfoCommentRecyclerView.adapter = CommentAdapter(this, testList)
    }
}