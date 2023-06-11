package com.github.bkmbigo.fundaschool.data.square.models

import com.github.bkmbigo.squareinappkotlin.data.network.dto.payments.ErrorCategory
import com.github.bkmbigo.squareinappkotlin.data.network.dto.payments.ErrorCode
import kotlinx.serialization.Serializable

@Serializable
data class SquareError(
    val category: ErrorCategory,
    val code: ErrorCode,
    val detail: String? = null,
    val field: String? = null
)
