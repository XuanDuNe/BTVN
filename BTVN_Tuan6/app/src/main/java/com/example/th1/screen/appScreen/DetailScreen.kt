package com.example.th1.screen.appScreen

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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.th1.Attachment
import com.example.th1.Subtask
import com.example.th1.Task
import com.example.th1.TaskViewModel

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
    
    // Gọi selectTask khi vào màn hình
    LaunchedEffect(taskId) {
        taskViewModel.selectTask(taskId)
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
                    IconButton(onClick = { /* Xử lý xóa task */ }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            tint = Color(0xFFF57C00) // Màu cam như trong hình
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color(0xFF2196F3) // Màu xanh cho tiêu đề
                )
            )
        }
    ) { paddingValues ->
        if (task == null) {
            // Hiển thị nếu không tìm thấy task
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Task not found")
            }
        } else {
            // Hiển thị chi tiết task
            TaskDetailContent(
                task = task,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Color(0xFFF5F5F5)) // Background màu xám nhạt
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
        
        // Card thông tin (Category, Status, Priority)
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF8D7DA) // Màu hồng nhạt từ hình ảnh
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                // Category
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Category,
                        contentDescription = null,
                        tint = Color.Black
                    )
                    Text(
                        text = "Category",
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                    Text(
                        text = task.category,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                
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
                        text = formatDetailStatus(task.status),
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
        
        // Subtasks section
        if (!task.subtasks.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "Subtasks",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            task.subtasks.forEach { subtask ->
                SubtaskItem(subtask = subtask)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        
        // Attachments section
        if (!task.attachments.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(
                text = "Attachments",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            task.attachments.forEach { attachment ->
                AttachmentItem(attachment = attachment)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        
        // Bottom padding to ensure content is not cut off
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun SubtaskItem(subtask: Subtask) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE9ECEF) // Màu xám nhạt từ hình ảnh
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = subtask.isCompleted,
                onCheckedChange = null, // Read-only
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary,
                    uncheckedColor = MaterialTheme.colorScheme.outline
                )
            )
            
            Spacer(modifier = Modifier.width(8.dp))
            
            Text(
                text = subtask.title,
                fontSize = 14.sp,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun AttachmentItem(attachment: Attachment) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE9ECEF) // Màu xám nhạt từ hình ảnh
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Attachment, // Thay thế Icons.Default.Link
                contentDescription = null,
                tint = Color.DarkGray
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Text(
                text = attachment.fileName,
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }
}

// Đổi tên hàm để tránh xung đột với hàm trong AppScreenOne.kt
fun formatDetailStatus(status: String): String {
    return when {
        status.equals("completed", ignoreCase = true) -> "Completed"
        status.equals("in_progress", ignoreCase = true) -> "In Progress"
        else -> "Pending"
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
        subtasks = listOf(
            Subtask(
                id = 11,
                title = "This task is related to Fitness. It needs to be completed",
                isCompleted = false
            ),
            Subtask(
                id = 12,
                title = "This task is related to Fitness. It needs to be completed",
                isCompleted = false
            ),
            Subtask(
                id = 13,
                title = "This task is related to Fitness. It needs to be completed",
                isCompleted = false
            )
        ),
        attachments = listOf(
            Attachment(
                id = 100,
                fileName = "document_1_0.pdf",
                fileUrl = "https://example.com/document_1_0.pdf"
            )
        ),
        reminders = null
    )
    
    MaterialTheme {
        Surface {
            TaskDetailContent(task = previewTask)
        }
    }
}

