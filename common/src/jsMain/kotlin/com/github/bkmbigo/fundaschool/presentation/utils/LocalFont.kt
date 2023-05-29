package com.github.bkmbigo.fundaschool.presentation.utils

import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

actual sealed class LocalFont(
    val fileName: String,
    actual val fontName: String,
    actual val fontStyle: FontStyle,
    actual val fontWeight: FontWeight
)

actual object EXO2_BOLD : LocalFont(
    "Exo2-Bold.ttf",
    "EXO2_BOLD",
    FontStyle.Normal,
    FontWeight.Bold
)

actual object EXO2_BOLD_ITALIC : LocalFont(
    "Exo2-Bold.ttf",
    "EXO2_BOLD_ITALIC",
    FontStyle.Italic,
    FontWeight.Bold
)

actual object EXO2_MEDIUM : LocalFont(
    "Exo2-Medium.ttf",
    "EXO2_MEDIUM",
    FontStyle.Normal,
    FontWeight.Medium
)

actual object EXO2_MEDIUM_ITALIC : LocalFont(
    "Exo2-MediumItalic.ttf",
    "EXO2_MEDIUM_ITALIC",
    FontStyle.Italic,
    FontWeight.Medium
)

actual object EXO2_REGULAR : LocalFont(
    "Exo2-Regular.ttf",
    "EXO2_REGULAR",
    FontStyle.Normal,
    FontWeight.Normal
)

actual object EXO2_SEMI_BOLD : LocalFont(
    "Exo2-SemiBold.ttf",
    "EXO2_SEMI_BOLD",
    FontStyle.Normal,
    FontWeight.SemiBold
)

actual object EXO2_SEMI_BOLD_ITALIC : LocalFont(
    "Exo2-SemiBoldItalic.ttf",
    "EXO2_SEMI_BOLD_ITALIC",
    FontStyle.Italic,
    FontWeight.SemiBold
)

actual object ROBOTO_SLAB_BLACK : LocalFont(
    "RobotoSlab-Black.ttf",
    "ROBOTO_SLAB_BLACK",
    FontStyle.Normal,
    FontWeight.Black
)

actual object ROBOTO_SLAB_BOLD : LocalFont(
    "RobotoSlab-Bold.ttf",
    "ROBOTO_SLAB_BOLD",
    FontStyle.Normal,
    FontWeight.Bold
)

actual object ROBOTO_SLAB_EXTRA_BOLD : LocalFont(
    "RobotoSlab-ExtraBold.ttf",
    "ROBOTO_SLAB_EXTRA_BOLD",
    FontStyle.Normal,
    FontWeight.ExtraBold
)

actual object ROBOTO_SLAB_EXTRA_LIGHT : LocalFont(
    "RobotoSlab-ExtraLight.ttf",
    "ROBOTO_SLAB_EXTRA_LIGHT",
    FontStyle.Normal,
    FontWeight.ExtraLight
)

actual object ROBOTO_SLAB_LIGHT : LocalFont(
    "RobotoSlab-Light.ttf",
    "ROBOTO_SLAB_LIGHT",
    FontStyle.Normal,
    FontWeight.Light
)

actual object ROBOTO_SLAB_MEDIUM : LocalFont(
    "RobotoSlab-Medium.ttf",
    "ROBOTO_SLAB_MEDIUM",
    FontStyle.Normal,
    FontWeight.Medium
)

actual object ROBOTO_SLAB_REGULAR : LocalFont(
    "RobotoSlab-Regular.ttf",
    "ROBOTO_SLAB_REGULAR",
    FontStyle.Normal,
    FontWeight.Normal
)

actual object ROBOTO_SLAB_SEMI_BOLD : LocalFont(
    "RobotoSlab-SemiBold.ttf",
    "ROBOTO_SLAB_SEMI_BOLD",
    FontStyle.Normal,
    FontWeight.SemiBold
)

actual object ROBOTO_SLAB_THIN : LocalFont(
    "RobotoSlab-Thin.ttf",
    "ROBOTO_SLAB_THIN",
    FontStyle.Normal,
    FontWeight.Thin
)