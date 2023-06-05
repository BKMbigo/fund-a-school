package com.github.bkmbigo.fundaschool.data.firebase.repositories.utils

import com.github.bkmbigo.fundaschool.domain.models.firebase.Project
import dev.gitlive.firebase.firestore.DocumentSnapshot
import dev.gitlive.firebase.firestore.rethrow
import kotlin.js.json

actual fun Project.encodeProject(): Any = json(
    "id" to id,
    "name" to name,
    "description" to description,
    "featured" to featured,
    "schools" to schools,
    "startDate" to startDate,
    "completionDate" to completionDate,
    "targetAmount" to targetAmount,
    "currentAmount" to currentAmount,
    "donors" to donors,
    "mediaUrl" to mediaUrl
)

actual fun DocumentSnapshot.decodeProject(): Project? = rethrow {
    Project(
        id = get("id") as String,
        name = get("name") as String,
        description = get("description") as String,
        featured = get("featured") as Boolean,
        schools = get("schools") as String,
        startDate = get("startDate") as Int,
        completionDate = get("completionDate") as Int,
        targetAmount = get("targetAmount") as Float,
        currentAmount = get("currentAmount") as Float,
        donors = get("donors") as Int,
        mediaUrl = get("mediaUrl") as String
    )
}