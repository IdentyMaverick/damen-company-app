// androidMain
package org.example.nazlican


import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource


@Composable
actual fun getLanguageIcon(): Painter {
    return painterResource(id = R.drawable.languageicon)
}

@Composable
actual fun getImagePainter(): Painter {
    return painterResource(id = R.drawable.damenlogowhite)
}

@Composable
actual fun getVersionNumber(): String? {
    val context = LocalContext.current
    return context.packageManager.getPackageInfo(context.packageName, 0).versionName
}

@Composable
actual fun getImagePainter2(): Painter {
    return painterResource(id = R.drawable.damenlogoblue)
}

@Composable
actual fun getLanguageIcon2(): Painter {
    return painterResource(id = R.drawable.languageiconwhite)
}