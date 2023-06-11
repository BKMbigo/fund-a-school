package com.github.bkmbigo.fundaschool.presentation.screens.home

import com.github.bkmbigo.fundaschool.domain.models.firebase.News
import com.github.bkmbigo.fundaschool.domain.models.firebase.Project
import com.github.bkmbigo.fundaschool.domain.models.firebase.User
import com.github.bkmbigo.fundaschool.domain.models.persistence.PendingDonation

data class HomeScreenState(
    val pendingDonation: PendingDonation? = null,
    val bookmarks: List<Project> = emptyList(),
    val news: List<News> = emptyList(),
    val featuredProjects: List<Project> = emptyList(),
    val recentlyEndedProjects: List<Project> = emptyList(),
    val topDonors: List<User> = emptyList(),
    val latestDonations: List<User> = emptyList()
)
