package com.github.bkmbigo.fundaschool.data.square.dto

import com.github.bkmbigo.fundaschool.domain.models.square.SubscriptionStatus
import kotlinx.serialization.Serializable

@Serializable
data class SubscriptionDTO(
    val id: String,
    val location_id: String,
    val plan_id: String,
    val customer_id: String,
    val start_date: String,
    val canceled_date: String,
    val status: SubscriptionStatus,
    val invoice_ids: List<String>,
    val card_id: String
)