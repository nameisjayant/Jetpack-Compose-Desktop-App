package network_work.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import network_work.data.Post
import network_work.data.network.ApiService


class PostRepository constructor(private val apiService: ApiService) {

    fun getPost(): Flow<List<Post>> = flow {
        apiService.getPost()
    }
}