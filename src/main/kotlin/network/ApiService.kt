package network

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import network_work.data.Post
import weather_app.data.model.City

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

    suspend fun getCurrentWeather(): City {
        return client.get {
            url("https://api.openweathermap.org/data/2.5/weather?q=delhi&appid=a45bda185288cef6b03035dd614f61b1")
        }
    }

}