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
        .baseUrl("http://172.16.22.59:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val server = retrofit.create(LoginService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        /** 회원가입 버튼 **/
        signUpButton.setOnClickListener { signUpCheck() }
    }

    fun signUpCheck(){
        val reg = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        when(reg.matches(emailEditTextView.text.toString())){
            true -> { idCheck() }
            false -> {
                when(emailEditTextView.text.toString().isNotEmpty()){
                    true -> {textviewChange(emailCheckTextView, emailEditTextView, "이메일 형식이 다릅니다.")}
                    false -> {textviewChange(emailCheckTextView, emailEditTextView, "이메일을 입력해 주세요.")}
                }

            }
        }
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
                singUpInfo.put("userEmail", emailEditTextView.text.toString())

                server.signUp(singUpInfo).enqueue(object : Callback<SignDTO>{
                    override fun onResponse(call: Call<SignDTO>, response: Response<SignDTO>) {
                        when(response.body()!!.resultNum){
                            "200" -> {
                                // 성공일경우
                                Log.d("성공 : ", response.body().toString())
                                finish()
                            }
                            "401" -> { textviewChange(emailCheckTextView, emailEditTextView, "중복된 이메일 입니다.") }
                            "400" -> { textviewChange(idCheckTextView, signIdEditTextView, "중복된 아이디 입니다.") }
                            else ->{
                                Log.d("else : ", response.body().toString())
                                // 일단 나오는거 분기
                            }
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
        emailCheckTextView.isVisible = false
        idCheckTextView.isVisible = false
        passwordCheckTextView.isVisible = false
        passwordCheckWrongTextView.isVisible = false
    }
}