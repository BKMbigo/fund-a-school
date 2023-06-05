package com.github.bkmbigo.fundaschool.data.firebase.repositories.utils

import com.github.bkmbigo.fundaschool.domain.models.firebase.School
import dev.gitlive.firebase.firestore.DocumentSnapshot

expect fun School.encodeSchool(): Any
expect fun DocumentSnapshot.decodeSchool(): School?