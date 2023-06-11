package com.github.bkmbigo.fundaschool.presentation.screens.project

import com.github.bkmbigo.fundaschool.domain.models.firebase.Project

data class ProjectScreenState(
    val project: Project,
    val isAdmin: Boolean = false,
    val isEditing: Boolean = false
)
