package com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.sp
import com.github.bkmbigo.fundaschool.presentation.utils.Platform

actual fun generateDefaultLayoutProperties(
    screenWidth: Int,
    exoFontFamily: FontFamily,
    robotoSansFontFamily: FontFamily
): LayoutProperties = object : LayoutProperties {

    val applicationTextStyle = TextStyle(
        fontFamily = exoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    )

    val actionTextStyle = TextStyle(
        fontFamily = exoFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp
    )

    val pageTextStyle = TextStyle(
        fontFamily = exoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )

    val pageSubTextStyle = TextStyle(
        fontFamily = exoFontFamily,
        fontSize = 14.sp
    )

    val sectionTitleTextStyle = TextStyle(
        fontFamily = exoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    )

    val informationTitleTextStyle = TextStyle(
        fontFamily = exoFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize =  14.sp
    )

    val informationTextStyle = TextStyle(
        fontFamily = robotoSansFontFamily,
        fontSize = 11.sp
    )

    val informationEmphasisText = informationTextStyle.copy(fontWeight = FontWeight.SemiBold)

    val textLayoutHelperTextStyle = TextStyle(
        fontFamily = exoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    )


    val bodyTextStyle = TextStyle(
        fontFamily = robotoSansFontFamily,
        fontSize = 11.sp
    )


    val bodyEmphasisText = bodyTextStyle.copy(
        fontWeight = FontWeight.SemiBold
    )


    val footerTextStyle = TextStyle(
        fontFamily = exoFontFamily,
        fontSize = 10.sp
    )

    val dialogTitleText = sectionTitleTextStyle.copy(fontWeight = FontWeight.SemiBold)
    val dialogActionText = TextStyle(
        fontFamily = exoFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    )

    override val TextStyle = object : LayoutProperties.TextStyleProperties {
        override val applicationTitle: TextStyle = applicationTextStyle
        override val actionsTitle: TextStyle = actionTextStyle
        override val pageTitle: TextStyle = pageTextStyle
        override val pageSubTitle: TextStyle = pageSubTextStyle
        override val sectionTitle: TextStyle = sectionTitleTextStyle
        override val informationTitle: TextStyle = informationTitleTextStyle
        override val informationEmphasis: TextStyle = informationEmphasisText
        override val informationText: TextStyle = informationTextStyle
        override val bodyEmphasis: TextStyle = bodyEmphasisText
        override val bodyText: TextStyle = bodyTextStyle
        override val footerText: TextStyle = footerTextStyle
        override val dialogTitle: TextStyle = dialogTitleText
        override val dialogAction: TextStyle = dialogActionText
        override val textLayoutHelper: TextStyle = textLayoutHelperTextStyle
    }
    override val screenWidth: Int = screenWidth
    override val platform: Platform = Platform.ANDROID
}