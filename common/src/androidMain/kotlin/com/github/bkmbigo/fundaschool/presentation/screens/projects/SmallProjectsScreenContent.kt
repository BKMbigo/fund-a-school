package com.github.bkmbigo.fundaschool.presentation.screens.projects

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.github.bkmbigo.fundaschool.presentation.components.dialog.projectfilter.ProjectsFilterDialog
import com.github.bkmbigo.fundaschool.presentation.components.project.ProjectItem
import com.github.bkmbigo.fundaschool.presentation.screen.projects.ProjectsScreenAction
import com.github.bkmbigo.fundaschool.presentation.screen.projects.ProjectsScreenState
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty

@Composable
fun SmallProjectsScreenContent(
    state: ProjectsScreenState,
    onAction: (ProjectsScreenAction) -> Unit,
) {
    val layoutProperties = LocalLayoutProperty.current

    // State Variables
    var showFilterDialog by remember { mutableStateOf(false) }

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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
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
                            showFilterDialog = true
                        },
                        modifier = Modifier.weight(1f, true),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterAlt,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(text = "Filter")
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
                                stiffness = Spring.StiffnessVeryLow
                            ),
                            shrinkTowards = Alignment.End
                        )
                    ) {
                        TextButton(
                            onClick = {
                                onAction(ProjectsScreenAction.ClearFilters)
                            }
                        ) {
                            Text(text = "Clear")
                        }
                    }
                }

                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 4.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, true),
                    contentPadding = PaddingValues(vertical = 8.dp)
                ) {
                    items(state.projects) { projectItem ->
                        ProjectItem(
                            project = projectItem,
                            isProjectBookmarked = false,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            onProjectOpened = { ProjectsScreenAction.NavigateToProject(projectItem) },
                            onBookmarkChanged = { ProjectsScreenAction.BookmarkChange(projectItem) }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }

    // Project Filter Dialog
    if(showFilterDialog) {
        Dialog(
            onDismissRequest = { showFilterDialog = false }
        ) {
            ProjectsFilterDialog(
                state = state.filter,
                onFilterUpdated = {
                    onAction(ProjectsScreenAction.FilterProjects(it))
                    showFilterDialog = false
                },
                onDismissRequest = { showFilterDialog = false }
            )
        }
    }
}