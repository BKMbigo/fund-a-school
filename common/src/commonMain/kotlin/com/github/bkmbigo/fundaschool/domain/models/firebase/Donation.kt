package com.github.bkmbigo.fundaschool.domain.models.firebase

import dev.gitlive.firebase.firestore.QueryDocumentSnapshot
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant


data class Donation (
    val id: String = "",
    val projectId: String = "",
    val userId: String? = null,
    val timestamp: Long = Clock.System.now().toEpochMilliseconds(),
    val amount: Float = 0f
)