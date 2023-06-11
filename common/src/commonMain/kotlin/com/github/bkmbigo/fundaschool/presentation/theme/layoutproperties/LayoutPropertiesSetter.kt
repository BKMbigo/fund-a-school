package com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties

import androidx.compose.ui.text.font.FontFamily

expect fun generateDefaultLayoutProperties(
    screenWidth: Int,
    exoFontFamily: FontFamily,
    robotoSansFontFamily: FontFamily
): LayoutProperties