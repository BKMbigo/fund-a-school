package com.github.bkmbigo.fundaschool.presentation.components.dialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp

@Composable
fun SidebarScreen(
    isSidebarOpen: Boolean = false,
    modifier: Modifier = Modifier,
    alignment: Alignment.Horizontal = Alignment.Start,
    sidebarContent: @Composable BoxScope.() -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
    ) {
        val animatedBlur by animateDpAsState(
            targetValue = if (isSidebarOpen) 8.dp else 0.dp,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy,
                stiffness = Spring.StiffnessHigh,
                visibilityThreshold = 1.dp
            )
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(
                    animatedBlur
                ),
            content = content
        )

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .align(
                    if (alignment == Alignment.End) {
                        Alignment.TopEnd
                    } else {
                        Alignment.TopStart
                    }
                )
        ) {
            AnimatedVisibility(
                visible = isSidebarOpen,
                enter = if (alignment == Alignment.End) {
                    slideInHorizontally(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioNoBouncy,
                            stiffness = Spring.StiffnessVeryLow
                        ),
                        initialOffsetX = { it }
                    )
                } else {
                    slideInHorizontally(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioNoBouncy,
                            stiffness = Spring.StiffnessVeryLow
                        ),
                        initialOffsetX = { -it }
                    )
                },
                exit = if (alignment == Alignment.End) {
                    slideOutHorizontally(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioNoBouncy,
                            stiffness = Spring.StiffnessMediumLow
                        ),
                        targetOffsetX = { it }
                    )
                } else {
                    slideOutHorizontally(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioNoBouncy,
                            stiffness = Spring.StiffnessMediumLow
                        ),
                        targetOffsetX = { -it }
                    )
                }
            ) {
                sidebarContent()
            }
        }
    }
}