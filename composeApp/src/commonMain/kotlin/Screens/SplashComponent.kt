package Screens

import TokenSave
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
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
    val onSplashFinished: () -> Unit,
    val onSplashFinished2: () -> Unit,
    private val tokenSave: TokenSave
) : ComponentContext by componentContext {


    init {
        CoroutineScope(Dispatchers.Main).launch {
            if (!tokenSave.token.isNullOrBlank()) {
                delay(3000)
                onSplashFinished2()
            } else {
                delay(3000) // 3 saniye sonra geçiş yapılır
            onSplashFinished()
        }
        }
        }

    @Composable
    fun Render() {
        println("splash screen token is: ${tokenSave.token}")
        val version = getVersionNumber()

        Box(
            modifier = Modifier.Companion
                .fillMaxSize()
                .background(color = AppColors.Primary),
            contentAlignment = Alignment.Companion.Center
        ) {
            Column(
                modifier = Modifier.Companion
                    .align(Alignment.Companion.Center)
            ) {
                Image(
                    painter = getImagePainter(),
                    contentDescription = "Damen Logo",
                    modifier = Modifier.Companion
                        .size(256.dp)
                )
                Text(
                    text = "Version: $version",
                    color = AppColors.Secondary,
                    modifier = Modifier.Companion
                        .align(Alignment.Companion.CenterHorizontally)
                )
                Spacer(modifier = Modifier.Companion.height(40.dp))
                if (!tokenSave.token.isNullOrBlank()) {
                    CircularProgressIndicator(
                        color = AppColors.Third,
                        strokeWidth = 4.dp,
                        modifier = Modifier.Companion.size(48.dp)
                            .align(Alignment.Companion.CenterHorizontally)
                    )
                }
            }

        }
    }
}