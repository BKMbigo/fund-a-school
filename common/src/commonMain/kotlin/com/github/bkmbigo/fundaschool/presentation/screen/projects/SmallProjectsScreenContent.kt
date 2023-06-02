package com.github.bkmbigo.fundaschool.presentation.screen.projects

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty

@Composable
fun SmallProjectsScreenContent(
    state: ProjectsScreenState,
    onAction: (ProjectsScreenAction) -> Unit,
) {
    val layoutProperties = LocalLayoutProperty.current

    Column {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                IconButton(
                    onClick = { onAction(ProjectsScreenAction.NavigateUp) },
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ChevronLeft,
                        contentDescription = null
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "Projects Screen",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = layoutProperties.TextStyle.pageTitle
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            /*TODO: Add Filter Dialog*/
                        },
                        modifier = Modifier.weight(1f, true)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Filter,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = "Filter"
                        )
                    }

                    AnimatedVisibility(
                        visible = state.filter.isActive(),
                        enter = expandHorizontally(
                            animationSpec = spring(
                                stiffness = Spring.StiffnessVeryLow
                            ),
                            expandFrom = Alignment.End
                        ),
                        exit = shrinkHorizontally(
                            animationSpec = spring(
                                stiffness = Spring.StiffnessLow
                            ),
                            shrinkTowards = Alignment.End
                        )
                    ) {
                        TextButton(
                            onClick = {
                                onAction(ProjectsScreenAction.ClearFilters)
                            }
                        ) {
                            Text(
                                text = "Clear"
                            )
                        }
                    }
                }

                Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(state.projects) { projectItem ->
                        Text(
                            text = projectItem.name
                        )
//                      /*TODO: Project View*/
                    }
                }
            }
        }
    }
}