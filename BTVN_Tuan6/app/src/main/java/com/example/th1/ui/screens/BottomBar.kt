package com.example.th1.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.th1.R

@Composable
fun BottomBar(navController: NavController) {
    val items = listOf(
        Screen.Home,
        Screen.Calendar,
        Screen.Notes,
        Screen.Settings
    )
    
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        
        items.forEach { screen ->
            NavigationBarItem(
                icon = { 
                    Icon(
                        imageVector = when (screen) {
                            Screen.Home -> Icons.Default.Home
                            Screen.Calendar -> Icons.Default.CalendarToday
                            Screen.Notes -> Icons.Default.Notes
                            Screen.Settings -> Icons.Default.Settings
                            else -> Icons.Default.Add
                        },
                        contentDescription = stringResource(screen.resourceId)
                    )
                },
                label = { Text(stringResource(screen.resourceId)) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

sealed class Screen(val route: String, val resourceId: Int) {
    object Home : Screen("app_screen_one", R.string.home)
    object Calendar : Screen("calendar_screen", R.string.calendar)
    object Notes : Screen("notes_screen", R.string.notes)
    object Settings : Screen("settings_screen", R.string.settings)
} 