package com.github.bkmbigo.fundaschool.domain.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val id: String,
    val name: String,
    val description: String,
    val featured: Boolean = false,
    val schools: List<String>,
    val startDate: LocalDate,
    val completionDate: LocalDate,
    val targetAmount: Float,
    val currentAmount: Float = 0f,
    val donors: Int = 0,
    val media: List<Media>,
)
