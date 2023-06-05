package com.github.bkmbigo.fundaschool.data.firebase.repositories.utils

import com.github.bkmbigo.fundaschool.domain.models.firebase.Sponsorship
import dev.gitlive.firebase.firestore.DocumentSnapshot
import kotlin.js.json

actual fun Sponsorship.encodeSponsorship(): Any = json(
    "id" to id,
    "title" to title,
    "caption" to caption,
    "text" to text,
    "targetAmount" to targetAmount,
    "currentAmount" to currentAmount,
    "activeSponsors" to activeSponsors,
    "dueDate" to dueDate,
    "media" to mediaUrl
)

actual fun DocumentSnapshot.decodeSponsorship(): Sponsorship?  = Sponsorship(
    id = get("id") as String,
    title = get("title") as String,
    caption = get("caption") as String,
    text = get("text") as String,
    targetAmount = get("targetAmount") as Float,
    currentAmount = get("currentAmount") as Float,
    activeSponsors = get("activeSponsors") as Int,
    dueDate = get("dueDate") as Int,
    mediaUrl = get("media") as String
)