package com.github.bkmbigo.fundaschool.presentation.components.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.bkmbigo.fundaschool.domain.models.Project
import com.github.bkmbigo.fundaschool.presentation.components.news.MainMediaImageViewOptions
import com.github.bkmbigo.fundaschool.presentation.components.news.MediaImageView
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty
import com.github.bkmbigo.fundaschool.presentation.utils.applyCustomSize
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun featuredProject(
    project: Project,
    size: DpSize,
    modifier: Modifier = Modifier,
    isProjectBookmarked: Boolean = false,
    onProjectBookmarked: () -> Unit = {},
    onProjectOpened: () -> Unit = {}
) {
    val layoutProperties = LocalLayoutProperty.current

    val remainingDays = remember(project) {
        Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault()).date.toEpochDays() - project.completionDate.toEpochDays()
    }

    ElevatedCard(
        onClick = onProjectOpened,
        modifier = modifier.applyCustomSize(size),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, true)
        ) {
            if (project.media.isNotEmpty()) {
                MediaImageView(
                    media = project.media.first(),
                    modifier = Modifier.fillMaxSize(),
                    options = MainMediaImageViewOptions(ContentScale.Crop)
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.tertiaryContainer),
                )
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
                    remainingDays <= 90,
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
                    onClick = onProjectBookmarked
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
        ) {

            Text(
                text = project.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                style = layoutProperties.TextStyle.informationTitle
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.School,
                    contentDescription = null
                )

                if (project.schools.isNotEmpty()) {
                    Text(
                        text = project.schools.joinToString(", "),
                        style = MaterialTheme.typography.bodySmall
                    )
                } else {
                    Text(
                        text = "Unspecified",
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.bodySmall,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            Divider(modifier = Modifier.fillMaxWidth().padding(start = 36.dp, end = 8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = null
                )

                Text(
                    text = project.startDate.toString(),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Divider(modifier = Modifier.fillMaxWidth().padding(start = 36.dp, end = 8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Today,
                    contentDescription = null
                )

                Text(
                    text = project.completionDate.toString(),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${project.donors} donors",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp
            )

            ProjectDonationBar(
                project,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(12.dp)
                    .padding(end = 8.dp)
            )

            Text(
                text = "${project.currentAmount} out of ${project.targetAmount}",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp
            )
        }
    }
}