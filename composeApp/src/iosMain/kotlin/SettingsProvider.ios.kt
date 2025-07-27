import com.russhwolf.settings.Settings
import com.russhwolf.settings.NSUserDefaultsSettings
import platform.Foundation.NSUserDefaults

actual fun getSettings(): Settings {
    val delegate = NSUserDefaults.standardUserDefaults
    return NSUserDefaultsSettings(delegate)
}
