// iosMain
import com.russhwolf.settings.Settings
import com.russhwolf.settings.AppleSettings
import platform.Foundation.NSUserDefaults

actual fun getSettings(): Settings {
    return AppleSettings(NSUserDefaults.standardUserDefaults)
}
