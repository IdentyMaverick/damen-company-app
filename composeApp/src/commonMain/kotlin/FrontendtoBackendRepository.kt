import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.call.*
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class LoginResponse(val success: Boolean, val message: String)

@Serializable
data class UserCredentials(val username: String, val password: String)

@Serializable
data class NewsResponse(val id: Int, val title: String, val content: String, val imageUrl: String)

@Serializable
data class CalendarsResponse(val id: Int, val title: String, val description: String, val date: String)

@Serializable
data class FeedbacksResponse(val point: Int, val comment: String)
object ApiService {
    var onSessionExpired: (() -> Unit)? = null
    val settings = getSettings()
    val tokenSave = TokenSave(settings)
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(io.ktor.client.plugins.HttpCallValidator) {
            handleResponseExceptionWithRequest { exception, _ ->
                if (exception is ClientRequestException && exception.response.status.value == 401) {
                    // Token süresi dolmuş
                    tokenSave.token = null
                    onSessionExpired?.invoke()
                }
            }
        }
    }

    suspend fun login(username: String, password: String): LoginResponse {
        return client.post("http://192.168.0.246:9090/login") {
            contentType(ContentType.Application.Json)
            setBody(UserCredentials(username, password))
        }.body()
    }

    suspend fun news(): List<NewsResponse> {
        return client.get("http://192.168.0.246:9090/news") {
            contentType(ContentType.Application.Json)
        }.body()
    }

    suspend fun newsSpecific(newsId: Int): NewsResponse {
        return client.get("http://192.168.0.246:9090/news/$newsId") {
            contentType(ContentType.Application.Json)
        }.body()
    }

    suspend fun calendars(): List<CalendarsResponse> {
        return client.get("http://192.168.0.246:9090/calendars") {
            contentType(ContentType.Application.Json)
        }.body()
    }

    suspend fun feedbacks(point: Int, comment: String): FeedbacksResponse {
        return client.post("http://192.168.0.246:9090/feedbacks") {
            contentType(ContentType.Application.Json)
            setBody(FeedbacksResponse(point, comment))
        }.body()
    }
}
