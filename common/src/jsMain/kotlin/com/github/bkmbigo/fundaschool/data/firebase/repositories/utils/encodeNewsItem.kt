package com.github.bkmbigo.fundaschool.data.firebase.repositories.utils

import com.github.bkmbigo.fundaschool.data.firebase.util.NewsItem
import com.github.bkmbigo.fundaschool.domain.utils.NewsCategory
import dev.gitlive.firebase.firestore.DocumentSnapshot
import dev.gitlive.firebase.firestore.rethrow
import kotlin.js.json

actual fun NewsItem.encodeNewsItem(): Any = json(
    "id" to id,
    "title" to id,
    "caption" to caption,
    "category" to category.name,
    "uploadDate" to uploadDate,
    "text" to text,
    "author" to author,
    "associatedProject" to associatedProject,
    "mediaUrl" to mediaUrl
)

actual fun DocumentSnapshot.decodeNewsItem(): NewsItem? = rethrow {
    NewsItem(
        id = get("id") as String,
        title = get("title") as String,
        caption = get("caption") as String,
        category = when(get("category") as String){
            NewsCategory.GENERAL_NEWS.name -> NewsCategory.GENERAL_NEWS
            NewsCategory.COMPLETED_PROJECTS.name -> NewsCategory.COMPLETED_PROJECTS
            NewsCategory.ACHIEVEMENTS.name -> NewsCategory.ACHIEVEMENTS
            NewsCategory.SUCCESS_STORIES.name -> NewsCategory.SUCCESS_STORIES
            NewsCategory.DONATIONS.name -> NewsCategory.DONATIONS
            NewsCategory.UPDATE_ON_PROJECTS.name -> NewsCategory.UPDATE_ON_PROJECTS
            else -> NewsCategory.NEW_PROJECTS
        },
        uploadDate = get("uploadDate") as Int,
        text = get("text") as String,
        author = get("author") as String,
        associatedProject = get("associatedProject") as String?,
        mediaUrl = get("mediaUrl") as String
    )
}