package com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import com.github.bkmbigo.fundaschool.presentation.utils.FormFactor

interface LayoutProperties {

    val TextStyle: TextStyleProperties
    val formFactor: FormFactor

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