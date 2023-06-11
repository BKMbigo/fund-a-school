package com.github.bkmbigo.fundaschool.presentation.screens.project

import com.github.bkmbigo.fundaschool.domain.models.firebase.Project

sealed class ProjectScreenAction {
    object NavigateUp: ProjectScreenAction()
    object ToggleIsEditing: ProjectScreenAction()
    class SaveProject(val project: Project): ProjectScreenAction()
    object DeleteProject: ProjectScreenAction()
    class DonateToProject(val amount: Float): ProjectScreenAction()
}
