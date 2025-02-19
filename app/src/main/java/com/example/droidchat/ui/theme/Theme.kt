package com.example.droidchat.ui.theme

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.Window
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
fun DroidChatTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    useDynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        useDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            applyWindowInsets(window, view, colorScheme, darkTheme)
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MaterialTheme.typography.copy(
            displayLarge = DroidChatTypographyToken.heading1,
            displayMedium = DroidChatTypographyToken.heading2,
            displaySmall = DroidChatTypographyToken.heading3,
            bodyLarge = DroidChatTypographyToken.body1,
            bodyMedium = DroidChatTypographyToken.body2,
            labelSmall = DroidChatTypographyToken.caption
        ),
        content = content
    )
}

private fun applyWindowInsets(window: Window, view: View, colorScheme: ColorScheme, darkTheme: Boolean) {
    window.statusBarColor = colorScheme.primary.toArgb()
    WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
}
