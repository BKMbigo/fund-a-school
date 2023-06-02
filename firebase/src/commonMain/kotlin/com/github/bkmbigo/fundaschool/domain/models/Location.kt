package com.github.bkmbigo.fundaschool.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Location (
    val latitude: Long,
    val longitude: Long
)
