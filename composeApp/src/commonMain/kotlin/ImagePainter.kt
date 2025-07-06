// commonMain

package org.example.nazlican

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

@Composable
expect fun getLanguageIcon(): Painter  // Language Icon Getter

@Composable
expect fun getImagePainter(): Painter  // Damen White Logo Getter

@Composable
expect fun getVersionNumber(): String?  // App Version Number Getter

@Composable
expect fun getImagePainter2(): Painter // Damen Blue Logo Getter

@Composable
expect fun getLanguageIcon2(): Painter // Damen Blue Logo Getter