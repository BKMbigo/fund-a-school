package com.github.bkmbigo.fundaschool.presentation.screen.news

import com.github.bkmbigo.fundaschool.domain.models.News

data class NewsScreenState(
    val news: News,
    val isAdmin: Boolean = false,
    val isEditing: Boolean = false
)
