package com.example.drinkproject.DRINK

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.drinkproject.R
import com.example.drinkproject.databinding.ActivityDrinkCommentEditBinding
import kotlinx.android.synthetic.main.activity_drink_comment_edit.*

class DrinkCommentEditActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDrinkCommentEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrinkCommentEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goBackButton.setOnClickListener { finish() }
    }
}