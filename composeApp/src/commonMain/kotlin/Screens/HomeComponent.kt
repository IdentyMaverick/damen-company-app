package Screens

import NewsResponse
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PriorityHigh
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.value.MutableValue
import com.yourapp.design.AppColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import openUrl
import org.example.nazlican.getImagePainter
import org.example.nazlican.getImagePainter2
import org.jetbrains.compose.resources.painterResource

class HomeComponent(componentContext: ComponentContext, private val onFeedbackClick: () -> Unit, private val onLogOut: () -> Unit, private val onITSupportClick: () -> Unit, private val onClickNewsDetail: (Int) -> Unit, private val onClickEventCalendar: () -> Unit) :
    ComponentContext by componentContext {
    val newsState = MutableValue<List<NewsResponse>>(emptyList())

        init {
            loadNews()
        }
    @Composable
    fun Render() {
        var showDialog by remember { mutableStateOf(false) }
        var showDialog2 by remember { mutableStateOf(false) }
        var searchText by remember { mutableStateOf("") }
        val scrollState = rememberScrollState()
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val state by newsState.subscribeAsState()

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(
                    drawerContainerColor = AppColors.Secondary
                ) {
                    // Drawer içeriği buraya
                    Row(
                        Modifier.Companion
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.Companion.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        Image(
                            painter = getImagePainter2(),
                            contentDescription = "Blue Damen Logo",
                            modifier = Modifier.Companion.size(125.dp)
                        )
                        Text(
                            "v0.2.0-dev",
                            modifier = Modifier.Companion.padding(5.dp, top = 15.dp),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Companion.Bold,
                            color = AppColors.Primary
                        )
                    }
                    HorizontalDivider(
                        thickness = 3.dp,
                        modifier = Modifier.Companion.padding(horizontal = 10.dp),
                        color = AppColors.Third
                    )
                    NavigationDrawerItem(
                        label = { Text("Kullanıcı Bilgileri", fontSize = 17.sp) },
                        selected = false,
                        onClick = { showDialog = true },
                        icon = {
                            IconButton(
                                onClick = { scope.launch { drawerState.open() } },
                                modifier = Modifier.Companion
                                    .size(45.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AccountCircle,
                                    contentDescription = "User Icon",
                                    tint = AppColors.Primary
                                )
                            }
                        }
                    )
                    HorizontalDivider(
                        thickness = 1.dp,
                        modifier = Modifier.Companion.padding(horizontal = 20.dp),
                        color = AppColors.Third
                    )
                    NavigationDrawerItem(
                        label = { Text("Uygulama Hakkında", fontSize = 17.sp) },
                        selected = false,
                        onClick = { showDialog2 = true },
                        icon = {
                            IconButton(
                                onClick = { scope.launch { drawerState.open() } },
                                modifier = Modifier.Companion
                                    .size(45.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = "Info Icon",
                                    tint = AppColors.Primary
                                )
                            }
                        }
                    )
                    HorizontalDivider(
                        thickness = 1.dp,
                        modifier = Modifier.Companion.padding(horizontal = 20.dp),
                        color = AppColors.Third
                    )
                    NavigationDrawerItem(
                        label = {
                            Text(
                                "Çıkış Yap",
                                fontSize = 17.sp,
                                color = AppColors.Primary,
                                fontWeight = FontWeight.Companion.ExtraBold
                            )
                        },
                        selected = false,
                        onClick = { onLogOut() },
                        icon = {
                            IconButton(
                                onClick = { scope.launch { drawerState.open() } },
                                modifier = Modifier.Companion
                                    .size(45.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                                    contentDescription = "Info Icon",
                                    tint = AppColors.Primary
                                )
                            }
                        }
                    )

                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            confirmButton = {
                                Button(onClick = { showDialog = false }, colors = ButtonDefaults.buttonColors(containerColor = AppColors.Primary)) {
                                    Text("Tamam")
                                }
                            },
                            title = { Text( "Kullanıcı Bilgileri" ) },
                            text = { Text( "Yakında" ) }
                        )
                    }

                    if (showDialog2) {
                    AlertDialog(
                        onDismissRequest = { showDialog2 = false },
                        confirmButton = {
                            Button(onClick = { showDialog2= false }, colors = ButtonDefaults.buttonColors(containerColor = AppColors.Primary)) {
                                Text("Tamam")
                            }
                        },
                        title = { Text("Uygulama Hakkında") },
                        text = { Text("Damen App, Damen şirketi çalışanlarının güncel haberlere, duyurulara ve çeşitli iç özelliklere erişimini kolaylaştırmak amacıyla geliştirilmiştir.\n" + "\n" +
                                "Sürüm: v0.2.0-dev\n" +
                                "\n" +
                                "Geliştirici: Osman Deniz Savaş\n" +
                                "\n" +
                                "İletişim: osman.deniz.savas@damen.com") }
                    )
                }
                }
            },
            scrimColor = AppColors.Primary
        ) {

            Box(
                modifier = Modifier.Companion // Tüm ana ekran kapsıyan box.
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.Companion
                        .fillMaxSize()
                ) {
                    Box( // Search Box üstünü kapsayan box.
                        modifier = Modifier.Companion
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(AppColors.Primary)
                    ) {
                        Box( // Damen logosunu ve ikonları kapsayan box. Yatay düzlemde.
                            modifier = Modifier.Companion
                                .align(Alignment.Companion.Center)
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .height(56.dp)
                        ) {
                            // Sol ikon
                            IconButton(
                                onClick = { scope.launch { drawerState.open() } },
                                modifier = Modifier.Companion
                                    .size(45.dp)
                                    .align(Alignment.Companion.CenterStart)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Menu Icon",
                                    tint = Color.Companion.White
                                )
                            }
                            // Ortadaki logo
                            Image(
                                painter = getImagePainter(),
                                contentDescription = "Damen Logo",
                                modifier = Modifier.Companion
                                    .size(128.dp)
                                    .align(Alignment.Companion.Center)
                            )
                            // Sağdaki ikonlar
                            Row( // Sağdaki 2 ikonu ayrıca yatayda içeren row. Önemsiz.
                                modifier = Modifier.Companion.align(Alignment.Companion.CenterEnd),
                                verticalAlignment = Alignment.Companion.CenterVertically
                            ) {
                                IconButton(
                                    onClick = { onFeedbackClick() },
                                    modifier = Modifier.Companion.size(40.dp)
                                ) {
                                    Icon(
                                        Icons.Default.Forum,
                                        contentDescription = null,
                                        tint = Color.Companion.White
                                    )
                                }
                                Spacer(Modifier.Companion.size(10.dp))
                                LanguageSelectorIcon()
                            }
                        }
                        Box(  // SearchBox ile bölen yatay çizgi box.
                            modifier = Modifier.Companion
                                .fillMaxWidth()
                                .height(5.dp)
                                .background(color = AppColors.Third)
                                .align(Alignment.Companion.BottomCenter)
                        )
                    }

                    // 🔽 SearchBox artık ana boxın dışında ve üstüne taşacak şekilde hizalanmış.
                    Box( // İkinci SearchBox üstünü kapsayan box. Amacı çizginin ortasına koyabilmek.
                        modifier = Modifier.Companion
                            .fillMaxWidth()
                            .offset(y = (-20).dp) // yukarı taş
                        // Yüksekliğe göre ayarlayabilirsin
                    ) {
                        SearchBox(
                            query = searchText,
                            onQueryChange = { searchText = it },
                            modifier = Modifier.Companion
                                .padding(horizontal = 24.dp)
                                .fillMaxWidth()
                        )
                    }
                    Box(
                        modifier = Modifier.Companion //
                            .background(color = AppColors.Third)
                            .align(Alignment.Companion.CenterHorizontally)
                    ) {
                    }
                    Row(
                        modifier = Modifier.Companion
                            .align(Alignment.Companion.CenterHorizontally)
                    ) {
                        IconLazyRow()
                    }
                    Column(
                        modifier = Modifier.Companion.background(Color.Companion.Transparent)
                            .align(alignment = Alignment.Companion.CenterHorizontally)
                    ) {
                        Row(horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(
                                "DAMEN HABER",
                                color = AppColors.Primary,
                                modifier = Modifier.Companion
                                    .weight(1f)
                                    .padding(horizontal = 20.dp),
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Companion.Bold
                                )
                            )
                            Text(
                                "Tümünü Gör",
                                style = TextStyle(
                                    color = AppColors.Third,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Companion.Bold
                                ),
                                textDecoration = TextDecoration.Companion.Underline,
                                modifier = Modifier.Companion
                                    .padding(horizontal = 20.dp)
                                    .clickable(onClick = {})
                                    .align(Alignment.Companion.Bottom)
                            )
                        }
                        Spacer(Modifier.Companion.size(20.dp))
                        HorizontalDivider(
                            thickness = 2.dp,
                            modifier = Modifier.Companion.padding(horizontal = 10.dp),
                            color = AppColors.Third
                        )
                        NewsLazyColumn(state)
                        Spacer(Modifier.Companion.size(20.dp))
                    }
                }

            }
        }
    }

    @Composable
    fun SearchBox(
        query: String,
        onQueryChange: (String) -> Unit,
        modifier: Modifier = Modifier.Companion
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = modifier
                .height(48.dp)
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            placeholder = {
                Text(
                    "ARA",
                    fontSize = 12.sp
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = AppColors.Primary
                )
            },
            trailingIcon = {
                IconButton(onClick = {/* Telefon QR Codunu Aç */ }) {
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
        var surveyButtonClicked by remember { mutableStateOf(false) }
        var showDialog by remember { mutableStateOf(false) }

        LazyRow(
            modifier = Modifier.Companion
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(start = 6.dp, end = 6.dp)
        ) {
            items(itemsList) { item ->
                Column {
                    IconButton(
                        onClick = { }, modifier = Modifier.Companion
                            .size(40.dp)
                            .background(
                                Color.Companion.Gray,
                                shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
                            )
                    ) {
                        Icon(
                            Icons.Default.PriorityHigh,
                            contentDescription = null,
                            tint = Color.Companion.White
                        )
                    }
                    Spacer(modifier = Modifier.Companion.size(10.dp))
                    Text(
                        "Ramak\nKala",
                        fontWeight = FontWeight.Companion.Bold,
                        textAlign = TextAlign.Companion.Center,
                        fontSize = 10.sp
                    )
                }
                Spacer(Modifier.Companion.size(25.dp))
                Column {
                    IconButton(
                        onClick = { surveyButtonClicked = !surveyButtonClicked },
                        modifier = Modifier.Companion
                            .size(40.dp)
                            .background(
                                AppColors.Primary,
                                shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
                            )
                    ) {
                        Icon(
                            Icons.Default.SentimentSatisfied,
                            contentDescription = null,
                            tint = Color.Companion.White
                        )
                    }
                    Spacer(modifier = Modifier.Companion.size(10.dp))
                    Text(
                        "Anket",
                        fontWeight = FontWeight.Companion.Bold,
                        textAlign = TextAlign.Companion.Center,
                        fontSize = 10.sp
                    )
                }
                Spacer(Modifier.Companion.size(25.dp))
                Column {
                    IconButton(
                        enabled = true,
                        onClick = { onClickEventCalendar() }, modifier = Modifier.Companion
                            .size(40.dp)
                            .background(
                                AppColors.Primary,
                                shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
                            )
                    ) {
                        Icon(
                            Icons.Default.Celebration,
                            contentDescription = null,
                            tint = Color.Companion.White
                        )
                    }
                    Spacer(modifier = Modifier.Companion.size(10.dp))
                    Text(
                        "Etkinlik\nTakvimi",
                        fontWeight = FontWeight.Companion.Bold,
                        textAlign = TextAlign.Companion.Center,
                        fontSize = 10.sp
                    )
                }
                Spacer(Modifier.Companion.size(25.dp))
                Column {
                    IconButton(
                        enabled = true,
                        onClick = { onITSupportClick() }, modifier = Modifier.Companion
                            .size(40.dp)
                            .background(
                                AppColors.Primary,
                                shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
                            )
                    ) {
                        Icon(
                            Icons.Default.Devices,
                            contentDescription = null,
                            tint = Color.Companion.White
                        )
                    }
                    Spacer(modifier = Modifier.Companion.size(10.dp))
                    Text(
                        "IT\nDestek",
                        fontWeight = FontWeight.Companion.Bold,
                        textAlign = TextAlign.Companion.Center,
                        fontSize = 10.sp
                    )
                }
                Spacer(Modifier.Companion.size(25.dp))
                Column {
                    IconButton(
                        enabled = true,
                        onClick = { showDialog = true }, modifier = Modifier.Companion
                            .size(40.dp)
                            .background(
                                Color.Companion.Gray,
                                shape = androidx.compose.foundation.shape.RoundedCornerShape(10.dp)
                            )
                    ) {
                        Icon(
                            Icons.Default.Medication,
                            contentDescription = null,
                            tint = Color.Companion.White
                        )
                    }
                    Spacer(modifier = Modifier.Companion.size(10.dp))
                    Text(
                        "Medikal",
                        fontWeight = FontWeight.Companion.Bold,
                        textAlign = TextAlign.Companion.Center,
                        fontSize = 10.sp
                    )
                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            confirmButton = {
                                Button(onClick = { showDialog = false }, colors = ButtonDefaults.buttonColors(containerColor = AppColors.Primary)) {
                                    Text("Tamam")
                                }
                            },
                            title = { Text("Medikal") },
                            text = { Text("Yakın zamanda erişime açılacaktır.") }
                        )
                    }
                }
            }
        }
    if (surveyButtonClicked) {
        openUrl("https://www.google.com/")
        surveyButtonClicked = !surveyButtonClicked
    }
    }

    @Composable
    fun NewsLazyColumn(state: List<NewsResponse>) {
        LazyColumn(
            modifier = Modifier.Companion
                .padding(top = 10.dp),
            contentPadding = PaddingValues(horizontal = 15.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state,
                key = { it.id }) { item ->
                Card(
                    modifier = Modifier
                        .height(200.dp)
                        .clickable(onClick = { onClickNewsDetail(item.id) }),
                    elevation = CardDefaults.cardElevation(10.dp),
                    border = BorderStroke(width = 0.dp, color = Color.Transparent),
                    colors = CardDefaults.cardColors(containerColor = AppColors.Secondary)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // Arka plan resim
                        AsyncImage(
                            model = item.imageUrl.ifBlank { getImagePainter2() },
                            contentDescription = null,
                            contentScale = ContentScale.Crop, // FillBounds yerine Crop daha doğal durur
                            modifier = Modifier.fillMaxSize()
                        )
                        println("imageUrl is: ${item.imageUrl}")

                        // Alt tarafa doğru kararma efekti
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Black.copy(alpha = 0.6f) // altta siyahlaşır
                                        ),
                                        startY = 0f,
                                        endY = Float.POSITIVE_INFINITY
                                    )
                                )
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                                .padding(8.dp),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                item.title,
                                style = TextStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                ),
                            modifier = Modifier
                                .padding(start = 30.dp, bottom = 30.dp)
                                )
                        }
                    }
                }

                Spacer(Modifier.Companion.size(20.dp))
            }
        }
    }


    @Composable
    fun LanguageSelectorIcon() { // Dropdown Language Selector
        var expanded by remember { mutableStateOf(false) }

        Box {
            IconButton(
                onClick = { expanded = true },
                modifier = Modifier.Companion
                    .size(45.dp)
                    .padding(end = 20.dp)
            ) {
                Icon(
                    Icons.Default.Language,
                    contentDescription = null,
                    tint = Color.Companion.White
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                containerColor = AppColors.Third
            ) {
                DropdownMenuItem(
                    text = {
                        Text(
                            "Türkçe",
                            color = AppColors.Secondary
                        )
                    },
                    onClick = {
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = {
                        Text(
                            "English",
                            color = AppColors.Secondary
                        )
                    },
                    onClick = {
                        expanded = false
                    }
                )
            }
        }
    }
    private fun loadNews() {
        CoroutineScope(Dispatchers.IO).launch {
            val news = ApiService.news()
            newsState.value = news
        }
    }
}