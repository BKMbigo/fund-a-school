package com.github.bkmbigo.fundaschool.data.firebase.repositories.utils

import com.github.bkmbigo.fundaschool.domain.models.firebase.Project
import dev.gitlive.firebase.firestore.DocumentSnapshot

expect fun Project.encodeProject(): Any
expect fun DocumentSnapshot.decodeProject(): Project?