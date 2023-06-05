package com.github.bkmbigo.fundaschool.presentation.components.project

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.bkmbigo.fundaschool.domain.models.firebase.Project
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty
import com.github.bkmbigo.fundaschool.presentation.utils.applyCustomSize

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

    ElevatedCard(
        onClick = onProjectOpened,
        modifier = modifier.applyCustomSize(size),
    ) {
        ProjectMediaView(
            project,
            isProjectBookmarked = isProjectBookmarked,
            modifier = Modifier.fillMaxWidth().weight(1f, true),
            onBookmarkChanged = onProjectBookmarked
        )

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
                textAlign = TextAlign.Center,
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
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )

                if (project.schools.isNotEmpty()) {
                    Text(
                        text = project.schools,
                        style = layoutProperties.TextStyle.informationText,
                        textAlign = TextAlign.End,
                    )
                } else {
                    Text(
                        text = "Unspecified",
                        textAlign = TextAlign.End,
                        style = layoutProperties.TextStyle.informationText,
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
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )

                Text(
                    text = project.startDate.toString(),
                    textAlign = TextAlign.End,
                    style = layoutProperties.TextStyle.informationText
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
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )

                Text(
                    text = project.completionDate.toString(),
                    textAlign = TextAlign.End,
                    style = layoutProperties.TextStyle.informationText
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
                    .padding(vertical = 2.dp)
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