package com.github.bkmbigo.fundaschool.data.firebase.repositories.utils

import com.github.bkmbigo.fundaschool.domain.models.firebase.Location
import com.google.firebase.firestore.ktx.toObject
import dev.gitlive.firebase.firestore.DocumentSnapshot

actual fun Location.encodeLocation(): Any = this

actual fun DocumentSnapshot.decodeLocation(): Location? = android.toObject()