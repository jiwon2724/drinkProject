package com.example.drinkproject.DRINK

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.drinkproject.R
import kotlinx.android.synthetic.main.activity_drink_comment_edit.*

class DrinkCommentEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_comment_edit)

        goBackButton.setOnClickListener {
            finish()
        }
    }
}