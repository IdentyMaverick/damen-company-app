import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import com.russhwolf.settings.get

class TokenSave(private val settings: Settings) {

    companion object {
        private const val KEY_TOKEN = "token"
    }

    var token: String?
        get() = settings.getStringOrNull(KEY_TOKEN)
        set(value) {
            if (value == null) {
                settings.remove(KEY_TOKEN)
            } else {
                settings[KEY_TOKEN] = value
            }
        }

}