package com.github.bkmbigo.fundaschool.presentation.components.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@OptIn(ExperimentalFoundationApi::class)
@Composable
actual fun HorizontalPagerList(
    pageCount: Int,
    modifier: Modifier,
    contentPadding: PaddingValues,
    verticalAlignment: Alignment.Vertical,
    forceList: Boolean,
    pageSize: PageSize,
    pagerState: PagerState,
    pagerContent: @Composable (index: Int) -> Unit,
    listContent: LazyListScope.() -> Unit
) {
    if (forceList) {
        LazyRow(
            modifier = modifier,
            verticalAlignment = verticalAlignment,
            contentPadding = contentPadding,
            horizontalArrangement = Arrangement.Center,
            content = listContent
        )
    } else {
        HorizontalPager(
            pageCount,
            modifier,
            state = pagerState,
            pageSize = pageSize,
            contentPadding = contentPadding,
            verticalAlignment = verticalAlignment,
            pageContent = pagerContent
        )
    }
}