package com.github.bkmbigo.fundaschool.presentation.components.topbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LargeTopBar(
    onSearch: (String) -> Unit,
    onOpenDialog: () -> Unit,
    modifier: Modifier = Modifier
) {
    val layoutProperties = LocalLayoutProperty.current

    // State Properties
    var showSearchTextField by remember { mutableStateOf(false) }
    var searchText by rememberSaveable(stateSaver = TextFieldValue.Saver) { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Fund A School",
                style = layoutProperties.TextStyle.applicationTitle,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )

            Box(
                modifier = Modifier.weight(1f, true)
            )

            AnimatedVisibility(
                visible = showSearchTextField,
                enter = expandHorizontally(
                    animationSpec = spring(
                        stiffness = Spring.StiffnessLow
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
                OutlinedTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                    },
                    shape = RoundedCornerShape(12.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Search..."
                        )
                    },
                    trailingIcon = {
                        if (searchText.text.isNotBlank()) {
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
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant
                    )
                )

                Spacer(modifier = Modifier.width(4.dp))

                Button(
                    onClick = {
                        onSearch(searchText.text)
                        searchText = TextFieldValue("")
                    },
                    enabled = searchText.text.isNotBlank()
                ) {
                    Text(
                        text = "Search"
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))
            }

            Spacer(modifier = Modifier.width(4.dp))

            IconButton(
                onClick = {
                    showSearchTextField = !showSearchTextField
                }
            ) {
                Icon(
                    imageVector = if (showSearchTextField) Icons.Default.SearchOff else Icons.Default.Search,
                    contentDescription = null
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            IconButton(
                onClick = onOpenDialog
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            }

        }

    }
}