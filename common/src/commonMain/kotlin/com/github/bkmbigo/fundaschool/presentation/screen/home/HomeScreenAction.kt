package com.github.bkmbigo.fundaschool.presentation.screen.home

import com.github.bkmbigo.fundaschool.domain.models.firebase.News
import com.github.bkmbigo.fundaschool.domain.models.firebase.Project

sealed class HomeScreenAction {
    object NavigateToAdmin: HomeScreenAction()
    object NavigateToDonations: HomeScreenAction()
    object NavigateToProjects: HomeScreenAction()
    object NavigateToAboutUs: HomeScreenAction()
    class NavigateToNews(val news: News): HomeScreenAction()
    class NavigateToProject(val project: Project): HomeScreenAction()
    class Search(val searchText: String): HomeScreenAction()
}
