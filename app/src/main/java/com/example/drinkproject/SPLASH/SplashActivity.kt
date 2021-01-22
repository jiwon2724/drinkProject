package com.example.drinkproject.SPLASH

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.drinkproject.LOGIN.LoginActivity
import com.example.drinkproject.LOGIN.SignUpActivity
import com.example.drinkproject.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 1500 // 1초

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            checkNetwork()
        }, SPLASH_TIME_OUT)
    }

    private fun checkNetwork() {
        stateTextView.text = "네트워크를 확인하고 있습니다."
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager // 인터넷 연결이 설정되어있는지 확인을 위한 변수 선언
        val networkInfo = cm.activeNetworkInfo // 네트워크 정보를 받을 변수 선언

        if ((networkInfo != null && networkInfo.isConnected)) {
            if (networkInfo.type == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(this, "와이파이에 연결되었습니다.", Toast.LENGTH_SHORT).show()
            }
            if (networkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                Toast.makeText(this, "모바일 데이터에 연결되었습니다.", Toast.LENGTH_SHORT).show()
            }

            /** 1초동안 딜레이 후 appVersionCheck() 함수 호출 **/
            Handler(Looper.getMainLooper()).postDelayed({
                checkUserInfo()
            }, SPLASH_TIME_OUT)
        }
    }

    private fun checkUserInfo() {
        stateTextView.text = "사용자 정보 확인중.."
        // todo list
        /**
         *
         * 자동로그인 판별 후 로그인창, 메인 창
         * 세션으로 값 저장할건지?
         *
         * **/
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}