package com.github.bkmbigo.fundaschool.presentation.screen.project

import com.github.bkmbigo.fundaschool.domain.models.firebase.Project

sealed class ProjectScreenAction {
    object NavigateUp: ProjectScreenAction()
    object ToggleIsEditing: ProjectScreenAction()
    class SaveProject(val project: Project): ProjectScreenAction()
    object DeleteProject: ProjectScreenAction()
    object DonateToProject: ProjectScreenAction()
}
