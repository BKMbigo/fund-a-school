package com.github.bkmbigo.fundaschool.data.repositories

import com.github.bkmbigo.fundaschool.data.util.ProjectItem
import com.github.bkmbigo.fundaschool.data.util.ProjectItem.Companion.toProjectItem
import com.github.bkmbigo.fundaschool.domain.models.Media
import com.github.bkmbigo.fundaschool.domain.models.Project
import com.github.bkmbigo.fundaschool.domain.repositories.ProjectRepository
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
        val media = project.media.map { mediaItem ->
            mediaReference.add(Media.serializer(), mediaItem).id
        }

        val id = collectionReference.add(ProjectItem.serializer(), project.toProjectItem(media)).id
        collectionReference.document(id).update("id" to id)
    }

    override suspend fun updateProject(project: Project) {
        val media = project.media.map { mediaItem ->
            val id = mediaReference.add(Media.serializer(), mediaItem).id
            mediaReference.document(id).update("id" to id)
            id
        }

        collectionReference.document(project.id).update(
            "name" to project.name,
            "description" to project.description,
            "featured" to project.featured,
            "schools" to project.schools,
            "startDate" to project.startDate.toEpochDays(),
            "completionDate" to project.completionDate.toEpochDays(),
            "targetAmount" to project.targetAmount,
            "currentAmount" to project.currentAmount,
            "donors" to project.donors,
            "media" to media
        )
    }

    override suspend fun deleteProject(project: Project) {
        project.media.forEach { mediaItem ->
            mediaReference.document(mediaItem.id).delete()
        }

        collectionReference.document(project.id).delete()
    }

    override suspend fun getProject(projectId: String): Project =
        collectionReference.document(projectId).get().data<ProjectItem>().generateProject()

    override suspend fun getAllProjects(): List<Project> =
        collectionReference.get().documents.map { it.data<ProjectItem>().generateProject() }

    override fun observeProjects(): Flow<List<Project>> =
        collectionReference.snapshots.map { snapshot ->
            snapshot.documents.map { it.data<ProjectItem>().generateProject() }
        }

    override suspend fun getFeaturedProjects(): List<Project> =
        collectionReference
            .where("featured", true)
            .get()
            .documents
            .map { it.data<ProjectItem>().generateProject() }

    override suspend fun getProjectsInSchool(schoolId: String): List<Project> =
        collectionReference
            .where("schools", null, listOf(schoolId))
            .get()
            .documents
            .map { it.data<ProjectItem>().generateProject() }

    private suspend fun ProjectItem.generateProject(): Project {
        val mediaList = media.map { item ->
            mediaReference.document(item).get().data<Media>()
        }

        return toProject(mediaList)
    }
}