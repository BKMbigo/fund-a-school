package com.github.bkmbigo.fundaschool.data.square.dto

import kotlinx.serialization.Serializable

@Serializable
data class MoneyDTO(
    val amount: Float,
    val currency: String = "USD"
)
