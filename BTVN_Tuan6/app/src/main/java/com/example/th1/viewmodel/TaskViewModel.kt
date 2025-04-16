package com.example.th1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.th1.data.model.Task
import com.example.th1.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {
    
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()
    
    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask: StateFlow<Task?> = _selectedTask.asStateFlow()
    
    init {
        loadTasks()
    }
    
    private fun loadTasks() {
        viewModelScope.launch {
            repository.getTasks().collect { taskList ->
                _tasks.value = taskList
            }
        }
    }
    
    fun addTask(title: String, description: String) {
        viewModelScope.launch {
            repository.addTask(title, description)
            loadTasks()
        }
    }
    
    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
            loadTasks()
        }
    }
    
    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            repository.deleteTask(taskId)
            loadTasks()
        }
    }
    
    fun selectTask(task: Task) {
        _selectedTask.value = task
    }
    
    fun clearSelectedTask() {
        _selectedTask.value = null
    }
} 