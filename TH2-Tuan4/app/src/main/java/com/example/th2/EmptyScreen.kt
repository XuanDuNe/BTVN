package com.example.th2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.th2.ui.theme.TH2Theme
import com.example.th2.ui.theme.TopBar

@Composable
fun EmptyScreen(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.uth),
            contentDescription = "Logo",
            modifier = Modifier
                .size(70.dp) // Kích thước hợp lý
                .padding(8.dp), // Padding để tránh dính sát
            tint = Color.Unspecified // Giữ nguyên màu gốc
        )

        Spacer(modifier = Modifier.width(30.dp))
        Text(
            text = "Empty",
            fontSize = 33.sp,
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Card(
            modifier = Modifier
                .fillMaxWidth()





                .padding(top = 32.dp, bottom = 32.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        ) {
            Column(
                modifier = Modifier
                        .padding(24.dp)
                    .fillMaxHeight(0.4f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Hình ảnh empty state
                Image(
                    painter = painterResource(id = R.drawable.ic_empty),
                    contentDescription = "Empty State",
                    modifier = Modifier
                        .size(120.dp)
                        .padding(bottom = 16.dp)
                )

                // Tiêu đề
                Text(
                    text = "No Tasks Available",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Mô tả
                Text(
                    text = "It looks like you don't have any tasks yet. Start by adding a new task!",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenPreview() {
    TH2Theme {
        EmptyScreen(navController = rememberNavController())
    }
}