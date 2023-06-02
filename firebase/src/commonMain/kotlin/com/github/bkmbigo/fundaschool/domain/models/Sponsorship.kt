package com.github.bkmbigo.fundaschool.domain.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Sponsorship(
    val id: String,
    val title: String,
    val description: String,
    val targetAmount: Float,
    val currentAmount: Float,
    val dueDate: LocalDate,
    val media: List<Media>
)