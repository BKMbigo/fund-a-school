package com.github.bkmbigo.fundaschool.domain.models

import com.github.bkmbigo.fundaschool.domain.utils.MediaType
import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val id: String,
    val mediaType: MediaType,
    val url: String
)
