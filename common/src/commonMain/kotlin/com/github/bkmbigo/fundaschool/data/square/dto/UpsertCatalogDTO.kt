package com.github.bkmbigo.fundaschool.data.square.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpsertCatalogDTO(
    val idempotency_key: String,
    @SerialName("object")
    val _object: EntryCatalogObjectDTO
)
