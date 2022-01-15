// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import network_work.data.Post
import network_work.data.network.ApiService
import network_work.data.repository.PostRepository
import network_work.data.ui.PostViewModel
import network_work.di.appModule
import network_work.utils.ApiResponse
import org.koin.core.context.startKoin
import org.koin.dsl.module

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
    val viewmodel = PostViewModel(PostRepository(ApiService()), scope)

    startKoin {
        modules(appModule)
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "Demo App"
    ) {


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
