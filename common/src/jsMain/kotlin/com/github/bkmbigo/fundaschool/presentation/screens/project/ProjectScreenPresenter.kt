package com.github.bkmbigo.fundaschool.presentation.screens.project

import com.github.bkmbigo.fundaschool.data.persistence.PersistenceKey
import com.github.bkmbigo.fundaschool.data.persistence.settings
import com.github.bkmbigo.fundaschool.domain.models.firebase.Project
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.AuthRepository
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.ProjectRepository
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.UserRepository
import com.russhwolf.settings.set
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProjectScreenPresenter(
    private val project: Project?,
    private val projectRepository: ProjectRepository,
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
    private val coroutineScope: CoroutineScope
) {

    private val isNew = project == null


    private val _state = MutableStateFlow(
        ProjectScreenState(
            project ?: Project.EmptyProject,
            isEditing = project?.let{ false } ?: true
        )
    )
    val state = _state.asStateFlow()

    init {
        getDefaultIsAdmin()
    }

    fun onDonateToProject(amount: Float) {
        settings[PersistenceKey.pendingDonationTitle] = project?.name ?: ""
        settings[PersistenceKey.pendingDonationAmount] = amount
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
                    .currentUser()?.let { currentUser -> userRepository.getUser(currentUser.uid)?.admin } ?: false
            )
        }
    }

}