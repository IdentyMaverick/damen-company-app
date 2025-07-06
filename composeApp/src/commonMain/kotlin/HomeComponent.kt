import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.PriorityHigh
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.RssFeed
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.ComponentContext
import com.yourapp.design.AppColors
import org.example.nazlican.getImagePainter
import org.example.nazlican.getLanguageIcon
import org.example.nazlican.getLanguageIcon2

class HomeComponent(componentContext: ComponentContext) : ComponentContext by componentContext {
    @Composable
    fun Render() {
        var searchText by remember { mutableStateOf("") }
        val itemsList = listOf("A", "B", "C", "D", "E")
        val scrollState = rememberScrollState()

        Box(modifier = Modifier // Tüm ana ekran kapsıyan box.
            .fillMaxSize()){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                Box( // Search Box üstünü kapsayan box.
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(AppColors.Primary)
                ) {
                    Box( // Damen logosunu ve ikonları kapsayan box. Yatay düzlemde.
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .height(56.dp)
                    ) {
                        // Sol ikon
                        IconButton(
                            onClick = { /* expanded = true */ },
                            modifier = Modifier
                                .size(45.dp)
                                .align(Alignment.CenterStart)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu Icon",
                                tint = Color.White
                            ) }
                        // Ortadaki logo
                        Image(
                            painter = getImagePainter(),
                            contentDescription = "Damen Logo",
                            modifier = Modifier
                                .size(128.dp)
                                .align(Alignment.Center)
                        )
                        // Sağdaki ikonlar
                        Row( // Sağdaki 2 ikonu ayrıca yatayda içeren row. Önemsiz.
                            modifier = Modifier.align(Alignment.CenterEnd),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { }, modifier = Modifier.size(40.dp)) {
                                Icon(Icons.Default.Forum, contentDescription = null, tint = Color.White)
                            }
                            IconButton(onClick = {  }, modifier = Modifier.size(40.dp)) {
                                Icon(Icons.Default.Language, contentDescription = null, tint = Color.White)
                            }
                        }
                    }
                    Box(  // SearchBox ile bölen yatay çizgi box.
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(5.dp)
                            .background(color = AppColors.Third)
                            .align(Alignment.BottomCenter)
                    )
                }

                // 🔽 SearchBox artık ana boxın dışında ve üstüne taşacak şekilde hizalanmış.
                Box( // İkinci SearchBox üstünü kapsayan box. Amacı çizginin ortasına koyabilmek.
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-20).dp) // yukarı taş
                    // Yüksekliğe göre ayarlayabilirsin
                ) {
                    SearchBox(
                        query = searchText,
                        onQueryChange = { searchText = it },
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .fillMaxWidth()
                    )
                }
                Box(modifier = Modifier //
                    .background(color = AppColors.Third)
                    .align(Alignment.CenterHorizontally)){
                }
                Row(modifier = Modifier
                    .align(Alignment.CenterHorizontally)) {
                    IconLazyRow()
                }
                Column(modifier = Modifier.background(Color.Transparent)
                    .align(alignment = Alignment.CenterHorizontally)) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("DAMEN HABER",
                            color = AppColors.Primary,
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 20.dp),
                            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
                        Text("Tümünü Gör",
                            style = TextStyle(color = AppColors.Third, fontSize = 12.sp, fontWeight = FontWeight.Bold),
                            textDecoration = TextDecoration.Underline,
                            modifier = Modifier
                                .padding(horizontal = 20.dp)
                                .clickable(onClick = {})
                                .align(Alignment.Bottom))
                    }
                    Spacer(Modifier.size(20.dp))
                    NewsLazyRow()
                    Spacer(Modifier.size(20.dp))
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(horizontal = 15.dp)
                        .background(color = AppColors.Primary, shape = RoundedCornerShape(10.dp))) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text("GÜNÜN MENÜSÜ",
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 20.dp),
                                style = TextStyle(color = Color.White, fontSize = 15.sp, letterSpacing = 3.sp, fontWeight = FontWeight.Bold))
                        }
                    }
                }
            }

        }
    }

    @Composable
fun SearchBox(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
){
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .size(35.dp)
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        placeholder = {Text("ARA",
            fontSize = 10.sp)},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon"
            )
        },
        trailingIcon = {
            IconButton(onClick = {/* Telefon QR Codunu Aç */}){
                Icon(
                    imageVector = Icons.Default.QrCodeScanner,
                    contentDescription = "QrCodeScanner",
                    tint = AppColors.Primary
                )
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(5.dp),
        colors = OutlinedTextFieldDefaults
            .colors(
                focusedBorderColor = AppColors.Third,
                unfocusedBorderColor = AppColors.Third,
                cursorColor = AppColors.Primary,
                unfocusedContainerColor = AppColors.Secondary,
                focusedContainerColor = AppColors.Secondary
            )
    )
}

    @Composable
    fun IconLazyRow() {
        val itemsList = listOf("A") // Temsili veri

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(start = 6.dp, end = 6.dp)
        ) {
            items(itemsList) { item ->
                Column {
                    IconButton(onClick = { }, modifier = Modifier
                        .size(40.dp)
                        .background(AppColors.Primary, shape = RoundedCornerShape(10.dp))) {
                        Icon(Icons.Default.PriorityHigh, contentDescription = null, tint = Color.White)
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Text("Ramak\nKala",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp)
                }
                Spacer(Modifier.size(15.dp))
                Column() {
                    IconButton(onClick = { }, modifier = Modifier
                        .size(40.dp)
                        .background(AppColors.Primary, shape = RoundedCornerShape(10.dp))) {
                        Icon(Icons.Default.SentimentSatisfied, contentDescription = null, tint = Color.White)
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Text("Anket",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp)
                }
                Spacer(Modifier.size(15.dp))
                Column {
                    IconButton(onClick = { }, modifier = Modifier
                        .size(40.dp)
                        .background(AppColors.Primary, shape = RoundedCornerShape(10.dp))) {
                        Icon(Icons.Default.Restaurant, contentDescription = null, tint = Color.White)
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Text("Yemek\nListesi",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp)
                }
                Spacer(Modifier.size(15.dp))
                Column {
                    IconButton(onClick = { }, modifier = Modifier
                        .size(40.dp)
                        .background(AppColors.Primary, shape = RoundedCornerShape(10.dp))) {
                        Icon(Icons.Default.Celebration, contentDescription = null, tint = Color.White)
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Text("Etkinlik\nTakvimi",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp)
                }
                Spacer(Modifier.size(15.dp))
                Column {
                    IconButton(onClick = { }, modifier = Modifier
                        .size(40.dp)
                        .background(AppColors.Primary, shape = RoundedCornerShape(10.dp))) {
                        Icon(Icons.Default.Newspaper, contentDescription = null, tint = Color.White)
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Text("Magazin",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp)
                }
                Spacer(Modifier.size(15.dp))
                Column {
                    IconButton(onClick = { }, modifier = Modifier
                        .size(40.dp)
                        .background(AppColors.Primary, shape = RoundedCornerShape(10.dp))) {
                        Icon(Icons.Default.Devices, contentDescription = null, tint = Color.White)
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Text("IT\nDestek",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp)
                }
                Spacer(Modifier.size(15.dp))
                Column {
                    IconButton(onClick = { }, modifier = Modifier
                        .size(40.dp)
                        .background(AppColors.Primary, shape = RoundedCornerShape(10.dp))) {
                        Icon(Icons.Default.Medication, contentDescription = null, tint = Color.White)
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Text("Medikal",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp)
                }
            }
        }
    }
    @Composable
    fun NewsLazyRow() {
        val newsList = listOf("A","B","C","D","E","F","G")

        LazyColumn(
            modifier = Modifier
                .background(Color.Transparent)
                .fillMaxWidth()
                .height(200.dp)
        ){
            items(newsList) { item ->

                Box(
                    modifier = Modifier
                        .clickable(onClick = {})
                ){
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { }, modifier = Modifier
                                .padding(start = 20.dp)
                                .size(40.dp)
                                .background(Color.Transparent, shape = RoundedCornerShape(10.dp))
                            ) {
                                Icon(Icons.Default.RssFeed, contentDescription = null, tint = AppColors.Primary)
                            }
                            Text("6001 suya indirildi",
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(start = 20.dp),
                                style = TextStyle(fontWeight = FontWeight.Bold))
                            IconButton(onClick = { }, modifier = Modifier
                                .padding(end = 20.dp)
                                .size(40.dp)
                                .background(Color.Transparent, shape = RoundedCornerShape(10.dp))
                            ) {
                                Icon(Icons.Default.ArrowRight, contentDescription = null, tint = AppColors.Primary)
                            }
                        }
                        HorizontalDivider(
                            modifier = Modifier
                                .padding(horizontal = 20.dp),
                            DividerDefaults.Thickness,
                            AppColors.Third
                        )
                    }

                }
            }
        }
    }
}
