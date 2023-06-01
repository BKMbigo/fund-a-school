package com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.github.bkmbigo.fundaschool.presentation.utils.FormFactor

actual fun generateDefaultLayoutProperties(
    formFactor: FormFactor,
    exoFontFamily: FontFamily,
    robotoSansFontFamily: FontFamily
): LayoutProperties = object : LayoutProperties {

    val applicationTextStyle = TextStyle(
        fontFamily = exoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = when (formFactor) {
            FormFactor.PORTRAIT -> 24.sp
            FormFactor.LANDSCAPE -> 24.sp
        }
    )

    val actionTextStyle = TextStyle(
        fontFamily = exoFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = when (formFactor) {
            FormFactor.PORTRAIT -> 22.sp
            FormFactor.LANDSCAPE -> 24.sp
        }
    )

    val pageTextStyle = TextStyle(
        fontFamily = exoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = when (formFactor) {
            FormFactor.PORTRAIT -> 20.sp
            FormFactor.LANDSCAPE -> 24.sp
        }
    )

    val pageSubTextStyle = TextStyle(
        fontFamily = exoFontFamily,
        fontSize = when (formFactor) {
            FormFactor.PORTRAIT -> 14.sp
            FormFactor.LANDSCAPE -> 18.sp
        }
    )

    val sectionTitleTextStyle = TextStyle(
        fontFamily = exoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = when (formFactor) {
            FormFactor.PORTRAIT -> 16.sp
            FormFactor.LANDSCAPE -> 22.sp
        }
    )

    val informationTitleTextStyle = TextStyle(
        fontFamily = exoFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = when (formFactor) {
            FormFactor.PORTRAIT -> 14.sp
            FormFactor.LANDSCAPE -> 16.sp
        }
    )

    val informationTextStyle = TextStyle(
        fontFamily = robotoSansFontFamily,
        fontSize = when (formFactor) {
            FormFactor.PORTRAIT -> 11.sp
            FormFactor.LANDSCAPE -> 13.sp
        }
    )

    val informationEmphasisText = informationTextStyle.copy(fontWeight = FontWeight.SemiBold)

    val textLayoutHelperTextStyle = TextStyle(
        fontFamily = exoFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp
    )


    val bodyTextStyle = TextStyle(
        fontFamily = robotoSansFontFamily,
        fontSize = when (formFactor) {
            FormFactor.PORTRAIT -> 10.sp
            FormFactor.LANDSCAPE -> 10.sp
        }
    )


    val bodyEmphasisText = bodyTextStyle.copy(
        fontWeight = FontWeight.SemiBold
    )


    val footerTextStyle = TextStyle(
        fontFamily = exoFontFamily,
        fontSize = when (formFactor) {
            FormFactor.PORTRAIT -> 10.sp
            FormFactor.LANDSCAPE -> 10.sp
        }
    )

    val dialogTitleText = sectionTitleTextStyle.copy(fontWeight = FontWeight.SemiBold)
    val dialogActionText = TextStyle(
        fontFamily = exoFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = when (formFactor) {
            FormFactor.PORTRAIT -> 16.sp
            FormFactor.LANDSCAPE -> 16.sp
        }
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


    override val formFactor: FormFactor = formFactor


}