import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashComponent(
    componentContext: ComponentContext,
    val onSplashFinished: () -> Unit
) : ComponentContext by componentContext {

    init {
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000) // 2 saniye sonra geçiş yapılır
            onSplashFinished()
        }
    }

    @Composable
    fun Render() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Logo Burada - Splash Screen")
        }
    }
}
