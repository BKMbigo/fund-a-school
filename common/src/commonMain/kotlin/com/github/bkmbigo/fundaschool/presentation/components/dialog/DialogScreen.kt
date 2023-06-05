package com.github.bkmbigo.fundaschool.presentation.components.dialog

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp

@Composable
fun DialogScreen(
    isDialogOpen: Boolean,
    modifier: Modifier = Modifier,
    dialogContent: @Composable BoxScope.() -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
    ) {
        val animatedBlur by animateDpAsState(
            targetValue = if (isDialogOpen) 8.dp else 0.dp,
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

        if (isDialogOpen) {
            Box(
                modifier = Modifier.align(Alignment.Center)
            ) {
                dialogContent()
            }
        }
    }
}