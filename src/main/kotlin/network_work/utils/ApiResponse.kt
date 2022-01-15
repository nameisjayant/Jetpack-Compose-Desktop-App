package network_work.utils

import network_work.data.Post

sealed class ApiResponse {

    data class Success(val data: List<Post>) : ApiResponse()
    data class Failure(val msg: Throwable) : ApiResponse()
    object Loading : ApiResponse()

}
