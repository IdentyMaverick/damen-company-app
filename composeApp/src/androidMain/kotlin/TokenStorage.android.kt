import android.annotation.SuppressLint
import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

@SuppressLint("StaticFieldLeak")
lateinit var androidContext: Context

// commonMain
actual fun getSettings(): Settings = SharedPreferencesSettings(
    androidContext.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
)
