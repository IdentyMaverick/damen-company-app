sealed class Screen {
    object Splash : Screen()
    object Login : Screen()
    object Home : Screen()
    object Feedback : Screen()
    object ITSupport : Screen()
    data class ITSupportsDetails(val questionsId: String, val questionsDescription: String) : Screen()
    data class News(val newsId: Int) : Screen()
    object EventCalendars : Screen()
}