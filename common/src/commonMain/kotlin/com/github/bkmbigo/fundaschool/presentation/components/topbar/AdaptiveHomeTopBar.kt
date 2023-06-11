package com.github.bkmbigo.fundaschool.presentation.components.topbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty
import com.github.bkmbigo.fundaschool.presentation.utils.Platform

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdaptiveHomeTopBar(
    onAction: (AdaptiveHomeTopBarAction) -> Unit,
    modifier: Modifier = Modifier
) {
    val layoutProperties = LocalLayoutProperty.current

    var showSearchField by remember { mutableStateOf(false) }
    var searchText by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Fund A School",
                modifier = Modifier
                    .padding(start = 12.dp)
                    .padding(vertical = 4.dp),
                style = layoutProperties.TextStyle.applicationTitle
            )

            Spacer(modifier = Modifier.width(8.dp))

            if (layoutProperties.platform == Platform.WEB) {
                TopBarActionsRow(
                    userIsAdmin = false,
                    onAction = onAction,
                    modifier = Modifier.fillMaxHeight().weight(1f, true)
                )
            } else {
                Box(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.width(4.dp))

            if(layoutProperties.screenWidth > 1080) {
                Column(
                    modifier = Modifier
                        .height(TextFieldDefaults.MinHeight + 4.dp)
                        .padding(vertical = 4.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    AnimatedVisibility(
                        visible = showSearchField,
                        enter = expandHorizontally(
                            animationSpec = spring(
                                stiffness = Spring.StiffnessVeryLow
                            ),
                            expandFrom = Alignment.End
                        ),
                        exit = shrinkHorizontally(
                            animationSpec = spring(
                                stiffness = Spring.StiffnessMediumLow
                            ),
                            shrinkTowards = Alignment.End
                        )
                    ) {
                        Row(
                            modifier = Modifier.height(IntrinsicSize.Min),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SearchField(
                                value = searchText,
                                onValueChange = {
                                    searchText = it
                                },
                                modifier = Modifier.padding(horizontal = 4.dp),
                                onAction = onAction
                            )

                            Button(
                                onClick = {
                                    if (searchText.text.isNotBlank()) {
                                        onAction(AdaptiveHomeTopBarAction.Search(searchText.text))
                                    }
                                },
                                enabled = searchText.text.isNotBlank(),
                                shape = RoundedCornerShape(5.dp)
                            ) {
                                Text(
                                    text = "Search"
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.width(4.dp))

            IconButton(
                onClick = {
                    showSearchField = !showSearchField
                },
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Icon(
                    imageVector = if(showSearchField) {
                        Icons.Default.SearchOff
                    } else {
                        Icons.Default.Search
                    },
                    contentDescription = null
                )
            }

            IconButton(
                onClick = { onAction(AdaptiveHomeTopBarAction.OpenActionsDialog) },
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            }
        }

        if(layoutProperties.screenWidth < 1080) {
            AnimatedVisibility(
                visible = showSearchField,
                enter = expandVertically(
                    animationSpec = spring(
                        stiffness = Spring.StiffnessVeryLow
                    ),
                    expandFrom = Alignment.Top
                ),
                exit = shrinkVertically(
                    animationSpec = spring(
                        stiffness = Spring.StiffnessMediumLow
                    ),
                    shrinkTowards = Alignment.Top
                )
            ) {
                Row(
                    modifier = Modifier.height(IntrinsicSize.Min),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SearchField(
                        value = searchText,
                        onValueChange = {
                            searchText = it
                        },
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
                        onAction = onAction
                    )


                    Button(
                        onClick = {
                            if(searchText.text.isNotBlank()) {
                                onAction(AdaptiveHomeTopBarAction.Search(searchText.text))
                            }
                        },
                        enabled = searchText.text.isNotBlank(),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Text(
                            text = "Search"
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    onAction: (AdaptiveHomeTopBarAction) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        placeholder = {
            Text(text = "Search...")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        trailingIcon = {
            if(value.text.isNotBlank()) {
                IconButton(
                    onClick = {
                        onValueChange(TextFieldValue(""))
                    },
                    modifier = Modifier.padding(horizontal = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Backspace,
                        contentDescription = null
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions {
            if(value.text.isNotBlank()) {
                onAction(AdaptiveHomeTopBarAction.Search(value.text))
            }
        }
    )
}