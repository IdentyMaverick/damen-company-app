import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.subscribeAsState

@Composable
fun AppRoot(root: RootComponent) {
    val stack by root.childStack.subscribeAsState()

    when (val child = stack.active.instance) {
        is RootComponent.Child.Splash -> child.component.Render()
        is RootComponent.Child.Login -> child.component.Render()
        is RootComponent.Child.Home -> child.component.Render()
    }
}