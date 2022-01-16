import network_work.data.ui.PostViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import weather_app.data.ui.CityViewModel

class BaseApplication : KoinComponent {

    val viewmodel: PostViewModel by inject()
    val cityviewmodel: CityViewModel by inject()
}