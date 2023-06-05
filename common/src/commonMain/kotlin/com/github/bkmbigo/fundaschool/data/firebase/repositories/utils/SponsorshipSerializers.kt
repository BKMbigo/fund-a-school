package com.github.bkmbigo.fundaschool.data.firebase.repositories.utils

import com.github.bkmbigo.fundaschool.domain.models.firebase.Sponsorship
import dev.gitlive.firebase.firestore.DocumentSnapshot

expect fun Sponsorship.encodeSponsorship(): Any
expect fun DocumentSnapshot.decodeSponsorship(): Sponsorship?