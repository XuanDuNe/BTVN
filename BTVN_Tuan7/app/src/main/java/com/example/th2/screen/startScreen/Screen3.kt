package com.example.th2.screen.startScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.layout.ContentScale
import com.example.th2.R

@Composable
fun Screen3(onNextClicked: () -> Unit, onBackClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Skip",
                fontSize = 16.sp,
                color = Color.Blue,
                modifier = Modifier
//                    .clickable { onSkipClicked() } // Xử lý sự kiện nhấn vào Skip

            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_screen2),
            contentDescription = "Easy Time Management",
            modifier = Modifier
                .size(350.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Fit // Giữ tỷ lệ ảnh
        )

        // Nút Back và Next
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = onBackClicked,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Back",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Button(
                onClick = onNextClicked,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Next",
                    color = Color.White,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}