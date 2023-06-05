package com.github.bkmbigo.fundaschool.presentation.components.project

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.domain.models.firebase.Project
import com.github.bkmbigo.fundaschool.presentation.components.news.MediaImageView
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty
import com.github.bkmbigo.fundaschool.presentation.utils.applyCustomSize

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun recentlyEndedProject(
    project: Project,
    size: DpSize,
    modifier: Modifier = Modifier,
    onOpenProject: () -> Unit,
) {
    val layoutProperties = LocalLayoutProperty.current

    ElevatedCard(
        onClick = onOpenProject,
        modifier = modifier.applyCustomSize(size)
    ) {
        if(project.mediaUrl.isNotBlank()) {
            MediaImageView(
                mediaUrl = project.mediaUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, true),
                contentScale = ContentScale.FillHeight
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, true),
            )
        }

        Column(
            modifier = Modifier
                .weight(1f, true)
                .padding(horizontal = 8.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = project.name,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                style = layoutProperties.TextStyle.informationTitle
            )

            Spacer(modifier = Modifier.height(4.dp))

            ProjectDonationBar(
                project = project,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(4.dp))

            InformationRow(
                title = "Total Donations",
                value = project.currentAmount.toString(),
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            )

            Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp))

            InformationRow(
                title = "Target Amount",
                value = project.targetAmount.toString(),
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            )

            Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp))

            InformationRow(
                title = "Target Amount",
                value = project.targetAmount.toString(),
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            )



            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun InformationRow(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    val layoutProperties = LocalLayoutProperty.current

    Row(
        modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = layoutProperties.TextStyle.informationEmphasis
        )

        Text(
            text = value,
            style = layoutProperties.TextStyle.informationText
        )
    }
}