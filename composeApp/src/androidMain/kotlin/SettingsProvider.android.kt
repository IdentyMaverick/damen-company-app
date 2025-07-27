import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

lateinit var appContext: Context

actual fun getSettings(): Settings =
    SharedPreferencesSettings(appContext.getSharedPreferences("app_prefs", Context.MODE_PRIVATE))
