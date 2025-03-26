package com.example.api

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.api.ui.theme.APITheme
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

// Data Models
data class ProfileModel(
    var age: String,
    var name: String,
    var email: String
)

data class UserModel(
    var profile: ProfileModel
)

// API Interface
interface UserApi{
    @Headers("Accept: application/json")
    @GET("users/{id}")
    fun getUserById(@Path("id") id: String): Call<UserModel>
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            APITheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Simple API Request",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val id = remember { mutableStateOf(TextFieldValue()) }
                val profile = remember { mutableStateOf(ProfileModel("", "", "")) }

                Text(
                    text = "API Sample",
                    fontSize = 24.sp
                )

                Spacer(modifier = Modifier.height(15.dp))

                TextField(
                    label = { Text(text = "User ID") },
                    value = id.value,
                    onValueChange = { id.value = it }
                )

                Spacer(modifier = Modifier.height(15.dp))

                Button(
                    onClick = {
                        sendRequest(id.value.text, profile)
                    }
                ) {
                    Text(text = "Get Data")
                }

                Spacer(modifier = Modifier.height(15.dp))

                Text(text = "Age: ${profile.value.age}", fontSize = 20.sp)
                Text(text = "Name: ${profile.value.name}", fontSize = 20.sp)
                Text(text = "Email: ${profile.value.email}", fontSize = 20.sp)
            }
        }
    )
}

fun sendRequest(id: String, profileState: MutableState<ProfileModel>) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(UserApi::class.java)

    val call = api.getUserById(id)
    call.enqueue(object : Callback<UserModel> {
        override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
            if (response.isSuccessful) {
                response.body()?.let {
                    profileState.value = it.profile
                }
            } else {
                Log.e("Main", "Response error: ${response.errorBody()?.string()}")
            }
        }

        override fun onFailure(call: Call<UserModel>, t: Throwable) {
            Log.e("Main", "Failed: ${t.message}")
        }
    })
}
