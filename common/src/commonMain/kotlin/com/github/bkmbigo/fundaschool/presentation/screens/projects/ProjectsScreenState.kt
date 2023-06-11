package com.github.bkmbigo.fundaschool.presentation.screens.projects

import com.github.bkmbigo.fundaschool.domain.models.firebase.Project

data class ProjectsScreenState(
    val filter: ProjectsFilter = ProjectsFilter(),
    val projects: List<Project> = emptyList(),
    val isAdmin: Boolean = false
)