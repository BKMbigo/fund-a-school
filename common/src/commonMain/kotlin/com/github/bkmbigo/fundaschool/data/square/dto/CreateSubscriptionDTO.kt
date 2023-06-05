package com.github.bkmbigo.fundaschool.data.square.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateSubscriptionDTO(
    val idempotency_key: String,
    val location_id: String,
    val plan_id: String,
    val customer_id: String,
    val start_date: String,
    val canceled_date: String,
)
