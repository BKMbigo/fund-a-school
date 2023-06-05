package com.github.bkmbigo.fundaschool.data.firebase.repositories.utils

import com.github.bkmbigo.fundaschool.domain.models.firebase.Donation
import com.github.bkmbigo.fundaschool.domain.models.firebase.Location
import dev.gitlive.firebase.firestore.DocumentSnapshot
import dev.gitlive.firebase.firestore.rethrow
import kotlin.js.json

actual fun Donation.encodeDonation(): Any = json(
    "id" to id,
    "projectId" to projectId,
    "userId" to userId,
    "timestamp" to timestamp,
    "amount" to amount
)

actual fun DocumentSnapshot.decodeDonation(): Donation? = rethrow {
    Donation(
        id = get("id") as String,
        projectId = get("projectId") as String,
        userId = get("userId") as String?,
        timestamp = get("timestamp") as Long,
        amount = get("amount") as Float
    )
}

actual fun Location.encodeLocation(): Any = json(
    "longitude" to longitude,
    "latitude" to latitude
)
actual fun DocumentSnapshot.decodeLocation(): Location?  = rethrow {
    Location(
        latitude = get("latitude") as Float,
        longitude = get("longitude") as Float
    )
}