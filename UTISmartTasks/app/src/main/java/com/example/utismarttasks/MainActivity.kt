// MainActivity.kt
package com.example.utismarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.utismarttasks.ui.theme.UTISmartTasksTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UTISmartTasksTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TaskApp()
                }
            }
        }
    }
}

@Composable
fun TaskApp() {
    val navController = rememberNavController()
    val viewModel: TaskViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "taskList"
    ) {
        composable("taskList") {
            TaskListScreen(
                viewModel = viewModel,
                onTaskSelected = { task ->
                    navController.navigate("taskDetail/${task.id}")
                },
                onAddTask = {
                    navController.navigate("addTask")
                }
            )
        }
        composable("taskDetail/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
            val task = viewModel.tasks.firstOrNull { it.id == taskId } ?: viewModel.selectedTask

            if (task != null) {
                TaskDetailScreen(
                    task = task,
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() },
                    onEdit = {
                        navController.navigate("editTask/${task.id}")
                    }
                )
            } else {
                ErrorScreen(message = "Task not found", onBack = { navController.popBackStack() })
            }
        }
        composable("addTask") {
            AddEditTaskScreen(
                viewModel = viewModel,
                onSave = { navController.popBackStack() },
                onCancel = { navController.popBackStack() }
            )
        }
        composable("editTask/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
            val task = viewModel.tasks.firstOrNull { it.id == taskId } ?: viewModel.selectedTask

            if (task != null) {
                AddEditTaskScreen(
                    task = task,
                    viewModel = viewModel,
                    onSave = { navController.popBackStack() },
                    onCancel = { navController.popBackStack() }
                )
            } else {
                ErrorScreen(message = "Task not found", onBack = { navController.popBackStack() })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    viewModel: TaskViewModel,
    onTaskSelected: (Task) -> Unit,
    onAddTask: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("UTI SmartTasks") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddTask,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { padding ->
        when {
            viewModel.isLoading -> LoadingScreen()
            viewModel.errorMessage != null -> ErrorScreen(
                message = viewModel.errorMessage!!,
                onRetry = { viewModel.loadTasks() }
            )
            viewModel.tasks.isEmpty() -> EmptyTaskList()
            else -> TaskList(
                tasks = viewModel.tasks,
                onTaskSelected = onTaskSelected,
                modifier = Modifier.padding(padding)
            )
        }
    }
}

@Composable
fun TaskList(
    tasks: List<Task>,
    onTaskSelected: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(tasks) { task ->
            TaskItem(task = task, onClick = { onTaskSelected(task) })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskItem(task: Task, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f)
                )
                StatusChip(status = task.status)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = task.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = task.category, style = MaterialTheme.typography.labelSmall)
                Text(text = task.dueDate, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    task: Task,
    viewModel: TaskViewModel,
    onBack: () -> Unit,
    onEdit: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Task Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = onEdit) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.deleteTask(task.id) },
                containerColor = MaterialTheme.colorScheme.error
            ) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = task.title, style = MaterialTheme.typography.headlineMedium)

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = task.description, style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(16.dp))

                InfoRow("Category", task.category)
                InfoRow("Status", task.status)
                InfoRow("Priority", task.priority)
                InfoRow("Due Date", task.dueDate)

                Spacer(modifier = Modifier.height(24.dp))

                Text("Subtasks", style = MaterialTheme.typography.titleLarge)

                Spacer(modifier = Modifier.height(8.dp))

                task.subtasks.forEach { subtask ->
                    SubtaskItem(subtask = subtask)
                }

                if (task.attachments.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text("Attachments", style = MaterialTheme.typography.titleLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    task.attachments.forEach { attachment ->
                        AttachmentItem(attachment = attachment)
                    }
                }
            }
        }
    }
}

// Các composable phụ trợ (LoadingScreen, ErrorScreen, EmptyTaskList, v.v.) giữ nguyên như trước