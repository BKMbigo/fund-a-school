package com.github.bkmbigo.fundaschool.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontFamily
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.DefaultLocalProperties
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.generateDefaultLayoutProperties
import com.github.bkmbigo.fundaschool.presentation.theme.typography.TypographyItem
import com.github.bkmbigo.fundaschool.presentation.theme.typography.generateDefaultTypography
import com.github.bkmbigo.fundaschool.presentation.utils.FormFactor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun FundASchoolTheme(
    formFactorState: StateFlow<FormFactor> = MutableStateFlow(FormFactor.Default),
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val materialDefaults = TypographyItem.getMaterialDefaults()

    var exoFamily = remember<FontFamily?> { null }
    var robotoSans = remember<FontFamily?> { null }

    val formFactor by formFactorState.collectAsState()

    var typographyItem by remember { mutableStateOf(materialDefaults) }
    var layoutProperties by remember { mutableStateOf(DefaultLocalProperties) }

    LaunchedEffect(Unit) {
        // Make calls asynchronously
        exoFamily = generateExoFontFamily()
        robotoSans = generateRobotoSansFontFamily()
    }

    LaunchedEffect(exoFamily, robotoSans, formFactor) {
        val newTypographyItem = generateDefaultTypography(exoFamily ?: FontFamily.Serif, robotoSans ?: FontFamily.SansSerif)
        layoutProperties = generateDefaultLayoutProperties(formFactor, exoFamily ?: FontFamily.Serif, robotoSans ?: FontFamily.SansSerif)
        typographyItem = newTypographyItem
    }

    SetUpView()

    MaterialTheme(
        colorScheme = getDefaultColorScheme(isDarkTheme),
        typography = typographyItem.toMaterialTypography()
    ) {
        CompositionLocalProvider(
            LocalLayoutProperty provides layoutProperties,
            content = content
        )
    }
}


@Composable
expect fun getDefaultColorScheme(
    isDarkTheme: Boolean,
    useDynamicColors: Boolean = true
): ColorScheme

@Composable
expect fun SetUpView()