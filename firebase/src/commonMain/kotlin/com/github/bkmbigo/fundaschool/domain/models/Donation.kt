package com.github.bkmbigo.fundaschool.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Donation (
    val id: String,
    val userId: String,
    val projectId: String,
    val amount: Float
)
