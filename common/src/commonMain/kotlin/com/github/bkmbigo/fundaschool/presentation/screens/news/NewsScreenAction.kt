package com.github.bkmbigo.fundaschool.presentation.screens.news

import com.github.bkmbigo.fundaschool.domain.models.firebase.News
import com.github.bkmbigo.fundaschool.domain.models.firebase.Project

sealed class NewsScreenAction{
    object ToggleIsEditing: NewsScreenAction()
    object NavigateUp: NewsScreenAction()
    class NavigateToProject(val project: Project): NewsScreenAction()
    class SaveNews(val news: News): NewsScreenAction()
    object DeleteNews: NewsScreenAction()
}
