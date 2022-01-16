package di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import network.ApiService
import network_work.data.repository.PostRepository
import network_work.data.ui.PostViewModel
import org.koin.dsl.module
import weather_app.data.repository.CityRepository
import weather_app.data.ui.CityViewModel


val appModule = module {
    single { ApiService() }
    factory { PostRepository(get()) }
    factory { CoroutineScope(SupervisorJob()) }
    factory { PostViewModel(get(), get()) }

    factory { CityRepository(get()) }
    factory { CityViewModel(get(), get()) }
}