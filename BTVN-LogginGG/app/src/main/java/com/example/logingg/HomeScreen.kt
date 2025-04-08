package com.example.logingg

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.logingg.ui.theme.TopBar
import com.google.firebase.auth.FirebaseAuth

@Composable
fun HomeScreen(navController: NavController) {
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser

    Column(
        modifier = Modifier.fillMaxSize().padding(30.dp),
        verticalArrangement = Arrangement.Top
    ) {
        TopBar("Profile",navController)

        user?.let {
            // Hiển thị ảnh đại diện
            Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.Center) {
                Image(
                    painter = rememberAsyncImagePainter(it.photoUrl),
                    contentDescription = "Ảnh đại diện",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.LightGray, CircleShape)
                )
            }
            Spacer(modifier = Modifier.height(40.dp))
        Column {
                // Hiển thị tên và email
                Text("Name", fontWeight = FontWeight.Bold)
                Text(text = " ${it.displayName ?: "Không có"}",
                    modifier = Modifier
                        .border(1.dp, Color.Black) // Viền màu đen, độ dày 2dp
                        .padding(8.dp)
                        .fillMaxWidth(0.9f)) // Khoảng cách từ viền vào chữ)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Email", fontWeight = FontWeight.Bold)
                Text(text = " ${it.email ?: "Không có"}",
                    modifier = Modifier
                        .border(1.dp,Color.Black)
                        .padding(8.dp)
                        .fillMaxWidth(0.9f)
                )

            }
            Spacer(modifier = Modifier.height(150.dp))
        }
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center){
            // Nút đăng xuất
            Button(
                onClick = {
                    auth.signOut()
                    navController.navigate("login")   {
                        popUpTo("home") { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1976D2), // Xanh dương đậm (#1976D2)
                    contentColor = Color(0xFFFFFFFF)    // Màu trắng (#FFFFFF)
                ),
                modifier = Modifier.fillMaxWidth()

            ) {
                Text(text = "Back")
            }

        }
    }
}
