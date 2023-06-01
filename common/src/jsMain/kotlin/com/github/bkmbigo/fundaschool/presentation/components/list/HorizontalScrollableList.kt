package com.github.bkmbigo.fundaschool.presentation.components.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

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
    val coroutineScope = rememberCoroutineScope()

    val lazyRowState = rememberLazyListState()

    val startScrollIndicatorsInteractionSource = remember { MutableInteractionSource() }
    val endScrollIndicatorsInteractionSource = remember { MutableInteractionSource() }

    var rowHeight by remember { mutableStateOf(0) }
    var isStartScrollIndicatorVisible by remember { mutableStateOf(false) }
    var isEndScrollIndicatorVisible by remember { mutableStateOf(false) }

    val areItemsPresentToTheStart by remember {
        derivedStateOf {
            lazyRowState.firstVisibleItemIndex > 0
        }
    }

    val areItemsPresentToTheEnd by remember {
        derivedStateOf {
            val layoutInfo = lazyRowState.layoutInfo
            val visibleItems = layoutInfo.visibleItemsInfo
            if(visibleItems.isNotEmpty()) {
                visibleItems.last().index < layoutInfo.totalItemsCount - 1
            } else false
        }
    }

    LaunchedEffect(Unit) {
        launch {
            startScrollIndicatorsInteractionSource.interactions.collect { interaction ->
                if (interaction is HoverInteraction) {
                    when (interaction) {
                        is HoverInteraction.Enter -> {
                            isStartScrollIndicatorVisible = true
                        }

                        is HoverInteraction.Exit -> {
                            isStartScrollIndicatorVisible = false
                        }
                    }
                }
            }
        }

        launch {
            endScrollIndicatorsInteractionSource.interactions.collect { interaction ->
                if(interaction is HoverInteraction) {
                    when (interaction) {
                        is HoverInteraction.Enter -> {
                            isEndScrollIndicatorVisible = true
                        }
                        is HoverInteraction.Exit -> {
                            isEndScrollIndicatorVisible = false
                        }
                    }
                }
            }
        }
    }

    Layout(
        modifier = modifier,
        content = {
            LazyRow(
                modifier = modifier
                    .onSizeChanged {
                        rowHeight = it.height
                    },
                state = lazyRowState,
                contentPadding = contentPadding,
                horizontalArrangement = horizontalArrangement,
                verticalAlignment = verticalAlignment,
                content = content
            )

            Box(
                modifier = Modifier
                    .hoverable(
                        startScrollIndicatorsInteractionSource,
                        areItemsPresentToTheStart
                    )
            ) {
                AnimatedVisibility(
                    visible = isStartScrollIndicatorVisible,
                    modifier = Modifier.align(Alignment.CenterStart),
                    enter = slideInHorizontally(
                        animationSpec = spring(
                            stiffness = Spring.StiffnessVeryLow,
                            dampingRatio = Spring.DampingRatioLowBouncy
                        ),
                        initialOffsetX = { -it }
                    ),
                    exit = slideOutHorizontally(
                        animationSpec = spring(
                            stiffness = Spring.StiffnessVeryLow,
                            dampingRatio = Spring.DampingRatioLowBouncy
                        ),
                        targetOffsetX = { -it }
                    )
                ) {
                    FilledIconButton(
                        onClick = {
                            coroutineScope.launch {
                                lazyRowState.animateScrollToItem(
                                    (lazyRowState.layoutInfo.visibleItemsInfo.first().index - lazyRowState.layoutInfo.visibleItemsInfo.size + 1).coerceAtLeast(0)
                                )
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ChevronLeft,
                            contentDescription = null
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .hoverable(
                        endScrollIndicatorsInteractionSource,
                        areItemsPresentToTheEnd
                    )
            ) {
                AnimatedVisibility(
                    visible = isEndScrollIndicatorVisible,
                    modifier = Modifier.align(Alignment.CenterEnd),
                    enter = slideInHorizontally(
                        animationSpec = spring(
                            stiffness = Spring.StiffnessLow,
                            dampingRatio = Spring.DampingRatioNoBouncy
                        ),
                        initialOffsetX = { it }
                    ),
                    exit = slideOutHorizontally(
                        animationSpec = spring(
                            stiffness = Spring.StiffnessMediumLow,
                            dampingRatio = Spring.DampingRatioNoBouncy
                        ),
                        targetOffsetX = { it }
                    )
                ) {
                    FilledIconButton(
                        onClick = {
                            coroutineScope.launch {
                                lazyRowState.animateScrollToItem(
                                    lazyRowState.layoutInfo.visibleItemsInfo.last().index
                                )
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ChevronRight,
                            contentDescription = null
                        )
                    }
                }
            }

        }
    ) { measurables, constraints ->

        val listMeasurable = measurables.first().measure(
            constraints.copy(
                minWidth = constraints.maxWidth,
                maxWidth = constraints.maxWidth
            )
        )
        val startIndicatorMeasurable = measurables[1].measure(
            Constraints.fixed(
                width = 50.dp.toPx().roundToInt(),
                height = listMeasurable.height,
            )
        )
        val endIndicatorMeasurable = measurables[2].measure(
            constraints.copy(
                minWidth = 50.dp.toPx().roundToInt(),
                maxWidth = 50.dp.toPx().roundToInt(),
                minHeight = listMeasurable.height,
                maxHeight = listMeasurable.height
            )
        )


        layout(constraints.maxWidth, listMeasurable.height) {
            listMeasurable.place(0,0)
            startIndicatorMeasurable.place(0,0)
            endIndicatorMeasurable.place(constraints.maxWidth - 50.dp.toPx().roundToInt(),0)
        }


    }
}