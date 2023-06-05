package com.github.bkmbigo.fundaschool.presentation.screens.news

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.bkmbigo.fundaschool.di.withKoin
import com.github.bkmbigo.fundaschool.domain.models.firebase.News
import com.github.bkmbigo.fundaschool.presentation.screen.news.NewsScreenAction

class NewsScreen(val news: News?): Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val screenModel = rememberScreenModel { NewsScreenModel(news, withKoin(), withKoin(), withKoin()) }

        val state by screenModel.state.collectAsState()

        SmallNewsScreen(
            state = state,
            onAction = { action ->
                when(action) {
                    is NewsScreenAction.SaveNews -> {
                        screenModel.saveNews(action.news)
                    }
                    is NewsScreenAction.NavigateToProject -> {

                    }
                    NewsScreenAction.DeleteNews -> {
                        screenModel.deleteNews()
                        navigator.pop()
                    }
                    NewsScreenAction.NavigateUp -> {
                        navigator.pop()
                    }
                    NewsScreenAction.ToggleIsEditing -> screenModel.toggleEditing()
                }
            }
        )
    }
}