package com.github.bkmbigo.fundaschool.data.firebase.repositories.utils

import com.github.bkmbigo.fundaschool.domain.models.firebase.Location
import dev.gitlive.firebase.firestore.DocumentSnapshot

expect fun Location.encodeLocation(): Any
expect fun DocumentSnapshot.decodeLocation(): Location?