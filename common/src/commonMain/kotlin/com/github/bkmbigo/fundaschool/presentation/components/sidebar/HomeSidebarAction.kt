package com.github.bkmbigo.fundaschool.presentation.components.sidebar

sealed class HomeSidebarAction {
    object NavigateUp: HomeSidebarAction()
    object NavigateToHome: HomeSidebarAction()
    object NavigateToProjects: HomeSidebarAction()
    object NavigateToDonations: HomeSidebarAction()
    object NavigateToAboutUs: HomeSidebarAction()
}