package org.example.nazlican

import AppRoot
import RootComponent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import appContext
import com.arkivanov.decompose.DefaultComponentContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        appContext = this
        val root = RootComponent(DefaultComponentContext(lifecycle = lifecycle))

        setContent {
            AppRoot(root)
        }
    }
}