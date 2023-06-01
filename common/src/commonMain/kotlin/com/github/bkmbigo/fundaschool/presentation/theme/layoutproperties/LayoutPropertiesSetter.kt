package com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties

import androidx.compose.ui.text.font.FontFamily
import com.github.bkmbigo.fundaschool.presentation.utils.FormFactor

expect fun generateDefaultLayoutProperties(
    formFactor: FormFactor,
    exoFontFamily: FontFamily,
    robotoSansFontFamily: FontFamily
): LayoutProperties