package com.github.bkmbigo.fundaschool.presentation.utils

import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.github.bkmbigo.fundaschool.R

actual sealed class LocalFont(
    val resourceId: Int,
    actual val fontName: String,
    actual val fontStyle: FontStyle,
    actual val fontWeight: FontWeight
)

actual object EXO2_BOLD : LocalFont(
    R.font.exo2_bold,
    "EXO2_BOLD",
    FontStyle.Normal,
    FontWeight.Bold
)

actual object EXO2_BOLD_ITALIC : LocalFont(
    R.font.exo2_bold_italic,
    "EXO2_BOLD_ITALIC",
    FontStyle.Italic,
    FontWeight.Bold
)

actual object EXO2_MEDIUM : LocalFont(
    R.font.exo2_medium,
    "EXO2_MEDIUM",
    FontStyle.Normal,
    FontWeight.Medium
)

actual object EXO2_MEDIUM_ITALIC : LocalFont(
    R.font.exo2_medium_italic,
    "EXO2_MEDIUM_ITALIC",
    FontStyle.Italic,
    FontWeight.Medium
)

actual object EXO2_REGULAR : LocalFont(
    R.font.exo2_regular,
    "EXO2_REGULAR",
    FontStyle.Normal,
    FontWeight.Normal
)

actual object EXO2_SEMI_BOLD : LocalFont(
    R.font.exo2_semi_bold,
    "EXO2_REGULAR",
    FontStyle.Normal,
    FontWeight.SemiBold
)

actual object EXO2_SEMI_BOLD_ITALIC : LocalFont(
    R.font.exo2_semi_bold_italic,
    "EXO2_REGULAR",
    FontStyle.Italic,
    FontWeight.SemiBold
)

actual object ROBOTO_SLAB_BLACK : LocalFont(
    R.font.roboto_slab_black,
    "ROBOTO_SLAB_BLACK",
    FontStyle.Normal,
    FontWeight.Black
)

actual object ROBOTO_SLAB_BOLD : LocalFont(
    R.font.roboto_slab_bold,
    "ROBOTO_SLAB_BOLD",
    FontStyle.Normal,
    FontWeight.Bold
)

actual object ROBOTO_SLAB_EXTRA_BOLD : LocalFont(
    R.font.roboto_slab_extra_bold,
    "ROBOTO_SLAB_EXTRA_BOLD",
    FontStyle.Normal,
    FontWeight.ExtraBold
)

actual object ROBOTO_SLAB_EXTRA_LIGHT : LocalFont(
    R.font.roboto_slab_extra_light,
    "ROBOTO_SLAB_EXTRA_LIGHT",
    FontStyle.Normal,
    FontWeight.ExtraLight
)

actual object ROBOTO_SLAB_LIGHT : LocalFont(
    R.font.roboto_slab_light,
    "ROBOTO_SLAB_LIGHT",
    FontStyle.Normal,
    FontWeight.Light
)

actual object ROBOTO_SLAB_MEDIUM : LocalFont(
    R.font.roboto_slab_medium,
    "ROBOTO_SLAB_MEDIUM",
    FontStyle.Normal,
    FontWeight.Medium
)

actual object ROBOTO_SLAB_REGULAR : LocalFont(
    R.font.roboto_slab_regular,
    "ROBOTO_SLAB_REGULAR",
    FontStyle.Normal,
    FontWeight.Normal
)

actual object ROBOTO_SLAB_SEMI_BOLD : LocalFont(
    R.font.roboto_slab_semi_bold,
    "ROBOTO_SLAB_SEMI_BOLD",
    FontStyle.Normal,
    FontWeight.SemiBold
)

actual object ROBOTO_SLAB_THIN : LocalFont(
    R.font.roboto_slab_thin,
    "ROBOTO_SLAB_THIN",
    FontStyle.Normal,
    FontWeight.Thin
)