package com.github.bkmbigo.fundaschool.presentation.components.topbar

sealed class AdaptiveHomeTopBarAction {
    object OpenActionsDialog: AdaptiveHomeTopBarAction()
    class Search(val searchText: String): AdaptiveHomeTopBarAction()
    object NavigateToAdminScreen: AdaptiveHomeTopBarAction()
    object NavigateToDonationsScreen: AdaptiveHomeTopBarAction()
    object NavigateToProjectsScreen: AdaptiveHomeTopBarAction()
    object NavigateToAboutUsScreen: AdaptiveHomeTopBarAction()
}