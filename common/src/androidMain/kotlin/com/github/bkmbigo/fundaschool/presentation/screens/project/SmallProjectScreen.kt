package com.github.bkmbigo.fundaschool.presentation.screens.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.github.bkmbigo.fundaschool.presentation.components.dialog.amountpicker.AmountPickerDialog
import com.github.bkmbigo.fundaschool.presentation.components.news.MediaImageView
import com.github.bkmbigo.fundaschool.presentation.components.project.ProjectInformationCard
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SmallProjectScreen(
    state: ProjectScreenState,
    onAction: (ProjectScreenAction) -> Unit
) {
    val layoutProperties = LocalLayoutProperty.current

    //State Variables
    var titleText by rememberSaveable(stateSaver = TextFieldValue.Saver, inputs = arrayOf(state.project)) { mutableStateOf(TextFieldValue(state.project.name)) }
    var textText by rememberSaveable(stateSaver = TextFieldValue.Saver, inputs = arrayOf(state.project)) { mutableStateOf(TextFieldValue(state.project.name)) }
    var targetAmountText by rememberSaveable(stateSaver = TextFieldValue.Saver, inputs = arrayOf(state.project)) { mutableStateOf(TextFieldValue(state.project.targetAmount.toString())) }

    var showEnterAmountDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            IconButton(
                onClick = { onAction(ProjectScreenAction.NavigateUp) },
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ChevronLeft,
                    contentDescription = null
                )
            }

            Box(
                modifier = Modifier.weight(1f, true)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            if (!state.isEditing) {
                Text(
                    text = state.project.name,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 4.dp),
                    style = layoutProperties.TextStyle.pageTitle
                )
            } else {
                OutlinedTextField(
                    value = titleText,
                    onValueChange = {
                        titleText = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textStyle = layoutProperties.TextStyle.pageTitle.copy(textAlign = TextAlign.Center),
                    shape = RoundedCornerShape(12.dp)
                )
            }

            if(state.isAdmin) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ElevatedSuggestionChip(
                        onClick = {
                              onAction(ProjectScreenAction.SaveProject(state.project.copy(featured = !state.project.featured)))
                        },
                        label = {
                            Text(
                                text = "Featured"
                            )
                        },
                        elevation = if(state.project.featured) {
                            SuggestionChipDefaults.elevatedSuggestionChipElevation(4.dp)
                        } else {
                            SuggestionChipDefaults.suggestionChipElevation(0.dp)
                        },
                        colors = SuggestionChipDefaults.elevatedSuggestionChipColors(
                            containerColor = if(state.project.featured) { MaterialTheme.colorScheme.primaryContainer } else { MaterialTheme.colorScheme.surface }
                        ),
                        icon = {
                            AnimatedVisibility(
                                visible = state.project.featured,
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
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null
                                )
                            }
                        },
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    Box(
                        modifier = Modifier.weight(1f, true)
                    )

                    TextButton(
                        onClick = {
                            if (state.isEditing) {
                                onAction(
                                    ProjectScreenAction.SaveProject(
                                        state.project.copy()
                                    )
                                )
                            } else {
                                onAction(ProjectScreenAction.ToggleIsEditing)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (state.isEditing) Icons.Default.Save else Icons.Default.Edit,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = if (state.isEditing) "Save Project" else "Edit Project"
                        )
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    IconButton(
                        onClick = {
                            onAction(ProjectScreenAction.DeleteProject)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null
                        )
                    }
                }
            }


            if(state.project.mediaUrl.isNotBlank()){
                MediaImageView(
                    mediaUrl = state.project.mediaUrl,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(vertical = 4.dp, horizontal = 4.dp)
                        .clip(RoundedCornerShape(12.dp)),
                )
            }


            if(!state.isEditing) {
                Text(
                    text = state.project.description,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    style = layoutProperties.TextStyle.bodyText
                )
            } else {
                OutlinedTextField(
                    value = textText,
                    onValueChange = {
                        textText = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textStyle = layoutProperties.TextStyle.pageSubTitle.copy(textAlign = TextAlign.Center),
                    shape = RoundedCornerShape(12.dp)
                )
            }

            Divider(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp))

            Text(
                text = "Information",
                style = layoutProperties.TextStyle.sectionTitle,
            )

            ProjectInformationCard(
                project = state.project,
                targetAmount = targetAmountText,
                onTargetAmountChanged = { targetAmountText = it },
                size = DpSize.Unspecified,
                modifier = Modifier
                    .width(IntrinsicSize.Max)
                    .height(IntrinsicSize.Min)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .align(Alignment.CenterHorizontally),
                isEditing = state.isEditing
            )

            Divider(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp))

            Text(
                text = "Donate",
                style = layoutProperties.TextStyle.sectionTitle
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = { showEnterAmountDialog = true }
                ) {
                    Icon(
                        imageVector = Icons.Default.Money,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = "Donate"
                    )
                }
            }


            Divider(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp))

            Text(
                text = "Media",
                style = layoutProperties.TextStyle.sectionTitle
            )

            Divider(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp))

            Text(
                text = "Top Donations",
                style = layoutProperties.TextStyle.sectionTitle
            )

        }
    }


    if(showEnterAmountDialog) {
        Dialog(
            onDismissRequest = { showEnterAmountDialog = false }
        ) {
            AmountPickerDialog(
                onDismissRequest = { showEnterAmountDialog = false },
                onAmountPicked = {
                    showEnterAmountDialog = false
                    onAction(ProjectScreenAction.DonateToProject(it))
                }
            )
        }
    }
}