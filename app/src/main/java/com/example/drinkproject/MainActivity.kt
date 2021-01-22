package com.example.drinkproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.drinkproject.DRINK.DrinkListActivity
import com.example.drinkproject.DRINK.DrinkMainActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moveDrinkListTextView.setOnClickListener {
            val intent = Intent(this, DrinkMainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}