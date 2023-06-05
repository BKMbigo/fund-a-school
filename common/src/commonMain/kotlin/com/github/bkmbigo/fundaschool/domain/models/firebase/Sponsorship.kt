package com.github.bkmbigo.fundaschool.domain.models.firebase

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


data class Sponsorship(
    val id: String = "",
    val title: String = "",
    val caption: String = "",
    val text: String = "",
    val targetAmount: Float = 0f,
    val currentAmount: Float = 0f,
    val activeSponsors: Int = 0,
    val dueDate: Int = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.toEpochDays(),
    val mediaUrl: String = ""
)