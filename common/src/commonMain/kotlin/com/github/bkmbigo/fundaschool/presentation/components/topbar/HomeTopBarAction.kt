package com.github.bkmbigo.fundaschool.presentation.components.topbar

sealed class HomeTopBarAction {
    class SearchText(
        text: String,
    ): HomeTopBarAction()

    object NavigateToNews: HomeTopBarAction()
    object NavigateToProjects: HomeTopBarAction()
    object NavigateToDonation: HomeTopBarAction()
    object NavigateToAboutUs: HomeTopBarAction()
    object ShowOptionsDialog: HomeTopBarAction()
    object ShowSideNavigation: HomeTopBarAction()
}