package com.github.bkmbigo.fundaschool.presentation.screen.admin

import com.github.bkmbigo.fundaschool.domain.models.News
import com.github.bkmbigo.fundaschool.domain.models.Project

sealed class AdminScreenAction {
    object NavigateUp: AdminScreenAction()
    object AddNews: AdminScreenAction()
    class OpenNews(val news: News): AdminScreenAction()
    object AddProject: AdminScreenAction()
    class OpenProject(val project: Project): AdminScreenAction()
    class SearchProjects(val searchText: String): AdminScreenAction()
    class SearchNews(val searchText: String): AdminScreenAction()
}