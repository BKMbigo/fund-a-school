package com.github.bkmbigo.fundaschool.presentation.theme.typography

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.github.bkmbigo.fundaschool.presentation.theme.DefaultTypography

//val LocalTypography = staticCompositionLocalOf { TypographyItem.getMaterialDefaults(Typography()) }

@Stable
fun generateDefaultTypography(
    exoFontFamily: FontFamily,
    robotoSansFontFamily: FontFamily
) : TypographyItem {
    val defaultTypography = TypographyItem.getMaterialDefaults(Typography())

    return TypographyItem.getMaterialDefaults(Typography()).copy(
        applicationTitle = defaultTypography.applicationTitle.copy(
            fontFamily = exoFontFamily,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        ),
        pageTitle = defaultTypography.pageTitle.copy(
            fontFamily = exoFontFamily,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        ),
        sectionTitle = defaultTypography.sectionTitle.copy(
            fontFamily = exoFontFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        ),
        informationTitle = defaultTypography.informationTitle.copy(
            fontFamily = exoFontFamily,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        ),
        informationText = defaultTypography.informationText.copy(fontFamily = robotoSansFontFamily),
        bodyText = defaultTypography.bodyText.copy(
            fontFamily = robotoSansFontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light
        ),
    )
}