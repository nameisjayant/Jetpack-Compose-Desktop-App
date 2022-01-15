package network_work.data.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import network_work.data.repository.PostRepository
import network_work.utils.ApiResponse

class PostViewModel constructor(private val postRepository: PostRepository, private val scope: CoroutineScope) {

    private val _response: MutableState<ApiResponse> = mutableStateOf(ApiResponse.Loading)
    val response = _response

    init {
        getPost()
    }


    private fun getPost() = scope.launch(Dispatchers.Main) {
        postRepository.getPost()
            .onStart {
                response.value = ApiResponse.Loading
            }.catch { e ->
                response.value = ApiResponse.Failure(e)
            }.collect {
                print(it[0].body)
                response.value = ApiResponse.Success(it)
            }
    }
}