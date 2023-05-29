package com.github.bkmbigo.fundaschool.presentation.theme.typography

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight

data class TypographyItem(
    val applicationTitle: TextStyle,
    val pageTitle: TextStyle,
    val sectionTitle: TextStyle,

    val informationTitle: TextStyle,
    val informationText: TextStyle,

    val bodyText: TextStyle,
    val footerText: TextStyle
) {

    fun toMaterialTypography() = Typography().copy(
        displayMedium = applicationTitle,
        headlineLarge = pageTitle,
        titleLarge = sectionTitle,

        titleMedium = informationTitle,
        bodyMedium = informationText,

        bodySmall = bodyText,
        labelSmall = footerText
    )

    companion object {
        @Composable
        fun getMaterialDefaults(): TypographyItem = TypographyItem(
            applicationTitle = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            pageTitle = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            sectionTitle = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
            informationTitle = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            informationText = MaterialTheme.typography.bodyMedium,
            bodyText = MaterialTheme.typography.bodySmall,
            footerText = MaterialTheme.typography.labelSmall
        )

        fun getMaterialDefaults(typography: Typography = Typography()): TypographyItem = TypographyItem(
            applicationTitle = typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            pageTitle = typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            sectionTitle = typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
            informationTitle = typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            informationText = typography.bodyMedium,
            bodyText = typography.bodySmall,
            footerText = typography.labelSmall
        )
    }
}