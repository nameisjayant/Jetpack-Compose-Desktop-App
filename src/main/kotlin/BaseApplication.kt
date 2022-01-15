import network_work.data.ui.PostViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BaseApplication : KoinComponent{

    val viewmodel:PostViewModel by inject()

}