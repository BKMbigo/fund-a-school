package com.github.bkmbigo.fundaschool.presentation.screens.project

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.bkmbigo.fundaschool.di.withKoin
import com.github.bkmbigo.fundaschool.domain.models.Project
import com.github.bkmbigo.fundaschool.presentation.screen.project.ProjectScreenAction
import com.github.bkmbigo.fundaschool.presentation.screen.project.ProjectScreenState

class ProjectScreen(val project: Project?): Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel{ ProjectScreenModel(project, withKoin(), withKoin(), withKoin()) }

        val state by screenModel.state.collectAsState()

        SmallProjectScreen(
            state = state,
            onAction = { action ->
                when(action) {
                    ProjectScreenAction.DeleteProject -> {
                        screenModel.deleteProject()
                        navigator.pop()
                    }
                    ProjectScreenAction.NavigateUp -> {
                        navigator.pop()
                    }
                    is ProjectScreenAction.SaveProject -> {
                        screenModel.saveProject(action.project)
                    }
                    ProjectScreenAction.ToggleIsEditing -> {
                        screenModel.toggleIsEditing()
                    }
                }
            }
        )
    }
}