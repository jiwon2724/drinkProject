package com.example.drinkproject.USER

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drinkproject.R
import com.example.drinkproject.RESOURCE.ADAPTER.UserInfoAdatper
import com.example.drinkproject.RESOURCE.App
import com.example.drinkproject.RESOURCE.Constant
import com.example.drinkproject.RESOURCE.ItemClickListener
import kotlinx.android.synthetic.main.activity_user_info.*

class UserInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)
        init()

        val itemList = arrayListOf<String>("bar", "설정", "bar", "내가 평가한 주류", "나의 주류 레벨", "내가 찜한 주류", "bar", "로그아웃")
        val userInfoAdatper = UserInfoAdatper(this, itemList)
        userRecyclerView.adapter = userInfoAdatper
        userRecyclerView.layoutManager = LinearLayoutManager(this)

        closeButton.setOnClickListener {
            finish()
            overridePendingTransition(R.xml.no_change,R.xml.slide_right)
        }

        userInfoAdatper.setItemClickListner(object : ItemClickListener{
            override fun onClick(view: View, position: Int) {
                Log.d("postion : ", position.toString())
            }
        })
    }

    fun init(){
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

        userIdTextView.text = App.prefs.getStringData(Constant.userId)


    }

    override fun onBackPressed() {
        finish()
        overridePendingTransition(R.xml.no_change,R.xml.slide_right)
    }
}