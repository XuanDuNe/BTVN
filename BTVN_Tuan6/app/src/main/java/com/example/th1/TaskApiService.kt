package com.example.th1

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

interface TaskApiService {
    @GET("tasks")
    suspend fun getTasks(): Response<ApiResponse>

    @POST("tasks")
    suspend fun createTask(@Body task: Task): Response<ApiResponse>
}