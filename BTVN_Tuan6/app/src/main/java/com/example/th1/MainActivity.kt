package com.example.th1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.compose.foundation.layout.WindowInsets
import com.example.th1.screen.BottomBar
import com.example.th1.screen.appScreen.AddNewTaskScreen
import com.example.th1.screen.appScreen.AppScreenOne
import com.example.th1.screen.appScreen.DetailScreen
import com.example.th1.screen.startScreen.OnboardingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
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
    val taskViewModel = remember { TaskViewModel() }
    
    // Kiểm tra hiển thị BottomBar dựa vào route hiện tại
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showBottomBar = currentRoute != "onboarding" 
                     && currentRoute != "detail_screen/{taskId}"
    
    // Sử dụng WindowInsets để loại bỏ hoặc giảm insets hệ thống
    Scaffold(
        // Giảm hoặc loại bỏ insets để TopBar sát với phần trên màn hình
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            if (showBottomBar) {
                BottomBar(navController = navController)
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AppNavHost(
                navController = navController,
                taskViewModel = taskViewModel
            )
        }
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    taskViewModel: TaskViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "onboarding"
    ) {
        // Màn hình Onboarding
        composable("onboarding") {
            OnboardingScreen(
                onFinish = {
                    navController.navigate("app_screen_one") {
                        popUpTo("onboarding") { inclusive = true }
                    }
                }
            )
        }
        
        // Màn hình chính hiển thị danh sách tasks (Home)
        composable("app_screen_one") {
            AppScreenOne(
                navController = navController,
                taskViewModel = taskViewModel,
                onTaskClick = { taskId ->
                    navController.navigate("detail_screen/$taskId")
                }
            )
        }
        
        // Màn hình chi tiết task
        composable(
            route = "detail_screen/{taskId}",
            arguments = listOf(navArgument("taskId") { type = NavType.IntType })
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt("taskId") ?: -1
            DetailScreen(
                navController = navController,
                taskId = taskId,
                taskViewModel = taskViewModel
            )
        }
        composable("add_new_task") {
            AddNewTaskScreen(
                navController = navController,
                onAddTask = { title, description ->
                    taskViewModel.addTask(title, description)
                    navController.navigateUp()
                }
            )
        }
        
        // Các màn hình khác (tạm thời hiển thị placeholders)
        composable("calendar_screen") {
            PlaceholderScreen("Calendar Screen")
        }
        
        composable("notes_screen") {
            PlaceholderScreen("Notes Screen")
        }
        
        composable("settings_screen") {
            PlaceholderScreen("Settings Screen")
        }

    }
}

// Màn hình placeholder tạm thời cho các tab chưa phát triển
@Composable
fun PlaceholderScreen(title: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$title\nComing Soon!",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
    }
}