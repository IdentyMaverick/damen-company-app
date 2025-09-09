import Screens.Feedback
import Screens.HomeComponent
import Screens.ITSupport
import Screens.ITSupportsDetails
import Screens.LoginComponent
import Screens.NewsDetails
import Screens.SplashComponent
import com.arkivanov.decompose.*
import com.arkivanov.decompose.router.stack.*
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value




import Screens.EventCalendar

class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Screen>()
    val settings = getSettings()
    val tokenSave = TokenSave(settings)

    init {
        ApiService.onSessionExpired = {onSessionExpired()}
    }

    private fun onSessionExpired() {
        tokenSave.token = null
        navigation.replaceAll(Screen.Login)
    }

    val childStack: Value<ChildStack<Screen, Child>> = childStack(
        source = navigation,
        serializer = null,
        initialConfiguration = Screen.Splash, // Başlangıç ekranı
        childFactory = ::createChild
    )

    private fun createChild(screen: Screen, context: ComponentContext): Child =
        when (screen) {
            is Screen.Splash -> Child.Splash(SplashComponent(context, onSplashFinished = {
                navigation.replaceCurrent(Screen.Login)
            }, onSplashFinished2 = { navigation.replaceCurrent(Screen.Home) }, tokenSave))

            is Screen.Login -> Child.Login(LoginComponent(context, onLoginSuccess = {
                navigation.replaceCurrent(Screen.Home)
            }, tokenSave))

            is Screen.Home -> Child.Home(
                HomeComponent(
                    context,
                onFeedbackClick = {
                    navigation.replaceCurrent(Screen.Feedback)
                }, onLogOut = {
                    navigation.replaceCurrent(Screen.Login)
                    tokenSave.token = null
                },
                onITSupportClick = {
                    navigation.replaceCurrent(Screen.ITSupport)
                },
                    onClickNewsDetail = { newsId ->
                        navigation.replaceCurrent( Screen.News( newsId ) ) },
                    onClickEventCalendar = {
                        navigation.replaceCurrent(Screen.EventCalendars)
                    }
            )
            )
            is Screen.Feedback -> Child.Feedbacks(
                Feedback(
                    context,
                    onFeedbackLeft = {
                        navigation.replaceCurrent(Screen.Home)
                    })
            )

            is Screen.ITSupport -> Child.ITSupports(
                ITSupport(
                    context,
                    onITSupportLeft = {
                        navigation.replaceCurrent(Screen.Home)
                    },
                    onITSupportButton = { id, description ->
                        navigation.push(Screen.ITSupportsDetails(id, description))
                    })
            )
            is Screen.ITSupportsDetails -> Child.ITSupportsDetail(
                ITSupportsDetails(
                    context,
                    screen.questionsId,
                    onFeedbackLefts = {
                        navigation.pop()
                    },
                    screen.questionsDescription
                )
            )
            is Screen.News -> Child.News(
                component = NewsDetails(
                    componentContext = context,
                    newsId = screen.newsId, // Haber id parametresini geçir
                    onNewsDetailsBack = {
                        navigation.replaceCurrent(Screen.Home) // Detaydan geri dönerken stack’i pop et
                    }
                )
            )
        is Screen.EventCalendars -> Child.EventCalendars (
            component = EventCalendar(
                componentContext = context,
                onEventCalendarBack = {
                    navigation.replaceCurrent( Screen.Home )
                },
                onFeedbackClick = {
                    navigation.replaceCurrent(Screen.Feedback)
                }
            )
        )
        }

    sealed class Child {
        class Splash(val component: SplashComponent) : Child()
        class Login(val component: LoginComponent) : Child()
        class Home(val component: HomeComponent) : Child()
        class Feedbacks(val component: Feedback) : Child()
        class ITSupports(val component: ITSupport) : Child()
        class ITSupportsDetail(val component: ITSupportsDetails) : Child()
        class News(val component: NewsDetails) : Child()
        class EventCalendars(val component: EventCalendar) : Child()
    }
}
