// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import network_work.data.Post
import di.appModule
import utils.ApiResponse
import org.koin.core.context.startKoin
import weather_app.data.screens.Header

@Composable
@Preview
fun App() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = { Text("Email") },
                placeholder = { Text("enter your email") },
            )
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = { Text("Password") },
                placeholder = { Text("enter your password") },
            )

            OutlinedButton(onClick = {}) {
                Text("Log in")
            }
        }
    }
}

fun main() = application {
    val scope = rememberCoroutineScope()


    startKoin {
        modules(appModule)
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "Demo App",
        resizable = false
    ) {
        //  RestApi()
        Header()
    }
}

@Composable
@Preview
fun RestApi() {
    when (val res = BaseApplication().viewmodel.response.collectAsState(ApiResponse.Empty).value) {

        is ApiResponse.Success -> {
            LazyColumn {
                items(res.data) { post ->
                    EachRow(post)
                }
            }
        }
        is ApiResponse.Failure -> {
            Column(
                modifier = Modifier.fillMaxSize().padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "${res.msg}")
            }
        }
        ApiResponse.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize().padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
@Preview
fun EachRow(post: Post) {

    Card(
        modifier = Modifier.fillMaxWidth().padding(10.dp),
    ) {
        Text(text = post.body!!)
    }

}
