package com.example.logingg

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.logingg.ui.theme.TopBar
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val googleSignInHelper = remember { GoogleSignInHelper(context) }
    val auth = FirebaseAuth.getInstance()

    // Nếu đã đăng nhập, chuyển sang HomeScreen
    val currentUser = auth.currentUser

    // Launcher xử lý kết quả đăng nhập
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            googleSignInHelper.handleSignInResult(
                data = result.data,
                onSuccess = {
                    Log.d("LoginScreen", "Đăng nhập thành công: $it")
                    navController.navigate("home")
                },
                onError = {
                    Log.e("LoginScreen", "Đăng nhập thất bại: $it")
                }
            )
        }
    }

    Column(
        modifier = Modifier.fillMaxSize() .padding(15.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.size(312.dp, 252.dp)
                .padding(16.dp)
                .background(
                    Color(0xFFD9ECF7),
                    shape = RoundedCornerShape(16.dp)
                ) // Tạo nền xanh nhạt
                .clip(
                    RoundedCornerShape(10.dp) // Bo góc ảnh,
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.uth_removebg_preview),
                contentDescription = "Ảnh UTH",
                contentScale = ContentScale.Fit, // Để ảnh không bị cắt
                modifier = Modifier
                    .size(192.dp, 132.dp)
                    .padding(8.dp) // Cách lề để không dính vào viền
            )
        }
        Text(
            "SmartTasks",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color(0xFF1976D2),
            modifier = Modifier
                .padding(5.dp),
        )
        Text(
            "A simple and  efficient to do app",
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Color(0xFF1976D2),
        )
        Spacer(modifier = Modifier.height(80.dp))
        Text("Welcome",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.Bold)
        Text("Ready to explore?Log in to get started.",
            fontSize = 12.sp)
        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .size(350.dp, 67.dp) // Điều chỉnh kích thước phù hợp
                .background(
                    Color(0xFFD9ECF7),
                    shape = RoundedCornerShape(12.dp)
                ) // Nền xanh nhạt + bo góc
                .clip(RoundedCornerShape(12.dp)) // Cắt theo góc bo
                .clickable {
                    val signInIntent = googleSignInHelper.getSignInIntent()
                    launcher.launch(signInIntent)
                },
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.images_removebg_preview), // Thay bằng icon Google
                    contentDescription = "Google Logo",
                    modifier = Modifier.size(24.dp) // Kích thước logo
                )
                Spacer(modifier = Modifier.width(8.dp)) // Khoảng cách giữa logo và chữ
                Text(
                    text = "SIGN IN WITH GOOGLE",
                    fontSize = 14.sp, // Kích thước chữ
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A237E) // Màu xanh đậm
                )
            }
        }
    }
}
