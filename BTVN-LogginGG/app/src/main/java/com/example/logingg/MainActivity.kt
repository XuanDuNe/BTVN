package com.example.logingg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.logingg.ui.theme.LoginGGTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginGGTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}
