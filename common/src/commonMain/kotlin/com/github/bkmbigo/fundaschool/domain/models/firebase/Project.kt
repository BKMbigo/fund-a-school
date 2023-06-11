package com.github.bkmbigo.fundaschool.domain.models.firebase

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class Project(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val featured: Boolean = false,
    val schools: String = "",
    val startDate: Int = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.toEpochDays(),
    val completionDate: Int = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.toEpochDays(),
    val targetAmount: Float = 0f,
    val currentAmount: Float = 0f,
    val donors: Int = 0,
    val mediaUrl: String = "",
) {


    companion object {
        private val todayDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.toEpochDays()
        // Find a more efficient solution to getting default completion date
        private val defaultCompletionDate = Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault()).date.let { LocalDate.fromEpochDays(it.toEpochDays() + 28) }.toEpochDays()


        val EmptyProject = Project(
            id = "",
            name = "",
            description = "",
            featured = false,
            schools = "",
            startDate = todayDate,
            completionDate = defaultCompletionDate,
            targetAmount = 0.0f,
            currentAmount = 0.0f,
            donors = 0,
            mediaUrl = ""
        )
    }
}
