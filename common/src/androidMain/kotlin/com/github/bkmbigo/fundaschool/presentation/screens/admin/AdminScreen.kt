package com.github.bkmbigo.fundaschool.presentation.screens.admin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.bkmbigo.fundaschool.di.withKoin
import com.github.bkmbigo.fundaschool.presentation.screens.news.NewsScreen
import com.github.bkmbigo.fundaschool.presentation.screens.project.ProjectScreen

class AdminScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val screenModel = rememberScreenModel {
            AdminScreenModel(
                donationRepository = withKoin(),
                projectRepository = withKoin(),
                newsRepository = withKoin(),
                userRepository = withKoin(),
                authRepository = withKoin()
            )
        }

        val state by screenModel.state.collectAsState()

        SmallAdminScreenContent(
            state = state,
            onAction = { action ->
                when (action) {
                    AdminScreenAction.AddNews -> {
                        navigator.push(NewsScreen(null))
                    }

                    AdminScreenAction.AddProject -> {
                        navigator.push(ProjectScreen(null))
                    }

                    AdminScreenAction.NavigateUp -> {
                        navigator.pop()
                    }

                    is AdminScreenAction.OpenNews -> {
                        navigator.push(NewsScreen(action.news))
                    }

                    is AdminScreenAction.OpenProject -> {
                        navigator.push(ProjectScreen(action.project))
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