package com.github.bkmbigo.fundaschool.presentation.components.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.domain.models.firebase.Project
import com.github.bkmbigo.fundaschool.presentation.components.news.MediaImageView
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty
import com.github.bkmbigo.fundaschool.presentation.utils.applyCustomSize
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
internal fun bookmarkedProject(
    project: Project,
    size: DpSize,
    modifier: Modifier = Modifier
) {
    val layoutProperties = LocalLayoutProperty.current

    val remainingDays by remember {
        derivedStateOf {
            Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.toEpochDays()
            - project.completionDate
        }
    }

    ElevatedCard(
        modifier = modifier
            .applyCustomSize(size),
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f, true),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = project.name,
                    modifier = Modifier.fillMaxWidth(),
                    style = layoutProperties.TextStyle.informationTitle
                )

                Text(
                    text = if (remainingDays < 30) {
                        "$remainingDays remaining days"
                    } else {
                        "Completion date: ${project.completionDate}"
                    },
                    modifier = Modifier.fillMaxWidth(),
                    style = layoutProperties.TextStyle.bodyEmphasis
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            AnimatedVisibility(
                visible = project.mediaUrl.isNotEmpty(),
                modifier = Modifier.fillMaxHeight(),
                enter = expandHorizontally(
                    animationSpec = spring(
                        stiffness = Spring.StiffnessLow
                    ),
                    expandFrom = Alignment.End
                ),
            ) {
                if (project.mediaUrl.isNotBlank()) {
                    MediaImageView(
                        mediaUrl = project.mediaUrl,
                        modifier = Modifier.fillMaxHeight(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}