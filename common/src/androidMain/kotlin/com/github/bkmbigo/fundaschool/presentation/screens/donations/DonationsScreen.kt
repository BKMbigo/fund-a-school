package com.github.bkmbigo.fundaschool.presentation.screens.donations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.bkmbigo.fundaschool.di.withKoin
import com.github.bkmbigo.fundaschool.presentation.screen.MainScreenAction
import com.github.bkmbigo.fundaschool.presentation.screen.donations.DonationsScreenAction
import com.github.bkmbigo.fundaschool.presentation.screens.project.ProjectScreen
import kotlinx.coroutines.flow.StateFlow

class DonationsScreen(
    private val onMainAction: (MainScreenAction) -> Unit,
): Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel {
            DonationsScreenModel(withKoin(), withKoin(), withKoin())
        }

        val state by screenModel.state.collectAsState()

        SmallDonationsScreenContent(
            state = state,
            onAction = { action ->
                when(action) {
                    is DonationsScreenAction.NavigateToProject -> {
                        navigator.push(ProjectScreen(project = action.project, onMainAction = onMainAction))
                    }
                    DonationsScreenAction.NavigateUp -> {
                        navigator.pop()
                    }
                }
            }
        )
    }
}