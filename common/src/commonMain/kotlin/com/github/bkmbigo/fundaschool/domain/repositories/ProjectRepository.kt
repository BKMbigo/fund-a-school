package com.github.bkmbigo.fundaschool.domain.repositories

import com.github.bkmbigo.fundaschool.domain.models.Project
import kotlinx.coroutines.flow.Flow

interface ProjectRepository {
    suspend fun insertProject(project: Project)
    suspend fun updateProject(project: Project)
    suspend fun deleteProject(project: Project)
    suspend fun getProject(projectId: String): Project?
    suspend fun getAllProjects(projectId: String): List<Project>

    fun observeProjects(): Flow<List<Project>>

    suspend fun getFeaturedProjects(): List<Project>
    suspend fun getProjectsInSchool(schoolId: String): List<Project>
}