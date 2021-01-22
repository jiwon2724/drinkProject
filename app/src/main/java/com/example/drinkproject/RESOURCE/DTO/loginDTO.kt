package com.example.drinkproject.RESOURCE.DTO

import com.google.gson.annotations.SerializedName

data class SignDTO(
    @SerializedName("resultNum") var resultNum : String?,
    @SerializedName("result") var result : Boolean?
)