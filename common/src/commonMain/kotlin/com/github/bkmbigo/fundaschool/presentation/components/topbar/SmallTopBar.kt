package com.github.bkmbigo.fundaschool.presentation.components.topbar

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallTopBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {},
    onOpenDialog: () -> Unit = {}
) {
    val layoutProperties = LocalLayoutProperty.current

    var isSearchOn by remember { mutableStateOf(false) }
    var searchText by  rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Fund A School",
                modifier = Modifier
                    .padding(start = 12.dp)
                    .padding(vertical = 4.dp),
                style = layoutProperties.TextStyle.applicationTitle
            )

            Box(
                modifier = Modifier.weight(1f, true)
            )

            IconButton(
                onClick = {
                    isSearchOn = !isSearchOn
                },
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Icon(
                    imageVector = if(isSearchOn) {
                        Icons.Default.SearchOff
                    } else {
                        Icons.Default.Search
                    },
                    contentDescription = null
                )
            }

            IconButton(
                onClick = onOpenDialog,
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            }

        }

        AnimatedVisibility(
            visible = isSearchOn,
            enter = expandVertically(
                spring(
                    stiffness = Spring.StiffnessMedium
                ),
                expandFrom = Alignment.Top,
                initialHeight = { 0 }
            ) + expandHorizontally(
                spring(
                    stiffness = Spring.StiffnessMedium
                ),
                expandFrom = Alignment.End,
                initialWidth = { it / 16 }
            ),
            exit = shrinkVertically(
                spring(
                    stiffness = Spring.StiffnessMedium
                ),
                shrinkTowards = Alignment.Top,
                targetHeight = { 0 }
            )
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
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
                    if(searchText.text.isNotBlank()) {
                        onSearch(searchText.text)
                        searchText = TextFieldValue("")
                    }
                }
            )
        }
    }
}