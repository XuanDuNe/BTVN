package com.example.th1.data.api

import com.example.th1.data.model.Task
import retrofit2.http.*

interface TaskApiService {
    @GET("tasks")
    suspend fun getTasks(): List<Task>
    
    @POST("tasks")
    suspend fun addTask(@Body task: Task): Task
    
    @PUT("tasks/{id}")
    suspend fun updateTask(@Path("id") id: Int, @Body task: Task)
    
    @DELETE("tasks/{id}")
    suspend fun deleteTask(@Path("id") id: Int)
} 