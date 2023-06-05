package com.github.bkmbigo.fundaschool.data.firebase.repositories.utils

import com.github.bkmbigo.fundaschool.domain.models.firebase.Donation
import dev.gitlive.firebase.firestore.DocumentSnapshot

expect fun Donation.encodeDonation(): Any
expect fun DocumentSnapshot.decodeDonation(): Donation?