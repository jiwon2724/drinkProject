package com.example.drinkproject.USER

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import com.example.drinkproject.R

class UserInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        val displayMetrics : DisplayMetrics = resources.displayMetrics  // 화면정보 획득
        // applicationContext.display?.getRealMetrics(displayMetrics) // 디바이스 실제 사이즈

        val width = displayMetrics.widthPixels
        val height = displayMetrics.heightPixels
        val params : WindowManager.LayoutParams = window.attributes
        params.x = (width/2)
        params.height = height
        params.width = (width*.75).toInt()
        params.y = -(height/2)
        this.window.attributes = params
    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(R.xml.slide_right, R.xml.no_change)
    }
}