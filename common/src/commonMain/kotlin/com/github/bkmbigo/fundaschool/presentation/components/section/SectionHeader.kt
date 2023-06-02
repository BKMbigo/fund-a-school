package com.github.bkmbigo.fundaschool.presentation.components.section

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SectionHeader(
    title: String,
    onSearch: (String) -> Unit,
    onAdd: () -> Unit,
    modifier: Modifier = Modifier,
    isAdmin: Boolean = false
) {
    val layoutProperties = LocalLayoutProperty.current

    var searchText by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }
    var showSearchField by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = layoutProperties.TextStyle.sectionTitle
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        showSearchField = !showSearchField
                    }
                ) {
                    Icon(
                        imageVector = if (showSearchField) {
                            Icons.Default.SearchOff
                        } else {
                            Icons.Default.Search
                        },
                        contentDescription = null
                    )
                }

                if(isAdmin) {
                    Spacer(modifier = Modifier.width(4.dp))

                    TextButton(
                        onClick = onAdd
                    ) {
                        Text(
                            text = "Add"
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.fillMaxWidth())

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
                    stiffness = Spring.StiffnessLow
                ),
                shrinkTowards = Alignment.Top
            )
        ) {
            Row {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        if (searchText.text.isNotBlank()) {
                            IconButton(
                                onClick = {

                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Backspace,
                                    contentDescription = null
                                )
                            }
                        }
                    },
                    placeholder = {
                        Text(
                            text = "Search..."
                        )
                    },
                    shape = RoundedCornerShape(16.dp),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions {
                        onSearch(searchText.text)
                    }
                )

                Spacer(modifier = Modifier.width(4.dp))

                TextButton(
                    onClick = {
                        onSearch(searchText.text)
                    },
                    enabled = searchText.text.isNotBlank()
                ) {
                    Text(text = "Search")
                }
            }
        }
    }
}