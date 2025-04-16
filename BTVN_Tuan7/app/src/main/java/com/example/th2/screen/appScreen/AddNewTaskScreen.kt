package com.example.th2.screen.appScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.th2.TaskViewModel
import android.widget.Toast

@Composable
fun AddNewTaskScreen(
    navController: NavController,
    taskViewModel: TaskViewModel = viewModel()
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isTitleError by remember { mutableStateOf(false) }
    var isDescriptionError by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top Bar with back button
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
                Text(
                    text = "Add New Task",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2196F3)
                )
            }
        }

        // Task input field
        OutlinedTextField(
            value = title,
            onValueChange = { 
                title = it
                isTitleError = it.isBlank()
            },
            label = { Text("Task") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF2196F3),
                focusedLabelColor = Color(0xFF2196F3)
            ),
            isError = isTitleError,
            supportingText = {
                if (isTitleError) {
                    Text(
                        text = "Task title cannot be empty",
                        color = Color.Red
                    )
                }
            }
        )

        // Description input field
        OutlinedTextField(
            value = description,
            onValueChange = { 
                description = it
                isDescriptionError = it.isBlank()
            },
            label = { Text("Description") },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(vertical = 8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF2196F3),
                focusedLabelColor = Color(0xFF2196F3)
            ),
            isError = isDescriptionError,
            supportingText = {
                if (isDescriptionError) {
                    Text(
                        text = "Description cannot be empty",
                        color = Color.Red
                    )
                }
            }
        )

        // Add button
        Button(
            onClick = {
                isTitleError = title.isBlank()
                isDescriptionError = description.isBlank()
                
                if (!isTitleError && !isDescriptionError) {
                    taskViewModel.addTask(title, description)
                    Toast.makeText(context, "Task added successfully!", Toast.LENGTH_SHORT).show()
                    navController.navigateUp()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3)
            )
        ) {
            Text(
                text = "Add Task",
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
} 