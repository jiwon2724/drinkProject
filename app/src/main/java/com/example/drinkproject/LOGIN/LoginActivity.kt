package com.example.drinkproject.LOGIN

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.drinkproject.DRINK.DrinkMainActivity
import com.example.drinkproject.R
import com.example.drinkproject.RESOURCE.Constant
import com.example.drinkproject.RESOURCE.DTO.SignDTO
import com.example.drinkproject.RESOURCE.SERVICE.LoginService
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    val retrofit = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val server = retrofit.create(LoginService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /** 회원가입으로 이동 **/
        signupTextView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            // todo list
            /**
             *
             * 정규식 처리
             * 일치하지 않았을 때 분기처리
             *
             *
             * **/

            loginApiCall()
        }
    }
    fun loginApiCall(){
        val loginUserInfo : HashMap<String, String> = HashMap()
        loginUserInfo.put("userId", idEditTextView.text.toString())
        loginUserInfo.put("userPass", passwordEditTextView.text.toString())
        server.login(loginUserInfo)
            .enqueue(object : Callback<SignDTO>{
                override fun onResponse(call: Call<SignDTO>, response: Response<SignDTO>) {
                    when(response.body()!!.resultNum){
                        "200" -> {
                            val intent = Intent(this@LoginActivity, DrinkMainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                        "400" -> { Log.d("정보 다름", "정보 다름") }
                    }
                }

                override fun onFailure(call: Call<SignDTO>, t: Throwable) {
                    Log.d("실패 : ", t.message.toString())
                }
            })
    }
}