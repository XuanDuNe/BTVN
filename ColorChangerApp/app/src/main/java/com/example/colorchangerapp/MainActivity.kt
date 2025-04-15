package com.example.colorchangerapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.edit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Lấy màu lưu từ SharedPreferences
        val sharedPref = getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)
        val savedColor = sharedPref.getInt("bg_color", Color.White.hashCode())
        val initialColor = Color(savedColor)

        setContent {
            // Truyền đúng tham số vào ThemeSettingScreen
            ThemeSettingScreen(
                initialColor = initialColor,
                onColorApplied = { selectedColor ->
                    sharedPref.edit {
                        putInt("bg_color", selectedColor.hashCode())
                    }
                }
            )
        }
    }
}

@Composable
fun ThemeSettingScreen(
    initialColor: Color,
    onColorApplied: (Color) -> Unit
) {
    val themeColors = listOf(
        Color.White,
        Color(0xFF121212),
        Color(0xFFE91E63),
        Color(0xFF2196F3)
    )

    var selectedColor by remember { mutableStateOf(initialColor) }
    var appliedColor by remember { mutableStateOf(initialColor) }

    val animatedBgColor by animateColorAsState(targetValue = appliedColor)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(animatedBgColor)
            .padding(32.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "Setting",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = if (appliedColor == Color.White) Color.Black else Color.White
            )
            Text(
                text = "Choosing the right theme sets the tone and personality of your app",
                fontSize = 14.sp,
                color = if (appliedColor == Color.White) Color.DarkGray else Color.LightGray,
                modifier = Modifier.padding(horizontal = 16.dp),
            )

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                themeColors.forEach { color ->
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(color)
                            .border(
                                width = if (selectedColor == color) 3.dp else 1.dp,
                                color = if (selectedColor == color) Color.Black else Color.Gray,
                                shape = CircleShape
                            )
                            .clickable {
                                selectedColor = color
                            }
                    )
                }
            }

            Button(
                onClick = {
                    appliedColor = selectedColor
                    onColorApplied(appliedColor) // lưu lại
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
            ) {
                Text(text = "Apply")
            }
        }
    }
}
