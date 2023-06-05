package com.github.bkmbigo.fundaschool.data.firebase.util

import com.github.bkmbigo.fundaschool.domain.models.firebase.News
import com.github.bkmbigo.fundaschool.domain.models.firebase.Project
import com.github.bkmbigo.fundaschool.domain.utils.NewsCategory
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

data class NewsItem(
    val id: String = "",
    val title: String = "",
    val caption: String = "",
    val category: NewsCategory = NewsCategory.NEW_PROJECTS,
    val uploadDate: Int = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date.toEpochDays(),
    val text: String = "",
    val author: String = "",
    val associatedProject: String? = null,
    val mediaUrl: String = ""
) {

    fun toNews(associatedProject: Project?): News = News(
        id = id,
        title = title,
        caption = caption,
        category = category,
        uploadDate = LocalDate.fromEpochDays(uploadDate),
        text = text,
        author = author,
        associatedProject = associatedProject,
        mediaUrl = mediaUrl
    )

    companion object {
        fun News.toNewsItem(associatedProject: String?): NewsItem =
            NewsItem(
                id = id,
                title = title,
                caption = caption,
                category = category,
                uploadDate = uploadDate.toEpochDays(),
                text = text,
                author = author,
                associatedProject = associatedProject,
                mediaUrl = mediaUrl
            )

    }
}
