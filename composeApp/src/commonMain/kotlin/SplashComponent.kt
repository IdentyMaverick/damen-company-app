import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.yourapp.design.AppColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.nazlican.getImagePainter
import org.example.nazlican.getVersionNumber

class SplashComponent(
    componentContext: ComponentContext,
    val onSplashFinished: () -> Unit
) : ComponentContext by componentContext {

    init {
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000) // 3 saniye sonra geçiş yapılır
            onSplashFinished()
        }
    }

    @Composable
    fun Render() {
        val version = getVersionNumber()

        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = AppColors.Primary), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Image(
                    painter = getImagePainter(),
                    contentDescription = "Damen Logo",
                    modifier = Modifier
                        .size(256.dp)
                )
                Text(text = "Version: $version",
                    color = AppColors.Secondary,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally))
            }

        }
    }
}
