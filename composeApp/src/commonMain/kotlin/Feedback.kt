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
import androidx.compose.material.icons.filled.Api
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Forum
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.ComponentContext
import com.yourapp.design.AppColors

class Feedback(componentContext: ComponentContext, private val onFeedbackLeft: () -> Unit) : ComponentContext by componentContext {

    @Composable
    fun Render() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp) // sabit yükseklik opsiyonel
                    .padding(top = 50.dp)
            ) {
                // Ortalanmış Feedback yazısı
                Text(
                    text = "Geri Bildirim",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.align(Alignment.Center),
                    color = AppColors.Primary
                )

                // Sol tarafta ama kenara yapışık değil
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { onFeedbackLeft() }, modifier = Modifier.size(60.dp)) {
                        Icon(
                            Icons.Default.ArrowLeft,
                            contentDescription = null,
                            tint = AppColors.Primary,
                            modifier = Modifier
                                .size(40.dp)
                        )
                    }
                }
            }
            Text("Deneyiminizi Puanlayın",
                style = TextStyle(fontSize = 18.sp, color = AppColors.Primary),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 25.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
            ) {
                IconButton(onClick = {  }, modifier = Modifier.size(40.dp)) {
                    Icon(
                        Icons.Outlined.StarOutline,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp),
                        tint = AppColors.Primary
                    )
                }
                IconButton(onClick = {  }, modifier = Modifier.size(40.dp)) {
                    Icon(
                        Icons.Outlined.StarOutline,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp),
                        tint = AppColors.Primary
                    )
                }
                IconButton(onClick = {  }, modifier = Modifier.size(40.dp)) {
                    Icon(
                        Icons.Outlined.StarOutline,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp),
                        tint = AppColors.Primary
                    )
                }
                IconButton(onClick = {  }, modifier = Modifier.size(40.dp)) {
                    Icon(
                        Icons.Outlined.StarOutline,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp),
                        tint = AppColors.Primary
                    )
                }
                IconButton(onClick = {  }, modifier = Modifier.size(40.dp)) {
                    Icon(
                        Icons.Outlined.StarOutline,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp),
                        tint = AppColors.Primary
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 40.dp, vertical = 20.dp),
                color = AppColors.Third,
            )
        Text("Neleri iyileştirebiliriz?",
            style = TextStyle(fontSize = 15.sp, color = AppColors.Primary),
            modifier = Modifier
                .padding(horizontal = 40.dp, vertical = 20.dp))
            CommentBox(
                onSend = {comment ->
                    println("Yeni yorum: $comment")
                }
            )
        }
    }
    @Composable
    fun CommentBox(
        onSend: (String) -> Unit
    ) {
        var commentText by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = commentText,
                onValueChange = {commentText = it},
                placeholder = {Text("Yorumunuzu yazın")},
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 56.dp, max = 150.dp),
                maxLines = 5,
                colors = TextFieldDefaults.colors(unfocusedContainerColor = AppColors.Secondary,
                    unfocusedIndicatorColor = AppColors.Primary,
                    unfocusedPlaceholderColor = AppColors.Primary,
                    focusedContainerColor = AppColors.Secondary,
                    focusedIndicatorColor = AppColors.Primary,
                    focusedPlaceholderColor = AppColors.Primary)
            )

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = {
                    if (commentText.isNotBlank()) {
                        onSend(commentText.trim())
                        commentText = ""
                    }
                },
                enabled = commentText.isNotBlank(),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AppColors.Primary),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text("Gönder")
            }
        }
    }
}