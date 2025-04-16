package com.example.th2.screen.startScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit = {}
) {
    var currentScreen by remember { mutableIntStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    // Tự động chuyển từ Screen1 sang Screen2 sau 3 giây
    LaunchedEffect(currentScreen) {
        if (currentScreen == 0) {
            delay(3000L)
            currentScreen = 1
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        when (currentScreen) {
            0 -> Screen1()
            1 -> Screen2(
                onNextClicked = { coroutineScope.launch { currentScreen = 2 } },
                onBackClicked = { coroutineScope.launch { currentScreen = 0 } }
            )
            2 -> Screen3(
                onNextClicked = { coroutineScope.launch { currentScreen = 3 } },
                onBackClicked = { coroutineScope.launch { currentScreen = 1 } }
            )
            3 -> Screen4(
                onNextClicked = { onFinish() },
                onBackClicked = { coroutineScope.launch { currentScreen = 2 } }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun OnboardingScreenPreview() {
    OnboardingScreen()
}