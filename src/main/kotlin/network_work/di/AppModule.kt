package network_work.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import network_work.data.network.ApiService
import network_work.data.repository.PostRepository
import network_work.data.ui.PostViewModel
import org.koin.dsl.module


val appModule = module {
    single { ApiService() }
    single { PostRepository(get()) }
    factory { CoroutineScope(SupervisorJob()) }
    single { PostViewModel(get(), get()) }
}