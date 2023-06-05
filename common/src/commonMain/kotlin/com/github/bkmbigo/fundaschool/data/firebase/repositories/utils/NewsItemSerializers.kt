package com.github.bkmbigo.fundaschool.data.firebase.repositories.utils

import com.github.bkmbigo.fundaschool.data.firebase.util.NewsItem
import dev.gitlive.firebase.firestore.DocumentSnapshot

expect fun NewsItem.encodeNewsItem(): Any
expect fun DocumentSnapshot.decodeNewsItem(): NewsItem?