package com.github.bkmbigo.fundaschool.presentation.screen.news

import com.github.bkmbigo.fundaschool.domain.models.News
import com.github.bkmbigo.fundaschool.domain.models.Project

sealed class NewsScreenAction{
    object ToggleIsEditing: NewsScreenAction()
    object NavigateUp: NewsScreenAction()
    class NavigateToProject(project: Project): NewsScreenAction()
    class SaveNews(val news: News): NewsScreenAction()
    object DeleteNews: NewsScreenAction()
}
