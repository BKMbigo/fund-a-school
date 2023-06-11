package com.github.bkmbigo.fundaschool.presentation.screens.donations

import com.github.bkmbigo.fundaschool.domain.models.firebase.Project

sealed class DonationsScreenAction {
    object NavigateUp: DonationsScreenAction()
    class NavigateToProject(val project: Project): DonationsScreenAction()
}