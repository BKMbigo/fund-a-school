package com.github.bkmbigo.fundaschool.presentation.components.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkRemove
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.domain.models.firebase.Project
import com.github.bkmbigo.fundaschool.presentation.components.news.MediaImageView
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProjectMediaView(
    project: Project,
    isProjectBookmarked: Boolean = false,
    modifier: Modifier = Modifier,
    onBookmarkChanged: () -> Unit,
) {
    val layoutProperties = LocalLayoutProperty.current

    val remainingDays = remember(project) {
        val days = Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .date.toEpochDays() - project.completionDate

        if (days in 1..59) {
            days
        } else null
    }

    Box(
        modifier = modifier
    ) {
        if (project.mediaUrl.isNotBlank()) {
            MediaImageView(
                mediaUrl = project.mediaUrl,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.tertiaryContainer),
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.BrokenImage,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "No Preview Available"
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 4.dp,
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimatedVisibility(
                remainingDays != null,
                enter = expandHorizontally(
                    animationSpec = spring(
                        stiffness = Spring.StiffnessVeryLow
                    ),
                    expandFrom = Alignment.Start,
                    initialWidth = { 0 }
                )
            ) {
                ElevatedAssistChip(
                    onClick = {},
                    label = {
                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = "$remainingDays days to go",
                            style = layoutProperties.TextStyle.bodyEmphasis
                        )
                    }
                )
            }

            Box(
                modifier = Modifier.weight(1f, true)
            )

            IconButton(
                onClick = onBookmarkChanged
            ) {
                Icon(
                    imageVector = if (isProjectBookmarked) Icons.Default.Bookmark
                    else Icons.Default.BookmarkRemove,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }

}