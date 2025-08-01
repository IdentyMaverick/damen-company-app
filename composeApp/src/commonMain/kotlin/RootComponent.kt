import com.arkivanov.decompose.*
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.Value

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Screen>()

    val childStack: Value<ChildStack<Screen, Child>> = childStack(
        source = navigation,
        serializer = null,
        initialConfiguration = Screen.Splash, // Başlangıç ekranı
        childFactory = ::createChild
    )

    private fun createChild(screen: Screen, context: ComponentContext): Child =
        when (screen) {
            is Screen.Splash -> Child.Splash(SplashComponent(context) {
                navigation.replaceCurrent(Screen.Login)
            })

            is Screen.Login -> Child.Login(LoginComponent (context, onLoginSuccess = {
                navigation.replaceCurrent(Screen.Home)
            }))

            is Screen.Home -> Child.Home(HomeComponent(context,
                onFeedbackClick = {
                        navigation.replaceCurrent(Screen.Feedback)
                    }
                )
            )
            is Screen.Feedback -> Child.Feedbacks(Feedback(context,
                onFeedbackLeft = {
                    navigation.replaceCurrent(Screen.Home)
                }))
        }

    sealed class Child {
        class Splash(val component: SplashComponent) : Child()
        class Login(val component: LoginComponent) : Child()
        class Home(val component: HomeComponent) : Child()
        class Feedbacks(val component: Feedback) : Child()
    }
}
