import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.yourapp.design.AppColors
import io.ktor.client.engine.cio.CIO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.nazlican.getImagePainter
import org.example.nazlican.getImagePainter2
import org.example.nazlican.getLanguageIcon

class LoginComponent(componentContext: ComponentContext, private val onLoginSuccess: () -> Unit) : ComponentContext by componentContext {

    @Composable
    fun Render() {
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Box(modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    LanguageSelectorIcon()
                }


            }
            Image(
                painter = getImagePainter2(),
                contentDescription = "Damen Logo",
                modifier = Modifier
                    .size(256.dp)
                    .align(Alignment.TopCenter)
                    .padding(top = 50.dp)
            )
            LoginInputs(
                username = username,
                password = password,
                onUsernameChange = {username = it},
                onPasswordChange = {password = it}
            )
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun LoginInputs(
        username: String,
        password: String,
        onUsernameChange: (String) -> Unit,
        onPasswordChange: (String) -> Unit
    ) {
        var passwordVisible by remember { mutableStateOf(false) }
        var isFormValid = username.isNotBlank() && password.isNotBlank()
        var showErrorDialog by remember { mutableStateOf(false) }
        var isLoading by remember {mutableStateOf(false)}
        val coroutineScope = rememberCoroutineScope()
        var errorMessageAlertDialog by remember { mutableStateOf<String>("") }

        if (showErrorDialog) {
            AlertDialog(
                onDismissRequest = {showErrorDialog = false},
                title = {Text("Hata",
                    color = AppColors.Secondary)},
                text = {Text("Kullanıcı adı ve şifre boş veya hatalı",
                    color = AppColors.Secondary)},
                confirmButton = {
                    Button(onClick = {showErrorDialog = false
                                     isLoading = false},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppColors.Third
                        )) {
                        Text("Tamam",
                            color = AppColors.Primary)
                    }
                },
                containerColor = AppColors.Primary
            )
        }

        if (errorMessageAlertDialog != "") {
            AlertDialog(
                onDismissRequest = { errorMessageAlertDialog = "" },
                title = {Text("Hata",
                    color = AppColors.Primary)},
                text = { Text(text = errorMessageAlertDialog,
                    color = AppColors.Primary) },
                confirmButton = {
                    Button(onClick = { errorMessageAlertDialog = "" },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppColors.Third
                        )) {
                        Text("Tamam",
                            color = AppColors.Primary)
                    }
                }
            )
        }
        Column {
            // Username
            OutlinedTextField(
                value = username,
                onValueChange = onUsernameChange,
                label = {Text("Kullanıcı Adı")},
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp, end = 40.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = AppColors.Secondary,
                    unfocusedContainerColor = AppColors.Secondary,
                    focusedLabelColor = AppColors.Primary,
                    cursorColor = AppColors.Primary,
                    focusedTextColor = AppColors.Primary,
                    focusedPlaceholderColor = AppColors.Primary,
                    unfocusedPlaceholderColor = AppColors.Primary,
                    unfocusedIndicatorColor = AppColors.Third,
                    focusedIndicatorColor = AppColors.Third
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = onPasswordChange,
                label = {Text("Şifre")},
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                    IconButton(onClick = {passwordVisible=!passwordVisible}) {
                        Icon(imageVector = icon, contentDescription = null)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp, end = 40.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = AppColors.Secondary,
                    unfocusedContainerColor = AppColors.Secondary,
                    focusedLabelColor = AppColors.Primary,
                    cursorColor = AppColors.Primary,
                    focusedTextColor = AppColors.Primary,
                    focusedPlaceholderColor = AppColors.Primary,
                    unfocusedIndicatorColor = AppColors.Third,
                    focusedIndicatorColor = AppColors.Third
                )
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = {
                    isLoading = true
                    coroutineScope.launch {
                        val respond = ApiService.login(username,password)

                            if (respond.success) {
                                    onLoginSuccess()
                                println("token is: ${respond.message}")
                            } else if (!respond.success) {
                            errorMessageAlertDialog = respond.message
                            isLoading = false
                            }
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 24.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AppColors.Primary)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        color = AppColors.Third,
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                } else Text("Giriş Yap")
            }
        }
    }
    @Composable
    public fun LanguageSelectorIcon() { // Dropdown Language Selector
        var expanded by remember {mutableStateOf(false)}

        Box {
            IconButton(onClick = {expanded = true},
                modifier = Modifier
                    .size(45.dp)
                    .padding(end = 20.dp)){
                Image(
                    painter = getLanguageIcon(),
                    contentDescription = "Language Icon"
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
