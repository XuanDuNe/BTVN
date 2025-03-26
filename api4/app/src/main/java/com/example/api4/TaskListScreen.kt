package com.example.api4

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskListScreen(viewModel: TaskViewModel) {
    LaunchedEffect(Unit) { viewModel.fetchTasks() }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("SmartTasks", style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(10.dp))

        if (viewModel.tasks.value.isEmpty()) {
            EmptyTaskScreen()
        } else {
            viewModel.tasks.value.forEach { task ->
                TaskItem(task, onDelete = { viewModel.deleteTask(task.id) })
            }
        }
    }
}