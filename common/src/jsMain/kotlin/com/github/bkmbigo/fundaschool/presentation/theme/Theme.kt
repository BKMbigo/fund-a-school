package com.github.bkmbigo.fundaschool.presentation.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable

@Composable
actual fun getDefaultColorScheme(isDarkTheme: Boolean, useDynamicColors: Boolean): ColorScheme {
    return if(isDarkTheme){
        DarkColorScheme
    } else {
        LightColorScheme
    }
}

@Composable
actual fun SetUpView() {
    // NO-OP
}