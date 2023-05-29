package com.github.bkmbigo.fundaschool.presentation.screens.home

import com.github.bkmbigo.fundaschool.domain.models.News
import com.github.bkmbigo.fundaschool.domain.models.Project
import com.github.bkmbigo.fundaschool.presentation.utils.FormFactor

sealed class HomeScreenState {
    abstract val news: List<News>
    abstract val featuredProjects: List<Project>
    abstract val bookmarkedProjects: List<Project>
    abstract val recentlyEndedProjects: List<Project>
    data class MobileHomeScreenState(
        val isHomeOptionsDialogOpen: Boolean = false,
        override val news: List<News> = emptyList(),
        override val featuredProjects: List<Project> = emptyList(),
        override val bookmarkedProjects: List<Project> = emptyList(),
        override val recentlyEndedProjects: List<Project> = emptyList(),
    ): HomeScreenState() {
        override fun copy(
            news: List<News>,
            featuredProjects: List<Project>,
            bookmarkedProjects: List<Project>,
            recentlyEndedProjects: List<Project>
        ): HomeScreenState = MobileHomeScreenState(
            isHomeOptionsDialogOpen = this.isHomeOptionsDialogOpen,
            news = news,
            featuredProjects = featuredProjects,
            bookmarkedProjects = bookmarkedProjects,
            recentlyEndedProjects = recentlyEndedProjects
        )
    }

    data class SmallHomeScreenState(
        val isSidebarOpen: Boolean = false,
        override val news: List<News> = emptyList(),
        override val featuredProjects: List<Project> = emptyList(),
        override val bookmarkedProjects: List<Project> = emptyList(),
        override val recentlyEndedProjects: List<Project> = emptyList(),
    ): HomeScreenState() {
        override fun copy(
            news: List<News>,
            featuredProjects: List<Project>,
            bookmarkedProjects: List<Project>,
            recentlyEndedProjects: List<Project>
        ): HomeScreenState = SmallHomeScreenState(
            isSidebarOpen = this.isSidebarOpen,
            news = news,
            featuredProjects = featuredProjects,
            bookmarkedProjects = bookmarkedProjects,
            recentlyEndedProjects = recentlyEndedProjects
        )
    }


    data class LargeHomeScreenState(
        val project: Boolean = false,
        override val news: List<News> = emptyList(),
        override val featuredProjects: List<Project> = emptyList(),
        override val bookmarkedProjects: List<Project> = emptyList(),
        override val recentlyEndedProjects: List<Project> = emptyList()
    ): HomeScreenState() {
        override fun copy(
            news: List<News>,
            featuredProjects: List<Project>,
            bookmarkedProjects: List<Project>,
            recentlyEndedProjects: List<Project>
        ): HomeScreenState = LargeHomeScreenState(
            project = this.project,
            news = news,
            featuredProjects = featuredProjects,
            bookmarkedProjects = bookmarkedProjects,
            recentlyEndedProjects = recentlyEndedProjects
        )
    }

    abstract fun copy(
        news: List<News> = this.news,
        featuredProjects: List<Project> = this.featuredProjects,
        bookmarkedProjects: List<Project> = this.bookmarkedProjects,
        recentlyEndedProjects: List<Project> = this.recentlyEndedProjects
    ): HomeScreenState
}

fun getDefaultHomeScreenState(formFactor: FormFactor) =
    when (formFactor) {
        FormFactor.MOBILE -> HomeScreenState.MobileHomeScreenState()
        FormFactor.SMALL -> HomeScreenState.SmallHomeScreenState()
        FormFactor.LARGE -> HomeScreenState.LargeHomeScreenState()
    }
