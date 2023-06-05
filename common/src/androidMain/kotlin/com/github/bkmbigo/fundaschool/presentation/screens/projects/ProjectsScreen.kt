package com.github.bkmbigo.fundaschool.presentation.screens.projects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.bkmbigo.fundaschool.di.withKoin
import com.github.bkmbigo.fundaschool.presentation.screen.MainScreenAction
import com.github.bkmbigo.fundaschool.presentation.screen.projects.ProjectsScreenAction
import com.github.bkmbigo.fundaschool.presentation.screens.project.ProjectScreen
import kotlinx.coroutines.flow.StateFlow

class ProjectsScreen(
    private val onMainAction: (MainScreenAction) -> Unit
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel { ProjectsScreenModel(withKoin()) }

        val state by screenModel.state.collectAsState()

        SmallProjectsScreenContent(
            state = state,
            onAction = { action ->
                when (action) {
                    is ProjectsScreenAction.ClearFilters -> {
                        screenModel.clearFilter()
                    }

                    is ProjectsScreenAction.BookmarkChange -> {
                        /*TODO: Implement bookmarks*/
                    }

                    is ProjectsScreenAction.FilterProjects -> {
                        screenModel.updateFilter(action.projectsFilter)
                    }

                    is ProjectsScreenAction.NavigateToProject -> {
                        navigator.push(ProjectScreen(action.project, onMainAction = onMainAction))
                    }

                    ProjectsScreenAction.NavigateUp -> {
                        navigator.pop()
                    }
                }
            }
        )
    }
}