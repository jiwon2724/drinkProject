package com.example.drinkproject.DRINK

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drinkproject.R
import com.example.drinkproject.RESOURCE.ADAPTER.CommentAdapter
import com.example.drinkproject.databinding.ActivityDrinkInfoActicityBinding
import kotlinx.android.synthetic.main.activity_drink_info_acticity.*

class DrinkInfoActicity : AppCompatActivity() {

    lateinit var binding : ActivityDrinkInfoActicityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrinkInfoActicityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goBackBUtton.setOnClickListener { finish() }
        binding.commentMoreTextView.setOnClickListener {
            val intent = Intent(this, DrinkCommentListActivity::class.java)
            //todo 주류 정보 넘기기
            startActivity(intent)
        }

        binding.evaluationButton.setOnClickListener {
            val intent = Intent(this, DrinkCommentEditActivity::class.java)
            //todo 주류 정보 넘기기
            startActivity(intent)
        }

        /** test Recyclerview**/
        val testList = arrayListOf<String>("test11", "test22", "test33")
        binding.drinkInfoCommentRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.drinkInfoCommentRecyclerView.adapter = CommentAdapter(this, testList)
    }
}