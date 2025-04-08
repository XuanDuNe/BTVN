package com.example.th2.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.th2.R

@Composable
fun TopBar(title: String, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .statusBarsPadding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Icon back với hành động quay lại
        Image(
            painter = painterResource(id = R.drawable.ic_back_th2),
            contentDescription = "Back Icon",
            modifier = Modifier
                .padding(start = 8.dp)
                .size(48.dp)
                .clickable {
                    navController.popBackStack() // Quay lại màn hình trước
                }
        )

        // Tiêu đề ở giữa với kích thước chữ tùy chỉnh
        Text(
            text = title,
            color = Color.Blue,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )

        // Không gian bên phải
        Spacer(modifier = Modifier.width(48.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TH2Theme {
        // Trong preview, NavController không cần thiết, nên dùng giả lập
        TopBar(title = "Detail", navController = rememberNavController())
    }
}