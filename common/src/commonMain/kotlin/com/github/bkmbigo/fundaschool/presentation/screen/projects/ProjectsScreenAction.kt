package com.github.bkmbigo.fundaschool.presentation.screen.projects

sealed class ProjectsScreenAction {
    object NavigateUp: ProjectsScreenAction()
    class FilterProjects(val projectsFilter: ProjectsFilter): ProjectsScreenAction()
    object ClearFilters: ProjectsScreenAction()
}