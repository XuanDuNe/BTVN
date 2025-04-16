package com.example.th2.screen.appScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.th2.Task
import com.example.th2.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navController: NavController,
    taskId: Int,
    taskViewModel: TaskViewModel = viewModel()
) {
    val uiState by taskViewModel.uiState.collectAsState()
    
    // Tìm task dựa vào ID
    val task = remember(uiState.tasks, taskId) {
        uiState.tasks.find { it.id == taskId }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { 
                        task?.let { taskViewModel.deleteTask(it.id) }
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color(0xFFF57C00)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color(0xFF2196F3)
                )
            )
        }
    ) { paddingValues ->
        if (task == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Task not found")
            }
        } else {
            TaskDetailContent(
                task = task,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color(0xFFF5F5F5))
            )
        }
    }
}

@Composable
fun TaskDetailContent(
    task: Task,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Tiêu đề task
        Text(
            text = task.title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        
        // Mô tả task
        Text(
            text = task.description,
            fontSize = 16.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(top = 8.dp)
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Card thông tin (Status, Priority)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF8D7DA)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                // Status
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = Color.Black
                    )
                    Text(
                        text = "Status",
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                    Text(
                        text = task.status,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                // Priority
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color.Black
                    )
                    Text(
                        text = "Priority",
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                    Text(
                        text = task.priority,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        
        // Bottom padding to ensure content is not cut off
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun DetailScreenPreview() {
    val previewTask = Task(
        id = 1,
        title = "Complete Android Project",
        description = "Finish the UI, integrate API, and write documentation",
        status = "IN_PROGRESS",
        priority = "HIGH",
        category = "WORK",
        dueDate = "2024-03-26T14:00:00",
        createdAt = "2024-03-23T08:00:00",
        updatedAt = "2024-03-23T08:00:00",
        subtasks = null,
        attachments = null,
        reminders = null
    )
    
    MaterialTheme {
        Surface {
            TaskDetailContent(task = previewTask)
        }
    }
}

