package com.github.bkmbigo.fundaschool.presentation.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val lightBackgroundColor = Color(0xFFF9FFF9)
private val lightSurfaceColor = Color(0xFFEBFCF0)
private val lightPrimaryColor = Color(0xFF006E14)
private val lightPrimaryContainerColor = Color(0xFF92FA88)
private val lightOnPrimaryContainerColor = Color(0xFF002202)
private val lightSecondaryColor = Color(0xFF006A66)
private val lightSecondaryContainerColor = Color(0xFF70F7F0)

private val darkBackgroundColor = Color(0xFF1A1C18)
private val darkSurfaceColor = Color(0xFF121410)
private val darkPrimaryColor = Color(0xFF77DD6F)
private val darkPrimaryContainerColor = Color(0xFF00530C)
private val darkSecondaryColor = Color(0xFF4EDAD3)
private val darkSecondaryContainerColor = Color(0xFF00504D)


val LightColorScheme = lightColorScheme(
    primary = lightPrimaryColor,
    primaryContainer = lightPrimaryContainerColor,
    onPrimaryContainer = lightOnPrimaryContainerColor,
    secondary = lightSecondaryColor,
    secondaryContainer = lightSecondaryContainerColor,
    background = lightBackgroundColor,
    surface = lightSurfaceColor
)

val DarkColorScheme = darkColorScheme(
    primary = darkPrimaryColor,
    primaryContainer = darkPrimaryContainerColor,
    secondary = darkSecondaryColor,
    secondaryContainer = darkSecondaryContainerColor,
    background = darkBackgroundColor,
    surface = darkSurfaceColor
)