package com.github.bkmbigo.fundaschool.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import com.github.bkmbigo.fundaschool.presentation.theme.typography.TypographyItem
import com.github.bkmbigo.fundaschool.presentation.theme.typography.generateDefaultTypography
import kotlinx.coroutines.async

@Composable
fun FundASchoolTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val materialDefaults = TypographyItem.getMaterialDefaults()

    var typographyItem by remember { mutableStateOf(materialDefaults) }

    LaunchedEffect(Unit) {
        val exoFamily = async { generateExoFontFamily() }
        val robotoSans = async { generateRobotoSansFontFamily() }

        val newTypographyItem = generateDefaultTypography(exoFamily.await(), robotoSans.await())
        typographyItem = newTypographyItem
    }

    SetUpView()

    MaterialTheme(
        colorScheme = getDefaultColorScheme(isDarkTheme),
        typography = typographyItem.toMaterialTypography(),
        content = content
    )
}


@Composable
expect fun getDefaultColorScheme(
    isDarkTheme: Boolean,
    useDynamicColors: Boolean = true
): ColorScheme

@Composable
expect fun SetUpView()