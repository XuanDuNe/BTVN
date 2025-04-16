package com.example.th1.ui.screens.appScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.th1.R
import com.example.th1.data.model.Task
import com.example.th1.viewmodel.TaskViewModel

@Composable
fun DetailScreen(
    navController: NavController,
    taskId: Int,
    taskViewModel: TaskViewModel
) {
    val tasks by taskViewModel.tasks.collectAsState()
    val task = tasks.find { it.id == taskId }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.task_details)) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.back))
                    }
                }
            )
        }
    ) { paddingValues ->
        if (task != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.headlineMedium
                )
                
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.bodyLarge
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = {
                            taskViewModel.deleteTask(task.id)
                            navController.navigateUp()
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(stringResource(R.string.delete))
                    }
                    
                    Button(
                        onClick = {
                            taskViewModel.updateTask(task.copy(isCompleted = !task.isCompleted))
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(if (task.isCompleted) stringResource(R.string.mark_incomplete) else stringResource(R.string.mark_complete))
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text(stringResource(R.string.task_not_found))
            }
        }
    }
} 