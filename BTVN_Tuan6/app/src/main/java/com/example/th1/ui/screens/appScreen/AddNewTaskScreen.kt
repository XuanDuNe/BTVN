package com.example.th1.ui.screens.appScreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.th1.R
import com.example.th1.viewmodel.TaskViewModel

@Composable
fun AddNewTaskScreen(
    navController: NavController,
    taskViewModel: TaskViewModel
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    
    Scaffold(

    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(stringResource(R.string.task_title)) },
                modifier = Modifier.fillMaxWidth()
            )
            
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text(stringResource(R.string.task_description)) },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            
            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        taskViewModel.addTask(title, description)
                        navController.navigateUp()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = title.isNotBlank()
            ) {
                Text(stringResource(R.string.add_task))
            }
        }
    }
} 