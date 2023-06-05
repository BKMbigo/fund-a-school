package com.github.bkmbigo.fundaschool.data.firebase.repositories.utils

import com.github.bkmbigo.fundaschool.domain.models.firebase.User
import dev.gitlive.firebase.firestore.DocumentSnapshot
import dev.gitlive.firebase.rethrow
import kotlin.js.json

actual fun User.encodeUser(): Any = json(
    "id" to id,
    "name" to name,
    "photoUrl" to photoUrl,
    "admin" to admin,
    "customerId" to customerId
)

actual fun DocumentSnapshot.decodeUser(): User? = rethrow {
    User(
        id = get("id") as String,
        name = get("name") as String,
        photoUrl = get("photoUrl") as String?,
        admin = get("admin") as Boolean,
        customerId = get("customerId") as String?
    )
}