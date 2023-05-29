package com.github.bkmbigo.fundaschool.presentation.utils

import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

expect sealed class LocalFont{
    val fontName: String
    val fontStyle: FontStyle
    val fontWeight: FontWeight
}

expect object EXO2_BOLD : LocalFont
expect object EXO2_BOLD_ITALIC : LocalFont
expect object EXO2_MEDIUM : LocalFont
expect object EXO2_MEDIUM_ITALIC : LocalFont
expect object EXO2_REGULAR : LocalFont
expect object EXO2_SEMI_BOLD : LocalFont
expect object EXO2_SEMI_BOLD_ITALIC : LocalFont

expect object ROBOTO_SLAB_BLACK: LocalFont
expect object ROBOTO_SLAB_BOLD: LocalFont
expect object ROBOTO_SLAB_EXTRA_BOLD: LocalFont
expect object ROBOTO_SLAB_EXTRA_LIGHT: LocalFont
expect object ROBOTO_SLAB_LIGHT: LocalFont
expect object ROBOTO_SLAB_MEDIUM: LocalFont
expect object ROBOTO_SLAB_REGULAR: LocalFont
expect object ROBOTO_SLAB_SEMI_BOLD: LocalFont
expect object ROBOTO_SLAB_THIN: LocalFont