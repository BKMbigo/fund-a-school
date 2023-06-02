package com.github.bkmbigo.fundaschool.data.util

import com.github.bkmbigo.fundaschool.domain.models.Media
import com.github.bkmbigo.fundaschool.domain.models.Project
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
class ProjectItem (
    val id: String,
    val name: String,
    val description: String,
    val featured: Boolean = false,
    val schools: List<String>,
    val startDate: Int,
    val completionDate: Int,
    val targetAmount: Float,
    val currentAmount: Float = 0f,
    val donors: Int = 0,
    val media: List<String>
) {

    fun toProject(mediaList: List<Media>): Project = Project(
        id = id,
        name = name,
        description = description,
        featured = featured,
        schools = schools,
        startDate = LocalDate.fromEpochDays(startDate),
        completionDate = LocalDate.fromEpochDays(completionDate),
        targetAmount = targetAmount,
        currentAmount = currentAmount,
        donors = donors,
        media = mediaList
    )

    companion object {
        fun Project.toProjectItem(mediaList: List<String>): ProjectItem =
            ProjectItem(
                id = id,
                name = name,
                description = description,
                featured = featured,
                schools = schools,
                startDate = startDate.toEpochDays(),
                completionDate = completionDate.toEpochDays(),
                targetAmount = targetAmount,
                currentAmount = currentAmount,
                donors = donors,
                media = mediaList
            )

    }
}