package com.github.bkmbigo.fundaschool.presentation.screen.projects

import com.github.bkmbigo.fundaschool.domain.models.Project

data class ProjectsScreenState(
    val filter: ProjectsFilter = ProjectsFilter(),
    val projects: List<Project> = emptyList(),
    //val isAdmin: Boolean = false
)