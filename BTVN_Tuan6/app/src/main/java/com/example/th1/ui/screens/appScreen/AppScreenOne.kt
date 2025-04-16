package com.example.th1.ui.screens.appScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.th1.R
import com.example.th1.data.model.Task
import com.example.th1.viewmodel.TaskViewModel

@Composable
fun AppScreenOne(
    navController: NavController,
    taskViewModel: TaskViewModel,
    onTaskClick: (Int) -> Unit
) {
    val tasks by taskViewModel.tasks.collectAsState()
    
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_new_task") }
            ) {
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add_task))
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (tasks.isEmpty()) {
                Text(
                    text = stringResource(R.string.no_tasks),
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(tasks) { task ->
                        TaskItem(
                            task = task,
                            onTaskClick = { onTaskClick(task.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TaskItem(
    task: Task,
    onTaskClick: () -> Unit
) {
    Card(
        onClick = onTaskClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = task.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
} 