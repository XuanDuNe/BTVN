package com.example.th1.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://your-api-url.com/"
    
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    
    val taskApiService: TaskApiService = retrofit.create(TaskApiService::class.java)
} 