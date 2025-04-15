package com.example.th1


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun DetailScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBar(title = "Detail", navController = navController)
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {

                }


                Text(
                    text = "\"The only way to do great work is to love what you do\"",
                    modifier = Modifier.padding(vertical = 16.dp),
                    textAlign = TextAlign.Center
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF90CAF9))
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "\"The only way to do great work is to love what you do.\"",
                        fontFamily = FontFamily.Monospace,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                }

                Text(
                    text = "Steve Jobs",
                    modifier = Modifier.padding(top = 16.dp),
                    color = Color.Gray
                )

                Text(
                    text = "http://quotes.thisgrandpablogs.com/",
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = Color(0xFF2196F3)
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = { navController.navigate("start") },
                    modifier = Modifier
                        .width(315.dp)
                        .height(70.dp)
                        .padding(vertical = 8.dp)
                ) {
                    Text("BACK TO ROOT")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(rememberNavController())
}

