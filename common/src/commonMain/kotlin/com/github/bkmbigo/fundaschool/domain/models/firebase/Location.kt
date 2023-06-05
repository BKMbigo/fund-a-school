package com.github.bkmbigo.fundaschool.domain.models.firebase

import dev.gitlive.firebase.firestore.QueryDocumentSnapshot


data class Location (
    val latitude: Float = 0f,
    val longitude: Float = 0f
)
