package network_work.data.network

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import network_work.data.Post

class ApiService {

    private val client = HttpClient(CIO) {

        install(JsonFeature) {
            serializer = GsonSerializer()
        }

        engine {
            requestTimeout = 100_00
        }
    }

    suspend fun getPost(): List<Post> {
        return client.get {
            url("https://jsonplaceholder.typicode.com/posts")
        }
    }

}