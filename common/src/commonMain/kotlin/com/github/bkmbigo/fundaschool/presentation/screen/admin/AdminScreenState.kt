package com.github.bkmbigo.fundaschool.presentation.screen.admin

import com.github.bkmbigo.fundaschool.domain.models.Donation
import com.github.bkmbigo.fundaschool.domain.models.News
import com.github.bkmbigo.fundaschool.domain.models.Project

data class AdminScreenState(
    val isAdmin:Boolean = false,
    // Overview Values
    val completedProjects: Int,
    val totalDonations: Float,
    val ongoingProjects: Float,
    val totalDonors: Int,

    //news
    val news: List<News>,

    //projects
    val projects: List<Project>,

    // Donations
    val donations: List<Donation>
)
