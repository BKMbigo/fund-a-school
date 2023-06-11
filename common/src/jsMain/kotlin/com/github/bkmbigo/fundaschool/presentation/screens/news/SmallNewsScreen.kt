package com.github.bkmbigo.fundaschool.presentation.screens.news

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.presentation.components.news.MediaImageView
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallNewsScreen(
    state: NewsScreenState,
    onAction: (NewsScreenAction) -> Unit
) {
    val layoutProperties = LocalLayoutProperty.current

    // State Variables
    var titleText by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        inputs = arrayOf(state.news.title)
    ) { mutableStateOf(TextFieldValue(state.news.title)) }
    var captionText by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        inputs = arrayOf(state.news.caption)
    ) { mutableStateOf(TextFieldValue(state.news.caption)) }
    var textText by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        inputs = arrayOf(state.news.text)
    ) { mutableStateOf(TextFieldValue(state.news.text)) }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            IconButton(
                onClick = { onAction(NewsScreenAction.NavigateUp) },
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

        LazyColumn(
            modifier = Modifier
                .weight(1f, true)
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            item {
                if (!state.isEditing) {
                    Text(
                        text = state.news.title,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                            .padding(vertical = 4.dp),
                        style = layoutProperties.TextStyle.pageTitle
                    )
                } else {
                    OutlinedTextField(
                        value = titleText,
                        onValueChange = {
                            titleText = it
                        },
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        textStyle = layoutProperties.TextStyle.pageTitle.copy(textAlign = TextAlign.Center),
                        shape = RoundedCornerShape(12.dp)
                    )
                }
            }

            item {
                if (!state.isEditing) {
                    Text(
                        text = state.news.caption,
                        modifier = Modifier.align(Alignment.Start).padding(vertical = 4.dp),
                        style = layoutProperties.TextStyle.pageSubTitle
                    )
                } else {
                    OutlinedTextField(
                        value = captionText,
                        onValueChange = {
                            captionText = it
                        },
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        textStyle = layoutProperties.TextStyle.pageSubTitle.copy(textAlign = TextAlign.Center),
                        shape = RoundedCornerShape(12.dp)
                    )
                }
            }
            item {
                Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp))
            }
            item {
                if (state.isAdmin) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = {
                                if (state.isEditing) {
                                    onAction(
                                        NewsScreenAction.SaveNews(
                                            state.news.copy(
                                                title = titleText.text,
                                                caption = captionText.text,
                                                text = textText.text,
                                                uploadDate = Clock.System.now()
                                                    .toLocalDateTime(TimeZone.currentSystemDefault()).date,

                                                )
                                        )
                                    )
                                } else {
                                    onAction(NewsScreenAction.ToggleIsEditing)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (state.isEditing) Icons.Default.Save else Icons.Default.Edit,
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = if (state.isEditing) "Save Article" else "Edit Article"
                            )
                        }

                        Spacer(modifier = Modifier.width(4.dp))

                        IconButton(
                            onClick = {
                                onAction(NewsScreenAction.DeleteNews)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
            item {
                Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp))
            }

            item {
                if (state.news.mediaUrl.isNotBlank()) {
                    MediaImageView(
                        mediaUrl = state.news.mediaUrl,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp, horizontal = 4.dp)
                            .clip(RoundedCornerShape(12.dp)),
                    )
                }
            }
            item {

                if (!state.isEditing) {
                    Text(
                        text = state.news.text,
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        style = layoutProperties.TextStyle.bodyText
                    )
                } else {
                    OutlinedTextField(
                        value = textText,
                        onValueChange = {
                            textText = it
                        },
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        textStyle = layoutProperties.TextStyle.pageSubTitle.copy(textAlign = TextAlign.Center),
                        shape = RoundedCornerShape(12.dp)
                    )
                }
            }
        }
    }
}