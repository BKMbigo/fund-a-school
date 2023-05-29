package com.github.bkmbigo.fundaschool.presentation.theme.typography

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import com.github.bkmbigo.fundaschool.presentation.theme.DefaultTypography

//val LocalTypography = staticCompositionLocalOf { TypographyItem.getMaterialDefaults(Typography()) }

fun generateDefaultTypography(
    exoFontFamily: FontFamily,
    robotoSansFontFamily: FontFamily
) : TypographyItem {
    val defaultTypography = TypographyItem.getMaterialDefaults(Typography())

    return TypographyItem.getMaterialDefaults(Typography()).copy(
        applicationTitle = defaultTypography.applicationTitle.copy(fontFamily = exoFontFamily),
        pageTitle = defaultTypography.pageTitle.copy(fontFamily = exoFontFamily),
        sectionTitle = defaultTypography.sectionTitle.copy(fontFamily = exoFontFamily),
        informationTitle = defaultTypography.informationTitle.copy(fontFamily = robotoSansFontFamily),
        informationText = defaultTypography.informationText.copy(fontFamily = robotoSansFontFamily),
        bodyText = defaultTypography.bodyText.copy(fontFamily = robotoSansFontFamily),
    )
}