package com.example.drinkproject.LOGIN

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.drinkproject.R
import com.example.drinkproject.RESOURCE.Constant
import com.example.drinkproject.RESOURCE.DTO.SignDTO
import com.example.drinkproject.RESOURCE.SERVICE.LoginService
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignUpActivity : AppCompatActivity() {

    val retrofit = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val server = retrofit.create(LoginService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        /** 회원가입 버튼 **/
        signUpButton.setOnClickListener { idCheck() }
    }

    fun idCheck(){
        when(signIdEditTextView.text.toString().isNotEmpty() && signPasswordEditText.text.toString().isNotEmpty()){
            true -> { signUpApiCall() }
            false -> {
                when(signIdEditTextView.text.toString().isNotEmpty()){
                    true -> { textviewChange(passwordCheckTextView, signPasswordEditText) }
                    false -> {textviewChange(idCheckTextView, signIdEditTextView)}
                }
            }
        }
    }

    fun signUpApiCall(){
        when(signPasswordEditText.text.toString() == signPasswordCheckEditTextView.text.toString()){
            true -> {
                val singUpInfo : HashMap<String, String> = HashMap()
                singUpInfo.put("userId", signIdEditTextView.text.toString())
                singUpInfo.put("userPass", signPasswordEditText.text.toString())

                server.signUp(singUpInfo).enqueue(object : Callback<SignDTO>{
                    override fun onResponse(call: Call<SignDTO>, response: Response<SignDTO>) {
                        when(response.body()!!.httpCode){
                            "200" -> {
                                Log.d("성공 : ", response.body().toString())
                                finish()
                            }
                            "400" -> { textviewChange(idCheckTextView, signIdEditTextView, "중복된 아이디 입니다.") }
                            else ->{ Log.d("else : ", response.body().toString()) }
                        }
                    }

                    override fun onFailure(call: Call<SignDTO>, t: Throwable) {
                        Log.d("실패 이유 : ", t.message.toString())
                    }
                })
            }
            false -> {textviewChange(passwordCheckWrongTextView, signPasswordCheckEditTextView)}
        }
    }

    fun textviewChange(textview : TextView, editTextView : EditText, str : String){
        textViewInVisible()
        editTextView.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.underlineRed))
        textview.isVisible = true
        textview.text = str
        editTextView.requestFocus()
    }

    fun textviewChange(textview : TextView, editTextView : EditText){
        textViewInVisible()
        textview.isVisible = true
        editTextView.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.underlineRed))
        editTextView.requestFocus()
    }

    fun textViewInVisible(){
        idCheckTextView.isVisible = false
        passwordCheckTextView.isVisible = false
        passwordCheckWrongTextView.isVisible = false
    }
}