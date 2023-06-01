package com.github.bkmbigo.fundaschool.presentation.screen.home

import com.github.bkmbigo.fundaschool.domain.models.News
import com.github.bkmbigo.fundaschool.domain.models.Project
import com.github.bkmbigo.fundaschool.domain.models.User

data class HomeScreenState(
    val bookmarks: List<Project> = emptyList(),
    val news: List<News> = emptyList(),
    val featuredProjects: List<Project> = emptyList(),
    val recentlyEndedProjects: List<Project> = emptyList(),
    val topDonors: List<User> = emptyList(),
    val latestDonations: List<User> = emptyList()
)
