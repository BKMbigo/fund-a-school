package com.github.bkmbigo.fundaschool.presentation.screens.project

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import app.softwork.routingcompose.Router
import com.github.bkmbigo.fundaschool.di.withKoin
import com.github.bkmbigo.fundaschool.domain.models.firebase.Project
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.ProjectRepository
import kotlinx.browser.window

@Composable
fun ProjectScreen(
    projectId: String?,
    router: Router
) {

    val coroutineScope = rememberCoroutineScope()
    val projectRepository = remember { withKoin<ProjectRepository>() }

    var isLoadingCurrentItem by remember { mutableStateOf(false) }
    var currentProjectItem by remember { mutableStateOf<Project?>(null) }

    val presenter = remember(currentProjectItem) {
        ProjectScreenPresenter(
            project = currentProjectItem,
            authRepository = withKoin(),
            projectRepository = projectRepository,
            userRepository = withKoin(),
            coroutineScope = coroutineScope
        )
    }

    LaunchedEffect(Unit) {
        if (projectId != null) {
            isLoadingCurrentItem = true
            currentProjectItem =
                projectRepository.getProject(projectId.substring(1, projectId.length - 1))
            isLoadingCurrentItem = false
        }
    }

    val state by presenter.state.collectAsState()

    SmallProjectScreen(
        state = state,
        onAction = { action ->
            when(action) {
                ProjectScreenAction.DeleteProject -> {
                    presenter.deleteProject()
                }
                is ProjectScreenAction.DonateToProject -> {

                }
                ProjectScreenAction.NavigateUp -> {
                    window.history.back()
                }
                is ProjectScreenAction.SaveProject -> {
                    presenter.saveProject(action.project)
                }
                ProjectScreenAction.ToggleIsEditing -> {
                    presenter.toggleIsEditing()
                }
            }
        }
    )
}