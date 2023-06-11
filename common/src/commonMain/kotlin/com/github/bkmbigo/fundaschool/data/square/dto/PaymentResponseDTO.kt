package com.github.bkmbigo.fundaschool.data.square.dto

import com.github.bkmbigo.fundaschool.data.square.models.SquareError
import kotlinx.serialization.Serializable

@Serializable
data class PaymentResponseDTO(
    val errors: List<SquareError>? = emptyList(),
    val payment: PaymentsDTO? = null
)
