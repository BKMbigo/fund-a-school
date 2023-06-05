package com.github.bkmbigo.fundaschool.data.firebase.repositories.utils

import com.github.bkmbigo.fundaschool.domain.models.firebase.User
import com.google.firebase.firestore.ktx.toObject
import dev.gitlive.firebase.firestore.DocumentSnapshot

actual fun User.encodeUser(): Any = this
actual fun DocumentSnapshot.decodeUser(): User? = android.toObject()