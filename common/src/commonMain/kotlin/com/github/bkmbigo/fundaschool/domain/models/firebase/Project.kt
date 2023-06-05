package com.github.bkmbigo.fundaschool.domain.models.firebase

import kotlinx.datetime.Clock
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
)
