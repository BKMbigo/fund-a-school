package com.github.bkmbigo.fundaschool.data.firebase.repositories.utils

import com.github.bkmbigo.fundaschool.domain.models.firebase.Donation
import com.google.firebase.firestore.ktx.toObject
import dev.gitlive.firebase.firestore.DocumentSnapshot

actual fun Donation.encodeDonation(): Any = this

actual fun DocumentSnapshot.decodeDonation(): Donation? =
    this.android.toObject<Donation>()
