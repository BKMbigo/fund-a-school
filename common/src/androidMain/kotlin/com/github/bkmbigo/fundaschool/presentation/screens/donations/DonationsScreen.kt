package com.github.bkmbigo.fundaschool.presentation.screens.donations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.bkmbigo.fundaschool.di.withKoin
import com.github.bkmbigo.fundaschool.presentation.screens.project.ProjectScreen

class DonationsScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val screenModel = remember {
            DonationsScreenModel(withKoin(), withKoin(), withKoin())
        }

        val state by screenModel.state.collectAsState()

        SmallDonationsScreenContent(
            state = state,
            onAction = { action ->
                when (action) {
                    is DonationsScreenAction.NavigateToProject -> {
                        navigator.push(ProjectScreen(action.project))
                    }

                    DonationsScreenAction.NavigateUp -> {
                        navigator.pop()
                    }
                }
            }
        )
    }

}