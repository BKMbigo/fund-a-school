package com.github.bkmbigo.fundaschool.presentation.screens.news

import com.github.bkmbigo.fundaschool.domain.models.firebase.News

data class NewsScreenState(
    val news: News,
    val isAdmin: Boolean = false,
    val isEditing: Boolean = false
)
