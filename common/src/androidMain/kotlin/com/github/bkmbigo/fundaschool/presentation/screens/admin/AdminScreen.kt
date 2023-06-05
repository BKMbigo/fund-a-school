package com.github.bkmbigo.fundaschool.presentation.screens.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.bkmbigo.fundaschool.di.withKoin
import com.github.bkmbigo.fundaschool.presentation.screen.MainScreenAction
import com.github.bkmbigo.fundaschool.presentation.screen.admin.AdminScreenAction
import com.github.bkmbigo.fundaschool.presentation.screen.admin.AdminScreenState
import com.github.bkmbigo.fundaschool.presentation.screens.news.NewsScreen
import com.github.bkmbigo.fundaschool.presentation.screens.project.ProjectScreen
import kotlinx.coroutines.flow.StateFlow

class AdminScreen(
    private val onMainAction: (MainScreenAction) -> Unit,
): Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel { AdminScreenModel(withKoin(), withKoin(), withKoin(), withKoin(), withKoin()) }

        val state by screenModel.state.collectAsState()

        SmallAdminScreenContent(
            state = state,
            onAction = { action ->
                when(action) {
                    AdminScreenAction.AddNews -> { navigator.push(NewsScreen(null)) }
                    AdminScreenAction.AddProject -> {
                        navigator.push(ProjectScreen(null, onMainAction))
                    }
                    AdminScreenAction.NavigateUp -> { navigator.pop() }
                    is AdminScreenAction.OpenNews -> { navigator.push(NewsScreen(action.news)) }
                    is AdminScreenAction.OpenProject -> {
                        navigator.push(ProjectScreen(action.project, onMainAction))
                    }
                    is AdminScreenAction.SearchNews -> {

                    }
                    is AdminScreenAction.SearchProjects -> {

                    }
                }
            }
        )
    }
}