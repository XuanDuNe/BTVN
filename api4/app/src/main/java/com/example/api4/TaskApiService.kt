package com.example.api4

import com.google.android.gms.gcm.Task
import retrofit2.http.GET
import retrofit2.http.DELETE
import retrofit2.http.Path

interface TaskApiService {
    @GET("tasks")
    suspend fun getTasks(): List<Task>

    @DELETE("task/{id}")
    suspend fun deleteTask(@Path("id") id: Int)
}