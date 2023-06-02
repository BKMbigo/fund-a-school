package com.github.bkmbigo.fundaschool.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class School(
    val id: String,
    val name: String,
    val location: Location,
)
