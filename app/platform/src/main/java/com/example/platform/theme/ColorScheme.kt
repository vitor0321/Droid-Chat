package com.example.platform.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

public val DarkColorScheme = darkColorScheme(
    primary = Turquoise80,
    onPrimary = Color.White,
    primaryContainer = Turquoise30,
    onPrimaryContainer = Turqouise90,
    secondary = Green80,
    onSecondary = Green20,
    tertiary = Wildfire80,
    onTertiary = Wildfire20,
    surface = Neutral10,
    onSurface = Neutral90,
    onSurfaceVariant = Neutral60,
    inverseSurface = Color.White,
    inverseOnSurface = Color.Black,
    error = ColorError,
)

public val LightColorScheme = lightColorScheme(
    primary = Turquoise80,
    onPrimary = Color.White,
    primaryContainer = Turqouise90,
    onPrimaryContainer = Turqouise10,
    secondary = Green40,
    onSecondary = Color.Black,
    tertiary = Wildfire90,
    onTertiary = Color.Black,
    surface = Surface,
    onSurface = Neutral10,
    onSurfaceVariant = Neutral60,
    inverseSurface = Color.Black,
    inverseOnSurface = Color.White,
    error = ColorError,
)