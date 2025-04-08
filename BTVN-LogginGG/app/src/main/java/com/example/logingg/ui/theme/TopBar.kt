package com.example.logingg.ui.theme



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
import com.example.logingg.R


@Composable
fun TopBar(title: String, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        // Icon back với hành động quay lại
        Image(
            painter = painterResource(id = R.drawable.icon_back),
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
            color = Color(0xFF1976D2), // Xanh dương đậm (#1976D2)
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

