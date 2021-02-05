package com.example.drinkproject.DRINK

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.drinkproject.R
import kotlinx.android.synthetic.main.activity_drink_info_acticity.*

class DrinkInfoActicity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_info_acticity)

        goBackBUtton.setOnClickListener {
            finish()
        }
    }
}