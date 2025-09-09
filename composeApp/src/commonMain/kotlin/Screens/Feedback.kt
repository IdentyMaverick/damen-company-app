package Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowLeft
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class Feedback(componentContext: ComponentContext, private val onFeedbackLeft: () -> Unit) : ComponentContext by componentContext {

    @Composable
    fun Render() {
        var selectedRating by remember { mutableStateOf(0) }
        println("selectedRating is: $selectedRating")
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
                // Ortalanmış Feedback yazısı
                Text(
                    text = "Geri Bildirim",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Companion.ExtraBold,
                    modifier = Modifier.Companion.align(Alignment.Companion.Center),
                    color = AppColors.Primary
                )

                // Sol tarafta ama kenara yapışık değil
                Row(
                    modifier = Modifier.Companion
                        .align(Alignment.Companion.CenterStart)
                        .padding(start = 16.dp),
                    verticalAlignment = Alignment.Companion.CenterVertically
                ) {
                    IconButton(
                        onClick = { onFeedbackLeft() },
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
                }
            }
            Text(
                "Deneyiminizi Puanlayın",
                style = TextStyle(fontSize = 18.sp, color = AppColors.Primary),
                modifier = Modifier.Companion
                    .align(Alignment.Companion.CenterHorizontally)
                    .padding(vertical = 25.dp)
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ) {
                for (i in 1..5) {
                    IconButton(
                        onClick = { selectedRating = i },
                        modifier = Modifier.Companion.size(40.dp)
                    ) {
                        Icon(
                            imageVector = if (i <= selectedRating) Icons.Filled.Star else Icons.Outlined.StarOutline,
                            contentDescription = "Yıldız $i",
                            modifier = Modifier.Companion.size(40.dp),
                            tint = AppColors.Primary
                        )
                    }
                }
            }
            HorizontalDivider(
                modifier = Modifier.Companion
                    .align(Alignment.Companion.CenterHorizontally)
                    .padding(horizontal = 40.dp, vertical = 20.dp),
                color = AppColors.Third,
            )
            Text(
                "Neleri iyileştirebiliriz?",
                style = TextStyle(
                    fontSize = 15.sp,
                    color = AppColors.Primary,
                    fontWeight = FontWeight.Companion.Bold
                ),
                modifier = Modifier.Companion
                    .padding(horizontal = 40.dp, vertical = 20.dp)
            )
            CommentBox(
                selectedRating = selectedRating,
                onSend = { comment ->
                    println("Yeni yorum: $comment")
                    selectedRating = 0
                },
            )
        }
    }
    @Composable
    fun CommentBox(
        selectedRating: Int,
        onSend: (String) -> Unit

    ) {
        var showDialog by remember { mutableStateOf(false) }
        var commentText by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.Companion
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = commentText,
                onValueChange = { commentText = it },
                placeholder = { Text("Yorumunuzu yazın") },
                modifier = Modifier.Companion
                    .fillMaxWidth()
                    .heightIn(min = 56.dp, max = 150.dp),
                maxLines = 5,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = AppColors.Secondary,
                    unfocusedIndicatorColor = AppColors.Primary,
                    unfocusedPlaceholderColor = AppColors.Primary,
                    focusedContainerColor = AppColors.Secondary,
                    focusedIndicatorColor = AppColors.Primary,
                    focusedPlaceholderColor = AppColors.Primary
                )
            )

            Spacer(Modifier.Companion.height(8.dp))

            Button(
                onClick = {
                    feedbacksSend(selectedRating, commentText)
                    if (commentText.isNotBlank()) {
                        onSend(commentText.trim())
                        commentText = ""
                    }
                        showDialog = true
                          },
                enabled = commentText.isNotBlank() && selectedRating >= 1,
                modifier = Modifier.Companion
                    .align(Alignment.Companion.CenterHorizontally)
                    .padding(top = 50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AppColors.Primary),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Gönder")
            }



            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        Button(onClick = { showDialog = false }) {
                            Text("Tamam")
                        }
                    },
                    title = { Text("Gönderim Başarılı") },
                    text = { Text("Geri Bildirim başarıyla gönderildi") }
                )
            }
            }
    }
    private fun feedbacksSend(point: Int, comment: String) {
        CoroutineScope(Dispatchers.IO).launch {
            ApiService.feedbacks(point, comment)
        }
    }
}