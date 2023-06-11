package com.github.bkmbigo.fundaschool.presentation.screens.projects

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import app.softwork.routingcompose.Router
import app.softwork.routingcompose.navigate
import com.github.bkmbigo.fundaschool.di.withKoin
import com.github.bkmbigo.fundaschool.presentation.navigation.LocalDestinations
import com.github.bkmbigo.fundaschool.presentation.screens.project.SmallProjectScreen
import kotlinx.browser.window

@Composable
fun ProjectsScreen(
    router: Router
) {
    val coroutineScope = rememberCoroutineScope()

    val presenter = remember {
        ProjectsScreenPresenter(
            projectRepository = withKoin(),
            coroutineScope = coroutineScope
        )
    }

    val state by presenter.state.collectAsState()

    SmallProjectsScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is ProjectsScreenAction.BookmarkChange -> {

                }
                ProjectsScreenAction.ClearFilters -> {
                    presenter.clearFilter()
                }
                is ProjectsScreenAction.FilterProjects -> {
                    presenter.updateFilter(action.projectsFilter)
                }
                is ProjectsScreenAction.NavigateToProject -> {
                    router.navigate(
                        to = LocalDestinations.PROJECT.route,
                        parameters = mapOf(
                            "projectId" to action.project.id
                        )
                    )
                }
                ProjectsScreenAction.NavigateUp -> {
                    window.history.back()
                }
            }
        }
    )

}