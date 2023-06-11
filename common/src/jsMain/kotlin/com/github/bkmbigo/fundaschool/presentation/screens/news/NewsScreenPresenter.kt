package com.github.bkmbigo.fundaschool.presentation.screens.news

import com.github.bkmbigo.fundaschool.domain.models.firebase.News
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.AuthRepository
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.NewsRepository
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.UserRepository
import com.github.bkmbigo.fundaschool.domain.utils.NewsCategory
import com.github.bkmbigo.fundaschool.utils.LogInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class NewsScreenPresenter(
    private val news: News?,
    private val authRepository: AuthRepository,
    private val newsRepository: NewsRepository,
    private val userRepository: UserRepository,
    private val coroutineScope: CoroutineScope
) {
    private val isNew = news == null

    private val _state = MutableStateFlow(
        NewsScreenState(
            news ?: News(
                "",
                "",
                "",
                NewsCategory.NEW_PROJECTS,
                Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
                "",
                "",
                null,
                ""
            ),
            isEditing = news?.let{ false } ?: true
        )
    )
    val state = _state.asStateFlow()

    init {
        checkIfUserIsAdmin()
    }

    private fun checkIfUserIsAdmin() {
        coroutineScope.launch {
            _state.value = _state.value.copy(
                isAdmin = authRepository
                    .currentUser()?.let { currentUser ->
                        val uid = currentUser.uid
                        LogInfo("Current UID: $uid")
                        val user = userRepository.getUser(currentUser.uid)
                        LogInfo("Current User: ${user}")
                        val isAdm = user?.admin
                        LogInfo("Value is Admin: $isAdm")
                        isAdm
                    } ?: false
            )
        }
    }

    fun toggleEditing() {
        _state.value = _state.value.copy(isEditing = !_state.value.isEditing)
    }

    fun saveNews(news: News) {
        coroutineScope.launch {
            if (isNew) {
                newsRepository.insertNews(news)
            } else {
                newsRepository.updateNews(news)
            }
            _state.value = _state.value.copy(
                news = news,
                isEditing = false
            )
        }
    }

    fun deleteNews() {
        coroutineScope.launch {
            if (news != null) {
                newsRepository.deleteNews(news)
            }
        }
    }

}