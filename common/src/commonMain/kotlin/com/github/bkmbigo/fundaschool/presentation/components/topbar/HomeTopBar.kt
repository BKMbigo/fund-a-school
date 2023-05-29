package com.github.bkmbigo.fundaschool.presentation.components.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.bkmbigo.fundaschool.presentation.utils.FormFactor

@Composable
fun HomeTopBar(
    formFactor: FormFactor,
    onAction: (HomeTopBarAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (formFactor) {
        FormFactor.MOBILE -> MobileHomeTopBar(onAction, modifier)
        FormFactor.SMALL -> SmallHomeTopBar(onAction, modifier)
        FormFactor.LARGE -> LargeHomeTopBar(onAction, modifier)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MobileHomeTopBar(
    onAction: (HomeTopBarAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        CenterAlignedTopAppBar(
            title = { /*NO-OP*/ },
            actions = {
                IconButton(
                    onClick = {
                        onAction(HomeTopBarAction.ShowOptionsDialog)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = null
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(4.dp))

        ProvideTextStyle(MaterialTheme.typography.displayMedium) {
            Text(
                text = "Fund A School",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SmallHomeTopBar(
    onAction: (HomeTopBarAction) -> Unit,
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                onAction(HomeTopBarAction.ShowSideNavigation)
            },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = null
            )
        }

        Text(
            text = "Fund A School",
            modifier = Modifier
                .padding(vertical = 12.dp)
                .padding(start = 48.dp),
            fontFamily = MaterialTheme.typography.displayMedium.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )

        Box(
            modifier = Modifier.weight(1f)
        )

        IconButton(
            onClick = {
                onAction(HomeTopBarAction.ShowOptionsDialog)
            },
            modifier = Modifier.padding(end = 12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LargeHomeTopBar(
    onAction: (HomeTopBarAction) -> Unit,
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Fund A School",
            modifier = Modifier
                .padding(vertical = 12.dp)
                .padding(start = 60.dp),
            fontFamily = MaterialTheme.typography.displayMedium.fontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )

        ProvideTextStyle(MaterialTheme.typography.titleLarge) {
            TextButton(
                onClick = {
                    onAction(HomeTopBarAction.NavigateToNews)
                }
            ) {
                Text(
                    text = "News"
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            TextButton(
                onClick = {
                    onAction(HomeTopBarAction.NavigateToProjects)
                }
            ) {
                Text(
                    text = "Projects"
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            TextButton(
                onClick = {
                    onAction(HomeTopBarAction.NavigateToDonation)
                }
            ) {
                Text(
                    text = "Donations"
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            TextButton(
                onClick = {
                    onAction(HomeTopBarAction.NavigateToAboutUs)
                }
            ) {
                Text(
                    text = "About us"
                )
            }

            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}