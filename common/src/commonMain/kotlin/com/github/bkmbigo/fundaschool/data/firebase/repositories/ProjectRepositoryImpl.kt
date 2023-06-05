package com.github.bkmbigo.fundaschool.data.firebase.repositories

import com.github.bkmbigo.fundaschool.data.firebase.repositories.utils.decodeProject
import com.github.bkmbigo.fundaschool.data.firebase.repositories.utils.encodeProject
import com.github.bkmbigo.fundaschool.domain.models.firebase.Project
import com.github.bkmbigo.fundaschool.domain.repositories.firebase.ProjectRepository
import com.github.bkmbigo.fundaschool.utils.LogInfo
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.where
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProjectRepositoryImpl(
    firestore: FirebaseFirestore
) : ProjectRepository {
    private val collectionReference = firestore.collection("project")
    private val mediaReference = firestore.collection("media")

    override suspend fun insertProject(project: Project) {

        val id = collectionReference.add(project, { it.encodeProject() }).id
        collectionReference.document(id).update(mapOf("id" to id))
    }

    override suspend fun updateProject(project: Project) {

        collectionReference.document(project.id).update(
            mapOf(
                "name" to project.name,
                "description" to project.description,
                "featured" to project.featured,
                "schools" to project.schools,
                "startDate" to project.startDate,
                "completionDate" to project.completionDate,
                "targetAmount" to project.targetAmount,
                "currentAmount" to project.currentAmount,
                "donors" to project.donors,
                "media" to project.mediaUrl
            )
        )
    }

    override suspend fun deleteProject(project: Project) {
        collectionReference.document(project.id).delete()
    }

    override suspend fun getProject(projectId: String): Project? =
        collectionReference.document(projectId).get().data<Project>({ decodeProject() })

    override suspend fun getAllProjects(): List<Project> =
        collectionReference.get().documents.map {
            val project = it.data<Project>({
                val projectItem = decodeProject()
                LogInfo("Project Item is ${projectItem}")
                projectItem
            })
            LogInfo("Project is ${project}")
            project
        }.filterNotNull()

    override fun observeProjects(): Flow<List<Project>> =
        collectionReference.snapshots.map { snapshot ->
            snapshot.documents.map { it.data<Project>({ decodeProject() }) }.filterNotNull()
        }

    override suspend fun getFeaturedProjects(): List<Project> =
        collectionReference
            .where("featured", true)
            .get()
            .documents
            .mapNotNull { it.data<Project>({ decodeProject() }) }

    override suspend fun getProjectsInSchool(schoolId: String): List<Project> =
        collectionReference
            .where("schools", null, listOf(schoolId))
            .get()
            .documents
            .mapNotNull { it.data<Project>({ decodeProject() }) }

}