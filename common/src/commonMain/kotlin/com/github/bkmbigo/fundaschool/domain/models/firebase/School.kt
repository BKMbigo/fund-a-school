package com.github.bkmbigo.fundaschool.domain.models.firebase

data class School(
    val id: String = "",
    val name: String = "",
    val location: Location = Location(),
)
