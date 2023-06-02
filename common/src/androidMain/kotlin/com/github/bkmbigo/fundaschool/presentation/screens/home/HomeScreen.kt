package com.github.bkmbigo.fundaschool.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.bkmbigo.fundaschool.di.withKoin
import com.github.bkmbigo.fundaschool.presentation.screen.home.HomeScreenAction
import com.github.bkmbigo.fundaschool.presentation.screens.admin.AdminScreen
import com.github.bkmbigo.fundaschool.presentation.screens.project.ProjectScreen
import com.github.bkmbigo.fundaschool.presentation.screens.projects.ProjectsScreen

class HomeScreen: Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val screenModel = rememberScreenModel<HomeScreenModel>{
            HomeScreenModel(
                projectRepository = withKoin(),
                newsRepository = withKoin(),
                donationRepository = withKoin(),
                userRepository = withKoin()
            )
        }

        val state by screenModel.state.collectAsState()

        HomeScreenContent(
            state = state,
            onAction = { action ->
                when(action) {
                    HomeScreenAction.NavigateToAboutUs -> {}
                    HomeScreenAction.NavigateToAdmin -> { navigator.push(AdminScreen()) }
                    HomeScreenAction.NavigateToDonations -> {}
                    HomeScreenAction.NavigateToProjects -> { navigator.push(ProjectsScreen()) }
                    is HomeScreenAction.NavigateToNews -> { navigator.push(AdminScreen()) }
                    is HomeScreenAction.NavigateToProject -> { navigator.push(ProjectScreen(action.project)) }
                    is HomeScreenAction.Search -> {}
                }
            }
        )
    }
}