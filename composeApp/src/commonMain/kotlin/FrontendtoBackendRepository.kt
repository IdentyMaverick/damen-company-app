import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class LoginResponse(val success: Boolean, val message: String)

@Serializable
data class UserCredentials(val username: String, val password: String)

object ApiService {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    suspend fun login(username: String, password: String): LoginResponse {
        return client.post("http://10.0.2.2:9090/login") {
            contentType(ContentType.Application.Json)
            setBody(UserCredentials(username, password))
        }.body()
    }
}
