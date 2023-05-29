package com.github.bkmbigo.fundaschool.presentation.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.github.bkmbigo.fundaschool.presentation.utils.LocalFontFamily

actual suspend fun generateExoFontFamily(): FontFamily =
    FontFamily(
        LocalFontFamily.EXO.fonts.map {
            Font(
                resId = it.resourceId,
                weight = it.fontWeight,
                style = it.fontStyle
            )
        }
    )

actual suspend fun generateRobotoSansFontFamily(): FontFamily =
    FontFamily(
        LocalFontFamily.ROBOTO_SANS.fonts.map {
            Font(
                resId = it.resourceId,
                weight = it.fontWeight,
                style = it.fontStyle
            )
        }
    )