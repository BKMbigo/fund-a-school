package com.github.bkmbigo.fundaschool.presentation.components.sidebar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// TODO: Expose as expect/actual as android devices do not need LARGE version
@Composable
fun HomeSidebar(
    onDismissRequest: () -> Unit,
    onAction: (HomeSidebarAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxHeight()
                .widthIn(min = 250.dp)
                .fillMaxSize(0.3f)
                .shadow(4.dp)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProvideTextStyle(MaterialTheme.typography.headlineLarge) {
                    Text(
                        text = "Fund A School",
                        modifier = Modifier.weight(1f, true),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                IconButton(
                    onClick = onDismissRequest
                ) {
                    Icon(
                        imageVector = Icons.Default.Cancel,
                        contentDescription = null
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp))

            Spacer(modifier = Modifier.height(4.dp))

            ProvideTextStyle(MaterialTheme.typography.titleLarge) {
                TextButton(
                    onClick = {
                        onAction(HomeSidebarAction.NavigateToHome)
                    }
                ) {
                    Text(
                        text = "Home"
                    )
                }

                Spacer(Modifier.height(4.dp))

                TextButton(
                    onClick = {
                        onAction(HomeSidebarAction.NavigateToProjects)
                    }
                ) {
                    Text(
                        text = "Projects"
                    )
                }

                Spacer(Modifier.height(4.dp))

                TextButton(
                    onClick = {
                        onAction(HomeSidebarAction.NavigateToDonations)
                    }
                ) {
                    Text(
                        text = "Donations"
                    )
                }

                Spacer(Modifier.height(4.dp))

                TextButton(
                    onClick = {
                        onAction(HomeSidebarAction.NavigateToAboutUs)
                    }
                ) {
                    Text(
                        text = "About Us"
                    )
                }
            }

            Box(
                modifier = Modifier.weight(1f, true)
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))


            ProvideTextStyle(MaterialTheme.typography.labelSmall) {
                Text(
                    text = "Fund A School\n\u00A9 2023",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    fontFamily = MaterialTheme.typography.headlineLarge.fontFamily,
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f, true)
                .clickable (
                    interactionSource = remember{ MutableInteractionSource() },
                    indication = null,
                    onClick = onDismissRequest
                )
        )
    }
}