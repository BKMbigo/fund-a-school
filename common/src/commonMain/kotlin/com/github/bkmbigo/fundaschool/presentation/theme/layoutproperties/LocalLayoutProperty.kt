package com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import com.github.bkmbigo.fundaschool.presentation.theme.DefaultTypography
import com.github.bkmbigo.fundaschool.presentation.utils.Platform

val LocalLayoutProperty = staticCompositionLocalOf<LayoutProperties> { DefaultLocalProperties } // Consider migrating to compositionLocalOf()

val DefaultLocalProperties = object : LayoutProperties {
    override val TextStyle = object : LayoutProperties.TextStyleProperties {
        override val applicationTitle: TextStyle = DefaultTypography.displayMedium.copy(fontWeight = FontWeight.Bold)
        override val actionsTitle: TextStyle = DefaultTypography.titleMedium
        override val pageTitle: TextStyle = DefaultTypography.headlineLarge.copy(fontWeight = FontWeight.Bold)
        override val pageSubTitle: TextStyle = DefaultTypography.bodyLarge.copy(fontStyle = FontStyle.Italic)
        override val sectionTitle: TextStyle = DefaultTypography.titleLarge
        override val informationTitle: TextStyle = DefaultTypography.titleMedium
        override val informationEmphasis: TextStyle = DefaultTypography.titleSmall.copy(fontWeight = FontWeight.SemiBold)
        override val informationText: TextStyle = DefaultTypography.bodyMedium
        override val bodyEmphasis: TextStyle = DefaultTypography.bodySmall.copy(fontWeight = FontWeight.SemiBold)
        override val bodyText: TextStyle = DefaultTypography.bodySmall
        override val footerText: TextStyle = DefaultTypography.labelSmall
        override val dialogTitle: TextStyle = DefaultTypography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
        override val dialogAction: TextStyle = DefaultTypography.bodyMedium
        override val textLayoutHelper: TextStyle = DefaultTypography.bodySmall.copy(fontWeight = FontWeight.Bold)
    }
    override val platform = Platform.WEB
    override val screenWidth: Int = 0
}