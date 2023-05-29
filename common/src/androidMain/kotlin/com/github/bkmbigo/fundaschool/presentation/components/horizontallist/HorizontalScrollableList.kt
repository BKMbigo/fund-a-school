package com.github.bkmbigo.fundaschool.presentation.components.horizontallist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Adds a scroll button on Web clients
 */
@Composable
actual fun HorizontalScrollableList(
    modifier: Modifier,
    contentPadding: PaddingValues,
    horizontalArrangement: Arrangement.Horizontal,
    verticalAlignment: Alignment.Vertical,
    content: LazyListScope.() -> Unit
) {
    LazyRow(
        modifier = modifier,
        contentPadding = contentPadding,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
        content = content
    )
}