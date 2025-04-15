package com.example.baithuchanh3_tuan3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.baithuchanh3_tuan3.ui.theme.Baithuchanh3_tuan3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Baithuchanh3_tuan3Theme {
                MainContent()
            }
        }
    }
}

@Composable
fun MainContent() {
    var currentPage by remember { mutableStateOf(1) }

    when (currentPage) {
        1 -> SplashScreen(onClick = { currentPage = 2 })
        2 -> OnboardingPage2(onNextClick = { currentPage = 3 })
        3 -> OnboardingPage3(
            onBackClick = { currentPage = 2 },
            onNextClick = { currentPage = 4 }
        )
        4 -> OnboardingPage4(onBackClick = { currentPage = 3 })
    }
}

@Composable
fun SplashScreen(onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.uth),
                contentDescription = "UTH Logo",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "UTH SmartTasks",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { onClick() },
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun OnboardingPage2(onNextClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.bro2),
                contentDescription = "Easy Time Management",
                modifier = Modifier.size(300.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Title
            Text(
                text = "Easy Time Management",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Description
            Text(
                text = "With management based on priority and daily tasks, it will give you convenience in managing and determining the tasks that must be done first.",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 18.dp)
            )
        }

        // Next Button
        Button(
            onClick = onNextClick,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)),
            modifier = Modifier
                .height(60.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Next", color = Color.White,
                fontSize = 32.sp,)
        }
    }
}

@Composable
fun OnboardingPage3(onBackClick: () -> Unit, onNextClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.bro1),
                contentDescription = "Increase Work Effectiveness",
                modifier = Modifier.size(300.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Title
            Text(
                text = "Increase Work Effectiveness",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Description
            Text(
                text = "Time management and the determination of more important tasks will give your job statistics better and always improve.",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 18.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            //Icon Button
            Button(
                onClick = onBackClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)),
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(0.2f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            // Next Button
            Button(
                onClick = onNextClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)),
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(0.9f)
            ) {
                Text(
                    text = "Next", color = Color.White,
                    fontSize = 32.sp,)
            }
        }
    }
}

@Composable
fun OnboardingPage4(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.bro),
                contentDescription = "Reminder Notification",
                modifier = Modifier.size(300.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Title
            Text(
                text = "Reminder Notification",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Description
            Text(
                text = "The advantage of this application is that it also provides reminders for you so you don't forget to keep doing your assignments well and according to the time you have set.",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 18.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Back Icon Button
            Button(
                onClick = onBackClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)),
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(0.2f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back),
                    contentDescription = "Back",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
            }


// Get Started Button
            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007BFF)),
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(0.9f)
            ) {
                Text(
                    text = "Get Started", color = Color.White,
                    fontSize = 32.sp,)
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewOnboardingPage4() {
    Baithuchanh3_tuan3Theme {
        OnboardingPage4(onBackClick = {  })
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingPage3() {
    Baithuchanh3_tuan3Theme {
        OnboardingPage3 (
            onNextClick = {  },
            onBackClick = {  })
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    Baithuchanh3_tuan3Theme {
        SplashScreen(onClick = {  })
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingPage2() {
    Baithuchanh3_tuan3Theme {
        OnboardingPage2(onNextClick = {  })
    }
}