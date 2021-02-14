package com.example.drinkproject.RESOURCE.DTO

import com.google.gson.annotations.SerializedName

data class SignDTO(
    @SerializedName("httpCode") var httpCode : String?,
    @SerializedName("userId") var userId : String?,
    @SerializedName("userPass") var userPass : String?
)