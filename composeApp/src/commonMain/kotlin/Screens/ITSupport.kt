package Screens

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowLeft
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Language
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.ComponentContext
import com.yourapp.design.AppColors
import openUrl
import org.example.nazlican.getImagePainter

class ITSupport(componentContext: ComponentContext, private val onITSupportLeft: () -> Unit, private val onITSupportButton: (String, String) -> Unit): ComponentContext by componentContext {

    @Composable
    fun Render() {
        val scrollState = rememberScrollState()
        val questionsList = listOf(
            Questions("1","VPN'e giremiyorum", "Vpn ayarlarını kontrol et"),
            Questions("2","Guests şifresi nedir?", "Guests şifresi 'freeaccess' ")
        )
        var ticketButtonClicked by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier.Companion // Tüm ana ekran kapsıyan box.
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.Companion
                    .fillMaxSize()
                    .verticalScroll(scrollState)
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
                            onClick = { onITSupportLeft() },
                            modifier = Modifier.Companion
                                .size(45.dp)
                                .align(Alignment.Companion.CenterStart)
                        ) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowLeft,
                                contentDescription = null,
                                tint = AppColors.Third,
                                modifier = Modifier.Companion
                                    .size(40.dp)
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
                                onClick = {},
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
                Column(
                    modifier = Modifier.Companion
                        .fillMaxWidth()
                        .background(Color.Companion.Transparent)
                        .align(alignment = Alignment.Companion.CenterHorizontally)
                        .padding(top = 20.dp)
                ) {

                    Text(
                        "Sık Sorulan Sorular",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Companion.ExtraBold,
                            color = AppColors.Primary
                        ),
                        modifier = Modifier.Companion.align(Alignment.Companion.CenterHorizontally)
                    )
                    Spacer(Modifier.Companion.size(20.dp))
                    LazyColumn(
                        modifier = Modifier.Companion.size(400.dp)
                            .padding(horizontal = 30.dp)
                    ) {

                        itemsIndexed(questionsList) { index, item ->
                            Button(
                                onClick = {
                                    onITSupportButton(
                                        questionsList[index].title,
                                        questionsList[index].description
                                    )
                                },
                                modifier = Modifier.Companion.fillMaxWidth(),
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = AppColors.Primary)
                            ) {

                                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                                    Text(
                                        (index + 1).toString() + "- ",
                                        style = TextStyle(fontSize = 15.sp, color = AppColors.Third)
                                    )
                                    Text(
                                        item.title,
                                        style = TextStyle(
                                            fontSize = 15.sp,
                                            color = AppColors.Secondary
                                        )
                                    )
                                }
                            }
                        }
                    }
                    Text(
                        "Eğer çözümün burada yoksa alt taraftan ticket oluştur.",
                        modifier = Modifier.Companion
                            .align(Alignment.Companion.CenterHorizontally)
                            .padding(top = 100.dp),
                        style = TextStyle(fontWeight = FontWeight.Companion.Bold)
                    )
                    Button(
                        onClick = { ticketButtonClicked = !ticketButtonClicked },
                        colors = ButtonDefaults.buttonColors(containerColor = AppColors.Third),
                        modifier = Modifier.Companion.align(Alignment.Companion.CenterHorizontally)
                            .padding(top = 30.dp)
                    ) {
                        Text(
                            "Ticket Oluştur",
                            style = TextStyle(
                                color = AppColors.Primary,
                                fontWeight = FontWeight.Companion.Bold
                            )
                        )
                    }
                }
            }

        }
    if (ticketButtonClicked) {
        openUrl("https://selfservicedesk.damen.com/damen")
    } // Ticket Oluştura tıklayınca Service Desk sitesi açılıyor.
    }
    data class Questions( //// Soruların oluşturulduğu data sınıfı
        val id: String,
        val title: String,
        val description: String
    )
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
}