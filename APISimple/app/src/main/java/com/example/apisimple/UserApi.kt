package com.example.apisimple

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {
    @GET("users/{id}")
    fun getUserById(@Path("id") id: Int): Call<UserModel>
}
