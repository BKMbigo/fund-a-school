package com.github.bkmbigo.fundaschool.data.firebase.repositories.utils

import com.github.bkmbigo.fundaschool.domain.models.firebase.Sponsorship
import com.google.firebase.firestore.ktx.toObject
import dev.gitlive.firebase.firestore.DocumentSnapshot

actual fun Sponsorship.encodeSponsorship(): Any = this
actual fun DocumentSnapshot.decodeSponsorship(): Sponsorship? = android.toObject()