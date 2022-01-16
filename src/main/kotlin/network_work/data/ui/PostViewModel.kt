package network_work.data.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import network_work.data.repository.PostRepository
import utils.ApiResponse

class PostViewModel constructor(private val postRepository: PostRepository, private val scope: CoroutineScope) {

    private val _response: MutableStateFlow<ApiResponse> = MutableStateFlow(ApiResponse.Empty)
    val response = _response.asStateFlow()

    init {
        getPost()
    }


    private fun getPost() = scope.launch(Dispatchers.Main) {
        _response.value = ApiResponse.Loading
        postRepository.getPost()
            .catch { e ->
                _response.value = ApiResponse.Failure(e)
            }.collect {data->
                print(data[0].body)
                _response.value = ApiResponse.Success(data)
            }
    }
}