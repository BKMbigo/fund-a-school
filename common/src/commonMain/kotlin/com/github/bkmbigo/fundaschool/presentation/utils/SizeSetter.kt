package com.github.bkmbigo.fundaschool.presentation.utils

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize

/**
 * Used when size of a composable needs to e set explicitly
 * [Dp.Infinity] fills the whole size
 * [Dp.Unspecified] does not set size
 * @param size DpSize
 */
fun Modifier.applyCustomSize(size: DpSize): Modifier =
    then(
        when(size.height) {
            Dp.Infinity -> Modifier.fillMaxHeight()
            Dp.Unspecified -> Modifier
            else -> Modifier.height(size.height)
        }
    ).then(
        when(size.width) {
            Dp.Infinity -> Modifier.fillMaxWidth()
            Dp.Unspecified -> Modifier
            else -> Modifier.width(size.width)
        }
    )