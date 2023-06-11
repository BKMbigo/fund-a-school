package com.github.bkmbigo.fundaschool.presentation.screens.projects

import kotlinx.datetime.LocalDate

data class ProjectsFilter(
    val searchText: String = "",
    val ongoingProjects: Boolean = true,
    val completedProjects: Boolean = true,
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null
) {

    fun isActive(): Boolean =
        searchText.isNotBlank() || !ongoingProjects || !completedProjects || startDate != null || endDate != null
}
