package com.github.bkmbigo.fundaschool.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily

val DefaultTypography = Typography()

expect suspend fun generateExoFontFamily(): FontFamily
expect suspend fun generateRobotoSansFontFamily(): FontFamily