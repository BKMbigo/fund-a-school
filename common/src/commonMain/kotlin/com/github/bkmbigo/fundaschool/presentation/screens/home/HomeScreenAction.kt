package com.github.bkmbigo.fundaschool.presentation.screens.home

import com.github.bkmbigo.fundaschool.domain.models.News

sealed class HomeScreenAction {
    object NavigateToAllNews: HomeScreenAction()
    object NavigateToProjects: HomeScreenAction()
    object NavigateToDonations: HomeScreenAction()
    object NavigateToAboutUs: HomeScreenAction()
    object OpenHomeOptionsDialog: HomeScreenAction()
    object OpenCloseSidebar: HomeScreenAction()
    class NavigateToNews(
        val news: News
    ) : HomeScreenAction()
}