package Screens

import NewsResponse
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowLeft
import androidx.compose.material.icons.automirrored.filled.More
import androidx.compose.material.icons.automirrored.filled.ReadMore
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.arkivanov.decompose.ComponentContext
import com.yourapp.design.AppColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch


class NewsDetails(
    componentContext: ComponentContext,
    private val newsId: Int,
    private val onNewsDetailsBack: () -> Unit
) : ComponentContext by componentContext {

    private var news by mutableStateOf<NewsResponse?>(null) // state ile tanımladık

    init {
        loadSpecificNews(newsId)
    }

    @Composable
    fun Render() {
        Box(Modifier.fillMaxSize().background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.Black,
                    Color.Black.copy(alpha = 0.7f)
                ),
                startY = 400f,
                endY = Float.POSITIVE_INFINITY
            )
        )) {
            AsyncImage(
                model = news?.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            // Üst bar
            Row(Modifier.fillMaxWidth().padding(vertical = 20.dp)) {
                IconButton(
                    onClick = { onNewsDetailsBack() },
                    modifier = Modifier.size(45.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowLeft,
                        contentDescription = "Back",
                        tint = AppColors.Primary,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 1f) // altta siyahlaşır
                            ),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    )
            ) {
                Text(
                    text = news?.title ?: "Yükleniyor...",
                    style = TextStyle(fontSize = 20.sp, color = AppColors.Secondary),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 50.dp)
                )
            }
            Column(
                Modifier
                    .align(Alignment.BottomCenter)
                    .height(400.dp)
                    .fillMaxWidth()
                    .background(color = AppColors.Secondary, shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(26.dp))
                Text(
                    text = news?.content ?: "",
                    style = TextStyle(fontSize = 16.sp, color = AppColors.Primary),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }

    private fun loadSpecificNews(newsId: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            news = ApiService.newsSpecific(newsId)
        }
    }
}
