package network_work.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import network_work.data.Post
import network_work.data.network.ApiService


class PostRepository constructor(private val apiService: ApiService) {

    fun getPost(): Flow<List<Post>> = flow {
        emit(apiService.getPost())
    }.flowOn(Dispatchers.IO)
}