package com.github.bkmbigo.fundaschool.presentation.theme

import android.app.Activity
import android.os.Build
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.core.view.WindowCompat

@Composable
actual fun getDefaultColorScheme(
    isDarkTheme: Boolean,
    useDynamicColors: Boolean
): ColorScheme = when {
    useDynamicColors && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (isDarkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
    }

    isDarkTheme -> DarkColorScheme
    else -> LightColorScheme
}

@Composable
actual fun SetUpView() {
    val view = LocalView.current
    if (!view.isInEditMode) {
        val isDarkTheme = isSystemInDarkTheme()
        val color = colorScheme.background.toArgb()
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = color
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDarkTheme
        }
    }
}