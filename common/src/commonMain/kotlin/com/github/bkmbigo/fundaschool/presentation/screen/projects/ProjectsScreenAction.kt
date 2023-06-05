package com.github.bkmbigo.fundaschool.presentation.screen.projects

import com.github.bkmbigo.fundaschool.domain.models.firebase.Project

sealed class ProjectsScreenAction {
    object NavigateUp: ProjectsScreenAction()
    class FilterProjects(val projectsFilter: ProjectsFilter): ProjectsScreenAction()
    object ClearFilters: ProjectsScreenAction()
    class NavigateToProject(val project: Project): ProjectsScreenAction()
    class BookmarkChange(val project: Project): ProjectsScreenAction()
}