package com.github.bkmbigo.fundaschool.presentation.screens.home

import androidx.compose.runtime.Composable
import com.github.bkmbigo.fundaschool.presentation.screen.home.HomeScreenAction
import com.github.bkmbigo.fundaschool.presentation.screen.home.HomeScreenState
import com.github.bkmbigo.fundaschool.presentation.theme.layoutproperties.LocalLayoutProperty
import com.github.bkmbigo.fundaschool.presentation.utils.FormFactor

@Composable
fun HomeScreen(
    state: HomeScreenState,
    onAction: (HomeScreenAction) -> Unit
) {
    val layoutProperties = LocalLayoutProperty.current

    when(layoutProperties.formFactor) {
        FormFactor.SMALL -> {
            SmallHomeScreenContent(
                state = state,
                onAction = onAction
            )
        }
        FormFactor.LARGE -> {
            LargeHomeScreen(
                state = state,
                onAction = onAction
            )
        }
    }
}