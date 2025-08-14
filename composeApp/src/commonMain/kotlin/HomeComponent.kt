import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Devices
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Medication
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.PriorityHigh
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.RssFeed
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.ComponentContext
import com.yourapp.design.AppColors
import kotlinx.coroutines.launch
import org.example.nazlican.getImagePainter
import org.example.nazlican.getImagePainter2
import org.example.nazlican.getLanguageIcon

class HomeComponent(componentContext: ComponentContext, private val onFeedbackClick: () -> Unit, private val onLogOut: () -> Unit, private val onITSupportClick: () -> Unit) :
    ComponentContext by componentContext {
    @Composable
    fun Render() {
        var searchText by remember { mutableStateOf("") }
        //val itemsList = listOf("A", "B", "C", "D", "E")
        val scrollState = rememberScrollState()
        val scope = rememberCoroutineScope()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(
                    drawerContainerColor = AppColors.Secondary
                ) {
                    // Drawer içeriği buraya
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        Image(
                            painter = getImagePainter2(),
                            contentDescription = "Blue Damen Logo",
                            modifier = Modifier.size(125.dp)
                        )
                        Text(
                            "v0.1.0-dev",
                            modifier = Modifier.padding(5.dp, top = 15.dp),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = AppColors.Primary
                        )
                    }
                    HorizontalDivider(thickness = 3.dp, modifier = Modifier.padding(horizontal = 10.dp), color = AppColors.Third)
                    NavigationDrawerItem(
                        label = { Text("Kullanıcı Bilgileri", fontSize = 17.sp) },
                        selected = false,
                        onClick = { /* örnek tıklama */ },
                        icon = {
                            IconButton(
                                onClick = { scope.launch { drawerState.open() } },
                                modifier = Modifier
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
                    HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 20.dp), color = AppColors.Third)
                    NavigationDrawerItem(
                        label = { Text("Uygulama Hakkında", fontSize = 17.sp) },
                        selected = false,
                        onClick = { /* örnek tıklama */ },
                        icon = {
                            IconButton(
                                onClick = { scope.launch { drawerState.open() } },
                                modifier = Modifier
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
                    HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(horizontal =20.dp), color = AppColors.Third)
                    NavigationDrawerItem(
                        label = { Text("Çıkış Yap", fontSize = 17.sp, color = Color.Red, fontWeight = FontWeight.ExtraBold) },
                        selected = false,
                        onClick = { onLogOut() },
                        icon = {
                            IconButton(
                                onClick = { scope.launch { drawerState.open() } },
                                modifier = Modifier
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
                }
            },
            scrimColor = AppColors.Primary
        ) {

            Box(
                modifier = Modifier // Tüm ana ekran kapsıyan box.
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
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
                                onClick = { scope.launch { drawerState.open() } },
                                modifier = Modifier
                                    .size(45.dp)
                                    .align(Alignment.CenterStart)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Menu Icon",
                                    tint = Color.White
                                )
                            }
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
                                IconButton(
                                    onClick = { onFeedbackClick() },
                                    modifier = Modifier.size(40.dp)
                                ) {
                                    Icon(
                                        Icons.Default.Forum,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }
                                Spacer(Modifier.size(10.dp))
                                LanguageSelectorIcon()
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
                    Box(
                        modifier = Modifier //
                            .background(color = AppColors.Third)
                            .align(Alignment.CenterHorizontally)
                    ) {
                    }
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    ) {
                        IconLazyRow()
                    }
                    Column(
                        modifier = Modifier.background(Color.Transparent)
                            .align(alignment = Alignment.CenterHorizontally)
                    ) {
                        Row(horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(
                                "DAMEN HABER",
                                color = AppColors.Primary,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 20.dp),
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            )
                            Text(
                                "Tümünü Gör",
                                style = TextStyle(
                                    color = AppColors.Third,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier
                                    .padding(horizontal = 20.dp)
                                    .clickable(onClick = {})
                                    .align(Alignment.Bottom)
                            )
                        }
                        Spacer(Modifier.size(20.dp))
                        HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(horizontal = 10.dp), color = AppColors.Third)
                        NewsLazyColumn()
                        Spacer(Modifier.size(20.dp))
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

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(start = 6.dp, end = 6.dp)
        ) {
            items(itemsList) { item ->
                Column {
                    IconButton(
                        onClick = { }, modifier = Modifier
                            .size(40.dp)
                            .background(Color.Gray, shape = RoundedCornerShape(10.dp))
                    ) {
                        Icon(
                            Icons.Default.PriorityHigh,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        "Ramak\nKala",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp
                    )
                }
                Spacer(Modifier.size(15.dp))
                Column {
                    IconButton(
                        onClick = { surveyButtonClicked = !surveyButtonClicked }, modifier = Modifier
                            .size(40.dp)
                            .background(AppColors.Primary, shape = RoundedCornerShape(10.dp))
                    ) {
                        Icon(
                            Icons.Default.SentimentSatisfied,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        "Anket",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp
                    )
                }
                Spacer(Modifier.size(15.dp))
                Column {
                    IconButton(
                        enabled = false,
                        onClick = { }, modifier = Modifier
                            .size(40.dp)
                            .background(Color.Gray, shape = RoundedCornerShape(10.dp))
                    ) {
                        Icon(
                            Icons.Default.Celebration,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        "Etkinlik\nTakvimi",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp
                    )
                }
                Spacer(Modifier.size(15.dp))
                Column {
                    IconButton(
                        enabled = false,
                        onClick = { }, modifier = Modifier
                            .size(40.dp)
                            .background(Color.Gray, shape = RoundedCornerShape(10.dp))
                    ) {
                        Icon(Icons.Default.Newspaper, contentDescription = null, tint = Color.White)
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        "Magazin",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp
                    )
                }
                Spacer(Modifier.size(15.dp))
                Column {
                    IconButton(
                        enabled = true,
                        onClick = { onITSupportClick() }, modifier = Modifier
                            .size(40.dp)
                            .background(AppColors.Primary, shape = RoundedCornerShape(10.dp))
                    ) {
                        Icon(Icons.Default.Devices, contentDescription = null, tint = Color.White)
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        "IT\nDestek",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp
                    )
                }
                Spacer(Modifier.size(15.dp))
                Column {
                    IconButton(
                        enabled = false,
                        onClick = { }, modifier = Modifier
                            .size(40.dp)
                            .background(Color.Gray, shape = RoundedCornerShape(10.dp))
                    ) {
                        Icon(
                            Icons.Default.Medication,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        "Medikal",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 10.sp
                    )
                }
            }
        }
    if (surveyButtonClicked) {
        openUrl("https://www.google.com/")
        surveyButtonClicked = !surveyButtonClicked
    }
    }

    @Composable
    fun NewsLazyColumn() {
            LazyColumn(
                modifier = Modifier
                    .padding(top = 10.dp),
                contentPadding = PaddingValues(horizontal = 15.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(listOf("A", "B", "C")) { item ->
                    Card(
                        modifier = Modifier
                            .height(200.dp)
                            .clickable(onClick = {}),
                        elevation = CardDefaults.cardElevation(10.dp),
                        border = BorderStroke(width = 5.dp, color = AppColors.Primary),
                        colors = CardDefaults.cardColors(containerColor = AppColors.Secondary)
                    ) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize().padding(0.dp)) {
                            Box(modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter).background(color = AppColors.Primary).height(50.dp ),
                                contentAlignment = Alignment.Center) {
                                Text(item, color = AppColors.Secondary)
                            }
                        }
                    }
                    Spacer(Modifier.size(20.dp))
                }
            }
    }


    @Composable
    fun LanguageSelectorIcon() { // Dropdown Language Selector
        var expanded by remember {mutableStateOf(false)}

        Box {
            IconButton(onClick = {expanded = true},
                modifier = Modifier
                    .size(45.dp)
                    .padding(end = 20.dp)){
                Icon(
                    Icons.Default.Language,
                    contentDescription = null,
                    tint = Color.White
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {expanded = false},
                containerColor = AppColors.Third
            ) {
                DropdownMenuItem(
                    text = { Text("Türkçe",
                        color = AppColors.Secondary)},
                    onClick = {
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("English",
                        color = AppColors.Secondary)},
                    onClick = {
                        expanded = false
                    }
                )
            }
        }
    }
}
