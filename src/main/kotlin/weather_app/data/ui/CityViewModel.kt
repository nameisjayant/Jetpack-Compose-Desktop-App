package weather_app.data.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import utils.WeatherResponse
import weather_app.data.repository.CityRepository

class CityViewModel constructor(
    private val cityRepository: CityRepository,
    private val scope: CoroutineScope
) {

    private val _cityResponse: MutableStateFlow<WeatherResponse> = MutableStateFlow(WeatherResponse.Empty)
    val cityResponse = _cityResponse.asStateFlow()

    init {
        getCurrentWeather()
    }

    fun getCurrentWeather() = scope.launch {
        cityRepository.getCurrentWeather()
            .onStart {
                _cityResponse.value = WeatherResponse.Loading
            }.catch { e ->
                _cityResponse.value = WeatherResponse.Failure(e)
            }.collect {
                _cityResponse.value = WeatherResponse.Success(it)
            }
    }

}