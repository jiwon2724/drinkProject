package com.example.drinkproject.LOGIN

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.drinkproject.DRINK.DrinkMainActivity
import com.example.drinkproject.R
import com.example.drinkproject.RESOURCE.App
import com.example.drinkproject.RESOURCE.Constant
import com.example.drinkproject.RESOURCE.DTO.SignDTO
import com.example.drinkproject.RESOURCE.SERVICE.LoginService
import com.example.drinkproject.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    val retrofit = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val server = retrofit.create(LoginService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** 회원가입으로 이동 **/
        binding.signupTextView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener {
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
                    when(response.body()!!.httpCode){
                        "200" -> {
                            App.prefs.putStringData(Constant.userId, idEditTextView.text.toString())
                            App.prefs.putStringData(Constant.userPass, passwordEditTextView.text.toString())
                            App.prefs.putBooleanData(Constant.autoLogin, true)
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