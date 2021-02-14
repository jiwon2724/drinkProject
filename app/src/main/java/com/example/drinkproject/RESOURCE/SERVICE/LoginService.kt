package com.example.drinkproject.RESOURCE.SERVICE

import com.example.drinkproject.RESOURCE.Constant
import com.example.drinkproject.RESOURCE.DTO.SignDTO
import retrofit2.Call
import retrofit2.http.*

interface LoginService {
    @POST(Constant.HTTP_API_POST_SIGN_UP)
    fun signUp(@Body body : Map<String, String>) : Call<SignDTO>

    @POST(Constant.HTTP_API_POST_LOGIN)
    fun login(@Body body : Map<String, String>) : Call<SignDTO>
}