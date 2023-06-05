package com.github.bkmbigo.fundaschool.domain.models.square

import kotlinx.datetime.LocalDate

data class Subscription(
    val id: String,
    val locationId: String,
    val planId: String,
    val startDate: LocalDate,
    val canceledDate: LocalDate,
    val status: SubscriptionStatus,
    val cardId: String
)
