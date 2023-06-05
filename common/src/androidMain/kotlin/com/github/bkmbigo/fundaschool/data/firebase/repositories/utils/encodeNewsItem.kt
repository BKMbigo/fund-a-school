package com.github.bkmbigo.fundaschool.data.firebase.repositories.utils

import com.github.bkmbigo.fundaschool.data.firebase.util.NewsItem
import com.google.firebase.firestore.ktx.toObject
import dev.gitlive.firebase.firestore.DocumentSnapshot

actual fun NewsItem.encodeNewsItem(): Any = this
actual fun DocumentSnapshot.decodeNewsItem(): NewsItem? = android.toObject()