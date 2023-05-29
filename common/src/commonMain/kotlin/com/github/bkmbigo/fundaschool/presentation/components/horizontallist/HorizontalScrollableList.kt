package com.github.bkmbigo.fundaschool.presentation.components.horizontallist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

/**
 * Adds a scroll button on Web clients
 */
@Composable
expect fun HorizontalScrollableList(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    content: LazyListScope.() -> Unit
)