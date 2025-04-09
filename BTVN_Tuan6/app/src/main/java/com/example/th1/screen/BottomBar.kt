package com.example.th1.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.th1.R

@Composable
fun BottomBar(navController: NavController) {
    // Lấy route hiện tại để xác định mục nào đang được chọn
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.fillMaxWidth()
    ) {
        // FAB - Đảm bảo nó được đặt ở trên NavigationBar bằng cách tăng zIndex
        FloatingActionButton(
            onClick = { navController.navigate("add_new_task") },
            containerColor = Color(0xFF2196F3),
            contentColor = Color.White,
            shape = CircleShape,
            modifier = Modifier
                .size(56.dp)
                .offset(y = (-28).dp) // Đẩy FAB lên trên, so với NavigationBar
                .zIndex(1f) // Đảm bảo FAB hiển thị phía trên NavigationBar
                .align(Alignment.Center)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
        
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .zIndex(0f), // Đảm bảo NavigationBar ở dưới FAB
            containerColor = Color.White,
            contentColor = Color.Gray
        ) {
            // Home icon
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_home),
                        contentDescription = "Home",
                        tint = if (currentRoute == "app_screen_one") Color(0xFF2196F3) else Color.Gray,
                        modifier = Modifier.size(26.dp)
                    )
                },
                selected = currentRoute == "app_screen_one",
                onClick = {
                    if (currentRoute != "app_screen_one") {
                        navController.navigate("app_screen_one") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White
                )
            )
            
            // Calendar icon
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calendar),
                        contentDescription = "Calendar",
                        tint = if (currentRoute == "calendar_screen") Color(0xFF2196F3) else Color.Gray,
                        modifier = Modifier.size(26.dp)
                    )
                },
                selected = currentRoute == "calendar_screen",
                onClick = {
                    if (currentRoute != "calendar_screen") {
                        navController.navigate("calendar_screen") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White
                )
            )
            
            // Placeholder cho FAB - để trống ở giữa
            NavigationBarItem(
                icon = { Box(modifier = Modifier.size(26.dp)) },
                selected = false,
                onClick = { },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White,
                    unselectedIconColor = Color.Transparent
                )
            )
            
            // Notes/Document icon
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_docs),
                        contentDescription = "Notes",
                        tint = if (currentRoute == "notes_screen") Color(0xFF2196F3) else Color.Gray,
                        modifier = Modifier.size(26.dp)
                    )
                },
                selected = currentRoute == "notes_screen",
                onClick = {
                    if (currentRoute != "notes_screen") {
                        navController.navigate("notes_screen") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White
                )
            )
            
            // Settings icon
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_setting),
                        contentDescription = "Settings",
                        tint = if (currentRoute == "settings_screen") Color(0xFF2196F3) else Color.Gray,
                        modifier = Modifier.size(26.dp)
                    )
                },
                selected = currentRoute == "settings_screen",
                onClick = {
                    if (currentRoute != "settings_screen") {
                        navController.navigate("settings_screen") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White
                )
            )
        }
    }
}

