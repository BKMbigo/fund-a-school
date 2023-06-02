package com.github.bkmbigo.fundaschool.presentation.screens.project

import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.github.bkmbigo.fundaschool.domain.models.Project
import com.github.bkmbigo.fundaschool.domain.repositories.AuthRepository
import com.github.bkmbigo.fundaschool.domain.repositories.ProjectRepository
import com.github.bkmbigo.fundaschool.domain.repositories.UserRepository
import com.github.bkmbigo.fundaschool.presentation.screen.project.ProjectScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class ProjectScreenModel(
    private val project: Project?,
    private val projectRepository: ProjectRepository,
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : ScreenModel {
    private val isNew = project == null

    private val _state = MutableStateFlow(
        ProjectScreenState(
            project ?: DefaultProject,
            isEditing = project?.let{ false } ?: true
        )
    )
    val state = _state.asStateFlow()

    init {
        getDefaultIsAdmin()
    }

    fun toggleIsEditing() {
        _state.value = _state.value.copy(isEditing = !_state.value.isEditing)
    }
    fun saveProject(project: Project) {
        coroutineScope.launch {
            if(isNew) {
                projectRepository.insertProject(project)
            } else {
                projectRepository.updateProject(project)
            }
            _state.value = _state.value.copy(
                project = project,
                isEditing = false
            )
        }
    }
    fun deleteProject() {
        if(project != null) {
            coroutineScope.launch {
                projectRepository.deleteProject(project)
            }
        }
    }

    private fun getDefaultIsAdmin() {
        coroutineScope.launch {
            _state.value = _state.value.copy(
                isAdmin = authRepository
                    .currentUser()?.let { currentUser -> userRepository.getUser(currentUser.uid)?.isAdmin } ?: false
            )
        }
    }


    companion object {
        private val todayDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
        // Find a more efficient solution to getting default completion date
        private val defaultCompletionDate = Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault()).date.let { LocalDate.fromEpochDays(it.toEpochDays() + 14) }


        private val DefaultProject = Project(
            id = "",
            name = "",
            description = "",
            featured = false,
            schools = listOf(),
            startDate = todayDate,
            completionDate = defaultCompletionDate,
            targetAmount = 0.0f,
            currentAmount = 0.0f,
            donors = 0,
            media = listOf()
        )
    }
}