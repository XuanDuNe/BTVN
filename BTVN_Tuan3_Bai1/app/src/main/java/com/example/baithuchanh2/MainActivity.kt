package com.example.baithuchanh2
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baithuchanh2.ui.theme.Baithuchanh2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Baithuchanh2Theme {
                AppNavigator()
            }
        }
    }
}

@Composable
fun AppNavigator() {
    var currentScreen by remember { mutableStateOf("welcome") }
    var selectedComponent by remember { mutableStateOf("") }

    when (currentScreen) {
        "welcome" -> WelcomeScreen(onReadyClick = { currentScreen = "uiList" })
        "uiList" -> UIComponentsListScreen(onItemClick = { component ->
            selectedComponent = component
            if (component == "Text" || component == "Image") {
                currentScreen = "textDetail"
            }
        })
        "textDetail" -> TextDetailScreen(
            component = selectedComponent,
            onBackClick = { currentScreen = "uiList" }
        )
    }
}




@Composable
fun WelcomeScreen(onReadyClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.jetpack_logo),
            contentDescription = "Jetpack Compose Logo",
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Jetpack Compose", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(60.dp))
        Button(
            onClick = onReadyClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196f3), // Màu nền của Button
                contentColor = Color.White // Màu chữ
            )
        ) {
            Text(text = "I'm ready")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWelcomeScreen() {
    WelcomeScreen(onReadyClick = {})
}

// Trang 2
@Composable
fun UIComponentsListScreen(onItemClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            text = "UI Components List",
            fontSize = 24.sp,
            color = Color(0xFF2196F3), // Màu xanh dương
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )


        // Display
        Text(
            text = "Display",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        )

        val displayComponents = listOf(
            "Text" to "Displays text",
            "Image" to "Displays an image"
        )

        displayComponents.forEach { (component, description) ->
            ComponentButton(component, description, onItemClick)
        }

        // Input
        Text(
            text = "Input",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        )

        val inputComponents = listOf(
            "TextField" to "Input field for text",
            "PasswordField" to "Input field for passwords"
        )

        inputComponents.forEach { (component, description) ->
            ComponentButton(component, description, onItemClick)
        }

        // Layout
        Text(
            text = "Layout",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
        )

        val layoutComponents = listOf(
            "Column" to "Arranges elements vertically",
            "Row" to "Arranges elements horizontally"
        )

        layoutComponents.forEach { (component, description) ->
            ComponentButton(component, description, onItemClick)
        }
    }
}

@Composable
fun ComponentButton(component: String, description: String, onItemClick: (String) -> Unit) {
    Button(
        onClick = { onItemClick(component) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF2196f3).copy(alpha = 0.3f), // 50% trong suốt // Màu nền của Button
            contentColor = Color.Black // Màu chữ,
        ),
        shape = RoundedCornerShape(16.dp) // Bo góc 16dp
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = component, fontWeight = FontWeight.Bold)
            Text(text = description, fontSize = 12.sp)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewUIComponentsListScreen() {
    UIComponentsListScreen(onItemClick = {})
}


@Composable
fun TextDetailScreen(component: String, onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_back),
                    contentDescription = "Back"
                )
            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Text Detail",
                    fontSize = 26.sp,
                    color = Color(0xFF2196F3), // Màu xanh dương
                    fontWeight = FontWeight.Bold,

                )
            }

            Spacer(modifier = Modifier.width(48.dp))
        }


        if (component == "Text") {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = 29.sp, fontWeight = FontWeight.Bold)) {
                            append("The ")
                        }
                        withStyle(style = SpanStyle(fontSize = 29.sp, textDecoration = TextDecoration.LineThrough)) {
                            append("quick ")
                        }
                        withStyle(style = SpanStyle(fontSize = 29.sp, fontWeight = FontWeight.Bold, color = Color(0xFFD2691E))) {
                            append("Brown ")
                        }
                        append("\n")
                        withStyle(style = SpanStyle(fontSize = 29.sp)) {
                            append("fox ")
                        }
                        withStyle(style = SpanStyle(fontSize = 29.sp)) {
                            append("j ")
                        }
                        withStyle(style = SpanStyle(fontSize = 29.sp, fontWeight = FontWeight.Bold)) {
                            append("u ")
                        }
                        withStyle(style = SpanStyle(fontSize = 29.sp)) {
                            append("m p s ")
                        }
                        withStyle(style = SpanStyle(fontSize = 29.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)) {
                            append("over ")
                        }
                        append("\n")
                        withStyle(style = SpanStyle(fontSize = 29.sp)) {
                            append("the ")
                        }
                        withStyle(style = SpanStyle(fontSize = 29.sp, fontStyle = FontStyle.Italic, textDecoration = TextDecoration.Underline)) {
                            append("lazy ")
                        }
                        withStyle(style = SpanStyle(fontSize = 29.sp)) {
                            append("dog.")
                        }
                    },
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else if (component == "Image") {
            Box(
                modifier = Modifier
                    .fillMaxSize(), // Chiếm toàn bộ kích thước màn hình
                contentAlignment = Alignment.Center // Căn giữa hình ảnh
            ) {
                Image(
                    painter = painterResource(id = R.drawable.jetpack_logo),
                    contentDescription = "Jetpack Compose Logo",
                    modifier = Modifier.size(300.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTextDetailScreen() {
    TextDetailScreen(
        component = "Text",
        onBackClick = {}
    )
}