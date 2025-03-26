package com.example.api4
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.gcm.Task
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TaskViewModel : ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://amock.io/api/researchUTH/") // Thay API URL thật của bạn
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(TaskApiService::class.java)

    var tasks = mutableStateOf<List<Task>>(emptyList())
        private set

    fun fetchTasks() {
        viewModelScope.launch {
            try {
                tasks.value = service.getTasks()
            } catch (e: Exception) {
                Log.e("API_ERROR", "Failed to fetch tasks", e)
            }
        }
    }

    fun deleteTask(id: Int) {
        viewModelScope.launch {
            try {
                service.deleteTask(id)
                fetchTasks()
            } catch (e: Exception) {
                Log.e("API_ERROR", "Failed to delete task", e)
            }
        }
    }
}

