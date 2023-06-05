package com.github.bkmbigo.fundaschool.data.firebase.repositories.utils

import com.github.bkmbigo.fundaschool.domain.models.firebase.Location
import com.github.bkmbigo.fundaschool.domain.models.firebase.School
import dev.gitlive.firebase.firestore.DocumentSnapshot
import dev.gitlive.firebase.firestore.rethrow
import kotlin.js.json

actual fun School.encodeSchool(): Any = json(
    "id" to id,
    "name" to name,
    "location" to location,
)

actual fun DocumentSnapshot.decodeSchool(): School? = rethrow {
    School(
        id = get("id") as String,
        name = get("name") as String,
        location = get("location") as Location /*This would not work*/
    )
}