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
import com.example.drinkproject.databinding.ActivitySignUpBinding
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignUpActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignUpBinding

    val retrofit = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val server = retrofit.create(LoginService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** 회원가입 버튼 **/
        binding.signUpButton.setOnClickListener { idCheck() }
    }

    fun idCheck(){
        when(binding.signIdEditTextView.text.toString().isNotEmpty() && binding.signPasswordEditText.text.toString().isNotEmpty()){
            true -> { signUpApiCall() }
            false -> {
                when(binding.signIdEditTextView.text.toString().isNotEmpty()){
                    true -> { textviewChange(binding.passwordCheckTextView, binding.signPasswordEditText) }
                    false -> {textviewChange(binding.idCheckTextView, binding.signIdEditTextView)}
                }
            }
        }
    }

    fun signUpApiCall(){
        when(binding.signPasswordEditText.text.toString() == binding.signPasswordCheckEditTextView.text.toString()){
            true -> {
                val singUpInfo : HashMap<String, String> = HashMap()
                singUpInfo.put("userId", binding.signIdEditTextView.text.toString())
                singUpInfo.put("userPass", binding.signPasswordEditText.text.toString())

                server.signUp(singUpInfo).enqueue(object : Callback<SignDTO>{
                    override fun onResponse(call: Call<SignDTO>, response: Response<SignDTO>) {
                        when(response.body()!!.httpCode){
                            "200" -> {
                                Log.d("성공 : ", response.body().toString())
                                finish()
                            }
                            "400" -> { textviewChange(binding.idCheckTextView, binding.signIdEditTextView, "중복된 아이디 입니다.") }
                            else ->{ Log.d("else : ", response.body().toString()) }
                        }
                    }

                    override fun onFailure(call: Call<SignDTO>, t: Throwable) {
                        Log.d("실패 이유 : ", t.message.toString())
                    }
                })
            }
            false -> {textviewChange(binding.passwordCheckWrongTextView, binding.signPasswordCheckEditTextView)}
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
        binding.idCheckTextView.isVisible = false
        binding.passwordCheckTextView.isVisible = false
        binding.passwordCheckWrongTextView.isVisible = false
    }
}