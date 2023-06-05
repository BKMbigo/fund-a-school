package com.github.bkmbigo.fundaschool.presentation.components.date

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun DatePickerField(
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null
)