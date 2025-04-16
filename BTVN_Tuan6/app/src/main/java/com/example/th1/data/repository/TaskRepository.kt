package com.example.th1.data.repository

import com.example.th1.data.api.TaskApiService
import com.example.th1.data.model.Task
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TaskRepository(private val apiService: TaskApiService) {
    
    fun getTasks(): Flow<List<Task>> = flow {
        try {
            val tasks = apiService.getTasks()
            emit(tasks)
        } catch (e: Exception) {
            // Handle error
            emit(emptyList())
        }
    }
    
    suspend fun addTask(title: String, description: String): Task {
        return apiService.addTask(Task(title = title, description = description))
    }
    
    suspend fun updateTask(task: Task) {
        apiService.updateTask(task.id, task)
    }
    
    suspend fun deleteTask(taskId: Int) {
        apiService.deleteTask(taskId)
    }
} 