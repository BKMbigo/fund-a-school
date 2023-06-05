package com.github.bkmbigo.fundaschool.presentation.components.project

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.github.bkmbigo.fundaschool.domain.models.firebase.Project
import com.github.bkmbigo.fundaschool.domain.models.firebase.Sponsorship

@Composable
fun ProjectDonationBar(
    project: Project,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = MaterialTheme.colorScheme.surfaceVariant,
) {
    DonationBar(
        progress = (project.currentAmount / project.targetAmount).coerceIn(0.0f, 1.0f),
        modifier = modifier,
        color = color,
        trackColor = trackColor
    )
}

@Composable
fun SponsorshipDonationBar(
    sponsorship: Sponsorship,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = MaterialTheme.colorScheme.surfaceVariant
) {
    DonationBar(
        progress = (sponsorship.currentAmount / sponsorship.targetAmount).coerceIn(0.0f, 1.0f),
        modifier = modifier,
        color = color,
        trackColor = trackColor
    )
}

/**
 * @param progress Progress of the bar (from 0.0 to 1.0)
 */
@Composable
private fun DonationBar(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    trackColor: Color = MaterialTheme.colorScheme.surface,
) {
    val animatedSize by animateFloatAsState(
        targetValue = 1f,
        animationSpec = spring(
            stiffness = Spring.StiffnessVeryLow
        )
    )


    Canvas(
        modifier = modifier
    ) {
        drawRoundRect(
            color = trackColor,
            topLeft = Offset.Zero,
            size = size,
            cornerRadius = CornerRadius(10f, 10f)
        )

        drawRoundRect(
            color = color,
            topLeft = Offset.Zero,
            size = size * progress* animatedSize,
            cornerRadius = CornerRadius(10f, 10f)
        )
    }
}
