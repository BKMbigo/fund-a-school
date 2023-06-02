package com.github.bkmbigo.fundaschool.presentation.components.project

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.domain.models.Project
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProjectListItem(
    project: Project,
    isProjectBookmarked: Boolean = false,
    modifier: Modifier = Modifier,
    onProjectOpened: () -> Unit,
    onBookmarkChanged: () -> Unit
) {
    val layoutProperties = LocalLayoutProperty.current

    ElevatedCard(
        onClick = onProjectOpened,
        modifier = modifier
    ) {
        ProjectMediaView(
            project,
            isProjectBookmarked,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, true)
                .padding(4.dp),
            onBookmarkChanged = onBookmarkChanged
        )

        Column (
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
        ) {
            Text(
                text = project.name,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = layoutProperties.TextStyle.informationTitle
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Schools",
                    style = layoutProperties.TextStyle.informationEmphasis
                )

                Box(
                    modifier = Modifier.weight(1f, true)
                )

                Text(
                    text = project.schools.joinToString(),
                    style = layoutProperties.TextStyle.informationText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.weight(1f, true),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = "Start Date",
                            style = layoutProperties.TextStyle.informationEmphasis
                        )
                    }
                    Text(
                        text = project.startDate.toString(),
                        modifier = Modifier.weight(1f, true),
                        style = layoutProperties.TextStyle.informationText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Divider(modifier = Modifier.fillMaxHeight().padding(horizontal = 4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier.weight(1f, true),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Today,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(2.dp))
                            Text(
                                text = "Completion Date",
                                style = layoutProperties.TextStyle.informationEmphasis
                            )
                        }
                        Text(
                            text = project.startDate.toString(),
                            style = layoutProperties.TextStyle.informationText,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            ProjectDonationBar(
                project,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 2.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Donors: ${project.donors}",
                    maxLines = 1,
                    style = layoutProperties.TextStyle.footerText
                )

                Box(modifier = Modifier.weight(1f, true))

                Text(
                    text = "${project.currentAmount} out of ${project.targetAmount}",
                    maxLines = 1,
                    style = layoutProperties.TextStyle.footerText
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
    }
}