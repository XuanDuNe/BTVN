package com.example.th2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.th2.data.TaskDatabase
import com.example.th2.data.TaskEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val database = TaskDatabase.getDatabase(application)
    private val taskDao = database.taskDao()

    private val _uiState = MutableStateFlow(TaskUiState(isLoading = true))
    val uiState: StateFlow<TaskUiState> = _uiState

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            taskDao.getAllTasks()
                .catch {
                    _uiState.value = TaskUiState(isError = true)
                }
                .collect { taskEntities ->
                    _uiState.value = TaskUiState(
                        tasks = taskEntities.map { it.toTask() },
                        isLoading = false
                    )
                }
        }
    }

    fun addTask(title: String, description: String) {
        viewModelScope.launch {
            val taskEntity = TaskEntity(
                title = title,
                description = description
            )
            taskDao.insertTask(taskEntity)
        }
    }
    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            val taskToDelete = TaskEntity(
                id = taskId,
                title = "",
                description = "",
                status = "",
                priority = "",
                category = "",
                dueDate = "",
                createdAt = "",
                updatedAt = ""
            )
            taskDao.deleteTask(taskToDelete)
        }
    }
    private fun TaskEntity.toTask(): Task {
        return Task(
            id = id,
            title = title,
            description = description,
            status = status,
            priority = priority,
            category = category,
            dueDate = dueDate,
            createdAt = createdAt,
            updatedAt = updatedAt,
            subtasks = null,
            attachments = null,
            reminders = null
        )
    }
} 