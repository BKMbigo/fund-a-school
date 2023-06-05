package com.github.bkmbigo.fundaschool.data.firebase.repositories.utils

import com.github.bkmbigo.fundaschool.domain.models.firebase.User
import dev.gitlive.firebase.firestore.DocumentSnapshot

expect fun User.encodeUser(): Any
expect fun DocumentSnapshot.decodeUser(): User?