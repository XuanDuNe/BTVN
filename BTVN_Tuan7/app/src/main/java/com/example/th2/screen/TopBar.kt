package com.example.th2.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.th2.R

@Composable
fun SmartTasksTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.White)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo_uth),
            contentDescription = "UTH Logo",
            modifier = Modifier
                .size(50.dp)
        )

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "SmartTasks",
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "A simple and efficient to-do app",
                color = Color.Gray,
                fontSize = 14.sp
            )
        }


        Box(
            modifier = Modifier
                .size(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bell),
                contentDescription = "Notifications",
                tint = Color(0xFFFFA500),

                modifier = Modifier.size(30.dp)

            )

            Box(
                modifier = Modifier
                    .size(12.dp)
                    .offset(x = 10.dp, y = (-10).dp)
                    .background(Color.Red, shape = CircleShape)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SmartTasksTopBarPreview() {
    SmartTasksTopBar()
}