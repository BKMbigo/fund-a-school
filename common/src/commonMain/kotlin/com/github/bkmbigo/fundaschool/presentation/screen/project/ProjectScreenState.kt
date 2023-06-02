package com.github.bkmbigo.fundaschool.presentation.screen.project

import com.github.bkmbigo.fundaschool.domain.models.Project

data class ProjectScreenState(
    val project: Project,
    val isAdmin: Boolean = false,
    val isEditing: Boolean = false
)
