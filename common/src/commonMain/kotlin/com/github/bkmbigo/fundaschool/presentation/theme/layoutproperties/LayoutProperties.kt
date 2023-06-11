package com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties

import androidx.compose.ui.text.TextStyle
import com.github.bkmbigo.fundaschool.presentation.utils.Platform

interface LayoutProperties {

    val TextStyle: TextStyleProperties
    val screenWidth: Int
    val platform: Platform

    interface TextStyleProperties {
        val applicationTitle: TextStyle
        val actionsTitle: TextStyle
        val pageTitle: TextStyle
        val pageSubTitle: TextStyle
        val sectionTitle: TextStyle
        val informationTitle: TextStyle
        val informationEmphasis: TextStyle
        val informationText: TextStyle
        val bodyEmphasis: TextStyle
        val bodyText: TextStyle
        val footerText: TextStyle

        // Dialog
        val dialogTitle: TextStyle
        val dialogAction: TextStyle

        // EditText
        val textLayoutHelper: TextStyle
    }
}