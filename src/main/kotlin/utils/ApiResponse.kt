package utils

import network_work.data.Post
import weather_app.data.model.City

sealed class ApiResponse {

    data class Success(val data: List<Post>) : ApiResponse()
    data class Failure(val msg: Throwable) : ApiResponse()
    object Loading : ApiResponse()
    object Empty : ApiResponse()
}

sealed class WeatherResponse {

    data class Success(val data: City) : WeatherResponse()
    data class Failure(val msg: Throwable) : WeatherResponse()
    object Loading : WeatherResponse()
    object Empty : WeatherResponse()

}
