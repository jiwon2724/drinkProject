package com.example.drinkproject.RESOURCE

class Constant {
    companion object {
        const val BASE_URL = "http://172.30.1.16:8080/"

        /** SharedPreferences **/
        const val autoLogin ="autoLogin" // 자동로그인
        const val userId = "userId" // 유저 아이디
        const val userPass ="userPass" // 유저 비밀번호


        /** HTTP API 리스트 **/
        const val HTTP_API_POST_SIGN_UP = "api/signUp" // 회원가입
        const val HTTP_API_POST_LOGIN = "api/signIn" // 로그인
    }
}