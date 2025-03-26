package com.example.apisimple

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UserScreen()
        }
    }
}

private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://jsonplaceholder.typicode.com/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

private val api: UserApi = retrofit.create(UserApi::class.java)

private fun fetchUserData(id: Int, userState: MutableState<UserModel?>) {
    val call = api.getUserById(id)
    call.enqueue(object : Callback<UserModel> {
        override fun onResponse(call: Call<UserModel>, response: Response<UserModel?>) {
            if (response.isSuccessful) {
                userState.value = response.body()
            } else {
                userState.value = null
            }
        }

        override fun onFailure(call: Call<UserModel>, t: Throwable) {
            Log.e("API_ERROR", "Lỗi: ${t.message}")
        }
    })
}

@Preview
@Composable
fun UserScreen() {
    val userState = remember { mutableStateOf<UserModel?>(null) }
    var userId by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = userId,
            onValueChange = { userId = it },
            label = { Text("Nhập ID người dùng") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val id = userId.toIntOrNull()
                if (id != null) {
                    fetchUserData(id, userState)
                } else {
                    Log.e("INPUT_ERROR", "ID không hợp lệ")
                }
            }
        ) {
            Text("Tìm kiếm")
        }


        Spacer(modifier = Modifier.height(16.dp))

        userState.value?.let { user ->
            Text(text = "ID: ${user.id}", fontSize = 20.sp)
            Text(text = "Name: ${user.name}", fontSize = 20.sp)
            Text(text = "Email: ${user.email}", fontSize = 20.sp)
        } ?: Text("Không tìm thấy người dùng", fontSize = 16.sp)
    }
}

