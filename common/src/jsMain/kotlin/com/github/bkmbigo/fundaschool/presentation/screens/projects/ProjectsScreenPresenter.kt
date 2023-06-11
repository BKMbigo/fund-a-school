package com.github.bkmbigo.fundaschool.presentation.screens.projects

import com.github.bkmbigo.fundaschool.domain.models.firebase.Project
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.ProjectRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class ProjectsScreenPresenter(
    private val projectRepository: ProjectRepository,
    private val coroutineScope: CoroutineScope
) {

    private val _state = MutableStateFlow(ProjectsScreenState())
    val state = _state.asStateFlow()

    private var currentProjectList = emptyList<Project>()

    init {
        coroutineScope.launch {
            observeProjects()
        }
    }

    internal fun clearFilter() {
        _state.value =
            _state.value.copy(
                filter = ProjectsFilter(),
                projects = currentProjectList.filterProjects(ProjectsFilter())
            )
    }
    internal fun updateFilter(filter: ProjectsFilter) {
        coroutineScope.launch {
            _state.value =
                _state.value.copy(
                    filter = filter,
                    projects = currentProjectList.filterProjects(filter)
                )
        }
    }

    private suspend fun observeProjects() {
        projectRepository.observeProjects().collect { projects ->
            currentProjectList = projects
            _state.value =
                _state.value.copy(projects = projects.filterProjects(_state.value.filter))
        }
    }

    private fun List<Project>.filterProjects(filter: ProjectsFilter): List<Project> = this.filter {

        if (filter.isActive()) {
            (if (!filter.completedProjects) {
                it.completionDate < Clock.System.now()
                    .toLocalDateTime(TimeZone.currentSystemDefault()).date.toEpochDays()
            } else true)
                    &&
                    (if (!filter.ongoingProjects) {
                        it.completionDate > Clock.System.now()
                            .toLocalDateTime(TimeZone.currentSystemDefault()).date.toEpochDays()
                    } else true)
                    &&
                    (if (filter.startDate != null || filter.endDate != null) {
                        (if (filter.startDate != null) {
                            it.startDate > filter.startDate.toEpochDays()
                        } else true) &&
                                (if (filter.endDate != null) {
                                    it.completionDate < filter.endDate.toEpochDays()
                                } else true)
                    } else true)
                    &&
                    (if (filter.searchText.isNotBlank()) {
                        it.name.contains(filter.searchText) || it.description.contains(filter.searchText)
                    } else true)

        } else true
    }

}