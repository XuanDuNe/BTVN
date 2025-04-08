package com.example.th2

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun ListScreen(navController: NavController, viewModel: TaskViewModel = viewModel()) {
    val uiState = viewModel.uiState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.uth),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(70.dp) // Kích thước hợp lý
                    .padding(8.dp), // Padding để tránh dính sát
                tint = Color.Unspecified // Giữ nguyên màu gốc
            )

            Spacer(modifier = Modifier.width(30.dp))
            Text(
                text = "SmartTasks",
                fontSize = 33.sp,
                color = Color.Blue,
                fontWeight = FontWeight.Bold
            )
        }

        when {
            uiState.isLoading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
            uiState.isError -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = uiState.errorMessage ?: "Error", color = Color.Red)
                    Button(onClick = { viewModel.refresh() }) {
                        Text("Retry")
                    }
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 16.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.tasks) { task ->
                        TaskItem(task = task, navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun TaskItem(task: Task, navController: NavController) {
    // Danh sách màu ngẫu nhiên
    val colors = listOf(
        Color(0xFFFFCDD2), // Đỏ nhạt
        Color(0xFFC8E6C9), // Xanh lá nhạt
        Color(0xFFBBDEFB), // Xanh dương nhạt
        Color(0xFFFFF9C4), // Vàng nhạt
        Color(0xFFD1C4E9)  // Tím nhạt
    )

    val backgroundColor = colors.random() // Chọn màu ngẫu nhiên

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("detail/${task.id}") },
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = task.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = task.description, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Status: ${task.status}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
