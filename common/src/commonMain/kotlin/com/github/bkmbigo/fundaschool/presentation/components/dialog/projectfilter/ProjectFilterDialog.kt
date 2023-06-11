package com.github.bkmbigo.fundaschool.presentation.components.dialog.projectfilter

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.presentation.components.dialog.DialogLayout
import com.github.bkmbigo.fundaschool.presentation.screens.projects.ProjectsFilter
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectsFilterDialog(
    state: ProjectsFilter,
    onFilterUpdated: (ProjectsFilter) -> Unit,
    onDismissRequest: () -> Unit
) {
    val layoutProperties = LocalLayoutProperty.current

    // State variables
    var searchText by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(state.searchText))
    }
    var projectStateFilter by remember(state) {
        mutableStateOf(
            if (!state.ongoingProjects) {
                ProjectStateFilter.COMPLETED
            } else if (!state.completedProjects) {
                ProjectStateFilter.ONGOING
            } else {
                ProjectStateFilter.BOTH
            }
        )
    }
    var startDate by remember(state) { mutableStateOf(state.startDate) }
    var endDate by remember(state) { mutableStateOf(state.endDate) }
    var showDateDialog by remember { mutableStateOf<DateDialogShown?>(null) }

    DialogLayout(
        onDismissRequest = onDismissRequest
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Search",
            style = layoutProperties.TextStyle.textLayoutHelper
        )
        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Search..."
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            trailingIcon = {
                if(searchText.text.isNotBlank()) {
                    IconButton(
                        onClick = {
                            searchText = TextFieldValue("")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Backspace,
                            contentDescription = null
                        )
                    }
                }
            },
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Project State",
            style = layoutProperties.TextStyle.textLayoutHelper
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ElevatedSuggestionChip(
                onClick = {
                    projectStateFilter = when(projectStateFilter) {
                        ProjectStateFilter.ONGOING -> {
                            ProjectStateFilter.ONGOING
                        }
                        ProjectStateFilter.COMPLETED -> {
                            ProjectStateFilter.BOTH
                        }
                        ProjectStateFilter.BOTH -> {
                            ProjectStateFilter.COMPLETED
                        }
                    }
                },
                label = {
                    Text(
                        text = "Ongoing"
                    )
                },
                icon = {
                    AnimatedVisibility(
                        visible = projectStateFilter != ProjectStateFilter.COMPLETED,
                        enter = expandHorizontally(
                            animationSpec = spring(
                                stiffness = Spring.StiffnessVeryLow
                            ),
                            expandFrom = Alignment.Start
                        ),
                        exit = shrinkHorizontally(
                            animationSpec = spring(
                                stiffness = Spring.StiffnessMediumLow
                            ),
                            shrinkTowards = Alignment.Start
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null
                        )
                    }
                },
                colors = SuggestionChipDefaults.elevatedSuggestionChipColors(
                    containerColor = if(projectStateFilter != ProjectStateFilter.COMPLETED) {
                        MaterialTheme.colorScheme.primaryContainer
                    } else {
                        MaterialTheme.colorScheme.surfaceVariant
                    },
                    labelColor = if(projectStateFilter != ProjectStateFilter.COMPLETED) {
                        MaterialTheme.colorScheme.onPrimaryContainer
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },
                    iconContentColor = if(projectStateFilter != ProjectStateFilter.COMPLETED) {
                        MaterialTheme.colorScheme.onPrimaryContainer
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }
                ),
                elevation = SuggestionChipDefaults.elevatedSuggestionChipElevation(
                    if(projectStateFilter != ProjectStateFilter.COMPLETED) 0.dp else 4.dp
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            ElevatedSuggestionChip(
                onClick = {
                    projectStateFilter = when(projectStateFilter) {
                        ProjectStateFilter.ONGOING -> {
                            ProjectStateFilter.BOTH
                        }
                        ProjectStateFilter.COMPLETED -> {
                            ProjectStateFilter.COMPLETED
                        }
                        ProjectStateFilter.BOTH -> {
                            ProjectStateFilter.ONGOING
                        }
                    }
                },
                label = {
                    Text(
                        text = "Completed"
                    )
                },
                icon = {
                    AnimatedVisibility(
                        visible = projectStateFilter != ProjectStateFilter.ONGOING,
                        enter = expandHorizontally(
                            animationSpec = spring(
                                stiffness = Spring.StiffnessVeryLow
                            ),
                            expandFrom = Alignment.Start
                        ),
                        exit = shrinkHorizontally(
                            animationSpec = spring(
                                stiffness = Spring.StiffnessMediumLow
                            ),
                            shrinkTowards = Alignment.Start
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null
                        )
                    }
                },
                colors = SuggestionChipDefaults.elevatedSuggestionChipColors(
                    containerColor = if(projectStateFilter != ProjectStateFilter.ONGOING) {
                        MaterialTheme.colorScheme.primaryContainer
                    } else {
                        MaterialTheme.colorScheme.surfaceVariant
                    },
                    labelColor = if(projectStateFilter != ProjectStateFilter.ONGOING) {
                        MaterialTheme.colorScheme.onPrimaryContainer
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },
                    iconContentColor = if(projectStateFilter != ProjectStateFilter.ONGOING) {
                        MaterialTheme.colorScheme.onPrimaryContainer
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    }
                ),
                elevation = SuggestionChipDefaults.elevatedSuggestionChipElevation(
                    if(projectStateFilter != ProjectStateFilter.ONGOING) 0.dp else 4.dp
                )
            )
        }

        /*TODO: Add Date Filters (hindered by lack of DatePickerDialog on js)*/

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text(
                    text = "Cancel"
                )
            }

            Button(
                onClick = {
                    onFilterUpdated(
                        ProjectsFilter(
                            searchText = searchText.text,
                            ongoingProjects = projectStateFilter != ProjectStateFilter.COMPLETED,
                            completedProjects = projectStateFilter != ProjectStateFilter.ONGOING,
                            startDate = startDate,
                            endDate = endDate
                        )
                    )
                }
            ) {
                Text(
                    text = "Save Filter"
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

private enum class ProjectStateFilter {
    ONGOING,
    COMPLETED,
    BOTH
}

private enum class DateDialogShown {
    START,
    END
}