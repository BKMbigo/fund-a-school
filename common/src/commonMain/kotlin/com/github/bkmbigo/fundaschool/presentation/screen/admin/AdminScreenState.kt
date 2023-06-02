package com.github.bkmbigo.fundaschool.presentation.screen.admin

import com.github.bkmbigo.fundaschool.domain.models.Donation
import com.github.bkmbigo.fundaschool.domain.models.News
import com.github.bkmbigo.fundaschool.domain.models.Project

data class AdminScreenState(
    val isAdmin:Boolean = false,
    // Overview Values
    val completedProjects: Int = 0,
    val totalDonations: Float = 0f,
    val ongoingProjects: Int = 0,
    val totalDonors: Int = 0,

    //news
    val news: List<News> = emptyList(),

    //projects
    val projects: List<Project> = emptyList(),

    // Donations
    val donations: List<Donation> = emptyList()
)
