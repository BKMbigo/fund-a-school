package com.github.bkmbigo.fundaschool.domain.models.square

import kotlinx.datetime.Instant

data class Payment(
    val id: String,
    val createdAt: Instant,
    val amount: Float,
    val customerId: String,
    val referenceId: String,
    val emailAddress: String
)
