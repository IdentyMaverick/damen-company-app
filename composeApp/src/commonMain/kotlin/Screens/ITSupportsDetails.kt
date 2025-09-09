package Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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

class ITSupportsDetails(componentContext: ComponentContext, private val questionId: String, private val onFeedbackLefts: () -> Unit, private val questionDescription: String): ComponentContext by componentContext {
    @Composable
    fun Render() {
        var ticketButtonClicked by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier.Companion
                .fillMaxSize()
                .background(color = Color.Companion.White)
        ) {
            Box(
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .height(100.dp) // sabit yükseklik opsiyonel
                    .padding(top = 50.dp)
            ) {

                Row(
                    modifier = Modifier.Companion
                        .align(Alignment.Companion.CenterStart)
                        .padding(start = 16.dp),
                    verticalAlignment = Alignment.Companion.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        onClick = { onFeedbackLefts() },
                        modifier = Modifier.Companion.size(60.dp)
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowLeft,
                            contentDescription = null,
                            tint = AppColors.Primary,
                            modifier = Modifier.Companion
                                .size(40.dp)
                        )
                    }
                    Text(questionId, style = TextStyle(color = AppColors.Third, fontSize = 23.sp))
                }
            }
            Card(
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(horizontal = 40.dp, vertical = 50.dp)
            ) {

                Column(
                    modifier = Modifier.Companion.padding(16.dp)
                        .fillMaxSize()
                ) {
                    Text(
                        text = questionDescription,
                        style = TextStyle(fontSize = 18.sp, color = AppColors.Primary),
                        modifier = Modifier.Companion.align(Alignment.Companion.CenterHorizontally)
                    )
                }
            }
            Text(
                "Eğer çözümün burada yoksa alt taraftan ticket oluştur.",
                modifier = Modifier.Companion
                    .align(Alignment.Companion.CenterHorizontally),
                style = TextStyle(fontWeight = FontWeight.Companion.Bold)
            )
            Button(
                onClick = { ticketButtonClicked = !ticketButtonClicked },
                colors = ButtonDefaults.buttonColors(containerColor = AppColors.Third),
                modifier = Modifier.Companion.align(Alignment.Companion.CenterHorizontally)
                    .padding(top = 50.dp)
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
        if (ticketButtonClicked) {
            openUrl("https://selfservicedesk.damen.com/damen")
        }
    }
}