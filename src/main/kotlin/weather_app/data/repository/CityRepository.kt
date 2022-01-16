package weather_app.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import network.ApiService
import weather_app.data.model.City

class CityRepository constructor(private val apiService: ApiService) {

    fun getCurrentWeather():Flow<City> = flow {
        emit(apiService.getCurrentWeather())
    }
}