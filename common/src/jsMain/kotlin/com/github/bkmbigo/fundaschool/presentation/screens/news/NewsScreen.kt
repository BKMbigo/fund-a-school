package com.github.bkmbigo.fundaschool.presentation.screens.news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import app.softwork.routingcompose.Router
import app.softwork.routingcompose.navigate
import com.github.bkmbigo.fundaschool.di.withKoin
import com.github.bkmbigo.fundaschool.domain.models.firebase.News
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.NewsRepository
import kotlinx.browser.window

@Composable
fun NewsScreen(
    newsId: String?,
    router: Router
) {
    val coroutineScope = rememberCoroutineScope()
    val newsRepository = remember { withKoin<NewsRepository>() }

    var isLoadingCurrentItem by remember { mutableStateOf(false) }
    var currentNewsItem by remember { mutableStateOf<News?>(null) }

    val presenter = remember(currentNewsItem) {
        NewsScreenPresenter(
            news = currentNewsItem,
            authRepository = withKoin(),
            newsRepository = newsRepository,
            userRepository = withKoin(),
            coroutineScope = coroutineScope
        )
    }

    LaunchedEffect(Unit) {
        if (newsId != null) {
            isLoadingCurrentItem = true
            currentNewsItem =
                newsRepository.getNews(newsId.substring(1, newsId.length - 1))
            isLoadingCurrentItem = false
        }
    }

    val state by presenter.state.collectAsState()

    SmallNewsScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is NewsScreenAction.NavigateToProject -> {
                    router.navigate(
                        to = "/project",
                        parameters = mapOf(
                            "projectId" to action.project.id
                        )
                    )
                }

                is NewsScreenAction.SaveNews -> {
                    presenter.saveNews(action.news)
                }

                NewsScreenAction.DeleteNews -> {
                    presenter.deleteNews()
                }

                NewsScreenAction.NavigateUp -> {
                    window.history.back()
                }

                NewsScreenAction.ToggleIsEditing -> {
                    presenter.toggleEditing()
                }
            }
        }
    )

}